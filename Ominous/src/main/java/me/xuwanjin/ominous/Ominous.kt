package me.xuwanjin.ominous

import android.content.Context

class Ominous {
    var logSavePath: String? = null
    var isCatchEventLog: Boolean = true
    var logPid: Int? = null
    lateinit var context: Context

    class Builder {

    }
}