package com.mamontov.pokemondex.data.repository.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PokemonTypeResponse(
    @SerialName("name") val name: String = "",
) {
}

@Serializable
data class PokemonTypeItem(
    @SerialName("type") val type: PokemonTypeResponse? = null
)
