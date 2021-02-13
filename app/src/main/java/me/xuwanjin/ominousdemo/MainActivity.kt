
package me.xuwanjin.ominousdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import me.xuwanjin.ominous.activity.OminousSettingsActivity
import me.xuwanjin.ominous.catcher.OminousCatcher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, OminousSettingsActivity::class.java))
    }
}