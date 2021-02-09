
package me.xuwanjin.ominousdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import me.xuwanjin.ominous.catcher.OminousCatcher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ominousCatcher:OminousCatcher = OminousCatcher("/sdcard/Download/", Process.myPid())
        Thread(ominousCatcher).start()
    }
}