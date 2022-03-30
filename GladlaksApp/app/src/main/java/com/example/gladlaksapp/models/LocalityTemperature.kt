package com.example.gladlaksapp.models

data class LocalityTemperature(
    var lat: Double,
    var lon: Double,
    var depth: Int,
    var data: List<Float?>,
)