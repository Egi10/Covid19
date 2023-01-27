package id.buaja.covid19.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.buaja.covid19.ui.theme.CovidTheme
import id.buaja.covid19.ui.theme.DarkJungleGreen
import id.buaja.covid19.R

@Composable
fun CovidInformation(
    image: Int,
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 24.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = message,
            modifier = Modifier
                .size(150.dp)
        )

        Spacer(
            modifier = Modifier
                .padding(
                    top = 16.dp
                )
        )

        Text(
            text = message,
            fontSize = 16.sp,
            color = DarkJungleGreen,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CovidInformationPreview() {
    CovidTheme {
        CovidInformation(
            image = R.drawable.img_empty,
            message = "Kami mengalami masalah untuk Sumber Data, jadi untuk sementara feature ini tidak bisa diakses"
        )
    }
}