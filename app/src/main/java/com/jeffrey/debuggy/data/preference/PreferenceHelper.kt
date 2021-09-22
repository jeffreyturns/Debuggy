package com.jeffrey.debuggy.data.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object PreferenceHelper {

    private val keys: PreferenceKeys = PreferenceKeys

    private inline fun SharedPreferences.edit(task: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        task(editor)
        editor.apply()
    }

    @Throws(UnsupportedOperationException::class)
    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            else -> throw UnsupportedOperationException("No implementation")
        }
    }

    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class)
    operator fun <T> SharedPreferences.get(key: String, defaultValue: T? = null): T {
        return when (defaultValue) {
            is String -> getString(key, defaultValue as? String) as T
            is Int -> getInt(key, defaultValue as? Int ?: -1) as T
            is Boolean -> getBoolean(key, defaultValue as? Boolean ?: false) as T
            is Float -> getFloat(key, defaultValue as? Float ?: -1f) as T
            is Long -> getLong(key, defaultValue as? Long ?: -1) as T
            else -> throw UnsupportedOperationException("No implementation")
        }
    }

    private operator fun invoke(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    fun adb(context: Context): Boolean {
        return this(context)[keys.KEY_ADB_ENABLED, false]
    }

    fun adb(context: Context, value: Boolean) {
        this(context)[keys.KEY_ADB_ENABLED] = value
    }

    fun port(context: Context): String {
        return this(context)[keys.KEY_CUSTOM_PORT, "5555"]
    }

    fun port(context: Context, value: String) {
        this(context)[keys.KEY_CUSTOM_PORT] = value
    }

    fun runAfterBoot(context: Context): Boolean {
        return this(context)[keys.KEY_ADB_AFTER_BOOT, false]
    }

    fun runAfterBoot(context: Context, value: Boolean) {
        this(context)[keys.KEY_ADB_AFTER_BOOT] = value
    }

    fun theme(context: Context): Int {
        return this(context)[keys.KEY_APP_THEME, 0]
    }

    fun theme(context: Context, value: Int) {
        this(context)[keys.KEY_APP_THEME] = value
    }

    fun useSystemColors(context: Context): Boolean {
        return this(context)[keys.KEY_USE_SYSTEM_COLORS, false]
    }

    fun useSystemColors(context: Context, value: Boolean) {
        this(context)[keys.KEY_USE_SYSTEM_COLORS] = value
    }
}