package com.example.gladlaksapp.models

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("UPDATE favorites SET isFavorite = 1 WHERE locality_no == :localityNo")
    suspend fun addFavorite(localityNo: Int)

    @Query("UPDATE favorites SET isFavorite = 0 WHERE locality_no == :localityNo")
    suspend fun deleteFavorite(localityNo: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorites(favorites: List<FavoriteLocality>)

    @Query("SELECT * FROM favorites JOIN localities ON favorites.locality_no == localities.locality_no WHERE favorites.isFavorite == 1")
    fun getFavoriteLocalities() : Flow<List<Locality>>

    @Query("SELECT * FROM favorites")
    fun getAll() : Flow<List<FavoriteLocality>>

    @Query("SELECT * FROM favorites WHERE locality_no LIKE :localityNo LIMIT 1")
    fun getFavorite(localityNo: Int) : FavoriteLocality

}