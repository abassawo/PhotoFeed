package com.lindenlabs.photofeed

import android.app.Application
import di.initKoin
import org.koin.android.ext.koin.androidContext

class PhotoFeedApplication : Application(){

    override fun onCreate() {
        super.onCreate()


        initKoin {
            androidContext(this@PhotoFeedApplication)
            modules(viewModelModule)
        }
    }
}
