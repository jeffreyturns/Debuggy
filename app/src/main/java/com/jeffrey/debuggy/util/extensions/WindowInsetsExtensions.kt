package com.jeffrey.debuggy.util.extensions

import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins

fun View.addInsetPaddings(
    left: Boolean = false,
    top: Boolean = false,
    right: Boolean = false,
    bottom: Boolean = false
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->

            val leftInset =
                if (left) insets.getInsets(WindowInsetsCompat.Type.systemBars()).left else 0
            val topInset =
                if (top) insets.getInsets(WindowInsetsCompat.Type.systemBars()).top else 0
            val rightInset =
                if (right) insets.getInsets(WindowInsetsCompat.Type.systemBars()).right else 0
            val bottomInset =
                if (bottom) insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom else 0

            this.setPadding(
                leftInset + this.paddingLeft,
                topInset + this.paddingTop,
                rightInset + this.paddingRight,
                bottomInset + this.paddingBottom
            )
            insets
        }
    }
}

fun View.addInsetMargins(
    left: Boolean = false,
    top: Boolean = false,
    right: Boolean = false,
    bottom: Boolean = false
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->

            val leftInset =
                if (left) insets.getInsets(WindowInsetsCompat.Type.systemBars()).left else 0
            val topInset =
                if (top) insets.getInsets(WindowInsetsCompat.Type.systemBars()).top else 0
            val rightInset =
                if (right) insets.getInsets(WindowInsetsCompat.Type.systemBars()).right else 0
            val bottomInset =
                if (bottom) insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom else 0

            this.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                updateMargins(
                    leftInset + this.leftMargin,
                    topInset + this.topMargin,
                    rightInset + this.rightMargin,
                    bottomInset + this.bottomMargin
                )
            }

            insets
        }
    }
}