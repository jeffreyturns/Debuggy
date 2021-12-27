package com.jeffrey.debuggy.data.sectioned

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.slot.Slot
import com.jeffrey.debuggy.data.slot.SlotAdapter
import com.jeffrey.debuggy.databinding.ItemCardSectionedBinding
import com.jeffrey.debuggy.databinding.ItemDeveloperBinding
import com.jeffrey.debuggy.ui.base.BaseViewHolder

class CardSectionedAdapter(
    private val data: List<Slot>,
    private val title: String,
    private val context: Context
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.create(parent, ItemCardSectionedBinding::inflate)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder.binding as ItemCardSectionedBinding) {
            if (title.isEmpty()) category.visibility =
                View.GONE else category.text = title
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = SlotAdapter(data)
        }

    }

    override fun getItemCount() = 1
}