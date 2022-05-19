package com.example.gladlaksapp.repositories

import com.example.gladlaksapp.datasources.NorKystNetworkDataSource
import com.example.gladlaksapp.models.GraphCoords
import com.example.gladlaksapp.models.GraphLine
import kotlinx.coroutines.*

/**
 * Repository for the NorKyst data source.
 * This class contains some business logic so it is in a sense also a service
 */
class NorKystRepository(
    private val dataSource: NorKystNetworkDataSource
) {
    suspend fun getLocalityTemperature(
        lat: Double,
        lon: Double,
    ) = coroutineScope {
        val temps = awaitAll(
            async { dataSource.getLocalityTemperature(lat, lon, 1) },
            async { dataSource.getLocalityTemperature(lat, lon, 2) }
        )

        return@coroutineScope temps.map { line ->
            GraphLine(
                label = "${line.depthInMeters} meter",
                coords = line.data.mapIndexed { index, point ->
                    GraphCoords(index.toFloat(), point ?: 0f)
                }
            )
        }
    }
}