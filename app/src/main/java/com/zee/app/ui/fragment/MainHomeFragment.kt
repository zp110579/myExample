import com.zee.app.R
import com.zee.extendobject.setVisible
import kotlinx.android.synthetic.main.fragment_main_home.*

/**
 *created by zee on 2020/9/8.
 * 首页
 */
class MainHomeFragment : BaseFragment() {
    override fun getLayoutID(): Int {
        return R.layout.fragment_main_home
    }

    override fun inViews() {
        tabLayout_home.setVisible()

        tabLayout_home.setViewPager(viewPager_homeItem)
    }
}