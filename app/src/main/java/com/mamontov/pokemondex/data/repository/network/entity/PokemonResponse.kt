package com.mamontov.pokemondex.data.repository.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    @SerialName("id") val id: Int = -1,
    @SerialName("name") val name: String = "",
    @SerialName("sprites") val sprites: PokemonSpriteResponse? = null,
    @SerialName("types") val types: List<PokemonTypeItem> = emptyList(),
    @SerialName("abilities") val abilities: List<PokemonAbilitiesResponse> = emptyList(),
    @SerialName("stats") val pokemonStats: List<PokemonStatsResponse> = emptyList(),
)

@Serializable
data class PokemonStatsResponse(
    @SerialName("base_stat") val baseStat: Int = 0,
    @SerialName("stat") val stat: PokemonStatResponse? = null,
)

@Serializable
data class PokemonStatResponse(
    @SerialName("name") val name: String = "",
)

