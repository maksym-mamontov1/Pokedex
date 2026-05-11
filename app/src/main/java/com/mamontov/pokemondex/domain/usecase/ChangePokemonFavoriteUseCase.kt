package com.mamontov.pokemondex.domain.usecase

import com.mamontov.pokemondex.domain.PokemonRepository

class ChangePokemonFavoriteUseCase(
    private val pokemonRepository: PokemonRepository,
) {

    suspend operator fun invoke(pokemonId: Int) {
        pokemonRepository.changePokemonFavorite(pokemonId)
    }
}