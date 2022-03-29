package com.example.gladlaksapi.models

data class LocationTemperature(
    var lat: Double,
    var lon: Double,
    var depth: Int,
    var data: List<Float?>,
)