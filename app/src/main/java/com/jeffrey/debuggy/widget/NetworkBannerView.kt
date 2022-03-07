package com.jeffrey.debuggy.widget

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.jeffrey.debuggy.databinding.ViewBannerConnectionBinding

class NetworkBannerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private val binding = ViewBannerConnectionBinding.inflate(LayoutInflater.from(context), this)

    init {
        binding.actionButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val panelIntent = Intent(Settings.Panel.ACTION_WIFI)
                ActivityCompat.startActivityForResult(context as Activity, panelIntent, 545, null)
            } else {
                ContextCompat.startActivity(
                    context,
                    Intent(WifiManager.ACTION_PICK_WIFI_NETWORK),
                    null
                )
            }
        }
    }
}
