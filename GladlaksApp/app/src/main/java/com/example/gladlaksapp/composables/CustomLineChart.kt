package com.example.gladlaksapp.composables


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.lightColorScheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.gladlaksapp.models.GraphLine
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


@Composable
fun CustomLineChart(
    height: Int,
    lines: List<GraphLine>,
    textSize: Int = 14,
) {
    val size = (textSize.dp).value
    val colors = listOf(Color(0xFFFFBDAE), md_theme_light_tertiary)

    Column {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(height.dp),
            factory = { context ->
                LineChart(context).apply {
                    this.data = LineData(createDataSets(lines, colors))
                    this.axisRight.isEnabled = false
                    this.description.isEnabled = false
                    this.isHovered = false
                    this.axisLeft.valueFormatter = DegFormatter()
                    this.axisLeft.textSize = size
                    this.axisLeft.labelCount = 10
                    this.xAxis.position = XAxis.XAxisPosition.BOTTOM
                    this.xAxis.textSize = size
                    this.xAxis.valueFormatter = TimeFormatter()
                    this.xAxis.labelCount = 8
                    this.legend.isEnabled = false
                    this.extraBottomOffset = 5f
                    this.animateY(500)
                    this.invalidate()
                }
            })
        CustomChartLegend(lines = lines, colors = colors)
    }
}

@Composable
fun CustomChartLegend(
    lines: List<GraphLine>,
    colors: List<Color>,
) {
    val size = 13

    Row(
        modifier = Modifier.padding(top = 8.dp, end = 6.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        lines.forEachIndexed { index, line ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .width(size.dp)
                    .height(size.dp)
                    .background(if (index < colors.size) colors[index] else Color.Gray)
            )
            Text(
                modifier = Modifier.padding(start = 5.dp, end = 10.dp),
                text = line.label
            )
        }
    }
}

fun createDataSets(
    lines: List<GraphLine>,
    colors: List<Color>,
): List<ILineDataSet> {
  
    // TODO use system colors here
    val colors = listOf(Color(0xFFFFBDAE), lightColorScheme().tertiary)

    return lines.mapIndexed { index, line ->
        val lineWidth = 3f
        val color = if (index < colors.size) colors[index] else Color.Gray

        val entries = line.coords.map { coords ->
            Entry(coords.x, coords.y)
        }

        val set = LineDataSet(entries, line.label)
        set.color = color.toArgb()
        set.lineWidth = lineWidth
        set.circleRadius = lineWidth / 2
        set.setCircleColor(color.toArgb())
        set.setDrawHighlightIndicators(false)
        set.setDrawCircleHole(false)
        set.setDrawValues(false)
        set
    }
}

class DegFormatter : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?) = "%.2f${176.toChar()}".format(value)
}

class TimeFormatter : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val decimal = if (value.mod(1f) > 0f) "%.1f" else "%.0f"
        return decimal.format(value) + "t"
    }
}