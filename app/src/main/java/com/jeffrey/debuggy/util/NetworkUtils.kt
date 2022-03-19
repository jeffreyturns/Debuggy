package com.jeffrey.debuggy.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import com.jeffrey.debuggy.util.system.launchUI

object NetworkUtils {

    fun isOverWIFI(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> false
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> false
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> false
                        ConnectivityManager.TYPE_ETHERNET -> false
                        else -> false
                    }
                }
            }
        }

        return result
    }

    fun listener(context: Context, onAvailable: () -> Unit, onLost: () -> Unit) {

        if (isOverWIFI(context))
            onAvailable() else
            onLost()

        val networkCallback: ConnectivityManager.NetworkCallback =
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    launchUI {
                        if (isOverWIFI(context))
                            onAvailable()
                    }
                }

                override fun onLost(network: Network) {
                    launchUI {
                        if (isOverWIFI(context))
                            onLost()
                    }
                }
            }

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
            connectivityManager.registerNetworkCallback(request, networkCallback)
        }
    }
}