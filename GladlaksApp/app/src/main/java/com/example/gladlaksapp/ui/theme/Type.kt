package com.example.gladlaksapp.ui.theme

//import androidx.compose.material.Typography
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.gladlaksapp.R

val SoraFont = FontFamily(
    fonts = listOf(
        Font(resId = R.font.sora_light, weight = FontWeight.Light),
        Font(resId = R.font.sora_regular, weight = FontWeight.Normal),
        Font(resId = R.font.sora_medium, weight = FontWeight.Medium),
        Font(resId = R.font.sora_semibold, weight = FontWeight.SemiBold),
    )
)

// Material typography styles
val Typography = Typography(
    //defaultFontFamily = SoraFont,
    titleMedium = TextStyle(
        fontFamily = SoraFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        letterSpacing = 0.1.sp
    ),
    labelLarge = TextStyle(
        fontFamily = SoraFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    )
)