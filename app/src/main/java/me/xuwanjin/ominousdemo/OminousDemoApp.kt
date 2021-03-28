package me.xuwanjin.ominousdemo

import android.app.Application
import android.os.Process
import me.xuwanjin.ominous.Ominous
import me.xuwanjin.ominous.OminousConstant

class OminousDemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val ominous: Ominous.OminousInstance = Ominous.Builder()
            .setCatchEventLog(true)
            .setContext(applicationContext)
            .setLogSavePath(OminousConstant.LOG_SAVE_PATH_ANDROID_DATA)
            .setLogPid(Process.myPid())
            .build()
        ominous.startCatchLog()

    }
}