package com.jeffrey.debuggy.utils

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import com.jeffrey.debuggy.BuildConfig
import com.jeffrey.debuggy.R
import java.net.NetworkInterface
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun ip(): String {
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
            } catch (ex: Exception) {
                Log.e("IP Address", ex.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return if (ip.toString().length > 13) Constants.UNDEFINED_TEXT else ip.toString()
    }

    fun restartApp(context: Context) {
        val packageManager = context.packageManager
        val intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
        val componentName = intent.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        context.startActivity(mainIntent)
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
        val intentBuilder = CustomTabsIntent.Builder()
        val customTabsIntent = intentBuilder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    fun getCurrentReleaseUrl(): String {
        return Constants.URL_CURRENT_RELEASE +
                BuildConfig.VERSION_NAME.substring(
                    0,
                    3
                )
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