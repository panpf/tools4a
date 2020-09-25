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

package com.github.panpf.tools4a.dimen.test;

import android.content.res.Resources;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.github.panpf.tools4a.dimen.Dimenx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DimenxTest {

    @Test
    public void testDp2px() {
        float density = Resources.getSystem().getDisplayMetrics().density;

        Assert.assertEquals((int) (7f * density + 0.5f), Dimenx.dp2px(7f));
        Assert.assertEquals((int) ((float) 7 * density + 0.5f), Dimenx.dp2px(7));
        Assert.assertEquals(0, Dimenx.dp2px(0f));
        Assert.assertEquals(0, Dimenx.dp2px(0));

        Assert.assertEquals(7f * density, Dimenx.dp2pxF(7f), 0f);
        Assert.assertEquals((float) 7 * density, Dimenx.dp2pxF(7), 0f);
        Assert.assertEquals(0f, Dimenx.dp2pxF(0f), 0f);
        Assert.assertEquals(0f, Dimenx.dp2pxF(0), 0f);
    }

    @Test
    public void testSp2px() {
        float scaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity;

        Assert.assertEquals((int) (7f * scaledDensity + 0.5f), Dimenx.sp2px(7f));
        Assert.assertEquals((int) ((float) 7 * scaledDensity + 0.5f), Dimenx.sp2px(7));
        Assert.assertEquals(0, Dimenx.sp2px(0f));
        Assert.assertEquals(0, Dimenx.sp2px(0));

        Assert.assertEquals(7f * scaledDensity, Dimenx.sp2pxF(7f), 0f);
        Assert.assertEquals((float) 7 * scaledDensity, Dimenx.sp2pxF(7), 0f);
        Assert.assertEquals(0f, Dimenx.sp2pxF(0f), 0f);
        Assert.assertEquals(0f, Dimenx.sp2pxF(0), 0f);
    }

    @Test
    public void testPt2px() {
        float xdpi = Resources.getSystem().getDisplayMetrics().xdpi;

        Assert.assertEquals((int) (7f * xdpi * (1.0f / 72) + 0.5f), Dimenx.pt2px(7f));
        Assert.assertEquals((int) ((float) 7 * xdpi * (1.0f / 72) + 0.5f), Dimenx.pt2px(7));
        Assert.assertEquals(0, Dimenx.pt2px(0f));
        Assert.assertEquals(0, Dimenx.pt2px(0));

        Assert.assertEquals(7f * xdpi * (1.0f / 72), Dimenx.pt2pxF(7f), 0f);
        Assert.assertEquals((float) 7 * xdpi * (1.0f / 72), Dimenx.pt2pxF(7), 0f);
        Assert.assertEquals(0f, Dimenx.pt2pxF(0f), 0f);
        Assert.assertEquals(0f, Dimenx.pt2pxF(0), 0f);
    }

    @Test
    public void testIn2px() {
        float xdpi = Resources.getSystem().getDisplayMetrics().xdpi;

        Assert.assertEquals((int) (7f * xdpi + 0.5f), Dimenx.in2px(7f));
        Assert.assertEquals((int) ((float) 7 * xdpi + 0.5f), Dimenx.in2px(7));
        Assert.assertEquals(0, Dimenx.in2px(0f));
        Assert.assertEquals(0, Dimenx.in2px(0));

        Assert.assertEquals(7f * xdpi, Dimenx.in2pxF(7f), 0f);
        Assert.assertEquals((float) 7 * xdpi, Dimenx.in2pxF(7), 0f);
        Assert.assertEquals(0f, Dimenx.in2pxF(0f), 0f);
        Assert.assertEquals(0f, Dimenx.in2pxF(0), 0f);
    }

    @Test
    public void testMm2px() {
        float xdpi = Resources.getSystem().getDisplayMetrics().xdpi;

        Assert.assertEquals((int) (7f * xdpi * (1.0f / 25.4f) + 0.5f), Dimenx.mm2px(7f));
        Assert.assertEquals((int) ((float) 7 * xdpi * (1.0f / 25.4f) + 0.5f), Dimenx.mm2px(7));
        Assert.assertEquals(0, Dimenx.mm2px(0f));
        Assert.assertEquals(0, Dimenx.mm2px(0));

        Assert.assertEquals(7f * xdpi * (1.0f / 25.4f), Dimenx.mm2pxF(7f), 0f);
        Assert.assertEquals((float) 7 * xdpi * (1.0f / 25.4f), Dimenx.mm2pxF(7), 0f);
        Assert.assertEquals(0f, Dimenx.mm2pxF(0f), 0f);
        Assert.assertEquals(0f, Dimenx.mm2pxF(0), 0f);
    }

    @Test
    public void testPx2Dp() {
        float density = Resources.getSystem().getDisplayMetrics().density;

        Assert.assertEquals(7f / density, Dimenx.px2dp(7f), 0f);
        Assert.assertEquals((float) 7 / density, Dimenx.px2dp(7), 0f);
        Assert.assertEquals(0f, Dimenx.px2dp(0f), 0f);
        Assert.assertEquals(0f, Dimenx.px2dp(0), 0f);
    }

    @Test
    public void testPx2Sp() {
        float scaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity;

        Assert.assertEquals(7f / scaledDensity, Dimenx.px2sp(7f), 0f);
        Assert.assertEquals((float) 7 / scaledDensity, Dimenx.px2sp(7), 0f);
        Assert.assertEquals(0f, Dimenx.px2sp(0f), 0f);
        Assert.assertEquals(0f, Dimenx.px2sp(0), 0f);
    }

    @Test
    public void testPx2Pt() {
        float xdpi = Resources.getSystem().getDisplayMetrics().xdpi;

        Assert.assertEquals(7f / xdpi / (1.0f / 72), Dimenx.px2pt(7f), 0f);
        Assert.assertEquals((float) 7 / xdpi / (1.0f / 72), Dimenx.px2pt(7), 0f);
        Assert.assertEquals(0f, Dimenx.px2pt(0f), 0f);
        Assert.assertEquals(0f, Dimenx.px2pt(0), 0f);
    }

    @Test
    public void testPx2In() {
        float xdpi = Resources.getSystem().getDisplayMetrics().xdpi;

        Assert.assertEquals(7f / xdpi, Dimenx.px2in(7f), 0f);
        Assert.assertEquals((float) 7 / xdpi, Dimenx.px2in(7), 0f);
        Assert.assertEquals(0f, Dimenx.px2in(0f), 0f);
        Assert.assertEquals(0f, Dimenx.px2in(0), 0f);
    }

    @Test
    public void testPx2Mm() {
        float xdpi = Resources.getSystem().getDisplayMetrics().xdpi;

        Assert.assertEquals(7f / xdpi / (1.0f / 25.4f), Dimenx.px2mm(7f), 0f);
        Assert.assertEquals((float) 7 / xdpi / (1.0f / 25.4f), Dimenx.px2mm(7), 0f);
        Assert.assertEquals(0f, Dimenx.px2mm(0f), 0f);
        Assert.assertEquals(0f, Dimenx.px2mm(0), 0f);
    }
}
