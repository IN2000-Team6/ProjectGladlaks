package com.example.gladlaksapp.composables.bottomSheet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun mapsView(){
    val sheetState = rememberBottomSheetScaffoldState(
        //Maybe change from remember
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
        //TODO: Skitcontent
            LazyColumn(content = )
        },
        sheetPeekHeight = 0.dp

    ) {
        //TODO: Add reference to kart
    }
}

