package com.jeffrey.debuggy.utils

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.Animatable
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.google.android.material.transition.FadeThroughProvider
import com.google.android.material.transition.MaterialSharedAxis
import com.google.android.material.transition.SlideDistanceProvider
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.monet.MonetDynamicPalette
import kotlin.math.roundToInt


object TransitionUtil {

    fun getMaterialSharedAxis(context: Context, forward: Boolean): MaterialSharedAxis {
        return MaterialSharedAxis(MaterialSharedAxis.X, forward).apply {
            (primaryAnimatorProvider as SlideDistanceProvider).slideDistance =
                context.resources.getDimension(R.dimen.shared_axis_x_slide_distance).roundToInt()
            duration = 450L
            (secondaryAnimatorProvider as FadeThroughProvider).progressThreshold = 0.22f
            interpolator = AnimationUtils.loadInterpolator(context, R.anim.fast_out_extra_slow_in)
        }
    }

    fun enableFAB(fab: ConstraintLayout, context: Context) {
        val colorAnimation =
            ValueAnimator.ofObject(
                ArgbEvaluator(),
                MonetDynamicPalette(context).fabLayoutEnabledColor,
                MonetDynamicPalette(context).fabLayoutDisabledColor
            )
        colorAnimation.duration = 500
        colorAnimation.addUpdateListener { animator ->
            fab.setBackgroundColor(
                animator.animatedValue as Int
            )
        }
        colorAnimation.start()
    }

    fun disableFAB(fab: ConstraintLayout, context: Context) {
        val colorAnimation =
            ValueAnimator.ofObject(
                ArgbEvaluator(),
                MonetDynamicPalette(context).fabLayoutDisabledColor,
                MonetDynamicPalette(context).fabLayoutEnabledColor
            )
        colorAnimation.duration = 500
        colorAnimation.addUpdateListener { animator ->
            fab.setBackgroundColor(
                animator.animatedValue as Int
            )
        }
        colorAnimation.start()
    }

    fun enableIcon(icon: ImageView, context: Context) {
        val colorAnimation =
            ValueAnimator.ofObject(
                ArgbEvaluator(),
                MonetDynamicPalette(context).fabIconEnabledColor,
                MonetDynamicPalette(context).fabIconDisabledColor
            )
        colorAnimation.duration = 500
        colorAnimation.addUpdateListener { animator ->
            icon.setColorFilter(
                animator.animatedValue as Int
            )
        }
        colorAnimation.start()
    }

    fun disableIcon(icon: ImageView, context: Context) {
        val colorAnimation =
            ValueAnimator.ofObject(
                ArgbEvaluator(),
                MonetDynamicPalette(context).fabIconDisabledColor,
                MonetDynamicPalette(context).fabIconEnabledColor
            )
        colorAnimation.duration = 500
        colorAnimation.addUpdateListener { animator ->
            icon.setColorFilter(
                animator.animatedValue as Int
            )
        }
        colorAnimation.start()
    }

    fun enableIconImage(icon: ImageView, context: Context) {
        icon.setImageDrawable(
            AnimatedVectorDrawableCompat.create(
                context,
                R.drawable.avd_wifi_start_24dp
            )
        )
        (icon.drawable as Animatable).start()
    }

    fun disableIconImage(icon: ImageView, context: Context) {
        icon.setImageDrawable(
            AnimatedVectorDrawableCompat.create(
                context,
                R.drawable.avd_wifi_end_24dp
            )
        )
        (icon.drawable as Animatable).start()
    }
}