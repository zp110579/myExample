package com.zee.app.base
import android.app.Application
import com.zee.utils.ZLibrary

/**
 *created by zee on 2020/9/7.
 *
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ZLibrary.init(this, true)

    }
}