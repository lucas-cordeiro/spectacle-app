package br.com.spectacle.app.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.core.view.WindowCompat
import br.com.spectacle.app.core.ds.R
import br.com.spectacle.app.core.ds.arch.collectActions
import br.com.spectacle.app.core.ds.arch.collectAsStateLifecycleAware
import br.com.spectacle.app.core.ds.theme.SpectacleTheme
import br.com.spectacle.app.feature.movies.MoviesActivity
import br.com.spectacle.app.presentation.splash.components.AnimateSplashBackground
import br.com.spectacle.app.presentation.splash.components.AnimateSplashButtons
import br.com.spectacle.app.presentation.splash.components.AnimatedSplashIcon
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    internal val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        collectActions(viewModel, ::handleAction)

        setContent {
            SpectacleTheme {
                ProvideWindowInsets {
                    SplashScreen()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.splashScreenOnResume()
    }

    private fun handleAction(action: SplashUiAction) {
        when (action) {
            is SplashUiAction.NavigateToMovies -> navigateToMovies()
            is SplashUiAction.NavigateToMusics -> Toast.makeText(this, "Musics", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToMovies(){
        startActivity(MoviesActivity.getIntent(this))
    }
}