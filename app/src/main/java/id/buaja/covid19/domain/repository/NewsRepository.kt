package id.buaja.covid19.domain.repository

import id.buaja.covid19.domain.usecase.model.News
import kotlinx.coroutines.flow.Flow

/**
 * Created By Julsapargi Nursam 3/28/20
 */

interface NewsRepository {
    fun getNews(): Flow<List<News>>
}