package br.com.spectacle.app.feature.movies.domain.model

data class Movie(
    val id: Long,
    val title: String,
    val poster: String,
    val releaseDate: Long,
    val genres: List<Long>
)
