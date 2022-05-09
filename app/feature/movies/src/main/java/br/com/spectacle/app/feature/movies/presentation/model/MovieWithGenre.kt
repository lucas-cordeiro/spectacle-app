package br.com.spectacle.app.feature.movies.presentation.model

import br.com.spectacle.app.feature.movies.domain.model.Genre
import br.com.spectacle.app.feature.movies.domain.model.Movie

data class MovieWithGenre(
    val genre: Genre,
    val movies: List<Movie>
)
