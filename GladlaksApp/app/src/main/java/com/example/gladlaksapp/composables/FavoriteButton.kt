package com.example.gladlaksapp.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
){
    val savedToFav = remember { mutableStateOf(false) }

    IconToggleButton(
        checked = savedToFav.value,
        onCheckedChange = {savedToFav.value = it},
        modifier = Modifier
            .padding(end = 5.dp)
            .size(width = 40.dp, height = 40.dp)
    ) {
        val tint by animateColorAsState(
            if (savedToFav.value) Color(0xFFEC407A)
            else Color(0xFFB0BEC5)
        )
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Hjerteformet knapp",
            tint = tint)
    }
}


/*val liked = remember { mutableStateOf(true) }

IconToggleButton(
    checked = liked.value,
    onCheckedChange = { liked.value = it }
) {
    val tint by animateColorAsState(
        if (liked.value) Color(0xFF7BB661)
        else Color.LightGray
    )
    Icon(
        Icons.Filled.Favorite,
        contentDescription = "Localized description",
        tint = tint
    )

}*/