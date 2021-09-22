package com.jeffrey.debuggy.data.slot

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.jeffrey.debuggy.ui.setting.about.LibrariesSheet
import com.jeffrey.debuggy.utils.Constants
import com.jeffrey.debuggy.utils.Utils

class SlotAction(private val context: Context) {

    fun callChangelog() {
        Utils.openCustomTab(context, Utils.getCurrentReleaseUrl())
    }

    fun callLibraries() {
        val portSheet = LibrariesSheet()
        portSheet.show(
            (context as AppCompatActivity).supportFragmentManager,
            LibrariesSheet::class.java.name
        )
    }

    fun callUpdates() {
        Utils.openCustomTab(context, Constants.URL_LATEST_RELEASE)
    }
}
