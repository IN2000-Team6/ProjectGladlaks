package com.example.gladlaksapp.composables.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gladlaksapp.composables.FavoritesBottomSheet
import com.example.gladlaksapp.composables.MapBottomSheet
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.viewmodels.MainViewModel

@Composable
fun FavoritesScreen(
    model: MainViewModel = viewModel()
) {
    //endre til databasekall?
    val localities by model.localities.observeAsState()
    val loadedLocality by model.localityDetail.observeAsState()
    val localityTemps by model.localityTemps.observeAsState()

    FavoritesBottomSheet(
        localities = testLocalities,
        loadedLocality = loadedLocality,
        resetLoadedLocality = { model.resetLoadedLocality() },
        localityTemps = localityTemps,
        loadLocalityDetails = { loc ->
            model.loadLocalityDetails(loc)
        },
    )
}


val testLocalities = arrayListOf(
    Locality(localityNo = 1, name="a", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 2, name="b", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 3, name="c", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = true),
    Locality(localityNo = 4, name="d", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 5, name="e", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = true),
    Locality(localityNo = 6, name="f", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
)