package me.xuwanjin.ominous.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import me.xuwanjin.ominous.api.IOnPermissionGranted

fun requestPermission(
    activity: Activity,
    permission: String,
    iOnPermissionGranted: IOnPermissionGranted?
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (ContextCompat.checkSelfPermission(
                activity,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //没有权限则申请权限
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        } else {
            iOnPermissionGranted?.onPermissionGranted()
        }
    } else {
        iOnPermissionGranted?.onPermissionGranted()
    }
}