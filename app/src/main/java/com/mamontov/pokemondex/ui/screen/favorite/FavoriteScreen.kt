package com.mamontov.pokemondex.ui.screen.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mamontov.pokemondex.ui.screen.favorite.component.FavoriteScreenComponent
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen() {
    val viewModel = koinViewModel<FavoriteViewModel>()
    val state by viewModel.state.collectAsState()

    DisposableEffect(Unit) {
        viewModel.onScreenStart(state.query)
        onDispose { }
    }

    FavoriteScreenComponent(
        pokemons = state.pokemons,
        onFavoriteClick = viewModel::onFavoriteClick,
        onSearchChanged = viewModel::onSearchChanged,
    )
}