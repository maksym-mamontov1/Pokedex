package com.mamontov.pokemondex.data.repository.local

import com.mamontov.pokemondex.data.repository.local.mapper.PokemonLocalToDomainMapper
import com.mamontov.pokemondex.domain.entity.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalPokemonDataSource(
    private val pokemonDao: PokemonDao,
    private val mapper: PokemonLocalToDomainMapper,
) {

    fun observePokemons(query: String): Flow<List<Pokemon>> = pokemonDao.observePokemons(query)
        .map { it.map(mapper::toDomain) }

    fun observeFavoritePokemon(query: String): Flow<List<Pokemon>> = pokemonDao.observeFavoritePokemons(query)
        .map { it.map(mapper::toDomain) }

    suspend fun addPokemons(pokemons: List<Pokemon>) {
        val favoriteIds = pokemonDao.getFavoriteIds()
        pokemons.map {
            mapper.toLocal(it.copy(isFavorite = favoriteIds.contains(it.id)))
        }.let { pokemonDao.addPokemons(it) }
    }

    suspend fun getPokemon(pokemonId: Int): Pokemon = pokemonDao.getPokemon(pokemonId)
        .run(mapper::toDomain)

    suspend fun addPokemon(pokemon: Pokemon) {
        val favoriteIds = pokemonDao.getFavoriteIds()
        pokemonDao.addPokemon(
            mapper.toLocal(pokemon).copy(isFavorite = favoriteIds.contains(pokemon.id))
        )
    }

    fun observeFavoriteCount(): Flow<Int> = pokemonDao.observeFavoriteCount()
}