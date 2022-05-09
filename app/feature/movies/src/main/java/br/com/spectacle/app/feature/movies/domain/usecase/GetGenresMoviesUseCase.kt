package br.com.spectacle.app.feature.movies.domain.usecase

import br.com.spectacle.app.feature.movies.domain.model.Genre
import br.com.spectacle.app.feature.movies.domain.repository.MovieRepository

internal class GetGenresMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): List<Genre> {
        return movieRepository.getGenres()
    }
}