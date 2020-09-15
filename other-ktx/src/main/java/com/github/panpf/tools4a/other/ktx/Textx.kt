/*
 * Copyright (C) 2020 panpf <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4a.other.ktx

import android.text.SpannableStringBuilder
import com.github.panpf.tools4a.other.Textx


/**
 * Modify the display color of a string using Html
 */
inline fun String.changeColorByHtml(color: String): String = Textx.changeColorByHtml(this, color)

/**
 * Modify the display red color of a string using Html
 */
inline fun String.changeColorToRedByHtml(): String = Textx.changeColorToRedByHtml(this)


/**
 * Use Html to modify the display color of all specified keywords in a string
 */
inline fun String.changeKeywordColorByHtml(keyword: String, color: String): String = Textx.changeKeywordColorByHtml(this, keyword, color)

/**
 * Use Html to modify the display color of all specified keywords in a string
 */
inline fun String.changeKeywordColorToRedByHtml(keyword: String): String = Textx.changeKeywordColorToRedByHtml(this, keyword)


/**
 * Use Spannable to modify the display color of a string
 */
inline fun String.changeColorBySpannable(color: Int): SpannableStringBuilder = Textx.changeColorBySpannable(this, color)

/**
 * Use Spannable to modify the display color of a string
 */
inline fun String.changeColorToRedBySpannable(): SpannableStringBuilder = Textx.changeColorToRedBySpannable(this)


/**
 * Use Spannable to modify the display color of all specified keywords in a string
 */
inline fun String.changeKeywordColorBySpannable(keyword: String, color: Int): SpannableStringBuilder =
        Textx.changeKeywordColorBySpannable(this, keyword, color)

/**
 * Use Spannable to modify the display color of all specified keywords in a string
 */
inline fun String.changeKeywordColorToRedBySpannable(keyword: String): SpannableStringBuilder = Textx.changeKeywordColorToRedBySpannable(this, keyword)