package com.example.gladlaksapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.gladlaksapp.datasources.BarentswatchRepository
import com.example.gladlaksapp.datasources.NorKystRepository
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatryk.vico.core.entry.FloatEntry
import com.example.gladlaksapp.models.GraphLine
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.database.FavoriteLocality
import com.example.gladlaksapp.models.database.FavoriteRepository
import com.example.gladlaksapp.models.database.LocalityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*
import javax.inject.Inject

@HiltViewModel
 class LocalityViewModel @Inject constructor(
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
    val favoriteLocalities = favoriteRepository.loadedFavoritesFlow.asLiveData()
    val localityDetail = MutableLiveData<LocalityDetailsWrapper?>()
    val localityTemps = MutableLiveData<List<GraphLine>>()
    val groupedChartProducer = ChartEntryModelProducer() // Louse chart data goes here - similar to LiveData

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

            // Fetch generation data
            loadLouseData(locality.localityNo, 2020,year)
        }
    }

    /**
     * Returns a list of lists containing [FloatEntry]
     * @param localityNo localityNo as given by BarentsWatch
     * @param gen1 the first year to compare
     * @param gen2 the second year to compare
     */
    fun loadLouseData(localityNo: Int, gen1: Int, gen2: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val allData = barentsWatchRepo.getTwoGenerations(localityNo,gen1,gen2)
            groupedChartProducer.setEntries(allData)
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
