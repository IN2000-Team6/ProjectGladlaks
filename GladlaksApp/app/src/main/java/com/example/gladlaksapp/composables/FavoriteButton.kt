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

//TODO rewrite favorite status
//val coroutineScope = rememberCoroutineScope()
/*
    val favorites by favViewModel.favorites.observeAsState()
    //    val favorites = favViewModel.favorites

    val isFavorite = favorites?.filter { it.localityNo == locality.localityNo }
    val len = favorites?.size

    LaunchedEffect(key1 = null) {
        favViewModel.loadFavorites()
    }



    fun toggleFavorite(){
        coroutineScope.launch {
            favViewModel.toggleFavorite(locality)
            Log.d("Check bool", "$isFavorite, ($len)")
            Log.d("Selected loc", "${locality.localityNo}")
            Log.d("loc in favs", "${favorites?.filter{it.localityNo == locality.localityNo}}")
            Log.d("Check bool", "After: $isFavorite, ($len)")
        }
    }

 */