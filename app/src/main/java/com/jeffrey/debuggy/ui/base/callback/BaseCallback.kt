package com.jeffrey.debuggy.ui.base.callback

/**
 * Callback interface for make call to another Class
 */
interface BaseCallback {

    val action: Action

    val callback: Unit
        get() {
            return action.invoke()
        }
}

typealias Action = () -> Unit