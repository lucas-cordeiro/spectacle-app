package br.com.spectacle.app.feature.movies.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.spectacle.app.core.ds.arch.collectActions
import br.com.spectacle.app.core.ds.theme.SpectacleTheme
import com.google.accompanist.insets.ProvideWindowInsets
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesActivity : ComponentActivity() {

    internal val viewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SpectacleTheme {
                ProvideWindowInsets {
                    MoviesScreen()
                }
            }
        }
    }

    internal fun finishScreen(){
        finish()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }
}