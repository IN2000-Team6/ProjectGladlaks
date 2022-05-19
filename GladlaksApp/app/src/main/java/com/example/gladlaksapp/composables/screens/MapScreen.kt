package com.example.gladlaksapp.composables.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gladlaksapp.composables.map.MapBottomSheet
import com.example.gladlaksapp.composables.reusables.NetworkNotice
import com.example.gladlaksapp.utils.ConnectionState
import com.example.gladlaksapp.viewmodels.LocalityViewModel
import com.example.gladlaksapp.utils.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MapScreen(
    viewModel: LocalityViewModel = hiltViewModel(),
) {
    val localities by viewModel.localities.observeAsState()
    val loadedLocality by viewModel.localityDetail.observeAsState()
    val localityTemps by viewModel.localityTemps.observeAsState()

    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    if (!isConnected) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            NetworkNotice()
        }
    } else {
        MapBottomSheet(
            localities = localities,
            loadedLocality = loadedLocality,
            resetLoadedLocality = { viewModel.resetLoadedLocality() },
            localityTemps = localityTemps,
            loadLocalityDetails = { loc ->
                viewModel.loadLocalityDetails(loc)
            },
        )
    }
}