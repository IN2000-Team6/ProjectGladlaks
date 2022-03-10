package com.example.gladlaksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.gladlaksapp.composables.appContainer.AppContainer
import com.example.gladlaksapp.composables.localityMap.LocalityMap
import com.example.gladlaksapp.models.Locality

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val testLocalities = listOf(
            Locality(123445, "Location", 59.913868, 10.752245, true)
        )

        setContent {
            AppContainer {
                Box(Modifier.fillMaxSize()) {
                    LocalityMap(testLocalities)
                }
            }
        }
    }
}
