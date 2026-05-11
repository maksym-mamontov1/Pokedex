package com.mamontov.pokemondex.ui.screen.pokedex

import androidx.lifecycle.viewModelScope
import com.mamontov.pokemondex.domain.entity.Pokemon
import com.mamontov.pokemondex.domain.usecase.ChangePokemonFavoriteUseCase
import com.mamontov.pokemondex.domain.usecase.ObservePokemonsUseCase
import com.mamontov.pokemondex.ui.base.BaseViewModel
import com.mamontov.pokemondex.ui.screen.pokedex.state.PokedexScreenState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PokedexViewModel(
    private val observePokemonsUseCase: ObservePokemonsUseCase,
    private val changePokemonFavoriteUseCase: ChangePokemonFavoriteUseCase,
) : BaseViewModel<PokedexScreenState>(
    PokedexScreenState(),
) {
    private var observeJob: Job? = null

    fun onScreenStart(query: String = "") {
        observePokemons(query)
    }

    private fun observePokemons(query: String = "") {
        observeJob?.cancel()
        changeState { copy(query = query) }
        observeJob = observePokemonsUseCase(query)
            .onEach {
                changeState {
                    copy(pokemons = it)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onFavoriteClick(pokemon: Pokemon) {
        viewModelScope.launch { changePokemonFavoriteUseCase(pokemon.id) }
    }

    fun onSearchChanged(query: String) {
        observePokemons(query)
    }

    fun onScreenClose() {
        // none
    }
}