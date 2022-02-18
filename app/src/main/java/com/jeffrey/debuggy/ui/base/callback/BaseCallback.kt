package com.jeffrey.debuggy.ui.base.callback

/**
 * Callback interface for make call to another Class
 */
interface BaseCallback {

    val action: () -> Unit

    val callback: Unit
        get() {
            return action.invoke()
        }
}
