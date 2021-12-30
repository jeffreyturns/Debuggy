package com.jeffrey.debuggy.ui.main

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.*
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
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
import com.jeffrey.debuggy.util.extensions.addInsetPaddings
import com.jeffrey.debuggy.util.extensions.navigationType
import com.jeffrey.debuggy.util.extensions.toDp
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
        themeCall(false)
        setTheme(R.style.Theme_Debuggy_App)
        if (preference.useSystemColors) DynamicColors.applyIfAvailable(this)
    }

    override fun setUpViews() {
        setSupportActionBar(binding.toolbar)

        if (preference.authenticationEnabled) {
            AuthenticationManager.getBiometricPrompt(
                this,
                onError = ::closeApp,
                onSucceeded = ::initFromStart,
                onFailed = ::closeApp
            )
                .authenticate(AuthenticationManager.info(getString(R.string.title_biometric_prompt)))
        } else {
            initFromStart()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = Color.TRANSPARENT
            when (this.navigationType()) {
                0 -> {
                    window.navigationBarColor =
                        ResourcesCompat.getColor(resources, R.color.color_background_70, theme)
                }
                2 -> {
                    window.navigationBarColor = Color.TRANSPARENT
                }
            }
            setupInsets()
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _: NavController?, destination: NavDestination, _: Bundle? ->
            binding.collapsingToolbar.title =
                navHostFragment.navController.currentDestination!!.label
            binding.toolbar.title = navHostFragment.navController.currentDestination!!.label
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
        if (App.isRoot()) {
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

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat?,
        pref: Preference?
    ): Boolean {
        return true
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

    private fun closeApp() {
        finishAffinity()
    }

    private fun setupInsets() {
        binding.root.addInsetPaddings(left = true, right = true)
        ViewCompat.setOnApplyWindowInsetsListener(binding.collapsingToolbar) { view, insets ->
            view.updateLayoutParams<AppBarLayout.LayoutParams> {
                val appBarHeight = view.context.resources.getDimension(R.dimen.app_bar_height)
                height =
                    (appBarHeight + insets.getInsets(WindowInsetsCompat.Type.statusBars()).top).roundToInt()
            }
            insets
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { view, insets ->
            56.toDp.let {
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

    fun themeCall(recreate: Boolean) {
        if (recreate) Utils.restartApp(this) else
            AppCompatDelegate.setDefaultNightMode(
                when (preference.appTheme) {
                    0 -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    1 -> AppCompatDelegate.MODE_NIGHT_NO
                    2 -> AppCompatDelegate.MODE_NIGHT_YES
                    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }
            )
    }
}