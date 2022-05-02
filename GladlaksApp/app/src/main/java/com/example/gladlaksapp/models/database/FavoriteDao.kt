package com.example.gladlaksapp.models.database

import androidx.room.*
import com.example.gladlaksapp.models.Locality
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteLocality)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorites(favorites: List<FavoriteLocality>)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteLocality)

    //@Query("SELECT * FROM localities JOIN favorites ON localities.locality_no == favorites.locality_no")
    @Query("SELECT * FROM localities JOIN favorites ON favorites.locality_no == localities.locality_no")
    suspend fun getFavorites() : List<Locality>

    @Query("SELECT * FROM favorites")
    suspend fun getAll() : List<FavoriteLocality>

}