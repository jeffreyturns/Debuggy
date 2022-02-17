package com.jeffrey.debuggy.ui.setting.about

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.data.about.AboutAdapter
import com.jeffrey.debuggy.databinding.FragmentAboutBinding
import com.jeffrey.debuggy.ui.base.fragment.BaseFragment
import com.jeffrey.debuggy.util.TransitionUtils
import kotlin.math.abs

class AboutSettingsFragment : BaseFragment<FragmentAboutBinding>(FragmentAboutBinding::inflate) {

    override fun setUpViews() {
        enterTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), false)
        returnTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)

        setHasOptionsMenu(true)

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding.parallaxFrame.alpha = 1.0f - abs(
                    recyclerView.computeVerticalScrollOffset() / (
                            binding.parallaxFrame.height
                                .toFloat() / 2)
                )
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = AboutAdapter(requireActivity(), this)
    }
}