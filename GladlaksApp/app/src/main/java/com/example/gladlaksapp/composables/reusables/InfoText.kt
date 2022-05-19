package com.example.gladlaksapp.composables.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.R


/**
 * Composable used for short bodies of texts along with an info icon
 * @param textId the id for the [stringResource] to use as text
 * @param style the [TextStyle] to use with the text
 */
@Composable
fun InfoText(
    textId: Int,
    style: TextStyle
) {
    val image: Painter = painterResource(R.drawable.ic_error_alert_outline)

    Box(modifier = Modifier.fillMaxWidth()){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(top = 15.dp, bottom = 15.dp)
        ) {
            Image(painter = image,
                contentDescription = "NOTIS-IKON",
                modifier = Modifier.padding(start = 10.dp)
            )
            Column(modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f),
            ) {
                Text(
                    text = stringResource(textId),
                    style = style
                )
            }
        }
    }
}