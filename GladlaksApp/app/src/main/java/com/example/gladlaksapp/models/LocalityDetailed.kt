package com.example.gladlaksapp.models

data class LocalityDetailed (
    val localityName: String,
    val localityWeek: LocalityWeek,
    val liceCountPreviousWeek: LiceCountPreviousWeek,

)

data class LocalityWeek (
    val id: Int,
    val localityNo: Int,
    val year: Int,
    val week: Int,
    val hasReportedLice: Boolean,
    val hasMechanicalRemoval: Boolean,
    val hasBathTreatment: Boolean,
    val hasInFeedTreatment: Boolean,
    val hasCleanerFishDeployed: Boolean,
    val avgAdultFemaleLice: Number,
    val avgMobileLice: Number,
    val avgStationaryLice: Number,
)

data class LiceCountPreviousWeek (
    val year: Int,
    val week: Int,
    val hasReportedLice: Boolean,
    val avgAdultFemaleLice: Number,
    val avgMobileLice: Number,
    val avgStationaryLice: Number

)
