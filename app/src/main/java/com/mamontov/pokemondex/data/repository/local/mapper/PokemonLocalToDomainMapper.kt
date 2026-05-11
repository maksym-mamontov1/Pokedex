package com.mamontov.pokemondex.data.repository.local.mapper

import com.mamontov.pokemondex.data.repository.local.entity.PokemonEntity
import com.mamontov.pokemondex.domain.entity.Pokemon

class PokemonLocalToDomainMapper {

    fun toLocal(pokemon: Pokemon): PokemonEntity = pokemon.run {
        PokemonEntity(
            id = id,
            name = name,
            avatarUrl = avatarUrl,
            types = types,
            abilities = abilities,
            pokemonStats = pokemonStats,
            isFavorite = isFavorite,
        )
    }

    fun toDomain(pokemonEntity: PokemonEntity): Pokemon = pokemonEntity.run {
        Pokemon(
            id = id,
            name = name,
            avatarUrl = avatarUrl,
            types = types,
            abilities = abilities,
            pokemonStats = pokemonStats,
            isFavorite = isFavorite,
        )
    }
}