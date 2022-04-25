package com.example.gladlaksapp

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.gladlaksapp.composables.AppContainer
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gladlaksapp.composables.BottomNavScreenContainer
import com.example.gladlaksapp.composables.screens.FavoritesScreen
import com.example.gladlaksapp.composables.screens.MapScreen
import com.example.gladlaksapp.composables.screens.Screen
import com.example.gladlaksapp.composables.screens.SplashScreen
import kotlinx.coroutines.delay

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
                        BottomNavScreenContainer(navController = navController) { MapScreen() }
                    }
                    composable(Screen.Favorites.route) {
                        BottomNavScreenContainer(navController = navController) { FavoritesScreen() }
                    }
                    composable(Screen.Search.route) {
                        BottomNavScreenContainer(navController = navController) { Text("Søk side") }
                    }
                }
            }
        }
    }
}

