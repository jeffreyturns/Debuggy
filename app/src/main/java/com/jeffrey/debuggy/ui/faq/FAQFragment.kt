package com.jeffrey.debuggy.ui.faq

import androidx.recyclerview.widget.LinearLayoutManager
import com.jeffrey.debuggy.data.faq.FAQAdapter
import com.jeffrey.debuggy.data.faq.faqList
import com.jeffrey.debuggy.databinding.FragmentFaqBinding
import com.jeffrey.debuggy.ui.base.BaseFragment
import com.jeffrey.debuggy.utils.TransitionUtil
import com.jeffrey.debuggy.utils.extensions.addInsetPaddings

class FAQFragment : BaseFragment<FragmentFaqBinding>(FragmentFaqBinding::inflate) {

    override fun setUpViews() {
        enterTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), false)
        returnTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), true)

        binding.recyclerView.addInsetPaddings(bottom = true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = FAQAdapter(faqList(requireActivity()))
    }
}