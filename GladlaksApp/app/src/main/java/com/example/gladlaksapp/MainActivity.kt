package com.example.gladlaksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.gladlaksapp.composables.appContainer.AppContainer
import com.example.gladlaksapp.composables.LocalityMap
import com.example.gladlaksapp.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val data by model.localities.observeAsState()

            AppContainer {
                Box(Modifier.fillMaxSize()) {
                    LocalityMap(data)
                }
            }
        }
    }
}
