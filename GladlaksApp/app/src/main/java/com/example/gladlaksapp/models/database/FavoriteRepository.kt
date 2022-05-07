package com.example.gladlaksapp.models.database

class FavoriteRepository (
    private val favoriteDao: FavoriteDao
) {
    suspend fun getAll() = favoriteDao.getAll()

    fun getFavoriteLocalities() = favoriteDao.getFavorites()

    suspend fun insertFavorites(favorites: List<FavoriteLocality>) = favoriteDao.insertFavorites(favorites)

    suspend fun checkIfFavorite(localityNo: Int) = favoriteDao.checkIfFavorite(localityNo)

    suspend fun addFavorite(localityNo: Int) = favoriteDao.addFavorite(localityNo)

    suspend fun deleteFavorite(localityNo: Int) = favoriteDao.deleteFavorite(localityNo)
}