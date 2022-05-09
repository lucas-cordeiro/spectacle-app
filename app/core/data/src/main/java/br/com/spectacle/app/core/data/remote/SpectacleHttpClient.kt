package br.com.spectacle.app.core.data.remote

import com.google.firebase.auth.FirebaseAuth
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.coroutines.tasks.await
import logcat.logcat

class SpectacleHttpClient(
    private val auth: FirebaseAuth,
    private val spectacleUrl: SpectacleUrl
) {
    suspend operator fun invoke(): HttpClient {
        val token = auth.currentUser?.getIdToken(false)?.await()?.token.orEmpty()
        return HttpClient(OkHttp){
            install(JsonFeature) {
                serializer = GsonSerializer {
                    serializeNulls()
                }
            }
            install(Logging) {
                logger =  object : Logger {
                    override fun log(message: String) {
                        logcat("KTOR") { message }
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)

                url(
                    scheme = spectacleUrl.scheme,
                    host = spectacleUrl.host,
                    path = this.url.encodedPath,
                    port = spectacleUrl.port
                )

                headers.append("Authentication", token)
            }
        }
    }
}