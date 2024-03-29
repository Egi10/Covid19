package id.buaja.covid19

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import id.buaja.covid19.di.Modules
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Copyright 2020 WOWBid Perintis, PT.
 *
 * Created By Julsapargi Nursam 2/17/20
 */

class MyApp : Application() {
    override fun onCreate() {
        instance = this
        super.onCreate()

        //Calligraphy
        ViewPump.init(ViewPump.builder()
            .addInterceptor(CalligraphyInterceptor(
                CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/roboto_regular.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build()))
            .build())

        //Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            Modules()
        }
    }

    companion object {
        private var instance: MyApp? = null

        val context: Context?
            get() = instance
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}