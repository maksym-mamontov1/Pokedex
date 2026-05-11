package com.mamontov.pokemondex.ui.screen.pokedex

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mamontov.pokemondex.ui.screen.pokedex.component.PokedexScreenComponent
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokedexScreen() {
    val viewModel = koinViewModel<PokedexViewModel>()
    val state by viewModel.state.collectAsState()

    DisposableEffect(null) {
        viewModel.onScreenStart(state.query)
        onDispose {
        }
    }

    PokedexScreenComponent(
        pokemons = state.pokemons,
        onFavoriteClick = viewModel::onFavoriteClick,
        onSearchChanged = viewModel::onSearchChanged
    )
}