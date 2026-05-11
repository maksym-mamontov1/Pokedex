package com.mamontov.pokemondex.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class PokemonStats(
    val health: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefence: Int,
    val speed: Int,
) {
    val total: Int
        get() = health + attack + defense + specialAttack + specialDefence + speed

    companion object {
        const val MAX_STAT = 300
    }
}