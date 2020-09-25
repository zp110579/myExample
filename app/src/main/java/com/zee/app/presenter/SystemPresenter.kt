import com.zee.app.bean.Bean
import com.zee.app.bean.Category
import com.zee.extendobject.eventBusPost
import com.zee.http.executeHttpWithGet
import com.zee.http.getHttpRequest
import com.zee.http.request.ZStringResult
import org.greenrobot.eventbus.EventParams
import java.lang.Exception

/**
 *created by zee on 2020/9/14.
 *
 */
class SystemPresenter {

    /**
     * 获得体系下的分类
     */
    fun getSystemDatas() {
        "/tree/json".executeHttpWithGet(object : ZStringResult() {
            override fun onSuccessAsyncThread(data: String?) {
                val list = optObject<List<Category>>("data", Category::class.java, true)
                eventBusPost(list, "system")
            }

            override fun onError(e: Exception?) {
                super.onError(e)
            }
        })
    }

    /**
     * 获得系统的分类的详细
     */
    fun getSystemCateData(page: Int, id: Int) {
        CommonPresenter.getSystemCateData(page, id, "SystemTagData")
    }
}