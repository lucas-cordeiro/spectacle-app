package br.com.spectacle.app.feature.login.presentation

import androidx.lifecycle.viewModelScope
import br.com.spectacle.app.core.domain.error.InvalidFieldException
import br.com.spectacle.app.core.ds.arch.ViewModel
import br.com.spectacle.app.core.ds.component.dialog.UiDialog
import br.com.spectacle.app.feature.login.domain.usecase.RegisterWithEmailAndPasswordUseCase
import br.com.spectacle.app.feature.login.domain.usecase.SingInWithEmailAndPasswordUseCase
import br.com.spectacle.app.feature.login.domain.usecase.VerifyEmailAlreadyExistUseCase
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val singInWithEmailAndPasswordUseCase: SingInWithEmailAndPasswordUseCase,
    private val registerWithEmailAndPasswordUseCase: RegisterWithEmailAndPasswordUseCase,
    private val verifyEmailAlreadyExistUseCase: VerifyEmailAlreadyExistUseCase
) : ViewModel<LoginUiState, LoginUiAction>(LoginUiState()) {

    fun clickedConfirmEmail(email: String) {
        verifyEmailAlreadyExist(email)
    }

    private fun verifyEmailAlreadyExist(email: String) {
        viewModelScope.launch {
            try {
                validateEmail(email = email)

                setLoading(true)
                val result = verifyEmailAlreadyExistUseCase(email)

                handleEmailAlreadyExist(result = result, email = email)
            } catch (t: Throwable) {
                handleError(throwable = t)
            } finally {
                setLoading(false)
            }
        }
    }

    private fun validateEmail(email: String) {
        if (!email.contains("@") || !email.contains(".") || email.length < 5) {
            throw InvalidFieldException("E-mail")
        }
    }

    private fun handleEmailAlreadyExist(
        result: Boolean,
        email: String
    ) {
        val destination = if (result) {
            LoginDestination.SING_IN
        } else {
            LoginDestination.REGISTER
        }
        setState { state ->
            state.copy(
                email = email,
                loading = false,
                destination = destination
            )
        }
        sendAction { LoginUiAction.NavigateToPasswordScreen }
    }

    fun clickedConfirmPassword(password: String) {
        singInWithEmailAndPassword(password)
    }

    private fun singInWithEmailAndPassword(password: String) {
        viewModelScope.launch {
            try {
                validatePassword(password)
                setLoading(true)
                if(state.value.destination == LoginDestination.SING_IN){
                    singInWithEmailAndPasswordUseCase(
                        email = state.value.email,
                        password = password
                    )
                }else{
                    registerWithEmailAndPasswordUseCase(
                        email = state.value.email,
                        password = password
                    )
                }
                handleSingInWithEmailAndPassword()
            } catch (t: Throwable) {
                handleError(throwable = t)
            } finally {
                setLoading(false)
            }
        }
    }

    private fun validatePassword(password: String) {
        if (password.length < 5) {
            throw InvalidFieldException("Senha")
        }
    }

    private fun handleSingInWithEmailAndPassword(){
        sendAction { LoginUiAction.FinishScreen }
    }

    fun clickedBackButton() {
        val currentDestination = state.value.destination
        if (currentDestination != LoginDestination.EMAIL) {
            setState { state ->
                state.copy(destination = LoginDestination.EMAIL)
            }
        }
    }

    private fun handleError(throwable: Throwable) {
        when (throwable) {
            is InvalidFieldException -> {
                showErrorDialog(throwable.message)
            }
            else -> {
                throwable.printStackTrace()
                showErrorDialog(message = "Ocorreu um erro inesperado, tente novamente")
            }
        }
    }

    fun clickedDismissDialog() {
        setState { state -> state.copy(uiDialog = null) }
    }

    private fun showErrorDialog(
        message: String,
        title: String = "Ops!"
    ) {
        setState { state ->
            state.copy(
                uiDialog = UiDialog(
                    title = title,
                    message = message
                )
            )
        }
    }

    private fun setLoading(value: Boolean) {
        setState { state -> state.copy(loading = value) }
    }
}

