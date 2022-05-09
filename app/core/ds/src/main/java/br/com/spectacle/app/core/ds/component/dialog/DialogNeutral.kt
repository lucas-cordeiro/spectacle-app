package br.com.spectacle.app.core.ds.component.dialog

import androidx.compose.material.Text
import androidx.compose.foundation.clickable
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun DialogNeutral(
    message: @Composable() () -> Unit,
    onDismissRequest: () -> Unit,
    onNeutralButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    button: String? = null,
    title: (@Composable () -> Unit)? = null,
) {
    AlertDialog(
        shape = RoundedCornerShape(8.dp),
        onDismissRequest = onDismissRequest,
        title = title,
        text = message,
        properties = properties,
        buttons = {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val buttonRef = createRef()
                Text(
                    text = button.orEmpty(),
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(onClick = onNeutralButtonClick)
                        .padding(16.dp)
                        .constrainAs(buttonRef) {
                            end.linkTo(parent.end, 8.dp)
                            bottom.linkTo(parent.bottom, 8.dp)
                        }
                )
            }
        },
        modifier = modifier.padding(16.dp)
    )
}

@Composable
fun DialogNeutral(
    message: String,
    onDismissRequest: () -> Unit,
    onNeutralButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    title: String = "Atenção",
    button: String = "Ok",
) {
    DialogNeutral(
        onDismissRequest = onDismissRequest,
        onNeutralButtonClick = onNeutralButtonClick,
        title = {
            if (!title.isNullOrBlank()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
        },
        message = {
            Text(
                text = message,
                style = MaterialTheme.typography.body1,
            )
        },
        properties = properties,
        button = button,
        modifier = modifier.padding(16.dp)
    )
}