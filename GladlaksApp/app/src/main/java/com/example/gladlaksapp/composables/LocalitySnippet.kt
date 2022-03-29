package com.example.gladlaksapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.R

@Composable
fun LocalitySnippet(
    locality: Locality?,
    isCollapsed: Boolean,
    onClick: () -> Unit,
) {
    val image: Painter = painterResource(R.drawable.locality_icon)

    if (locality != null) {
        Box(modifier = Modifier.fillMaxWidth()){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(painter = image,
                    contentDescription = "IKON",
                    modifier = Modifier.padding(start = 20.dp)
                )
                Column(modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                ) {
                    Text(
                        text = locality.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Lokalitet: ${locality.localityNo}",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(width = 150.dp, height = 40.dp),
                    onClick = onClick,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF9CDCDA),//MaterialTheme.colorScheme.secondaryContainer
                        contentColor = Color(0xFF303631),//MaterialTheme.colorScheme.onSecondaryContainer,
                        disabledContainerColor = Color(0x1F1F1F1F),
                        disabledContentColor = Color(0xFF191C1D)
                    )
                ) {
                    Text(
                        text = if (isCollapsed) "Se mer" else "Se mindre",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

        }
    }
}