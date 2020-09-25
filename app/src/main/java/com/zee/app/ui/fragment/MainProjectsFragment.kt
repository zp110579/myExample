import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.zee.app.R
import com.zee.app.bean.Category
import com.zee.extendobject.eventBusRegister
import kotlinx.android.synthetic.main.fragment_main_project.*
import org.greenrobot.eventbus.SubscribeMainThread

/**
 *created by zee on 2020/9/8.
 *项目
 */
class MainProjectsFragment : BaseFragment() {
    override fun getLayoutID(): Int {
        return R.layout.fragment_main_project
    }

    override fun inViews() {
        eventBusRegister(this)
        ProjectsPresenter().getProjectTree()
    }

    @SubscribeMainThread(tag = "projects")
    fun onCategoryData(list: List<Category>) {
        viewPager_projects.adapter = object : FragmentStatePagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return MainProjectItemFragment.newInstance(list[position])
            }

            override fun getCount(): Int {
                return list.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return list[position].name
            }
        }

        tabLayout_projects.setViewPager(viewPager_projects)
    }
}