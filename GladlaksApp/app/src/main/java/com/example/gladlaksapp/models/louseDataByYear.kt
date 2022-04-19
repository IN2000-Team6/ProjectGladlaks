package com.example.gladlaksapp.models

data class louseDataByYear(
    val localityNo: Int,
    val year: Int,
    val maxCombinedNumberOfLice: String,
    val data: List<louseData>

)

data class louseData(
    val week: Int,
    val avgAdultFemaleLice: Int,
    val avgMobileLice : Int,
    val avgStationaryLice : Int,
    val hasReportedLice : Boolean
)
