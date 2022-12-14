package com.example.gladlaksapp.repositories

import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.FavoriteDao
import com.example.gladlaksapp.models.FavoriteLocality
import kotlinx.coroutines.flow.Flow

class FavoriteRepository (
    private val favoriteDao: FavoriteDao
) {
    val favoritesFlow: Flow<List<FavoriteLocality>>
        get() = favoriteDao.getAll()

    val loadedFavoritesFlow: Flow<List<Locality>>
        get() = favoriteDao.getFavoriteLocalities()

    suspend fun insertFavorites(favorites: List<FavoriteLocality>) = favoriteDao.insertFavorites(favorites)

    suspend fun addFavorite(localityNo: Int) = favoriteDao.addFavorite(localityNo)

    suspend fun deleteFavorite(localityNo: Int) = favoriteDao.deleteFavorite(localityNo)
}