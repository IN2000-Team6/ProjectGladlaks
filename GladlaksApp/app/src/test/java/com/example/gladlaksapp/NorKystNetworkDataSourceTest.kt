package com.example.gladlaksapp

import com.example.gladlaksapp.datasources.NorKystNetworkDataSource
import com.example.gladlaksapp.models.LocalityTemperature
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

class NorKystNetworkDataSourceTest {
    private val datasource = NorKystNetworkDataSource

    private val LAT = 61.0
    private val LON = 4.8
    private val DEPTH = 1

    @Test
    fun `should get locality temperature timeseries data`() {
        runBlocking {
            val result: LocalityTemperature = datasource.getLocalityTemperature(LAT, LON, DEPTH)

            assertEquals(result.data.size, 43)
            assertEquals(result.lat, LAT, 0.0)
            assertEquals(result.lat, LAT, 0.0)
            assertEquals(result.lat, LAT, 0.0)
        }
    }
}