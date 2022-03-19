package com.jeffrey.debuggy.ui.setting.general

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.SwitchPreference
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferenceKeys
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.ui.base.callback.Action
import com.jeffrey.debuggy.ui.base.callback.BaseCallback
import com.jeffrey.debuggy.ui.base.callback.assign
import com.jeffrey.debuggy.ui.base.fragment.BasePreferenceFragmentCompat
import com.jeffrey.debuggy.util.TransitionUtils
import com.jeffrey.debuggy.util.view.SnackbarEvent
import com.jeffrey.debuggy.util.view.callback
import com.jeffrey.debuggy.util.view.snack
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.jeffrey.debuggy.util.view.action as snackAction

class GeneralSettingsFragment : BasePreferenceFragmentCompat(), KoinComponent, BaseCallback {

    private val preference: PreferencesHelper by inject()

    override val action: Action = {
        assign {
            val port: Preference = findPreference("port")!!
            port.summary = preference.port
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_general, rootKey)

        val port: Preference = findPreference("port")!!
        port.summary = preference.port
        port.setOnPreferenceClickListener {
            val portSheet = PortPreferenceSheet()
            portSheet.show(
                this.childFragmentManager,
                PortPreferenceSheet::class.java.name
            )
            true
        }


        val adbAfterBoot: SwitchPreference = findPreference("adb_after_boot")!!
        adbAfterBoot.isChecked = preference.runAfterBoot
        adbAfterBoot.setOnPreferenceChangeListener { _, value ->
            preference.runAfterBoot = value == true
            true
        }
        callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), false)
        returnTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)
    }

    fun undo() {
        requireActivity().snack(requireActivity().getString(R.string.message_port_reset)) {
            snackAction(requireActivity().getString(R.string.action_undo)) {
                this.dismiss()
            }
            this.callback {
                when (this) {
                    is SnackbarEvent.Timeout -> {
                        preference.port = PreferenceKeys.DEF_VAL_CUSTOM_PORT
                        callback
                    }
                    is SnackbarEvent.Swipe -> {
                        preference.port = PreferenceKeys.DEF_VAL_CUSTOM_PORT
                        callback
                    }
                    else -> {}
                }
            }
        }
    }
}