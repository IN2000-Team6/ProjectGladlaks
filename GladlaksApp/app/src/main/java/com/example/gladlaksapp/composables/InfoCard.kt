package com.example.gladlaksapp.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoCard(
    content: @Composable () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(3.dp),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp),
        containerColor = Color.White,
    ) {
        Box {
            content()
        }
    }
}