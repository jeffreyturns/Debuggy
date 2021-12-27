package com.jeffrey.debuggy.data.slot

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.databinding.ItemSlotBinding
import com.jeffrey.debuggy.ui.base.BaseViewHolder
import com.jeffrey.debuggy.util.Constants

class SlotAdapter(private val slot: List<Slot>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.create(parent, ItemSlotBinding::inflate)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val list: Slot = slot[position]

        with(holder.binding as ItemSlotBinding) {
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

    override fun getItemCount() = slot.size
}