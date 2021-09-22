package com.jeffrey.debuggy.data.about

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.developer.Developer
import com.jeffrey.debuggy.data.developer.DeveloperAdapter
import com.jeffrey.debuggy.databinding.ItemDeveloperCardBinding

class DeveloperCardAdapter(
    private val data: List<Developer>,
    private val category: String,
    private val context: Context
) :
    RecyclerView.Adapter<DeveloperCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_developer_card, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.category.text = category
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = DeveloperAdapter(data, context)
        }
    }

    override fun getItemCount() = 1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemDeveloperCardBinding.bind(view)
    }
}