package com.jeffrey.debuggy.ui.setting.about

import android.content.Context
import com.jeffrey.debuggy.utils.Utils

class LibrariesAction(private val context: Context) {

    private fun callUrl(url: String) {
        Utils.openCustomTab(context, url)
    }

    fun callMaterialComponents() {
        callUrl("https://github.com/material-components/material-components-android")
    }

    fun callCoilKtx() {
        callUrl("https://github.com/coil-kt/coil")
    }

    fun callAndroidX() {
        callUrl("https://github.com/androidx/androidx")
    }

    fun callKoin() {
        callUrl("https://github.com/InsertKoinIO/koin")
    }
}