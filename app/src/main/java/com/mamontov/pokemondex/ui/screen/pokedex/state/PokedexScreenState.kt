package com.mamontov.pokemondex.ui.screen.pokedex.state

import com.mamontov.pokemondex.domain.entity.Pokemon

data class PokedexScreenState(
    val pokemons: List<Pokemon> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = true,
)