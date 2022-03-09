package com.example.gladlaksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
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
                        val scope = rememberCoroutineScope()

                        val sheetState = rememberBottomSheetScaffoldState(
                            bottomSheetState = rememberBottomSheetState(
                                initialValue = BottomSheetValue.Collapsed
                            )
                        )

                        BottomSheetScaffold(
                            sheetContent = {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .padding(bottom = 30.dp, start = 16.dp, end = 16.dp, top = 16.dp)
                                ) {
                                    Text(
                                        text = "Detaljer",
                                        modifier = Modifier
                                            .padding(bottom = 4.dp)
                                    )
                                    CustomBarChart(400)
                                }
                            },
                            scaffoldState = sheetState,
                            sheetElevation = 20.dp,
                            sheetPeekHeight = 65.dp,
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp)
                            ) {
                                Greeting(resources.getString(R.string.app_name))
                                Button(
                                    modifier = Modifier.padding(top = 10.dp),
                                    onClick = {
                                        scope.launch { sheetState.bottomSheetState.expand()
                                    }
                                }) {
                                    Text("VIS GRAF")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomBarChart(height: Int) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp),
        factory = { context ->
            BarChart(context).apply {
                val data = BarData(getDate())
                data.barWidth = 0.2f
                this.data = data
                this.animateY(500)
                this.invalidate()
                this.xAxis.position = XAxis.XAxisPosition.BOTTOM
                this.axisRight.isEnabled = false
                this.description.isEnabled = false
                this.setScaleEnabled(false)
                this.isHovered = false
                this.axisLeft.axisMinimum = 0f
            }
        },
    )
}

@Composable
fun Greeting(name: String) {
    Text("Velkommen til $name!")
}

fun getDate(): List<IBarDataSet> {
    val set1 = mutableListOf(
        BarEntry(0f, 110.000f),
        BarEntry(1f, 40.000f),
        BarEntry(2f, 60.000f),
        BarEntry(3f, 30.000f),
        BarEntry(4f, 90.000f),
        BarEntry(5f, 100.000f),
        BarEntry(6f, 150.000f),
        BarEntry(7f, 150.000f),
    )

    val barDataSet1 = BarDataSet(set1, "Data label")

    barDataSet1.setColors(*ColorTemplate.PASTEL_COLORS)

    return listOf(barDataSet1)
}