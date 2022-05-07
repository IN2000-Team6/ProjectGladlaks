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

    //val localities = testLocalities
    val localities by lfViewModel.localities.observeAsState()
    val loadedLocality by lfViewModel.localityDetail.observeAsState()
    val localityTemps by lfViewModel.localityTemps.observeAsState()


    FavoritesBottomSheet(
        localities = localities,
        loadedLocality = loadedLocality,
        resetLoadedLocality = { lfViewModel.resetLoadedLocality() },
        localityTemps = localityTemps,
        loadLocalityDetails = { loc ->
            lfViewModel.loadLocalityDetails(loc)
        },
    )
}

// DUMMY DATA
val testLocalities = arrayListOf(
    Locality(localityNo = 1, name="a", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 2, name="b", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 3, name="c", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = true),
    Locality(localityNo = 4, name="d", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 5, name="e", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = true),
    Locality(localityNo = 6, name="f", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
)