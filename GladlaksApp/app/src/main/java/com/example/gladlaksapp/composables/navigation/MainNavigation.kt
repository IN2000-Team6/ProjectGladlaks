package com.example.gladlaksapp.composables.navigation

import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gladlaksapp.composables.screens.Screen

@Composable
fun MainNavigation(
    items: List<Screen>,
    controller: NavController
) {
    fun onNavClick(screen: Screen) {
        Log.d("flow", controller.backQueue.toString())
        controller.navigate(screen.route) {
            controller.graph.startDestinationRoute?.let { screen_route ->
                popUpTo(screen_route) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    BottomNavigation (backgroundColor = MaterialTheme.colorScheme.background) {
        val navBackStackEntry by controller.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            val label = stringResource(screen.resourceId)

            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = { onNavClick(screen) },
                label = { Text(label) },
                icon = { Icon(screen.icon, contentDescription = label, tint = Color(0xFF01809C))}
            )
        }
    }
}