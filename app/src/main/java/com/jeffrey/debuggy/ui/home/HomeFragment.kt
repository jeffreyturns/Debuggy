package com.jeffrey.debuggy.ui.home

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.sectioned.CardSectionedAdapter
import com.jeffrey.debuggy.data.slot.informationHomeList
import com.jeffrey.debuggy.data.slot.instructionHomeList
import com.jeffrey.debuggy.databinding.FragmentHomeBinding
import com.jeffrey.debuggy.ui.base.BaseFragment
import com.jeffrey.debuggy.utils.NetworkUtil
import com.jeffrey.debuggy.utils.TransitionUtil
import com.jeffrey.debuggy.utils.extensions.addInsetPaddings

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun setUpViews() {
        returnTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), true)

        setHasOptionsMenu(true)

        NetworkUtil.listener(
            requireActivity(),
            ::hideBanner,
            ::showBanner
        )

        binding.recyclerView.addInsetPaddings(bottom = true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = ConcatAdapter(
            CardSectionedAdapter(
                informationHomeList(requireActivity()),
                requireActivity().resources.getString(R.string.header_information),
                requireActivity()
            ),
            CardSectionedAdapter(
                instructionHomeList(requireActivity()),
                requireActivity().resources.getString(R.string.header_instruction),
                requireActivity()
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.navigation_settings) {
            Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
            ).navigate(R.id.navigation_settings)
            exitTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), false)
            reenterTransition = TransitionUtil.getMaterialSharedAxis(requireActivity(), true)
        }
        return false
    }

    private fun showBanner() {
        binding.mainContainer.transitionToEnd()
    }

    private fun hideBanner() {
        binding.mainContainer.transitionToStart()
    }
}