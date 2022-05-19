package com.example.gladlaksapp.models


/**
 * Data class representing all reported louse data from a locality
 * through a year.
 * Contains a list of [LouseData] data classes
 */
data class LouseDataByYear(
    val localityNo: Int,
    val year: Int,
    val maxCombinedNumberOfLice: String,
    val data: List<LouseData>
)

/**
 * Data class representing a single weeks louse report. Contains
 * all different types of observed lice counts.
 */
data class LouseData(
    val week: Int,
    val avgAdultFemaleLice: Float,
    val avgMobileLice : Float,
    val avgStationaryLice : Float,
    val hasReportedLice : Boolean
)
