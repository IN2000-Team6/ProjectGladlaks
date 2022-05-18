package com.example.gladlaksapp.composables.favorite


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
import com.example.gladlaksapp.composables.FavoritesColumn
import com.example.gladlaksapp.composables.LocalitySheetContent
import com.example.gladlaksapp.composables.LocalitySnippet
import com.example.gladlaksapp.composables.NetworkNotice
import com.example.gladlaksapp.composables.locality.ToggleArrowButton
import com.example.gladlaksapp.models.*
import com.example.gladlaksapp.viewmodels.FavoriteViewModel
import com.example.gladlaksapp.viewmodels.LocalityViewModel
import com.example.gladlaksapp.models.ConnectionState
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun FavoritesBottomSheet(
    localityTemps: List<GraphLine>?,
    loadedLocality: LocalityDetailsWrapper?,
    loadLocalityDetails: (Locality) -> Unit,
    resetLoadedLocality: () -> Unit,
    locViewModel: LocalityViewModel = hiltViewModel(),
    favViewModel: FavoriteViewModel = hiltViewModel(),
) {
    val localities by locViewModel.favoriteLocalities.observeAsState()

    val coroutineScope = rememberCoroutineScope()
    val initialPeekHeight = 0
    val selectedPeekHeight = 0

    // Local state
    var selectedLocality by remember { mutableStateOf<Locality?>(null) }
    var peekHeight by remember { mutableStateOf(initialPeekHeight) }
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )

    val favorites by favViewModel.favorites.observeAsState()
    var favoriteLocality by remember { mutableStateOf<FavoriteLocality?>(null) }

    val redColor = MaterialTheme.colorScheme.error
    val grayColor = MaterialTheme.colorScheme.surface
    val favButtonTint = if (favoriteLocality?.isFavorite == true) redColor else grayColor

    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available


    LaunchedEffect(favorites, selectedLocality) {
        if (favorites != null && selectedLocality != null)
            favoriteLocality =
                favorites?.filter { it.localityNo == selectedLocality?.localityNo }?.get(0)
    }

    // Event handlers
    fun onButtonClick(locality: Locality) {
        coroutineScope.launch {
            if (selectedLocality != null && selectedLocality!!.localityNo != locality.localityNo) {
                resetLoadedLocality()
            }
            selectedLocality = locality
            sheetState.bottomSheetState.expand()
        }

    }

    fun toggleBottomSheet() {
        coroutineScope.launch {
            //sheetState.bottomSheetState.expand()
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
                if (favoriteLocality?.isFavorite == false) favViewModel.addFavorite(favoriteLocality!!)
                else favoriteLocality?.let { favViewModel.deleteFavorite(it) }
            }
        }
    }

    // Side effects
    LaunchedEffect(sheetState.bottomSheetState.isExpanded) {
        if ((selectedLocality != null && isConnected)) {
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
            if (localities != null) {
                FavoritesColumn(
                    favoritesList = localities!!,
                    toggleFavorite = ::toggleFavorite,
                    onButtonClick = ::onButtonClick,
                    isCollapsed = sheetState.bottomSheetState.isCollapsed,
                    favButtonTint = redColor,
                )
            }
        },
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ToggleArrowButton(
                    isExpanded = sheetState.bottomSheetState.isExpanded,
                    onClick = ::toggleBottomSheet,
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

                if (!isConnected) {
                    NetworkNotice()
                } else {
                    LocalitySheetContent(
                        selectedLocality = selectedLocality,
                        loadedLocality = loadedLocality,
                        graphLines = localityTemps
                    )
                }
            }
        },
    )
}


