package br.com.spectacle.app.feature.login.data.repository

import br.com.spectacle.app.feature.login.data.remote.LoginRemoteDataSource
import br.com.spectacle.app.feature.login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class LoginRepositoryImpl(
    private val remoteDataSource: LoginRemoteDataSource,
    private val auth: FirebaseAuth
) : LoginRepository {
    override suspend fun verifyEmailAlreadyExist(email: String): Boolean {
        return remoteDataSource.verifyEmailAlreadyExist(email).email
    }

    override suspend fun singInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun registerWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }
}