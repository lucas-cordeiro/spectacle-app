package br.com.spectacle.app.feature.login.domain.usecase

import br.com.spectacle.app.core.domain.helper.SecurityUtils
import br.com.spectacle.app.feature.login.domain.repository.LoginRepository
import java.security.InvalidParameterException

class RegisterWithEmailAndPasswordUseCase(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String){
        val sha256Password = SecurityUtils.sha256(password) ?: throw InvalidParameterException("Invalid password, cannot be null")
        loginRepository.registerWithEmailAndPassword(email = email, password = sha256Password)
    }
}