package com.zee.base

import android.os.Bundle
import android.view.View
import android.view.ViewStub

/**
 * created by zee on 2020/9/7.
 */
abstract class BarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bar_activity)
        val stubView = findViewById<ViewStub>(R.id.viewStub_content)
        stubView.layoutResource = getLayoutID()
        stubView.inflate()
        initViews()
        //返回
        val view = findViewById<View>(R.id.ivBack)
        view.setOnClickListener { finish(); };
    }

}