package com.gabdanho.hapibi.app

import android.app.Application
import com.vk.id.VKID
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HapibiApp : Application() {
    override fun onCreate() {
        super.onCreate()

        VKID.init(this)
        VKID.logsEnabled = true
    }
}