package com.jeffrey.debuggy.ui.setting.experimental

import android.os.Bundle
import androidx.preference.Preference
import com.jeffrey.debuggy.AppDebuggy
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.ui.base.fragment.BasePreferenceFragmentCompat
import com.jeffrey.debuggy.util.RootUtils
import com.jeffrey.debuggy.util.view.action
import com.jeffrey.debuggy.util.view.snack

class ExperimentalSettingsFragment : BasePreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_experimental, rootKey)

        val softRestart: Preference = findPreference("soft_restart")!!
        val adbOverUSBVar: Preference = findPreference("adb_over_usb_var")!!
        if (!AppDebuggy.root) {
            softRestart.isEnabled = false
            adbOverUSBVar.isEnabled = false
        }

        softRestart.setOnPreferenceClickListener {
            requireActivity().snack(requireActivity().getString(R.string.message_soft_restart)) {
                action(requireActivity().getString(R.string.action_yes)) { RootUtils.softRestart() }
            }
            true
        }

        adbOverUSBVar.setOnPreferenceClickListener {
            val adbOverUSBVarSheet = ADBOverUSBVarPreferenceSheet()
            adbOverUSBVarSheet.show(
                this.childFragmentManager,
                ADBOverUSBVarPreferenceSheet::class.java.name
            )
            true
        }
    }
}