import com.zee.app.bean.Category
import com.zee.extendobject.eventBusPost
import com.zee.http.executeHttpWithGet
import com.zee.http.request.ZStringResult

/**
 *created by zee on 2020/9/18.
 *项目Presenter
 */
class ProjectsPresenter {
    fun getProjectTree() {
        "project/tree/json".executeHttpWithGet(object : ZStringResult() {
            override fun onSuccessAsyncThread(data: String?) {
                val list = optObject<List<Category>>("data", Category::class.java, true)
                eventBusPost(list, "projects")
            }
        })
    }

    fun getDatas(page:Int,id:Int) {
        CommonPresenter.getSystemCateData(page,id,"projects")
    }
}