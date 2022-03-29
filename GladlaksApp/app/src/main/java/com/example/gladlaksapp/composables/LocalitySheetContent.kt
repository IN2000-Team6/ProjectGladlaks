package com.example.gladlaksapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.gladlaksapp.models.LocalityDetailsWrapper

@Composable
fun LocalitySheetContent(loadedLocality: LocalityDetailsWrapper?) {
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color.LightGray)
    ) {
        Text("Poop")
    }
}