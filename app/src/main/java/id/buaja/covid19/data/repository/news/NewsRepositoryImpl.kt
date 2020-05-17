package id.buaja.covid19.data.repository.news

import id.buaja.covid19.data.remote.NewsRemoteDataSource
import id.buaja.covid19.util.ResultState

/**
 * Created By Julsapargi Nursam 3/28/20
 */

class NewsRepositoryImpl(private val newsRemoteDataSource: NewsRemoteDataSource) : NewsRepository {
    override suspend fun getNews(): ResultState<Any> {
        return newsRemoteDataSource.getNews()
    }
}