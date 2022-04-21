package com.example.gladlaksapp.composables.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gladlaksapp.composables.MapBottomSheet
import com.example.gladlaksapp.viewmodels.MainViewModel

@Composable
fun MapScreen(
    model: MainViewModel = viewModel()
) {
    val localities by model.localities.observeAsState()
    val loadedLocality by model.localityDetail.observeAsState()
    val localityTemps by model.localityTemps.observeAsState()

    MapBottomSheet(
        localities = localities,
        loadedLocality = loadedLocality,
        resetLoadedLocality = { model.resetLoadedLocality() },
        localityTemps = localityTemps,
        loadLocalityDetails = { loc ->
            model.loadLocalityDetails(loc)
        },
    )
}