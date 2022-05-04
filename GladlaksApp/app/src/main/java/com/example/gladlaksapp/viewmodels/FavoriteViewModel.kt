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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    //TODO Include saved state handle?
    private val favoriteRepository: FavoriteRepository,
): ViewModel() {
    lateinit var favorites: List<FavoriteLocality>

    private suspend fun getAll(){
        coroutineScope {
            favorites = favoriteRepository.getAll()
        }
    }

    suspend fun addFavoriteToDb(favoriteLocality: FavoriteLocality?){
        coroutineScope {
            if (favoriteLocality != null) {
                favoriteRepository.insertFavorite(favoriteLocality)
            }
            //TODO Throw exception
        }
    }

    suspend fun deleteFavorite(favoriteLocality: FavoriteLocality?){
        coroutineScope {
            if (favoriteLocality != null) {
                favoriteRepository.deleteFavorite(favoriteLocality)
            }
            //TODO Throw exception
        }
    }

    suspend fun isFavorite(locality: Locality): Boolean {
        getAll()
        val favorite = FavoriteLocality(locality.localityNo)
        return favorite in favorites
    }
}