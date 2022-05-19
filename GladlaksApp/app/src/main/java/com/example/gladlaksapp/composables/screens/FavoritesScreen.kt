package com.example.gladlaksapp.composables.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gladlaksapp.composables.favorite.FavoritesBottomSheet
import com.example.gladlaksapp.viewmodels.LocalityViewModel

@Composable
fun FavoritesScreen(
    locViewModel: LocalityViewModel = hiltViewModel()
) {
    val loadedLocality by locViewModel.localityDetail.observeAsState()
    val localityTemps by locViewModel.localityTemps.observeAsState()

    FavoritesBottomSheet(
        loadedLocality = loadedLocality,
        resetLoadedLocality = { locViewModel.resetLoadedLocality() },
        localityTemps = localityTemps,
        loadLocalityDetails = { loc ->
            locViewModel.loadLocalityDetails(loc)
        }
    )
}

