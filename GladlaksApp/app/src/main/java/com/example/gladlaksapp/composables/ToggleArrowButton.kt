package com.example.gladlaksapp.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.gladlaksapp.R

@Composable
fun ToggleArrowButton(
    onClick: () -> Unit,
    isExpanded: Boolean
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (isExpanded) Icons.Filled.ExpandMore else Icons.Filled.ExpandLess,
            contentDescription = if (isExpanded) {
                stringResource(R.string.show_less)
            } else {
                stringResource(R.string.show_more)
            }
        )
    }
}