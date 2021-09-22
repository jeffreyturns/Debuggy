package com.jeffrey.debuggy.ui.setting.general

import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferenceHelper
import com.jeffrey.debuggy.databinding.DialogPortPreferenceBinding
import com.jeffrey.debuggy.ui.base.BaseSheetFragment


class PortPreferenceSheet : BaseSheetFragment<DialogPortPreferenceBinding>(
    DialogPortPreferenceBinding::inflate
) {

    override fun setUpViews() {
        binding.actionReset.setOnClickListener {
            PreferenceHelper.port(requireActivity(), "5555")
            parentFragmentCallBack()
            dismiss()
        }
        binding.actionCancel.setOnClickListener { dismiss() }
        binding.actionSave.setOnClickListener {
            if (binding.textData.editText!!.text.toString().length < 4) {
                binding.textData.error =
                    requireActivity().getString(R.string.error_port_less_4_char)
            } else {
                PreferenceHelper.port(
                    requireActivity(),
                    binding.textData.editText!!.text.toString()
                )
                parentFragmentCallBack()
                dismiss()
            }
        }
    }

    private fun parentFragmentCallBack() {
        try {
            val callback = parentFragment as GeneralSettingsFragment?
            callback!!.updatePort()
        } catch (e: ClassCastException) {
            throw ClassCastException("interface not implemented")
        }
    }
}