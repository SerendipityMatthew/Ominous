package me.xuwanjin.ominous.catcher

import android.util.Log
import me.xuwanjin.ominous.OminousConstant.Companion.LOG_COMMAND_WITH_EVENT_LOG
import me.xuwanjin.ominous.bean.DeviceAndAppInfo
import me.xuwanjin.ominous.utils.getDate
import me.xuwanjin.ominous.utils.getDateWithHours
import java.io.*
import java.lang.StringBuilder

class OminousCatcher(
    private val mLogSavePath: String,
    private val mPid: Int,
    private val mDeviceAndAppInfo: DeviceAndAppInfo
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
        if (!logPathWithHoursFile.exists()) {
            logPathWithHoursFile.createNewFile()
            writeDeviceAndAppInfo(logPathWithHoursFile)
        }
        return logPathWithHoursFile
    }

    /**
     * 创建文件的时候, 把设备的信息, app 的信息 写入到文件当中
     */
    private fun writeDeviceAndAppInfo(logFile:File) {
        val stringBuilder = StringBuilder()
        stringBuilder.append("=============== app info ==================\n")
        stringBuilder.append("appName = " + mDeviceAndAppInfo.appName + "\n")
        stringBuilder.append("appVersionCode = " + mDeviceAndAppInfo.appVersionCode + "\n")
        stringBuilder.append("appVersionName = " + mDeviceAndAppInfo.appVersionName + "\n")
        stringBuilder.append("appCompiledCommitId = " + mDeviceAndAppInfo.appCompiledCommitId + "\n")
        stringBuilder.append("=============== device info ==================\n")
        stringBuilder.append("deviceBrand = " + mDeviceAndAppInfo.deviceBrand + "\n")
        stringBuilder.append("deviceModel = " + mDeviceAndAppInfo.deviceModel + "\n")
        stringBuilder.append("deviceManufacturer = " + mDeviceAndAppInfo.deviceManufacturer + "\n")
        stringBuilder.append("deviceProduct = " + mDeviceAndAppInfo.deviceProduct + "\n")
        stringBuilder.append("deviceSdkVersion = " + mDeviceAndAppInfo.deviceSdkVersion + "\n")
        stringBuilder.append("isDeviceRoot = " + mDeviceAndAppInfo.isDeviceRoot + "\n")
        stringBuilder.append("isEmulator = " + mDeviceAndAppInfo.isEmulator + "\n")
        stringBuilder.append("\n")
        stringBuilder.append("\n")
        stringBuilder.append("\n")
        val fileOutputStream:FileOutputStream = FileOutputStream(logFile, true)
        fileOutputStream.write(stringBuilder.toString().toByteArray())
        fileOutputStream.flush()
        fileOutputStream.close()
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