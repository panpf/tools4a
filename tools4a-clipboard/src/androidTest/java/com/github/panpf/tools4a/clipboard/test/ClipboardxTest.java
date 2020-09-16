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

package com.github.panpf.tools4a.clipboard.test;

import android.app.Activity;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.panpf.tools4a.clipboard.ClipContent;
import com.github.panpf.tools4a.clipboard.ClipHtmlText;
import com.github.panpf.tools4a.clipboard.ClipIntent;
import com.github.panpf.tools4a.clipboard.ClipPlainText;
import com.github.panpf.tools4a.clipboard.ClipUri;
import com.github.panpf.tools4a.clipboard.Clipboardx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ClipboardxTest {

    private static final String TEST_TEXT = "我也在奢望，奢望有一天，你能来找我";
    private static final String TEST_TEXT2 = "你还会回来吗？应该不会了。";
    private static final String TEST_HTML_TEXT = "短文学伤感文章情感日志心情日记散文精选诗歌大全经典语句专题长篇小说投稿网址积分商城";
    private static final String TEST_HTML_HTML = "<ul>\n" +
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
            "\t</ul>\n";
    private static final String TEST_HTML_TEXT2 = "散文随笔美文欣赏伤感的句子优美的句子唯美的句子伤心的句子名言名句想念的句子励志签名个人签名感人的话爱情语录表白的话悲伤的句子搞笑语录爱情宣言表白的句子人生格言一句话经典语录励志的话伤心的个性签名有哲理的话";
    private static final String TEST_HTML_HTML2 = "<ul>\n" +
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
            "  </ul>\n";

    @Test
    public void testLabel() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Clipboardx.copyText(context, "clipLabel", TEST_TEXT);
        CharSequence content = Clipboardx.getLabel(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(content);
        } else if (content != null) {
            Assert.assertEquals("clipLabel", content);
        } else {
            Assert.fail("Get clipboard content error");
        }
    }

    @Test
    public void testText() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Clipboardx.copyText(context, TEST_TEXT);
        CharSequence content = Clipboardx.getText(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(content);
        } else if (content != null) {
            Assert.assertEquals(TEST_TEXT, content);
        } else {
            Assert.fail("Get clipboard content error");
        }

        Clipboardx.copyText(context, new CharSequence[]{TEST_TEXT, TEST_TEXT2});
        CharSequence[] texts = Clipboardx.getTexts(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(texts);
        } else if (texts != null) {
            Assert.assertEquals(TEST_TEXT, texts[0]);
            Assert.assertEquals(TEST_TEXT2, texts[1]);
        } else {
            Assert.fail("Get clipboard content error");
        }
    }

    @Test
    public void testHtmlText() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }

        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Clipboardx.copyHtmlText(context, TEST_HTML_TEXT, TEST_HTML_HTML);
        ClipHtmlText clipHtmlText = Clipboardx.getHtmlText(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(clipHtmlText);
        } else if (clipHtmlText != null) {
            Assert.assertEquals(TEST_HTML_TEXT, clipHtmlText.text);
            Assert.assertEquals(TEST_HTML_HTML, clipHtmlText.htmlText);
        } else {
            Assert.fail("Get clipboard content error");
        }

        Clipboardx.copyHtmlText(context, new ClipHtmlText[]{new ClipHtmlText(TEST_HTML_TEXT, TEST_HTML_HTML), new ClipHtmlText(TEST_HTML_TEXT2, TEST_HTML_HTML2)});
        ClipHtmlText[] htmlTexts = Clipboardx.getHtmlTexts(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(htmlTexts);
        } else if (htmlTexts != null) {
            ClipHtmlText clipHtmlText1 = htmlTexts[0];
            Assert.assertEquals(TEST_HTML_TEXT, clipHtmlText1.text);
            Assert.assertEquals(TEST_HTML_HTML, clipHtmlText1.htmlText);

            ClipHtmlText clipHtmlText2 = htmlTexts[1];
            Assert.assertEquals(TEST_HTML_TEXT2, clipHtmlText2.text);
            Assert.assertEquals(TEST_HTML_HTML2, clipHtmlText2.htmlText);
        } else {
            Assert.fail("Get clipboard content error");
        }
    }

    @Test
    public void testIntent() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Intent intent = new Intent(context, Activity.class);
        Clipboardx.copyIntent(context, intent);
        Intent result = Clipboardx.getIntent(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(result);
        } else if (result != null) {
            Assert.assertEquals(result.getComponent().toString(), intent.getComponent().toString());
        } else {
            Assert.fail("Get clipboard content error");
        }

        Intent intent2 = new Intent(context, Activity.class);
        Clipboardx.copyIntent(context, new Intent[]{intent, intent2});
        Intent[] results = Clipboardx.getIntents(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(results);
        } else if (results != null) {
            Assert.assertEquals(results[0].getComponent().toString(), intent.getComponent().toString());
            Assert.assertEquals(results[1].getComponent().toString(), intent2.getComponent().toString());
        } else {
            Assert.fail("Get clipboard content error");
        }
    }

    @Test
    public void testRawUri() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Uri uri = Uri.parse("https://www.github.com");
        Clipboardx.copyRawUri(context, uri);
        ClipUri result = Clipboardx.getUri(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(result);
        } else if (result != null) {
            Assert.assertEquals(result.mimeType, ClipDescription.MIMETYPE_TEXT_URILIST);
            Assert.assertEquals(result.uri.toString(), uri.toString());
        } else {
            Assert.fail("Get clipboard content error");
        }

        Uri uri2 = Uri.parse("https://www.youtube.com");
        Clipboardx.copyRawUri(context, new Uri[]{uri, uri2});
        ClipUri[] results = Clipboardx.getUris(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(results);
        } else if (results != null) {
            Assert.assertEquals(results[0].mimeType, ClipDescription.MIMETYPE_TEXT_URILIST);
            Assert.assertEquals(results[0].uri.toString(), uri.toString());
            Assert.assertEquals(results[1].mimeType, ClipDescription.MIMETYPE_TEXT_URILIST);
            Assert.assertEquals(results[1].uri.toString(), uri2.toString());
        } else {
            Assert.fail("Get clipboard content error");
        }
    }

    @Test
    public void testMimeTypeUri() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Uri uri = Uri.parse("https://www.github.com");
        Clipboardx.copyMimeTypeUri(context, "app/android", uri);
        ClipUri result = Clipboardx.getUri(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(result);
        } else if (result != null) {
            Assert.assertEquals("app/android", result.mimeType);
            Assert.assertEquals(uri.toString(), result.uri.toString());
        } else {
            Assert.fail("Get clipboard content error");
        }

        Uri uri2 = Uri.parse("https://www.youtube.com");
        Clipboardx.copyMimeTypeUri(context, "app/android", new Uri[]{uri, uri2});
        ClipUri[] results = Clipboardx.getUris(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(results);
        } else if (results != null) {
            Assert.assertEquals("app/android", results[0].mimeType);
            Assert.assertEquals(uri.toString(), results[0].uri.toString());
            Assert.assertEquals("app/android", results[1].mimeType);
            Assert.assertEquals(uri2.toString(), results[1].uri.toString());
        } else {
            Assert.fail("Get clipboard content error");
        }
    }

    @Test
    public void testUri() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Uri uri = Uri.parse("https://www.github.com");
        Clipboardx.copyUri(context, uri);
        ClipUri result = Clipboardx.getUri(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(result);
        } else if (result != null) {
            Assert.assertEquals(ClipDescription.MIMETYPE_TEXT_URILIST, result.mimeType);
            Assert.assertEquals(uri.toString(), result.uri.toString());
        } else {
            Assert.fail("Get clipboard content error");
        }

        Uri uri2 = Uri.parse("https://www.youtube.com");
        Clipboardx.copyUri(context, new Uri[]{uri, uri2});
        ClipUri[] results = Clipboardx.getUris(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(results);
        } else if (results != null) {
            Assert.assertEquals(ClipDescription.MIMETYPE_TEXT_URILIST, results[0].mimeType);
            Assert.assertEquals(uri.toString(), results[0].uri.toString());
            Assert.assertEquals(ClipDescription.MIMETYPE_TEXT_URILIST, results[1].mimeType);
            Assert.assertEquals(uri2.toString(), results[1].uri.toString());
        } else {
            Assert.fail("Get clipboard content error");
        }
    }

    @Test
    public void testListener() {
        final Context context = InstrumentationRegistry.getInstrumentation().getContext();

        final String[] result = new String[1];
        ClipboardManager.OnPrimaryClipChangedListener listener = new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                result[0] = "onPrimaryClipChanged";
            }
        };

        Clipboardx.addPrimaryClipChangedListener(context, listener);
        Clipboardx.copyText(context, "Hello Word");
        // Callback will be delayed
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(result[0]);
        } else {
            Assert.assertEquals("onPrimaryClipChanged", result[0]);
        }

        result[0] = "None";
        Clipboardx.removePrimaryClipChangedListener(context, listener);
        Clipboardx.copyText(context, "Hello Word");
        // Callback will be delayed
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("None", result[0]);
    }

    @Test
    public void testMultiType() {
        final Context context = InstrumentationRegistry.getInstrumentation().getContext();

        ClipPlainText text = new ClipPlainText(TEST_TEXT);
        ClipHtmlText htmlText = new ClipHtmlText(TEST_HTML_TEXT, TEST_HTML_HTML);
        ClipIntent intent = new ClipIntent(new Intent(context, Activity.class));
        ClipUri uri = new ClipUri("app/android", Uri.parse("https://www.youtube.com"));

        Clipboardx.copyContents(context, new ClipContent[]{text, htmlText, intent, uri});

        ClipContent[] results = Clipboardx.getContents(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Assert.assertNull(results);
        } else if (results != null) {
            ClipPlainText textResult = (ClipPlainText) results[0];
            Assert.assertEquals(textResult.text, text.text);

            ClipHtmlText htmlTextResult = (ClipHtmlText) results[1];
            Assert.assertEquals(htmlTextResult.text, htmlText.text);
            Assert.assertEquals(htmlTextResult.htmlText, htmlText.htmlText);

            ClipIntent intentResult = (ClipIntent) results[2];
            Assert.assertEquals(intentResult.intent.getComponent().toString(), intent.intent.getComponent().toString());

            ClipUri uriResult = (ClipUri) results[3];
            Assert.assertEquals(uriResult.mimeType, uri.mimeType);
            Assert.assertEquals(uriResult.uri.toString(), uri.uri.toString());
        } else {
            Assert.fail("Get clipboard content error");
        }
    }

    @Test
    public void testClean() {
        if (Build.VERSION.SDK_INT >= 28) {
            final Context context = InstrumentationRegistry.getInstrumentation().getContext();

            Clipboardx.copyText(context, "Hello Word");
            CharSequence content = Clipboardx.getText(context);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Assert.assertNull(content);
            } else if (content != null) {
                Assert.assertEquals("Hello Word", content);
            } else {
                Assert.fail("Get clipboard content error");
            }

            Clipboardx.clear(context);
            Assert.assertNull(Clipboardx.getText(context));
        }
    }
}
