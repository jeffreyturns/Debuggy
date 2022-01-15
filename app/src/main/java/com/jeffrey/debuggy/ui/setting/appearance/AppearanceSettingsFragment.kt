package com.jeffrey.debuggy.ui.setting.appearance

import android.os.Build
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.SwitchPreference
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.ui.base.BasePreferenceFragmentCompat
import com.jeffrey.debuggy.util.Constants
import com.jeffrey.debuggy.util.extensions.restartApp
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppearanceSettingsFragment : BasePreferenceFragmentCompat(), KoinComponent {

    private val preference: PreferencesHelper by inject()

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
        systemColors.isChecked = preference.useSystemColors
        systemColors.setOnPreferenceChangeListener { _, value ->
            preference.useSystemColors = value == true
            requireActivity().restartApp()
            true
        }
        update()
    }

    fun update() {
        val appearance: Preference = findPreference("theme")!!
        appearance.summary =
            when (preference.appTheme) {
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
                    Constants.UNDEFINED_TEXT
                }
            }
    }
}