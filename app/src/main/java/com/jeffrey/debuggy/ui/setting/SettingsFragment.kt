package com.jeffrey.debuggy.ui.setting

import android.os.Bundle
import androidx.preference.Preference
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.ui.base.fragment.BasePreferenceFragmentCompat
import com.jeffrey.debuggy.util.TransitionUtils
import com.jeffrey.debuggy.util.extensions.navigate

class SettingsFragment : BasePreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)

        val preferences = listOf("general", "appearance", "security", "experimental", "about")
        val destinations = listOf(
            R.id.navigation_general_settings,
            R.id.navigation_appearance_settings,
            R.id.navigation_security_settings,
            R.id.navigation_experimental_settings,
            R.id.navigation_about_settings
        )

        preferences.zip(destinations).forEach { pair ->
            val preference: Preference = findPreference(pair.component1())!!

            preference.setOnPreferenceClickListener {
                requireActivity().navigate(pair.component2())
                exitTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), false)
                reenterTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)

                true
            }
        }
    }
}