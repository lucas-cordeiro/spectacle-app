package br.com.spectacle.app.feature.movies.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.arch.collectActions
import br.com.spectacle.app.core.ds.arch.collectAsStateLifecycleAware
import br.com.spectacle.app.core.ds.theme.Background
import br.com.spectacle.app.core.ds.theme.BackgroundVariant
import br.com.spectacle.app.feature.movies.presentation.components.MoviesBackground
import br.com.spectacle.app.feature.movies.presentation.components.MoviesBottomSheetContent
import br.com.spectacle.app.feature.movies.presentation.components.MoviesList
import br.com.spectacle.app.feature.movies.presentation.components.MoviesLoading
import br.com.spectacle.app.feature.movies.presentation.components.MoviesSwitch
import br.com.spectacle.app.feature.movies.presentation.components.MoviesSwitchOption
import br.com.spectacle.app.feature.movies.presentation.components.MoviesToolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MoviesActivity.MoviesScreen(
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateLifecycleAware()

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val scope = rememberCoroutineScope()
    collectActions(viewModel) { action -> handleAction(action, sheetState, scope) }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        scrimColor = Background.copy(alpha = 0.4f),
        sheetBackgroundColor = BackgroundVariant,
        sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        sheetContent = {
            MoviesBottomSheetContent(
                isFavoritesTab = state.isFavoritesTab,
                clickedConfirm = { viewModel.clickedConfirmAction() },
                clickedCancel = { viewModel.clickedCancelAction() },
            )
        },
    ) {
        Box(
            modifier = modifier
        ) {
            MoviesBackground(isFavoritesTab = state.isFavoritesTab)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                MoviesToolbar()

                MoviesSwitch(
                    option = if (state.isFavoritesTab) {
                        MoviesSwitchOption.Favorites
                    } else {
                        MoviesSwitchOption.Populars
                    },
                    clickedOption = { option ->
                        viewModel.clickedTab(option)
                    },
                    modifier = Modifier.padding(top = 16.dp)
                )

                if (state.loading)
                    MoviesLoading()

                MoviesList(
                    loading = state.loading,
                    moviesWithGenre = state.movies,
                    clickedMovie = { movie -> viewModel.clickedMovie(movie) },
                    modifier = Modifier.padding(top = 32.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun MoviesActivity.handleAction(
    action: MoviesUiAction,
    sheetState: ModalBottomSheetState,
    scope: CoroutineScope
) {
    when (action) {
        is MoviesUiAction.FinishScreen -> finishScreen()
        is MoviesUiAction.CollapseBottomSheet -> scope.launch { sheetState.hide() }
        is MoviesUiAction.ExpandBottomSheet -> scope.launch { sheetState.show() }
    }
}