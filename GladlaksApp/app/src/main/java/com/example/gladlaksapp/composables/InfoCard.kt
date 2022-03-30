package com.example.gladlaksapp.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoCard (
    content: @Composable () -> Unit
){
    OutlinedCard (
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        containerColor = Color.White,

            ){
        Box(modifier = Modifier.padding(20.dp)){
            content()
        }
    }
}