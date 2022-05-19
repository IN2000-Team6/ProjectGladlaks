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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.R

@Composable
fun FavoriteLocalitySnippet(
    locality: Locality?,
    isCollapsed: Boolean,
    onExpandClick: (Locality) -> Boolean,
    toggleFavorite: () -> Unit,
    favButtonTint: Color,
) {
    val image: Painter = painterResource(R.drawable.locality_icon)

    if (locality != null) {
        Box(modifier = Modifier.fillMaxWidth()){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(painter = image,
                    contentDescription = stringResource(R.string.icon_content_description),
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
                        text = "${R.string.locality_number_prefix} ${locality.localityNo}",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(width = 130.dp, height = 40.dp),
                    onClick = {
                            onExpandClick(locality)
                            true},
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                ) {
                    Text(
                        modifier = Modifier.offset(y = (-1).dp),
                        style = MaterialTheme.typography.labelLarge,
                        text = stringResource(R.string.see_more)
                    )
                }
            }

        }
    }
}
