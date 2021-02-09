package me.xuwanjin.ominous.catcher

import android.util.Log
import me.xuwanjin.ominous.OminousConstant.Companion.LOG_COMMAND_WITH_EVENT_LOG
import me.xuwanjin.ominous.utils.getDate
import me.xuwanjin.ominous.utils.getDateWithHours
import java.io.*

class OminousCatcher(
    private val mLogSavePath: String,
    private val mPid: Int,
) : Runnable {

    override fun run() {
        val logCatcherCmd = "$LOG_COMMAND_WITH_EVENT_LOG | grep \"($mPid)\""

        val logCatcherProcess: Process = Runtime.getRuntime().exec(logCatcherCmd)

        val bufferedReader = BufferedReader(InputStreamReader(logCatcherProcess.inputStream))
        var logLine: String
        while ((bufferedReader.readLine().also { logLine = it }) != null) {
            if (logLine.isEmpty()) {
                continue
            }
            if (logLine.contains(mPid.toString(), true)) {
                val logFilePath = prepareLogFilePath()
                writeLogToFile(logLine, logFilePath)
            }
        }
    }

    /**
     *  准备log保存的路径
     *  @return 返回 log 生成的 file
     */
    private fun prepareLogFilePath(): File {
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
        val logPathWithHours = logPathWithDate + File.separator + getDateWithHours() + ".log"
        val logPathWithHoursFile = File(logPathWithHours)
        if (!logPathWithDateFile.exists()) {
            Log.d("Matthew", "prepareLogFilePath: ${logPathWithHoursFile.path}")
            logPathWithHoursFile.createNewFile()
        }
        return logPathWithHoursFile
    }

    private fun writeLogToFile(logLine: String, logFilePath: File) {
        val content: String = logLine + "\r\n"
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(logFilePath, true)
            fileOutputStream.write(content.toByteArray())
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (fileNotFoundException: FileNotFoundException) {
            fileNotFoundException.printStackTrace()
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        } finally {
            fileOutputStream?.close()
        }

    }
}