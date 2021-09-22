package com.jeffrey.debuggy.data.faq

import android.content.Context
import com.jeffrey.debuggy.R

fun faqList(context: Context): List<FAQ> {
    return listOf(
        FAQ(
            context.getString(R.string.title_without_root),
            context.getString(R.string.message_without_root)
        ),
        FAQ(
            context.getString(R.string.title_connect_via_pc),
            context.getString(R.string.message_connect_via_pc)
        ),
        FAQ(
            context.getString(R.string.title_connect_more_devices),
            context.getString(R.string.message_connect_more_devices)
        ),
        FAQ(
            context.getString(R.string.title_after_load_not_work),
            context.getString(R.string.message_after_load_not_work)
        )
    )
}