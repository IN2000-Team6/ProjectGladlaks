package com.example.gladlaksapp.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.R

@Composable
fun NetworkNotice() {
    val image: Painter = painterResource(R.drawable.ic_error_alert_outline_red)

    Box(modifier = Modifier.fillMaxWidth()){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(painter = image,
            contentDescription = "error icon",
            modifier = Modifier.padding(start = 20.dp)
            )
            Column(modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            ) {
                Text(
                    text = stringResource(R.string.network_notice_text),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 320)
fun PreviewNetworkNotice() {
    NetworkNotice()
}

