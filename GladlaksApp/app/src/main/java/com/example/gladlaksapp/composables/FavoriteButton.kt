package com.example.gladlaksapp.composables

import android.util.Log
import androidx.compose.animation.animateColorAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.database.FavoriteLocality
import com.example.gladlaksapp.viewmodels.FavoriteViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoriteButton(
    locality: Locality,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
){
    val coroutineScope = rememberCoroutineScope()

    var isFavorite by remember {
        mutableStateOf(locality.isFavorite)
    }

    val redColor = Color(0xFFEC407A)
    val grayColor = Color(0xFFB0BEC5)

    fun toggleFavorite(){
        coroutineScope.launch {
            val favoriteLocality = mutableStateOf(
                FavoriteLocality(locality.localityNo)
            )

            if (isFavorite) {
                favoriteViewModel.deleteFavorite(favoriteLocality.value)
                isFavorite = false
            } else {
                favoriteViewModel.addFavoriteToDb(favoriteLocality.value)
                isFavorite = true
            }
        }
    }

    IconButton(
        onClick = ::toggleFavorite,
        modifier = Modifier
            .padding(end = 5.dp)
            .size(width = 40.dp, height = 40.dp),
    ) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Hjerteformet knapp",
            tint = if (isFavorite) redColor else grayColor)
    }
}