package com.example.gladlaksapp.composables.favorite

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    toggleFavorite: () -> Unit,
    favButtonTint: Color
){
    IconButton(
        onClick = toggleFavorite,
        modifier = Modifier
            .padding(end = 5.dp)
            .size(width = 40.dp, height = 40.dp),
    ) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Hjerteformet knapp",
            tint = favButtonTint
        )
    }
}