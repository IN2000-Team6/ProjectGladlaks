package com.example.gladlaksapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF01809C),
    onPrimary = Color(0xFFffffff),
    secondary = Color(0xFF9CDCDA),
    onSecondary = Color(0xFF000000),
    tertiary = Color(0xFFFFBDAE),
    onTertiary = Color(0xFF000000),
    error = Color(0xFFba1b1b),
    onError = Color(0xFFffffff),
    background = Color(0xFFfbfdfd),
    onBackground = Color(0xFF294045),
    surface = Color(0xFF95A8A8),
    onSurface = Color(0xFF294045),
    surfaceVariant = Color(0xFF32373A),
    outline = Color(0xFF4B5357)
)

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF71DDFF),
    onPrimary = Color(0xFF003543),
    secondary = Color(0xFF62B3B0),
    onSecondary = Color(0xFF003735),
    tertiary = Color(0xFFffb4a2),
    onTertiary = Color(0xFF5e1608),
    error = Color(0xFFffb4a9),
    onError = Color(0xFF680003),
    background = Color(0xFF294045),
    onBackground = Color(0xFFe0e3e3),
    surface = Color(0xFF1E2D30),
    onSurface = Color(0xFFe0e3e3),
    outline = Color(0xFF899296),
)

/**
 * A theme wrapper composable to provide the theme to all children composables
 */
@Composable
fun GladlaksAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}