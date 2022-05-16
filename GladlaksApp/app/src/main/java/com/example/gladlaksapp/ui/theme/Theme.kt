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
    //primaryContainer = md_theme_light_primaryContainer,
    //onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = Color(0xFF9CDCDA),
    onSecondary = Color(0xFF000000),
    //secondaryContainer = md_theme_light_secondaryContainer,
    //onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = Color(0xFFFFBDAE),
    onTertiary = Color(0xFF000000),
    //tertiaryContainer = md_theme_light_tertiaryContainer,
    //onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = Color(0xFFba1b1b),
    //errorContainer = md_theme_light_errorContainer,
    onError = Color(0xFFffffff),
    //onErrorContainer = md_theme_light_onErrorContainer,
    background = Color(0xFFfbfdfd),
    onBackground = Color(0xFF294045),
    surface = Color(0xFF95A8A8),
    onSurface = Color(0xFF294045),
    surfaceVariant = Color(0xFF32373A),
    //onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = Color(0xFF4B5357)
    //inverseOnSurface = md_theme_light_inverseOnSurface,
    //inverseSurface = md_theme_light_inverseSurface,
    //inversePrimary = md_theme_light_inversePrimary,
)

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF71DDFF),
    onPrimary = Color(0xFF003543),
    //primaryContainer = md_theme_dark_primaryContainer,
    //onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = Color(0xFF62B3B0),
    onSecondary = Color(0xFF003735),
    //secondaryContainer = md_theme_dark_secondaryContainer,
    //onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = Color(0xFFffb4a2),
    onTertiary = Color(0xFF5e1608),
    //tertiaryContainer = md_theme_dark_tertiaryContainer,
    //onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = Color(0xFFffb4a9),
    //errorContainer = md_theme_dark_errorContainer,
    onError = Color(0xFF680003),
    //onErrorContainer = md_theme_dark_onErrorContainer,
    background = Color(0xFF294045),
    onBackground = Color(0xFFe0e3e3),
    surface = Color(0xFF1E2D30),
    onSurface = Color(0xFFe0e3e3),
    //surfaceVariant = md_theme_dark_surfaceVariant,
    //onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = Color(0xFF899296),
    //inverseOnSurface = md_theme_dark_inverseOnSurface,
    //inverseSurface = md_theme_dark_inverseSurface,
    //inversePrimary = md_theme_dark_inversePrimary,
    //shadow = md_theme_dark_shadow,
)



    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */

@Composable
fun GladlaksAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        //shapes = Shapes,
        content = content
    )

}