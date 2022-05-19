package com.example.gladlaksapp.testUtils

import com.example.gladlaksapp.models.*

/**
 * Testing locality entities
 */
val testLocalities = arrayListOf(
    Locality(localityNo = 1, name="a", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 2, name="b", hasPd = false, hasIla = false, isOnLand = true, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 3, name="c", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = true),
    Locality(localityNo = 4, name="d", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 5, name="e", hasPd = false, hasIla = false, isOnLand = true, lat = 1.0, lon = 1.0, hasReportedLice = true),
    Locality(localityNo = 6, name="f", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
)


val testLousedataGen1 = listOf(
        LouseData(week = 1, avgAdultFemaleLice = 0.02f, avgMobileLice = 0.1f, avgStationaryLice = 0.15f, hasReportedLice = true),
        LouseData(week = 2, avgAdultFemaleLice = 0f, avgMobileLice = 0f, avgStationaryLice = 0f, hasReportedLice = false),
        LouseData(week = 3, avgAdultFemaleLice = 0.02f, avgMobileLice = 0.1f, avgStationaryLice = 0.15f, hasReportedLice = true),
        LouseData(week = 4, avgAdultFemaleLice = 0.23f, avgMobileLice = 0.2f, avgStationaryLice = 0.21f, hasReportedLice = true),
)

val testLousedataGen2 = listOf(
        LouseData(week = 1, avgAdultFemaleLice = 0.03f, avgMobileLice = 0.1f, avgStationaryLice = 0.15f, hasReportedLice = true),
        LouseData(week = 2, avgAdultFemaleLice = 0.1f, avgMobileLice = 0f, avgStationaryLice = 0f, hasReportedLice = false),
        LouseData(week = 3, avgAdultFemaleLice = 0.03f, avgMobileLice = 0.1f, avgStationaryLice = 0.15f, hasReportedLice = true),
        LouseData(week = 4, avgAdultFemaleLice = 0.28f, avgMobileLice = 0.2f, avgStationaryLice = 0.21f, hasReportedLice = true),
)




