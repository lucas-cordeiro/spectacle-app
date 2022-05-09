package br.com.spectacle.app.core.ds.component.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

private const val DURATION = 400
private const val OFFSET = 1000

private val animationSpec = tween<IntOffset>(
    durationMillis = DURATION,
    easing = FastOutSlowInEasing,
)

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.slideComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { OFFSET }, animationSpec = animationSpec) + fadeIn()
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX  = { -OFFSET }, animationSpec = animationSpec) + fadeOut()
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -OFFSET }, animationSpec = animationSpec) + fadeIn()
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX  = { OFFSET }, animationSpec = animationSpec) + fadeOut()
        },
        content = content
    )
}