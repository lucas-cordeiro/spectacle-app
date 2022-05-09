package br.com.spectacle.app.feature.login.di

import br.com.spectacle.app.feature.login.data.network.LoginRemoteDataSource
import br.com.spectacle.app.feature.login.data.repository.LoginRepositoryImpl
import br.com.spectacle.app.feature.login.domain.repository.LoginRepository
import br.com.spectacle.app.feature.login.domain.usecase.RegisterWithEmailAndPasswordUseCase
import br.com.spectacle.app.feature.login.domain.usecase.SingInWithEmailAndPasswordUseCase
import br.com.spectacle.app.feature.login.domain.usecase.VerifyEmailAlreadyExistUseCase
import br.com.spectacle.app.feature.login.presentation.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel {
        val loginRemoteDataSource = LoginRemoteDataSource(
            httpClient = get()
        )
        val loginRepository: LoginRepository = LoginRepositoryImpl(
            remoteDataSource = loginRemoteDataSource,
            auth = FirebaseAuth.getInstance()
        )

        val verifyEmailAlreadyExistUseCase = VerifyEmailAlreadyExistUseCase(
            loginRepository = loginRepository
        )
        val singInWithEmailAndPasswordUseCase = SingInWithEmailAndPasswordUseCase(
            loginRepository = loginRepository
        )
        val registerWithEmailAndPasswordUseCase = RegisterWithEmailAndPasswordUseCase(
            loginRepository = loginRepository
        )

        LoginViewModel(
            singInWithEmailAndPasswordUseCase = singInWithEmailAndPasswordUseCase,
            registerWithEmailAndPasswordUseCase = registerWithEmailAndPasswordUseCase,
            verifyEmailAlreadyExistUseCase = verifyEmailAlreadyExistUseCase
        )
    }
}