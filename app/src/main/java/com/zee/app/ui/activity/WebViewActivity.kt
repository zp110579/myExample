package com.zee.app.ui.activity

import android.view.View
import com.zee.app.R
import com.zee.app.bean.Bean
import com.zee.base.BarActivity
import kotlinx.android.synthetic.main.activity_detail.*

/**
 *created by zee on 2020/9/14.
 *
 */
class WebViewActivity : BarActivity() {
    var link = ""
    override fun getLayoutID(): Int {
        return R.layout.activity_detail
    }

    override fun initViews() {
        val bean = intent.getSerializableExtra("bean") as Bean
        link = bean.link
    }

    override fun onStart() {
        super.onStart()
        webView_detail.loadUrl(link)
    }
}