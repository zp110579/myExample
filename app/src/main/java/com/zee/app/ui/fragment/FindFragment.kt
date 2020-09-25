import com.zee.app.R
import com.zee.extendobject.eventBusRegister
import kotlinx.android.synthetic.main.fragment_recyclerview.view.*
import org.greenrobot.eventbus.SubscribeMainThread

/**
 *created by zee on 2020/9/18.
 *面试
 */
class FindFragment : BaseFragment() {
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