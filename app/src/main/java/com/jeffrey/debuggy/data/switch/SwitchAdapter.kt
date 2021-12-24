package com.jeffrey.debuggy.data.switch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.databinding.ItemSwitchBinding
import com.jeffrey.debuggy.ui.main.MainActivity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SwitchAdapter(private val context: Context) :
    RecyclerView.Adapter<SwitchAdapter.ViewHolder>(), KoinComponent {

    private val preference: PreferencesHelper by inject()

    val title
        get() = context.getString(
            if (preference.adbEnabled)
                R.string.title_switch_adb_enabled else R.string.title_switch_adb_disabled
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_switch, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.masterSwitch.text = title
            binding.masterSwitch.isChecked = preference.adbEnabled
            binding.masterSwitch.setOnClickListener {
                (context as MainActivity).tcpStatus()
                binding.masterSwitch.text = title
            }

        }
    }

    override fun getItemCount() = 1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemSwitchBinding.bind(view)
    }
}