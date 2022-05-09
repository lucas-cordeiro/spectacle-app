package br.com.spectacle.app.feature.musics.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.R
import br.com.spectacle.app.feature.musics.domain.model.Music
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
internal fun QueryMusics(
    visible: Boolean,
    loading: Boolean,
    query: String?,
    musics: List<Music>?,
    updateQuery: (String) -> Unit,
    clickedMusic: (Music) -> Unit,
    modifier: Modifier = Modifier
) {
    if (visible)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            QueryInput(
                initialQuery = query,
                updateQuery = updateQuery,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            MusicsLoading(visible = loading)

            Crossfade(targetState = Pair(musics, !loading)) { state ->
                val (list, listVisible) = state
                when {
                    !list.isNullOrEmpty() && listVisible -> {
                        Musics(
                            musics = list,
                            clickedMusic = clickedMusic,
                        )
                    }
                    listVisible -> {
                        EmptyData()
                    }
                }
            }
        }

}

@Composable
private fun Musics(
    musics: List<Music>,
    clickedMusic: (Music) -> Unit
) {
    LazyColumn {
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
private fun QueryInput(
    initialQuery: String?,
    updateQuery: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember(initialQuery) { mutableStateOf(initialQuery.orEmpty()) }

    LaunchedEffect(query) {
        updateQuery(query)
    }

    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        textStyle = MaterialTheme.typography.h5.copy(
            color = MaterialTheme.colors.onBackground
        ),
        placeholder = {
            Text(
                text = "Busque por música, artisa, álbum...",
                style = MaterialTheme.typography.subtitle2.copy(
                    fontWeight = FontWeight.Normal
                ),
                color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.disabled)
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primaryVariant
        ),
        modifier = modifier
            .fillMaxWidth()
    )
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
                text = "Tente buscar por seu artista, álbum ou música favorita!",
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
