package com.example.gladlaksapp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gladlaksapp.models.Locality

@Composable
fun LocalitySheetTop(
    locality: Locality?,
) {
    if (locality != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Text("Lokasjon: ${locality.name}")
        }
    }
}