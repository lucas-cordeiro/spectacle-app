package br.com.spectacle.app.core.ds.arch.action

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class ViewModel<Action: UiAction> : ViewModel() {

    private val viewModelAction = Action<Action>()

    val action = viewModelAction.action

    protected fun sendAction(action: () -> Action){
        viewModelScope.launch { viewModelAction.sendAction(action) }
    }
}
