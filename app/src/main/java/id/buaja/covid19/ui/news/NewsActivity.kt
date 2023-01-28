package id.buaja.covid19.ui.news

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import id.buaja.covid19.R
import id.buaja.covid19.component.CovidInformation
import id.buaja.covid19.component.CovidTopAppBar
import id.buaja.covid19.domain.usecase.model.News
import id.buaja.covid19.ui.maps.MapsActivity
import id.buaja.covid19.ui.news.model.NewsUiState
import id.buaja.covid19.ui.theme.CovidTheme
import id.buaja.covid19.util.dateFormatUtc
import id.buaja.covid19.util.startActivity
import org.koin.androidx.compose.koinViewModel


class NewsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CovidTheme {
                NewsRoute(
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
fun NewsRoute(
    onClickNavigation: () -> Unit,
    viewModel: NewsViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    NewsScreen(
        uiState = uiState.value,
        onClickNavigation = onClickNavigation
    )
}

@Composable
private fun NewsScreen(
    uiState: NewsUiState,
    onClickNavigation: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CovidTopAppBar(
                title = stringResource(id = R.string.title_news),
                onClickNavigation = onClickNavigation
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (uiState) {
                    NewsUiState.Loading -> {
                        Spacer(
                            modifier = Modifier
                                .padding(
                                    top = 16.dp
                                )
                        )

                        CircularProgressIndicator()
                    }

                    is NewsUiState.Success -> {
                        LazyColumn(
                            contentPadding = PaddingValues(
                                top = 16.dp,
                                bottom = 16.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            content = {
                                itemsIndexed(uiState.news) { index, item ->
                                    ItemNews(
                                        index = index,
                                        item = item,
                                        list = uiState.news
                                    )
                                }
                            }
                        )
                    }

                    NewsUiState.Empty -> {
                        CovidInformation(
                            image = R.drawable.img_empty,
                            message = stringResource(R.string.empty),
                            modifier = Modifier
                                .padding(all = 16.dp)
                        )
                    }

                    is NewsUiState.Error -> {
                        CovidInformation(
                            image = R.drawable.img_error,
                            message = uiState.message,
                            modifier = Modifier
                                .padding(all = 16.dp)
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun ItemNews(
    index: Int,
    item: News,
    list: List<News>
) {
    if (index == 0) {
        ItemNewsHeader(
            image = item.image,
            title = item.title,
            name = item.name,
            publishedAt = item.publishedAt,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
        )

        NewsDivider()
    } else {
        ItemNewsFooter(
            image = item.image,
            title = item.title,
            name = item.name,
            publishedAt = item.publishedAt,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
        )

        if (index < list.lastIndex) {
            NewsDivider()
        }
    }
}

@Composable
private fun ItemNewsHeader(
    image: String,
    title: String,
    name: String,
    publishedAt: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = image,
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(
                    shape = RoundedCornerShape(8.dp)
                ),
            placeholder = painterResource(id = R.drawable.noimage_found),
            contentScale = ContentScale.Crop
        )

        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = name,
            fontSize = 12.sp,
            color = MaterialTheme.colors.secondary
        )

        Text(
            text = publishedAt.dateFormatUtc() ?: "-",
            fontSize = 12.sp,
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
private fun ItemNewsFooter(
    image: String,
    title: String,
    name: String,
    publishedAt: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = image,
            contentDescription = title,
            modifier = Modifier
                .size(55.dp)
                .clip(
                    shape = RoundedCornerShape(8.dp)
                ),
            placeholder = painterResource(id = R.drawable.noimage_found),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp
                )
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = name,
                fontSize = 12.sp,
                color = MaterialTheme.colors.secondary
            )

            Text(
                text = publishedAt.dateFormatUtc() ?: "-",
                fontSize = 12.sp,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

@Composable
private fun NewsDivider() {
    Spacer(
        modifier = Modifier
            .padding(
                top = 16.dp
            )
    )

    Divider()
}