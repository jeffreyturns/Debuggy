package com.jeffrey.debuggy.util.extensions

import android.util.Log

fun Any.writeLog(level: Level, message: String, tr: Throwable? = null) {
    when (level) {
        Level.WTF -> Log.wtf(this::class.java.simpleName, message, tr)
        Level.DEBUG -> Log.d(this::class.java.simpleName, message, tr)
        Level.ERROR -> Log.e(this::class.java.simpleName, message, tr)
        Level.INFO -> Log.i(this::class.java.simpleName, message, tr)
        Level.VERBOSE -> Log.v(this::class.java.simpleName, message, tr)
        Level.WARN -> Log.w(this::class.java.simpleName, message, tr)
    }
}

enum class Level {
    WTF, DEBUG, ERROR, INFO, VERBOSE, WARN
}