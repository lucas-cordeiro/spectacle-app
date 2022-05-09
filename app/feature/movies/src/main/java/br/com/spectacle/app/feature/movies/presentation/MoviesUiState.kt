package br.com.spectacle.app.feature.movies.presentation

import br.com.spectacle.app.core.domain.model.movie.Movie
import br.com.spectacle.app.core.ds.arch.state.UiState

internal data class MoviesUiState(
    val isLoading: Boolean,
    val popularsMovies: List<Movie>,
    val favoritesMovies: List<Movie>
): UiState
