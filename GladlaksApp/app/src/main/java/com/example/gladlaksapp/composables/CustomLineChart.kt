package com.example.gladlaksapp.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.gladlaksapp.models.GraphLine
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import md_theme_light_tertiary

@Composable
fun CustomLineChart(
    height: Int,
    lines: List<GraphLine>,
    textSize: Int = 14,
) {
    val size = (textSize.dp).value

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp),
        factory = { context ->
            LineChart(context).apply {
                this.data = LineData(createDataSets(lines))
                this.axisRight.isEnabled = false
                this.description.isEnabled = false
                this.isHovered = false
                this.xAxis.position = XAxis.XAxisPosition.BOTTOM
                this.axisLeft.textSize = size
                this.xAxis.textSize = size
                this.legend.textSize = size
                this.legend.xOffset = -1f
                this.animateY(500)
                this.invalidate()
            }
        })
}

fun createDataSets(
    lines: List<GraphLine>,
): List<ILineDataSet> {
    // TODO use system colors here
    val colors = listOf(Color(0xFFFFBDAE), md_theme_light_tertiary)

    return lines.mapIndexed { index, line ->
        val color = colors[index].toArgb()
        val entries = line.coords.map { coords ->
            Entry(coords.x, coords.y)
        }

        val set = LineDataSet(entries, line.label)
        set.color = color
        set.lineWidth = line.lineWidth
        set.circleRadius = line.lineWidth / 2
        set.setCircleColor(color)
        set.setDrawHighlightIndicators(false)
        set.setDrawCircleHole(false)
        set.setDrawValues(false)
        set
    }
}