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

package com.github.panpf.tools4a.graphics.test;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.panpf.tools4a.graphics.Paintx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PaintxTest {

    @Test
    public void testGetTextWidth() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        String text = "0123456789";

        Paint paint = new Paint();
        paint.setTextSize(dp2px(context, 14));

        float textWidth = Paintx.getTextWidth(paint, text);
        Assert.assertTrue(textWidth > 0);
        Assert.assertEquals(textWidth, Paintx.getTextWidth(paint.getTextSize(), text), 0f);
    }

    @Test
    public void testGetTextHeight() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Paint paint = new Paint();
        paint.setTextSize(dp2px(context, 14));

        float textHeight = Paintx.getTextHeight(paint);
        Assert.assertTrue(textHeight > 0);
        Assert.assertEquals(textHeight, Paintx.getTextHeight(paint.getTextSize()), 0f);

        float textHeightCompact = Paintx.getTextHeightCompact(paint);
        Assert.assertTrue(textHeightCompact > 0);
        Assert.assertTrue(textHeightCompact < textHeight);
        Assert.assertEquals(textHeightCompact, Paintx.getTextHeightCompact(paint.getTextSize()), 0f);
    }

    @Test
    public void testGetTextBounds() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        String text = "0123456789";

        Paint paint = new Paint();
        paint.setTextSize(dp2px(context, 14));

        Rect bounds = Paintx.getTextBounds(paint, text);
        Assert.assertTrue(bounds.width() > 0);
        Assert.assertTrue(bounds.height() > 0);
        Assert.assertEquals(bounds, Paintx.getTextBounds(paint.getTextSize(), text));
    }

    @Test
    public void testGetDrawTextVerticalCenterBaseLine() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Paint paint = new Paint();
        paint.setTextSize(dp2px(context, 14));

        float baseLine = Paintx.getDrawTextVerticalCenterBaseLine(paint, 0, 100);
        Assert.assertTrue(baseLine > 0);
        Assert.assertTrue(baseLine < 100);

        float baseLineCompact = Paintx.getDrawTextVerticalCenterBaseLineCompact(paint, 0, 100);
        Assert.assertTrue(baseLineCompact > 0);
        Assert.assertTrue(baseLineCompact < 100);

        Assert.assertTrue(baseLineCompact < baseLine);
    }


    public static int dp2px(@NonNull Context context, float dpValue) {
        return dpValue != 0 ? (int) (dpValue * context.getResources().getDisplayMetrics().density + 0.5f) : 0;
    }

    public static int dp2px(@NonNull Context context, int dpValue) {
        return dpValue != 0 ? (int) ((float) dpValue * context.getResources().getDisplayMetrics().density + 0.5f) : 0;
    }
}
