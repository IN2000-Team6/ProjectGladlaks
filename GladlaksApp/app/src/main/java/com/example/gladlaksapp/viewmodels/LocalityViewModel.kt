package com.example.gladlaksapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.database.FavoriteLocality
import com.example.gladlaksapp.models.database.FavoriteRepository
import com.example.gladlaksapp.models.database.LocalityDatabase
import com.example.gladlaksapp.models.database.LocalityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LocalityViewModel(application: Application): AndroidViewModel(application) {

    //Room queries to be run in coroutine
//    private val backgroundScope: CoroutineContext = customCoroutineScope.coroutineContext + viewModelScope.coroutineContext

    private val getAllLocalities: Flow<List<Locality>>
    private val localityRepository: LocalityRepository

    private val getAllFavorites: List<FavoriteLocality>
    private val favoriteRepository: FavoriteRepository


    /*fun getAll(){
        viewModelScope.launch (Dispatchers.IO) {

            try {
                localityRepository.getAll().collect { value ->
                    println("Received $value")
                }
            } catch (e: Exception) {
                println("The flow has thrown an exception: $e")

            }
        }
    }*/

    private fun insertAll(localities: List<Locality>){
        viewModelScope.launch(Dispatchers.IO){
            localityRepository.insertAll(localities)
        }
    }

    fun insertFavorite(favorite: FavoriteLocality){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.insertFavorite(favorite)
        }
    }

    init{
        val localityDao = LocalityDatabase.getDatabase(application).localityDao()
        localityRepository = LocalityRepository(localityDao)
        getAllLocalities = localityRepository.getAll()

        val favoriteDao = LocalityDatabase.getDatabase(application).favoriteDao()
        favoriteRepository = FavoriteRepository(favoriteDao)
        getAllFavorites = favoriteRepository.getAll()

        viewModelScope.launch(Dispatchers.IO) {  }
    }
}
