package com.jeffrey.debuggy.data.preference

import android.content.Context
import android.content.SharedPreferences

open class PreferencesHelper(context: Context) {

    private val keys: PreferenceKeys = PreferenceKeys

    private val bufferPref: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    var adbEnabled: Boolean
        get() =
            bufferPref.getBoolean(keys.KEY_ADB_ENABLED, keys.DEF_VAL_ADB_ENABLED)
        set(adbEnabled) =
            bufferPref.edit().putBoolean(keys.KEY_ADB_ENABLED, adbEnabled).apply()

    var port: String
        get() =
            bufferPref.getString(keys.KEY_CUSTOM_PORT, keys.DEF_VAL_CUSTOM_PORT).toString()
        set(port) =
            bufferPref.edit().putString(keys.KEY_CUSTOM_PORT, port).apply()

    var runAfterBoot: Boolean
        get() =
            bufferPref.getBoolean(keys.KEY_ADB_AFTER_BOOT, keys.DEF_VAL_ADB_AFTER_BOOT)
        set(runAfterBoot) =
            bufferPref.edit().putBoolean(keys.KEY_ADB_AFTER_BOOT, runAfterBoot).apply()

    var appTheme: Int
        get() =
            bufferPref.getInt(keys.KEY_APP_THEME, keys.DEF_VAL_APP_THEME)
        set(theme) =
            bufferPref.edit().putInt(keys.KEY_APP_THEME, theme).apply()

    var useSystemColors: Boolean
        get() =
            bufferPref.getBoolean(keys.KEY_USE_SYSTEM_COLORS, keys.DEF_VAL_USE_SYSTEM_COLORS)
        set(useSystemColors) =
            bufferPref.edit().putBoolean(keys.KEY_USE_SYSTEM_COLORS, useSystemColors).apply()
}
