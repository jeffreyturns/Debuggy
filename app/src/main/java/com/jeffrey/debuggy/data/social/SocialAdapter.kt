package com.jeffrey.debuggy.data.social

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.databinding.ItemSlotBinding
import com.jeffrey.debuggy.util.Utils

class SocialAdapter(private val slot: List<Social>, private val context: Context) :
    RecyclerView.Adapter<SocialAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_slot, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.summary.visibility = View.GONE
            binding.icon.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_insert_link_24dp,
                    context.theme
                )
            )
            val list: Social = slot[position]
            binding.title.text = list.title
            binding.container.setOnClickListener {
                Utils.openCustomTab(context, list.url)
            }
        }
    }

    override fun getItemCount() = slot.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemSlotBinding.bind(view)
    }
}