package com.example.gladlaksapp.composables.reusables


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.lightColorScheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.gladlaksapp.R
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
    val colors = listOf(Color(0xFFFFBDAE), lightColorScheme().tertiary)

    Column {
        Text(
            text = stringResource(R.string.sea_temperature),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp, top = 15.dp),
            textAlign = TextAlign.Center,
        )

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ){
            Text(
                text = "timer etter midnatt",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        CustomChartLegend(lines = lines, colors = colors)
        InfoText(R.string.temp_chart_text, MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun CustomChartLegend(
    lines: List<GraphLine>,
    colors: List<Color>,
) {
    val indicatorSize = 13

    Row(
        modifier = Modifier
            .padding(top = 8.dp, end = 6.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        lines.forEachIndexed { index, line ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .width(indicatorSize.dp)
                    .height(indicatorSize.dp)
                    .background(if (index < colors.size) colors[index] else Color.Gray)
            )
            Text(
                modifier = Modifier.padding(start = 5.dp, end = 10.dp),
                style = MaterialTheme.typography.bodySmall,
                text = line.label
            )
        }
    }
}

fun createDataSets(
    lines: List<GraphLine>,
    colors: List<Color>,
): List<ILineDataSet> {
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
    override fun getAxisLabel(value: Float, axis: AxisBase?) = "%.2f${176.toChar()}C".format(value)
}

class TimeFormatter : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val decimal = if (value.mod(1f) > 0f) "%.1f" else "%.0f"
        return if(value > 9) decimal.format(value) else "0" + decimal.format(value)
    }
}