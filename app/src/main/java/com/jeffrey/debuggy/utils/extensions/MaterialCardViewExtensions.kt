package com.jeffrey.debuggy.utils.extensions

import android.content.Context
import com.google.android.material.card.MaterialCardView
import com.jeffrey.debuggy.utils.Constants
import com.jeffrey.debuggy.utils.ShapeAppearanceUtil

fun MaterialCardView.setMonetLikeShapeAppearance(shape_key: Int, context: Context) {
    this.shapeAppearanceModel =
        when (shape_key) {
            Constants.SHAPE_KEY_TOP -> {
                ShapeAppearanceUtil.getTopShape(context)
            }
            Constants.SHAPE_KEY_INSIDE -> {
                ShapeAppearanceUtil.getInsideShape(context)
            }
            Constants.SHAPE_KEY_BOTTOM -> {
                ShapeAppearanceUtil.getBottomShape(context)
            }
            else -> {
                ShapeAppearanceUtil.getRoundShape(context)
            }
        }
}