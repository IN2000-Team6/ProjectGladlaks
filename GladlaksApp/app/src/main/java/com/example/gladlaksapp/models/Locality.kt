package com.example.gladlaksapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents each locality with limited information
 */
@Entity(tableName = "localities")
data class Locality (
    @PrimaryKey @ColumnInfo(name="locality_no") val localityNo: Int,
    val name: String,
    val lat: Double,
    val lon: Double,
    val isOnLand: Boolean,
    val hasPd: Boolean,
    val hasIla: Boolean
)

/**
 * Represents a list of all localities listed this week
 */
data class LocalitiesWrapper (
    val week: Int,
    val year: Int,
    val localities: List<Locality>
)