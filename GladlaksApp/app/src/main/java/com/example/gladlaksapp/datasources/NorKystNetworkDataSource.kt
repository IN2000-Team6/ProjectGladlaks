package com.example.gladlaksapp.datasources

import com.example.gladlaksapp.models.LocalityTemperature
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

class NorKystNetworkDataSource {
    private val temperatureUrl = "http://158.37.63.93:8080/temperature?lat=%s&lon=%s&depth=%s"

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    /**
     * Get a time series of temperatures given a locality and a depth
     * @param lat locality latitude
     * @param lon locality longitude
     * @param depth levels in nordkyst model: [0, 3, 10...]
     */
    suspend fun getLocalityTemperature(
        lat: Double,
        lon: Double,
        depth: Int,
    ): LocalityTemperature {
        return client.get(temperatureUrl.format(lat, lon, depth))
    }
}