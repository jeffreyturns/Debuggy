package com.jeffrey.debuggy.util

import android.content.Context
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.jeffrey.debuggy.R

object ShapeAppearanceUtils {
    fun getStartShape(context: Context): ShapeAppearanceModel {
        return ShapeAppearanceModel.builder()
            .setAllCornerSizes(context.resources.getDimension(R.dimen.keyline_24))
            .setBottomLeftCorner(
                CornerFamily.ROUNDED,
                context.resources.getDimension(R.dimen.keyline_4)
            )
            .setBottomRightCorner(
                CornerFamily.ROUNDED,
                context.resources.getDimension(R.dimen.keyline_4)
            )
            .build()
    }

    fun getInsideShape(context: Context): ShapeAppearanceModel {
        return ShapeAppearanceModel.builder()
            .setAllCornerSizes(context.resources.getDimension(R.dimen.keyline_4))
            .build()
    }

    fun getEndShape(context: Context): ShapeAppearanceModel {
        return ShapeAppearanceModel.builder()
            .setAllCornerSizes(context.resources.getDimension(R.dimen.keyline_24))
            .setTopLeftCorner(
                CornerFamily.ROUNDED,
                context.resources.getDimension(R.dimen.keyline_4)
            )
            .setTopRightCorner(
                CornerFamily.ROUNDED,
                context.resources.getDimension(R.dimen.keyline_4)
            )
            .build()
    }

    fun getRoundShape(context: Context): ShapeAppearanceModel {
        return ShapeAppearanceModel.builder()
            .setAllCornerSizes(context.resources.getDimension(R.dimen.keyline_24))
            .build()
    }

    fun getTopShape(context: Context): ShapeAppearanceModel {
        return ShapeAppearanceModel.builder()
            .setAllCornerSizes(context.resources.getDimension(R.dimen.keyline_24))
            .setBottomLeftCorner(
                CornerFamily.ROUNDED,
                context.resources.getDimension(R.dimen.keyline_4)
            )
            .setBottomRightCorner(
                CornerFamily.ROUNDED,
                context.resources.getDimension(R.dimen.keyline_4)
            )
            .build()
    }

    fun getBottomShape(context: Context): ShapeAppearanceModel {
        return ShapeAppearanceModel.builder()
            .setAllCornerSizes(context.resources.getDimension(R.dimen.keyline_24))
            .setTopLeftCorner(
                CornerFamily.ROUNDED,
                context.resources.getDimension(R.dimen.keyline_4)
            )
            .setTopRightCorner(
                CornerFamily.ROUNDED,
                context.resources.getDimension(R.dimen.keyline_4)
            )
            .build()
    }
}