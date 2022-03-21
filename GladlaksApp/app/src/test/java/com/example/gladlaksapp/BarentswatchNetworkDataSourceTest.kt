package com.example.gladlaksapp

import com.example.gladlaksapp.datasources.BarentswatchNetworkDataSource
import com.example.gladlaksapp.models.Localities
import com.example.gladlaksapp.models.LocalityDetailed
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class BarentswatchNetworkDataSourceTest {

    private val datasource = BarentswatchNetworkDataSource

    @Test
    fun testLocalitiesReturnsCorrectAmount() {
        runBlocking {
            val result: Localities = datasource.getLocalities(2022,11)
            //print(result)
            assertTrue(result.localities.isNotEmpty())
            assertEquals(result.localities.size, 1712) //This changes per week
        }
    }

    @Test
    fun testLocalityDetailedReturnsData() {
        runBlocking {
            val result: LocalityDetailed = datasource.getDetailedLocalityInfo(17015,2022,11)
            print(result)
            assertEquals(result.localityName, "Seglberget")
        }
    }
}