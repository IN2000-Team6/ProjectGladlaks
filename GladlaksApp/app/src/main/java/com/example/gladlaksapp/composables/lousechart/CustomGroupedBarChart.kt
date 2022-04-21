package com.example.gladlaksapp.composables.lousechart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.gladlaksapp.composables.GroupedChart
import com.example.gladlaksapp.models.LouseData
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry

@Composable
fun customGroupedBarChart(
    generations: List<List<LouseData>>,
    textSize: Int = 14,
    height: Int,
) {
    val size = (textSize.dp).value
    val colors = listOf(Color(0xFFFFBDAE), Color(0xFF9c4331))

    Column {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(height.dp),
            factory = { context ->
                BarChart(context).apply {
                    this.axisRight.isEnabled = false
                    this.description.text = "Sammenligning"
                    this.isHovered = false

                }

            })
    }
}

fun createDataSets(
    lousedata: List<List<LouseData>>,
    colors: List<Color>
): List<BarDataSet>? {

    val colors = listOf(Color(0xFFFFBDAE), lightColorScheme().tertiary)

    val sets = lousedata.map { data ->
        val entries = data.mapIndexed { index, week ->
            BarEntry(index.toFloat(),week.avgAdultFemaleLice)
        }
    }
    return null
}