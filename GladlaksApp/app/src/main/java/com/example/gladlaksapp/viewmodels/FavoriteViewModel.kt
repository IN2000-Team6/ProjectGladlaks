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
class FavoriteViewModel @Inject constructor(
    //TODO Include saved state handle?
    private val localityRepository: LocalityRepository,
): ViewModel() {

    init{
        viewModelScope.launch {
        }

    }

}

data class LocalityViewState(
    val localities: List<Locality> = emptyList(),
    val selectedLocality: Locality? = null
)