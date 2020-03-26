package id.buaja.covid19

import android.app.Application
import android.content.Context
import id.buaja.covid19.di.Modules
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
}