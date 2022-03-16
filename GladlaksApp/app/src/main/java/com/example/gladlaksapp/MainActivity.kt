package com.example.gladlaksapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.example.gladlaksapp.composables.appContainer.AppContainer
import com.example.gladlaksapp.datasources.BarentswatchNetworkDataSource
import com.example.gladlaksapp.models.Locality
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //rot rot rot

        val testLocalities = listOf(
            Locality(123445, "Location", 59.913868, 10.752245, true)
        )

        setContent {
            AppContainer {
                Box(Modifier.fillMaxSize()) {
                    Text("FUCK!!!")
                }
            }
        }
    }
}
