package com.mamontov.pokemondex.data.repository.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpriteResponse(
    @SerialName("other") val other: PokemonSpriteOtherResponse? = null,
)

@Serializable
data class PokemonSpriteOtherResponse(
    @SerialName("dream_world") val dreamWorld: PokemonDreamWorldResponse? = null,
)

@Serializable
data class PokemonDreamWorldResponse(
    @SerialName("front_default") val avatarUrl: String? = null,
)