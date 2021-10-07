package com.jeffrey.debuggy.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import com.jeffrey.debuggy.data.notification.NotificationHelper
import com.jeffrey.debuggy.ui.main.MainActivity
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader


object RootUtil {

    fun canRunRootCommands(su: Process): Boolean {

        var retrieval: Boolean

        try {
            val os = DataOutputStream(su.outputStream)
            val d = BufferedReader(InputStreamReader(su.inputStream))
            os.writeBytes("id\n")
            os.flush()
            if (d.readLine().contains("uid=0")) {
                retrieval = true
                Log.d("ROOT", "Root access granted")
            } else {
                retrieval = false
                Log.d("ROOT", "Root access rejected: $d")
            }
        } catch (e: Exception) {
            retrieval = false
            Log.d("ROOT", "Root access rejected [" + e.javaClass.name + "] : " + e.message)
        }
        return retrieval
    }

    fun getDaemonStatus(su: Process): String {
        return try {
            val dataOutputStream = DataOutputStream(su.outputStream)
            dataOutputStream.writeBytes("getprop init.svc.adbd" + "\n")
            dataOutputStream.flush()
            dataOutputStream.close()
            val reader = BufferedReader(
                InputStreamReader(su.inputStream)
            )
            var read: Int
            val buffer = CharArray(4096)
            val output = StringBuffer()
            while (reader.read(buffer).also { read = it } > 0) {
                output.append(buffer, 0, read)
            }
            reader.close()
            su.waitFor()
            output.toString()
        } catch (e: IOException) {
            throw RuntimeException(e)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
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
            e.printStackTrace()
        }
    }

    fun disableTcp(notificationHelper: NotificationHelper) {
        notificationHelper.cancelADBEnabledNotification()
        try {
            callCommandWithADBDRestart("setprop service.adb.tcp.port -1")
        } catch (e: Exception) {
            e.printStackTrace()
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
            throw java.lang.Exception(e)
        } catch (e2: InterruptedException) {
            e2.printStackTrace()
        } catch (e3: java.lang.Exception) {
            e3.printStackTrace()
        }
    }

    fun changeADBOverUSBVar(enable: Boolean) {
        try {
            callCommandWithADBDRestart("settings put global adb_enabled ${if (enable) 1 else 0}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}