package com.mamontov.pokemondex.di

import androidx.room.Room
import com.mamontov.pokemondex.data.database.AppDatabase
import com.mamontov.pokemondex.data.repository.local.PokemonDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import kotlin.jvm.java

val DataBaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "application_database.db"
        ).build()
    }

    single<PokemonDao> { get<AppDatabase>().pokemonDao() }
}