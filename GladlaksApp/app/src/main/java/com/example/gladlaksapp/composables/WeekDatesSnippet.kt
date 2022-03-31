package com.example.gladlaksapp.composables

import android.icu.util.Calendar
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Preview
@Composable
fun WeekDatesSnippet(){
    val now = java.util.Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"))

    val (week, mon, sun) = getWeekRange(now)

    //TODO Try ICU calendar methods
    //Get first day of week
    /*now.set(Calendar.DAY_OF_WEEK, now.firstDayOfWeek)
    val monday = SimpleDateFormat("dd.MM.").format(now.firstDayOfWeek)

    //Get last day of week
    now.add(Calendar.DAY_OF_WEEK,6)
    val sunday = SimpleDateFormat("dd.MM.yy").format(now.time)*/

    Box(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
            "UKE $week",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                "$mon-$sun",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

fun getWeekRange(calInstance: java.util.Calendar): Triple<Int?, String?, String?> {
    val week = calInstance.get(java.util.Calendar.WEEK_OF_YEAR)

    calInstance[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
    val monday = calInstance.time

    calInstance[Calendar.DAY_OF_WEEK] = Calendar.SUNDAY
    //Week starts on Sunday
    calInstance[Calendar.WEEK_OF_YEAR] = week+1
    val sunday = calInstance.time

    val sdf = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
    //Week numbers are zero-indexed in java
    return Triple(week-1, sdf.format(monday), sdf.format(sunday))
}
