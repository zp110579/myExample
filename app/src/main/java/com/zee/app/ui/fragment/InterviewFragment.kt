import com.zee.app.R
import com.zee.extendobject.eventBusRegister
import org.greenrobot.eventbus.SubscribeMainThread

/**
 *created by zee on 2020/9/18.
 *面试
 */
class InterviewFragment : BaseFragment() {
    override fun getLayoutID(): Int {
        return R.layout.fragment_recyclerview
    }

    override fun inViews() {
        eventBusRegister(this)

    }

    @SubscribeMainThread(tag = "interview")
    fun onEventBusData() {

    }
}