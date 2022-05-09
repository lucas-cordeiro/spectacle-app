package br.com.spectacle.app.feature.musics.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.arch.collectActions
import br.com.spectacle.app.core.ds.arch.collectAsStateLifecycleAware
import br.com.spectacle.app.core.ds.theme.Background
import br.com.spectacle.app.core.ds.theme.BackgroundVariant
import br.com.spectacle.app.feature.musics.presentation.components.MusicsBackground
import br.com.spectacle.app.feature.musics.presentation.components.MusicsBottomSheetContent
import br.com.spectacle.app.feature.musics.presentation.components.FavoriteMusics
import br.com.spectacle.app.feature.musics.presentation.components.MusicsLoading
import br.com.spectacle.app.feature.musics.presentation.components.MusicsSwitch
import br.com.spectacle.app.feature.musics.presentation.components.MusicsSwitchOption
import br.com.spectacle.app.feature.musics.presentation.components.MusicsToolbar
import br.com.spectacle.app.feature.musics.presentation.components.QueryMusics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MusicsActivity.MusicScreen(
    modifier: Modifier = Modifier
){
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
            MusicsBottomSheetContent(
                bottomSheetState = state.bottomSheetState,
                clickedConfirmMusicAction = { musicId ->
                    viewModel.clickedConfirmMusicAction(musicId)
                },
                clickedCancel = { viewModel.clickedCancelAction() },
            )
        },
        modifier = modifier
    ) {
        Box() {
            MusicsBackground(isFavoritesTab = state.isFavoritesTab)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                MusicsToolbar()

                MusicsSwitch(
                    option = if (state.isFavoritesTab) {
                        MusicsSwitchOption.Favorites
                    } else {
                        MusicsSwitchOption.Search
                    },
                    clickedOption = { option ->
                        viewModel.clickedTab(option)
                    },
                    modifier = Modifier.padding(top = 16.dp)
                )

                MusicsLoading(
                    visible = state.loading && state.isFavoritesTab,
                )

                QueryMusics(
                    visible = !state.isFavoritesTab,
                    loading = state.loading,
                    query = state.query,
                    musics = state.queryMusics,
                    updateQuery = { q -> viewModel.updateQuery(q) },
                    clickedMusic = { music -> viewModel.clickedMusic(music)},
                    modifier = Modifier.padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                FavoriteMusics(
                    visible = state.isFavoritesTab && !state.loading,
                    musics = state.favoritesMusics,
                    clickedMusic = { music -> viewModel.clickedMusic(music) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun MusicsActivity.handleAction(
    action: MusicsUiAction,
    sheetState: ModalBottomSheetState,
    scope: CoroutineScope
) {
    when (action) {
        is MusicsUiAction.CollapseBottomSheet -> scope.launch { sheetState.hide() }
        is MusicsUiAction.ExpandBottomSheet -> scope.launch { sheetState.show() }
    }
}