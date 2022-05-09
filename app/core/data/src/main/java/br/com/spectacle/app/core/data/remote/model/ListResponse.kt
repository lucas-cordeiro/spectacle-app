package br.com.spectacle.app.core.data.remote.model

data class ListResponse<T>(
    val count: Long = 0L,
    val data: List<T> = emptyList()
)
