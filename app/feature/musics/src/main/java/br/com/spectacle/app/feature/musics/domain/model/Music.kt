package br.com.spectacle.app.feature.musics.domain.model

internal data class Music(
    val id: Long,
    val title: String,
    val image: String,
    val artist: Artist
)
