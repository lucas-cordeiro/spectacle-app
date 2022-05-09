package br.com.spectacle.app.feature.movies.presentation.components

import br.com.spectacle.app.core.ds.component.switch.SwitchItem

internal sealed class MoviesSwitchOption(override val name: String): SwitchItem {
    object Favorites: MoviesSwitchOption("Favoritos")
    object Populars: MoviesSwitchOption("Populares")
}
