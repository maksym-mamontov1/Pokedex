package com.mamontov.pokemondex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mamontov.pokemondex.data.repository.local.PokemonDao
import com.mamontov.pokemondex.data.repository.local.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = Version.V_1,
    exportSchema = false
)
@TypeConverters(RoomConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}