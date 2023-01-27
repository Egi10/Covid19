package id.buaja.covid19.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import id.buaja.covid19.ui.theme.BlueGrey

@Composable
fun CovidTopAppBar(
    title: String,
    onClickNavigation: () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String = "",
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = BlueGrey,
        title = {
            Column {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color.White
                )

                if (subtitle.isNotEmpty()) {
                    Text(
                        text = subtitle,
                        fontSize = 12.sp
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onClickNavigation) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    )
}