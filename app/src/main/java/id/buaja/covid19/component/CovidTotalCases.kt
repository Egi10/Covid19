package id.buaja.covid19.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import id.buaja.covid19.R
import id.buaja.covid19.ui.theme.CovidTheme
import id.buaja.covid19.ui.theme.DarkJungleGreen

@Composable
fun CovidTotalCases(
    totalPositiveCases: Int,
    totalCasesRecovery: Int,
    totalDeathCases: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        ItemCases(
            title = stringResource(id = R.string.confirmed),
            cases = totalPositiveCases,
            modifier = Modifier
                .weight(1f)
        )

        ItemCases(
            title = stringResource(id = R.string.recovered),
            cases = totalCasesRecovery,
            modifier = Modifier
                .weight(1f)
        )

        ItemCases(
            title = stringResource(id = R.string.deaths),
            cases = totalDeathCases,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
private fun ItemCases(
    title: String,
    cases: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = MaterialTheme.colors.secondary
        )

        Text(
            text = cases.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DarkJungleGreen
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun TotalCasesPreview() {
    CovidTheme {
        CovidTotalCases(
            totalPositiveCases = 1141024,
            totalCasesRecovery = 1121197,
            totalDeathCases = 12497
        )
    }
}