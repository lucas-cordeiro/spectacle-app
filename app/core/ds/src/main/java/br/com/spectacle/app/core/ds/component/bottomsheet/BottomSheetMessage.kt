package br.com.spectacle.app.core.ds.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.component.button.BasicTextButton
import br.com.spectacle.app.core.ds.component.button.RoundedButton

@Composable
fun BottomSheetMessage(
    message: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    errorCode: Int? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(color = MaterialTheme.colors.surface)
            .padding(horizontal = 16.dp).clickable(onClick = {}, interactionSource = MutableInteractionSource(), indication = null)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface.copy(
                    alpha = 0.9f
                )
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp)
        )
        if (errorCode != null)
            Text(
                text = "Código: #${errorCode.toString().padStart(4, '0')}",
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.onSurface.copy(
                        alpha = 0.7f
                    )
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = 16.dp)
            )

        Spacer(modifier = Modifier.height(70.dp))

        RoundedButton(
            text = { BasicTextButton(text = "Entendi!") },
            onClick = onClick,
            shapeCorner = 8.dp,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        )
    }
}
