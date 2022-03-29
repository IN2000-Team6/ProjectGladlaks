package com.example.gladlaksapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gladlaksapp.datasources.BarentswatchNetworkDataSource
import com.example.gladlaksapp.datasources.BarentswatchRepository
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val barentsWatchRepo = BarentswatchRepository
    val localities = MutableLiveData<List<Locality>>()
    val localityDetail = MutableLiveData<LocalityDetailsWrapper>()

    fun loadLocalityDetails(localityNo: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = barentsWatchRepo.getDetailedLocalityInfo(localityNo, 2022, 10)
            localityDetail.postValue(data)
        }
    }

    fun resetLoadedLocality() {
        localityDetail.postValue(null)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = barentsWatchRepo.getLocalities(2022, 12)
            localities.postValue(data.localities)
        }
    }
}