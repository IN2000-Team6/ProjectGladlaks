package com.example.gladlaksapp.datasources

import com.example.gladlaksapp.models.LocalityTemperature
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

object NorKystNetworkDataSource {
    private const val temperatureUrl = "http://10.0.2.2:8080/temperature?lat=%s&lon=%s&depth=%s"

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    suspend fun getLocalityTemperature(
        lat: Double,
        lon: Double,
        depth: Int,
    ): LocalityTemperature {
        return client.get(temperatureUrl.format(lat, lon, depth))
    }
}