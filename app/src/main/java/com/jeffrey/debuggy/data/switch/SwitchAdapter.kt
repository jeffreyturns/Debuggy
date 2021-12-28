package com.jeffrey.debuggy.data.switch

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.databinding.ItemSwitchBinding
import com.jeffrey.debuggy.ui.base.BaseViewHolder
import com.jeffrey.debuggy.ui.main.MainActivity
import com.jeffrey.debuggy.util.extensions.getAttr
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class SwitchAdapter(private val context: Context) :
    RecyclerView.Adapter<BaseViewHolder>(), KoinComponent {

    private val preference: PreferencesHelper by inject()

    val title
        get() = context.getString(
            if (preference.adbEnabled)
                R.string.title_switch_adb_enabled else R.string.title_switch_adb_disabled
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.create(parent, ItemSwitchBinding::inflate)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder.binding as ItemSwitchBinding) {
            masterSurface.setBackgroundColor(if (preference.adbEnabled) context.getAttr(R.attr.colorSecondary) else Color.WHITE)
            masterSwitch.text = title
            masterSwitch.isChecked = preference.adbEnabled
            masterSwitch.setOnClickListener {
                (context as MainActivity).tcpStatus()
                masterSwitch.text = title
                changeColor(masterSurface)
            }
        }
    }

    private fun changeColor(surface: ConstraintLayout) {
        val fromColor =
            if (preference.adbEnabled) context.getAttr(R.attr.colorSecondary) else Color.WHITE
        val toColor =
            if (!preference.adbEnabled) context.getAttr(R.attr.colorSecondary) else Color.WHITE

        val colorAnimation: ValueAnimator =
            ValueAnimator.ofObject(
                ArgbEvaluator(),
                toColor,
                fromColor
            )
        colorAnimation.duration = 500
        colorAnimation.addUpdateListener { animator ->
            surface.setBackgroundColor(
                animator.animatedValue as Int
            )
        }
        colorAnimation.start()
    }

    override fun getItemCount() = 1
}
