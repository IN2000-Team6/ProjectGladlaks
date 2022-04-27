package com.example.gladlaksapp.composables

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
import com.patrykandpatryk.vico.core.axis.formatter.AxisValueFormatter
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

//TODO Break up composables and make test
//TODO Add axes labels and legend
//TODO Rewrite graph 2

/**
 * Returns a list of floatentries - used for testing only
 * ! USED FOR TESTING ONLY !
 */
fun getRandomEntries(n: Int) = List(size = n) {
    0.6f * Random.nextFloat()
}.mapIndexed { x,y ->
    FloatEntry(
        x = x.toFloat(),
        y = y
    )
}


@Composable
fun CustomBarChart(
    chartEntryModelProducer: ChartEntryModelProducer,
    diffAnimationSpec: AnimationSpec<Float> = defaultDiffAnimationSpec
) {
    val thresholdColor = MaterialTheme.colorScheme.error
    val decorations = listOf(
        ThresholdLine(
            thresholdValue = 0.5f,
            lineComponent = ShapeComponent(
                color = thresholdColor
                    .copy(alpha=0.4f)
                    .toArgb()
            ),
            labelComponent = textComponent(
                color = thresholdColor,
                //margins = dimensionsOf(all = 1.dp),
                padding = dimensionsOf(horizontal = 14.dp),
                background = ShapeComponent(
                    shape = Shapes.roundedCornerShape(allPercent = 25),
                    strokeColor = thresholdColor.copy(alpha=.4f).toArgb(),
                    color = MaterialTheme.colorScheme.background.toArgb(),
                    strokeWidthDp = 1f
                ),
            ),
            labelVerticalPosition = ThresholdLine.LabelVerticalPosition.Top,
            labelHorizontalPosition = ThresholdLine.LabelHorizontalPosition.Start,
            minimumLineThicknessDp = 2f
        )
    )

    val chart = columnChart(
        innerSpacing = 2.dp,
        spacing = 12.dp,
        mergeMode = ColumnChart.MergeMode.Grouped,
        decorations = decorations,
        columns = listOf(
            lineComponent(
                shape = Shapes.cutCornerShape(topLeftPercent = 0),
                color = MaterialTheme.colorScheme.secondary,
                thickness = 8.dp
            ),
            lineComponent(
                shape = Shapes.cutCornerShape(topLeftPercent = 0),
                color = MaterialTheme.colorScheme.primary,
                thickness = 8.dp
            )
        )
    )
    Column(modifier = Modifier
        .fillMaxHeight(),
        //verticalArrangement = Arrangement.SpaceEvenly
    ) {
        //TODO The way this component is handled is not very good. Should separate a lot of these things
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Text(
                text = "Hunnlus per uke",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        /* Row(
             modifier = Modifier
                 .fillMaxWidth()
                 .weight(1f),
             verticalAlignment = Alignment.CenterVertically
         ) {
             Text(
                 text = "Ant" ,//all hunnlus per fisk,
                 style = MaterialTheme.typography.bodySmall,
                 //textAlign = TextAlign,
                 modifier = Modifier
                     .padding(2.dp)
                     //.rotate(-90f)
             )

             Box(
                 modifier = Modifier
                     .padding(6.dp)
                     .fillMaxSize()
             ){*/
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
            text = "Antall hunnlus per fisk",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .vertical()
                .rotate(-90f)
                .padding(8.dp)
            )

            ChartBody(
                chart = chart,
                chartEntryModelProducer = chartEntryModelProducer,
                diffAnimationSpec = diffAnimationSpec
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Uker",
                style = MaterialTheme.typography.bodySmall,
                //textAlign = TextAlign.Center,
                modifier = Modifier.padding(end=6.dp)
            )
        }
    }
}

fun Modifier.vertical() =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height - placeable.width )
            )
        }
    }

//TODO Adapt chart to be reusable component
@Composable
fun ChartBody(
    chart: ColumnChart,
    chartEntryModelProducer: ChartEntryModelProducer,
    diffAnimationSpec: AnimationSpec<Float>
) {
    Chart(
        chart = chart,
        chartModelProducer = chartEntryModelProducer,
        startAxis = createVerticalAxis {
            label = textComponent(
                color = Color(0xFF01809C),
                textSize = 12.sp,
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
                textSize = 12.sp
            ),
        ),
        diffAnimationSpec = diffAnimationSpec,
        marker = marker(),
        modifier = Modifier
            .fillMaxHeight()
    )
}


@Preview(showBackground = true, widthDp = 320, heightDp = 600)
@Composable
fun PreviewLouseChart() {
    val GENERATIONS = 2

    val chartEntries = ChartEntryModelProducer()
    chartEntries.setEntries(
        List(size = GENERATIONS) { getRandomEntries(Random.nextInt(5,12)) }
    )

    Column(
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        InfoCard {
            CustomBarChart(chartEntries)
        }
    }
}
