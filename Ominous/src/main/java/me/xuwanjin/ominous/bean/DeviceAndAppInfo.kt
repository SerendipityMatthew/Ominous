package me.xuwanjin.ominous.bean

import kotlin.properties.Delegates

class DeviceAndAppInfo {
    lateinit var deviceModel: String
    lateinit var deviceBrand: String
    lateinit var deviceManufacturer: String
    lateinit var deviceProduct: String
    var isEmulator = false
    var deviceSdkVersion by Delegates.notNull<Int>()
    var isDeviceRoot: Boolean = false

    var appVersionCode by Delegates.notNull<Long>()
    lateinit var appVersionName: String
    lateinit var appPackageName: String
    lateinit var appName: String

    /**
     *  app 被打包的时候, 最新的 commit id, 可以用于快速定位问题
     *  在不修改版本号, 释放了多个 debug 版本的情况下, 可以快速定位问题
     */
    lateinit var appCompiledCommitId: String

    /**
     *  app 被打包的时候, 最新的 commit id提交人的 邮箱地址,
     *  在默认的情况下, log 发送到这个邮箱里面去
     */
    lateinit var appCompiledCommittedEmail: String
}