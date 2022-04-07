package com.example.gladlaksapp.models.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gladlaksapp.models.Locality
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: FavoriteLocality)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorites(favorites: List<FavoriteLocality>)

    //@Query("SELECT * FROM localities JOIN favorites ON localities.locality_no == favorites.locality_no")
    @Query("SELECT * FROM localities JOIN favorites ON favorites.locality_no == localities.locality_no")
    fun getFavorites() : List<Locality>

    @Query("SELECT * FROM favorites")
    fun getAll() : List<FavoriteLocality>

}