package com.example.gladlaksapp.models

data class GraphCoords(
    val x: Float,
    val y: Float,
)

data class GraphLine(
    val label: String,
    val coords: List<GraphCoords>,
)
