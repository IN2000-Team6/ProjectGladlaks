package com.example.gladlaksapi.utils

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun getDateNowByFormat(pattern: String) = LocalDateTime
    .now(ZoneOffset.UTC)
    .format(DateTimeFormatter.ofPattern(pattern))
    .toString()