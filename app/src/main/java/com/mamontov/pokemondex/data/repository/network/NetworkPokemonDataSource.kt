package com.mamontov.pokemondex.data.repository.network

import com.mamontov.pokemondex.data.repository.network.entity.PokemonResponse
import com.mamontov.pokemondex.data.repository.network.entity.PokemonsResponse
import com.mamontov.pokemondex.data.repository.network.mapper.PokemonsNetworkToDomainMapper
import com.mamontov.pokemondex.domain.entity.Pokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.collections.mapNotNull

class NetworkPokemonDataSource(
    private val pokemonApi: PokemonApi,
    private val mapper: PokemonsNetworkToDomainMapper,
) {

    suspend fun getPokemons(): List<Pokemon> = coroutineScope {
        pokemonApi.getPokemons()
            ?.pokemonsUrls
            ?.map {
                async { getPokemon(it.name) }
            }?.awaitAll()
            ?.mapNotNull {
                it?.let(mapper::toDomain)
            } ?: emptyList()
    }

    private suspend fun getPokemon(pokemonName: String): PokemonResponse? = pokemonApi.getPokemon(pokemonName)
}