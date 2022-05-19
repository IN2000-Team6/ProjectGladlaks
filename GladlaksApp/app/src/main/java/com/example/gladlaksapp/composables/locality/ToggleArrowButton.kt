package com.example.gladlaksapp.composables.locality

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.R

/**
 * Arrow button composable.
 * @param onClick lambda function for button onClick
 * @param isExpanded controls the look and content description of the button
 *
 */
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
            tint = MaterialTheme.colorScheme.outline
        )
    }
}