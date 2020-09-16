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

import android.graphics.Point;
import android.graphics.Rect;
import android.widget.ImageView;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.github.panpf.tools4a.graphics.Resizex;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ResizexTest {

    @Test
    public void testSrcMappingStartRect() {
        Assert.assertEquals(new Rect(0, 0, 100, 100), Resizex.srcMappingStartRect(300, 100, 100, 100));
        Assert.assertEquals(new Rect(0, 0, 100, 100), Resizex.srcMappingStartRect(100, 300, 100, 100));
    }

    @Test
    public void testSrcMappingCenterRect() {
        Assert.assertEquals(new Rect(100, 0, 200, 100), Resizex.srcMappingCenterRect(300, 100, 100, 100));
        Assert.assertEquals(new Rect(0, 100, 100, 200), Resizex.srcMappingCenterRect(100, 300, 100, 100));
    }

    @Test
    public void testSrcMappingEndRect() {
        Assert.assertEquals(new Rect(200, 0, 300, 100), Resizex.srcMappingEndRect(300, 100, 100, 100));
        Assert.assertEquals(new Rect(0, 200, 100, 300), Resizex.srcMappingEndRect(100, 300, 100, 100));
    }

    @Test
    public void testSrcMatrixRect() {
        Assert.assertEquals(new Rect(0, 0, 200, 100), Resizex.srcMatrixRect(300, 500, 200, 100));
        Assert.assertEquals(new Rect(0, 0, 100, 100), Resizex.srcMatrixRect(300, 100, 100, 100));
        Assert.assertEquals(new Rect(0, 0, 100, 100), Resizex.srcMatrixRect(100, 300, 100, 100));
    }

    @Test
    public void testScaleTargetSize() {
        Assert.assertEquals(new Point(200, 100), Resizex.scaleTargetSize(300, 500, 200, 100));
        Assert.assertEquals(new Point(100, 100), Resizex.scaleTargetSize(300, 100, 100, 100));
        Assert.assertEquals(new Point(100, 100), Resizex.scaleTargetSize(100, 300, 100, 100));
    }

    @Test
    public void testCalculator() {
        Assert.assertEquals(new Resizex.Result(200, 200, new Rect(0, 100, 300, 400), new Rect(0, 0, 200, 200)),
                Resizex.calculator(300, 500, 200, 200, ImageView.ScaleType.CENTER_CROP, false));

        Assert.assertEquals(new Resizex.Result(200, 200, new Rect(0, 100, 300, 400), new Rect(0, 0, 200, 200)),
                Resizex.calculator(300, 500, 200, 200, ImageView.ScaleType.CENTER, false));

        Assert.assertEquals(new Resizex.Result(200, 200, new Rect(0, 100, 300, 400), new Rect(0, 0, 200, 200)),
                Resizex.calculator(300, 500, 200, 200, ImageView.ScaleType.CENTER_INSIDE, false));

        Assert.assertEquals(new Resizex.Result(200, 200, new Rect(0, 0, 300, 300), new Rect(0, 0, 200, 200)),
                Resizex.calculator(300, 500, 200, 200, ImageView.ScaleType.FIT_START, false));

        Assert.assertEquals(new Resizex.Result(200, 200, new Rect(0, 100, 300, 400), new Rect(0, 0, 200, 200)),
                Resizex.calculator(300, 500, 200, 200, ImageView.ScaleType.FIT_CENTER, false));

        Assert.assertEquals(new Resizex.Result(200, 200, new Rect(0, 200, 300, 500), new Rect(0, 0, 200, 200)),
                Resizex.calculator(300, 500, 200, 200, ImageView.ScaleType.FIT_END, false));

        Assert.assertEquals(new Resizex.Result(200, 200, new Rect(0, 0, 300, 500), new Rect(0, 0, 200, 200)),
                Resizex.calculator(300, 500, 200, 200, ImageView.ScaleType.FIT_XY, false));

        Assert.assertEquals(new Resizex.Result(200, 200, new Rect(0, 0, 200, 200), new Rect(0, 0, 200, 200)),
                Resizex.calculator(300, 500, 200, 200, ImageView.ScaleType.MATRIX, false));

        Assert.assertEquals(new Resizex.Result(100, 100, new Rect(100, 0, 200, 100), new Rect(0, 0, 100, 100)),
                Resizex.calculator(300, 100, 200, 200, ImageView.ScaleType.CENTER_CROP, false));

        Assert.assertEquals(new Resizex.Result(300, 300, new Rect(0, 100, 100, 200), new Rect(0, 0, 300, 300)),
                Resizex.calculator(100, 300, 200, 200, ImageView.ScaleType.CENTER_CROP, false));

        Assert.assertEquals(new Resizex.Result(200, 200, new Rect(0, 100, 100, 200), new Rect(0, 0, 200, 200)),
                Resizex.calculator(100, 300, 200, 200, ImageView.ScaleType.CENTER_CROP, true));
    }
}
