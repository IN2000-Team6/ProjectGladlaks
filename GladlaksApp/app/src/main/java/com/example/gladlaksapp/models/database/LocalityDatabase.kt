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

        @Volatile private var INSTANCE: LocalityDatabase? = null

        fun getDatabase(context: Context) : LocalityDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalityDatabase::class.java,
                    "locality_database"
                    ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}