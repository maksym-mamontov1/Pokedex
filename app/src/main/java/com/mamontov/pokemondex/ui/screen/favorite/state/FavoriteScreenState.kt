package com.mamontov.pokemondex.ui.screen.favorite.state

import com.mamontov.pokemondex.domain.entity.Pokemon

data class FavoriteScreenState(
    val pokemons: List<Pokemon> = emptyList(),
    val query: String = "",
)