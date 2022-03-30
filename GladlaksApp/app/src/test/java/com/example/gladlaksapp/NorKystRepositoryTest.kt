package com.example.gladlaksapp

import com.example.gladlaksapp.datasources.NorKystRepository
import com.example.gladlaksapp.models.GraphCoords
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

class NorKystRepositoryTest {
    private val repo = NorKystRepository

    private val LAT = 61.0
    private val LON = 4.8

    @Test
    fun `should correctly fetch and transform temps into coords`() {
        runBlocking {
            val result = repo.getLocalityTemperature(LAT, LON)

            assertEquals(result.size, 2)
            assertEquals(result[0][0]::class, GraphCoords::class)
        }
    }
}