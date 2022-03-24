package com.example.gladlaksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.gladlaksapp.viewmodels.MainViewModel
import com.example.gladlaksapp.composables.AppContainer
import com.example.gladlaksapp.composables.MapBottomSheet

class MainActivity : ComponentActivity() {
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val localities by model.localities.observeAsState()

            AppContainer {
                MapBottomSheet(localities = localities)
            }
        }
    }
}
