package id.buaja.covid19.util

/**
 * Copyright 2020 WOWBid Perintis Nusantara, PT.
 *
 * Created By Julsapargi Nursam 2/19/20
 */

sealed class ResultState<out T: Any> {
    data class Error(val error: String): ResultState<Nothing>()
    data class Message(val message: String): ResultState<String>()
    data class Success<out T: Any>(val data: T?): ResultState<T>()
}