package com.example.gladlaksapp.models.database

class FavoriteRepository (
    private val favoriteDao: FavoriteDao
) {

    suspend fun insertFavorite(favorite: FavoriteLocality) = favoriteDao.insertFavorite(favorite)

    fun getAll() = favoriteDao.getAll()

}