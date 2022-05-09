package br.com.spectacle.app.feature.login.presentation.screens

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import br.com.spectacle.app.feature.login.presentation.LoginDestination

@Composable
internal fun LoginPasswordScreen(
    password: String,
    destination: LoginDestination,
    loading: Boolean,
    clickedConfirmPassword: (String) -> Unit
){
    val description = if(destination == LoginDestination.REGISTER)
        "Informe sua senha para realizar seu cadastro"
    else
        "Informe sua senha para efetuar login"
    LoginInputScreen(
        input = password,
        label = "Senha",
        description = description,
        loading = loading,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = PasswordVisualTransformation(),
        clickedConfirmInput = clickedConfirmPassword
    )
}