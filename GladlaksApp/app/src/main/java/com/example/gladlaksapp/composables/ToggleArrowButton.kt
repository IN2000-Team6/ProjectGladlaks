package com.example.gladlaksapp.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.R


@Composable
fun ToggleArrowButton(
    onClick: () -> Unit,
    isExpanded: Boolean
) {
    IconButton(onClick = onClick, modifier = Modifier.size(24.dp)) {
        Icon(
            imageVector = if (isExpanded) Icons.Filled.ExpandMore else Icons.Filled.ExpandLess,
            contentDescription = if (isExpanded) {
                stringResource(R.string.show_less)
            } else {
                stringResource(R.string.show_more)
            },
            tint = MaterialTheme.colors.secondary

        )
    }
}