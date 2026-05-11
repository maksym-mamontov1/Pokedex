package com.mamontov.pokemondex.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.mamontov.pokemondex.ui.theme.color.DarkColorScheme
import com.mamontov.pokemondex.ui.theme.color.DarkPokemonTypeColorScheme
import com.mamontov.pokemondex.ui.theme.color.LightColorScheme
import com.mamontov.pokemondex.ui.theme.color.LightPokemonTypeColorScheme
import com.mamontov.pokemondex.ui.theme.color.LocalPokemonTypeColorScheme


@Composable
fun PokemonDexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        val context = LocalContext.current
        SideEffect {
            (context as? Activity)?.window?.let { window ->
                WindowCompat.setDecorFitsSystemWindows(window, false)
                WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
                WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars = true
            }
        }
    }

    val pokemonTypeColorScheme = LightPokemonTypeColorScheme

    CompositionLocalProvider(
        LocalPokemonTypeColorScheme provides pokemonTypeColorScheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}