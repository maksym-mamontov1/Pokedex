package com.mamontov.pokemondex.domain

import com.mamontov.pokemondex.domain.entity.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun observePokemons(query: String = ""): Flow<List<Pokemon>>

    suspend fun getPokemons(): List<Pokemon>

    suspend fun changePokemonFavorite(pokemonId: Int): Boolean

    fun observeFavoritePokemons(query: String = ""): Flow<List<Pokemon>>

    fun observeFavoriteCount(): Flow<Int>
}