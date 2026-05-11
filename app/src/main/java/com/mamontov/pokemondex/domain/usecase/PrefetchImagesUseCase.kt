package com.mamontov.pokemondex.domain.usecase

import android.content.Context
import coil3.SingletonImageLoader
import coil3.request.ImageRequest
import com.mamontov.pokemondex.domain.entity.Pokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class PrefetchImagesUseCase(
    private val context: Context
) {
    suspend operator fun invoke(pokemons: List<Pokemon>) = coroutineScope {
        val imageLoader = SingletonImageLoader.get(context)
        pokemons.map { pokemon ->
            async {
                if (pokemon.avatarUrl.isNotEmpty()) {
                    val request = ImageRequest.Builder(context)
                        .data(pokemon.avatarUrl)
                        .build()
                    imageLoader.enqueue(request)
                }
            }
        }.awaitAll()
    }
}