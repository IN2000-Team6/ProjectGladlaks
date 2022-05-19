package com.example.gladlaksapp.composables.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Block
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gladlaksapp.R

/**
 * Class representing our application screens
 */
sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Map : Screen("map", R.string.locality, Icons.Filled.Place)
    object Favorites : Screen("favorites", R.string.favorites, Icons.Filled.Favorite)
    object Splash : Screen("splash", R.string.splash, Icons.Filled.Block)
}

val screens = listOf(
    Screen.Map,
    Screen.Favorites,
)