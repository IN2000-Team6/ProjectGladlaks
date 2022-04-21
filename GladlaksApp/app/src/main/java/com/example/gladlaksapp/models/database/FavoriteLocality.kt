package com.example.gladlaksapp.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class wrapped with [Entity]. Represents a table with a single column.
 * Stores localities marked as favorite by the user.
 */
@Entity(tableName = "favorites")
data class FavoriteLocality(
    @PrimaryKey @ColumnInfo(name="locality_no") val localityNo: Int
)
