package br.com.spectacle.app.feature.musics.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.component.switch.Switch
import br.com.spectacle.app.core.ds.component.switch.SwitchDefaults

@Composable
internal fun MusicsSwitch(
    option: MusicsSwitchOption,
    clickedOption: (MusicsSwitchOption) -> Unit,
    modifier: Modifier = Modifier
){
    val options = listOf(
        MusicsSwitchOption.Favorites,
        MusicsSwitchOption.Search
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