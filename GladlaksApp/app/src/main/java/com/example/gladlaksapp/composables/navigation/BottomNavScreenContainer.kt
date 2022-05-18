package com.example.gladlaksapp.composables.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.gladlaksapp.composables.BottomNavLayout
import com.example.gladlaksapp.composables.MainNavigation
import com.example.gladlaksapp.composables.screens.screens

@Composable
fun BottomNavScreenContainer(
    navController: NavController,
    content: @Composable () -> Unit,
) {
    BottomNavLayout(
        content = content,
        bottomNavContent = {
            MainNavigation(
                controller = navController,
                items = screens
            )
        },
    )
}