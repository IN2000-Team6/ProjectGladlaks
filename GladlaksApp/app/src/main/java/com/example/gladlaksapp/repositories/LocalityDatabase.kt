package com.example.gladlaksapp.repositories

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.FavoriteDao
import com.example.gladlaksapp.models.FavoriteLocality
import com.example.gladlaksapp.models.LocalityDao

@Database(entities = [Locality::class, FavoriteLocality::class], version = 1)
abstract class LocalityDatabase : RoomDatabase() {
    abstract fun localityDao(): LocalityDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        const val DATABASE_NAME = "locality_database"
    }
}