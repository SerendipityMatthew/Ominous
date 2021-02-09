package me.xuwanjin.ominousdemo

import android.app.Application
import android.os.Process
import me.xuwanjin.ominous.Ominous

class OminousDemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val ominous:Ominous = Ominous.Builder()
            .setCatchEventLog(true)
            .setContext(applicationContext)
            .setLogPid(Process.myPid())
            .build()
        ominous.startCatchLog()
    }
}