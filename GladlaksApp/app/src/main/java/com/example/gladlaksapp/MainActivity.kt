package com.example.gladlaksapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.gladlaksapp.composables.LocalityMap
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.viewmodels.MainViewModel
import com.example.gladlaksapp.composables.AppContainer
import com.example.gladlaksapp.composables.MapBottomSheet

class MainActivity : ComponentActivity() {
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val localities by model.localities.observeAsState()

            val onMarkerClick = { loc: Locality ->
                Toast.makeText(this, "Location: ${loc.name}", Toast.LENGTH_SHORT).show()
                true
            }

            AppContainer {
                MapBottomSheet(localities = localities)
            }
        }
    }
}
