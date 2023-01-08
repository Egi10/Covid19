package id.buaja.covid19.util.network

sealed class ResultState<out T> {
    data class Error(val error: String): ResultState<Nothing>()
    data class Message(val message: String): ResultState<Nothing>()
    data class Success<T>(val data: T): ResultState<T>()
}