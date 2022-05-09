package br.com.spectacle.app.feature.login.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.component.button.RoundedButton
import br.com.spectacle.app.core.ds.theme.textField
import com.google.accompanist.insets.imePadding

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun LoginInputScreen(
    input: String,
    label: String,
    description: String,
    keyboardOptions: KeyboardOptions,
    clickedConfirmInput: (input: String) -> Unit,
    loading: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 32.dp
            )
    ) {

        var inputTextField by remember(input) { mutableStateOf(input) }
        val softwareKeyboardController = LocalSoftwareKeyboardController.current

        Column {
            TextField(
                value = inputTextField,
                onValueChange = { inputTextField = it },
                keyboardOptions = keyboardOptions,
                keyboardActions = KeyboardActions {
                    clickedConfirmInput(inputTextField)
                    softwareKeyboardController?.hide()
                },
                visualTransformation = visualTransformation,
                textStyle = MaterialTheme.typography.textField(),
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.subtitle1
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = description,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        RoundedButton(
            onClick = { clickedConfirmInput(inputTextField) },
            loading = loading,
            enabled = inputTextField.isNotBlank() && !loading,
            modifier = Modifier.imePadding()
        ) {
            Text(
                text = "Próximo",
                style  = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}