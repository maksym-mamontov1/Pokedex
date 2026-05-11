package com.mamontov.pokemondex.data.repository.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonAbilitiesResponse(
    @SerialName("ability") val ability: PokemonAbilityResponse,
)

@Serializable
data class PokemonAbilityResponse(
    @SerialName("name") val name: String
)
