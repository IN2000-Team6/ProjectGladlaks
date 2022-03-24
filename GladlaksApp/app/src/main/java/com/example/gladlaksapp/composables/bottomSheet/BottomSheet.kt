package com.example.gladlaksapp.composables.bottomSheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.models.Locality
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun BottomSheetStateHolder() {
    val coroutineScope = rememberCoroutineScope()
    val initialPeekHeight = 0
    val selectedPeekHeight = 60

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
            GoogleMapTest(
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
            Column {
                Button(onClick = {
                    coroutineScope.launch {
                        if (sheetState.bottomSheetState.isCollapsed) {
                            sheetState.bottomSheetState.expand()
                        } else {
                            sheetState.bottomSheetState.collapse()
                        }
                    }
                }) {
                    if (sheetState.bottomSheetState.isCollapsed) {
                        Text("Åpne")
                    } else {
                        Text("Lukk")
                    }
                }
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
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) {
            Text("Lokasjon: ${locality.name}")
        }
    }
}

@Composable
fun GoogleMapTest(
    onMarkerClick: (Locality) -> Unit,
    onMapClick: () -> Unit,
) {
    val loc1 = Locality(
        localityNo = 0,
        name = "Narvik",
        lat = 0.43,
        lon = 0.43,
        isOnLand = false,
    )

    val loc2 = Locality(
        localityNo = 1,
        name = "Hammerfest",
        lat = 0.43,
        lon = 0.43,
        isOnLand = false,
    )

    Column {
        Button(onClick = { onMarkerClick(loc1) }) {
            Text(text = "Locality 1")
        }
        Button(onClick = { onMarkerClick(loc2) }) {
            Text(text = "Locality 2")
        }
        Button(onClick = onMapClick) {
            Text("Map click")
        }
    }
}