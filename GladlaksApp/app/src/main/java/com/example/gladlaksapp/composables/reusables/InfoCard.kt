package com.example.gladlaksapp.composables.reusables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

/**
 * Composable used for showing any content in a styled box
 * @param content the composable you want to display in the box
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoCard(
    content: @Composable () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(3.dp),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Box {
            content()
        }
    }
}