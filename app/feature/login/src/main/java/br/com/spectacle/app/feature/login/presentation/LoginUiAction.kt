package br.com.spectacle.app.feature.login.presentation

import br.com.spectacle.app.core.ds.arch.action.UiAction

internal sealed class LoginUiAction : UiAction {
    object NavigateToPasswordScreen: LoginUiAction()
    object FinishScreen: LoginUiAction()
}
