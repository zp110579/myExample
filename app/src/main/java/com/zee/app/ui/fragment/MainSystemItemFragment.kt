import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zee.app.R
import com.zee.app.bean.Bean
import com.zee.app.bean.Category
import com.zee.base.BaseZAdapter
import com.zee.extendobject.eventBusRegister
import com.zee.extendobject.setGone
import com.zee.utils.ZListUtils
import com.zee.view.ZxTextView
import kotlinx.android.synthetic.main.fragment_main_system_item.*
import org.greenrobot.eventbus.EventParams
import org.greenrobot.eventbus.SubscribeMainThread

/**
 *created by zee on 2020/9/8.
 *首页详细
 */
class MainSystemItemFragment : BaseFragment() {
    var mType = 0;
    var curPage = 0;

    //当前选择的标签位置
    var curTagIndex = 0;
    lateinit var mCategory: Category
    var curID: Int = 0;

    companion object {

        fun newInstance(index: Int, category: Category): MainSystemItemFragment {
            val args = Bundle()

            args.putInt("type", index);
            args.putParcelable("bean", category);
            val fragment = MainSystemItemFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_main_system_item
    }

    override fun inViews() {
        eventBusRegister(this)
        arguments?.apply {
            mCategory = getParcelable<Category>("bean")!!
            curID = if (ZListUtils.isEmpty(mCategory.children)) id else mCategory.children[0].id
            SystemPresenter().getSystemCateData(curPage, curID)
        }
        if (ZListUtils.isNoEmpty(mCategory.children) && mCategory.children.size > 1) {
            //只有1个不显示出来
            recycler_view_tag.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            recycler_view_tag.adapter = object : BaseZAdapter<Category>(mCategory.children) {
                override fun getLayoutResID(): Int {
                    return R.layout.fragment_main_system_category
                }

                override fun initViews(parentView: View, location: Int) {
                    val data = bean
                    data.apply {
                        val nameTV = findViewById<ZxTextView>(R.id.tv_name)
                        nameTV.apply {
                            text = name
                            isSelected = (location == curTagIndex)
                        }

                        parentView.setOnClickListener {
                            curTagIndex = location
                            notifyDataSetChanged()
                            curID = data.id;
                            SystemPresenter().getSystemCateData(curPage, curID)
                        }
                    }
                }
            }
        } else {
            recycler_view_tag.setGone()
        }
        recycler_view_system.adapter = CommentAdapter(1)
        recycler_view_system.setLoadMoreListener {
            SystemPresenter().getSystemCateData(++curPage, curID)
        }
        arguments?.apply {
            mType = getInt("type")
            mCategory = getParcelable<Category>("bean")!!
        }
    }

    @SubscribeMainThread(tag = "SystemTagData")
    fun loadData(eventParams: EventParams) {
        val tempId = eventParams.getInt("id")
        if (tempId == curID) {
            val pageData = eventParams.getObject<PageData>("bean")
            val list = pageData.datas
            if (curPage == 0) {
                recycler_view_system.baseZAdapter.list = list
            } else {
                recycler_view_system.baseZAdapter.addAll(list)
                recycler_view_system.loadFinish()
            }
        }
    }

    @SubscribeMainThread(tag = "systemItem")
    fun onEBData(eventParams: EventParams) {
        val tempIndex = eventParams.getInt("type");
        if (mType == tempIndex) {
            val list = eventParams.getObject<List<Bean>>("list")
            recycler_view_system.baseZAdapter.addAll(list)
            recycler_view_system.loadFinish()
        }

    }
}