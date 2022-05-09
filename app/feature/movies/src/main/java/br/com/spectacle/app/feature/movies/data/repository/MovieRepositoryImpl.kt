package br.com.spectacle.app.feature.movies.data.repository

import br.com.spectacle.app.feature.movies.domain.model.Movie
import br.com.spectacle.app.feature.movies.data.remote.MovieRemoteDataSource
import br.com.spectacle.app.feature.movies.domain.model.Genre
import br.com.spectacle.app.feature.movies.domain.repository.MovieRepository

internal class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource
): MovieRepository {
    override suspend fun getPopulars(): List<Movie> {
        return remoteDataSource.getPopulars().data.map { input -> input.map() }
    }
    override suspend fun getFavorites(): List<Movie> {
        return remoteDataSource.getFavorites().data.map { input -> input.map() }
    }

    override suspend fun addFavoriteMovie(movieId: Long) {
        return remoteDataSource.addFavoriteMovie(movieId)
    }

    override suspend fun removeFavoriteMovie(movieId: Long) {
        return remoteDataSource.removeFavoriteMovie(movieId)
    }


    override suspend fun getGenres(): List<Genre> {
        return remoteDataSource.getGenres().data.map { input -> input.map() }
    }
}