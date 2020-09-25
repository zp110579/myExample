package com.zee.ext

import androidx.core.text.HtmlCompat

/**
 * created by zee on 2020/9/11.
 */
fun String?.htmlToSpanned() =
    if (this.isNullOrEmpty()) "" else HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)