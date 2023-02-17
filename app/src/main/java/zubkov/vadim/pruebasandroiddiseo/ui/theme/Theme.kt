package zubkov.vadim.pruebasandroiddiseo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = colorPrincipal,
    primaryVariant = colorBordes,
    secondary = colorBotones,
    background = colorFondo,
    surface = colorTitulo
)

private val LightColorPalette = lightColors(
    primary = colorPrincipal,
    primaryVariant = colorBordes,
    secondary = colorBotones,
    background = colorFondo

    /* Other default colors to override


    secondaryVariant: Color,

    surface: Color,
    error: Color,
    onPrimary: Color,
    onSecondary: Color,
    onBackground: Color,
    onSurface: Color,
    onError: Color
    */
)

@Composable
fun PruebasAndroidDiseñoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}