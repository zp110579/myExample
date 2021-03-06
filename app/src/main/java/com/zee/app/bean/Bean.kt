package com.zee.app.bean

import java.io.Serializable

/**
 * created by zee on 2020/9/11.
 */
class Bean(
    var isTop: Boolean,
    var apkLink: String,
    var audit: Int,
    var author: String,
    var canEdit: Boolean,
    var chapterId: Int,
    var chapterName: String,
    var collect: Boolean,
    var courseId: Int,
    var desc: String,
    var descMd: String,
    var envelopePic: String,
    var fresh: Boolean,
    var id: Int,
    var link: String,
    var niceDate: String,
    var niceShareDate: String,
    var origin: String,
    var prefix: String,
    var projectLink: String,
    var publishTime: Long,
    var realSuperChapterId: Int,
    var selfVisible: Int,
    var shareDate: Long,
    var shareUser: String,
    var superChapterId: Int,
    var superChapterName: String,
    var tags: List<Any>,
    var title: String,
    var type: Int,
    var userId: Int,
    var visible: Int,
    var zan: Int
) : Serializable {
    override fun toString(): String {
        return title
    }
}