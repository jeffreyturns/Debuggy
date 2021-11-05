package com.jeffrey.debuggy.data.social

import android.content.Context
import com.jeffrey.debuggy.R

fun socialsJeffreyList(context: Context): List<Social> {
    return listOf(
        Social(
            context.getString(R.string.title_developer_portfolio),
            "https://jeffreyturns.vercel.app/",
        ),
        Social(
            "Github",
            "https://github.com/Jeffrey01596",
        ),
    )
}