package br.com.spectacle.app.core.data.network

import io.ktor.http.*

data class SpectacleUrl(
    val scheme: String = "https",
    val host: String = "spectacle-423c3.uc.r.appspot.com",
    val port: Int = DEFAULT_PORT,
    val path: String = "/",
)
