package id.buaja.covid19.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created By Julsapargi Nursam 3/27/20
 */
 
fun String.dateFormat() : String? {
    val inputFormat = SimpleDateFormat("ssssssssssSSS", Locale.getDefault())
    val myDate = inputFormat.parse(this)
    val locale = Locale("id")
    val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy hh:mm:ss", locale)
    var dateFormat = ""
    myDate?.let {
        val myDateAsString = outputFormat.format(it)
        dateFormat = myDateAsString
    }

    return dateFormat
}