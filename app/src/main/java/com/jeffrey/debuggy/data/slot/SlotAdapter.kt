package com.jeffrey.debuggy.data.slot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.databinding.ItemSlotBinding
import com.jeffrey.debuggy.utils.Constants

class SlotAdapter(private val slot: List<Slot>) :
    RecyclerView.Adapter<SlotAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_slot, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list: Slot = slot[position]
        with(holder) {
            if (list.icon == Constants.NO_ICON_SLOT)
                binding.slot.visibility = View.GONE else
                binding.icon.setImageResource(list.icon)
            if (list.title == Constants.NO_TEXT)
                binding.title.visibility = View.GONE else
                binding.title.text = list.title
            if (list.summary == Constants.NO_TEXT)
                binding.summary.visibility = View.GONE else
                binding.summary.text = list.summary
            binding.container.setOnClickListener {
                list.action?.invoke()
            }
        }
    }

    override fun getItemCount() = slot.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemSlotBinding.bind(view)
    }
}