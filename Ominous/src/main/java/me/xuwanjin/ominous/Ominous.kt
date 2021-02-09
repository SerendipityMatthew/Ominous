package me.xuwanjin.ominous

import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.Process
import androidx.annotation.RequiresApi
import me.xuwanjin.ominous.catcher.OminousCatcher
import java.io.File

class Ominous {
    var mLogSavePath: String? = null
    var isCatchEventLog: Boolean = true
    var mLogPid: Int? = null
    lateinit var mContext: Context

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
                    sdcardDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                } else {
                    sdcardDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                }
                mLogSavePath = sdcardDir.path
            }
        }

        val ominousCatcher: OminousCatcher? = this.mLogSavePath?.let { OminousCatcher(it, this.mLogPid!!) }
        Thread(ominousCatcher).start()
    }
}