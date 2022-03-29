package com.example.gladlaksapp

import com.example.gladlaksapp.datasources.BarentswatchRepository
import com.example.gladlaksapp.models.LocalitiesWrapper
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class BarentswatchRepositoryTest {

    private val repository = BarentswatchRepository

    @Test
    fun testGetLocalitiesReturnsCorrectAmount() {
        runBlocking {
            val result: LocalitiesWrapper = repository.getLocalities(2022,12)
            Assert.assertTrue(result.localities.isNotEmpty())
            Assert.assertEquals(result.localities.size, 1714) //This changes per week
        }
    }

    @Test
    fun testGetLocalitiesInWaterReturnsCorrectAmount() {
        runBlocking {
            val result: List<Locality> = repository.getLocalitiesInWater(2022, 12)
            Assert.assertTrue(result.isNotEmpty())
            Assert.assertEquals(result.size,1345)
        }
    }

    @Test
    fun testLocalityDetailedReturnsData() {
        runBlocking {
            val result: LocalityDetailsWrapper = repository.getDetailedLocalityInfo(17015,2022,11)
            print(result)
            Assert.assertEquals(result.localityWeek.localityNo, 17015)
            Assert.assertEquals(result.localityName, "Seglberget")
        }
    }
}