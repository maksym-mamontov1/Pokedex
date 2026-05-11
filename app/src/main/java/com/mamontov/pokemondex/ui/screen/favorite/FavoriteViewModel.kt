package com.mamontov.pokemondex.ui.screen.favorite

import androidx.lifecycle.viewModelScope
import com.mamontov.pokemondex.domain.entity.Pokemon
import com.mamontov.pokemondex.domain.usecase.ChangePokemonFavoriteUseCase
import com.mamontov.pokemondex.domain.usecase.ObservePokemonFavoriteUseCase
import com.mamontov.pokemondex.ui.base.BaseViewModel
import com.mamontov.pokemondex.ui.screen.favorite.state.FavoriteScreenState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val observePokemonFavoriteUseCase: ObservePokemonFavoriteUseCase,
    private val changePokemonFavoriteUseCase: ChangePokemonFavoriteUseCase,
) : BaseViewModel<FavoriteScreenState>(
    FavoriteScreenState(),
) {
    private var observeJob: Job? = null

    fun onScreenStart(query: String = "") {
        observeFavorites(query)
    }

    private fun observeFavorites(query: String = "") {
        observeJob?.cancel()
        changeState { copy(query = query) }
        observeJob = observePokemonFavoriteUseCase(query)
            .onEach {
                changeState {
                    copy(pokemons = it,)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onFavoriteClick(pokemon: Pokemon) {
        viewModelScope.launch { changePokemonFavoriteUseCase(pokemon.id) }
    }

    fun onSearchChanged(query: String) {
        observeFavorites(query)
    }
}