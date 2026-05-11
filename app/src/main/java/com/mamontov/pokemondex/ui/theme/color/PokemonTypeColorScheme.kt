package com.mamontov.pokemondex.ui.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class PokemonTypeColors(
    val primary: Color,
    val secondary: Color
)

@Immutable
data class PokemonTypeColorScheme(
    val water: PokemonTypeColors,
    val fire: PokemonTypeColors,
    val grass: PokemonTypeColors,
    val bug: PokemonTypeColors,
    val poison: PokemonTypeColors,
    val default: PokemonTypeColors
)

val LightPokemonTypeColorScheme = PokemonTypeColorScheme(
    water = PokemonTypeColors(WaterPrimaryLight, WaterSecondaryDark),
    fire = PokemonTypeColors(FirePrimaryLight, FireSecondaryDark),
    grass = PokemonTypeColors(GrassPrimaryLight, GrassSecondaryDark),
    bug = PokemonTypeColors(BugPrimaryLight, BugSecondaryDark),
    poison = PokemonTypeColors(PoisonPrimaryLight, PoisonSecondaryDark),
    default = PokemonTypeColors(Color.White, Color.LightGray)
)

val DarkPokemonTypeColorScheme = PokemonTypeColorScheme(
    water = PokemonTypeColors(WaterPrimaryDark, WaterSecondaryDark),
    fire = PokemonTypeColors(FirePrimaryDark, FireSecondaryDark),
    grass = PokemonTypeColors(GrassPrimaryDark, GrassSecondaryDark),
    bug = PokemonTypeColors(BugPrimaryDark, BugSecondaryDark),
    poison = PokemonTypeColors(PoisonPrimaryDark, PoisonSecondaryDark),
    default = PokemonTypeColors(Color.DarkGray, Color.Black)
)

val LocalPokemonTypeColorScheme = staticCompositionLocalOf { LightPokemonTypeColorScheme }

fun PokemonTypeColorScheme.getColorForType(type: String, skipForCard: Boolean = false): PokemonTypeColors {
    return when (type.lowercase()) {
        "water" -> water
        "fire" -> fire
        "grass" -> grass
        "bug" -> bug
        "poison" -> if (skipForCard) default else poison
        else -> default
    }
}
