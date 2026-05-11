package com.mamontov.pokemondex.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State>(
    private val initialState: State,
) : ViewModel() {

    private val mutableState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State>
        get() = mutableState.asStateFlow()

    protected fun changeState(block: State.() -> State) {
        mutableState.value = block(mutableState.value)
    }
}