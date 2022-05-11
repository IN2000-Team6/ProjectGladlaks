package com.example.gladlaksapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gladlaksapp.datasources.BarentswatchRepository
import com.example.gladlaksapp.datasources.NorKystRepository
import com.example.gladlaksapp.models.GraphLine
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.database.FavoriteLocality
import com.example.gladlaksapp.models.database.FavoriteRepository
import com.example.gladlaksapp.models.database.LocalityDatabase
import com.example.gladlaksapp.models.database.LocalityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //TODO Include saved state handle?
    private val localityRepository: LocalityRepository,
    private val favoriteRepository: FavoriteRepository,
): ViewModel() {

    private val barentsWatchRepo = BarentswatchRepository
    private val norKystRepo = NorKystRepository
    private val now = LocalDate.now()
    private val week = now.get(WeekFields.of(Locale.GERMANY).weekOfYear())
    private val year = now.year

    val localities = MutableLiveData<List<Locality>>()
    val localityDetail = MutableLiveData<LocalityDetailsWrapper?>()
    val localityTemps = MutableLiveData<List<GraphLine>>()

    fun resetLoadedLocality() = localityDetail.postValue(null)

    fun loadLocalityDetails(locality: Locality) {
        viewModelScope.launch(Dispatchers.IO) {
            val temps = norKystRepo.getLocalityTemperature(
                lat = locality.lat,
                lon = locality.lon,
            )
            localityTemps.postValue(temps)

            val details = barentsWatchRepo.getDetailedLocalityInfo(
                localityNo = locality.localityNo,
                year = year,
                week = week,
            )
            localityDetail.postValue(details)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = barentsWatchRepo.getLocalitiesInWater(
                year = year,
                week = week,
            )
            localities.postValue(data)

            //TODO Check if database updates properly - do past localities get deleted?
            localityRepository.insertAll(data)
            //TODO favorites column likely gets overwritten
            favoriteRepository.insertFavorites( data.map{FavoriteLocality(it.localityNo,false)} )
        }
    }
}
