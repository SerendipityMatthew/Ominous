package me.xuwanjin.ominous

import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.Process
import me.xuwanjin.ominous.bean.DeviceAndAppInfo
import me.xuwanjin.ominous.catcher.OminousCatcher
import java.io.File

class Ominous {
    var mLogSavePath: String? = null
    var isCatchEventLog: Boolean = true
    var mLogPid: Int? = null
    lateinit var mContext: Context
    var mAppCommitId: String? = null
    var mAppCommitterEmail: String? = null

    class Builder {
        private var mOminous: Ominous = Ominous()

        fun setLogPid(pid: Int): Builder {
            mOminous.mLogPid = pid
            return this
        }

        fun setCatchEventLog(isCatchEventLog: Boolean): Builder {
            mOminous.isCatchEventLog = isCatchEventLog
            return this
        }

        fun setContext(context: Context): Builder {
            mOminous.mContext = context
            return this
        }

        fun setLogSavePath(logPath: String): Builder {
            mOminous.mLogSavePath = logPath
            return this
        }

        fun build(): Ominous {
            return mOminous
        }
    }

    fun startCatchLog() {
        if (mLogPid == null) {
            mLogPid = Process.myPid()
        }
        if (mLogSavePath == null) {
            val isMounted = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
            if (isMounted) {
                var sdcardDir: File? = null
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    sdcardDir =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                } else {
                    sdcardDir =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                }
                mLogSavePath = sdcardDir.path
            }
        }

        val ominousCatcher: OminousCatcher? =
            this.mLogSavePath?.let {
                OminousCatcher(it, this.mLogPid!!, getDeviceAndAppInfo())
            }
        Thread(ominousCatcher).start()
    }

    private fun getDeviceAndAppInfo(): DeviceAndAppInfo {
        val packageInfo = mContext.packageManager.getPackageInfo(mContext.packageName, 0)
        val versionCode =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode
            } else {
                packageInfo.versionCode.toLong()
            }
        val versionName = packageInfo.versionName
        val deviceAndAppInfo = DeviceAndAppInfo()
        deviceAndAppInfo.apply {
            appVersionCode = versionCode
            appVersionName = packageInfo.versionName
            appName = packageInfo.applicationInfo.name
            appPackageName = versionName
            appPackageName = mContext.packageName
            deviceModel = Build.MODEL
            deviceProduct = Build.PRODUCT
            deviceBrand = Build.BRAND
            deviceManufacturer = Build.MANUFACTURER
            deviceSdkVersion = Build.VERSION.SDK_INT
            appCompiledCommitId = this@Ominous.mAppCommitId
            appCompiledCommittedEmail = this@Ominous.mAppCommitterEmail
        }

        deviceAndAppInfo.let {

        }
        return deviceAndAppInfo
    }
}