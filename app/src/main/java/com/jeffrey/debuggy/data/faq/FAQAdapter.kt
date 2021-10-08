package com.jeffrey.debuggy.data.faq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.databinding.ItemFaqBinding
import com.jeffrey.debuggy.util.Constants

class FAQAdapter(private val data: List<FAQ>) :
    RecyclerView.Adapter<FAQAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_faq, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list: FAQ = data[position]
        with(holder) {
            if (list.title == Constants.NO_TEXT)
                binding.title.visibility = View.GONE else
                binding.title.text = list.title
            if (list.message == Constants.NO_TEXT)
                binding.message.visibility = View.GONE else
                binding.message.text = list.message
        }
    }

    override fun getItemCount() = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemFaqBinding.bind(view)
    }
}