package com.jeffrey.debuggy.util

import android.content.Context
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.jeffrey.debuggy.R

object ShapeAppearanceUtils {
    fun getTopShape(context: Context): ShapeAppearanceModel {
        return ShapeAppearanceModel.builder()
            .setAllCornerSizes(context.resources.getDimension(R.dimen.corners_size))
            .setBottomLeftCorner(CornerFamily.ROUNDED, context.resources.getDimension(R.dimen.corners_4))
            .setBottomRightCorner(CornerFamily.ROUNDED, context.resources.getDimension(R.dimen.corners_4))
            .build()
    }

    fun getInsideShape(context: Context): ShapeAppearanceModel {
        return ShapeAppearanceModel.builder()
            .setAllCornerSizes(context.resources.getDimension(R.dimen.corners_4))
            .build()
    }

    fun getBottomShape(context: Context): ShapeAppearanceModel {
        return ShapeAppearanceModel.builder()
            .setAllCornerSizes(context.resources.getDimension(R.dimen.corners_size))
            .setTopLeftCorner(CornerFamily.ROUNDED, context.resources.getDimension(R.dimen.corners_4))
            .setTopRightCorner(CornerFamily.ROUNDED, context.resources.getDimension(R.dimen.corners_4))
            .build()
    }

    fun getRoundShape(context: Context): ShapeAppearanceModel {
        return ShapeAppearanceModel.builder()
            .setAllCornerSizes(context.resources.getDimension(R.dimen.corners_size))
            .build()
    }
}