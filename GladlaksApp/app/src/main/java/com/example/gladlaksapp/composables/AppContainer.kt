package com.example.gladlaksapp.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gladlaksapp.ui.theme.GladlaksAppTheme

@Composable
fun AppContainer(content: @Composable () -> Unit) {
    GladlaksAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}