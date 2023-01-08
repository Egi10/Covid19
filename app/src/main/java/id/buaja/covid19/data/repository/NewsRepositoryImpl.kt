package id.buaja.covid19.data.repository

import id.buaja.covid19.BuildConfig
import id.buaja.covid19.data.repository.mapping.mapToEntityNews
import id.buaja.covid19.data.source.service.NewsApiService
import id.buaja.covid19.domain.repository.NewsRepository
import id.buaja.covid19.domain.usecase.model.News
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created By Julsapargi Nursam 3/28/20
 */

class NewsRepositoryImpl(
    private val newsApiService: NewsApiService,
    private val dispatcher: CoroutineDispatcher
) : NewsRepository {
    override fun getNews(): Flow<List<News>> {
        return flow {
            emit(
                newsApiService.getNews(
                    q = "covid19",
                    apiKey = BuildConfig.API_KEY
                ).articles.mapToEntityNews()
            )
        }.flowOn(dispatcher)
    }
}