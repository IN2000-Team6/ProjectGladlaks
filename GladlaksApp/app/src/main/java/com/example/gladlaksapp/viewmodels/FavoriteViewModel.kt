package com.example.gladlaksapp.viewmodels

import androidx.lifecycle.*
import com.example.gladlaksapp.models.FavoriteLocality
import com.example.gladlaksapp.repositories.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
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
