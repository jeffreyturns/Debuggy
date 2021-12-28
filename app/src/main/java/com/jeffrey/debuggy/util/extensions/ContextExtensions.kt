package com.jeffrey.debuggy.util.extensions

import android.content.Context
import android.util.TypedValue
import android.widget.Toast


fun Context.toast(text: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text.orEmpty(), duration).show()
}

fun Context.navigationType(): Int {
    val resourceId =
        this.resources.getIdentifier("config_navBarInteractionMode", "integer", "android")
    return if (resourceId > 0) {
        this.resources.getInteger(resourceId)
    } else 0
}

fun Context.getAttr(resId: Int): Int {
    val typedValue = TypedValue()
    this.theme.resolveAttribute(resId, typedValue, true)
    return typedValue.data
}
