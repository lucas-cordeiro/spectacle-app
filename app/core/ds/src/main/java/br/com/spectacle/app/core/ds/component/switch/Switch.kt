package br.com.spectacle.app.core.ds.component.switch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.component.divider.VerticalDivider
import br.com.spectacle.app.core.ds.theme.BackgroundVariant

private const val SWITCH_ANIMATION_DURATION = 300

@Composable
fun <T : SwitchItem> Switch(
    value: T,
    options: List<T>,
    onOptionSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    colors: SwitchColors = SwitchDefaults.switchColors(),
    padding: SwitchPadding = SwitchDefaults.switchPadding(),
    textStyle: SwitchTextStyle = SwitchDefaults.switchTextStyle(),
    dividerEnabled: Boolean = true,
    enabled: Boolean = true,
) {
    Surface(
        shape = shape,
        color = colors.backgroundColor(enabled = enabled).value,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(padding.backgroundPadding),
        ) {
            options.forEach { option ->
                val checkedIndex = options.indexOf(value)
                val optionIndex = options.indexOf(option)

                val checked = remember(value) { option == value }
                val state =
                    remember(checked) { if (checked) SwitchState.Enable else SwitchState.Disable }

                val showLeftDivider = remember(dividerEnabled, checked, checkedIndex, optionIndex) {
                    dividerEnabled && !checked && checkedIndex+1 < optionIndex
                }
                val showRightDivider = remember(dividerEnabled, checked, checkedIndex, optionIndex) {
                    dividerEnabled && !checked && checkedIndex-1 > optionIndex
                }

                SwitchImpl(
                    value = state,
                    onBackgroundColor = colors.onBackgroundColor(enabled = enabled).value,
                    selectedColor = colors.selectedColor(enabled = enabled).value,
                    onSelectedColor = colors.onSelectedColor(enabled = enabled).value,
                    shape = shape,
                    contentPadding = padding.contentPadding,
                    shadowPadding = padding.shadowPadding,
                    showLeftDivider = showLeftDivider,
                    showRightDivider = showRightDivider,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            onClick = { onOptionSelected(option) },
                            enabled = enabled,
                            indication = null,
                            interactionSource = MutableInteractionSource(),
                        )
                ) { color ->
                    val style = if(checked) textStyle.selectedTextStyle else textStyle.backgroundTextStyle
                    Text(
                        text = option.name,
                        style = style.copy(color = color),
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SwitchImpl(
    value: SwitchState,
    onBackgroundColor: Color,
    selectedColor: Color,
    onSelectedColor: Color,
    shape: Shape,
    contentPadding: Dp,
    shadowPadding: Dp,
    showLeftDivider: Boolean,
    showRightDivider: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.(color: Color) -> Unit,
) {
    val transition = updateTransition(value, label = "")
    val colorDrawFactory by transition.animateColor(
        transitionSpec = {
            when {
                initialState == SwitchState.Disable -> tween(
                    SWITCH_ANIMATION_DURATION,
                    easing = FastOutSlowInEasing
                )
                targetState == SwitchState.Disable -> tween(SWITCH_ANIMATION_DURATION)
                else -> spring()
            }
        }, label = ""
    ) {
        when (it) {
            SwitchState.Enable -> onSelectedColor
            SwitchState.Disable -> onBackgroundColor
        }
    }
    val backgroundDrawFactory by transition.animateFloat(
        transitionSpec = {
            when {
                initialState == SwitchState.Disable -> tween(
                    SWITCH_ANIMATION_DURATION,
                    easing = FastOutSlowInEasing
                )
                targetState == SwitchState.Disable -> tween(SWITCH_ANIMATION_DURATION)
                else -> spring()
            }
        }, label = ""
    ) {
        when (it) {
            SwitchState.Enable -> 1f
            SwitchState.Disable -> 0f
        }
    }

    val shadowDrawFactory by transition.animateDp(
        transitionSpec = {
            when {
                initialState == SwitchState.Disable -> tween(
                    delayMillis = SWITCH_ANIMATION_DURATION.div(7).times(5),
                    durationMillis = SWITCH_ANIMATION_DURATION.div(7).times(3),
                    easing = FastOutSlowInEasing
                )
                targetState == SwitchState.Disable -> tween(0)
                else -> spring()
            }
        }, label = ""
    ) {
        when (it) {
            SwitchState.Enable -> shadowPadding
            SwitchState.Disable -> 0.dp
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(visible = showLeftDivider) {
            VerticalDivider(
                modifier = Modifier.height(12.dp)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .shadow(
                    elevation = shadowDrawFactory,
                    shape = shape
                )
                .background(
                    color = selectedColor.copy(alpha = backgroundDrawFactory),
                    shape = shape
                )
                .padding(contentPadding)

        ) {
            content(colorDrawFactory)
        }
        AnimatedVisibility(visible = showRightDivider) {
            VerticalDivider(
                modifier = Modifier.height(12.dp)
            )
        }
    }
}

private sealed class SwitchState {
    object Enable : SwitchState()
    object Disable : SwitchState()
}

@Stable
interface SwitchColors {
    @Composable
    fun backgroundColor(enabled: Boolean): State<Color>

    @Composable
    fun onBackgroundColor(enabled: Boolean): State<Color>

    @Composable
    fun selectedColor(enabled: Boolean): State<Color>

    @Composable
    fun onSelectedColor(enabled: Boolean): State<Color>
}

@Stable
interface SwitchPadding {
    val shadowPadding: Dp
    val backgroundPadding: Dp
    val contentPadding: Dp
}

@Stable
interface SwitchTextStyle {
    val backgroundTextStyle: TextStyle
    val selectedTextStyle: TextStyle
}

object SwitchDefaults {
    @Composable
    fun switchColors(
        backgroundColor: Color = MaterialTheme.colors.onBackground.copy(alpha = 0.04f),
        onBackgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        selectedColor: Color =  BackgroundVariant,
        onSelectedColor: Color = MaterialTheme.colors.onSurface
    ): SwitchColors = DefaultSwitchColors(
        backgroundColor = backgroundColor,
        onBackgroundColor = onBackgroundColor,
        selectedColor = selectedColor,
        onSelectedColor = onSelectedColor
    )

    @Composable
    fun switchPadding(
        backgroundPadding: Dp = 4.dp,
        contentPadding: Dp = 2.dp,
        shadowPadding: Dp = 2.dp,
    ) = object : SwitchPadding {
        override val backgroundPadding: Dp
            get() = backgroundPadding

        override val contentPadding: Dp
            get() = contentPadding

        override val shadowPadding: Dp
            get() = shadowPadding
    }

    @Composable
    fun switchTextStyle(
        backgroundTextStyle: TextStyle = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Normal),
        selectedTextStyle: TextStyle =  MaterialTheme.typography.h4,
    ) = object : SwitchTextStyle {
        override val backgroundTextStyle: TextStyle
            get() = backgroundTextStyle

        override val selectedTextStyle: TextStyle
            get() = selectedTextStyle
    }
}

private class DefaultSwitchColors(
    private val backgroundColor: Color,
    private val onBackgroundColor: Color,
    private val selectedColor: Color,
    private val onSelectedColor: Color,
) : SwitchColors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) backgroundColor else backgroundColor.copy(alpha = ContentAlpha.disabled))
    }

    @Composable
    override fun onBackgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) onBackgroundColor else onBackgroundColor.copy(alpha = ContentAlpha.disabled))
    }

    @Composable
    override fun selectedColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) selectedColor else selectedColor.copy(alpha = ContentAlpha.disabled))
    }

    @Composable
    override fun onSelectedColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) onSelectedColor else onSelectedColor.copy(alpha = ContentAlpha.disabled))
    }
}