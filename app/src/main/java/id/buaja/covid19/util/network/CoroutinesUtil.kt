/*
 * Copyright 2020 buaja, id.
 * Created By Julsapargi Nursam 3/2/20 2:33 PM
 * Last modified 3/2/20 2:33 PM
 */

package id.buaja.covid19.util.network

import okio.IOException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException

suspend fun <T : Any> fetchState(call: suspend () -> ResultState<T>): ResultState<T> {
    return try {
        call.invoke()
    } catch (e: IOException) {
        ResultState.Error("Periksa Jaringan Anda")
    } catch (e: ConnectException) {
        ResultState.Error(e.message.toString())
    } catch (e: Exception) {
        ResultState.Error(e.message.toString())
    } catch (e: Throwable) {
        ResultState.Error(e.message.toString())
    } catch (e: SocketTimeoutException) {
        ResultState.Error(e.message.toString())
    }
}

suspend fun <T : Any> wrapper(call: suspend () -> Response<T>): ResultState<T> {
    return if (call.invoke().isSuccessful) {
        ResultState.Success(call.invoke().body())
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