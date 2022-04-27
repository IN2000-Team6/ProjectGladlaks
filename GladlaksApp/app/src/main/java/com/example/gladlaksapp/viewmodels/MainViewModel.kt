package com.example.gladlaksapp.viewmodels

import android.app.Application
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*

class MainViewModel(application: Application): /*ViewModel(),*/ AndroidViewModel(application) {

    private val getAllLocalities: Flow<List<Locality>>
    private val localityRepository: LocalityRepository

    private val getAllFavorites: List<FavoriteLocality>
    private val favoriteRepository: FavoriteRepository

    private fun insertAll(localities: List<Locality>){
        viewModelScope.launch(Dispatchers.IO){
            localityRepository.insertAll(localities)
        }
    }

    fun insertFavorite(favorite: FavoriteLocality){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.insertFavorite(favorite)
        }
    }

    private val barentsWatchRepo = BarentswatchRepository
    private val norKystRepo = NorKystRepository
    private val now = LocalDate.now()
    private val week = now.get(WeekFields.of(Locale.GERMANY).weekOfYear())
    private val year = now.year

    val localities = MutableLiveData<List<Locality>>()
    val localityDetail = MutableLiveData<LocalityDetailsWrapper>()
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
        val localityDao = LocalityDatabase.getDatabase(application).localityDao()
        localityRepository = LocalityRepository(localityDao)
        getAllLocalities = localityRepository.getAll()

        val favoriteDao = LocalityDatabase.getDatabase(application).favoriteDao()
        favoriteRepository = FavoriteRepository(favoriteDao)
        getAllFavorites = favoriteRepository.getAll()


        viewModelScope.launch(Dispatchers.IO) {
            val data = barentsWatchRepo.getLocalitiesInWater(
                year = year,
                week = week,
            )
            localities.postValue(data)
            insertAll(data)
        }
    }
}
