package br.com.spectacle.app.core.ds.arch

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import br.com.spectacle.app.core.ds.arch.action.UiAction
import br.com.spectacle.app.core.ds.arch.state.UiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

inline fun <reified T : UiState, R : UiAction> ComponentActivity.collectActions(
    viewModel: ViewModel<T, R>,
    noinline collector: suspend (R) -> Unit
) {
    viewModel.action
        .onEach(collector)
        .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
        .launchIn(lifecycleScope)
}

inline fun <reified T : UiAction> ComponentActivity.collectActions(
    viewModel: br.com.spectacle.app.core.ds.arch.action.ViewModel<T>,
    noinline collector: suspend (T) -> Unit
) {
    viewModel.action
        .onEach(collector)
        .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
        .launchIn(lifecycleScope)
}

@Composable
inline fun <reified T : UiState, R : UiAction> collectActions(
    viewModel: ViewModel<T, R>,
    noinline collector: suspend (R) -> Unit
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(lifecycle){
        viewModel.action
            .onEach(collector)
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .launchIn(this)
    }
}

@Composable
inline fun <reified T :UiAction> collectActions(
    viewModel: br.com.spectacle.app.core.ds.arch.action.ViewModel<T>,
    noinline collector: suspend (T) -> Unit
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(lifecycle){
        viewModel.action
            .onEach(collector)
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .launchIn(this)
    }
}
