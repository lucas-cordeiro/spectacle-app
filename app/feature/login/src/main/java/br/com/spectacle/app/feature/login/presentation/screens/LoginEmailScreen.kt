package br.com.spectacle.app.feature.login.presentation.screens

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun LoginEmailScreen(
    email: String,
    loading: Boolean,
    clickedConfirmEmail: (String) -> Unit
){
    LoginInputScreen(
        input = email,
        label = "E-mail",
        description = "Informe seu e-mail para buscarmos seu cadastro",
        loading = loading,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        clickedConfirmInput = clickedConfirmEmail
    )
}