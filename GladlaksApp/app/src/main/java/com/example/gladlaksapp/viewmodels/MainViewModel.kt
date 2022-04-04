package com.example.gladlaksapp.viewmodels

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
import java.time.LocalDate
import java.time.Year
import java.time.temporal.WeekFields
import java.util.*

class MainViewModel: ViewModel() {
    private val barentsWatchRepo = BarentswatchRepository
    private val norKystRepo = NorKystRepository
    private val now = LocalDate.now()
    private val week = now.get(WeekFields.of(Locale.GERMANY).weekOfYear())
    private val year = now.year
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
                year = year,//now.get(Calendar.YEAR),
                week = week, // now.get(Calendar.WEEK_OF_YEAR),
            )
            localityDetail.postValue(details)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = barentsWatchRepo.getLocalities(
                //TODO No hardcoded values!!
                year = year,//now.get(Calendar.YEAR),
                week = week,//now.get(Calendar.WEEK_OF_YEAR),
            )
            localities.postValue(data.localities)
        }
    }
}