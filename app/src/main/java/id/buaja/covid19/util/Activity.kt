package id.buaja.covid19.util

import android.app.Activity
import android.content.Intent

/**
 * Created By Julsapargi Nursam 3/27/20
 */

fun<T> Activity.startActivity(activity: Class<T>) {
    val intent = Intent(this, activity)
    startActivity(intent)
    finish()
}