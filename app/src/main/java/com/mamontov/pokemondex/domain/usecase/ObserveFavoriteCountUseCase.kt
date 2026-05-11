package com.mamontov.pokemondex.domain.usecase

import com.mamontov.pokemondex.domain.PokemonRepository
import kotlinx.coroutines.flow.Flow

class ObserveFavoriteCountUseCase(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(): Flow<Int> = pokemonRepository.observeFavoriteCount()
}