package br.com.spectacle.app.core.ds.component.button

import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun BasicTextButton(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colors.onPrimary,
){
    Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
            color = color,
            modifier = modifier
    )
}