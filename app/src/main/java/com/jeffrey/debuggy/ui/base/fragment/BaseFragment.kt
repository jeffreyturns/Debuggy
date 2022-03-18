package com.jeffrey.debuggy.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.jeffrey.debuggy.data.notification.NotificationHelper
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.util.NetworkUtils
import com.jeffrey.debuggy.util.aliases.FragmentInflate
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: FragmentInflate<VB>
) : Fragment(), KoinComponent {

    private var _binding: VB? = null
    val binding get() = _binding!!

    val preference by inject<PreferencesHelper>()
    val notification by inject<NotificationHelper>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        val view = binding.root
        setUpViews()
        initListener()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListener() {
        NetworkUtils.listener(
            requireActivity(),
            ::onAvailable,
            ::onLost
        )
    }

    open fun setUpViews() {}

    open fun onLost() {}

    open fun onAvailable() {}
}

