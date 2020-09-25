import android.view.View
import com.zee.app.R
import com.zee.app.bean.Bean
import com.zee.base.BaseZAdapter
import com.zee.ext.htmlToSpanned
import com.zee.extendobject.eventBusPost
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

/**
 *created by zee on 2020/9/11.
 *
 */
class CommentAdapter(var mType: Int = 0) : BaseZAdapter<Bean>() {
    override fun getLayoutResID(): Int {
        return R.layout.item_content
    }

    override fun initViews(parentView: View, location: Int) {
        val dataBean = get(location) as Bean
        dataBean.apply {
            if (isTop) {
                setVisible(R.id.tv_top)
            }
            if (fresh) {
                setVisible(R.id.tv_new)
            }

            setText(R.id.tv_title, title)

            setText(R.id.tv_author, if (author.isNotEmpty()) author else shareUser)
            if (desc.isNullOrBlank()) {
                setGone(R.id.tv_desc)
            } else {
                setVisible(R.id.tv_desc)
                setText(R.id.tv_desc, desc.htmlToSpanned())
            }
            if (mType == 0) {
                setText(R.id.tv_ChapterName, "$chapterName/$superChapterName")
            } else {
                setGone(R.id.tv_ChapterName)
            }
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")

            setText(R.id.tv_time, formatter.format(Date(publishTime)))
            parentView.setOnClickListener {
                eventBusPost(this, "webView")
            }
        }
    }
}