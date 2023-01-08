package id.buaja.covid19.domain.usecase

import id.buaja.covid19.domain.repository.NewsRepository
import id.buaja.covid19.domain.usecase.model.News
import id.buaja.covid19.util.network.ResultState
import id.buaja.covid19.util.network.asResult
import kotlinx.coroutines.flow.Flow

/**
 * Created By Julsapargi Nursam 3/28/20
 */

class GetNewsUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<ResultState<List<News>>> {
        return repository.getNews().asResult()
    }
}