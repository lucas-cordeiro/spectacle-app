package br.com.spectacle.app.feature.movies.data.remote.model

import br.com.spectacle.app.feature.movies.domain.model.Genre

data class GenreResponse(
    val id: Long = 0L,
    val name: String = ""
){
    fun map() : Genre {
        return Genre(
            id = id,
            name = name
        )
    }
}
