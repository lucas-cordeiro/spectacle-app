package br.com.spectacle.app.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import br.com.spectacle.app.core.ds.arch.collectActions
import br.com.spectacle.app.core.ds.theme.SpectacleTheme
import br.com.spectacle.app.feature.login.presentation.LoginActivity
import br.com.spectacle.app.feature.movies.presentation.MoviesActivity
import com.google.accompanist.insets.ProvideWindowInsets
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
            is SplashUiAction.NavigateToLogin -> navigateToLogin()
            is SplashUiAction.NavigateToMovies -> navigateToMovies()
            is SplashUiAction.NavigateToMusics -> Toast.makeText(this, "Musics", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToLogin(){
        startActivity(LoginActivity.getIntent(this))
    }

    private fun navigateToMovies(){
        startActivity(MoviesActivity.getIntent(this))
    }
}