package com.example.gladlaksapp.models.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gladlaksapp.models.Locality

@Database(entities = [Locality::class, FavoriteLocality::class], version = 1)
abstract class LocalityDatabase : RoomDatabase() {
    abstract fun localityDao(): LocalityDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        const val DATABASE_NAME = "locality_database"
    }
}