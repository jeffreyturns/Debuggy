package com.jeffrey.debuggy.data.about

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.utils.Constants
import com.jeffrey.debuggy.utils.TransitionUtil
import com.jeffrey.debuggy.utils.Utils

class AboutAction(private val context: Context, private val fragment: Fragment) {

    fun callSupportCard() {
        Navigation.findNavController(
            context as Activity,
            R.id.nav_host_fragment
        ).navigate(R.id.navigation_faq)
        fragment.exitTransition = TransitionUtil.getMaterialSharedAxis(context, false)
        fragment.reenterTransition =
            TransitionUtil.getMaterialSharedAxis(context, true)
    }

    fun callFeedbackCard() {
        Utils.openCustomTab(context, Constants.URL_FEEDBACK)
    }

    fun callNewsCard() {
        Utils.openCustomTab(context, Constants.URL_NEWS)
    }

    fun callTranslateCard() {
        Utils.openCustomTab(context, Constants.URL_TRANSLATE)
    }
}