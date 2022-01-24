package com.jeffrey.debuggy.data.preference

import android.content.Context
import android.content.SharedPreferences

open class PreferencesHelper(context: Context) {

    private val keys: PreferenceKeys = PreferenceKeys

    private val preferences: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    var adbEnabled: Boolean
        get() =
            preferences.getBoolean(keys.KEY_ADB_ENABLED, keys.DEF_VAL_ADB_ENABLED)
        set(adbEnabled) =
            preferences.edit().putBoolean(keys.KEY_ADB_ENABLED, adbEnabled).apply()

    var port: String
        get() =
            preferences.getString(keys.KEY_CUSTOM_PORT, keys.DEF_VAL_CUSTOM_PORT).toString()
        set(port) =
            preferences.edit().putString(keys.KEY_CUSTOM_PORT, port).apply()

    var runAfterBoot: Boolean
        get() =
            preferences.getBoolean(keys.KEY_ADB_AFTER_BOOT, keys.DEF_VAL_ADB_AFTER_BOOT)
        set(runAfterBoot) =
            preferences.edit().putBoolean(keys.KEY_ADB_AFTER_BOOT, runAfterBoot).apply()

    var appTheme: Int
        get() =
            preferences.getInt(keys.KEY_APP_THEME, keys.DEF_VAL_APP_THEME)
        set(theme) =
            preferences.edit().putInt(keys.KEY_APP_THEME, theme).apply()

    var useSystemColors: Boolean
        get() =
            preferences.getBoolean(keys.KEY_USE_SYSTEM_COLORS, keys.DEF_VAL_USE_SYSTEM_COLORS)
        set(useSystemColors) =
            preferences.edit().putBoolean(keys.KEY_USE_SYSTEM_COLORS, useSystemColors).apply()

    var authenticationEnabled: Boolean
        get() =
            preferences.getBoolean(
                keys.KEY_AUTHENTICATION_ENABLED,
                keys.DEF_VAL_AUTHENTICATION_ENABLED
            )
        set(authenticationEnabled) =
            preferences.edit().putBoolean(keys.KEY_AUTHENTICATION_ENABLED, authenticationEnabled)
                .apply()

    var adbAfterWhile: Int
        get() =
            preferences.getInt(keys.KEY_ADB_AFTER_WHILE, keys.DEF_VAL_ADB_AFTER_WHILE)
        set(adbAfterWhile) =
            preferences.edit().putInt(keys.KEY_ADB_AFTER_WHILE, adbAfterWhile).apply()
}
