package com.example.gladlaksapp.composables

import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gladlaksapp.models.GraphLine
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import com.example.gladlaksapp.models.LouseData
import com.example.gladlaksapp.viewmodels.MainViewModel
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer


@Composable
fun LocalitySheetContent(
    selectedLocality: Locality?,
    loadedLocality: LocalityDetailsWrapper?,
    graphLines: List<GraphLine>?,
    model: MainViewModel = viewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 20.dp)
    ) {
        if (loadedLocality == null || graphLines == null || selectedLocality == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Color(0xFF9CDCDA))
            }
        } else {
            InfoCard {
                LocalityInfo(selectedLocality, localityInfo = loadedLocality.localityWeek)
            }
            InfoCard {
                GroupedChart(chartEntryModelProducer = model.groupedChartProducer)
            }
            InfoCard {
                Box(modifier = Modifier.padding(start = 8.dp, bottom = 16.dp, end = 4.dp)) {
                    CustomLineChart(height = 300, lines = graphLines)
                }
            }
        }
    }
}