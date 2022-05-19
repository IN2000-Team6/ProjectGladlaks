package com.example.gladlaksapp.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


/**
 * Data Access Object used to update Locality table in the app database.
 */
@Dao
interface LocalityDao {

    @Query("SELECT * FROM localities")
    fun getAll() : Flow<List<Locality>>

    @Query("SELECT * FROM localities WHERE locality_no LIKE :input")
    fun getByNo(input: Int) : Flow<List<Locality>>

    @Query("SELECT * FROM localities WHERE name LIKE :input")
    fun getByName(input: String) : Flow<List<Locality>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocality(locality: Locality)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(localities: List<Locality>)

}