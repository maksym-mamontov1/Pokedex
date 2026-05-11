package com.mamontov.pokemondex.di

import com.mamontov.pokemondex.data.repository.local.LocalPokemonDataSource
import com.mamontov.pokemondex.data.repository.network.NetworkPokemonDataSource
import org.koin.dsl.module

val DataSourceModule = module {
    factory { LocalPokemonDataSource(get(), get()) }
    factory { NetworkPokemonDataSource(get(), get()) }
}