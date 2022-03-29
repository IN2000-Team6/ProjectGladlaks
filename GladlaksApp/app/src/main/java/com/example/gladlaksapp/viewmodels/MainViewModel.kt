package com.example.gladlaksapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gladlaksapp.datasources.BarentswatchNetworkDataSource
import com.example.gladlaksapp.models.Locality
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val barentswatchDataSource = BarentswatchNetworkDataSource
    val localities = MutableLiveData<List<Locality>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = barentswatchDataSource.getLocalities(2022, 12)
            localities.postValue(data.localities)
        }
    }
}