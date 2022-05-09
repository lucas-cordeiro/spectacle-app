package br.com.spectacle.app.core.ds.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.spectacle.app.core.ds.R

private val fontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.tt_commons_thin,
            weight = FontWeight.Thin,
            style = FontStyle.Normal,
        ),
        Font(
            resId = R.font.tt_commons_thin_italic,
            weight = FontWeight.Thin,
            style = FontStyle.Italic,
        ),
        Font(
            resId = R.font.tt_commons_extralight,
            weight = FontWeight.ExtraLight,
            style = FontStyle.Normal,
        ),
        Font(
            resId = R.font.tt_commons_extralight_italic,
            weight = FontWeight.ExtraLight,
            style = FontStyle.Italic,
        ),
        Font(
            resId = R.font.tt_commons_light,
            weight = FontWeight.Light,
            style = FontStyle.Normal,
        ),
        Font(
            resId = R.font.tt_commons_light_italic,
            weight = FontWeight.Light,
            style = FontStyle.Italic,
        ),
        Font(
            resId = R.font.tt_commons_regular,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.tt_commons_italic,
            weight = FontWeight.Normal,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.tt_commons_medium,
            weight = FontWeight.Medium,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.tt_commons_medium_italic,
            weight = FontWeight.Medium,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.tt_commons_semibold,
            weight = FontWeight.SemiBold,
            style = FontStyle.Normal,
        ),
        Font(
            resId = R.font.tt_commons_semibold_italic,
            weight = FontWeight.SemiBold,
            style = FontStyle.Italic,
        ),
        Font(
            resId = R.font.tt_commons_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal,
        ),
        Font(
            resId = R.font.tt_commons_bold_italic,
            weight = FontWeight.Bold,
            style = FontStyle.Italic,
        ),
        Font(
            resId = R.font.tt_commons_extrabold,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Normal,
        ),
        Font(
            resId = R.font.tt_commons_extrabold_italic,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Italic,
        ),
        Font(
            resId = R.font.tt_commons_black,
            weight = FontWeight.Black,
            style = FontStyle.Normal,
        ),
        Font(
            resId = R.font.tt_commons_black_italic,
            weight = FontWeight.Black,
            style = FontStyle.Italic,
        )
    )
)

// Set of Material typography styles to start with
val Typography = Typography (
    fontFamily,
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    h6 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    h5 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    h4 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 23.sp
    ),
    h1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    )
    /* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/
)

@Composable
fun Typography.textField() = TextStyle(
    color = MaterialTheme.colors.onSurface,
    fontSize = 18.sp,
    fontWeight = FontWeight.Medium
)
