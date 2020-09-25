import android.os.Bundle
import com.zee.app.R
import com.zee.app.bean.Category
import com.zee.extendobject.eventBusRegister
import kotlinx.android.synthetic.main.fragment_main_project_item.*
import org.greenrobot.eventbus.EventParams
import org.greenrobot.eventbus.SubscribeMainThread

/**
 *created by zee on 2020/9/8.
 *项目
 */
class MainProjectItemFragment : BaseFragment() {
    private var curPage = 0;
    private var curID = 0;
    override fun getLayoutID(): Int {
        return R.layout.fragment_main_project_item
    }

    companion object {
        fun newInstance(category: Category): MainProjectItemFragment {
            val args = Bundle()
            args.putParcelable("bean", category)
            val fragment = MainProjectItemFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun inViews() {
        eventBusRegister(this)
        recycler_view_project.adapter=CommentAdapter(1)
        arguments?.apply {
            val category = getParcelable<Category>("bean")!!
            curID=category.id
            ProjectsPresenter().getDatas(0, curID)
        }
    }

    @SubscribeMainThread(tag = "projects")
    fun loadData(eventParams: EventParams) {
        val tempId = eventParams.getInt("id")
        if (tempId == curID) {
            val pageData = eventParams.getObject<PageData>("bean")
            val list = pageData.datas
            if (curPage == 0) {
                recycler_view_project.baseZAdapter.list = list
            } else {
                recycler_view_project.baseZAdapter.addAll(list)
                recycler_view_project.loadFinish()
            }
        }
    }
}