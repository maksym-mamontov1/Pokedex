package com.mamontov.pokemondex.domain.usecase

import com.mamontov.pokemondex.domain.PokemonRepository

class InitPokemonsUseCase(
    private val pokemonRepository: PokemonRepository,
    private val prefetchImagesUseCase: PrefetchImagesUseCase,
) {

    suspend operator fun invoke() {
        val pokemons = pokemonRepository.getPokemons()
        prefetchImagesUseCase(pokemons)
    }
}