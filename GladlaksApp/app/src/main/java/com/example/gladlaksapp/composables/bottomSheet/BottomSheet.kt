package com.example.gladlaksapp.composables.bottomSheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Preview
@Composable
fun MapsScreen() {
    BottomSheetLayout(30)
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetLayout(
    selectedPeekHeight: Int,
    //content: @Composable () -> Unit,
    //sheetContent: @Composable () -> Unit
) {
    val initialPeekHeight = 0

    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
    )

    var peekHeight: Int by rememberSaveable {
        mutableStateOf(initialPeekHeight)
    }

    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(800.dp)
            ) {
                Text(text = "Hello from sheet")
                Button(onClick = {
                    coroutineScope.launch {
                        if (sheetState.bottomSheetState.isCollapsed) {
                            sheetState.bottomSheetState.expand()
                        }else{
                            sheetState.bottomSheetState.collapse()
                        }
                    }
                }) {
                    Text(text = "Up")
                }
                //TODO Sheet content
            }
        }, sheetPeekHeight = peekHeight.dp

    ) {
        Button(onClick = {

            coroutineScope.launch {

                if (sheetState.bottomSheetState.isCollapsed) {
                    if (peekHeight == selectedPeekHeight) {
                        peekHeight = initialPeekHeight
                    } else {
                        peekHeight = selectedPeekHeight
                    }
                } else {
                    peekHeight = initialPeekHeight
                    sheetState.bottomSheetState.collapse()
                }
            }
        }) {
            Text(text = "Expand/Collapse Bottom Sheet")
        }
        //TODO: Add reference to content
    }
}

