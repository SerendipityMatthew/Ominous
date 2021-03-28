package me.xuwanjin.ominous

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.Process
import android.util.Log
import androidx.core.app.ActivityCompat
import me.xuwanjin.ominous.bean.DeviceAndAppInfo
import me.xuwanjin.ominous.catcher.OminousCatcher
import java.io.File

class Ominous {
    @SuppressLint("StaticFieldLeak")
    object OminousInstance {
        /**
         *  log的保存路径
         *      1. /data/data/me.xuwanjin.ominousdemo/cache 路径, 优先使用这个路径
         *      2. /sdcard/Android/data/me.xuwanjin.ominousdemo/files
         *      3. /sdcard/Download/ 路径
         */
        var mLogSavePath: String? = null
        var isCatchEventLog: Boolean = true
        var mLogPid: Int? = null
        lateinit var mContext: Context
        var mAppCommitId: String? = null
        var mAppCommitterEmail: String? = null
        fun startCatchLog() {
            if (mLogPid == null) {
                mLogPid = Process.myPid()
            }
            Log.d(TAG, "startCatchLog: mLogSavePath111 = $mLogSavePath")
            if (mLogSavePath == null) {
                mLogSavePath = mContext.filesDir.canonicalPath
                Log.d(TAG, "startCatchLog: mLogSavePath222 = $mLogSavePath")
                Log.d(TAG, "startCatchLog: path = ${mContext.filesDir.path}")
                Log.d(TAG, "startCatchLog: canonicalPath = ${mContext.filesDir.canonicalPath}")
                Log.d(TAG, "startCatchLog: absoluteFile = ${mContext.filesDir.absoluteFile}")

            } else if (mLogSavePath == OminousConstant.LOG_SAVE_PATH_SDCARD) {
                val isMounted =
                    Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                if (!isMounted) {
                    return
                }
                var sdcardDir: File? = null
                val isCanWrite = ActivityCompat.checkSelfPermission(
                    mContext,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                val isCanRead = ActivityCompat.checkSelfPermission(
                    mContext,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
                if (isCanWrite == PackageManager.PERMISSION_GRANTED
                    && isCanRead == PackageManager.PERMISSION_GRANTED
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        sdcardDir =
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    } else {
                        sdcardDir =
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    }
                    mLogSavePath = sdcardDir.path
                } else {
                    mLogSavePath = mContext.filesDir.canonicalPath
                }

            } else if (mLogSavePath == OminousConstant.LOG_SAVE_PATH_ANDROID_DATA) {
                mLogSavePath = mContext.getExternalFilesDir("")?.path
            }
            Log.d(TAG, "startCatchLog: mLogSavePath = $mLogSavePath")

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
                appCompiledCommitId = mAppCommitId
                appCompiledCommittedEmail = mAppCommitterEmail
            }

            deviceAndAppInfo.let {

            }
            return deviceAndAppInfo
        }
    }

    companion object {
        const val TAG = "Ominous"
    }

    /**
     *  这种模式对 kotlin 似乎多余了
     *
     */
    class Builder {
        private var mOminous: OminousInstance = OminousInstance

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

        fun setAppCommitId(commitId: String): Builder {
            mOminous.mAppCommitId = commitId
            return this
        }

        fun setAppCommitterEmail(committerEmail: String): Builder {
            mOminous.mAppCommitterEmail = committerEmail
            return this
        }

        fun build() = OminousInstance
    }
}