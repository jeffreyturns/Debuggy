package com.jeffrey.debuggy

import android.app.Application
import com.jeffrey.debuggy.di.modulesList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class AppModule : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppModule)
            modules(modulesList)
        }
    }
}