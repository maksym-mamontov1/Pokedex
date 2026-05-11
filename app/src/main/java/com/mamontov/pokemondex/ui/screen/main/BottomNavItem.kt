package com.mamontov.pokemondex.ui.screen.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mamontov.pokemondex.R

import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavItem(
    @DrawableRes val iconRes: Int,
    @StringRes val labelRes: Int
) {
    @Serializable
    object Pokedex : BottomNavItem(
        iconRes = R.drawable.ic_pokedex_bottom_navigation,
        labelRes = R.string.nav_pokedex
    )

    @Serializable
    object Wishlist : BottomNavItem(
        iconRes = R.drawable.ic_pokemon_favorite_selected,
        labelRes = R.string.nav_wishlist
    )
}