package br.com.spectacle.app.feature.movies.data.remote.model

import br.com.spectacle.app.feature.movies.domain.model.Movie

data class MovieResponse(
    val id: Long = 0L,
    val title: String = "",
    val poster: String = "",
    val releaseDate: Long = 0L,
    val genres: List<Long> = emptyList()
){
    fun map() : Movie {
        return Movie(
            id = id,
            title = title,
            poster = poster,
            releaseDate = releaseDate,
            genres = genres
        )
    }
}
