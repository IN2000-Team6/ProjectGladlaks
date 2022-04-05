package com.example.gladlaksapp.models.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gladlaksapp.models.Locality
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalityDao {

    @Query("SELECT * FROM localities")
    fun getAll() : List<Locality>

    @Query("SELECT * FROM localities JOIN favorites ON localities.locality_no == favorites.locality_no")
    fun getFavorites() : List<Locality>

    @Query("SELECT * FROM localities WHERE locality_no LIKE :input")
    suspend fun getByNo(input: Int) : List<Locality>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocality(vararg localities: Locality)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(vararg favorite: FavoriteLocality)

}