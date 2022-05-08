package br.com.spectacle.app.core.ds.arch.state

import androidx.lifecycle.ViewModel

abstract class ViewModel<State: UiState>(
    initialState: State
) : ViewModel() {
    private val viewModelState = State(initialState)

    val state = viewModelState.state

    protected fun setState(state: (State) -> State){
        viewModelState.setState(state)
    }
}
