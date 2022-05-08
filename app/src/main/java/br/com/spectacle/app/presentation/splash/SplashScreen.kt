package br.com.spectacle.app.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import br.com.spectacle.app.core.ds.R
import br.com.spectacle.app.core.ds.arch.collectAsStateLifecycleAware
import br.com.spectacle.app.presentation.splash.components.AnimateSplashBackground
import br.com.spectacle.app.presentation.splash.components.AnimateSplashButtons
import br.com.spectacle.app.presentation.splash.components.AnimatedSplashIcon
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun SplashActivity.SplashScreen(){
    val state by viewModel.state.collectAsStateLifecycleAware()

    Box {
        AnimateSplashBackground(
            state = state.backgroundState,
            finishedListener = { viewModel.backgroundAnimationFinished() },
            modifier = Modifier.fillMaxSize()
        )

        AnimateSplashButtons(
            visible = state.isButtonsVisible,
            clickedMovieButton = { viewModel.clickedMoviesButton() },
            clickedMusicButton = { viewModel.clickedMusicsButton() },
            modifier = Modifier.navigationBarsPadding()
        )

        AnimatedSplashIcon(
            visible = state.backgroundState == BackgroundState.FULL_MOVIES,
            icon = ImageVector.vectorResource(id = R.drawable.ic_movies),
            color = MaterialTheme.colors.primary,
            modifier = Modifier.align(Alignment.Center)
        )

        AnimatedSplashIcon(
            visible = state.backgroundState == BackgroundState.FULL_MUSICS,
            icon = ImageVector.vectorResource(id = R.drawable.ic_musics),
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}