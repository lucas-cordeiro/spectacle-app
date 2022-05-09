package br.com.spectacle.app.data.repository

import br.com.spectacle.app.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthRepositoryImpl(
    private val auth: FirebaseAuth
) : AuthRepository {
    override suspend fun verifyHasUserLogged(): Boolean {
        return auth.currentUser != null
    }
}