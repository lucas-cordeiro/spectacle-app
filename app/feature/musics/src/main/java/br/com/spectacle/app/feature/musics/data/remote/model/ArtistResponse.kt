package br.com.spectacle.app.feature.musics.data.remote.model

import br.com.spectacle.app.feature.musics.domain.model.Artist

internal data class ArtistResponse(
    val id: Long,
    val name: String
){
    fun map(): Artist {
        return Artist(
            id = id,
            name = name
        )
    }
}
