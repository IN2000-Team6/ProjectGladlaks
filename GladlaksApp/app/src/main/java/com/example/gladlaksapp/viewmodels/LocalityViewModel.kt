package com.example.gladlaksapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.database.FavoriteLocality
import com.example.gladlaksapp.models.database.FavoriteRepository
import com.example.gladlaksapp.models.database.LocalityDatabase
import com.example.gladlaksapp.models.database.LocalityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class LocalityViewModel @Inject constructor(
    //TODO Include saved state handle?
    private val localityRepository: LocalityRepository,
): ViewModel() {


    //private val getAllLocalities: Flow<List<Locality>>
    //private val localityRepository: LocalityRepository

    private val _state = MutableStateFlow(LocalityViewState())
    private val _selectedLocality = MutableStateFlow<Locality?>(null)

    val state: StateFlow<LocalityViewState>
        get() = _state

    fun onLocalitySelected(locality: Locality) {
        _selectedLocality.value = locality
    }

    init{


        viewModelScope.launch {
            combine(
                localityRepository.localities().onEach { localities ->
                    if (localities.isNotEmpty() && _selectedLocality.value == null){
                        _selectedLocality.value = localities[0]
                    }
                },
                _selectedLocality,
            ){ localities, selectedLocality ->
                LocalityViewState(
                    localities = localities,
                    selectedLocality = selectedLocality,
                )
            }.collect { _state.value = it }
        }

        //addLocalitiesToDb()
    }

    /*private fun addLocalitiesToDb(){
        // DUMMY DATA
        val testLocalities = mutableListOf(
            Locality(localityNo = 1, name="a", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
            Locality(localityNo = 2, name="b", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
            Locality(localityNo = 3, name="c", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = true),
            Locality(localityNo = 4, name="d", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
            Locality(localityNo = 5, name="e", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = true),
            Locality(localityNo = 6, name="f", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
        )

        viewModelScope.launch {
            localityRepository.insertAll(testLocalities)
        }

    }*/
}

data class LocalityViewState(
    val localities: List<Locality> = emptyList(),
    val selectedLocality: Locality? = null
)