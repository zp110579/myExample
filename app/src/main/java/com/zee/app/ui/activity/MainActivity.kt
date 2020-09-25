package com.zee.app.ui.activity

import MainProjectsFragment
import MainHomeFragment
import MainMeFragment
import MainNavigationFragment
import MainSystemFragment
import android.content.Intent
import androidx.fragment.app.Fragment
import com.zee.app.R
import com.zee.app.bean.Bean
import com.zee.base.BaseActivity
import com.zee.extendobject.eventBusRegister
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.SubscribeMainThread

class MainActivity : BaseActivity() {
    private lateinit var fragments: Map<Int, Fragment>

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        eventBusRegister(this)
        fragments = mapOf(
            R.id.home to MainHomeFragment(),
            R.id.system to MainSystemFragment(),
            R.id.discovery to MainProjectsFragment(),
            R.id.navigation to MainNavigationFragment(),
            R.id.mine to MainMeFragment()
        )

        bottomNavigationView.apply {
            setOnNavigationItemSelectedListener { menuItem ->
                showFragment(menuItem.itemId)
                true
            }
        }
        showFragment(R.id.home)
    }

    private fun showFragment(menuItemId: Int) {
        val currentFragment = supportFragmentManager.fragments.find {
            it.isVisible && it in fragments.values
        }
        val targetFragment = fragments[menuItemId]
        supportFragmentManager.beginTransaction().apply {
            currentFragment?.let { if (it.isVisible) hide(it) }
            targetFragment?.let {
                if (it.isAdded) show(it) else add(R.id.fl, it)
            }
        }.commit()
    }

    @SubscribeMainThread(tag = "webView")
    fun openDetailActivity(bean: Bean) {
        var intentA = Intent(this, WebViewActivity::class.java)
        intentA.putExtra("bean", bean)
        startActivity(intentA)
    }
}
