import com.zee.app.bean.Bean
import com.zee.extendobject.eventBusPost
import com.zee.http.executeHttpWithGet
import com.zee.http.request.ZStringResult
import org.greenrobot.eventbus.EventParams

/**
 *created by zee on 2020/9/11.
 *
 */
class HomePresenter {

    /**
     * 置顶数据
     */
    fun getTopList() {
        "/article/top/json".executeHttpWithGet(object : ZStringResult() {
            override fun onSuccessAsyncThread(data: String?) {
                val list = optObject<List<Bean>>("data", Bean::class.java, true)
                val evenParam = EventParams()
                evenParam.put("type", 0)
                evenParam.put("list", list)
                list.forEach { it.isTop = true }
                eventBusPost(evenParam, "home")
            }
        })
    }

    /**
     * 首页数据
     */
    fun getDataList(page: Int) {
        "/article/list/${page}/json".executeHttpWithGet(WanDataResult(0))
    }

    /**
     * 热门项目
     */
    fun getHotProjects(page: Int) {
        "/article/listproject/${page}/json".executeHttpWithGet(WanDataResult(1))
    }

    /**
     * 广场数据
     */
    fun getUserArticle(page: Int) {
        "/user_article/list/${page}/json".executeHttpWithGet(WanDataResult(2))
    }
}