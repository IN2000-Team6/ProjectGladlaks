package com.example.gladlaksapp

import com.example.gladlaksapp.datasources.BarentswatchNetworkDataSource
import com.example.gladlaksapp.models.Localities
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class BarentswatchNetworkDataSourceTest {

    private val datasource = BarentswatchNetworkDataSource

    @Test
    fun testLocalitiesReturnsCorrectAmount() {
        runBlocking {
            val result: Localities = datasource.getLocalities(2022,11)
            assertTrue(result.localities.isNotEmpty())
            assertTrue(result.localities.size == 1712) //This changes per week
        }
    }
}