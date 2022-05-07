package com.example.gladlaksapp.composables

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
    //isFavorite: Boolean,
    favViewModel: FavoriteViewModel = hiltViewModel(),
){
    //TODO rewrite favorite status
    val coroutineScope = rememberCoroutineScope()

    val favorites by favViewModel.favorites.observeAsState()

    val isFavorite = favorites?.any { it.localityNo == locality.localityNo }

    val redColor = Color(0xFFEC407A)
    val grayColor = Color(0xFFB0BEC5)

    fun toggleFavorite(){
        coroutineScope.launch {
            favViewModel.toggleFavorite(locality)
            Log.d("Check bool", "$isFavorite, (${favorites?.size})")
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
            tint = redColor, //if (isFavorite == true) redColor else grayColor),
        )
    }

}
