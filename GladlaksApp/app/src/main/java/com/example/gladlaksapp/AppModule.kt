package com.example.gladlaksapp

import android.app.Application
import androidx.room.Room
import com.example.gladlaksapp.datasources.BarentswatchNetworkDataSource
import com.example.gladlaksapp.repositories.BarentswatchRepository
import com.example.gladlaksapp.datasources.NorKystNetworkDataSource
import com.example.gladlaksapp.repositories.NorKystRepository
import com.example.gladlaksapp.repositories.FavoriteRepository
import com.example.gladlaksapp.repositories.LocalityDatabase
import com.example.gladlaksapp.repositories.LocalityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Sets up dependency Graph with a context.
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