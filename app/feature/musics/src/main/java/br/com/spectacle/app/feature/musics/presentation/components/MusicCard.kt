package br.com.spectacle.app.feature.musics.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.feature.musics.domain.model.Music
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
internal fun MusicCard(
    music: Music,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        SubcomposeAsyncImage(
            model = music.image,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(60.dp)
        ) {
            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator(
                        strokeWidth = 1.dp,
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center)
                    )
                }
                else -> SubcomposeAsyncImageContent()
            }
        }

        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = music.title,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground
            )

            Text(
                text = music.artist.name,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Normal
                ),
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}
