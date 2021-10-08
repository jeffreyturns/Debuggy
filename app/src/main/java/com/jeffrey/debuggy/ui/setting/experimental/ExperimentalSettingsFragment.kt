package com.jeffrey.debuggy.ui.setting.experimental

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.util.RootUtils
import com.jeffrey.debuggy.util.TransitionUtils

class ExperimentalSettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_experimental, rootKey)

        val softRestart: Preference = findPreference("soft_restart")!!
        softRestart.setOnPreferenceClickListener {
            RootUtils.softRestart()
            true
        }

        val adbOverUSBVar: Preference = findPreference("adb_over_usb_var")!!
        adbOverUSBVar.setOnPreferenceClickListener {
            val adbOverUSBVarSheet = ADBOverUSBVarPreferenceSheet()
            adbOverUSBVarSheet.show(
                this.childFragmentManager,
                ADBOverUSBVarPreferenceSheet::class.java.name
            )
            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), false)
        returnTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)
    }
}