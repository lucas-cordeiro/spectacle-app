package br.com.spectacle.app.core.ds.arch.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class State<State : UiState>(initialState: State) {
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state

    fun setState(state: (State) -> State){
        _state.value = state(_state.value)
    }
}