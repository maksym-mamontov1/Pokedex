package com.mamontov.pokemondex.data.database

import androidx.room.TypeConverter
import com.mamontov.pokemondex.domain.entity.PokemonStats
import kotlinx.serialization.json.Json

class RoomConverter {
    private val json = Json

    @TypeConverter
    fun fromStringList(value: List<String>): String = json.encodeToString(value)

    @TypeConverter
    fun toStringList(value: String): List<String> = json.decodeFromString(value)

    @TypeConverter
    fun fromStats(value: PokemonStats): String = json.encodeToString(value)

    @TypeConverter
    fun toStats(value: String): PokemonStats = json.decodeFromString(value)
}