package br.com.spectacle.app.core.ds.component.bottomsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.R
import br.com.spectacle.app.core.ds.component.button.BasicTextButton
import br.com.spectacle.app.core.ds.component.button.RoundedButton
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun BottomSheetProgress(
    onClick: () -> Unit,
    state: BottomSheetProgressState,
    modifier: Modifier = Modifier,
    message: String? = null,
    errorCode: Int? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))

        val (iterations, clipSpec) = remember(state) {
            when (state) {
                BottomSheetProgressState.LOADING -> {
                    Pair(LottieConstants.IterateForever, LottieClipSpec.Progress(0f, 0.28f))
                }
                BottomSheetProgressState.SUCCESS -> {
                    Pair(1, LottieClipSpec.Progress(0.28f, 0.47f))
                }
                BottomSheetProgressState.ERROR -> {
                    Pair(1, LottieClipSpec.Progress(0.63f, 1f))
                }
            }
        }

        val progress by animateLottieCompositionAsState(
            composition,
            iterations = iterations,
            clipSpec = clipSpec
        )
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier.size(100.dp)
        )

        if (!message.isNullOrBlank()) {
            Text(
                text = message,
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSurface.copy(
                        alpha = 0.9f
                    )
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
            )
        }
        if (errorCode != null) {
            Text(
                text = "Código: #${errorCode.toString().padStart(4, '0')}",
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.onSurface.copy(
                        alpha = 0.7f
                    )
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = 16.dp)
            )
        }

        AnimatedVisibility(
            visible = state != BottomSheetProgressState.LOADING,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            RoundedButton(
                text = { BasicTextButton(text = "Entendi!") },
                onClick = onClick,
                shapeCorner = 8.dp,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
            )
        }
    }
}

enum class BottomSheetProgressState {
    LOADING, SUCCESS, ERROR
}
