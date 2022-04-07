package com.example.gladlaksapp.utils

import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.database.FavoriteLocality

/**
 * Testing locality entities
 */
val testLocalities = arrayListOf(
    Locality(localityNo = 1, name="a", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0),
    Locality(localityNo = 2, name="b", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0),
    Locality(localityNo = 3, name="c", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0),
    Locality(localityNo = 4, name="d", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0),
    Locality(localityNo = 5, name="e", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0),
    Locality(localityNo = 6, name="f", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0),
)

/**
 * Testing favorite entities
 */
val testFavorites = arrayListOf(
    FavoriteLocality(localityNo = 1),
    FavoriteLocality(localityNo = 4),
    FavoriteLocality(localityNo = 6),
    FavoriteLocality(localityNo = 7)
)