package com.jeffrey.debuggy.ui.setting.appearance

import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.databinding.DialogThemePreferenceBinding
import com.jeffrey.debuggy.ui.base.BaseSheetFragment
import com.jeffrey.debuggy.ui.main.MainActivity
import org.koin.core.component.inject

class ThemePreferenceSheet : BaseSheetFragment<DialogThemePreferenceBinding>(
    DialogThemePreferenceBinding::inflate
) {

    private val preference: PreferencesHelper by inject()

    override fun setUpViews() {
        when (preference.appTheme) {
            1 -> {
                binding.radioLight.isChecked = true
            }
            2 -> {
                binding.radioDark.isChecked = true
            }
            0 -> {
                binding.radioDefault.isChecked = true
            }
        }

        binding.actionCancel.setOnClickListener { dismiss() }

        binding.themeGroup.setOnCheckedChangeListener { _, _ ->
            when (binding.themeGroup.checkedRadioButtonId) {
                R.id.radio_light -> {
                    preference.appTheme = 1
                    parentFragmentCallBack()
                    dismiss()
                    (requireActivity() as MainActivity).themeCall(true)
                }
                R.id.radio_dark -> {
                    preference.appTheme = 2
                    parentFragmentCallBack()
                    dismiss()
                    (requireActivity() as MainActivity).themeCall(true)

                }
                R.id.radio_default -> {
                    preference.appTheme = 0
                    parentFragmentCallBack()
                    dismiss()
                    (requireActivity() as MainActivity).themeCall(true)
                }
            }
        }
    }

    private fun parentFragmentCallBack() {
        try {
            val callback = parentFragment as AppearanceSettingsFragment?
            callback!!.update()
        } catch (e: ClassCastException) {
            throw ClassCastException("interface not implemented")
        }
    }
}