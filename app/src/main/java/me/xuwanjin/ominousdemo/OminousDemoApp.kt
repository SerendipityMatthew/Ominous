package me.xuwanjin.ominousdemo

import android.app.Application
import android.os.Process
import me.xuwanjin.ominous.catcher.OminousCatcher

class OminousDemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val ominousCatcher: OminousCatcher = OminousCatcher("/sdcard/Download/", Process.myPid())
        Thread(ominousCatcher).start()
    }
}