package me.xuwanjin.ominous.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getDate(): String {
    return SimpleDateFormat("yyyy-MM-dd").format(Date())
}

@SuppressLint("SimpleDateFormat")
fun getDateWithHours(): String{
    return SimpleDateFormat("yyyy-MM-dd-HH").format(Date())
}