package com.example.gladlaksapp.composables.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gladlaksapp.composables.FavoritesBottomSheet
import com.example.gladlaksapp.viewmodels.LoadedFavoriteViewModel

@Composable
fun FavoritesScreen(
    lfViewModel: LoadedFavoriteViewModel = hiltViewModel()
) {
    val loadedLocality by lfViewModel.localityDetail.observeAsState()
    val localityTemps by lfViewModel.localityTemps.observeAsState()

    FavoritesBottomSheet(
        loadedLocality = loadedLocality,
        resetLoadedLocality = { lfViewModel.resetLoadedLocality() },
        localityTemps = localityTemps,
        loadLocalityDetails = { loc ->
            lfViewModel.loadLocalityDetails(loc)
        },
    )
}

