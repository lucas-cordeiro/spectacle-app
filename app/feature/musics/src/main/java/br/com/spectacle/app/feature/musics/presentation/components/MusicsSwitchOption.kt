package br.com.spectacle.app.feature.musics.presentation.components

import br.com.spectacle.app.core.ds.component.switch.SwitchItem

internal sealed class MusicsSwitchOption(override val name: String): SwitchItem {
    object Favorites: MusicsSwitchOption("Favoritas")
    object Search: MusicsSwitchOption("Buscar")
}
