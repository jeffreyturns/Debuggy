package com.jeffrey.debuggy.data.switch

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.preference.PreferencesHelper
import com.jeffrey.debuggy.databinding.ItemSwitchBinding
import com.jeffrey.debuggy.ui.base.BaseViewHolder
import com.jeffrey.debuggy.ui.main.MainActivity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SwitchAdapter(private val context: Context) :
    RecyclerView.Adapter<BaseViewHolder>(), KoinComponent {

    private val preference: PreferencesHelper by inject()

    val title
        get() = context.getString(
            if (preference.adbEnabled)
                R.string.title_switch_adb_enabled else R.string.title_switch_adb_disabled
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.create(parent, ItemSwitchBinding::inflate)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder.binding as ItemSwitchBinding) {
            masterSwitch.text = title
            masterSwitch.isChecked = preference.adbEnabled
            masterSwitch.setOnClickListener {
                (context as MainActivity).tcpStatus()
                masterSwitch.text = title
            }

        }
    }

    override fun getItemCount() = 1
}
