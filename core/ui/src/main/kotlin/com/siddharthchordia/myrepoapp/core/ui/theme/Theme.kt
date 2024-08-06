package com.siddharthchordia.myrepoapp.core.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    onPrimary = Color.DarkGray,
    onSecondary = Color.DarkGray,
    onBackground = Color.DarkGray,
    onPrimaryContainer = Color.DarkGray,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color.White,
    surface = Color.White,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    onSecondary = Color.White,
    onTertiary = Color.White,
    surface = Color.White,
    onSurface = Color(0xFF1C1B1F),
     */
)

@Composable
fun MyRepoAppTheme(
    darkTheme: Boolean = false,
    // isSystemInDarkTheme(), Disabled
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.White,
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = StatusBlue,
            // Turned Off
            darkIcons = false,
        )
        systemUiController.setNavigationBarColor(Color.Black)
    }
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
