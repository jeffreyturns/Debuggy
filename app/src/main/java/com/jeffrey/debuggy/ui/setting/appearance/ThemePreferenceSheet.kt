package com.jeffrey.debuggy.ui.setting.appearance

import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferenceHelper
import com.jeffrey.debuggy.databinding.DialogThemePreferenceBinding
import com.jeffrey.debuggy.ui.base.BaseSheetFragment
import com.jeffrey.debuggy.ui.main.MainActivity


class ThemePreferenceSheet : BaseSheetFragment<DialogThemePreferenceBinding>(
    DialogThemePreferenceBinding::inflate
) {

    override fun setUpViews() {
        when (PreferenceHelper.theme(requireActivity())) {
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
                    PreferenceHelper.theme(requireActivity(), 1)
                    parentFragmentCallBack()
                    dismiss()
                    (requireActivity() as MainActivity).themeCall(true)
                }
                R.id.radio_dark -> {
                    PreferenceHelper.theme(requireActivity(), 2)
                    parentFragmentCallBack()
                    dismiss()
                    (requireActivity() as MainActivity).themeCall(true)

                }
                R.id.radio_default -> {
                    PreferenceHelper.theme(requireActivity(), 0)
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
            callback!!.updateTheme()
        } catch (e: ClassCastException) {
            throw ClassCastException("interface not implemented")
        }
    }
}