package com.jeffrey.debuggy.ui.base.callback

/**
 * value to use with [BaseCallback] to avoid get() { return {} } construction
 */
val assign: (action: () -> Unit) -> Unit
    get() {
        return { action: () -> Unit ->
            action.invoke()
        }
    }