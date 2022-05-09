package br.com.spectacle.app.feature.musics.presentation

import br.com.spectacle.app.core.ds.arch.action.UiAction

sealed class MusicsUiAction: UiAction {
    object CollapseBottomSheet: MusicsUiAction()
    object ExpandBottomSheet: MusicsUiAction()
}