package id.buaja.covid19.ui.province

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.buaja.covid19.R
import id.buaja.covid19.component.CovidInformation
import id.buaja.covid19.component.CovidTopAppBar
import id.buaja.covid19.component.CovidTotalCases
import id.buaja.covid19.ui.maps.MapsActivity
import id.buaja.covid19.ui.province.model.ProvinceUiState
import id.buaja.covid19.ui.theme.CovidTheme
import id.buaja.covid19.ui.theme.DarkJungleGreen
import id.buaja.covid19.util.startActivity
import org.koin.androidx.compose.koinViewModel

class ProvinceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CovidTheme {
                ProvinceRoute(
                    onClickNavigation = {
                        startActivity(MapsActivity::class.java)
                    }
                )
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(MapsActivity::class.java)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}

@Composable
fun ProvinceRoute(
    onClickNavigation: () -> Unit,
    viewModel: ProvinceViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    ProvinceScreen(
        uiState = uiState.value,
        onClickNavigation = onClickNavigation
    )
}

@Composable
private fun ProvinceScreen(
    uiState: ProvinceUiState,
    onClickNavigation: () -> Unit
) {
    val totalProvince = when (uiState) {
        is ProvinceUiState.Success -> {
            uiState.province.provinceCases.size
        }

        else -> 0
    }

    val lastUpdate = when (uiState) {
        is ProvinceUiState.Success -> {
            uiState.province.lastUpdate
        }

        else -> ""
    }

    Scaffold(
        topBar = {
            CovidTopAppBar(
                title = stringResource(
                    id = R.string.total_province,
                    totalProvince.toString()
                ),
                subtitle = stringResource(id = R.string.last_update, lastUpdate),
                onClickNavigation = onClickNavigation
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
            ) {
                when (uiState) {
                    ProvinceUiState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is ProvinceUiState.Success -> {
                        LazyColumn(
                            contentPadding = PaddingValues(
                                all = 16.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            content = {
                                items(
                                    uiState.province.provinceCases,
                                    key = { province ->
                                        province.name
                                    }
                                ) { province ->
                                    ItemProvince(
                                        name = province.name,
                                        totalPositiveCases = province.totalPositiveCases,
                                        totalCasesRecovery = province.totalCasesRecovery,
                                        totalDeathCases = province.totalDeathCases
                                    )
                                }
                            }
                        )
                    }

                    ProvinceUiState.Empty -> {
                        CovidInformation(
                            image = R.drawable.img_empty,
                            message = stringResource(R.string.empty)
                        )
                    }

                    is ProvinceUiState.Error -> {
                        CovidInformation(
                            image = R.drawable.img_error,
                            message = uiState.message
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun ItemProvince(
    name: String,
    totalPositiveCases: Int,
    totalCasesRecovery: Int,
    totalDeathCases: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        elevation = 3.dp,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.provinsi),
                fontSize = 12.sp,
                color = MaterialTheme.colors.secondary
            )

            Text(
                text = name,
                fontSize = 16.sp,
                color = DarkJungleGreen,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier
                    .padding(top = 12.dp)
            )

            CovidTotalCases(
                totalPositiveCases = totalPositiveCases,
                totalCasesRecovery = totalCasesRecovery,
                totalDeathCases = totalDeathCases
            )
        }
    }
}
