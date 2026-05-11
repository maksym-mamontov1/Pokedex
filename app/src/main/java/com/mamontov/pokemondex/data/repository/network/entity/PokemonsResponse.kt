package com.mamontov.pokemondex.data.repository.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonsResponse(
    @SerialName("count") val count: Int,
    @SerialName("next") val nextUrl: String? = null,
    @SerialName("previous") val previousUrl: String? = null,
    @SerialName("results") val pokemonsUrls: List<PokemonListResponse>
)
