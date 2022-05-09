package br.com.spectacle.app.feature.movies.domain.repository

import br.com.spectacle.app.feature.movies.domain.model.Genre
import br.com.spectacle.app.feature.movies.domain.model.Movie

internal interface MovieRepository {
    suspend fun getPopulars(): List<Movie>
    suspend fun getFavorites(): List<Movie>

    suspend fun addFavoriteMovie(movieId: Long)
    suspend fun removeFavoriteMovie(movieId: Long)

    suspend fun getGenres(): List<Genre>
}