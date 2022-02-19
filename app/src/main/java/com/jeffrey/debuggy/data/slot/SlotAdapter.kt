package com.jeffrey.debuggy.data.slot

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.databinding.ItemSlotBinding
import com.jeffrey.debuggy.ui.base.BaseViewHolder
import com.jeffrey.debuggy.ui.base.recyclerview.UpdatableAdapter
import com.jeffrey.debuggy.util.Constants
import kotlin.properties.Delegates


class SlotAdapter(private val context: Context) :
    RecyclerView.Adapter<BaseViewHolder>(), UpdatableAdapter {

    var items: List<Slot> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o.title == n.title || o.summary == n.summary }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.create(parent, ItemSlotBinding::inflate)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val list: Slot = items[position]

        with(holder.binding as ItemSlotBinding) {
            if (list.clickable) {
                val outValue = TypedValue()
                context.theme.resolveAttribute(
                    android.R.attr.selectableItemBackground,
                    outValue,
                    true
                )
                container.apply {
                    setBackgroundResource(outValue.resourceId)
                    isClickable = true
                    isFocusable = true
                }
            }
            if (list.icon == Constants.NO_ICON_SLOT)
                slot.visibility = View.GONE else
                icon.setImageResource(list.icon)
            if (list.title == Constants.NO_TEXT)
                title.visibility = View.GONE else
                title.text = list.title
            if (list.summary == Constants.NO_TEXT)
                summary.visibility = View.GONE else
                summary.text = list.summary
            container.setOnClickListener {
                list.action?.invoke()
            }
        }
    }

    override fun getItemCount() = items.size
}