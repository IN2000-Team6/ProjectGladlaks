package com.example.gladlaksapp.di

import android.app.Application
import androidx.room.Room
import com.example.gladlaksapp.models.database.LocalityDatabase
import com.example.gladlaksapp.models.database.LocalityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Sets up dependency [Graph] with a context. Consider renaming/moving to avoid confusion with main
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalityDatabase(app: Application): LocalityDatabase {
        return Room.databaseBuilder(
            app,
            LocalityDatabase::class.java,
            LocalityDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalityRepository(db: LocalityDatabase): LocalityRepository {
        return LocalityRepository(db.localityDao())
    }
}