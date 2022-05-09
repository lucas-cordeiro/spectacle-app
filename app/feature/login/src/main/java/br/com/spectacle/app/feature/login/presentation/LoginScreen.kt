package br.com.spectacle.app.feature.login.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.spectacle.app.core.ds.arch.collectActions
import br.com.spectacle.app.core.ds.arch.collectAsStateLifecycleAware
import br.com.spectacle.app.core.ds.component.dialog.DialogNeutral
import br.com.spectacle.app.feature.login.presentation.components.BrandComponent
import br.com.spectacle.app.feature.login.presentation.components.LoginBackground
import br.com.spectacle.app.feature.login.presentation.navigation.LoginNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun LoginActivity.LoginScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberAnimatedNavController()

    val state by viewModel.state.collectAsStateLifecycleAware()

    collectActions(viewModel) { action ->
        handleAction(action, navController)
    }

    Box(modifier = modifier) {
        LoginBackground(
            destination = state.destination
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            BrandComponent(
                modifier = Modifier
                    .padding(top = 32.dp)
            )

            LoginNavigation(
                loading = state.loading,
                email = state.email,
                password = state.password,
                destination = state.destination,
                clickedConfirmEmail = { viewModel.clickedConfirmEmail(it) },
                clickedConfirmPassword = { viewModel.clickedConfirmPassword(it) },
                navController = navController,
                modifier = Modifier.weight(1f)
            )
        }

        val uiDialog = state.uiDialog
        if (uiDialog != null)
            DialogNeutral(
                title = uiDialog.title,
                message = uiDialog.message,
                onDismissRequest = { viewModel.clickedDismissDialog() },
                onNeutralButtonClick = { viewModel.clickedDismissDialog() }
            )
    }
}

private fun LoginActivity.handleAction(
    action: LoginUiAction,
    navController: NavController,
) {
    when (action) {
        is LoginUiAction.NavigateToPasswordScreen -> {
            navController.navigate(LoginRoutes.PASSWORD.name)
        }
        is LoginUiAction.FinishScreen -> {
            finish()
        }
    }
}