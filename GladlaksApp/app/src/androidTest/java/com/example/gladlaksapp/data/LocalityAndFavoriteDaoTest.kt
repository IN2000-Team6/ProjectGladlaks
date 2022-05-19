package com.example.gladlaksapp.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gladlaksapp.models.FavoriteDao
import com.example.gladlaksapp.models.FavoriteLocality
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.LocalityDao
import com.example.gladlaksapp.repositories.LocalityDatabase
import com.example.gladlaksapp.utils.testFavorites
import com.example.gladlaksapp.utils.testLocalities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before


@RunWith(AndroidJUnit4::class)
class SimpleLocalityTest {
    private lateinit var localityDao: LocalityDao
    private lateinit var favoriteDao: FavoriteDao
    private lateinit var db: LocalityDatabase

    private val localityA = Locality(localityNo = 123, name="test", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false)
    private val favoriteA = FavoriteLocality(localityNo = 123, isFavorite = true)

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, LocalityDatabase::class.java).build()
        localityDao = db.localityDao()
        favoriteDao = db.favoriteDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun `should insert list of localities`() = runBlocking {
        localityDao.insertAll(testLocalities)
        Log.d("testInsertAll", localityDao.getAll().first().size.toString())
        assertEquals(localityDao.getAll().first().size, 6)
    }

    @Test
    fun testSearch() = runBlocking {
        localityDao.insertAll(testLocalities)
        localityDao.insertLocality(localityA)
        Log.d("testSearch", localityDao.getAll().first().size.toString())
        val foundName = localityDao.getByName("test").first()[0]
        val foundNo = localityDao.getByNo(123).first()[0]

        assertTrue(localityA == foundName && localityA == foundNo)
    }


    @Test
    fun testWriteAndGetFavorites() = runBlocking {
        localityDao.insertAll(testLocalities)
        favoriteDao.insertFavorites(testFavorites)
        Log.d("testWriteAndGetFavorites", favoriteDao.getFavoriteLocalities().asLiveData().value!!.size.toString())
        assertEquals(4,favoriteDao.getAll().asLiveData().value!!.size)
        assertEquals(3, favoriteDao.getFavoriteLocalities().asLiveData().value!!.size)
    }
}