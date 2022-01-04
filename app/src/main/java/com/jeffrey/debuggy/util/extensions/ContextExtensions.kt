package com.jeffrey.debuggy.util.extensions

import android.content.Context
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.jeffrey.debuggy.R

fun Context.toast(text: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text.orEmpty(), duration).show()
}

fun Context.navigationType(): Int {
    val resourceId =
        this.resources.getIdentifier("config_navBarInteractionMode", "integer", "android")
    return if (resourceId > 0) {
        this.resources.getInteger(resourceId)
    } else 0
}

fun Context.getAttr(resId: Int): Int {
    val typedValue = TypedValue()
    this.theme.resolveAttribute(resId, typedValue, true)
    return typedValue.data
}

fun Context.navigate(destination: Int) = Navigation.findNavController(
    (this as AppCompatActivity),
    R.id.nav_host_fragment
).navigate(destination)

fun Context.navigateUp() = Navigation.findNavController(
    (this as AppCompatActivity),
    R.id.nav_host_fragment
).navigateUp()


fun Context.getCurrentFragment(): Fragment {
    return (this as AppCompatActivity).supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        ?.childFragmentManager?.fragments?.get(0)!!
}

fun Context.getNavController() =
    ((this as AppCompatActivity).supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

