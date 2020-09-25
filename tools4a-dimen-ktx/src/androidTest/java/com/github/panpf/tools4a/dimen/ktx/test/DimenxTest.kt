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

package com.github.panpf.tools4a.dimen.ktx.test

import android.content.res.Resources
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.panpf.tools4a.dimen.ktx.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DimenxTest {

    @Test
    fun testDp2px() {
        val density = Resources.getSystem().displayMetrics.density

        Assert.assertEquals((7f * density + 0.5f).toLong(), 7f.dp2px.toLong())
        Assert.assertEquals((7.toFloat() * density + 0.5f).toLong(), 7.dp2px.toLong())
        Assert.assertEquals(0, 0f.dp2px.toLong())
        Assert.assertEquals(0, 0.dp2px.toLong())

        Assert.assertEquals(7f * density, 7f.dp2pxF, 0f)
        Assert.assertEquals(7.toFloat() * density, 7.dp2pxF, 0f)
        Assert.assertEquals(0f, 0f.dp2pxF, 0f)
        Assert.assertEquals(0f, 0.dp2pxF, 0f)
    }

    @Test
    fun testSp2px() {
        val scaledDensity = Resources.getSystem().displayMetrics.scaledDensity

        Assert.assertEquals((7f * scaledDensity + 0.5f).toLong(), 7f.sp2px.toLong())
        Assert.assertEquals((7.toFloat() * scaledDensity + 0.5f).toLong(), 7.sp2px.toLong())
        Assert.assertEquals(0, 0f.sp2px.toLong())
        Assert.assertEquals(0, 0.sp2px.toLong())

        Assert.assertEquals(7f * scaledDensity, 7f.sp2pxF, 0f)
        Assert.assertEquals(7.toFloat() * scaledDensity, 7.sp2pxF, 0f)
        Assert.assertEquals(0f, 0f.sp2pxF, 0f)
        Assert.assertEquals(0f, 0.sp2pxF, 0f)
    }

    @Test
    fun testPt2px() {
        val xdpi = Resources.getSystem().displayMetrics.xdpi

        Assert.assertEquals((7f * xdpi * (1.0f / 72) + 0.5f).toLong(), 7f.pt2px.toLong())
        Assert.assertEquals((7.toFloat() * xdpi * (1.0f / 72) + 0.5f).toLong(), 7.pt2px.toLong())
        Assert.assertEquals(0, 0f.pt2px.toLong())
        Assert.assertEquals(0, 0.pt2px.toLong())

        Assert.assertEquals(7f * xdpi * (1.0f / 72), 7f.pt2pxF, 0f)
        Assert.assertEquals(7.toFloat() * xdpi * (1.0f / 72), 7.pt2pxF, 0f)
        Assert.assertEquals(0f, 0f.pt2pxF, 0f)
        Assert.assertEquals(0f, 0.pt2pxF, 0f)
    }

    @Test
    fun testIn2px() {
        val xdpi = Resources.getSystem().displayMetrics.xdpi

        Assert.assertEquals((7f * xdpi + 0.5f).toLong(), 7f.in2px.toLong())
        Assert.assertEquals((7.toFloat() * xdpi + 0.5f).toLong(), 7.in2px.toLong())
        Assert.assertEquals(0, 0f.in2px.toLong())
        Assert.assertEquals(0, 0.in2px.toLong())

        Assert.assertEquals(7f * xdpi, 7f.in2pxF, 0f)
        Assert.assertEquals(7.toFloat() * xdpi, 7.in2pxF, 0f)
        Assert.assertEquals(0f, 0f.in2pxF, 0f)
        Assert.assertEquals(0f, 0.in2pxF, 0f)
    }

    @Test
    fun testMm2px() {
        val xdpi = Resources.getSystem().displayMetrics.xdpi

        Assert.assertEquals((7f * xdpi * (1.0f / 25.4f) + 0.5f).toLong(), 7f.mm2px.toLong())
        Assert.assertEquals((7.toFloat() * xdpi * (1.0f / 25.4f) + 0.5f).toLong(), 7.mm2px.toLong())
        Assert.assertEquals(0, 0f.mm2px.toLong())
        Assert.assertEquals(0, 0.mm2px.toLong())

        Assert.assertEquals(7f * xdpi * (1.0f / 25.4f), 7f.mm2pxF, 0f)
        Assert.assertEquals(7.toFloat() * xdpi * (1.0f / 25.4f), 7.mm2pxF, 0f)
        Assert.assertEquals(0f, 0f.mm2pxF, 0f)
        Assert.assertEquals(0f, 0.mm2pxF, 0f)
    }

    @Test
    fun testPx2Dp() {
        val density = Resources.getSystem().displayMetrics.density

        Assert.assertEquals(7f / density, 7f.px2dp, 0f)
        Assert.assertEquals(7.toFloat() / density, 7.px2dp, 0f)
        Assert.assertEquals(0f, 0f.px2dp, 0f)
        Assert.assertEquals(0f, 0.px2dp, 0f)
    }

    @Test
    fun testPx2Sp() {
        val scaledDensity = Resources.getSystem().displayMetrics.scaledDensity

        Assert.assertEquals(7f / scaledDensity, 7f.px2sp, 0f)
        Assert.assertEquals(7.toFloat() / scaledDensity, 7.px2sp, 0f)
        Assert.assertEquals(0f, 0f.px2sp, 0f)
        Assert.assertEquals(0f, 0.px2sp, 0f)
    }

    @Test
    fun testPx2Pt() {
        val xdpi = Resources.getSystem().displayMetrics.xdpi

        Assert.assertEquals(7f / xdpi / (1.0f / 72), 7f.px2pt, 0f)
        Assert.assertEquals(7.toFloat() / xdpi / (1.0f / 72), 7.px2pt, 0f)
        Assert.assertEquals(0f, 0f.px2pt, 0f)
        Assert.assertEquals(0f, 0.px2pt, 0f)
    }

    @Test
    fun testPx2In() {
        val xdpi = Resources.getSystem().displayMetrics.xdpi

        Assert.assertEquals(7f / xdpi, 7f.px2in, 0f)
        Assert.assertEquals(7.toFloat() / xdpi, 7.px2in, 0f)
        Assert.assertEquals(0f, 0f.px2in, 0f)
        Assert.assertEquals(0f, 0.px2in, 0f)
    }

    @Test
    fun testPx2Mm() {
        val xdpi = Resources.getSystem().displayMetrics.xdpi

        Assert.assertEquals(7f / xdpi / (1.0f / 25.4f), 7f.px2mm, 0f)
        Assert.assertEquals(7.toFloat() / xdpi / (1.0f / 25.4f), 7.px2mm, 0f)
        Assert.assertEquals(0f, 0f.px2mm, 0f)
        Assert.assertEquals(0f, 0.px2mm, 0f)
    }
}
