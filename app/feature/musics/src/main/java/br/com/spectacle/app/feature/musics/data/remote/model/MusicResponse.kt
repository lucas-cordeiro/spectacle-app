package br.com.spectacle.app.feature.musics.data.remote.model

import br.com.spectacle.app.feature.musics.domain.model.Music

internal data class MusicResponse(
    val id: Long,
    val title: String,
    val image: String,
    val artist: ArtistResponse
) {
    fun map() : Music {
        return Music(
            id = id,
            title = title,
            image = image,
            artist = artist.map()
        )
    }
}
