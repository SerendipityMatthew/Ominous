package me.xuwanjin.ominous.activity

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import me.xuwanjin.ominous.api.IOnPermissionGranted
import me.xuwanjin.ominous.utils.requestPermission

class OminousSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onPermissionGranted = object : IOnPermissionGranted {
            override fun onPermissionGranted() {
                setContent {
                    val fragment = OminousSettingFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .add(fragment, "bugreport_dialog")
                        .commit()
                }
            }
        }

        requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, onPermissionGranted)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissionArray: Array<out String>,
        grantResultsArray: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissionArray, grantResultsArray)
        permissionArray.forEachIndexed { index, permission ->
            if (permission == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                val result = grantResultsArray[index]
                if (result == 0 || result == 1) {
                    setContent {
                        val fragment = OminousSettingFragment()
                        supportFragmentManager
                            .beginTransaction()
                            .add(fragment, "bugreport_dialog")
                            .commit()
                    }
                }
            }
        }
    }
}
