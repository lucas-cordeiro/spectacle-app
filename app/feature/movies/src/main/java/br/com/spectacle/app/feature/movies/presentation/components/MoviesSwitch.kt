package br.com.spectacle.app.feature.movies.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.component.switch.Switch
import br.com.spectacle.app.core.ds.component.switch.SwitchDefaults

@Composable
internal fun MoviesSwitch(
    option: MoviesSwitchOption,
    clickedOption: (MoviesSwitchOption) -> Unit,
    modifier: Modifier = Modifier
){
    val options = listOf(
        MoviesSwitchOption.Favorites,
        MoviesSwitchOption.Populars
    )
    
    Switch(
        value = option,
        options = options,
        shape = RoundedCornerShape(16.dp),
        onOptionSelected = clickedOption,
        padding = SwitchDefaults.switchPadding(
            backgroundPadding = 8.dp,
            contentPadding = 12.dp,
            shadowPadding = 0.dp
        ),
        modifier = modifier
    )
}