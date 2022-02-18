package com.jeffrey.debuggy.data.slot

import android.content.Context
import android.provider.Settings
import com.jeffrey.debuggy.AppDebuggy
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.ui.setting.about.LibrariesAction
import com.jeffrey.debuggy.util.Constants
import com.jeffrey.debuggy.util.Utils

fun informationHomeList(context: Context, port: String): List<Slot> {
    return listOf(
        Slot(
            context.resources.getString(R.string.title_adb_daemon),
            if (AppDebuggy.root) {
                if ((AppDebuggy.daemonStatus).replace("\n", "")
                        .replace("\r", "") == "running"
                ) context.resources.getString(R.string.summary_adb_daemon_running) else context.resources.getString(
                    R.string.summary_adb_daemon_stopped
                )
            } else {
                context.resources.getString(R.string.summary_adb_daemon_with_root)
            },
            R.drawable.ic_chip_24dp
        ),
        Slot(
            context.resources.getString(R.string.title_adbd_listening_port),
            context.resources.getString(
                R.string.summary_adbd_listening_port,
                port
            ),
            R.drawable.ic_usb_24dp
        ),
        Slot(
            context.resources.getString(R.string.title_root_access),
            if (AppDebuggy.root) context.resources.getString(R.string.summary_root_access_granted) else context.resources.getString(
                R.string.summary_root_access_declined
            ),
            R.drawable.ic_hashtag_24dp
        ),
        Slot(
            context.resources.getString(R.string.title_adb_over_usb),
            if (Settings.Global.getInt(
                    context.contentResolver,
                    Settings.Global.ADB_ENABLED,
                    0
                ) == 1
            ) context.resources.getString(R.string.summary_adb_over_usb_enabled) else context.resources.getString(
                R.string.summary_adb_over_usb_disabled
            ),
            R.drawable.ic_adb_24dp
        )
    )
}

fun instructionHomeList(context: Context, port: String): List<Slot> {
    return listOf(
        Slot(
            summary = context.resources.getString(R.string.summary_connect_to_device)
        ),
        Slot(
            summary = if (Utils.deviceIpv6Address != Constants.UNDEFINED_TEXT) context.resources.getString(
                R.string.summary_run_command,
                Utils.deviceIpv6Address,
                port
            ) else context.getString(R.string.message_ip_unable_determine)
        )
    )
}

fun instructionADBDisabledList(context: Context): List<Slot> {
    return listOf(
        Slot(
            summary = context.getString(R.string.message_instruction_when_adb_enabled)
        )
    )
}

fun descriptionAboutList(context: Context): List<Slot> {
    return listOf(
        Slot(
            title = context.getString(R.string.title_app_description),
            icon = R.drawable.ic_info_24dp
        )
    )
}

fun informationAboutList(context: Context, action: SlotAction): List<Slot> {
    return listOf(
        Slot(
            title = context.getString(R.string.title_changelog),
            icon = R.drawable.ic_new_releases_24dp,
            action = action::callChangelog
        ),
        Slot(
            title = context.getString(R.string.title_libraries),
            icon = R.drawable.ic_local_library_24dp,
            action = action::callLibraries
        ),
        Slot(
            title = context.getString(R.string.title_updates),
            icon = R.drawable.ic_save_alt_24dp,
            action = action::callUpdates
        )
    )
}

fun libsLibrariesList(action: LibrariesAction): List<Slot> {
    return listOf(
        Slot(
            title = "AndroidX / AndroidX",
            action = action::callAndroidX
        ),
        Slot(
            title = "Material Components / Material Components Android",
            action = action::callMaterialComponents
        ),
        Slot(
            title = "Coil-kt / Coil",
            action = action::callCoilKtx
        ),
        Slot(
            title = "InsertKoinIO / Koin",
            action = action::callKoin
        ),
        Slot(
            title = "Kotlin / Kotlinx.coroutines",
            action = action::callCoroutines
        )
    )
}