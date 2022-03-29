package com.example.gladlaksapp.models

import androidx.compose.ui.graphics.Color

data class GraphCoords(
    val x: Float,
    val y: Float,
)

data class GraphLine(
    val label: String,
    val color: Color,
    val coords: List<GraphCoords>,
    val lineWidth: Float = 3f,
)