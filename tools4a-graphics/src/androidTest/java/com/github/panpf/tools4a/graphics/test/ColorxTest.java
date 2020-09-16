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

import android.graphics.Color;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.github.panpf.tools4a.graphics.Colorx;
import com.github.panpf.tools4j.collections.Collectionx;
import com.github.panpf.tools4j.math.Mathx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Set;

@RunWith(AndroidJUnit4.class)
public class ColorxTest {

    private static final int COLOR = Color.parseColor("#89BB8713");

    @Test
    public void testColors() {
        List<Integer> colorList = Collectionx.mutableListOf(Colorx.WHITE, Colorx.WHITE_TRANSLUCENT, Colorx.BLACK, Colorx.BLACK_TRANSLUCENT,
                Colorx.TRANSPARENT, Colorx.RED, Colorx.RED_TRANSLUCENT, Colorx.RED_DARK, Colorx.RED_DARK_TRANSLUCENT, Colorx.GREEN,
                Colorx.GREEN_TRANSLUCENT, Colorx.GREEN_DARK, Colorx.GREEN_DARK_TRANSLUCENT, Colorx.GREEN_LIGHT, Colorx.GREEN_LIGHT_TRANSLUCENT,
                Colorx.BLUE, Colorx.BLUE_TRANSLUCENT, Colorx.BLUE_DARK, Colorx.BLUE_DARK_TRANSLUCENT, Colorx.BLUE_LIGHT, Colorx.BLUE_LIGHT_TRANSLUCENT,
                Colorx.SKY_BLUE, Colorx.SKY_BLUE_TRANSLUCENT, Colorx.SKY_BLUE_DARK, Colorx.SKY_BLUE_DARK_TRANSLUCENT, Colorx.SKY_BLUE_LIGHT,
                Colorx.SKY_BLUE_LIGHT_TRANSLUCENT, Colorx.GRAY, Colorx.GRAY_TRANSLUCENT, Colorx.GRAY_DARK, Colorx.GRAY_DARK_TRANSLUCENT, Colorx.GRAY_DIM,
                Colorx.GRAY_DIM_TRANSLUCENT, Colorx.GRAY_LIGHT, Colorx.GRAY_LIGHT_TRANSLUCENT, Colorx.ORANGE, Colorx.ORANGE_TRANSLUCENT, Colorx.ORANGE_DARK,
                Colorx.ORANGE_DARK_TRANSLUCENT, Colorx.ORANGE_LIGHT, Colorx.ORANGE_LIGHT_TRANSLUCENT, Colorx.GOLD, Colorx.GOLD_TRANSLUCENT, Colorx.PINK,
                Colorx.PINK_TRANSLUCENT, Colorx.FUCHSIA, Colorx.FUCHSIA_TRANSLUCENT, Colorx.GRAY_WHITE, Colorx.GRAY_WHITE_TRANSLUCENT, Colorx.PURPLE,
                Colorx.PURPLE_TRANSLUCENT, Colorx.CYAN, Colorx.CYAN_TRANSLUCENT, Colorx.CYAN_DARK, Colorx.CYAN_DARK_TRANSLUCENT, Colorx.YELLOW,
                Colorx.YELLOW_TRANSLUCENT, Colorx.YELLOW_LIGHT, Colorx.YELLOW_LIGHT_TRANSLUCENT, Colorx.CHOCOLATE, Colorx.CHOCOLATE_TRANSLUCENT, Colorx.TOMATO,
                Colorx.TOMATO_TRANSLUCENT, Colorx.ORANGE_RED, Colorx.ORANGE_RED_TRANSLUCENT, Colorx.SILVER, Colorx.SILVER_TRANSLUCENT,
                Colorx.HIGH_LIGHT, Colorx.LOW_LIGHT);
        Set<Integer> colorSet = Collectionx.toSet(colorList);
        Assert.assertEquals(colorList.size(), colorSet.size());
    }

    @Test
    public void testAlpha() {
        Assert.assertEquals(Colorx.getAlpha(COLOR), 137);
        Assert.assertEquals(Colorx.getAlpha(Colorx.setAlpha(COLOR, 211)), 211);
        Assert.assertEquals(Colorx.getAlpha(Colorx.addAlpha(COLOR, 0.56f)), 76);
    }

    @Test
    public void testHue() {
        Assert.assertEquals((int) Colorx.getHSVHue(COLOR), 41);
        Assert.assertEquals((int) Colorx.getHSVHue(Colorx.setHSVHue(COLOR, 111f)), 111);
    }

    @Test
    public void testSaturation() {
        // 不同设备存在色差，因此无法准确对比
        Assert.assertEquals(Mathx.scale(Colorx.getHSVSaturation(COLOR), 2), 0.9f, 0.1f);
        Assert.assertEquals(Mathx.scale(Colorx.getHSVSaturation(Colorx.setHSVSaturation(COLOR, 0.34f)), 2), 0.34f, 0.1f);
        Assert.assertEquals(Mathx.scale(Colorx.getHSVSaturation(Colorx.addHSVSaturation(COLOR, 0.34f)), 2), 0.31f, 0.1f);
    }

    @Test
    public void testHSVValue() {
        // 不同设备存在色差，因此无法准确对比
        Assert.assertEquals(Mathx.scale(Colorx.getHSVValue(COLOR), 2), 0.73f, 0.1f);
        Assert.assertEquals(Mathx.scale(Colorx.getHSVValue(Colorx.setHSVValue(COLOR, 0.21f)), 2), 0.21f, 0.1f);
        Assert.assertEquals(Mathx.scale(Colorx.getHSVValue(Colorx.addHSVValue(COLOR, 0.21f)), 2), 0.15f, 0.1f);
    }

    @Test
    public void testLight() {
        Assert.assertTrue(Colorx.isLight(Color.parseColor("#FFFFFF")));
        Assert.assertTrue(Colorx.isLight(Color.parseColor("#C0C0C0")));
        Assert.assertTrue(Colorx.isLight(Color.parseColor("#808080")));

        Assert.assertFalse(Colorx.isLight(Color.parseColor("#000000")));
    }

    @Test
    public void testArgbEvaluate() {
        // 不同设备存在色差，因此无法准确对比
        Assert.assertNotNull(String.valueOf(Colorx.argbEvaluate(Color.RED, Color.GREEN, 0.6f)));
    }

    @Test
    public void testCreateMatrixColorFilter() {
        Colorx.createMatrixColorFilter(Color.parseColor("#C0C0C0"));
    }
}
