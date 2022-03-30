package com.example.gladlaksapp.datasources

import android.util.Log
import com.example.gladlaksapp.models.GraphCoords
import com.example.gladlaksapp.models.GraphLine
import kotlinx.coroutines.*

object NorKystRepository {
    private val datasource = NorKystNetworkDataSource

    suspend fun getLocalityTemperature(
        lat: Double,
        lon: Double,
    ) = coroutineScope {
        val temps = awaitAll(
            async { datasource.getLocalityTemperature(lat, lon, 1) },
            async { datasource.getLocalityTemperature(lat, lon, 2) }
        )

        Log.d("nodata", temps.toString())

        return@coroutineScope temps.map { line ->
            GraphLine(
                label = "${line.depth} meter",
                coords = line.data.mapIndexed { index, point ->
                    GraphCoords(index.toFloat(), point ?: 0f)
                }
            )
        }
    }
}