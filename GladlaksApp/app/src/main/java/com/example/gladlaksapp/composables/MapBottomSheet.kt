package com.example.gladlaksapp.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.R
import com.example.gladlaksapp.models.Locality
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun MapBottomSheet(
    localities: List<Locality>?
) {
    val coroutineScope = rememberCoroutineScope()
    val initialPeekHeight = 0
    val selectedPeekHeight = 100

    var selectedLocality by rememberSaveable { mutableStateOf<Locality?>(null) }
    var peekHeight by rememberSaveable { mutableStateOf(initialPeekHeight) }
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
    )

    // TODO move onMarkerClick and onToggleSheet into own functions for readability
    BottomSheetScaffold(
        sheetPeekHeight = peekHeight.dp,
        content = {
            LocalityMap(
                localities = localities,
                onMarkerClick = { loc: Locality ->
                    selectedLocality = loc
                    peekHeight = selectedPeekHeight
                    true
                },
                onMapClick = {
                    coroutineScope.launch {
                        sheetState.bottomSheetState.collapse()
                        peekHeight = initialPeekHeight
                    }
                }
            )
        },
        sheetContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ToggleArrowButton(
                    onClick = {
                        coroutineScope.launch {
                            if (sheetState.bottomSheetState.isCollapsed) {
                                sheetState.bottomSheetState.expand()
                            } else {
                                sheetState.bottomSheetState.collapse()
                            }
                        }
                    },
                    isExpanded = sheetState.bottomSheetState.isExpanded
                )
                LocalityInfoBox(selectedLocality)
            }
        },
        scaffoldState = sheetState
    )
}

@Composable
fun LocalityInfoBox(
    locality: Locality?,
) {
    if (locality != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Text("Lokasjon: ${locality.name}")
        }
    }
}

@Composable
fun ToggleArrowButton(
    onClick: () -> Unit,
    isExpanded: Boolean
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (isExpanded) Icons.Filled.ExpandMore else Icons.Filled.ExpandLess,
            contentDescription = if (isExpanded) {
                stringResource(R.string.show_less)
            } else {
                stringResource(R.string.show_more)
            }
        )
    }
}

