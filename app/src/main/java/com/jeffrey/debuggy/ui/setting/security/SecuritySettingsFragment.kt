package com.jeffrey.debuggy.ui.setting.security

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.SwitchPreference
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.authentication.AuthenticationManager
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.ui.base.fragment.BasePreferenceFragmentCompat
import com.jeffrey.debuggy.util.Constants
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SecuritySettingsFragment : BasePreferenceFragmentCompat(), KoinComponent {

    private val preference: PreferencesHelper by inject()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_security, rootKey)

        val authentication: SwitchPreference = findPreference("authentication_enabled")!!
        authentication.isChecked = preference.authenticationEnabled

        authentication.setOnPreferenceChangeListener { _, value ->
            if (value == false) {
                AuthenticationManager.getBiometricPrompt(
                    requireActivity(),
                    onSucceeded = { authenticationPassed(value, authentication) })
                    .authenticate(AuthenticationManager.info(requireActivity().getString(R.string.title_biometric_prompt)))
            } else {
                authenticationPassed(value, authentication)
            }
            false
        }

        val adbAfterWhile: Preference = findPreference("adb_after_while")!!
        adbAfterWhile.summary = preference.adbAfterWhile.toString()
        adbAfterWhile.setOnPreferenceClickListener {
            val adbAfterWhileSheet = ADBAfterWhilePreferenceSheet()
            adbAfterWhileSheet.show(
                this.childFragmentManager,
                ADBAfterWhilePreferenceSheet::class.java.name
            )
            true
        }
        update()
    }

    private fun authenticationPassed(value: Any, pref: SwitchPreference) {
        pref.isChecked = value == true
        preference.authenticationEnabled = value == true
    }

    fun update() {
        val adbAfterWhile: Preference = findPreference("adb_after_while")!!
        adbAfterWhile.summary = when (preference.adbAfterWhile) {
            0 -> {
                requireActivity().getString(R.string.title_never)
            }
            1 -> {
                requireActivity().getString(R.string.title_one_hour)
            }
            2 -> {
                requireActivity().getString(R.string.title_two_hours)
            }
            4 -> {
                requireActivity().getString(R.string.title_four_hours)
            }
            6 -> {
                requireActivity().getString(R.string.title_six_hours)
            }
            12 -> {
                requireActivity().getString(R.string.title_twelve_hours)
            }
            else -> {
                Constants.UNDEFINED_TEXT
            }
        }
    }
}