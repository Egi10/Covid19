package id.buaja.covid19.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.buaja.covid19.MyApp
import id.buaja.covid19.R

/**
 * Created By Julsapargi Nursam 3/28/20
 */
 
fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.noimage_found)
        .transform(CenterCrop(), RoundedCorners(20))
        .into(this)
}