package com.jeffrey.debuggy.data.about

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.data.developer.Developer
import com.jeffrey.debuggy.data.developer.DeveloperAdapter
import com.jeffrey.debuggy.databinding.ItemDeveloperCardBinding
import com.jeffrey.debuggy.ui.base.holder.BaseViewHolder

class DeveloperCardAdapter(
    private val data: List<Developer>,
    private val title: String,
    private val context: Context
) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.create(parent, ItemDeveloperCardBinding::inflate)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder.binding as ItemDeveloperCardBinding) {
            category.text = title
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = DeveloperAdapter(data, context)
        }
    }

    override fun getItemCount() = 1
}