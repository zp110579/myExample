import android.os.Bundle
import com.zee.app.R
import com.zee.app.bean.Bean
import com.zee.extendobject.eventBusRegister
import kotlinx.android.synthetic.main.fragment_main_home_item.*
import org.greenrobot.eventbus.EventParams
import org.greenrobot.eventbus.SubscribeMainThread

/**
 *created by zee on 2020/9/8.
 *首页详细
 */
class MainHomeItemFragment : BaseFragment() {
    var mType = 0;
    var curPage = 0;

    companion object {

        fun newInstance(index: Int): MainHomeItemFragment {
            val args = Bundle()

            args.putInt("type", index);
            val fragment = MainHomeItemFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_main_home_item
    }

    override fun inViews() {
        eventBusRegister(this)
        recycler_view.adapter = CommentAdapter()
        recycler_view.setLoadMoreListener {
            loadData()
        }
        arguments?.apply {
            mType = getInt("type")
            if (mType == 0) {
                HomePresenter().getTopList()
            }
        }
        loadData()
    }

    fun loadData() {
        when (mType) {
            0 -> {
                //热门
                HomePresenter().getDataList(curPage++)
            }
            1 -> {
                //热门项目
                HomePresenter().getHotProjects(curPage++)
            }
            2 -> {
                //广场
                HomePresenter().getUserArticle(curPage++)
            }

        }
    }

    @SubscribeMainThread(tag = "home")
    fun onEBData(eventParams: EventParams) {
        val tempIndex = eventParams.getInt("type");
        if (mType == tempIndex) {
            val list = eventParams.getObject<List<Bean>>("list")
            recycler_view.baseZAdapter.addAll(list)
            recycler_view.loadFinish()
        }

    }
}