package com.jeffrey.debuggy.data.switch

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.jeffrey.debuggy.AppDebuggy
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.data.slot.instructionADBDisabledList
import com.jeffrey.debuggy.data.slot.instructionHomeList
import com.jeffrey.debuggy.databinding.ItemSwitchBinding
import com.jeffrey.debuggy.ui.base.holder.BaseViewHolder
import com.jeffrey.debuggy.ui.home.HomeFragment
import com.jeffrey.debuggy.ui.main.MainActivity
import com.jeffrey.debuggy.util.ShapeAppearanceUtils
import com.jeffrey.debuggy.util.system.LogLevel
import com.jeffrey.debuggy.util.system.getAttr
import com.jeffrey.debuggy.util.system.writeLog
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SwitchAdapter(private val context: Context, private val homeFragment: HomeFragment) :
    RecyclerView.Adapter<BaseViewHolder>(), KoinComponent {

    private val preference by inject<PreferencesHelper>()

    val title
        get() = context.getString(
            if (preference.adbEnabled)
                R.string.title_switch_adb_enabled else R.string.title_switch_adb_disabled
        )
    var switch: SwitchMaterial? = null
    var switchSurface: ConstraintLayout? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.create(parent, ItemSwitchBinding::inflate)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder.binding as ItemSwitchBinding) {
            switch = masterSwitch
            switchSurface = masterSurface
            rootError.visibility = if (AppDebuggy.root) View.GONE else View.VISIBLE

            masterSwitch.isEnabled = AppDebuggy.root

            if (AppDebuggy.root) {
                masterSurface.setBackgroundColor(
                    if (preference.adbEnabled) context.getAttr(
                        com.google.android.material.R.attr.colorPrimaryContainer
                    ) else context.getAttr(
                        com.google.android.material.R.attr.colorSurfaceVariant
                    )
                )
                masterSwitch.setTextColor(
                    if (preference.adbEnabled) context.getAttr(
                        com.google.android.material.R.attr.colorOnPrimaryContainer
                    ) else context.getAttr(
                        com.google.android.material.R.attr.colorOnSurfaceVariant
                    )
                )
            } else {
                masterCard.setCardBackgroundColor(context.getAttr(com.google.android.material.R.attr.colorErrorContainer))
                masterSwitch.setTextColor(context.getAttr(com.google.android.material.R.attr.colorOnErrorContainer))
                errorRootCard.shapeAppearanceModel = ShapeAppearanceUtils.getBottomShape(context)
                masterCard.shapeAppearanceModel = ShapeAppearanceUtils.getTopShape(context)
                masterSwitch.thumbTintList = ContextCompat.getColorStateList(
                    context,
                    R.color.selector_master_switch_thumb_root_unavailable
                )
                masterSwitch.trackTintList = ContextCompat.getColorStateList(
                    context,
                    R.color.selector_master_switch_track_root_unavailable
                )
            }

            masterSwitch.text =
                if (AppDebuggy.root) title else context.getString(R.string.title_switch_adb_unavailable)
            masterSwitch.isChecked = preference.adbEnabled

            masterSwitch.setOnClickListener {
                (context as MainActivity).tcpStatus()
                if (preference.adbEnabled) homeFragment.instructionAdapter?.slotAdapter?.items =
                    instructionHomeList(context, preference.port)
                else homeFragment.instructionAdapter?.slotAdapter?.items =
                    instructionADBDisabledList(context)
                masterSwitch.text = title
                changeColor(masterSurface, masterSwitch)
            }
        }
    }

    fun updateUI() {
        if (preference.adbEnabled) homeFragment.instructionAdapter?.slotAdapter?.items =
            instructionHomeList(context, preference.port)
        else homeFragment.instructionAdapter?.slotAdapter?.items =
            instructionADBDisabledList(context)
        switch?.text = title
        switch?.isChecked = preference.adbEnabled
        try {
            changeColor(switchSurface!!, switch!!)
        } catch (e: NullPointerException) {
            writeLog(LogLevel.ERROR, "View not must be null", e)
        }
    }

    private fun changeColor(surface: ConstraintLayout, switchText: SwitchMaterial) {
        val fromColorContainer =
            if (preference.adbEnabled) context.getAttr(com.google.android.material.R.attr.colorPrimaryContainer) else context.getAttr(
                com.google.android.material.R.attr.colorSurfaceVariant
            )
        val toColorContainer =
            if (!preference.adbEnabled) context.getAttr(com.google.android.material.R.attr.colorPrimaryContainer) else context.getAttr(
                com.google.android.material.R.attr.colorSurfaceVariant
            )

        val fromColorText =
            if (preference.adbEnabled) context.getAttr(com.google.android.material.R.attr.colorOnPrimaryContainer) else context.getAttr(
                com.google.android.material.R.attr.colorOnSurfaceVariant
            )
        val toColorText =
            if (!preference.adbEnabled) context.getAttr(com.google.android.material.R.attr.colorOnPrimaryContainer) else context.getAttr(
                com.google.android.material.R.attr.colorOnSurfaceVariant
            )

        val colorAnimationText: ValueAnimator =
            ValueAnimator.ofObject(
                ArgbEvaluator(),
                toColorText,
                fromColorText
            )

        val colorAnimationContainer: ValueAnimator =
            ValueAnimator.ofObject(
                ArgbEvaluator(),
                toColorContainer,
                fromColorContainer
            )
        colorAnimationContainer.duration = 200
        colorAnimationText.duration = 200
        colorAnimationText.addUpdateListener { animator ->
            switchText.setTextColor(
                animator.animatedValue as Int
            )
        }
        colorAnimationContainer.addUpdateListener { animator ->
            surface.setBackgroundColor(
                animator.animatedValue as Int
            )
        }
        colorAnimationContainer.start()
        colorAnimationText.start()
    }

    override fun getItemCount() = 1
}
