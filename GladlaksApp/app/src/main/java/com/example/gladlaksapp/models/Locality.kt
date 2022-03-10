package com.example.gladlaksapp.models

import com.google.android.libraries.maps.model.LatLng

data class Locality (
    val localityNo: Int,
    val name: String,
    val lat: Double,
    val lon: Double,
    val isOnLand: Boolean,
)

fun toLatLng(lat: Double, lng: Double) = LatLng(lat, lng)