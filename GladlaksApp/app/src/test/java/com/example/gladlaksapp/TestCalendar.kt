package com.example.gladlaksapp

import org.junit.Test
import java.util.*

class TestCalendar {

    @Test
    fun testCalendar() {
        val now = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"), )
        println(now.get(Calendar.WEEK_OF_YEAR))
        println(now.get(Calendar.WEEK_OF_YEAR))
        println(now.get(Calendar.YEAR))
    }
}