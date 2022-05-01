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
import com.example.gladlaksapp.composables.screens.FavoritesScreen
import com.example.gladlaksapp.composables.screens.MapScreen
import com.example.gladlaksapp.composables.screens.Screen
import com.example.gladlaksapp.composables.screens.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContainer {

                val navController = rememberNavController()

                //TODO: fjerne splashscreen fra stacken saa den kun kalles én gang
                NavHost(navController = navController, startDestination = getString(R.string.text_splash_screen)) {
                    composable(getString(R.string.text_splash_screen)) {
                        SplashScreen(navController = navController)
                    }
                    composable(Screen.Map.route) {
                        BottomNavScreenContainer(navController = navController) {
                            MapScreen(
                                //mViewModel = mViewModel,
                                //localityViewModel = localityViewModel,
                            )
                        }
                    }
                    composable(Screen.Favorites.route) {
                        BottomNavScreenContainer(navController = navController) {
                            FavoritesScreen(
                                //model = mViewModel
                            )
                        }
                    }
                    composable(Screen.Search.route) {
                        BottomNavScreenContainer(navController = navController) { Text("Søk side") }
                    }
                }
            }
        }
    }
}

