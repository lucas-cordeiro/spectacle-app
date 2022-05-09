package br.com.spectacle.app.feature.login.presentation

import br.com.spectacle.app.core.ds.arch.state.UiState
import br.com.spectacle.app.core.ds.component.dialog.UiDialog

internal data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val destination: LoginDestination = LoginDestination.EMAIL,
    val uiDialog: UiDialog? = null
) : UiState

enum class LoginDestination {
    EMAIL, SING_IN, REGISTER
}
