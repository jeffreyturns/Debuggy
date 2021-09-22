package com.jeffrey.debuggy.ui.setting

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.utils.TransitionUtil

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)

        val general: Preference = findPreference("general")!!
        general.setOnPreferenceClickListener {
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.navigation_general_settings)
            exitTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), false)
            reenterTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), true)

            true
        }

        val appearance: Preference = findPreference("appearance")!!
        appearance.setOnPreferenceClickListener {
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.navigation_appearance_settings)
            exitTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), false)
            reenterTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), true)

            true
        }

        val experimental: Preference = findPreference("experimental")!!
        experimental.setOnPreferenceClickListener {
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.navigation_experimental_settings)
            exitTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), false)
            reenterTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), true)

            true
        }

        val about: Preference = findPreference("about")!!
        about.setOnPreferenceClickListener {
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.navigation_about_settings)
            exitTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), false)
            reenterTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), true)

            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), false)
        returnTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), true)
    }
}