package br.com.spectacle.app.core.ds.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.spectacle.app.core.ds.arch.action.UiAction
import br.com.spectacle.app.core.ds.arch.state.UiState
import kotlinx.coroutines.launch

abstract class ViewModel<State: UiState, Action: UiAction>(
    initialState: State
) : ViewModel() {
    private val viewModelState = br.com.spectacle.app.core.ds.arch.state.State(initialState)
    private val viewModelAction = br.com.spectacle.app.core.ds.arch.action.Action<Action>()

    val state = viewModelState.state
    val action = viewModelAction.action

    protected fun setState(state: (State) -> State){
        viewModelState.setState(state)
    }

    protected fun sendAction(action: () -> Action){
        viewModelScope.launch { viewModelAction.sendAction(action) }
    }
}
