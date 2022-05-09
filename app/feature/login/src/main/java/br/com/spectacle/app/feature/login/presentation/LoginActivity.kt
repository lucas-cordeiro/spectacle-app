package br.com.spectacle.app.feature.login.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import br.com.spectacle.app.core.ds.theme.SpectacleTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : ComponentActivity() {

    internal val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SpectacleTheme {
                ProvideWindowInsets(
                    windowInsetsAnimationsEnabled = true
                ) {
                    LoginScreen(
                        modifier = Modifier.systemBarsPadding()
                    )
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.clickedBackButton()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}