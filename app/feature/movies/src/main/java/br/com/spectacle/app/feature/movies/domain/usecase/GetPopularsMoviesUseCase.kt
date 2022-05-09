package br.com.spectacle.app.feature.movies.domain.usecase

import br.com.spectacle.app.feature.movies.domain.model.Movie
import br.com.spectacle.app.feature.movies.domain.repository.MovieRepository

internal class GetPopularsMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): List<Movie> {
        return movieRepository.getPopulars()
    }
}