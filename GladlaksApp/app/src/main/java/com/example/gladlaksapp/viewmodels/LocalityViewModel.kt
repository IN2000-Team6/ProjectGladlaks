package com.example.gladlaksapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gladlaksapp.models.database.FavoriteLocality
import com.example.gladlaksapp.models.database.FavoriteRepository
import com.example.gladlaksapp.models.database.LocalityDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalityViewModel(application: Application): AndroidViewModel(application) {

    private val getAllFavorites: List<FavoriteLocality>
    private val favoriteRepository: FavoriteRepository

    fun insertFavorite(favorite: FavoriteLocality){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.insertFavorite(favorite)
        }
    }

    init{
        val favoriteDao = LocalityDatabase.getDatabase(application).favoriteDao()
        favoriteRepository = FavoriteRepository(favoriteDao)
        getAllFavorites = favoriteRepository.getAll()
    }
}

class LocalityViewModelFactory(application: Application): ViewModelProvider.Factory{
    private val app = application

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel?> create (modelClass: Class<T>): T {
        return LocalityViewModel(app) as T
    }
}