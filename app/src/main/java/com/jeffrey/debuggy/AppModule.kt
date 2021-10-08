package com.jeffrey.debuggy

import android.app.Application
import com.jeffrey.debuggy.di.helperModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

open class AppModule : Application() {

    fun Application.initModule() {
        startKoin {
            androidContext(this@initModule)
            modules(listOf(modules))
        }
    }

    private val modules = module {
        helperModule
    }
}