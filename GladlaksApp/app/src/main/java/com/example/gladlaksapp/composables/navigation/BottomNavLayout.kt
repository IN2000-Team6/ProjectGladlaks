package com.example.gladlaksapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavLayout(
    content: @Composable () -> Unit,
    bottomNavContent: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.weight(1f),
            content = {content()}
        )
        bottomNavContent()
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayBottomNavLayout() {
    BottomNavLayout(
        content = {  },
        bottomNavContent = {
            // Simulate bottom nav composable
            Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.Gray))
        }
    )
}

