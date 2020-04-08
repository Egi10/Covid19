/*
 * Copyright 2020 buaja, id.
 * Created By Julsapargi Nursam 3/2/20 2:33 PM
 * Last modified 3/2/20 2:33 PM
 */

package id.buaja.covid19.util

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

suspend fun <T : Any> fetchState(call: suspend () -> T): ResultState<T> {
    return try {
        ResultState.Success(call.invoke())
    } catch (e: Throwable) {
        when (e) {
            //No Network
            is IOException -> {
                ResultState.Error("No signal")
            }

            is HttpException -> {
                when(e.code()) {
                    404 -> {
                        ResultState.Error("Not Found")
                    }

                    else -> {
                        ResultState.Error("Error")
                    }
                }
            }

            is SocketTimeoutException -> {
                ResultState.Error("Periksa Jaringan Kamu")
            }

            else -> {
                ResultState.Error("Disini")
            }
        }
    }
}