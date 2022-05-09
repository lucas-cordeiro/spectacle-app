package br.com.spectacle.app.feature.movies.presentation

import androidx.lifecycle.viewModelScope
import br.com.spectacle.app.core.ds.arch.ViewModel
import br.com.spectacle.app.feature.movies.domain.model.Genre
import br.com.spectacle.app.feature.movies.domain.model.Movie
import br.com.spectacle.app.feature.movies.domain.usecase.AddFavoriteMovieUseCase
import br.com.spectacle.app.feature.movies.domain.usecase.GetFavoritesMoviesUseCase
import br.com.spectacle.app.feature.movies.domain.usecase.GetGenresMoviesUseCase
import br.com.spectacle.app.feature.movies.domain.usecase.GetPopularsMoviesUseCase
import br.com.spectacle.app.feature.movies.domain.usecase.RemoveFavoriteMovieUseCase
import br.com.spectacle.app.feature.movies.presentation.components.MoviesSwitchOption
import br.com.spectacle.app.feature.movies.presentation.model.MovieWithGenre
import kotlinx.coroutines.launch

internal class MoviesViewModel(
    private val getFavoritesMoviesUseCase: GetFavoritesMoviesUseCase,
    private val getPopularsMoviesUseCase: GetPopularsMoviesUseCase,
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val removeFavoriteMovieUseCase: RemoveFavoriteMovieUseCase,
    private val getGenresMoviesUseCase: GetGenresMoviesUseCase
) : ViewModel<MoviesUiState, MoviesUiAction>(MoviesUiState()) {
    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            try {
                setLoading(true)
                val genres = getGenresMoviesUseCase()
                handleGenres(genres)
            } catch (t: Throwable) {
                handleError(t)
            }
        }
    }

    private fun handleGenres(genres: List<Genre>) {
        setState { state -> state.copy(genres = genres) }
        handleTab(state.value.isFavoritesTab)
    }

    private fun handleTab(favorites: Boolean) {
        if (favorites) {
            getFavorites()
        } else {
            getPopulars()
        }
    }

    private fun getFavorites() {
        viewModelScope.launch {
            try {
                setLoading(true)
                val favorites = getFavoritesMoviesUseCase()
                handleMovies(favorites)
            } catch (t: Throwable) {
                handleError(t)
            } finally {
                setLoading(false)
            }
        }
    }

    private fun getPopulars() {
        viewModelScope.launch {
            try {
                setLoading(true)
                val populars = getPopularsMoviesUseCase()
                handleMovies(populars)
            } catch (t: Throwable) {
                handleError(t)
            } finally {
                setLoading(false)
            }
        }
    }

    private fun handleMovies(movies: List<Movie>) {
        setState { state ->
            val genres = state.genres.filter { genre ->
                movies.find { movie -> movie.genres.contains(genre.id) } != null
            }
            state.copy(
                movies = genres.map { genre ->
                    MovieWithGenre(
                        genre = genre,
                        movies = movies.filter { movie ->
                            movie.genres.contains(genre.id)
                        }
                    )
                }
            )
        }
    }

    fun clickedTab(option: MoviesSwitchOption) {
        updateTab(isFavoritesTab = option is MoviesSwitchOption.Favorites)
    }

    private fun updateTab(isFavoritesTab: Boolean) {
        setState { state ->
            state.copy(isFavoritesTab = isFavoritesTab)
        }
        handleTab(favorites = isFavoritesTab)
    }

    fun clickedMovie(movie: Movie) {
        setState { state ->
            state.copy(selectedMovie = movie)
        }
        sendAction { MoviesUiAction.ExpandBottomSheet }
    }

    fun clickedConfirmAction() {
        clickedCancelAction()
        handleUserActionWithMovie()
    }

    fun clickedCancelAction() {
        sendAction { MoviesUiAction.CollapseBottomSheet }
    }

    private fun handleUserActionWithMovie() {
        viewModelScope.launch {
            try {
                setLoading(true)
                val movieId = state.value.selectedMovie?.id ?: 0L
                val isFavoritesTab = state.value.isFavoritesTab
                if (isFavoritesTab) {
                    removeFavoriteMovieUseCase(movieId)
                } else {
                    addFavoriteMovieUseCase(movieId)
                }
                updateTab(isFavoritesTab = true)

            } catch (t: Throwable) {
                handleError(t)
            } finally {
                setLoading(false)
            }
        }
    }

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    private fun setLoading(value: Boolean) {
        setState { state -> state.copy(loading = value) }
    }
}