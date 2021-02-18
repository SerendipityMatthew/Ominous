package me.xuwanjin.ominous.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import me.xuwanjin.ominous.view.BugUploadView

class BugReportActivity : AppCompatActivity() {
    companion object {
        val BUTTON_WIDTH = 250.dp

        /**
         * 默认的log 发送的邮箱地址, 每个项目不同, 推荐邮箱每个项目的 committer email
         */
        var DEFAULT_LOG_RECEIVE_EMAIL = "xuwj@mxchip.com"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BugUploadView()
        }
    }

}