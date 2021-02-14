package me.xuwanjin.ominous.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import me.xuwanjin.ominous.view.BugReportDialog

class OminousSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowText(this)
        }
    }
}

@Composable
fun ShowText(context: Context) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Green)
    ) {
        BugReportDialog(context)
    }
}