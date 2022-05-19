package com.example.gladlaksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gladlaksapp.composables.navigation.BottomNavScreenContainer
import com.example.gladlaksapp.composables.reusables.AppContainer
import com.example.gladlaksapp.composables.screens.FavoritesScreen
import com.example.gladlaksapp.composables.screens.MapScreen
import com.example.gladlaksapp.composables.screens.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppContainer {
                val navController = rememberNavController()

                // Entry point for navigation in the app
                NavHost(navController = navController, startDestination = Screen.Favorites.route) {
                    composable(Screen.Map.route) {
                        BottomNavScreenContainer(navController = navController) {
                            MapScreen()
                        }
                    }
                    composable(Screen.Favorites.route) {
                        BottomNavScreenContainer(navController = navController) {
                            FavoritesScreen()
                        }
                    }
                }
            }
        }
    }
}

