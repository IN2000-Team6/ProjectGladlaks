package com.example.gladlaksapp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.database.FavoriteLocality
import com.example.gladlaksapp.models.database.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    //TODO Include saved state handle?
    private val favoriteRepository: FavoriteRepository,
): ViewModel() {

    //TODO add/delete bug
    //TODO livedata boolean isfavorite

    val favorites: LiveData<List<FavoriteLocality>> = favoriteRepository.favoritesFlow.asLiveData()

    suspend fun addFavorite(locality: FavoriteLocality){
        coroutineScope {
            favoriteRepository.addFavorite(locality.localityNo)
        }
    }

    suspend fun deleteFavorite(locality: FavoriteLocality){
        coroutineScope {
            favoriteRepository.deleteFavorite(locality.localityNo)
        }
    }

}
