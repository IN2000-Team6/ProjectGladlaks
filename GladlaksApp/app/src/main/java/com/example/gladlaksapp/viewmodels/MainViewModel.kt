package com.example.gladlaksapp.viewmodels

import android.util.Log
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gladlaksapp.datasources.BarentswatchRepository
import com.example.gladlaksapp.datasources.NorKystRepository
import com.example.gladlaksapp.models.GraphLine
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel: ViewModel() {
    private val barentsWatchRepo = BarentswatchRepository
    private val norKystRepo = NorKystRepository
    private val now = java.util.Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"))
    //TODO Fix the calendar being off 1 week

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
                //TODO No hardcoded values!!
                localityNo = locality.localityNo,
                year = /*2022,*/now.get(java.util.Calendar.YEAR),
                week = /*13, */ now.get(java.util.Calendar.WEEK_OF_YEAR)-1,
            )
            localityDetail.postValue(details)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = barentsWatchRepo.getLocalities(
                //TODO No hardcoded values!!
                year = /*2022,*/now.get(java.util.Calendar.YEAR),
                week = /*13, */ now.get(java.util.Calendar.WEEK_OF_YEAR)-1,
            )
            localities.postValue(data.localities)
        }
    }
}