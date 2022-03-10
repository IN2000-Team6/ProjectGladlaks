package com.example.gladlaksapp.models

data class Locality (
    val localityNo: Int,
    val name: String,
    val lat: Double,
    val lon: Double,
    val isOnLand: Boolean,
)