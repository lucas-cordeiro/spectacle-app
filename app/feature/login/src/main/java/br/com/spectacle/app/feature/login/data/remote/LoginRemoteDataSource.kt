package br.com.spectacle.app.feature.login.data.remote

import br.com.spectacle.app.core.data.remote.SpectacleHttpClient
import br.com.spectacle.app.feature.login.data.remote.model.UserVerifyResponse
import io.ktor.client.request.post

class LoginRemoteDataSource(
    private val httpClient: SpectacleHttpClient
) {
    suspend fun verifyEmailAlreadyExist(email: String): UserVerifyResponse {
        return httpClient().post("/verify"){
            body = hashMapOf("email" to email)
        }
    }
}