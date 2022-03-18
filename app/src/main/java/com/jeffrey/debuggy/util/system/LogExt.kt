package com.jeffrey.debuggy.util.system

import android.util.Log
import com.jeffrey.debuggy.BuildConfig

fun Any.writeLog(level: LogLevel, message: String, tr: Throwable? = null) {

    val tag = this::class.java.simpleName

    if (BuildConfig.DEBUG) {
        when (level) {
            LogLevel.WTF -> Log.wtf(tag, message, tr)
            LogLevel.DEBUG -> Log.d(tag, message, tr)
            LogLevel.ERROR -> Log.e(tag, message, tr)
            LogLevel.INFO -> Log.i(tag, message, tr)
            LogLevel.VERBOSE -> Log.v(tag, message, tr)
            LogLevel.WARN -> Log.w(tag, message, tr)
        }
    }
}

enum class LogLevel {
    WTF, DEBUG, ERROR, INFO, VERBOSE, WARN
}


internal fun String.toCamelSnake(): String {
    val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()
    return camelRegex.replace(this) {
        "_${it.value}"
    }.uppercase()
}


val Any.CREATE_CAMEL_TAG: String
    get() {
        val tag = javaClass.simpleName.toCamelSnake()
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

