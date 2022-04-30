package com.example.gladlaksapp.composables.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gladlaksapp.composables.MapBottomSheet
import com.example.gladlaksapp.viewmodels.LocalityViewModel
import com.example.gladlaksapp.viewmodels.MainViewModel

@Composable
fun MapScreen(
    mViewModel: MainViewModel = viewModel()
    //localityViewModel: LocalityViewModel,
) {
    //TODO Insert all localities to db using background thread

    val localities by mViewModel.localities.observeAsState()
    val loadedLocality by mViewModel.localityDetail.observeAsState()
    val localityTemps by mViewModel.localityTemps.observeAsState()

    MapBottomSheet(
        localities = localities,
        loadedLocality = loadedLocality,
        resetLoadedLocality = { mViewModel.resetLoadedLocality() },
        localityTemps = localityTemps,
        loadLocalityDetails = { loc ->
            mViewModel.loadLocalityDetails(loc)
        },
    )
}