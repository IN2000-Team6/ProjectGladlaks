package com.example.gladlaksapp.composables.map

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gladlaksapp.composables.locality.LocalitySheetContent
import com.example.gladlaksapp.composables.locality.LocalitySnippet
import com.example.gladlaksapp.composables.locality.ToggleArrowButton
import com.example.gladlaksapp.models.GraphLine
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import com.example.gladlaksapp.models.FavoriteLocality
import com.example.gladlaksapp.viewmodels.FavoriteViewModel
import kotlinx.coroutines.launch

/**
 * Main structure that is responsible for the bottom sheet functionality.
 * Takes in various different arguments.
 */
@Composable
@OptIn(ExperimentalMaterialApi::class)
fun MapBottomSheet(
    localities: List<Locality>?,
    localityTemps: List<GraphLine>?,
    loadedLocality: LocalityDetailsWrapper?,
    loadLocalityDetails: (Locality) -> Unit,
    resetLoadedLocality: () -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val initialPeekHeight = 0
    val selectedPeekHeight = 88 // The height of the bottom sheet when locality has been selected

    // Local state
    var selectedLocality by remember { mutableStateOf<Locality?>(null) }
    var peekHeight by remember { mutableStateOf(initialPeekHeight) }
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )

    val favorites by viewModel.favorites.observeAsState()
    var favoriteLocality by remember { mutableStateOf<FavoriteLocality?>(null) }

    val redColor = MaterialTheme.colorScheme.error
    val grayColor = MaterialTheme.colorScheme.surface
    val favButtonTint = if (favoriteLocality?.isFavorite == true) redColor else grayColor

    // Event handlers
    fun onMarkerClick(locality: Locality) {
        if (selectedLocality != null && selectedLocality!!.localityNo != locality.localityNo) {
            resetLoadedLocality()
        }
        selectedLocality = locality
        peekHeight = selectedPeekHeight
    }

    fun toggleBottomSheet() {
        coroutineScope.launch {
            if (sheetState.bottomSheetState.isCollapsed) {
                sheetState.bottomSheetState.expand()
            } else {
                sheetState.bottomSheetState.collapse()
            }
        }
    }

    fun onMapClick() {
        coroutineScope.launch {
            peekHeight = initialPeekHeight
            sheetState.bottomSheetState.collapse()
        }
    }

    fun onArrowClick() { // The arrow to open the bottom sheet
        coroutineScope.launch {
            if (sheetState.bottomSheetState.isCollapsed) {
                sheetState.bottomSheetState.expand()
            } else {
                peekHeight = selectedPeekHeight
                sheetState.bottomSheetState.collapse()
            }
        }
    }

    fun toggleFavorite() {
        coroutineScope.launch {
            if (favoriteLocality != null) {
                if (favoriteLocality?.isFavorite == false) viewModel.addFavorite(favoriteLocality!!)
                else favoriteLocality?.let { viewModel.deleteFavorite(it) }
            }
        }
    }

    // Side effects
    LaunchedEffect(favorites, selectedLocality) {
        // Set the initial state of the favorite (hart) state of the locality
        if (favorites != null && selectedLocality != null)
            favoriteLocality =
                favorites?.filter { it.localityNo == selectedLocality?.localityNo }?.get(0)
    }

    LaunchedEffect(sheetState.bottomSheetState.isExpanded) {
        // Load detailed locality data when bottom sheet is expanded
        if (selectedLocality != null) {
            if (loadedLocality == null || loadedLocality.localityName != selectedLocality!!.name) {
                loadLocalityDetails(selectedLocality!!)
            }
        }
    }

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(if (sheetState.bottomSheetState.isExpanded) 0.dp else 16.dp),
        sheetPeekHeight = peekHeight.dp,
        scaffoldState = sheetState,
        content = {
            LocalityMap(
                localities = localities,
                onMarkerClick = ::onMarkerClick,
                onMapClick = ::onMapClick,
            )
        },
        sheetContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ToggleArrowButton(
                    isExpanded = sheetState.bottomSheetState.isExpanded,
                    onClick = ::onArrowClick,
                )
                Box(modifier = Modifier.padding(bottom = 25.dp)) {
                    selectedLocality?.let {
                        LocalitySnippet(
                            locality = it,
                            onExpandClick = ::toggleBottomSheet,
                            isCollapsed = sheetState.bottomSheetState.isCollapsed,

                            toggleFavorite = ::toggleFavorite,
                            favButtonTint = favButtonTint,
                        )
                    }
                }
                LocalitySheetContent(
                    selectedLocality = selectedLocality,
                    loadedLocality = loadedLocality,
                    graphLines = localityTemps,
                )
            }
        }
    )
}


