package com.jeffrey.debuggy.di

import com.jeffrey.debuggy.data.notification.NotificationHelper
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val helperModule = module {
    single { NotificationHelper(androidContext()) }
    single { PreferencesHelper(androidContext()) }
}