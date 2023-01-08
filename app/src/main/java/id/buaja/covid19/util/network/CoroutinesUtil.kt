/*
 * Copyright 2020 buaja, id.
 * Created By Julsapargi Nursam 3/2/20 2:33 PM
 * Last modified 3/2/20 2:33 PM
 */

package id.buaja.covid19.util.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.IOException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException

fun <T> Flow<T>.asResult(): Flow<ResultState<T>> {
    return this
        .map<T, ResultState<T>> {
            ResultState.Success(it)
        }
}
suspend fun <T : Any> fetchState(call: suspend () -> ResultState<T>): ResultState<T> {
    return try {
        call.invoke()
    } catch (e: IOException) {
        ResultState.Message("Periksa Jaringan Anda")
    } catch (e: ConnectException) {
        ResultState.Message(e.message.toString())
    } catch (e: Exception) {
        ResultState.Message(e.message.toString())
    } catch (e: Throwable) {
        ResultState.Message(e.message.toString())
    } catch (e: SocketTimeoutException) {
        ResultState.Message(e.message.toString())
    }
}

suspend fun <T : Any> wrapper(call: suspend () -> Response<T>): ResultState<T> {
    return if (call.invoke().isSuccessful) {
        call.invoke().body()?.let {
            ResultState.Success(it)
        } ?: run {
            ResultState.Message("Data Null")
        }
    } else {
        fetchError(call.invoke())
    }
}

fun <T : Any> fetchError(response: Response<T>): ResultState<T> {
    return when (response.code()) {
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
            ResultState.Error(response.message())
        }
    }
}