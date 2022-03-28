package com.example.gladlaksapp.models

import androidx.compose.ui.graphics.Color
import com.example.gladlaksapp.ui.theme.Purple500
import com.example.gladlaksapp.ui.theme.Teal200

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

val line1 = GraphLine(
    coords = listOf(
        GraphCoords(0f, 10f),
        GraphCoords(1f, 4f),
        GraphCoords(2f, 6f),
        GraphCoords(3f, 3f),
        GraphCoords(4f, 9f),
        GraphCoords(5f, 7f),
        GraphCoords(6f, 8f),
        GraphCoords(8f, 6f),
    ),
    label = "3 meter",
    color = Teal200,
)

val line2 = GraphLine(
    coords = listOf(
        GraphCoords(0f, 7f),
        GraphCoords(1f, 8f),
        GraphCoords(2f, 4f),
        GraphCoords(3f, 5f),
        GraphCoords(4f, 6f),
        GraphCoords(5f, 6f),
        GraphCoords(6f, 8f),
        GraphCoords(8f, 3f),
    ),
    label = "10 meter",
    color = Purple500,
)