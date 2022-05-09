package br.com.spectacle.app.feature.musics.presentation.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetOptions
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetProgress
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetState

@Composable
internal fun MusicsBottomSheetContent(
    bottomSheetState: BottomSheetState?,
    clickedConfirmMusicAction: (Long) -> Unit,
    clickedCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Crossfade(targetState = bottomSheetState) { state ->
            when(state){
                is BottomSheetState.ItemAction -> {
                    BottomSheetOptions(
                        options = state.options,
                        onClick = { clickedConfirmMusicAction(state.itemId) },
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