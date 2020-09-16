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

package com.github.panpf.tools4a.graphics.ktx.test

import android.content.Context
import android.graphics.Paint
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.graphics.ktx.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PaintxTest {

    @Test
    fun testGetTextWidth() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()
        val text = "0123456789"

        val paint = Paint()
        paint.textSize = dp2px(context, 14).toFloat()

        val textWidth = paint.getTextWidth(text)
        Assert.assertTrue(textWidth > 0)
    }

    @Test
    fun testGetTextHeight() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()

        val paint = Paint()
        paint.textSize = dp2px(context, 14).toFloat()

        val textHeight = paint.getTextHeight()
        Assert.assertTrue(textHeight > 0)

        val textHeightCompact = paint.getTextHeightCompact()
        Assert.assertTrue(textHeightCompact > 0)
        Assert.assertTrue(textHeightCompact < textHeight)
    }

    @Test
    fun testGetTextBounds() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()
        val text = "0123456789"

        val paint = Paint()
        paint.textSize = dp2px(context, 14).toFloat()

        val bounds = paint.getTextBounds(text)
        Assert.assertTrue(bounds.width() > 0)
        Assert.assertTrue(bounds.height() > 0)
    }

    @Test
    fun testGetDrawTextVerticalCenterBaseLine() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()

        val paint = Paint()
        paint.textSize = dp2px(context, 14).toFloat()

        val baseLine = paint.getDrawTextVerticalCenterBaseLine(0f, 100f)
        Assert.assertTrue(baseLine > 0)
        Assert.assertTrue(baseLine < 100)

        val baseLineCompact = paint.getDrawTextVerticalCenterBaseLineCompact(0f, 100f)
        Assert.assertTrue(baseLineCompact > 0)
        Assert.assertTrue(baseLineCompact < 100)

        Assert.assertTrue(baseLineCompact < baseLine)
    }


    fun dp2px(context: Context, dpValue: Float): Int {
        return if (dpValue != 0f) ((dpValue * context.getResources().getDisplayMetrics().density + 0.5f).toInt()) else 0
    }

    fun dp2px(context: Context, dpValue: Int): Int {
        return if (dpValue != 0) ((dpValue.toFloat() * context.getResources().getDisplayMetrics().density + 0.5f).toInt()) else 0
    }
}
