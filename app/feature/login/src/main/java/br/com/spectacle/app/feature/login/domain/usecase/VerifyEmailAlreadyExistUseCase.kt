package br.com.spectacle.app.feature.login.domain.usecase

import br.com.spectacle.app.feature.login.domain.repository.LoginRepository

class VerifyEmailAlreadyExistUseCase(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String): Boolean {
        return loginRepository.verifyEmailAlreadyExist(email)
    }
}