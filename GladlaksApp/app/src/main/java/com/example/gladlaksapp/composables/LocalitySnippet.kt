package com.example.gladlaksapp.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gladlaksapp.models.Locality

@Composable
fun LocalitySnippet(
    locality: Locality
) {
   // TODO make the snippet composable
}

@Preview
@Composable
fun DisplayLocalitySnippet() {
    LocalitySnippet(
        Locality(
            localityNo = 1,
            name = "Test",
            lat = 10.3,
            lon = 13.4,
            isOnLand = false,
        )
    )
}