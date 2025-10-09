package com.example.courtscout.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.ui.graphics.Color

import com.example.courtscout.presentation.ui.theme.Black
import com.example.courtscout.presentation.ui.theme.BlueAccent
import com.example.courtscout.presentation.ui.theme.BlueAccentDark
import com.example.courtscout.presentation.ui.theme.DarkGray
import com.example.courtscout.presentation.ui.theme.Green
import com.example.courtscout.presentation.ui.theme.Gold
import com.example.courtscout.presentation.ui.theme.LightGray
import com.example.courtscout.presentation.ui.theme.MediumGray
import com.example.courtscout.presentation.ui.theme.OrangeDark
import com.example.courtscout.presentation.ui.theme.OrangeLight
import com.example.courtscout.presentation.ui.theme.OrangePrimary
import com.example.courtscout.presentation.ui.theme.White

import com.example.courtscout.presentation.ui.theme.Typography


private val DarkColorScheme = darkColorScheme(
    primary = OrangeLight,
    onPrimary = Black,
    primaryContainer = OrangeDark,
    onPrimaryContainer = White,
    secondary = BlueAccent,
    onSecondary = Black,
    secondaryContainer = BlueAccentDark,
    onSecondaryContainer = White,
    tertiary = Green,
    onTertiary = Black,
    background = DarkGray,
    onBackground = White,
    surface = DarkGray,
    onSurface = White,
    error = Color.Red,
    onError = Black
)

private val LightColorScheme = lightColorScheme(
    primary = OrangePrimary,
    onPrimary = White,
    primaryContainer = OrangeLight,
    onPrimaryContainer = OrangeDark,
    secondary = BlueAccent,
    onSecondary = White,
    secondaryContainer = BlueAccent,
    onSecondaryContainer = BlueAccentDark,
    tertiary = Green,
    onTertiary = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    error = Color.Red,
    onError = White
)

@Composable
fun CourtScoutTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}