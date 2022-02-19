package com.jeffrey.debuggy.data.slot

import com.jeffrey.debuggy.util.Constants

data class Slot(
    val title: String = Constants.NO_TEXT,
    val summary: String = Constants.NO_TEXT,
    val icon: Int = Constants.NO_ICON_SLOT,
    val clickable: Boolean = false,
    val action: (() -> Unit)? = null
)