package me.xuwanjin.ominous.catcher

import android.annotation.SuppressLint
import me.xuwanjin.ominous.utils.getDate
import me.xuwanjin.ominous.utils.getDateWithHours
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

class OminousCatcher(
        private val mLogSavePath: String,
        private val mPid: Int,
) : Runnable {

    override fun run() {
        val logCatcherCmd = "logcat | grep \"($mPid)\""

        val logCatcherProcess: Process = Runtime.getRuntime().exec(logCatcherCmd)

        var bufferedReader = BufferedReader(InputStreamReader(logCatcherProcess.inputStream))
        var logLine: String
        while ((bufferedReader.readLine().also { logLine = it }) != null) {
            if (logLine.isEmpty()) {
                continue
            }
            if (logLine.contains(mPid.toString(), true)) {
                logWriteToFile(logLine)

            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun logWriteToFile(logLine: String) {
        val logCatcherFile: File = File(mLogSavePath)
        if (!logCatcherFile.exists()) {
            logCatcherFile.mkdirs()
        }
        /**
         *  准备该天 log 的存放路径,
         */
        val logPathWithDate = mLogSavePath + File.separator + getDate()
        val logPathWithDateFile = File(logPathWithDate)
        if (!logPathWithDateFile.exists()) {
            logPathWithDateFile.mkdirs()
        }

        /**
         *  准备该一小时的 log 存放的路径
         */
        val logPathWithHours = logPathWithDate + File.separator + getDateWithHours()
        val logPathWithHoursFile = File(logPathWithHours)
        if (!logPathWithDateFile.exists()) {
            logPathWithHoursFile.createNewFile()
        }
    }
}