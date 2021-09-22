package com.jeffrey.debuggy.data.monet

import android.annotation.SuppressLint
import android.content.Context
import com.jeffrey.debuggy.utils.Utils

@SuppressLint("InlinedApi")
class MonetDynamicPalette(val context: Context) {

    private val darkMode = Utils.isDarkMode(context)
    private val monetCompatColors: MonetCompatColors = MonetCompatColors(context)

    val collapsingToolbarColor =
        if (darkMode) monetCompatColors.systemNeutral2Shade700 else monetCompatColors.systemNeutral2Shade100

    val fabLayoutEnabledColor =
        if (darkMode) monetCompatColors.systemAccent3Shade100 else monetCompatColors.systemAccent3Shade600

    val fabLayoutDisabledColor =
        if (darkMode) monetCompatColors.systemNeutral1Shade800 else monetCompatColors.systemNeutral1Shade200

    val fabIconEnabledColor =
        if (darkMode) monetCompatColors.systemNeutral2Shade900 else monetCompatColors.systemNeutral2Shade50

    val fabIconDisabledColor =
        if (darkMode) monetCompatColors.systemNeutral2Shade50 else monetCompatColors.systemNeutral2Shade900

    var snackbarBackgroundColor =
        if (darkMode) monetCompatColors.systemNeutral2Shade700 else monetCompatColors.systemNeutral2Shade100

    val snackbarTextColor =
        if (darkMode) monetCompatColors.systemNeutral2Shade50 else monetCompatColors.systemNeutral2Shade900
}
