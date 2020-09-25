import com.zee.app.bean.Bean
import com.zee.extendobject.eventBusPost
import com.zee.http.request.ZStringResult
import org.greenrobot.eventbus.EventParams

/**
 *created by zee on 2020/9/14.
 *
 */
class WanDataResult(var type: Int) : ZStringResult() {
    override fun onSuccessAsyncThread(data: String?) {
        val list = optObject<PageData>("data", PageData::class.java)
        val evenParam = EventParams()
        evenParam.put("type", type)
        evenParam.put("list", list.datas)
        eventBusPost(evenParam, "home")
    }
}