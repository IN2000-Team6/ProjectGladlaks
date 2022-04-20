package com.example.gladlaksapp.composables.lousechart

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.models.LouseData

@Composable
fun customGroupedBarChart(
    generations: List<List<LouseData>>,
    textSize: Int = 14,
    height: Int,
) {
    val size = (textSize.dp).value
    val colors = listOf(Color(0xFFFFBDAE), Color(0xFF9c4331))
}