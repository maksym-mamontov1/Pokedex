package com.mamontov.pokemondex.data.repository.network.mapper

import com.mamontov.pokemondex.data.repository.network.entity.PokemonStatsResponse
import com.mamontov.pokemondex.domain.entity.PokemonStats

class PokemonStatsNetworkToDomainMapper {

    fun toDomain(pokemonStats: List<PokemonStatsResponse>) = PokemonStats(
        health = pokemonStats.find { it.stat?.name == "hp" }?.baseStat ?: 0,
        attack = pokemonStats.find { it.stat?.name == "attack" }?.baseStat ?: 0,
        defense = pokemonStats.find { it.stat?.name == "defense" }?.baseStat ?: 0,
        specialAttack = pokemonStats.find { it.stat?.name == "special-attack" }?.baseStat ?: 0,
        specialDefence = pokemonStats.find { it.stat?.name == "special-defense" }?.baseStat ?: 0,
        speed = pokemonStats.find { it.stat?.name == "speed" }?.baseStat ?: 0,
    )
}