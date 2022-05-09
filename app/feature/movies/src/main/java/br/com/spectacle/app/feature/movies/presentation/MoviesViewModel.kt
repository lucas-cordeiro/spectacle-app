package br.com.spectacle.app.feature.movies.presentation

import androidx.lifecycle.viewModelScope
import br.com.spectacle.app.core.ds.arch.ViewModel
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetOption
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetState
import br.com.spectacle.app.feature.movies.domain.model.Genre
import br.com.spectacle.app.feature.movies.domain.model.Movie
import br.com.spectacle.app.feature.movies.domain.usecase.AddFavoriteMovieUseCase
import br.com.spectacle.app.feature.movies.domain.usecase.GetFavoritesMoviesUseCase
import br.com.spectacle.app.feature.movies.domain.usecase.GetGenresMoviesUseCase
import br.com.spectacle.app.feature.movies.domain.usecase.GetPopularsMoviesUseCase
import br.com.spectacle.app.feature.movies.domain.usecase.RemoveFavoriteMovieUseCase
import br.com.spectacle.app.feature.movies.presentation.components.MoviesSwitchOption
import br.com.spectacle.app.feature.movies.presentation.model.MovieWithGenre
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val BOTTOM_SHEET_DELAY = 200L
private const val REQUEST_DELAY = 2 * 1000L

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
                val genres = withContext(IO){
                    getGenresMoviesUseCase()
                }
                handleGenres(genres)
            } catch (t: Throwable) {
                handleError(t)
            }
        }
    }

    private fun handleGenres(genres: List<Genre>) {
        setState { state -> state.copy(genres = genres) }
        getMovies(state.value.isFavoritesTab)
    }


    private fun getMovies(isFavoritesTab: Boolean) {
        viewModelScope.launch {
            try {
                setLoading(true)
                val movies = withContext(IO) {
                    delay(REQUEST_DELAY)
                    if(isFavoritesTab)
                        getFavoritesMoviesUseCase()
                    else
                        getPopularsMoviesUseCase()
                }
                handleMovies(movies)
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
        getMovies(isFavoritesTab)
    }

    fun clickedMovie(movie: Movie) {
        viewModelScope.launch {
            setState { state ->
                val bottomSheetState = BottomSheetState.ItemAction(
                    options = getMovieActionByState(
                        state = state,
                        movie = movie
                    ),
                    itemId = movie.id
                )
                state.copy(bottomSheetState = bottomSheetState)
            }
            delay(BOTTOM_SHEET_DELAY)
            sendAction { MoviesUiAction.ExpandBottomSheet }
        }
    }

    fun clickedConfirmMovieAction(movieId: Long) {
        handleMovieAction(movieId)
    }

    fun clickedCancelAction() {
        viewModelScope.launch {
            sendAction { MoviesUiAction.CollapseBottomSheet }
            delay(BOTTOM_SHEET_DELAY)
            setBottomSheetState(null)
        }
    }

    private fun handleMovieAction(movieId: Long) {
        viewModelScope.launch {
            try {
                val isFavoritesTab = state.value.isFavoritesTab
                setBottomSheetState(
                    BottomSheetState.Progress.Loading(
                        getMovieActionMessageLoading(isFavoritesTab)
                    )
                )

                withContext(IO){
                    delay(REQUEST_DELAY)

                    if (isFavoritesTab) {
                        removeFavoriteMovieUseCase(movieId)
                    } else {
                        addFavoriteMovieUseCase(movieId)
                    }
                }

                handleResultMovieAction(isFavoritesTab)
            } catch (t: Throwable) {
                handleError(t)
            }
        }
    }

    private fun handleResultMovieAction(isFavoritesTab: Boolean){
        setBottomSheetState(
            BottomSheetState.Progress.Success(
                getMovieActionMessageSuccess(isFavoritesTab)
            )
        )
        if (isFavoritesTab){
            getMovies(isFavoritesTab)
        }
    }

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    private fun setLoading(value: Boolean) {
        setState { state -> state.copy(loading = value) }
    }

    private fun setBottomSheetState(value: BottomSheetState?) {
        setState { state -> state.copy(bottomSheetState = value) }
    }

    private fun getMovieActionByState(
        state: MoviesUiState,
        movie: Movie
    ): List<BottomSheetOption> {
        val message = if (state.isFavoritesTab) {
            "Remover \"${movie.title}\" dos Favoritos"
        } else {
            "Adicionar \"${movie.title}\" ao Favoritos"
        }
        return listOf(BottomSheetOption(message))
    }

    private fun getMovieActionMessageLoading(isFavoritesTab: Boolean): String {
        return if(isFavoritesTab) {
            "Removendo filme dos favoritos..."
        } else {
            "Adicionando filme aos favoritos..."
        }
    }

    private fun getMovieActionMessageSuccess(isFavoritesTab: Boolean): String {
        return if(isFavoritesTab) {
            "Filme removido com sucesso!"
        } else {
            "Filme adicionado com sucesso!"
        }
    }
}