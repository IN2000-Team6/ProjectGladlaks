package com.example.gladlaksapp.composables

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gladlaksapp.R

@Composable
fun MainNavigation(
    onClick: () -> Unit,
    selectedItemIndex: Int,
) {
    val backgroundColor = Color(0xFF01809C)
    val items = listOf(
        NavItem(R.string.locality, Icons.Filled.Place),
        NavItem(R.string.favorites, Icons.Filled.Favorite),
        NavItem(R.string.search, Icons.Filled.Search),
        NavItem(R.string.settings, Icons.Filled.Settings),
    )

    BottomNavigation (backgroundColor = Color(0xFFe9f2f1)) {
        items.forEachIndexed { index, item ->
            val label = stringResource(item.labelId)
            BottomNavigationItem(
                selected = selectedItemIndex == index,
                onClick = onClick,
                label = { Text(label) },
                icon = { Icon(item.icon, contentDescription = label, tint = backgroundColor)}
            )
        }
    }
}

data class NavItem(
    val labelId: Int,
    val icon: ImageVector,
)

@Preview
@Composable
fun DisplayMainNavigation() {
    MainNavigation(
        onClick = { },
        selectedItemIndex = 0,
    )
}