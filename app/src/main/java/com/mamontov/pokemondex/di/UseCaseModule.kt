package com.mamontov.pokemondex.di

import com.mamontov.pokemondex.domain.usecase.ChangePokemonFavoriteUseCase
import com.mamontov.pokemondex.domain.usecase.InitPokemonsUseCase
import com.mamontov.pokemondex.domain.usecase.ObserveFavoriteCountUseCase
import com.mamontov.pokemondex.domain.usecase.ObservePokemonFavoriteUseCase
import com.mamontov.pokemondex.domain.usecase.ObservePokemonsUseCase
import com.mamontov.pokemondex.domain.usecase.PrefetchImagesUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    factory { ObservePokemonsUseCase(get()) }
    factory { ObservePokemonFavoriteUseCase(get()) }
    factory { ObserveFavoriteCountUseCase(get()) }
    factory { ChangePokemonFavoriteUseCase(get()) }
    factory { InitPokemonsUseCase(get(), get()) }
    factory { PrefetchImagesUseCase(get()) }
}