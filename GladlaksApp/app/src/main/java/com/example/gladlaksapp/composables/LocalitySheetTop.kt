package com.example.gladlaksapp.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gladlaksapp.models.Locality

@Composable
fun LocalitySheetTop(
    locality: Locality?,
) {
    if (locality != null) {
        Row(
            modifier = Modifier.fillMaxSize(),
            //contentAlignment = Alignment.TopStart
        ) {
            LocalitySnippet(locality = locality)
            //Text("Lokasjon: ${locality.name}")
        }
    }
}

@Preview
@Composable
fun DisplayLocalitySheetTop() {
    LocalitySheetTop(
        Locality(
            localityNo = 1,
            name = "Test",
            lat = 10.3,
            lon = 13.4,
            isOnLand = false,
        )
    )
}