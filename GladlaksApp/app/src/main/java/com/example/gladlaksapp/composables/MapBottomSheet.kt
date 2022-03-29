package com.example.gladlaksapp.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.datasources.BarentswatchRepository
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun MapBottomSheet(
    localities: List<Locality>?
) {
    val coroutineScope = rememberCoroutineScope()
    val initialPeekHeight = 0
    val selectedPeekHeight = 75
    
    // Local state
    var selectedLocality by rememberSaveable { mutableStateOf<Locality?>(null) }
    var loadedLocality by rememberSaveable { mutableStateOf<LocalityDetailsWrapper?>(null) }
    var peekHeight by rememberSaveable { mutableStateOf(initialPeekHeight) }
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )

    // Repository
    val bwRepository = BarentswatchRepository

    fun onMarkerClick(locality: Locality) {
        selectedLocality = locality
        peekHeight = selectedPeekHeight
    }

    fun onMapClick() {
        coroutineScope.launch {
            sheetState.bottomSheetState.collapse()
            peekHeight = initialPeekHeight
        }
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

    fun loadDetails(localityNo: Int) {
        coroutineScope.launch {
            loadedLocality = bwRepository.getDetailedLocalityInfo(localityNo, 2022, 10) // These values cant be hardcoded
            Log.d("Loaded", loadedLocality!!.localityName)
        }
    }

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(16.dp),
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
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ToggleArrowButton(
                    isExpanded = sheetState.bottomSheetState.isExpanded,
                    onClick = ::toggleBottomSheet,
                )
                LocalitySheetTop(selectedLocality, sheetState.bottomSheetState, coroutineScope)
                LocalitySheetContent(loadedLocality = null)
                /*TODO Place more components here
                   (Locality info, graph ++)
                 */
                Text("poop")
                if (loadedLocality != null) {
                    Text(loadedLocality!!.localityName)
                }
                if (sheetState.bottomSheetState.isExpanded && selectedLocality != null) {
                    loadDetails(selectedLocality!!.localityNo)
                }
            }
        },
    )
}

@Preview
@Composable
fun DisplayMapBottomSheet() {
    MapBottomSheet(localities = emptyList())
}


