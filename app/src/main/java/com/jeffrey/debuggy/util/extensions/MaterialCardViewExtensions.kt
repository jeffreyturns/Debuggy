package com.jeffrey.debuggy.util.extensions

import android.content.Context
import com.google.android.material.card.MaterialCardView
import com.jeffrey.debuggy.util.Constants
import com.jeffrey.debuggy.util.ShapeAppearanceUtils

fun MaterialCardView.setMonetLikeShapeAppearance(shape_key: Int, context: Context) {
    this.shapeAppearanceModel =
        when (shape_key) {
            Constants.SHAPE_KEY_TOP -> {
                ShapeAppearanceUtils.getTopShape(context)
            }
            Constants.SHAPE_KEY_INSIDE -> {
                ShapeAppearanceUtils.getInsideShape(context)
            }
            Constants.SHAPE_KEY_BOTTOM -> {
                ShapeAppearanceUtils.getBottomShape(context)
            }
            else -> {
                ShapeAppearanceUtils.getRoundShape(context)
            }
        }
}