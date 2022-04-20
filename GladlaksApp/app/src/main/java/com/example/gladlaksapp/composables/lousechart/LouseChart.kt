package com.example.gladlaksapp.composables

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gladlaksapp.composables.lousechart.marker
import com.patrykandpatryk.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatryk.vico.compose.chart.Chart
import com.patrykandpatryk.vico.compose.chart.column.columnChart
import com.patrykandpatryk.vico.compose.chart.entry.defaultDiffAnimationSpec
import com.patrykandpatryk.vico.compose.component.shape.lineComponent
import com.patrykandpatryk.vico.compose.component.shape.textComponent
import com.patrykandpatryk.vico.compose.component.shapeComponent
import com.patrykandpatryk.vico.compose.dimensions.dimensionsOf
import com.patrykandpatryk.vico.core.axis.vertical.createVerticalAxis
import com.patrykandpatryk.vico.core.chart.column.ColumnChart
import com.patrykandpatryk.vico.core.chart.decoration.ThresholdLine
import com.patrykandpatryk.vico.core.component.shape.LineComponent
import com.patrykandpatryk.vico.core.component.shape.ShapeComponent
import com.patrykandpatryk.vico.core.component.shape.Shapes
import com.patrykandpatryk.vico.core.entry.ChartEntryModel
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatryk.vico.core.entry.FloatEntry
import com.patrykandpatryk.vico.core.entry.entryModelOf
import kotlin.random.Random

fun getRandomEntries(n: Int) = List(size = n) {
    0.6f * Random.nextFloat()
}.mapIndexed { x,y ->
    FloatEntry(
        x = x.toFloat(),
        y = y
    )
}


@Composable
fun LouseChartSimple(data: ChartEntryModel) {
    Chart(
        modifier = Modifier.height(100.dp),
        chart = columnChart(
            columns = listOf(
                lineComponent(
                    Color(0xFF01809C),
                    thickness = 6.dp,
                    shape = RoundedCornerShape(2.dp)
                )
            )
        ),
        startAxis = createVerticalAxis {
            label = textComponent(
                color = Color(0xFF01809C),
                textSize = 10.sp,
                background = shapeComponent(
                    shape = CutCornerShape(
                        CornerSize(percent=25),
                        CornerSize(percent=50),
                        CornerSize(percent=50),
                        CornerSize(percent=25),
                    ),
                    color = Color(0xFF01809C).copy(alpha = 0.1f)
                ),
                padding = dimensionsOf(end = 8.dp, start = 4.dp)
            )
            axis = null
            tick = null
            guideline = LineComponent(
                Color(0xFF01809C).copy(alpha = 0.1f).toArgb(),
                1.dp.value
            )
        },
        model = data
    )
}

@Composable
fun GroupedChart(
    chartEntryModelProducer: ChartEntryModelProducer,
    diffAnimationSpec: AnimationSpec<Float> = defaultDiffAnimationSpec
) {
    val decorations = listOf(
        ThresholdLine(
            thresholdValue = 0.5f,
            //thresholdRange = 0.5f .. f,
            lineComponent = ShapeComponent(
                color = MaterialTheme.colorScheme.tertiary
                    .copy(alpha=0.3f)
                    .toArgb()
            ),
            labelComponent = textComponent(
                color = MaterialTheme.colorScheme.tertiary,
                //margins = dimensionsOf(all = 1.dp),
                padding = dimensionsOf(horizontal = 14.dp),
                background = ShapeComponent(
                    shape = Shapes.roundedCornerShape(allPercent = 25),
                    strokeColor = MaterialTheme.colorScheme.tertiary.copy(alpha=.5f).toArgb(),
                    color = MaterialTheme.colorScheme.background.copy(alpha=.5f).toArgb(),
                    strokeWidthDp = 1f
                ),
            ),
            labelVerticalPosition = ThresholdLine.LabelVerticalPosition.Top,
            labelHorizontalPosition = ThresholdLine.LabelHorizontalPosition.Start,
            minimumLineThicknessDp = 2f
        )
    )

    val chart = columnChart(
        innerSpacing = 4.dp,
        spacing = 24.dp,
        mergeMode = ColumnChart.MergeMode.Grouped,
        decorations = decorations,
    )

    Chart(
        chart = chart,
        chartModelProducer = chartEntryModelProducer,
        startAxis = createVerticalAxis {
            label = textComponent(
                color = Color(0xFF01809C),
                textSize = 10.sp,
                background = shapeComponent(
                    shape = CutCornerShape(
                        CornerSize(percent=25),
                        CornerSize(percent=50),
                        CornerSize(percent=50),
                        CornerSize(percent=25),
                    ),
                    color = Color(0xFF01809C).copy(alpha = 0.1f)
                ),
                padding = dimensionsOf(end = 8.dp, start = 4.dp)
            )
            axis = null
            tick = null
            guideline = LineComponent(
                Color(0xFF01809C).copy(alpha = 0.1f).toArgb(),
                1.dp.value
            )
        },
        bottomAxis = bottomAxis(
            label = textComponent(
                textSize = 10.sp
            )
        ),
        diffAnimationSpec = diffAnimationSpec,
        marker = marker()
    )
}


@Preview(showBackground = true, widthDp = 320, heightDp = 600)
@Composable
fun PreviewLouseChart() {
    val GENERATIONS = 2

    val simpleTestData = entryModelOf(0.5f,0.3f,0.2f,0.45f)
    val chartEntries = ChartEntryModelProducer()
    chartEntries.setEntries(
        List(size = GENERATIONS) { getRandomEntries(Random.nextInt(5,12)) }
    )

    Column(
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        InfoCard {
            LouseChartSimple(simpleTestData)
        }
        InfoCard {
            GroupedChart(chartEntries)
        }
    }


}

