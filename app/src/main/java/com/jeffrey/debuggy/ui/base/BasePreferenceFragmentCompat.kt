package com.jeffrey.debuggy.ui.base

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.jeffrey.debuggy.util.TransitionUtils

abstract class BasePreferenceFragmentCompat : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), false)
        returnTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)
    }
}