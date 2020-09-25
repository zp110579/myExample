package com.zee.http

import com.zee.http.request.ZStringResult
import com.zee.http.request.ZxRequest
import java.util.HashMap

/**
 *created by zee on 2020/9/8.
 *
 */
object WanAndroidHttpManager {
    private const val BASE_URL = "https://www.wanandroid.com/"

    /**
     * post 请求
     */
    fun getZxRequestPost(url: String): ZxRequest {
        return getZxRequest(url).post()
    }

    /**
     * get 请求
     */
    fun getZxRequestGet(url: String): ZxRequest {
        return getZxRequest(url).get()
    }

    private fun getZxRequest(url: String): ZxRequest {
        return MyOk.load("${BASE_URL}$url").showLog(true, "玩Android")
    }
}


/**
 * String直接转网络请求
 */
fun String.postHttpRequest(hashMap: HashMap<String, Any>): ZxRequest {
    return WanAndroidHttpManager.getZxRequestPost(this).putParams(hashMap)
}

/**
 * String直接转网络请求
 */
fun String.getHttpRequest(hashMap: HashMap<String, Any>? = null): ZxRequest {
    return WanAndroidHttpManager.getZxRequestGet(this).putParams(hashMap)
}

/**
 * String直接转网络请求
 */
fun String.executeHttpWithGet(zStringResult: ZStringResult) {
    return WanAndroidHttpManager.getZxRequestGet(this).execute(zStringResult)
}