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

class CardSectionedAdapter(
    private val data: List<Slot>,
    private val category: String,
    private val context: Context
) : RecyclerView.Adapter<CardSectionedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_sectioned, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            if (category.isEmpty()) binding.category.visibility =
                View.GONE else binding.category.text =
                category
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = SlotAdapter(data)
        }

    }

    override fun getItemCount() = 1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCardSectionedBinding.bind(view)
    }
}