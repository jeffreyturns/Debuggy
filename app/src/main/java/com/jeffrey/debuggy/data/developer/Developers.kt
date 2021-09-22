package com.jeffrey.debuggy.data.developer

import android.content.Context
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.social.socialsJeffreyList

fun developersList(context: Context): List<Developer> {
    return listOf(
        Developer(
            "Jeffrey Turns",
            context.getString(R.string.jeffrey_status),
            "https://avatars1.githubusercontent.com/u/51068771?s=460&u=9558ff3c9db73237417b69b8b6de7b4d2872ce82&v=4",
            socialsJeffreyList(context)
        )
    )
}