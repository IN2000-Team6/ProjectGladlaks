package com.example.gladlaksapp

import org.junit.Test
import java.util.*

class TestCalendar {

    @Test
    fun testCalendar() {
        val now = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"), )
        print(now.get(Calendar.WEEK_OF_YEAR))
        print(now.get(Calendar.WEEK_OF_YEAR))
        print(now.get(Calendar.YEAR))
    }
}