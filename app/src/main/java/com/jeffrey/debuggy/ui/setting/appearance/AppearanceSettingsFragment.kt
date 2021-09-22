package com.jeffrey.debuggy.ui.setting.appearance

import android.os.Build
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferenceHelper
import com.jeffrey.debuggy.utils.TransitionUtil
import com.jeffrey.debuggy.utils.Utils

class AppearanceSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_appearance, rootKey)

        val appearance: Preference = findPreference("theme")!!
        appearance.setOnPreferenceClickListener {
            val appearanceSheet = ThemePreferenceSheet()
            appearanceSheet.show(
                this.childFragmentManager,
                ThemePreferenceSheet::class.java.name
            )
            true
        }

        val systemColors: SwitchPreference = findPreference("use_system_colors")!!
        systemColors.isEnabled = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) systemColors.isEnabled = true
        systemColors.isChecked = PreferenceHelper.useSystemColors(requireActivity())
        systemColors.setOnPreferenceChangeListener { _, value ->
            if (value == true) PreferenceHelper.useSystemColors(
                requireActivity(),
                true
            ) else PreferenceHelper.useSystemColors(
                requireActivity(),
                false
            )
            Utils.restartApp(requireActivity())
            true
        }
        updateTheme()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), false)
        returnTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), true)
    }

    fun updateTheme() {
        val appearance: Preference = findPreference("theme")!!
        appearance.summary =
            when (PreferenceHelper.theme(requireActivity())) {
                1 -> {
                    requireActivity().getString(R.string.summary_light)
                }
                2 -> {
                    requireActivity().getString(R.string.summary_dark)
                }
                0 -> {
                    requireActivity().getString(R.string.summary_default)
                }
                else -> {
                    "Undefined"
                }
            }
    }
}