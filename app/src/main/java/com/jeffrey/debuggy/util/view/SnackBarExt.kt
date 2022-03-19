package com.jeffrey.debuggy.util.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.google.android.material.elevation.SurfaceColors
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.ui.main.MainActivity
import com.jeffrey.debuggy.util.system.getAttr

inline fun Context.snack(
    @StringRes messageRes: Int,
    length: Int = Snackbar.LENGTH_LONG,
    action: Snackbar.() -> Unit
) {
    snack(resources.getString(messageRes), length, action)
}

inline fun Context.snack(
    message: String,
    length: Int = Snackbar.LENGTH_LONG,
    action: Snackbar.() -> Unit
) {
    val contentView =
        (this as MainActivity).findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
    Snackbar.make(contentView, message, length).apply {
        animationMode = Snackbar.ANIMATION_MODE_SLIDE
        setBackgroundTint(SurfaceColors.SURFACE_3.getColor(context))
        setTextColor(context.getAttr(R.attr.colorOnSurface))
        setActionTextColor(context.getAttr(R.attr.colorPrimary))
        action()
        show()
    }
}

internal fun Snackbar.action(
    @StringRes actionRes: Int,
    listener: (View) -> Unit
) {
    action(view.resources.getString(actionRes), listener)
}

internal fun Snackbar.action(action: String, listener: (View) -> Unit) {
    setAction(action, listener)
}

sealed class SnackbarEvent {
    object Shown : SnackbarEvent()

    object Timeout : SnackbarEvent()
    object Swipe : SnackbarEvent()
    object Action : SnackbarEvent()
    object Consecutive : SnackbarEvent()
    object Manual : SnackbarEvent()
}

inline fun Snackbar.callback(crossinline snackbarEvent: SnackbarEvent.() -> Unit) {
    this.addCallback(object : Snackbar.Callback() {

        override fun onShown(sb: Snackbar?) {
            super.onShown(sb)
            snackbarEvent(SnackbarEvent.Shown)
        }

        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            when (event) {

                Callback.DISMISS_EVENT_ACTION -> snackbarEvent(
                    SnackbarEvent.Action
                )
                Callback.DISMISS_EVENT_CONSECUTIVE -> snackbarEvent(
                    SnackbarEvent.Consecutive
                )
                Callback.DISMISS_EVENT_MANUAL -> snackbarEvent(
                    SnackbarEvent.Manual
                )
                Callback.DISMISS_EVENT_SWIPE -> snackbarEvent(
                    SnackbarEvent.Swipe
                )
                Callback.DISMISS_EVENT_TIMEOUT -> snackbarEvent(
                    SnackbarEvent.Timeout
                )
            }
        }
    })
}

typealias Callback<T> = BaseTransientBottomBar.BaseCallback<T>