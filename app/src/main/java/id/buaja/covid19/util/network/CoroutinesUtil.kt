/*
 * Copyright 2020 buaja, id.
 * Created By Julsapargi Nursam 3/2/20 2:33 PM
 * Last modified 3/2/20 2:33 PM
 */

package id.buaja.covid19.util.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import retrofit2.HttpException

fun <T> Flow<T>.asResult(): Flow<ResultState<T>> {
    return this
        .map<T, ResultState<T>> {
            ResultState.Success(it)
        }
        .catch { throwable ->
            emit(
                fetchError(throwable)
            )
        }
}

private fun fetchError(throwable: Throwable) = when (throwable) {
    is HttpException -> {
        when (throwable.code()) {
            404 -> {
                ResultState.Error("Not Found")
            }

            401 -> {
                ResultState.Error("Auth")
            }

            in 500..599 -> {
                ResultState.Error("Server Kami Sedang Bermasalah")
            }

            else -> {
                ResultState.Error(throwable.message.toString())
            }
        }
    }

    else -> {
        ResultState.Error(throwable.message.toString())
    }
}