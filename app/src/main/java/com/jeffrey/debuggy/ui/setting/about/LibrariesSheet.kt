package com.jeffrey.debuggy.ui.setting.about

import androidx.recyclerview.widget.LinearLayoutManager
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.sectioned.CardSectionedAdapter
import com.jeffrey.debuggy.data.slot.libsLibrariesList
import com.jeffrey.debuggy.databinding.DialogLibrariesBinding
import com.jeffrey.debuggy.ui.base.BaseSheetFragment

class LibrariesSheet : BaseSheetFragment<DialogLibrariesBinding>(
    DialogLibrariesBinding::inflate
) {

    override fun setUpViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = CardSectionedAdapter(
            libsLibrariesList(LibrariesAction(requireActivity())),
            requireActivity().resources.getString(R.string.header_dependencies),
            requireActivity()
        )
    }
}