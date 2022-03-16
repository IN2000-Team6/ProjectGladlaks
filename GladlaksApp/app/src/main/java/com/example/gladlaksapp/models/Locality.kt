package com.example.gladlaksapp.models

/**
 * Represents each locality with limited information
 */
data class Locality (
    val localityNo: Int,
    val name: String,
    val lat: Double,
    val lon: Double,
    val isOnLand: Boolean,
)

/**
 * Represents a list of all localities listed this week
 */
data class Localities (
    val week: Int,
    val year: Int,
    val localities: List<Locality>
)