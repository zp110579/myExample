import com.zee.app.bean.Bean
import java.io.Serializable

/**
 *created by zee on 2020/9/14.
 *
 */
class PageData(
    var datas: List<Bean>,
    var curPage: Int,
    var size: Int
) : Serializable