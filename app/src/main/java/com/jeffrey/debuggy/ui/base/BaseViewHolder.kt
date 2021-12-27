package com.jeffrey.debuggy.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        inline fun create(
            parent: ViewGroup,
            crossinline block: (inflater: LayoutInflater, container: ViewGroup, attach: Boolean) -> ViewBinding
        ) = BaseViewHolder(block(LayoutInflater.from(parent.context), parent, false))
    }
}