package com.jeffrey.debuggy.data.social

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.databinding.ItemSlotBinding
import com.jeffrey.debuggy.ui.base.holder.BaseViewHolder
import com.jeffrey.debuggy.util.Utils

class SocialAdapter(private val slot: List<Social>, private val context: Context) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.create(parent, ItemSlotBinding::inflate)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val list: Social = slot[position]

        with(holder.binding as ItemSlotBinding) {
            container.apply {
                val outValue = TypedValue()
                context.theme.resolveAttribute(
                    android.R.attr.selectableItemBackground,
                    outValue,
                    true
                )
                container.apply {
                    setBackgroundResource(outValue.resourceId)
                    isClickable = true
                    isFocusable = true
                }
            }
            summary.visibility = View.GONE
            icon.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_insert_link_24dp,
                    context.theme
                )
            )
            title.text = list.title
            container.setOnClickListener {
                Utils.openCustomTab(context, list.url)
            }
        }
    }

    override fun getItemCount() = slot.size
}