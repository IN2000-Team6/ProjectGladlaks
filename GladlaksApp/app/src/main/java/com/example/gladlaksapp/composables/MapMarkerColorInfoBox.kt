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
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.R

@Composable
fun MapMarkerColorInfoBox() {
    Box(modifier = Modifier
        .padding(10.dp)
        .clip(RoundedCornerShape(15.dp))
    ){
        Box(modifier = Modifier
            .height(40.dp)
            .width(150.dp)
            .align(Alignment.TopEnd)
            .background(Color(0xB4FFFFFF))
            .clip(RoundedCornerShape(15.dp))
        ){
            Column(modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
            ) {
                Row(Modifier.padding(start = 10.dp, top = 3.dp)) {
                    Image(
                        painter = painterResource(R.drawable.ic_white_border_marker_t),
                        contentDescription = "Turqoise Icon",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = " = Rapportert data",
                        style = MaterialTheme.typography.bodySmall)

                }
                Row(Modifier.padding(start = 10.dp, top = 3.dp)) {
                    Image(
                        painter = painterResource(R.drawable.ic_white_border_marker_g),
                        contentDescription = "Gray Icon",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = " = Ikke rapportert data",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}