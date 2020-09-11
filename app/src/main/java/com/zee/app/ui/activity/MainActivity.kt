package com.zee.app.ui.activity

import MainFindFragment
import MainHomeFragment
import MainMeFragment
import MainNavigationFragment
import MainTypeFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zee.app.R
import com.zee.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var fragments: Map<Int, Fragment>

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        fragments = mapOf(
            R.id.home to MainHomeFragment(),
            R.id.system to MainTypeFragment(),
            R.id.discovery to MainFindFragment(),
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
}
