package com.example.gladlaksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.gladlaksapp.composables.AppContainer
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gladlaksapp.composables.BottomNavScreenContainer
import com.example.gladlaksapp.composables.screens.MapScreen
import com.example.gladlaksapp.composables.screens.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContainer {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Screen.Map.route) {
                    composable(Screen.Map.route) {
                        BottomNavScreenContainer(navController = navController) { MapScreen() }
                    }
                    composable(Screen.Favorites.route) {
                        BottomNavScreenContainer(navController = navController) { Text("Favoritter side") }
                    }
                    composable(Screen.Search.route) {
                        BottomNavScreenContainer(navController = navController) { Text("Søk side") }
                    }
                }
            }
        }
    }
}