package br.com.spectacle.app.di

import br.com.spectacle.app.data.repository.AuthRepositoryImpl
import br.com.spectacle.app.domain.repository.AuthRepository
import br.com.spectacle.app.domain.usecase.VerifyHasUserLoggedUseCase
import br.com.spectacle.app.presentation.splash.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    viewModel {
        val auth = FirebaseAuth.getInstance()
        val authRepository: AuthRepository = AuthRepositoryImpl(auth = auth)
        val verifyHasUserLoggedUseCase = VerifyHasUserLoggedUseCase(
            authRepository = authRepository
        )
        SplashViewModel(
            verifyHasUserLoggedUseCase = verifyHasUserLoggedUseCase
        )
    }
}