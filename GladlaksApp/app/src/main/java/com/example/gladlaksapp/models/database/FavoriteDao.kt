package com.example.gladlaksapp.models.database

import android.util.MutableDouble
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.gladlaksapp.models.Locality
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    //suspend fun insertFavorite(favorite: FavoriteLocality)

    @Query("UPDATE favorites SET isFavorite = 1 WHERE locality_no = :localityNo")
    suspend fun addFavorite(localityNo: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorites(favorites: List<FavoriteLocality>)

    //@Delete
    //suspend fun deleteFavorite(favorite: FavoriteLocality)

    @Query("UPDATE favorites SET isFavorite = 0 WHERE locality_no = :localityNo")
    suspend fun deleteFavorite(localityNo: Int)

    //@Query("SELECT * FROM localities JOIN favorites ON localities.locality_no == favorites.locality_no")
    @Query("SELECT * FROM localities JOIN favorites ON favorites.locality_no == localities.locality_no")
    fun getFavorites() : LiveData<List<Locality>>

    @Query("SELECT * FROM favorites")
    suspend fun getAll() : List<FavoriteLocality>

    @Query("SELECT isFavorite FROM favorites WHERE locality_no = :localityNo")
    suspend fun checkIfFavorite(localityNo: Int): Boolean
}