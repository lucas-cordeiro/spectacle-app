package br.com.spectacle.app.feature.movies.presentation.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetMessage
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetOption
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetOptions
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetProgress
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetProgressState
import br.com.spectacle.app.feature.movies.domain.model.Movie
import br.com.spectacle.app.feature.movies.presentation.BottomSheetState

@Composable
internal fun MoviesBottomSheetContent(
    bottomSheetState: BottomSheetState?,
    clickedConfirmMovieAction: (Movie) -> Unit,
    clickedCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Crossfade(targetState = bottomSheetState) { state ->
            when(state){
                is BottomSheetState.MovieAction -> {
                    BottomSheetOptions(
                        options = state.options,
                        onClick = { clickedConfirmMovieAction(state.movie) },
                        onClose = { clickedCancel() }
                    )
                }
                is BottomSheetState.Progress -> {
                    BottomSheetProgress(
                        state = state.toBottomSheetProgressState(),
                        message = state.message,
                        onClick = { clickedCancel() },
                    )
                }
                else -> {
                    BottomSheetOptions(
                        options = emptyList(),
                        onClick = { clickedCancel() },
                        onClose = { clickedCancel() }
                    )
                }
            }
        }
    }
}