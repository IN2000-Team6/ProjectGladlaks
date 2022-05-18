package com.example.gladlaksapp.composables.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gladlaksapp.composables.MapBottomSheet
import com.example.gladlaksapp.models.ConnectionState
import com.example.gladlaksapp.viewmodels.FavoriteViewModel
import com.example.gladlaksapp.viewmodels.LocalityViewModel
import com.example.gladlaksapp.models.connectivityState

@Composable
fun MapScreen(
    mViewModel: LocalityViewModel = hiltViewModel(),
    favViewModel: FavoriteViewModel = hiltViewModel(),
    ) {
    //TODO Insert all localities to db using background thread

    val localities by mViewModel.localities.observeAsState()
    val loadedLocality by mViewModel.localityDetail.observeAsState()
    val localityTemps by mViewModel.localityTemps.observeAsState()

    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

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