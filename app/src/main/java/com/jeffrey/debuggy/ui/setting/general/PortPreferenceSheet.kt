package com.jeffrey.debuggy.ui.setting.general

import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.databinding.DialogPortPreferenceBinding
import com.jeffrey.debuggy.ui.base.BaseSheetFragment
import org.koin.core.component.inject

class PortPreferenceSheet : BaseSheetFragment<DialogPortPreferenceBinding>(
    DialogPortPreferenceBinding::inflate
) {

    private val preference: PreferencesHelper by inject()

    override fun setUpViews() {
        binding.actionReset.setOnClickListener {
            preference.port = "5555"
            parentFragmentCallBack()
            dismiss()
        }
        binding.actionCancel.setOnClickListener { dismiss() }
        binding.actionSave.setOnClickListener {
            if (binding.textData.editText!!.text.toString().length < 4) {
                binding.textData.error =
                    requireActivity().getString(R.string.error_port_less_4_char)
            } else {
                preference.port = binding.textData.editText!!.text.toString()
                parentFragmentCallBack()
                dismiss()
            }
        }
    }

    private fun parentFragmentCallBack() {
        try {
            val callback = parentFragment as GeneralSettingsFragment?
            callback!!.update()
        } catch (e: ClassCastException) {
            throw ClassCastException("interface not implemented")
        }
    }
}