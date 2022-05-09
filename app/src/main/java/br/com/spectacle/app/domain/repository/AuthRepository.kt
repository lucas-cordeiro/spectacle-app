package br.com.spectacle.app.domain.repository

interface AuthRepository {
    suspend fun verifyHasUserLogged(): Boolean
}