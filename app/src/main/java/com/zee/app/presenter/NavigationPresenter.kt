import com.zee.http.executeHttpWithGet
import com.zee.http.request.ZStringResult

/**
 *created by zee on 2020/9/21.
 *导航
 */
class NavigationPresenter {

    /**
     * 获得面试数据
     */
    fun getData(page: Int, id: Int) {
        "/article/list/$page/json?cid=$id".executeHttpWithGet(object : ZStringResult() {
            override fun onSuccessAsyncThread(data: String) {

            }
        }
        )
    }
}