package br.com.spectacle.app.feature.login.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.spectacle.app.core.ds.arch.collectAsStateLifecycleAware
import br.com.spectacle.app.core.ds.component.navigation.slideComposable
import br.com.spectacle.app.feature.login.presentation.LoginActivity
import br.com.spectacle.app.feature.login.presentation.LoginDestination
import br.com.spectacle.app.feature.login.presentation.LoginRoutes
import br.com.spectacle.app.feature.login.presentation.screens.LoginEmailScreen
import br.com.spectacle.app.feature.login.presentation.screens.LoginPasswordScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost


private val START_DESTINATION = LoginRoutes.EMAIL

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun LoginActivity.LoginNavigation(
    navController: NavHostController,
    loading: Boolean,
    email: String,
    password: String,
    destination: LoginDestination,
    clickedConfirmEmail: (String) -> Unit,
    clickedConfirmPassword: (String) -> Unit,
    modifier: Modifier = Modifier
){
    AnimatedNavHost(
        navController = navController,
        startDestination = START_DESTINATION.name,
        modifier = modifier
    ) {
        slideComposable(LoginRoutes.EMAIL.name) {
            LoginEmailScreen(
                email = email,
                loading = loading,
                clickedConfirmEmail = clickedConfirmEmail
            )
        }

        slideComposable(LoginRoutes.PASSWORD.name) {
            LoginPasswordScreen(
                password = password,
                loading = loading,
                destination = destination,
                clickedConfirmPassword = clickedConfirmPassword
            )
        }
    }
}