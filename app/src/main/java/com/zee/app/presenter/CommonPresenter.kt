import com.zee.extendobject.eventBusPost
import com.zee.http.getHttpRequest
import com.zee.http.request.ZStringResult
import org.greenrobot.eventbus.EventParams
import java.lang.Exception

/**
 *created by zee on 2020/9/18.
 *
 */
object CommonPresenter {
    fun getSystemCateData(page: Int, id: Int, tag: String) {
        val hashMap = HashMap<String, Any>()
        hashMap["cid"] = id
        "/article/list/${page}/json".getHttpRequest(hashMap).execute(object : ZStringResult() {
            override fun onSuccessAsyncThread(data: String?) {
                val pageData: PageData = optObject("data", PageData::class.java)
                val eventParams = EventParams()
                eventParams.put("bean", pageData)
                eventParams.put("id", id)
                eventBusPost(eventParams, tag)
            }
        })
    }
}