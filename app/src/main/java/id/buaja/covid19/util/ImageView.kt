package id.buaja.covid19.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import id.buaja.covid19.MyApp
import id.buaja.covid19.R

/**
 * Created By Julsapargi Nursam 3/28/20
 */
 
fun ImageView.loadImage(url: String) {
    MyApp.context?.let {
        Glide.with(it)
            .load(url)
            .placeholder(R.drawable.noimage_found)
            .into(this)
    }
}