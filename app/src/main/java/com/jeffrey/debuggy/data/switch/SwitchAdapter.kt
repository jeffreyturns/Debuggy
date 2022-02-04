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
import com.jeffrey.debuggy.App
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.data.slot.Slot
import com.jeffrey.debuggy.data.slot.instructionHomeList
import com.jeffrey.debuggy.databinding.ItemSwitchBinding
import com.jeffrey.debuggy.ui.base.BaseViewHolder
import com.jeffrey.debuggy.ui.home.HomeFragment
import com.jeffrey.debuggy.ui.main.MainActivity
import com.jeffrey.debuggy.util.ShapeAppearanceUtils
import com.jeffrey.debuggy.util.extensions.getAttr
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SwitchAdapter(private val context: Context, private val homeFragment: HomeFragment) :
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
            rootError.visibility = if (App.root) View.GONE else View.VISIBLE

            masterSwitch.isEnabled = App.root

            if (App.root) {
                masterSurface.setBackgroundColor(
                    if (preference.adbEnabled) context.getAttr(
                        R.attr.colorPrimaryContainer
                    ) else context.getAttr(
                        R.attr.colorSurfaceVariant
                    )
                )
                masterSwitch.setTextColor(
                    if (preference.adbEnabled) context.getAttr(
                        R.attr.colorOnPrimaryContainer
                    ) else context.getAttr(
                        R.attr.colorOnSurfaceVariant
                    )
                )
            } else {
                masterCard.setCardBackgroundColor(context.getAttr(R.attr.colorErrorContainer))
                masterSwitch.setTextColor(context.getAttr(R.attr.colorOnErrorContainer))
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
                if (App.root) title else context.getString(R.string.title_switch_adb_unavailable)
            masterSwitch.isChecked = preference.adbEnabled

            masterSwitch.setOnClickListener {
                (context as MainActivity).tcpStatus()
                if (preference.adbEnabled) homeFragment.instructionAdapter?.slotAdapter?.items =
                    instructionHomeList(context, preference.port)
                else homeFragment.instructionAdapter?.slotAdapter?.items = listOf(
                    Slot(
                        summary = context.getString(R.string.message_instruction_when_adb_enabled)
                    )
                )
                masterSwitch.text = title
                changeColor(masterSurface, masterSwitch)
            }
        }
    }

    private fun changeColor(surface: ConstraintLayout, switchText: SwitchMaterial) {
        val fromColorContainer =
            if (preference.adbEnabled) context.getAttr(R.attr.colorPrimaryContainer) else context.getAttr(
                R.attr.colorSurfaceVariant
            )
        val toColorContainer =
            if (!preference.adbEnabled) context.getAttr(R.attr.colorPrimaryContainer) else context.getAttr(
                R.attr.colorSurfaceVariant
            )

        val fromColorText =
            if (preference.adbEnabled) context.getAttr(R.attr.colorOnPrimaryContainer) else context.getAttr(
                R.attr.colorOnSurfaceVariant
            )
        val toColorText =
            if (!preference.adbEnabled) context.getAttr(R.attr.colorOnPrimaryContainer) else context.getAttr(
                R.attr.colorOnSurfaceVariant
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
