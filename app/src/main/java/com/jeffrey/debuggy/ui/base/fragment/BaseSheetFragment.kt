package com.jeffrey.debuggy.ui.base.fragment

import android.app.Dialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.*
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.util.Utils
import com.jeffrey.debuggy.util.aliases.FragmentInflate
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseSheetFragment<VB : ViewBinding>(
    private val inflate: FragmentInflate<VB>
) : BottomSheetDialogFragment(), KoinComponent {

    private val preference: PreferencesHelper by inject()
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    open fun setUpViews() {}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = object : BottomSheetDialog(requireContext(), theme) {

            override fun onAttachedToWindow() {
                super.onAttachedToWindow()

                val container = findViewById<FrameLayout>(R.id.container)
                val coordinator = findViewById<CoordinatorLayout>(com.google.android.material.R.id.coordinator)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (!Utils.isDarkMode(context, theme)) {
                        @Suppress("DEPRECATION")
                        window?.decorView?.systemUiVisibility =
                            WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS or
                                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                    }
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    window?.let {
                        WindowCompat.setDecorFitsSystemWindows(it, false)
                    }

                    container?.apply {
                        fitsSystemWindows = false
                        val topMargin = (layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin ?: 0
                        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
                            val newTopMargin = topMargin + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
                            val layoutParams = view.layoutParams as? ViewGroup.MarginLayoutParams
                            layoutParams?.topMargin = newTopMargin
                            view.layoutParams = layoutParams
                            insets
                        }
                    }

                    coordinator?.fitsSystemWindows = false
                }
            }
        }

        dialog.window?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                WindowCompat.setDecorFitsSystemWindows(it, false)
                it.navigationBarColor = Color.TRANSPARENT
                ViewCompat.setOnApplyWindowInsetsListener(it.decorView) { view, insets ->
                    val navigationInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
                    view.setPadding(
                        navigationInsets.left,
                        view.paddingTop,
                        navigationInsets.right,
                        view.paddingBottom
                    )
                    insets
                }
            }
        }

        return dialog
    }
}