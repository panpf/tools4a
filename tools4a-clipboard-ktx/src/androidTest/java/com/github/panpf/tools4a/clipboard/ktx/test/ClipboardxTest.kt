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

package com.github.panpf.tools4a.clipboard.ktx.test

import android.app.Activity
import android.content.ClipDescription
import android.content.ClipboardManager.OnPrimaryClipChangedListener
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.clipboard.*
import com.github.panpf.tools4a.clipboard.ktx.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ClipboardxTest {

    companion object {

        private val TEST_TEXT = "我也在奢望，奢望有一天，你能来找我"
        private val TEST_TEXT2 = "你还会回来吗？应该不会了。"
        private val TEST_HTML_TEXT = "短文学伤感文章情感日志心情日记散文精选诗歌大全经典语句专题长篇小说投稿网址积分商城"
        private val TEST_HTML_HTML = "<ul>\n" +
                "\t\t<li class=\"thiscase\"><a href=\"/sanwen/suibi/\">散文随笔</a></li>\n" +
                "\t\t<li><a href=\"/qinggan/meiwen/\">美文欣赏</a></li>\n" +
                "\t\t<li><a href=\"/yuju/shanggan/\">伤感的句子</a></li>\n" +
                "\t\t<li><a href=\"/yuju/youmei/\">优美的句子</a></li>\n" +
                "\t\t<li><a href=\"/yuju/weimei/\">唯美的句子</a></li>\n" +
                "\t\t<li><a href=\"/yuju/shangxin/\">伤心的句子</a></li>\n" +
                "\t\t<li><a href=\"/duanwen/mingyan/\">名言名句</a></li>\n" +
                "\t\t<li><a href=\"/yuju/xiangnian/\">想念的句子</a></li>\n" +
                "\t\t<li><a href=\"/duanwen/lizhi/\">励志签名</a></li>\n" +
                "\t\t<li><a href=\"/duanwen/gerenqianming/\">个人签名</a></li>\n" +
                "\t\t<li><a href=\"/huayu/ganren/\">感人的话</a></li>\n" +
                "\t\t<li class=\"thiscase\"><a href=\"/yulu/aiqing/\">爱情语录</a></li>\n" +
                "\t\t<li><a href=\"/huayu/biaobai/\">表白的话</a></li>\n" +
                "\t\t<li><a href=\"/juzi/beishang/\">悲伤的句子</a></li>\n" +
                "\t\t<li><a href=\"/yulu/gaoxiao/\">搞笑语录</a></li>\n" +
                "\t\t<li><a href=\"/yulu/aiqingxuanyan/\">爱情宣言</a></li>\n" +
                "\t\t<li><a href=\"/juzi/biaobai/\">表白的句子</a></li>\n" +
                "\t\t<li><a href=\"/duanwen/geyan/\">人生格言</a></li>\n" +
                "\t\t<li><a href=\"/yulu/yijuhua/\">一句话经典语录</a></li>\n" +
                "\t\t<li><a href=\"/huayu/lizhi/\">励志的话</a></li>\n" +
                "\t\t<li><a href=\"/yulu/shangxinqianming/\">伤心的个性签名</a></li>\n" +
                "\t\t<li><a href=\"/huayu/zheli/\">有哲理的话</a></li>\n" +
                "\t</ul>\n"
        private val TEST_HTML_TEXT2 = "散文随笔美文欣赏伤感的句子优美的句子唯美的句子伤心的句子名言名句想念的句子励志签名个人签名感人的话爱情语录表白的话悲伤的句子搞笑语录爱情宣言表白的句子人生格言一句话经典语录励志的话伤心的个性签名有哲理的话"
        private val TEST_HTML_HTML2 = "<ul>\n" +
                "    <li><a href=\"/\" >短文学</a></li>\n" +
                "    <li><a href=\"/shanggan/\" >伤感文章</a></li>\n" +
                "    <li><a href=\"/qinggan/\" >情感日志</a></li>\n" +
                "    <li><a href=\"/diary/\" >心情日记</a></li>\n" +
                "    <li><a href=\"/sanwen/\" >散文精选</a></li>\n" +
                "    <li><a href=\"/shige/\" >诗歌大全</a></li>\n" +
                "    <li><a href=\"/yuju/\" >经典语句</a></li>\n" +
                "    <li><a href=\"/haowenzhang/\" >专题</a></li>\n" +
                "    <li><a href=\"/book/\">长篇小说</a></li>\n" +
                "    <li><a href=\"/tougao.html\">投稿网址</a></li>\n" +
                "    <li><a href=\"/shop\">积分商城</a></li>\n" +
                "  </ul>\n"
    }

    @Test
    fun testLabel() {
        val context = InstrumentationRegistry.getContext()

        context.copyText("clipLabel", TEST_TEXT)
        val content = context.getClipDataLabel()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(content)
        } else if (content != null) {
            Assert.assertEquals("clipLabel", content)
        } else {
            Assert.fail("Get clipboard content error")
        }
    }

    @Test
    fun testText() {
        val context = InstrumentationRegistry.getContext()
        context.copyText(TEST_TEXT)
        val content = context.getClipText()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(content)
        } else if (content != null) {
            Assert.assertEquals(TEST_TEXT, content)
        } else {
            Assert.fail("Get clipboard content error")
        }
        context.copyText(arrayOf(TEST_TEXT, TEST_TEXT2))
        val texts = context.getClipTexts()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(texts)
        } else if (texts != null) {
            Assert.assertEquals(TEST_TEXT, texts[0])
            Assert.assertEquals(TEST_TEXT2, texts[1])
        } else {
            Assert.fail("Get clipboard content error")
        }
    }

    @Test
    fun testHtmlText() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return
        }
        val context = InstrumentationRegistry.getContext()
        context.copyHtmlText(TEST_HTML_TEXT, TEST_HTML_HTML)
        val clipHtmlText = context.getClipHtmlText()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(clipHtmlText)
        } else if (clipHtmlText != null) {
            Assert.assertEquals(TEST_HTML_TEXT, clipHtmlText.text)
            Assert.assertEquals(TEST_HTML_HTML, clipHtmlText.htmlText)
        } else {
            Assert.fail("Get clipboard content error")
        }
        context.copyHtmlText(arrayOf(ClipHtmlText(TEST_HTML_TEXT, TEST_HTML_HTML), ClipHtmlText(TEST_HTML_TEXT2, TEST_HTML_HTML2)))
        val htmlTexts = context.getClipHtmlTexts()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(htmlTexts)
        } else if (htmlTexts != null) {
            val clipHtmlText1 = htmlTexts[0]
            Assert.assertEquals(TEST_HTML_TEXT, clipHtmlText1.text)
            Assert.assertEquals(TEST_HTML_HTML, clipHtmlText1.htmlText)
            val clipHtmlText2 = htmlTexts[1]
            Assert.assertEquals(TEST_HTML_TEXT2, clipHtmlText2.text)
            Assert.assertEquals(TEST_HTML_HTML2, clipHtmlText2.htmlText)
        } else {
            Assert.fail("Get clipboard content error")
        }
    }

    @Test
    fun testIntent() {
        val context = InstrumentationRegistry.getContext()
        val intent = Intent(context, Activity::class.java)
        context.copyIntent(intent)
        val result = context.getClipIntent()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(result)
        } else if (result != null) {
            Assert.assertEquals(result.component.toString(), intent.component.toString())
        } else {
            Assert.fail("Get clipboard content error")
        }
        val intent2 = Intent(context, Activity::class.java)
        context.copyIntent(arrayOf(intent, intent2))
        val results = context.getClipIntents()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(results)
        } else if (results != null) {
            Assert.assertEquals(results[0].component.toString(), intent.component.toString())
            Assert.assertEquals(results[1].component.toString(), intent2.component.toString())
        } else {
            Assert.fail("Get clipboard content error")
        }
    }

    @Test
    fun testRawUri() {
        val context = InstrumentationRegistry.getContext()
        val uri = Uri.parse("https://www.github.com")
        context.copyRawUri(uri)
        val result = context.getClipUri()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(result)
        } else if (result != null) {
            Assert.assertEquals(result.mimeType, ClipDescription.MIMETYPE_TEXT_URILIST)
            Assert.assertEquals(result.uri.toString(), uri.toString())
        } else {
            Assert.fail("Get clipboard content error")
        }
        val uri2 = Uri.parse("https://www.youtube.com")
        context.copyRawUri(arrayOf(uri, uri2))
        val results = context.getClipUris()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(results)
        } else if (results != null) {
            Assert.assertEquals(results[0].mimeType, ClipDescription.MIMETYPE_TEXT_URILIST)
            Assert.assertEquals(results[0].uri.toString(), uri.toString())
            Assert.assertEquals(results[1].mimeType, ClipDescription.MIMETYPE_TEXT_URILIST)
            Assert.assertEquals(results[1].uri.toString(), uri2.toString())
        } else {
            Assert.fail("Get clipboard content error")
        }
    }

    @Test
    fun testMimeTypeUri() {
        val context = InstrumentationRegistry.getContext()
        val uri = Uri.parse("https://www.github.com")
        context.copyMimeTypeUri("app/android", uri)
        val result = context.getClipUri()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(result)
        } else if (result != null) {
            Assert.assertEquals("app/android", result.mimeType)
            Assert.assertEquals(uri.toString(), result.uri.toString())
        } else {
            Assert.fail("Get clipboard content error")
        }
        val uri2 = Uri.parse("https://www.youtube.com")
        context.copyMimeTypeUri("app/android", arrayOf(uri, uri2))
        val results = context.getClipUris()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(results)
        } else if (results != null) {
            Assert.assertEquals("app/android", results[0].mimeType)
            Assert.assertEquals(uri.toString(), results[0].uri.toString())
            Assert.assertEquals("app/android", results[1].mimeType)
            Assert.assertEquals(uri2.toString(), results[1].uri.toString())
        } else {
            Assert.fail("Get clipboard content error")
        }
    }

    @Test
    fun testUri() {
        val context = InstrumentationRegistry.getContext()
        val uri = Uri.parse("https://www.github.com")
        context.copyUri(uri)
        val result = context.getClipUri()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(result)
        } else if (result != null) {
            Assert.assertEquals(ClipDescription.MIMETYPE_TEXT_URILIST, result.mimeType)
            Assert.assertEquals(uri.toString(), result.uri.toString())
        } else {
            Assert.fail("Get clipboard content error")
        }
        val uri2 = Uri.parse("https://www.youtube.com")
        context.copyUri(arrayOf(uri, uri2))
        val results = context.getClipUris()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(results)
        } else if (results != null) {
            Assert.assertEquals(ClipDescription.MIMETYPE_TEXT_URILIST, results[0].mimeType)
            Assert.assertEquals(uri.toString(), results[0].uri.toString())
            Assert.assertEquals(ClipDescription.MIMETYPE_TEXT_URILIST, results[1].mimeType)
            Assert.assertEquals(uri2.toString(), results[1].uri.toString())
        } else {
            Assert.fail("Get clipboard content error")
        }
    }

    @Test
    fun testListener() {
        val context = InstrumentationRegistry.getContext()
        val result = arrayOfNulls<String>(1)
        val listener = OnPrimaryClipChangedListener { result[0] = "onPrimaryClipChanged" }
        context.addPrimaryClipChangedListener(listener)
        context.copyText("Hello Word")
        // Callback will be delayed
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(result[0])
        } else {
            Assert.assertEquals("onPrimaryClipChanged", result[0])
        }
        result[0] = "None"
        context.removePrimaryClipChangedListener(listener)
        context.copyText("Hello Word")
        // Callback will be delayed
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Assert.assertEquals("None", result[0])
    }

    @Test
    fun testMultiType() {
        val context = InstrumentationRegistry.getContext()
        val text = ClipPlainText(TEST_TEXT)
        val htmlText = ClipHtmlText(TEST_HTML_TEXT, TEST_HTML_HTML)
        val intent = ClipIntent(Intent(context, Activity::class.java))
        val uri = ClipUri("app/android", Uri.parse("https://www.youtube.com"))
        context.copyContents(arrayOf(text, htmlText, intent, uri))
        val results = context.getClipDataContents()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(results)
        } else if (results != null) {
            val textResult = results[0] as ClipPlainText
            Assert.assertEquals(textResult.text, text.text)
            val htmlTextResult = results[1] as ClipHtmlText
            Assert.assertEquals(htmlTextResult.text, htmlText.text)
            Assert.assertEquals(htmlTextResult.htmlText, htmlText.htmlText)
            val intentResult = results[2] as ClipIntent
            Assert.assertEquals(intentResult.intent.component.toString(), intent.intent.component.toString())
            val uriResult = results[3] as ClipUri
            Assert.assertEquals(uriResult.mimeType, uri.mimeType)
            Assert.assertEquals(uriResult.uri.toString(), uri.uri.toString())
        } else {
            Assert.fail("Get clipboard content error")
        }
    }

    @Test
    fun testClean() {
        if (Build.VERSION.SDK_INT >= 28) {
            val context = InstrumentationRegistry.getContext()
            context.copyText("Hello Word")
            val content = context.getClipText()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Assert.assertNull(content)
            } else if (content != null) {
                Assert.assertEquals("Hello Word", content)
            } else {
                Assert.fail("Get clipboard content error")
            }
            context.clearClip()
            Assert.assertNull(context.getClipText())
        }
    }
}
