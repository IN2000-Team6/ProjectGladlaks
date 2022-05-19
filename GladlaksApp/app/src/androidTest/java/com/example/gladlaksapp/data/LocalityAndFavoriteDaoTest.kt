package com.example.gladlaksapp.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gladlaksapp.models.FavoriteDao
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
class RoomDatabaseTest {
    private lateinit var localityDao: LocalityDao
    private lateinit var favoriteDao: FavoriteDao
    private lateinit var db: LocalityDatabase

    private val localityA = Locality(localityNo = 123, name="test", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false)

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
    fun should_insert_list_of_localities() = runBlocking {
        localityDao.insertAll(testLocalities)
        assertEquals(localityDao.getAll().first().size, 6)
    }

    @Test
    fun should_find_locality_by_name_and_no() = runBlocking {
        localityDao.insertAll(testLocalities)
        localityDao.insertLocality(localityA)
        val foundName = localityDao.getByName("test").first()[0]
        val foundNo = localityDao.getByNo(123).first()[0]

        assertTrue(localityA == foundName && localityA == foundNo)
    }


    @Test
    fun should_correctly_add_and_get_favorites() = runBlocking {
        localityDao.insertAll(testLocalities)
        favoriteDao.insertFavorites(testFavorites)
        assertEquals(4,favoriteDao.getAll().first().size)
        assertEquals(3, favoriteDao.getFavoriteLocalities().first().size)
    }
}