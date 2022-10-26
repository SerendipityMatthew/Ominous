package me.xuwanjin.ominous.utils

import android.annotation.SuppressLint
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.*
object Utils{
    val getDateWithHourFlow = MutableStateFlow("")

    var dateWithHourFlow = getDateWithHours()
}
@SuppressLint("SimpleDateFormat")
fun getDate(): String {
    return SimpleDateFormat("yyyy-MM-dd").format(Date())
}

@SuppressLint("SimpleDateFormat")
fun getDateWithHours(): String{
    return SimpleDateFormat("yyyy-MM-dd-HH").format(Date())
}