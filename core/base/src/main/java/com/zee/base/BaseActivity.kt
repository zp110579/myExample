package com.zee.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.zee.extendobject.eventBusRegister

/**
 * created by zee on 2020/9/7.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
    }

    abstract fun getLayoutID(): Int
    abstract fun initViews()

    override fun onDestroy() {
        super.onDestroy()
        eventBusRegister(this)
    }
}