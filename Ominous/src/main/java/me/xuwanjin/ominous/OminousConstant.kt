package me.xuwanjin.ominous

class OminousConstant {
    companion object {
        const val LOG_COMMAND_WITHOUT_EVENT_LOG = "logcat"
        const val LOG_COMMAND_WITH_EVENT_LOG = "logcat -b all "

        const val CURRENT_PERIOD_LOG = "current_period_log"
        const val SELECTED_PERIOD_LOG = "selected_period_log"
        const val SELECT_CURRENT_DAY_LOG = "select_current_day_log"
        const val SEND_TO_EMAIL = "send_to_email"
        const val MODIFY_EMAIL_RECEIVER = "modify_email_receiver"
        const val NO_SHOW_AGAIN = "no_show_again"

        const val LOG_SAVE_PATH_DATA = "log_save_path_data"
        const val LOG_SAVE_PATH_ANDROID_DATA ="log_save_path_android_data"
        const val LOG_SAVE_PATH_SDCARD ="log_save_path_sdcard"
    }
}