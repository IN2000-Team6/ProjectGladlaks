package com.example.gladlaksapp.composables


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.models.GraphLine
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import com.example.gladlaksapp.models.database.LocalityDatabase
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun FavoritesBottomSheet(
    localities: List<Locality>?,
    localityTemps: List<GraphLine>?,
    loadedLocality: LocalityDetailsWrapper?,
    loadLocalityDetails: (Locality) -> Unit,
    resetLoadedLocality: () -> Unit,
) {
    val database = LocalityDatabase
    val coroutineScope = rememberCoroutineScope()
    val initialPeekHeight = 0
    val selectedPeekHeight = 0

    // Local state
    var selectedLocality by remember { mutableStateOf<Locality?>(null) }
    var peekHeight by remember { mutableStateOf(initialPeekHeight) }
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )

    // Event handlers
    // må hente selected locality når se mer trykkes???
    fun onButtonClick(locality: Locality) {
        coroutineScope.launch {
            if (selectedLocality != null && selectedLocality!!.localityNo != locality.localityNo) {
                resetLoadedLocality()
            }
            selectedLocality = locality
            sheetState.bottomSheetState.expand()
        }

    }
    // her og?
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

    // Side effects
    LaunchedEffect(sheetState.bottomSheetState.isExpanded) {
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
            if (localities != null) {
                FavoritesColumn(
                    favoritesList = localities,
                    onClick = ::toggleBottomSheet,
                    onButtonClick = ::onButtonClick,
                    isCollapsed = sheetState.bottomSheetState.isCollapsed
                )
            } else {
                Text(
                    "HEIHEI LEGG TIL FAV A!"
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
                            onClick = ::toggleBottomSheet,
                            isCollapsed = sheetState.bottomSheetState.isCollapsed,
                        )
                    }
                }
                LocalitySheetContent(
                    selectedLocality = selectedLocality,
                    loadedLocality = loadedLocality,
                    graphLines = localityTemps
                )
            }
        },
    )
}


