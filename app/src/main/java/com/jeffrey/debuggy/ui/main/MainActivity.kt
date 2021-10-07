package com.jeffrey.debuggy.ui.main

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar
import com.jeffrey.debuggy.App
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.monet.MonetDynamicPalette
import com.jeffrey.debuggy.data.notification.NotificationHelper
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.databinding.ActivityMainBinding
import com.jeffrey.debuggy.ui.base.BaseActivity
import com.jeffrey.debuggy.utils.*
import com.jeffrey.debuggy.utils.extensions.addInsetPaddings
import com.jeffrey.debuggy.utils.extensions.navBarHeight
import com.jeffrey.debuggy.utils.extensions.navigationType
import com.jeffrey.debuggy.utils.extensions.toDp
import org.koin.android.ext.android.inject
import kotlin.math.roundToInt

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    private val notification: NotificationHelper by inject()
    private val preference: PreferencesHelper by inject()

    override fun preSuperCall() {
        themeCall(false)
        systemColorsTheme()
    }

    override fun setUpViews() {
        setSupportActionBar(binding.toolbar)

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

        initColors()

        if (preference.adbEnabled) {
            RootUtil.enableTcp(notification, this, preference.port)
            TransitionUtil.disableIconImage(binding.adbIcon, this)
        } else {
            RootUtil.disableTcp(notification)
            TransitionUtil.enableIconImage(binding.adbIcon, this)
        }

        binding.fabAnimation.setOnClickListener {
            if (App.isRoot()) {
                if (preference.adbEnabled) {
                    RootUtil.disableTcp(notification)
                    TransitionUtil.enableFAB(binding.fabAnimation, this)
                    TransitionUtil.enableIconImage(binding.adbIcon, this)
                    TransitionUtil.enableIcon(binding.adbIcon, this)
                    preference.adbEnabled = false
                } else {
                    RootUtil.enableTcp(notification, this, preference.port)
                    TransitionUtil.disableFAB(binding.fabAnimation, this)
                    TransitionUtil.disableIconImage(binding.adbIcon, this)
                    TransitionUtil.disableIcon(binding.adbIcon, this)
                    preference.adbEnabled = true
                }
            } else {
                callSnackBar(this.resources.getString(R.string.message_action_unavailable))
            }
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
                showFAB()
                binding.toolbar.navigationIcon = null
            } else {
                hideFAB()
                binding.toolbar.navigationIcon = AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_arrow_back_24dp
                )
                binding.toolbar.setNavigationOnClickListener { navController.navigateUp() }
            }
        }
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat?,
        pref: Preference?
    ): Boolean {
        return true
    }

    private fun showFAB() {
        binding.adbFab.clearAnimation()
        binding.adbFab.visibility = View.VISIBLE
        binding.adbFab.animate()
            .translationY(0F)
            .setDuration(450)
            .interpolator = AnimationUtils.loadInterpolator(this, R.anim.fast_out_extra_slow_in)
    }

    private fun hideFAB() {
        binding.adbFab.animate()
            .translationY(
                (binding.adbFab.height + this.navBarHeight() + this.toDp(16)).toFloat()
            )
            .setDuration(450)
            .withEndAction { binding.adbFab.visibility = View.GONE }
            .interpolator = AnimationUtils.loadInterpolator(this, R.anim.fast_out_extra_slow_in)
        binding.adbFab.clearAnimation()
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
            this.toDp(56).let {
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
        ViewCompat.setOnApplyWindowInsetsListener(binding.adbFab) { _, insets ->
            binding.adbFab.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                updateMargins(
                    bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom + this@MainActivity.toDp(
                        16
                    )
                )
            }
            insets
        }
    }

    private fun initColors() {
        binding.collapsingToolbar.setContentScrimColor(MonetDynamicPalette(this).collapsingToolbarColor)
        if (preference.adbEnabled) {
            binding.fabAnimation.setBackgroundColor(MonetDynamicPalette(this).fabLayoutEnabledColor)
            binding.adbIcon.setColorFilter(MonetDynamicPalette(this).fabIconEnabledColor)
        } else {
            binding.fabAnimation.setBackgroundColor(MonetDynamicPalette(this).fabLayoutDisabledColor)
            binding.adbIcon.setColorFilter(MonetDynamicPalette(this).fabIconDisabledColor)
        }
    }

    private fun callSnackBar(string: String) {
        val snack = Snackbar.make(
            binding.adbFab,
            string,
            Snackbar.LENGTH_LONG
        )
        snack.setTextColor(MonetDynamicPalette(this).snackbarTextColor)
        snack.setBackgroundTint(MonetDynamicPalette(this).snackbarBackgroundColor)
        snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
        snack.anchorView = binding.adbFab
        snack.show()
    }

    private fun systemColorsTheme() {
        if (preference.useSystemColors) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                setTheme(R.style.Theme_Debuggy_App_Monet)
            } else {
                preference.useSystemColors = false
            }
        } else setTheme(R.style.Theme_Debuggy_App)
    }

    fun themeCall(recreate: Boolean) {
        if (recreate) Utils.restartApp(this) else
            when (preference.appTheme) {
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
    }
}