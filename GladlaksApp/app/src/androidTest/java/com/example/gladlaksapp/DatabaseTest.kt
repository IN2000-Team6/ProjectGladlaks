package com.example.gladlaksapp

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.database.FavoriteLocality
import com.example.gladlaksapp.models.database.LocalityDao
import com.example.gladlaksapp.models.database.LocalityDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class SimpleLocalityTest {
    private lateinit var localityDao: LocalityDao
    private lateinit var db: LocalityDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, LocalityDatabase::class.java).build()
        localityDao = db.localityDao()
    }

    @Test
    fun writeUserAndReadInList() {
        val locality = Locality(
            localityNo = 1234,
            name="penis",
            hasIla = false,
            hasPd = false,
            isOnLand = false,
            lat = 10.0,
            lon = 10.2
        )
        val locality2 = Locality(
            localityNo = 12345,
            name="sus",
            hasPd = false,
            hasIla = false,
            isOnLand = false,
            lat = 5.0,
            lon = 6.0,
        )
        val favorite = FavoriteLocality(
            localityNo = 1234
        )
        runBlocking {
            localityDao.insertLocality(locality)
            localityDao.insertLocality(locality2)
            localityDao.insertFavorite(favorite)
            val byNo = localityDao.getByNo(1234)
            val favorites = localityDao.getFavorites()
            val all = localityDao.getAll()

            assertTrue(byNo[0] == locality)
            assertEquals(favorites.size,1)
            Log.d("all", all.toString())
        }
    }
}