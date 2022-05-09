package br.com.spectacle.app.domain.usecase

import br.com.spectacle.app.domain.repository.AuthRepository

class VerifyHasUserLoggedUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return authRepository.verifyHasUserLogged()
    }
}