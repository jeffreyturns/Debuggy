package com.jeffrey.debuggy.util

import android.content.Context
import androidx.core.app.NotificationCompat
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.notification.*
import com.jeffrey.debuggy.util.system.LogLevel
import com.jeffrey.debuggy.util.system.writeLog
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader

object RootUtils {

    val daemonStatus: String
        get() {
            val exec = Runtime.getRuntime().exec("su")
            try {
                val dataOutputStream = DataOutputStream(exec.outputStream)
                val reader = BufferedReader(
                    InputStreamReader(exec.inputStream)
                )
                val buffer = CharArray(4096)
                val output = StringBuffer()
                var read: Int

                dataOutputStream.apply {
                    writeBytes("getprop init.svc.adbd" + "\n")
                    flush()
                    close()
                }
                while (reader.read(buffer).also { read = it } > 0) {
                    output.append(buffer, 0, read)
                }
                reader.close()
                exec.waitFor()
                return output.toString()
            } catch (e: IOException) {
                writeLog(LogLevel.ERROR, "cannot get Daemon status cause IOException", e)
                return ""
            } catch (e: InterruptedException) {
                writeLog(LogLevel.ERROR, "cannot get Daemon status cause InterruptedException", e)
                return ""
            }
        }

    fun enableTcp(notificationHelper: NotificationHelper, context: Context, port: String) {

        notificationHelper.show(
            Notifications.ID_CREATE_ENABLED_ADB,
            OngoingNotification(
                Notifications.CHANNEL_ENABLE_ADB,
                context.resources.getString(R.string.title_adb_enabled),
                if (Utils.deviceIpv6Address != Constants.UNDEFINED_TEXT) context.resources.getString(
                    R.string.content_adb_enabled,
                    Utils.deviceIpv6Address
                ) else context.getString(R.string.message_ip_unable_determine),
                NotificationCompat.PRIORITY_LOW,
                false,
                listOf(
                    NotificationAction(
                        R.drawable.ic_wifi_off_24dp,
                        context.resources.getString(R.string.message_disable_tcp),
                        NotificationReceiver.disableTCP(context)
                    )
                )
            )
        )
        try {
            callCommandWithADBDRestart("setprop service.adb.tcp.port $port")
        } catch (e: Exception) {
            writeLog(LogLevel.ERROR, "cannot enable TCP service", e)
        }
    }

    fun disableTcp(notificationHelper: NotificationHelper) {
        notificationHelper.cancel(Notifications.ID_CREATE_ENABLED_ADB)
        try {
            callCommandWithADBDRestart("setprop service.adb.tcp.port -1")
        } catch (e: Exception) {
            writeLog(LogLevel.ERROR, "cannot disable TCP service", e)
        }
    }

    @Throws(Exception::class)
    fun callCommandWithADBDRestart(str: String) {
        val exec = Runtime.getRuntime().exec("su")
        DataOutputStream(exec.outputStream).apply {
            writeBytes("$str\n")
            writeBytes("stop adbd\n")
            writeBytes("start adbd\n")
            writeBytes("exit\n")
            flush()
            close()
        }
        exec.waitFor()
    }

    fun softRestart() {
        try {
            val exec = Runtime.getRuntime().exec("su")
            DataOutputStream(exec.outputStream).apply {
                writeBytes("killall zygote\n")
                flush()
                writeBytes("exit\n")
                flush()
            }
            exec.waitFor()
        } catch (e: IOException) {
            writeLog(LogLevel.ERROR, "Soft restart IOException", e)
        } catch (e: InterruptedException) {
            writeLog(LogLevel.ERROR, "Soft restart interrupted exception", e)
        } catch (e: Exception) {
            writeLog(LogLevel.ERROR, "Soft restart exception", e)
        }
    }

    fun changeADBOverUSBVar(enable: Boolean) {
        try {
            callCommandWithADBDRestart("settings put global adb_enabled ${if (enable) 1 else 0}")
        } catch (e: Exception) {
            writeLog(LogLevel.ERROR, "ADB variable changing failed", e)
        }
    }
}