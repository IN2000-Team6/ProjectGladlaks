package com.example.gladlaksapp.composables

import android.graphics.fonts.Font
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.R
import com.example.gladlaksapp.ui.theme.GladlaksAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocalitySnippet(
    locality: Locality
) {
    // TODO make the snippet composable

    val image: Painter = painterResource(R.drawable.ic_launcher_background)

    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            //.padding(16.dp)
    ) {
        Image(painter = image,
            contentDescription = "IKON",
            modifier = Modifier.padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
        )

        Column(modifier = Modifier
            .padding(16.dp),
            //horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text(
                text = locality.name,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "Lokalitet: ${locality.localityNo}",
                style = MaterialTheme.typography.subtitle1
            )

        }
        Button(
            onClick = {},
            modifier = Modifier
                .background(Color.Blue)
        ) {
            Text("Vis mer")
        }

        /*
        ListItem(text = {
            Text(
                text = locality.name,
                style = MaterialTheme.typography.h6
            )
        }, secondaryText = {
            Text(
                text = "Lokalitet: ${locality.localityNo}",
                style = MaterialTheme.typography.subtitle2
            )
        }
        )
        */

    }
}

@Preview(widthDp = 320, heightDp = 80, showBackground = true)
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