package com.example.gladlaksapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.gladlaksapp.ui.theme.GladlaksAppTheme
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GladlaksAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        Greeting(resources.getString(R.string.app_name))
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .height(350.dp)
                                .shadow(8.dp)
                        ) {
                            ChartTest(10)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChartTest(padding: Int) {
    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding.dp),
        factory = { context ->
            BarChart(context).apply {
                val data = BarData(getDate())
                data.barWidth = 0.2f
                this.data = data
                this.animateY(500);
                this.invalidate();
                this.xAxis.position = XAxis.XAxisPosition.BOTTOM
                this.axisRight.isEnabled = false
                this.description.isEnabled = false
                this.setPinchZoom(false)
            }
        },
    )
}

@Composable
fun Greeting(name: String) {
    Text("Velkommen til $name!!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GladlaksAppTheme {
        Greeting("Hello")
    }
}

fun getDate(): List<IBarDataSet> {
    val set1 = mutableListOf(
        BarEntry(0f,110.000f),
        BarEntry(1f,40.000f),
        BarEntry(2f,60.000f),
        BarEntry(3f,30.000f),
        BarEntry(4f,90.000f),
        BarEntry(5f,100.000f),
        BarEntry(6f,150.000f),
    )

    val barDataSet1 = BarDataSet(set1, "Data label")

    barDataSet1.setColors(*ColorTemplate.COLORFUL_COLORS)

    return listOf(barDataSet1)
}