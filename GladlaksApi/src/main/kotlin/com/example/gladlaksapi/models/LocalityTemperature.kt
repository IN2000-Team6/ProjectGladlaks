package com.example.gladlaksapi.models

data class LocalityTemperature(
    val lat: Double,
    val lon: Double,
    val depth: Int,
    val depthInMeters: Int,
    val data: List<Float?>,
)