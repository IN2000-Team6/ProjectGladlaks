package com.example.gladlaksapp.composables.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gladlaksapp.composables.FavoritesBottomSheet
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.viewmodels.LoadedFavoriteViewModel
import com.example.gladlaksapp.viewmodels.MainViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

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