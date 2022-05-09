package br.com.spectacle.app.presentation.splash.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.presentation.splash.BackgroundState
import java.util.Collections.copy

private const val DURATION = 2000
private const val COLLAPSED_RADIUS = 1f

@Composable
fun AnimateSplashBackground(
    state: BackgroundState,
    finishedListener: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val radius by animateFloatAsState(
            targetValue = when (state) {
                BackgroundState.COLLAPSED -> COLLAPSED_RADIUS.dp.value
                BackgroundState.EXPANDED -> maxWidth.value
                else -> maxHeight.times(4).value
            },
            animationSpec = tween(
                durationMillis = DURATION,
                easing = FastOutSlowInEasing
            ),
            finishedListener = { radius ->
                if (radius != COLLAPSED_RADIUS) {
                    finishedListener()
                }
            }
        )

        val colorCircleMovies = MaterialTheme.colors.primary
        val colorCircleMusic = MaterialTheme.colors.primaryVariant

        val radiusDp = with(LocalDensity.current) { radius.toDp().times(2) }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = radiusDp)
        ) {
            val movieCircleCenter = Offset(x = 0f, y = 0f)
            if (state != BackgroundState.FULL_MUSICS)
                drawCircle(
                    color = colorCircleMovies.copy(0.5f),
                    radius = radius,
                    center = movieCircleCenter
                )

            val musicCircleCenter = Offset(x = size.width, y = size.height)
            if (state != BackgroundState.FULL_MOVIES)
                drawCircle(
                    color = colorCircleMusic.copy(0.5f),
                    radius = radius,
                    center = musicCircleCenter
                )
        }
    }
}