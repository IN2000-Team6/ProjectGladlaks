package com.example.gladlaksapp.composables.locality

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gladlaksapp.composables.reusables.CustomLineChart
import com.example.gladlaksapp.composables.reusables.WeekDatesSnippet
import com.example.gladlaksapp.composables.reusables.InfoCard
import com.example.gladlaksapp.models.GraphLine
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import com.example.gladlaksapp.viewmodels.LocalityViewModel

@Composable
fun LocalitySheetContent(
    selectedLocality: Locality?,
    loadedLocality: LocalityDetailsWrapper?,
    graphLines: List<GraphLine>?,
    locViewModel: LocalityViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 20.dp)
    ) {
        WeekDatesSnippet()
        if (loadedLocality == null || graphLines == null || selectedLocality == null) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                CircularProgressIndicator(color = Color(0xFF9CDCDA))
            }
        } else {
            InfoCard { LocalityInfo(selectedLocality, localityInfo = loadedLocality.localityWeek) }
            InfoCard { CustomBarChart(chartEntryModelProducer = locViewModel.groupedChartProducer) }
            InfoCard {
                Box(modifier = Modifier.padding(start = 8.dp, bottom = 16.dp, end = 4.dp)) {
                    CustomLineChart(height = 300, lines = graphLines)
                }
            }
        }
    }
}