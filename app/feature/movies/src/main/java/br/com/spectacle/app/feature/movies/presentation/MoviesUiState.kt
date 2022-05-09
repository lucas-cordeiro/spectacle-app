package br.com.spectacle.app.feature.movies.presentation

import br.com.spectacle.app.core.ds.arch.state.UiState
import br.com.spectacle.app.feature.movies.domain.model.Genre
import br.com.spectacle.app.feature.movies.domain.model.Movie
import br.com.spectacle.app.feature.movies.presentation.components.MoviesSwitchOption
import br.com.spectacle.app.feature.movies.presentation.model.MovieWithGenre

internal data class MoviesUiState(
    val loading: Boolean = true,
    val genres: List<Genre> = emptyList(),
    val movies: List<MovieWithGenre> = emptyList(),
    val isFavoritesTab: Boolean = true,
    val selectedMovie: Movie? = null
): UiState