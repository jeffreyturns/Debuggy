package com.jeffrey.debuggy.util.extensions

import android.util.Log
import com.jeffrey.debuggy.BuildConfig

fun Any.writeLog(level: Level, message: String, tr: Throwable? = null) {

    val tag = this::class.java.simpleName

    if (BuildConfig.DEBUG) {
        when (level) {
            Level.WTF -> Log.wtf(tag, message, tr)
            Level.DEBUG -> Log.d(tag, message, tr)
            Level.ERROR -> Log.e(tag, message, tr)
            Level.INFO -> Log.i(tag, message, tr)
            Level.VERBOSE -> Log.v(tag, message, tr)
            Level.WARN -> Log.w(tag, message, tr)
        }
    }
}

enum class Level {
    WTF, DEBUG, ERROR, INFO, VERBOSE, WARN
}