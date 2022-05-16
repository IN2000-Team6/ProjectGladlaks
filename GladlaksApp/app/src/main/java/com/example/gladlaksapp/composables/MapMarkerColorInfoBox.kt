package com.example.gladlaksapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.R

@Composable
fun MapMarkerColorInfoBox() {
    Box(modifier = Modifier
        .padding(10.dp)
        .clip(RoundedCornerShape(10.dp))
    ){
        Box(modifier = Modifier
            .height(40.dp)
            .width(145.dp)
            .align(Alignment.TopEnd)
            .background(Color(0xB4FFFFFF))
            .clip(RoundedCornerShape(15.dp))
        ){
            Column(modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
            ) {
                Row(Modifier.padding(start = 6.dp, top = 3.dp)) {
                    Image(
                        painter = painterResource(R.drawable.ic_white_border_marker_t),
                        contentDescription = stringResource(R.string.turquoise_icon_description),
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = stringResource(R.string.reported_data),
                        style = MaterialTheme.typography.bodySmall)

                }
                Row(Modifier.padding(start = 6.dp, top = 3.dp)) {
                    Image(
                        painter = painterResource(R.drawable.ic_white_border_marker_g),
                        contentDescription = stringResource(R.string.gray_icon_description),
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = stringResource(R.string.not_reported_data),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}