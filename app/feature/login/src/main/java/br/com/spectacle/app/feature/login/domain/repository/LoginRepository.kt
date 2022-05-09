package br.com.spectacle.app.feature.login.domain.repository

interface LoginRepository {
    suspend fun verifyEmailAlreadyExist(email: String): Boolean
    suspend fun singInWithEmailAndPassword(email: String, password: String)
    suspend fun registerWithEmailAndPassword(email: String, password: String)
}