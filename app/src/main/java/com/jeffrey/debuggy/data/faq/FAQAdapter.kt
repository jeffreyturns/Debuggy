package com.jeffrey.debuggy.data.faq

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.databinding.ItemFaqBinding
import com.jeffrey.debuggy.ui.base.holder.BaseViewHolder
import com.jeffrey.debuggy.util.Constants

class FAQAdapter(private val data: List<FAQ>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.create(parent, ItemFaqBinding::inflate)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val list: FAQ = data[position]

        with(holder.binding as ItemFaqBinding) {
            if (list.title == Constants.NO_TEXT)
                title.visibility = View.GONE else
                title.text = list.title
            if (list.message == Constants.NO_TEXT)
                message.visibility = View.GONE else
                message.text = list.message
        }
    }

    override fun getItemCount() = data.size

}