package br.com.spectacle.app.presentation.splash.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.R

@Composable
fun AnimateSplashButtons(
    visible: Boolean,
    clickedMovieButton: () -> Unit,
    clickedMusicButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier
    ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                val movieColor = MaterialTheme.colors.primary
                val musicColors = MaterialTheme.colors.primaryVariant


                CardButton(
                    color = movieColor,
                    icon = ImageVector.vectorResource(id = R.drawable.ic_movies),
                    title = "Filmes",
                    subTitle = "Explore novos mundos",
                    onClick = clickedMovieButton,
                    modifier = Modifier
                        .weight(1f)
                )

                CardButton(
                    color = musicColors,
                    icon = ImageVector.vectorResource(id = R.drawable.ic_musics),
                    title = "Músicas",
                    subTitle = "Sinta sempre uma nova emoção",
                    onClick = clickedMusicButton,
                    modifier = Modifier
                        .weight(1f)
                )

        }
    }
}

@Composable
private fun CardButton(
    color: Color,
    icon: ImageVector,
    title: String,
    subTitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cardBackgroundColor = MaterialTheme.colors.background.copy(alpha = 0.6f)

    val shape = RoundedCornerShape(16.dp)
    Box(
        modifier = modifier
            .padding(32.dp)
            .clip(shape)
            .fillMaxWidth()
            .background(cardBackgroundColor)
            .border(
                shape = shape,
                width = 1.dp,
                color = color,
            )
            .clickable(onClick = onClick),
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = subTitle,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onPrimary.copy(alpha = ContentAlpha.medium)
            )
        }
    }
}