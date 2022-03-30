package com.example.gladlaksapp.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.gladlaksapp.models.LocalityDetailsWrapper

@Composable
fun LocalitySheetContent(loadedLocality: LocalityDetailsWrapper?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        if (loadedLocality == null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = Color(0xFF9CDCDA)
                )
            }
        } else {
            InfoCard (
                content = { LocalityInfo(localityInfo = loadedLocality.localityWeek) }
            )
            Text(loadedLocality.localityName)
        }
    }
}