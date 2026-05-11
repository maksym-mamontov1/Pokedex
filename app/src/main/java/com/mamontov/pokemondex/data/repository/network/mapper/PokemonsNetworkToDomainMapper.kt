package com.mamontov.pokemondex.data.repository.network.mapper

import com.mamontov.pokemondex.data.repository.network.entity.PokemonResponse
import com.mamontov.pokemondex.domain.entity.Pokemon

class PokemonsNetworkToDomainMapper(
    private val statsMapper: PokemonStatsNetworkToDomainMapper,
) {

    fun toDomain(pokemonResponse: PokemonResponse): Pokemon = pokemonResponse.run {
        Pokemon(
            id = id,
            name = name.replaceFirstChar { it.uppercase() },
            avatarUrl = sprites?.other?.dreamWorld?.avatarUrl ?: "",
            types = types.mapNotNull { it.type?.name?.replaceFirstChar { char -> char.uppercase() } },
            abilities = abilities.map { it.ability.name.replaceFirstChar { char -> char.uppercase() } },
            pokemonStats = pokemonStats.run(statsMapper::toDomain),
            isFavorite = false,
        )
    }
}