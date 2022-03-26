package com.example.gladlaksapi.models

data class Location(
    val rows: String,
    val columns: String,
    var lat: Double,
    var lon: Double,
    var hours: Int,
    var data: List<List<Float?>>
)