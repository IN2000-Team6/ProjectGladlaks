package com.example.gladlaksapp.models

data class LouseDataByYear(
    val localityNo: Int,
    val year: Int,
    val maxCombinedNumberOfLice: String,
    val data: List<LouseData>

)

data class LouseData(
    val week: Int,
    val avgAdultFemaleLice: Int,
    val avgMobileLice : Int,
    val avgStationaryLice : Int,
    val hasReportedLice : Boolean
)
