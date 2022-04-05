package com.example.gladlaksapp.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteLocality(
    @PrimaryKey @ColumnInfo(name="locality_no") val localityNo: Int
)
