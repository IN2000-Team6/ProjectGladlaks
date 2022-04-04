package com.example.gladlaksapi.utils

import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Get the date now with an offset of a whole day based on a switch integer
 * Example, if the switch is 12 (o'clock), and the date-time is 2022.04.04:14.00, then
 * the date returned will be 2022.04.04. But if the date-time is 2022.04.04:10.00, then
 * the date returned will be 2022.04.03 –– that is one day-of-month less.
 */
fun getDateNowByFormatWithSwitch(pattern: String, switch: Int): String {
    val now = OffsetDateTime.now(ZoneId.of("Europe/Berlin"))

    // Offset
    val offset = if (now.hour < switch) now.withDayOfMonth(now.dayOfMonth - 1) else now

    return offset
        .format(DateTimeFormatter.ofPattern(pattern))
        .toString()
}