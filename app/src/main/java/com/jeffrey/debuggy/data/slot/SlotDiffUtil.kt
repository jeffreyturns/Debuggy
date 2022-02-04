package com.jeffrey.debuggy.data.slot

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil

class SlotDiffUtil(private val oldList: List<Slot>, private val newList: List<Slot>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title === newList[newItemPosition].title &&
                oldList[oldItemPosition].summary === newList[newItemPosition].summary
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_, value, name) = oldList[oldPosition]
        val (_, value1, name1) = newList[newPosition]

        return name == name1 && value == value1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}