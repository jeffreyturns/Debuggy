package com.jeffrey.debuggy.ui.setting.general

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferenceHelper
import com.jeffrey.debuggy.utils.TransitionUtil

class GeneralSettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_general, rootKey)

        val port: Preference = findPreference("port")!!
        port.summary = PreferenceHelper.port(requireActivity())
        port.setOnPreferenceClickListener {
            val portSheet = PortPreferenceSheet()
            portSheet.show(
                this.childFragmentManager,
                PortPreferenceSheet::class.java.name
            )
            true
        }

        val adbAfterBoot: SwitchPreference = findPreference("adb_after_boot")!!
        adbAfterBoot.isChecked = PreferenceHelper.runAfterBoot(requireActivity())
        adbAfterBoot.setOnPreferenceChangeListener { _, value ->
            if (value == true) PreferenceHelper.runAfterBoot(
                requireActivity(),
                true
            ) else PreferenceHelper.runAfterBoot(
                requireActivity(),
                false
            )
            true
        }
        updatePort()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), false)
        returnTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), true)
    }

    fun updatePort() {
        val port: Preference = findPreference("port")!!
        port.summary = PreferenceHelper.port(requireActivity())
    }
}