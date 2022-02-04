package com.jeffrey.debuggy.ui.main

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.ColorUtils
import androidx.core.view.*
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.color.DynamicColors
import com.jeffrey.debuggy.App
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.authentication.AuthenticationManager
import com.jeffrey.debuggy.data.notification.NotificationHelper
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.databinding.ActivityMainBinding
import com.jeffrey.debuggy.ui.base.BaseActivity
import com.jeffrey.debuggy.util.RootUtils
import com.jeffrey.debuggy.util.Utils
import com.jeffrey.debuggy.util.extensions.*
import com.jeffrey.debuggy.worker.TimeoutWorker
import com.jeffrey.debuggy.worker.WorkerUtils
import com.jeffrey.debuggy.worker.Workers
import org.koin.android.ext.android.inject
import kotlin.math.roundToInt

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    private val notification: NotificationHelper by inject()
    private val preference: PreferencesHelper by inject()
    private val work: WorkerUtils = WorkerUtils

    private val workManager = WorkManager.getInstance(this)

    override fun preSuperCall() {
        Utils.applyTheme(preference.appTheme)
        setTheme(
            if (this.isDarkMode)
                R.style.Theme_Debuggy_App_Dark
            else
                R.style.Theme_Debuggy_App_Light
        )
        if (preference.useSystemColors) DynamicColors.applyIfAvailable(
            this,
            R.style.Theme_Debuggy_DynamicColors
        )
    }

    override fun setUpViews() {
        setSupportActionBar(binding.toolbar)
        val navController: NavController = this.getNavController()

        if (preference.authenticationEnabled) {
            AuthenticationManager.getBiometricPrompt(
                this,
                onError = ::finishAffinity,
                onSucceeded = ::initFromStart,
                onFailed = ::finishAffinity
            )
                .authenticate(AuthenticationManager.info(getString(R.string.title_biometric_prompt)))
        } else {
            initFromStart()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = Color.TRANSPARENT
            when (this.navigationType()) {
                2 -> {
                    window.navigationBarColor = Color.TRANSPARENT
                }
                else -> {
                    window.navigationBarColor =
                        ColorUtils.setAlphaComponent(
                            this.getAttr(android.R.attr.colorBackground),
                            ((0.70f * 255).roundToInt())
                        )
                }
            }
            setupInsets()
        }

        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _: NavController?, destination: NavDestination, _: Bundle? ->
            binding.collapsingToolbar.title =
                navController.currentDestination!!.label
            binding.toolbar.title = navController.currentDestination!!.label
            binding.appBar.setExpanded(true)
            if (destination.id == R.id.navigation_home) {
                binding.toolbar.navigationIcon = null
            } else {
                binding.toolbar.navigationIcon = AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_arrow_back_24dp
                )
                binding.toolbar.setNavigationOnClickListener { navController.navigateUp() }
            }
        }
    }

    fun tcpStatus() {
        if (App.root) {
            if (preference.adbEnabled) {
                RootUtils.disableTcp(notification)
                work.cancelUniqueWork(workManager, Workers.WORKER_TIMEOUT_TASK_NAME)
                preference.adbEnabled = false
            } else {
                RootUtils.enableTcp(notification, this, preference.port)
                work.beginUniqueWork(
                    workManager,
                    OneTimeWorkRequest.from(TimeoutWorker::class.java),
                    this
                )
                preference.adbEnabled = true
            }
        }
    }

    private fun initFromStart() {
        if (preference.adbEnabled) {
            RootUtils.enableTcp(notification, this, preference.port)
            work.beginUniqueWork(
                workManager,
                OneTimeWorkRequest.from(TimeoutWorker::class.java),
                this
            )
        } else {
            RootUtils.disableTcp(notification)
            work.cancelUniqueWork(workManager, Workers.WORKER_TIMEOUT_TASK_NAME)
        }
    }

    private fun setupInsets() {
        binding.root.addInsetPaddings(left = true, right = true)
        ViewCompat.setOnApplyWindowInsetsListener(binding.collapsingToolbar) { view, insets ->
            view.updateLayoutParams<AppBarLayout.LayoutParams> {
                val collapsingAppBarHeight =
                    view.context.resources.getDimension(R.dimen.collapsing_app_bar_height)
                height =
                    (collapsingAppBarHeight + insets.getInsets(WindowInsetsCompat.Type.statusBars()).top).roundToInt()
            }
            insets
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { view, insets ->
            resources.getDimension(R.dimen.toolbar_m3_height).toInt().let {
                val statusInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
                val overflowPadding = resources.getDimension(R.dimen.keyline_8)
                val topInset = statusInsets.top
                view.updateLayoutParams<CollapsingToolbarLayout.LayoutParams> {
                    height = it + topInset
                }
                view.updatePadding(
                    left = statusInsets.left,
                    top = topInset,
                    right = statusInsets.right + overflowPadding.toInt()
                )
            }
            insets
        }
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat?,
        pref: Preference?
    ): Boolean {
        return true
    }
}