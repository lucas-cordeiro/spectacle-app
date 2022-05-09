package br.com.spectacle.app.feature.musics.presentation

import br.com.spectacle.app.core.ds.arch.state.UiState
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetState
import br.com.spectacle.app.feature.musics.domain.model.Music
import br.com.spectacle.app.feature.musics.presentation.components.MusicsSwitchOption

internal data class MusicsUiState(
    val loading: Boolean = true,
    val isFavoritesTab: Boolean = true,
    val favoritesMusics: List<Music> = emptyList(),
    val queryMusics: List<Music>? = null,
    val query: String? = null,
    val bottomSheetState: BottomSheetState? = null,
    val selectedMusic: Music? = null
) : UiState
