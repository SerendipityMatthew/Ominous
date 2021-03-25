package me.xuwanjin.ominous.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.xuwanjin.ominous.view.BugUploadView

class BugReportActivity : AppCompatActivity() {
    companion object {
        const val TAG = "BugReportActivity"
        val BUTTON_WIDTH = 250.dp

        /**
         * 默认的log 发送的邮箱地址, 每个项目不同, 推荐邮箱每个项目的 committer email
         */
        var DEFAULT_LOG_RECEIVE_EMAIL = "xuwj@mxchip.com"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShowBugView()
        }
    }
}
@Preview(widthDp = 400, heightDp = 600, backgroundColor = 0xFF00FF)
@Composable
fun ShowBugView(){
    BugUploadView(onFunctionSelected = {
        Log.d(BugReportActivity.TAG, "onCreate: it = $it")
    })
}