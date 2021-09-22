package com.jeffrey.debuggy.data.developer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.social.SocialAdapter
import com.jeffrey.debuggy.databinding.ItemDeveloperBinding

class DeveloperAdapter(
    private val slot: List<Developer>,
    private val context: Context
) :
    RecyclerView.Adapter<DeveloperAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_developer, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list: Developer = slot[position]
        with(holder) {
            binding.username.text = list.username
            binding.status.text = list.status
            binding.avatar.load(list.avatarUrl)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = SocialAdapter(list.socials, context)
        }

    }

    override fun getItemCount() = slot.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemDeveloperBinding.bind(view)
    }
}