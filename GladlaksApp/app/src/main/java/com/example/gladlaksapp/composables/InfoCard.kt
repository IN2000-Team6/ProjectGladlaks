package com.example.gladlaksapp.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.models.Locality

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoCard (
    additionalInfo: Locality? = null,
    content: @Composable () -> Unit
){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(3.dp),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(10.dp),
        containerColor = Color.White,

            ){
        Box{
            content()
        }
    }
}