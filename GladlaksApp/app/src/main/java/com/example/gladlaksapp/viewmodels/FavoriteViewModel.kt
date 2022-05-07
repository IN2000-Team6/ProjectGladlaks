package com.example.gladlaksapp.viewmodels

import android.util.Log
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

    //TODO make livedata boolean observable in button and dont change it

    val favorites = MutableLiveData<List<FavoriteLocality>>()

    init {
        viewModelScope.launch {
            favorites.postValue(favoriteRepository.getAll())
        }
    }

    suspend fun toggleFavorite(locality: Locality){
        coroutineScope {
            if (favoriteRepository.checkIfFavorite(locality.localityNo)){
                favoriteRepository.deleteFavorite(locality.localityNo)
                Log.d("Favorite value", "Deleted!")
            }else{
                favoriteRepository.addFavorite(locality.localityNo)
                Log.d("Favorite value", "Added!")
            }
        }
    }

}
