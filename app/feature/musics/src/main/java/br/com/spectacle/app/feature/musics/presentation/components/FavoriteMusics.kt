package br.com.spectacle.app.feature.musics.presentation.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.R
import br.com.spectacle.app.feature.musics.domain.model.Music
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
internal fun FavoriteMusics(
    visible: Boolean,
    musics: List<Music>,
    clickedMusic: (Music) -> Unit,
    modifier: Modifier = Modifier
) {
    if (visible)
        Crossfade(
            targetState = musics,
            modifier = modifier
        ) { list ->
            when {
                list.isNotEmpty() -> {
                    Content(
                        musics = list,
                        clickedMusic = clickedMusic
                    )
                }
                else -> {
                    EmptyData()
                }
            }
        }

}

@Composable
private fun Content(
    musics: List<Music>,
    clickedMusic: (Music) -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            PlayListCard(
                music = musics.first(),
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }

        items(musics) { music ->
            MusicCard(
                music = music,
                onClick = { clickedMusic(music) },
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}

@Composable
private fun PlayListCard(
    music: Music,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        SubcomposeAsyncImage(
            model = music.image,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(200.dp)
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

        Text(
            text = "Minha Playlist",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
private fun EmptyData() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.music_empty))
            val progress by animateLottieCompositionAsState(
                composition,
                iterations = LottieConstants.IterateForever
            )
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.size(200.dp)
            )

            Text(
                text = "Você ainda não adicionou nenhuma música à sua Playlist",
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp)
            )
        }
    }
}