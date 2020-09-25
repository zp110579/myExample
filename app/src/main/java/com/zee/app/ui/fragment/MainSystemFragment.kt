import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.zee.app.R
import com.zee.app.bean.Category
import com.zee.extendobject.eventBusRegister
import kotlinx.android.synthetic.main.fragment_main_system.*
import org.greenrobot.eventbus.SubscribeMainThread

/**
 *created by zee on 2020/9/8.
 * 体系
 */
class MainSystemFragment : BaseFragment() {
    override fun getLayoutID(): Int {
        return R.layout.fragment_main_system
    }

    override fun inViews() {
        eventBusRegister(this)
        SystemPresenter().getSystemDatas()
    }

    @SubscribeMainThread(tag = "system")
    fun onEventBusData(list: List<Category>) {
        viewPager_system.adapter = object : FragmentStatePagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return MainSystemItemFragment.newInstance(position,list[position])
            }

            override fun getCount(): Int {
                return list.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return list[position].name
            }
        }

        tabLayout_system.setViewPager(viewPager_system)
    }
}