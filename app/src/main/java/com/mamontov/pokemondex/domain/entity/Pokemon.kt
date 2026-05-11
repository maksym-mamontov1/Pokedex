package com.mamontov.pokemondex.domain.entity

data class Pokemon(
    val id: Int,
    val name: String,
    val avatarUrl: String,
    val types: List<String>,
    val abilities: List<String>,
    val pokemonStats: PokemonStats,
    val isFavorite: Boolean,
)
