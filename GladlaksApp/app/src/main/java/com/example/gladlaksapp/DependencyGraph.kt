package com.example.gladlaksapp

import android.content.Context
import androidx.room.Room
import com.example.gladlaksapp.models.database.FavoriteRepository
import com.example.gladlaksapp.models.database.LocalityDatabase
import com.example.gladlaksapp.models.database.LocalityRepository

/**
 * Singleton dependency graph to access database repositories in composables
 *
 * Consider using Koin/Dagger/Hilt instead
 */
object DependencyGraph {
    lateinit var database: LocalityDatabase

    val localityRepository by lazy {
        LocalityRepository(
            localityDao = database.localityDao()
        )
    }

    val favoriteRepository by lazy {
        FavoriteRepository(
            favoriteDao = database.favoriteDao()
        )
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, LocalityDatabase::class.java, "mcData.db")
            .fallbackToDestructiveMigration() // don't use this in production app
            .build()
    }
}