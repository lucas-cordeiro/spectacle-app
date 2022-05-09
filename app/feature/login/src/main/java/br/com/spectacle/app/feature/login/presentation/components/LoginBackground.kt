package br.com.spectacle.app.feature.login.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.feature.login.presentation.LoginDestination
import kotlin.math.abs

private const val DURATION = 400

private fun <T>configAnimationSpec() = tween<T>(
    durationMillis = DURATION,
    easing = FastOutSlowInEasing,
)

@Composable
fun LoginBackground(
    destination: LoginDestination,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier.fillMaxSize()) {
        val circleTopColor by animateColorAsState(
            targetValue = when (destination) {
                LoginDestination.EMAIL -> MaterialTheme.colors.primary
                LoginDestination.SING_IN -> MaterialTheme.colors.primaryVariant
                LoginDestination.REGISTER -> MaterialTheme.colors.secondary
            },
            animationSpec = configAnimationSpec()
        )

        val circleBottomColor by animateColorAsState(
            targetValue = when (destination) {
                LoginDestination.EMAIL -> MaterialTheme.colors.secondary
                LoginDestination.SING_IN -> MaterialTheme.colors.primary
                LoginDestination.REGISTER -> MaterialTheme.colors.primaryVariant
            },
            animationSpec = configAnimationSpec()
        )

        val circleXProgress by animateFloatAsState(
            targetValue = when (destination) {
                LoginDestination.EMAIL -> 0f
                LoginDestination.SING_IN,LoginDestination.REGISTER -> 1f
            },
            animationSpec = configAnimationSpec()
        )

        val radius = maxWidth.value.times(2)

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val circleTopOffset = Offset(
                x = size.width * circleXProgress,
                y = 0f
            )
            val circleBottomOffset = Offset(
                x = size.width * abs(1 - circleXProgress),
                y = size.height
            )

            drawCircle(
                brush = Brush.radialGradient(
                    0f to circleTopColor.copy(alpha = 0.3f),
                    0.5f to circleTopColor.copy(alpha = 0.2f),
                    1f to Color.Transparent,
                    center = circleTopOffset
                ),
                center = circleTopOffset,
                radius = radius
            )

            drawCircle(
                brush = Brush.radialGradient(
                    0f to circleBottomColor.copy(alpha = 0.3f),
                    0.5f to circleBottomColor.copy(alpha = 0.2f),
                    1f to Color.Transparent,
                    center = circleBottomOffset
                ),
                center = circleBottomOffset,
                radius = radius
            )
        }
    }
}