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

package com.github.panpf.tools4a.utils.test;

import android.graphics.Color;

import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.utils.Textx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TextxTest {


    @Test
    public void testChangeColor() {
        Assert.assertEquals("<font color=\"green\">警告</font>", Textx.changeColorByHtml("警告", "green"));
        Assert.assertEquals("<font color=\"red\">警告</font>", Textx.changeColorToRedByHtml("警告"));

        Assert.assertEquals("郑重<font color=\"green\">警告</font>如下郑重<font color=\"green\">警告</font>如下", Textx.changeKeywordColorByHtml("郑重警告如下郑重警告如下", "警告", "green"));
        Assert.assertEquals("郑重<font color=\"red\">警告</font>如下郑重<font color=\"red\">警告</font>如下", Textx.changeKeywordColorToRedByHtml("郑重警告如下郑重警告如下", "警告"));

        Assert.assertNotNull(Textx.changeColorBySpannable("警告", Color.GREEN));
        Assert.assertNotNull(Textx.changeColorToRedBySpannable("警告"));

        Assert.assertNotNull(Textx.changeKeywordColorBySpannable("郑重警告如下郑重警告如下", "警告", Color.GREEN));
        Assert.assertNotNull(Textx.changeKeywordColorToRedBySpannable("郑重警告如下郑重警告如下", "警告"));
    }
}
