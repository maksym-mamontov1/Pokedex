package com.mamontov.pokemondex.di

import com.mamontov.pokemondex.data.repository.local.mapper.PokemonLocalToDomainMapper
import com.mamontov.pokemondex.data.repository.network.mapper.PokemonStatsNetworkToDomainMapper
import com.mamontov.pokemondex.data.repository.network.mapper.PokemonsNetworkToDomainMapper
import org.koin.dsl.module

val MapperModule = module {

    //Network
    factory { PokemonsNetworkToDomainMapper(get()) }
    factory { PokemonStatsNetworkToDomainMapper() }

    //Local
    factory { PokemonLocalToDomainMapper() }
}