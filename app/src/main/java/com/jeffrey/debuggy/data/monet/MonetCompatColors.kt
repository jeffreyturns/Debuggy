package com.jeffrey.debuggy.data.monet

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@SuppressLint("InlinedApi")
class MonetCompatColors(val context: Context) : KoinComponent {

    private val preference: PreferencesHelper by inject()
    private val systemColors = preference.useSystemColors

    /*

    Define system accent 1

    */
    val systemAccent1Shade0 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_0 else R.color.amber_0,
        context.theme
    )

    val systemAccent1Shade10 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_10 else R.color.amber_10,
        context.theme
    )

    val systemAccent1Shade50 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_50 else R.color.amber_50,
        context.theme
    )

    val systemAccent1Shade100 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_100 else R.color.amber_100,
        context.theme
    )

    val systemAccent1Shade200 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_200 else R.color.amber_200,
        context.theme
    )

    val systemAccent1Shade300 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_300 else R.color.amber_300,
        context.theme
    )

    val systemAccent1Shade400 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_400 else R.color.amber_400,
        context.theme
    )

    val systemAccent1Shade500 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_500 else R.color.amber_500,
        context.theme
    )

    val systemAccent1Shade600 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_600 else R.color.amber_600,
        context.theme
    )

    val systemAccent1Shade700 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_700 else R.color.amber_700,
        context.theme
    )

    val systemAccent1Shade800 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_800 else R.color.amber_800,
        context.theme
    )

    val systemAccent1Shade900 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_900 else R.color.amber_900,
        context.theme
    )

    val systemAccent1Shade1000 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent1_1000 else R.color.amber_1000,
        context.theme
    )

    /*

    Define system accent 2

    */

    val systemAccent2Shade0 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_0 else R.color.amber_neutral_0,
        context.theme
    )

    val systemAccent2Shade10 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_10 else R.color.amber_neutral_10,
        context.theme
    )

    val systemAccent2Shade50 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_50 else R.color.amber_neutral_50,
        context.theme
    )

    val systemAccent2Shade100 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_100 else R.color.amber_neutral_100,
        context.theme
    )

    val systemAccent2Shade200 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_200 else R.color.amber_neutral_200,
        context.theme
    )

    val systemAccent2Shade300 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_300 else R.color.amber_neutral_300,
        context.theme
    )

    val systemAccent2Shade400 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_400 else R.color.amber_neutral_400,
        context.theme
    )

    val systemAccent2Shade500 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_500 else R.color.amber_neutral_500,
        context.theme
    )

    val systemAccent2Shade600 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_600 else R.color.amber_neutral_600,
        context.theme
    )

    val systemAccent2Shade700 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_700 else R.color.amber_neutral_700,
        context.theme
    )

    val systemAccent2Shade800 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_800 else R.color.amber_neutral_800,
        context.theme
    )

    val systemAccent2Shade900 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_900 else R.color.amber_neutral_900,
        context.theme
    )

    val systemAccent2Shade1000 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent2_1000 else R.color.amber_neutral_1000,
        context.theme
    )

    /*

   Define system accent 3

   */

    val systemAccent3Shade0 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_0 else R.color.light_green_0,
        context.theme
    )

    val systemAccent3Shade10 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_10 else R.color.light_green_10,
        context.theme
    )

    val systemAccent3Shade50 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_50 else R.color.light_green_50,
        context.theme
    )

    val systemAccent3Shade100 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_100 else R.color.light_green_100,
        context.theme
    )

    val systemAccent3Shade200 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_200 else R.color.light_green_200,
        context.theme
    )

    val systemAccent3Shade300 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_300 else R.color.light_green_300,
        context.theme
    )

    val systemAccent3Shade400 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_400 else R.color.light_green_400,
        context.theme
    )

    val systemAccent3Shade500 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_500 else R.color.light_green_500,
        context.theme
    )

    val systemAccent3Shade600 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_600 else R.color.light_green_600,
        context.theme
    )

    val systemAccent3Shade700 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_700 else R.color.light_green_700,
        context.theme
    )

    val systemAccent3Shade800 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_800 else R.color.light_green_800,
        context.theme
    )

    val systemAccent3Shade900 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_900 else R.color.light_green_900,
        context.theme
    )

    val systemAccent3Shade1000 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_accent3_1000 else R.color.light_green_1000,
        context.theme
    )

    /*

    Define system neutral 1

    */

    val systemNeutral1Shade0 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_0 else R.color.gray_0,
        context.theme
    )

    val systemNeutral1Shade10 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_10 else R.color.gray_10,
        context.theme
    )

    val systemNeutral1Shade50 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_50 else R.color.gray_50,
        context.theme
    )

    val systemNeutral1Shade100 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_100 else R.color.gray_100,
        context.theme
    )

    val systemNeutral1Shade200 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_200 else R.color.gray_200,
        context.theme
    )

    val systemNeutral1Shade300 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_300 else R.color.gray_300,
        context.theme
    )

    val systemNeutral1Shade400 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_400 else R.color.gray_400,
        context.theme
    )

    val systemNeutral1Shade500 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_500 else R.color.gray_500,
        context.theme
    )

    val systemNeutral1Shade600 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_600 else R.color.gray_600,
        context.theme
    )

    val systemNeutral1Shade700 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_700 else R.color.gray_700,
        context.theme
    )

    val systemNeutral1Shade800 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_800 else R.color.gray_800,
        context.theme
    )

    val systemNeutral1Shade900 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_900 else R.color.gray_900,
        context.theme
    )

    val systemNeutral1Shade1000 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral1_1000 else R.color.gray_1000,
        context.theme
    )

    /*

   Define system neutral 2

   */

    val systemNeutral2Shade0 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_0 else R.color.gray_neutral_0,
        context.theme
    )

    val systemNeutral2Shade10 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_10 else R.color.gray_neutral_10,
        context.theme
    )

    val systemNeutral2Shade50 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_50 else R.color.gray_neutral_50,
        context.theme
    )

    val systemNeutral2Shade100 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_100 else R.color.gray_neutral_100,
        context.theme
    )

    val systemNeutral2Shade200 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_200 else R.color.gray_neutral_200,
        context.theme
    )

    val systemNeutral2Shade300 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_300 else R.color.gray_neutral_300,
        context.theme
    )

    val systemNeutral2Shade400 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_400 else R.color.gray_neutral_400,
        context.theme
    )

    val systemNeutral2Shade500 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_500 else R.color.gray_neutral_500,
        context.theme
    )

    val systemNeutral2Shade600 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_600 else R.color.gray_neutral_600,
        context.theme
    )

    val systemNeutral2Shade700 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_700 else R.color.gray_neutral_700,
        context.theme
    )

    val systemNeutral2Shade800 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_800 else R.color.gray_neutral_800,
        context.theme
    )

    val systemNeutral2Shade900 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_900 else R.color.gray_neutral_900,
        context.theme
    )

    val systemNeutral2Shade1000 = ResourcesCompat.getColor(
        context.resources,
        if (systemColors) android.R.color.system_neutral2_1000 else R.color.gray_neutral_1000,
        context.theme
    )
}