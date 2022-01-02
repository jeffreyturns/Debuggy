package com.jeffrey.debuggy.ui.home

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.data.sectioned.CardSectionedAdapter
import com.jeffrey.debuggy.data.slot.informationHomeList
import com.jeffrey.debuggy.data.slot.instructionHomeList
import com.jeffrey.debuggy.data.switch.SwitchAdapter
import com.jeffrey.debuggy.databinding.FragmentHomeBinding
import com.jeffrey.debuggy.ui.base.BaseFragment
import com.jeffrey.debuggy.util.NetworkUtils
import com.jeffrey.debuggy.util.TransitionUtils
import com.jeffrey.debuggy.util.extensions.addInsetPaddings
import com.jeffrey.debuggy.util.extensions.navigate
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    var container: MotionLayout? = null
    private val preference: PreferencesHelper by inject()

    override fun setUpViews() {
        returnTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)

        container = binding.mainContainer
        setHasOptionsMenu(true)

        NetworkUtils.listener(
            requireActivity(),
            ::hideBanner,
            ::showBanner
        )

        binding.recyclerView.addInsetPaddings(bottom = true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = ConcatAdapter(
            SwitchAdapter(
                requireActivity()
            ),
            CardSectionedAdapter(
                informationHomeList(requireActivity(), preference.port),
                requireActivity().resources.getString(R.string.header_information),
                requireActivity()
            ),
            CardSectionedAdapter(
                instructionHomeList(requireActivity(), preference.port),
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
            requireActivity().navigate(R.id.navigation_settings)
            exitTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), false)
            reenterTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)
        }
        return false
    }

    private fun showBanner() {
        container?.transitionToEnd()
    }

    private fun hideBanner() {
        container?.transitionToStart()
    }
}