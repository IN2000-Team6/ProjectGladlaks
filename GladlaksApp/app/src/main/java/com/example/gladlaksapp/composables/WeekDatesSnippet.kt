package com.example.gladlaksapp.composables

import java.util.Calendar
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*
import java.util.Locale.GERMANY

@Preview(showBackground = true)
@Composable
fun WeekDatesSnippet(){
    val now = LocalDate.now()

    //Get week number
    val week = now.get(WeekFields.of(GERMANY).weekOfYear())

    //Get first day of week
    val monday = now.with(DayOfWeek.MONDAY).format(DateTimeFormatter.ofPattern("dd.MM"))

    //Get last day of week
    val sunday = now.with(DayOfWeek.SUNDAY).format(DateTimeFormatter.ofPattern("dd.MM.yy"))


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
