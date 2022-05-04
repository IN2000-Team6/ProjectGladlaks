package com.example.gladlaksapp.composables

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

    val isFavorite = remember {
        mutableStateOf(false)
    }

    fun isFavorite(): Boolean {
        coroutineScope.launch {
            isFavorite.value = !favoriteViewModel.isSaved(locality)
        }
        return isFavorite.value
    }

    //Added saveToFavorites here so only icon button recomposes on click (?)
    fun saveToFavorites(){
        coroutineScope.launch {
            val favoriteLocality = mutableStateOf(
                locality?.let { FavoriteLocality(it.localityNo) }
            )
            favoriteViewModel.addFavoriteToDb(favoriteLocality.value)
        }
    }

    fun deleteFavorite(){
        coroutineScope.launch{
            val favoriteLocality = mutableStateOf(
                locality?.let { FavoriteLocality(it.localityNo) }
            )
            favoriteViewModel.deleteFavorite(favoriteLocality.value)
        }
    }

    fun toggleFavorite(){
        if (!isFavorite()){
            saveToFavorites()
        }else{
            deleteFavorite()
        }
    }

    IconButton(
        onClick = ::toggleFavorite,
        modifier = Modifier
            .padding(end = 5.dp)
            .size(width = 40.dp, height = 40.dp),
    ) {
        val tint by animateColorAsState(
            if (isFavorite.value) Color(0xFFEC407A)
            else Color(0xFFB0BEC5)
        )
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Hjerteformet knapp",
            tint = tint)
    }
}