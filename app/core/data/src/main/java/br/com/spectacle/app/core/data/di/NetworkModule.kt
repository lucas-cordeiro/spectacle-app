package br.com.spectacle.app.core.data.di

import br.com.spectacle.app.core.data.remote.SpectacleHttpClient
import br.com.spectacle.app.core.data.remote.SpectacleUrl
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val networkModule = module {
    single {
        SpectacleHttpClient(
            auth = FirebaseAuth.getInstance(),
            spectacleUrl = SpectacleUrl()
        )
    }
}