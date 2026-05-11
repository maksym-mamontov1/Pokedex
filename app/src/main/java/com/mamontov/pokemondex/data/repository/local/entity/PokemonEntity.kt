package com.mamontov.pokemondex.data.repository.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mamontov.pokemondex.domain.entity.PokemonStats

@Entity("pokemon_table")
data class PokemonEntity(
    @ColumnInfo("id") @PrimaryKey val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("avatar_url") val avatarUrl: String,
    @ColumnInfo("types") val types: List<String>,
    @ColumnInfo("abilities") val abilities: List<String>,
    @ColumnInfo("pokemon_stats") val pokemonStats: PokemonStats,
    @ColumnInfo("favorite") val isFavorite: Boolean,
)