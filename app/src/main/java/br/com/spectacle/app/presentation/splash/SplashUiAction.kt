package br.com.spectacle.app.presentation.splash

import br.com.spectacle.app.core.ds.arch.action.UiAction

sealed class SplashUiAction : UiAction {
    object NavigateToLogin: SplashUiAction()
    object NavigateToMovies: SplashUiAction()
    object NavigateToMusics: SplashUiAction()
}