package com.example.gladlaksapp.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    // Local state
    var selectedLocality by rememberSaveable { mutableStateOf<Locality?>(null) }
    var peekHeight by rememberSaveable { mutableStateOf(initialPeekHeight) }
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )

    // TODO move onMarkerClick and onToggleSheet into own functions for readability
    BottomSheetScaffold(
        sheetPeekHeight = peekHeight.dp,
        scaffoldState = sheetState,
        content = {
            LocalityMap(
                localities = localities,
                onMarkerClick = { loc: Locality ->
                    selectedLocality = loc
                    peekHeight = selectedPeekHeight
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
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ToggleArrowButton(
                    isExpanded = sheetState.bottomSheetState.isExpanded,
                    onClick = {
                        coroutineScope.launch {
                            if (sheetState.bottomSheetState.isCollapsed) {
                                sheetState.bottomSheetState.expand()
                            } else {
                                sheetState.bottomSheetState.collapse()
                            }
                        }
                    },
                )
                LocalitySheetTop(selectedLocality, sheetState.bottomSheetState, coroutineScope)
            }
        },
    )
}



