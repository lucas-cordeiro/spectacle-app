package br.com.spectacle.app.feature.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MoviesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {  }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }
}