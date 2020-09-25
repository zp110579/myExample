import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.zee.app.R
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
        viewPager_homeItem.adapter = object : FragmentStatePagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return MainHomeItemFragment.newInstance(position)
            }

            override fun getCount(): Int {
                return 3
            }
        }

        tabLayout_home.setViewPager(viewPager_homeItem)
    }
}