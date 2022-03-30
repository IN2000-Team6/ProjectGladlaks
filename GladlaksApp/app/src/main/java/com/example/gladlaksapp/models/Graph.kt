package com.example.gladlaksapp.models

import androidx.compose.ui.graphics.Color

data class GraphCoords(
    val x: Float,
    val y: Float,
)

data class GraphLine(
    val label: String,
    val coords: List<GraphCoords>,
    val color: Color? = null,
    val lineWidth: Float = 3f,
)