package com.jeffrey.debuggy.data.developer

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jeffrey.debuggy.data.social.SocialAdapter
import com.jeffrey.debuggy.databinding.ItemDeveloperBinding
import com.jeffrey.debuggy.ui.base.BaseViewHolder

class DeveloperAdapter(
    private val slot: List<Developer>,
    private val context: Context
) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.create(parent, ItemDeveloperBinding::inflate)


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val list: Developer = slot[position]
        with(holder.binding as ItemDeveloperBinding) {
            username.text = list.username
            status.text = list.status
            avatar.load(list.avatarUrl)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = SocialAdapter(list.socials, context)
        }

    }

    override fun getItemCount() = slot.size
}