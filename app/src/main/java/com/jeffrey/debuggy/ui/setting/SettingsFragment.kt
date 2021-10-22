package com.jeffrey.debuggy.ui.setting

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.preference.Preference
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.ui.base.BasePreferenceFragmentCompat
import com.jeffrey.debuggy.util.TransitionUtils

class SettingsFragment : BasePreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)

        val general: Preference = findPreference("general")!!
        general.setOnPreferenceClickListener {
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.navigation_general_settings)
            exitTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), false)
            reenterTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)

            true
        }

        val appearance: Preference = findPreference("appearance")!!
        appearance.setOnPreferenceClickListener {
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.navigation_appearance_settings)
            exitTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), false)
            reenterTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)

            true
        }

        val security: Preference = findPreference("security")!!
        security.setOnPreferenceClickListener {
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.navigation_security_settings)
            exitTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), false)
            reenterTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)

            true
        }

        val experimental: Preference = findPreference("experimental")!!
        experimental.setOnPreferenceClickListener {
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.navigation_experimental_settings)
            exitTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), false)
            reenterTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)

            true
        }

        val about: Preference = findPreference("about")!!
        about.setOnPreferenceClickListener {
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.navigation_about_settings)
            exitTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), false)
            reenterTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)

            true
        }
    }
}