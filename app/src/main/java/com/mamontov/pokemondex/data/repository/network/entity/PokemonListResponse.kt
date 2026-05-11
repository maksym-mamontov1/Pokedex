package com.mamontov.pokemondex.data.repository.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    @SerialName("name")val name: String,
)