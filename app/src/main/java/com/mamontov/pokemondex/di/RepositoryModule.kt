package com.mamontov.pokemondex.di

import com.mamontov.pokemondex.data.repository.PokemonRepositoryImpl
import com.mamontov.pokemondex.domain.PokemonRepository
import org.koin.dsl.module

val RepositoryModule = module {

    single<PokemonRepository> { PokemonRepositoryImpl(get(), get()) }
}