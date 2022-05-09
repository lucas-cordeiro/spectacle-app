package br.com.spectacle.app.feature.movies.presentation

import br.com.spectacle.app.core.ds.arch.action.UiAction

internal sealed class MoviesUiAction : UiAction {
    object FinishScreen: MoviesUiAction()
    object ExpandBottomSheet: MoviesUiAction()
    object CollapseBottomSheet: MoviesUiAction()
}
