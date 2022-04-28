package com.example.gladlaksapp.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun FavoriteButton(
    savedToFav : Boolean,
    onCheckedFavButton: (Boolean) -> Unit
){
   //val savedToFav = remember { mutableStateOf(false) }

    IconToggleButton(
        checked = savedToFav,
        onCheckedChange = onCheckedFavButton,
        //interactionSource = remember { DisabledInteractionSource() },
        enabled = false,
        modifier = Modifier
            .padding(end = 5.dp)
            .size(width = 40.dp, height = 40.dp)
    ) {
        val tint by animateColorAsState(
            if (savedToFav) Color(0xFFEC407A)
            else Color(0xFFB0BEC5)
        )
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Hjerteformet knapp",
            tint = tint)
    }
}

class DisabledInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}