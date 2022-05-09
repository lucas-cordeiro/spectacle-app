package br.com.spectacle.app.core.ds.component.button

import androidx.compose.animation.core.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private enum class ButtonState {
    LOADING, NORMAL
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoundedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.primary,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    disableColor: Color =  MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
    progressAnimation: @Composable () -> Unit = {
        CircularProgressIndicator(
            color = MaterialTheme.colors.onPrimary,
            strokeWidth = 2.5.dp,
            modifier = Modifier.padding(4.dp)
        )
    },
    shapeCorner: Dp = 3.dp,
    height: Dp = 38.dp,
    text: @Composable () -> Unit,
) {
    BoxWithConstraints(modifier) {
        val state: ButtonState =
            remember(loading) { if (loading) ButtonState.LOADING else ButtonState.NORMAL }

        val width = with(LocalDensity.current) { constraints.maxWidth.toDp() }

        val transition = updateTransition(
            targetState = state,
            label = "RoundedButtonTransition",
        )

        val alphaFactory by transition.animateFloat(
            transitionSpec = {
                when {
                    initialState == ButtonState.LOADING -> tween(500, easing = FastOutSlowInEasing)
                    targetState == ButtonState.LOADING -> tween(500)
                    else -> spring()
                }
            },
            label = "RoundedButtonAlpha"
        ) {
            when (it) {
                ButtonState.LOADING -> 0f
                ButtonState.NORMAL -> 1f
            }
        }

        val widthOffsetFactory by transition.animateDp(
            transitionSpec = {
                when {
                    initialState == ButtonState.LOADING -> tween(500, easing = FastOutSlowInEasing)
                    targetState == ButtonState.LOADING -> tween(500)
                    else -> spring()
                }
            },
            label = "RoundedButtonWidth"
        ) {
            when (it) {
                ButtonState.LOADING -> height
                ButtonState.NORMAL -> width
            }
        }

        val roundedCornerFactory by transition.animateDp(
            transitionSpec = {
                when {
                    initialState == ButtonState.LOADING -> tween(500, easing = FastOutSlowInEasing)
                    targetState == ButtonState.LOADING -> tween(500)
                    else -> spring()
                }
            },
            label = "RoundedButtonCornersRadius"
        ) {
            when (it) {
                ButtonState.LOADING -> height / 2
                ButtonState.NORMAL -> shapeCorner
            }
        }


        Surface(
            color = if (enabled) backgroundColor else disableColor,
            onClick = {
                if (enabled && !loading)
                    onClick()
            },
            interactionSource = interactionSource,
            indication = rememberRipple(),
            modifier = Modifier
                .clip(RoundedCornerShape(roundedCornerFactory))
                .width(widthOffsetFactory)
                .height(height)
        ) {
            Box(Modifier.fillMaxSize()) {
                if (loading) {
                    Box(
                        Modifier
                            .size(40.dp)
                            .align(Alignment.Center)
                    ) {
                        progressAnimation()
                    }
                } else {
                    Box(
                        Modifier
                            .align(Alignment.Center)
                            .alpha(alphaFactory)
                    ) {
                        text()
                    }
                }
            }
        }
    }
}
