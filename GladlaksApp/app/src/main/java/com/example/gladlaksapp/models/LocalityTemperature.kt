package com.example.gladlaksapp.models

/**
 * Data class representing time series of temp data for a given locality.
 * depth is the levels used by the NorKyst model [0, 3, 10...]
 * depthInMeters is depth converted into meters
 */
data class LocalityTemperature(
    val lat: Double,
    val lon: Double,
    val depth: Int,
    val depthInMeters: Int,
    val data: List<Float?>,
)