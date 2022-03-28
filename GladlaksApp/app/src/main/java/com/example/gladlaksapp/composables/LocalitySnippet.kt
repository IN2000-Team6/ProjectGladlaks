package com.example.gladlaksapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.R
import com.example.gladlaksapp.ui.theme.ButtonBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocalitySnippet(
    locality: Locality,
    sheetState: BottomSheetState,
    coroutineScope: CoroutineScope
) {
    // TODO make the snippet composable

    val image: Painter = painterResource(R.drawable.locality_icon)

    Box(modifier = Modifier.fillMaxWidth()){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
            //.padding(16.dp)
        ) {
            Image(painter = image,
                contentDescription = "IKON",
                modifier = Modifier.padding(start = 20.dp, top = 5.dp, bottom = 5.dp)
            )

            Column(modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            ) {
                Text(
                    text = locality.name,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Lokalitet: ${locality.localityNo}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(start = 1.dp)
                )

            }
            Button(
                onClick = {
                      coroutineScope.launch {
                          if (sheetState.isCollapsed) {
                              sheetState.expand()
                          } else {
                              sheetState.collapse()
                          }
                      }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(width = 150.dp, height = 40.dp),

            ) {
                Text(
                    text = if (sheetState.isCollapsed) "Se mer" else "Se mindre"
                )
            }
        }

    }
}

/*
@Preview(widthDp = 412, heightDp = 80, showBackground = true)
@Composable
fun DisplayLocalitySnippet() {
    LocalitySnippet(
        Locality(
            localityNo = 1,
            name = "Test",
            lat = 10.3,
            lon = 13.4,
            isOnLand = false,
        )
    )
}
*/