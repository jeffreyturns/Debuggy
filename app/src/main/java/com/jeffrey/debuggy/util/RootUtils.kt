package com.jeffrey.debuggy.util

import android.content.Context
import android.content.Intent
import com.jeffrey.debuggy.data.notification.NotificationHelper
import com.jeffrey.debuggy.ui.main.MainActivity
import com.jeffrey.debuggy.util.extensions.Level
import com.jeffrey.debuggy.util.extensions.writeLog
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

                dataOutputStream.writeBytes("getprop init.svc.adbd" + "\n")
                dataOutputStream.flush()
                dataOutputStream.close()
                while (reader.read(buffer).also { read = it } > 0) {
                    output.append(buffer, 0, read)
                }
                reader.close()
                exec.waitFor()
                return output.toString()
            } catch (e: IOException) {
                writeLog(Level.ERROR, "cannot get Daemon status cause IOException", e)
                return ""
            } catch (e: InterruptedException) {
                writeLog(Level.ERROR, "cannot get Daemon status cause InterruptedException", e)
                return ""
            }
        }

    fun enableTcp(notificationHelper: NotificationHelper, context: Context, port: String) {
        val intent = Intent(context.applicationContext, MainActivity::class.java)
        notificationHelper.createADBEnabledNotification(
            context,
            intent
        )
        try {
            callCommandWithADBDRestart("setprop service.adb.tcp.port $port")
        } catch (e: Exception) {
            writeLog(Level.ERROR, "cannot enable TCP service", e)
        }
    }

    fun disableTcp(notificationHelper: NotificationHelper) {
        notificationHelper.cancelADBEnabledNotification()
        try {
            callCommandWithADBDRestart("setprop service.adb.tcp.port -1")
        } catch (e: Exception) {
            writeLog(Level.ERROR, "cannot disable TCP service", e)
        }
    }

    @Throws(Exception::class)
    fun callCommandWithADBDRestart(str: String) {
        val exec = Runtime.getRuntime().exec("su")
        val dataOutputStream = DataOutputStream(exec.outputStream)
        dataOutputStream.writeBytes(str + "\n")
        dataOutputStream.writeBytes("stop adbd\n")
        dataOutputStream.writeBytes("start adbd\n")
        dataOutputStream.writeBytes("exit\n")
        dataOutputStream.flush()
        dataOutputStream.close()
        exec.waitFor()
    }

    fun softRestart() {
        try {
            val exec = Runtime.getRuntime().exec("su")
            val dataOutputStream = DataOutputStream(exec.outputStream)
            dataOutputStream.writeBytes("killall zygote\n")
            dataOutputStream.flush()
            dataOutputStream.writeBytes("exit\n")
            dataOutputStream.flush()
            exec.waitFor()
        } catch (e: IOException) {
            writeLog(Level.ERROR, "Soft restart IOException", e)
        } catch (e: InterruptedException) {
            writeLog(Level.ERROR, "Soft restart interrupted exception", e)
        } catch (e: Exception) {
            writeLog(Level.ERROR, "Soft restart exception", e)
        }
    }

    fun changeADBOverUSBVar(enable: Boolean) {
        try {
            callCommandWithADBDRestart("settings put global adb_enabled ${if (enable) 1 else 0}")
        } catch (e: Exception) {
            writeLog(Level.ERROR, "ADB variable changing failed", e)
        }
    }
}