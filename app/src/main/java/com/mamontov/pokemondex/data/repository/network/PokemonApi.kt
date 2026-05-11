package com.mamontov.pokemondex.data.repository.network

import com.mamontov.pokemondex.data.repository.network.entity.PokemonResponse
import com.mamontov.pokemondex.data.repository.network.entity.PokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int = 15,
        @Query("offset") offset: Int = 0,
    ): PokemonsResponse?

    @GET("pokemon/{pokemon_name}")
    suspend fun getPokemon(
        @Path("pokemon_name") pokemonName: String,
    ): PokemonResponse?
}
