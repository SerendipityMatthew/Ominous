
package me.xuwanjin.ominousdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.xuwanjin.ominous.activity.BugReportActivity
import me.xuwanjin.ominous.activity.OminousSettingsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, BugReportActivity::class.java))
    }
}