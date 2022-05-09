package br.com.spectacle.app.feature.musics.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.spectacle.app.core.ds.theme.SpectacleTheme
import com.google.accompanist.insets.ProvideWindowInsets
import org.koin.androidx.viewmodel.ext.android.viewModel

class MusicsActivity : ComponentActivity() {

    internal val viewModel: MusicViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SpectacleTheme {
                ProvideWindowInsets {
                    MusicScreen()
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MusicsActivity::class.java)
    }
}