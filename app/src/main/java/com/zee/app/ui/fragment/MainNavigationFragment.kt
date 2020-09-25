import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.zee.app.R
import kotlinx.android.synthetic.main.fragment_main_mavigation.*

/**
 *created by zee on 2020/9/8.
 * 导航
 */
class MainNavigationFragment : BaseFragment() {
    override fun getLayoutID(): Int {
        return R.layout.fragment_main_mavigation
    }

    override fun inViews() {
        viewPager_other.adapter = object : FragmentStatePagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                when (position) {
                    0 -> return InterviewFragment()
                    1 -> return FindFragment()
                    else -> return NavigationFragment()
                }
            }

            override fun getCount(): Int {
                return 3
            }
        }
    }
}