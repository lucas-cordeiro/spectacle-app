package br.com.spectacle.app.feature.movies.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.component.button.BackButton

@Composable
internal fun MoviesToolbar(
    modifier: Modifier = Modifier
){
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Filmes",
            style = MaterialTheme.typography.h2.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}