package com.mamontov.pokemondex.ui.screen.main

import androidx.lifecycle.viewModelScope
import com.mamontov.pokemondex.domain.usecase.InitPokemonsUseCase
import com.mamontov.pokemondex.domain.usecase.ObserveFavoriteCountUseCase
import com.mamontov.pokemondex.ui.base.BaseViewModel
import com.mamontov.pokemondex.ui.screen.main.state.MainState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val initPokemonsUseCase: InitPokemonsUseCase,
    private val observeFavoriteCountUseCase: ObserveFavoriteCountUseCase,
) : BaseViewModel<MainState>(
    initialState = MainState()
) {

    fun onScreenStart() {
        viewModelScope.launch {
            initPokemonsUseCase()
        }
        observeFavoriteCountUseCase()
            .onEach { count ->
                changeState { copy(favoriteCount = count) }
            }
            .launchIn(viewModelScope)
    }
}