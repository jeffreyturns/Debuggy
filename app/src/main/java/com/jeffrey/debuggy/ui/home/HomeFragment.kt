package com.jeffrey.debuggy.ui.home

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.notification.NotificationReceiver
import com.jeffrey.debuggy.data.sectioned.CardSectionedAdapter
import com.jeffrey.debuggy.data.slot.informationHomeList
import com.jeffrey.debuggy.data.slot.instructionADBDisabledList
import com.jeffrey.debuggy.data.slot.instructionHomeList
import com.jeffrey.debuggy.data.switch.SwitchAdapter
import com.jeffrey.debuggy.databinding.FragmentHomeBinding
import com.jeffrey.debuggy.ui.base.fragment.BaseFragment
import com.jeffrey.debuggy.util.TransitionUtils
import com.jeffrey.debuggy.util.view.addInsetPaddings
import com.jeffrey.debuggy.util.system.navigate

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    var container: MotionLayout? = null
    var switchAdapter: SwitchAdapter? = null
    var informationAdapter: CardSectionedAdapter? = null
    var instructionAdapter: CardSectionedAdapter? = null

    private val notificationReceiver = object : NotificationReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                ACTION_DISABLE_ADB -> {
                    switchAdapter?.updateUI()
                }
            }
        }
    }

    override fun setUpViews() {
        returnTransition = TransitionUtils.getMaterialSharedAxis(requireActivity(), true)

        container = binding.mainContainer
        setHasOptionsMenu(true)

        switchAdapter = SwitchAdapter(requireActivity(), this)

        informationAdapter = CardSectionedAdapter(
            informationHomeList(requireActivity(), preference.port),
            requireActivity().resources.getString(R.string.header_information),
            requireActivity()
        )

        instructionAdapter = CardSectionedAdapter(
            if (preference.adbEnabled) instructionHomeList(
                requireActivity(),
                preference.port
            ) else instructionADBDisabledList(requireActivity()),
            requireActivity().resources.getString(R.string.header_instruction),
            requireActivity()
        )

        binding.recyclerView.addInsetPaddings(bottom = true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = ConcatAdapter(
            switchAdapter,
            informationAdapter,
            instructionAdapter
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

    override fun onLost() {
        container?.transitionToEnd()
        if (preference.adbEnabled) instructionAdapter?.slotAdapter?.items =
            instructionHomeList(requireActivity(), preference.port)
    }

    override fun onAvailable() {
        container?.transitionToStart()
        if (preference.adbEnabled) instructionAdapter?.slotAdapter?.items =
            instructionHomeList(requireActivity(), preference.port)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().registerReceiver(
            notificationReceiver,
            IntentFilter(NotificationReceiver.ACTION_DISABLE_ADB)
        )
    }

    override fun onStop() {
        super.onStop()
        requireActivity().unregisterReceiver(notificationReceiver)
    }
}