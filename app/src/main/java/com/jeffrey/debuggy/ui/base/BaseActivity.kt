package com.jeffrey.debuggy.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.jeffrey.debuggy.util.aliases.ActivityInflate
import org.koin.core.component.KoinComponent

abstract class BaseActivity<VB : ViewBinding>(
    private val inflate: ActivityInflate<VB>
) : AppCompatActivity(), KoinComponent {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        preSuperCall()
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpViews()
    }

    open fun preSuperCall() {}

    open fun setUpViews() {}
}