package com.example.gladlaksapp.di

import android.app.Application
import androidx.room.Room
import com.example.gladlaksapp.datasources.BarentswatchNetworkDataSource
import com.example.gladlaksapp.datasources.BarentswatchRepository
import com.example.gladlaksapp.datasources.NorKystNetworkDataSource
import com.example.gladlaksapp.datasources.NorKystRepository
import com.example.gladlaksapp.models.database.FavoriteRepository
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

    @Provides
    @Singleton
    fun provideFavoriteRepository(db: LocalityDatabase): FavoriteRepository {
        return FavoriteRepository(db.favoriteDao())
    }

    @Provides
    @Singleton
    fun provideBarentswatchRepository(dataSource: BarentswatchNetworkDataSource): BarentswatchRepository {
        return BarentswatchRepository(dataSource)
    }

    @Provides
    @Singleton
    fun provideNorKystRepository(dataSource: NorKystNetworkDataSource): NorKystRepository {
        return NorKystRepository(dataSource)
    }

    @Provides
    @Singleton
    fun provideNorKystNetworkDataSource(): NorKystNetworkDataSource {
        return NorKystNetworkDataSource()
    }

    @Provides
    @Singleton
    fun provideBarentswatchNetworkDataSource(): BarentswatchNetworkDataSource {
        return BarentswatchNetworkDataSource()
    }
}