package br.com.spectacle.app.feature.movies.domain.usecase

import br.com.spectacle.app.feature.movies.domain.repository.MovieRepository

internal class RemoveFavoriteMovieUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Long){
        return movieRepository.removeFavoriteMovie(movieId)
    }
}