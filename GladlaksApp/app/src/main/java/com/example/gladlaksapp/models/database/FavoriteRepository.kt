package com.example.gladlaksapp.models.database

class FavoriteRepository (
    private val favoriteDao: FavoriteDao
) {

    suspend fun getAll() = favoriteDao.getAll()

    fun getFavoriteLocalities() = favoriteDao.getFavorites()

    suspend fun insertFavorite(favorite: FavoriteLocality) = favoriteDao.insertFavorite(favorite)

    suspend fun deleteFavorite(favorite: FavoriteLocality) = favoriteDao.deleteFavorite(favorite)
}