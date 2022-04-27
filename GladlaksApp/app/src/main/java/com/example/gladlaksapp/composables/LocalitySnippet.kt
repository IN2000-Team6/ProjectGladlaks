package com.example.gladlaksapp.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.R

@Composable
fun LocalitySnippet(
    locality: Locality?,
    isCollapsed: Boolean,
    onClick: () -> Unit,
    onFavClick: () -> Unit,
) {
    val image: Painter = painterResource(R.drawable.locality_icon)
    var checked by remember { mutableStateOf(false) } //TODO: check if the locality is a favorite or not


    if (locality != null) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = image,
                    contentDescription = "IKON",
                    modifier = Modifier.padding(start = 20.dp)
                )
                Column(
                    modifier = Modifier
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
                //TODO: Koble til favoritter og database, endre farge om det er fav
                IconToggleButton(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .size(width = 40.dp, height = 40.dp)
                ) {
                    val tint by animateColorAsState(if (checked) Color(0xFFEC407A) else Color(0xFFB0BEC5))
                    Icon(Icons.Filled.Favorite, contentDescription = "Hjerteformet knapp", tint = tint)
                }
                Button(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(width = 130.dp, height = 40.dp),
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
                        modifier = Modifier.offset(y = (-1).dp),
                        style = MaterialTheme.typography.labelLarge,
                        text = if (isCollapsed) "Se mer" else "Se mindre",
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {


            }
        }
    }
}
