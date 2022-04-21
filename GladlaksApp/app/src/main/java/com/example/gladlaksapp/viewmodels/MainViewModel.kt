package com.example.gladlaksapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gladlaksapp.composables.getRandomEntries
import com.example.gladlaksapp.datasources.BarentswatchRepository
import com.example.gladlaksapp.datasources.NorKystRepository
import com.example.gladlaksapp.models.*
import com.patrykandpatryk.vico.core.entry.ChartEntry
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatryk.vico.core.entry.FloatEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*
import kotlin.random.Random

class MainViewModel: ViewModel() {
    private val barentsWatchRepo = BarentswatchRepository
    private val norKystRepo = NorKystRepository
    private val now = LocalDate.now()
    private val week = now.get(WeekFields.of(Locale.GERMANY).weekOfYear())
    private val year = now.year

    val localities = MutableLiveData<List<Locality>>()
    val localityDetail = MutableLiveData<LocalityDetailsWrapper>()
    val localityTemps = MutableLiveData<List<GraphLine>>()
    val groupedChartProducer= ChartEntryModelProducer()

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

    fun getRandomEntries(n: Int) = List(size = n) {
        0.6f * Random.nextFloat()
    }.mapIndexed { x,y ->
        FloatEntry(
            x = x.toFloat(),
            y = y
        )
    }

    fun loadLouseData(localityNo: Int, gen1: Int, gen2: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val allData = barentsWatchRepo.getTwoGenerations(localityNo,gen1,gen2)
            groupedChartProducer.setEntries(allData)
        }
    }


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = barentsWatchRepo.getLocalities(
                year = year,
                week = week,
            )
            localities.postValue(data.localities)

            //TODO get data when loading locality
            groupedChartProducer.setEntries(
                List(size = 2) { getRandomEntries(Random.nextInt(52)) }
            )
        }
    }
}