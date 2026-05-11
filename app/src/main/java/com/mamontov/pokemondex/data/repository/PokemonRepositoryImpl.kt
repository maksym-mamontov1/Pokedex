package com.mamontov.pokemondex.data.repository

import com.mamontov.pokemondex.data.repository.local.LocalPokemonDataSource
import com.mamontov.pokemondex.data.repository.network.NetworkPokemonDataSource
import com.mamontov.pokemondex.domain.PokemonRepository
import com.mamontov.pokemondex.domain.entity.Pokemon
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(
    val networkDataSource: NetworkPokemonDataSource,
    val localDataSource: LocalPokemonDataSource,
) : PokemonRepository {

    override fun observePokemons(query: String): Flow<List<Pokemon>> = localDataSource
        .observePokemons(query)

    override suspend fun getPokemons(): List<Pokemon> {
        val networkPokemons = networkDataSource.getPokemons()
        localDataSource.addPokemons(networkPokemons)
        return networkPokemons
    }

    override suspend fun changePokemonFavorite(pokemonId: Int): Boolean {
        val pokemon = localDataSource.getPokemon(pokemonId)
        localDataSource.replacePokemon(pokemon.copy(isFavorite = !pokemon.isFavorite))
        return true
    }

    override fun observeFavoritePokemons(query: String): Flow<List<Pokemon>> = localDataSource
        .observeFavoritePokemon(query)

    override fun observeFavoriteCount(): Flow<Int> = localDataSource
        .observeFavoriteCount()
}