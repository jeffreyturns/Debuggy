package com.jeffrey.debuggy

import android.app.Application
import com.jeffrey.debuggy.data.notification.NotificationHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.initModule() {
    startKoin {
        androidContext(this@initModule)
        modules(listOf(appModule))
    }
}

private val appModule = module {
    single { NotificationHelper(androidContext()) }
}
