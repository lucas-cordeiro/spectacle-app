package br.com.spectacle.app.presentation.splash

import br.com.spectacle.app.core.ds.arch.state.UiState

data class SplashUiState(
    val backgroundState: BackgroundState = BackgroundState.COLLAPSED,
    val isButtonsVisible: Boolean = false,
    val isLoading: Boolean = false
): UiState

enum class BackgroundState {
    COLLAPSED, EXPANDED, FULL_MOVIES, FULL_MUSICS
}
