package me.xuwanjin.ominous.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.DialogFragment
import me.xuwanjin.ominous.view.BugReportDialog

class OminousSettingFragment : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext())
            .apply {
                setContent {
                    ShowDialog()
                }
            }
    }

    override fun onResume() {
        super.onResume()

    }
}


@Preview(widthDp = 400, heightDp = 600)
@Composable
fun ShowDialog() {
    BugReportDialog()
}