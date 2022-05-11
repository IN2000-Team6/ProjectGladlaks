package com.example.gladlaksapp.models.database

import kotlinx.coroutines.flow.Flow

class FavoriteRepository (
    private val favoriteDao: FavoriteDao
) {
    val favoritesFlow: Flow<List<FavoriteLocality>>
        get() = favoriteDao.getAll()

    fun getFavorite(localityNo: Int) = favoriteDao.getFavorite(localityNo)

    fun getFavoriteLocalities() = favoriteDao.getFavoriteLocalities()

    suspend fun insertFavorites(favorites: List<FavoriteLocality>) = favoriteDao.insertFavorites(favorites)

    suspend fun addFavorite(localityNo: Int) = favoriteDao.addFavorite(localityNo)

    suspend fun deleteFavorite(localityNo: Int) = favoriteDao.deleteFavorite(localityNo)
}