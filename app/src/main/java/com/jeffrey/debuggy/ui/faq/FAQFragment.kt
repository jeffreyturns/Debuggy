package com.jeffrey.debuggy.ui.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeffrey.debuggy.data.faq.FAQAdapter
import com.jeffrey.debuggy.data.faq.faqList
import com.jeffrey.debuggy.databinding.FragmentFaqBinding
import com.jeffrey.debuggy.utils.TransitionUtil
import com.jeffrey.debuggy.utils.extensions.addInsetPaddings

class FAQFragment : Fragment() {

    private var _binding: FragmentFaqBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFaqBinding.inflate(inflater, container, false)
        enterTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), false)
        returnTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), true)
        val view = binding.root

        binding.recyclerView.addInsetPaddings(bottom = true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = FAQAdapter(faqList(requireActivity()))

        return view
    }
}