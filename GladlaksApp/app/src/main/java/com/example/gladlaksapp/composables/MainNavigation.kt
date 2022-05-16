package com.example.gladlaksapp.composables

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
        controller.navigate(screen.route) {
            popUpTo(controller.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }

    BottomNavigation (backgroundColor = Color(0xFFe9f2f1)) {
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