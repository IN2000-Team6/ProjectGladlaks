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
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*
import java.util.Locale.GERMANY

@Preview(showBackground = true)
@Composable
fun WeekDatesSnippet(){
    //TODO use LocalDate across the board
    val now = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"))
    val localdate = LocalDate.now()

    //Get week number
    val week = localdate.get(WeekFields.of(GERMANY).weekOfYear())

    //Get first day of week
    now.set(Calendar.DAY_OF_WEEK, now.firstDayOfWeek)
    val monday = SimpleDateFormat("dd").format(now.time)

    //Get last day of week
    now.add(Calendar.DAY_OF_WEEK,6)
    val sunday = SimpleDateFormat("dd.MM.yy").format(now.time)


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
                "$monday.-$sunday",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
