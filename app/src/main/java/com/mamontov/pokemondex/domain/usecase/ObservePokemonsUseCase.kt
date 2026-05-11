package com.mamontov.pokemondex.domain.usecase

import com.mamontov.pokemondex.domain.PokemonRepository
import com.mamontov.pokemondex.domain.entity.Pokemon
import kotlinx.coroutines.flow.Flow

class ObservePokemonsUseCase(
    private val pokemonRepository: PokemonRepository,
) {

    operator fun invoke(query: String = ""): Flow<List<Pokemon>> = pokemonRepository.observePokemons(query)
}