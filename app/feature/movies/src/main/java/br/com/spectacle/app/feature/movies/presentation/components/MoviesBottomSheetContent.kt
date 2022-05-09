package br.com.spectacle.app.feature.movies.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetOption
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetOptions

@Composable
internal fun MoviesBottomSheetContent(
    isFavoritesTab: Boolean,
    clickedConfirm: () -> Unit,
    clickedCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val options = remember(isFavoritesTab) {
        listOf(
            BottomSheetOption(
                if (isFavoritesTab)
                    "Remover dos Favoritos"
                else
                    "Adicionar aos Favoritos"
            )
        )
    }
    BottomSheetOptions(
        options = options,
        onClick = { clickedConfirm() },
        onClose = { clickedCancel() },
        modifier = modifier
    )
}