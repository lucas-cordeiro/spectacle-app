package br.com.spectacle.app.feature.musics.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
internal fun MusicsLoading(
    visible: Boolean,
    modifier: Modifier = Modifier
){
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(),
        exit = shrinkVertically(),
        modifier = modifier
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.music_loading))
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever
        )
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier.size(100.dp)
        )
    }
}