package com.example.gladlaksapp.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gladlaksapp.models.Locality

@Database(entities = [Locality::class, FavoriteLocality::class], version = 1)
abstract class LocalityDatabase : RoomDatabase() {
    abstract fun localityDao(): LocalityDao
}