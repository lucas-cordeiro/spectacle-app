package br.com.spectacle.app.feature.movies.presentation

import br.com.spectacle.app.core.ds.arch.state.UiState
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetOption
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetProgressState
import br.com.spectacle.app.feature.movies.domain.model.Genre
import br.com.spectacle.app.feature.movies.domain.model.Movie
import br.com.spectacle.app.feature.movies.presentation.components.MoviesSwitchOption
import br.com.spectacle.app.feature.movies.presentation.model.MovieWithGenre

internal data class MoviesUiState(
    val loading: Boolean = true,
    val genres: List<Genre> = emptyList(),
    val movies: List<MovieWithGenre> = emptyList(),
    val isFavoritesTab: Boolean = true,
    val bottomSheetState: BottomSheetState? = null
) : UiState

internal sealed class BottomSheetState {
    data class MovieAction(
        val options: List<BottomSheetOption>,
        val movie: Movie
    ) : BottomSheetState()

    sealed class Progress(open val message: String) : BottomSheetState() {
        data class Loading(
            override val message: String
        ) : Progress(message)

        data class Success(
            override val message: String
        ) : Progress(message)

        data class Error(
            override val message: String
        ) : Progress(message)

        fun toBottomSheetProgressState(): BottomSheetProgressState {
            return when (this) {
                is Loading -> BottomSheetProgressState.LOADING
                is Success -> BottomSheetProgressState.SUCCESS
                else -> BottomSheetProgressState.ERROR
            }
        }
    }

}