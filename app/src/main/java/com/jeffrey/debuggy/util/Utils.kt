package com.jeffrey.debuggy.util

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatDelegate
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsIntent.COLOR_SCHEME_SYSTEM
import com.jeffrey.debuggy.BuildConfig
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.util.system.LogLevel
import com.jeffrey.debuggy.util.system.getAttr
import com.jeffrey.debuggy.util.system.writeLog
import java.net.NetworkInterface
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    val deviceIpv6Address: String
        get() {
            var ip: String? = Constants.UNDEFINED_TEXT
            try {
                try {
                    val en = NetworkInterface
                        .getNetworkInterfaces()
                    while (en.hasMoreElements()) {
                        val networkInterface = en.nextElement()
                        val addressEnumeration = networkInterface.inetAddresses
                        while (addressEnumeration.hasMoreElements()) {
                            val inetAddress = addressEnumeration.nextElement()
                            if (!inetAddress.isLoopbackAddress) {
                                ip = inetAddress.hostAddress
                            }
                        }
                    }
                } catch (e: Exception) {
                    writeLog(LogLevel.ERROR, "Cannot get IP address using NetworkInterface", e)
                }
            } catch (e: Exception) {
                writeLog(LogLevel.ERROR, "Cannot get IP address", e)
            }
            return if (ip.toString().length > 15) Constants.UNDEFINED_TEXT else ip.toString()
        }

    fun getADBOverUSBStatus(context: Context): Boolean {
        return Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.ADB_ENABLED,
            0
        ) == 1
    }

    fun isDarkMode(context: Context, theme: Int): Boolean {

        var isDark = false

        when (theme) {
            1 -> isDark = false
            2 -> isDark = true
            0 -> {
                val nightModeFlags: Int = context.resources.configuration.uiMode and
                        Configuration.UI_MODE_NIGHT_MASK
                when (nightModeFlags) {
                    Configuration.UI_MODE_NIGHT_YES -> isDark = true
                    Configuration.UI_MODE_NIGHT_NO -> isDark = false
                    Configuration.UI_MODE_NIGHT_UNDEFINED -> isDark = false
                }
            }
        }
        return isDark
    }

    fun openCustomTab(
        context: Context,
        url: String
    ) {
        val intentBuilder = CustomTabsIntent.Builder().apply {
            setShowTitle(true)

            val scheme = CustomTabColorSchemeParams.Builder().apply {
                setNavigationBarColor(context.getAttr(com.google.android.material.R.attr.colorSurface))
                setToolbarColor(context.getAttr(com.google.android.material.R.attr.colorSurface))
            }.build()
            setDefaultColorSchemeParams(scheme)
        }.build()

        intentBuilder.launchUrl(context, Uri.parse(url))
    }

    fun getCurrentReleaseUrl(): String {
        return Constants.URL_CURRENT_RELEASE +
                BuildConfig.VERSION_NAME.substring(
                    0,
                    3
                )
    }

    fun applyTheme(theme: Int) {
        when (theme) {
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    fun getBuildTime(context: Context): String {
        return context.getString(
            R.string.version_concat_with_buildtime, BuildConfig.VERSION_NAME, SimpleDateFormat(
                Constants.BUILDTIME_PATTERN,
                Locale.getDefault()
            ).format(BuildConfig.BUILD_TIME)
        )
    }
}
