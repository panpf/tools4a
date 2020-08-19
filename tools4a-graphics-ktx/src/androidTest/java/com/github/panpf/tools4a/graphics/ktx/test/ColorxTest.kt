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

import android.graphics.Color
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.graphics.ktx.*
import com.github.panpf.tools4j.math.ktx.scale
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ColorxTest {

    companion object {
        private val COLOR = Color.parseColor("#89BB8713")
    }

    @Test
    fun testAlpha() {
        Assert.assertEquals(COLOR.getColorAlpha(), 137)
        Assert.assertEquals(COLOR.setColorAlpha(211).getColorAlpha(), 211)
        Assert.assertEquals(COLOR.addColorAlpha(0.56f).getColorAlpha(), 76)
    }

    @Test
    fun testHue() {
        Assert.assertEquals((COLOR.getColorHSVHue().toInt()).toLong(), 41)
        Assert.assertEquals((COLOR.setColorHSVHue(111f).getColorHSVHue().toInt()).toLong(), 111)
    }

    @Test
    fun testSaturation() {
        // 不同设备存在色差，因此无法准确对比
        Assert.assertEquals(COLOR.getColorHSVSaturation().scale(2), 0.9f, 0.1f)
        Assert.assertEquals(COLOR.setColorHSVSaturation(0.34f).getColorHSVSaturation().scale(2), 0.34f, 0.1f)
        Assert.assertEquals(COLOR.addColorHSVSaturation(0.34f).getColorHSVSaturation().scale(2), 0.31f, 0.1f)
    }

    @Test
    fun testHSVValue() {
        // 不同设备存在色差，因此无法准确对比
        Assert.assertEquals(COLOR.getColorHSVValue().scale(2), 0.73f, 0.1f)
        Assert.assertEquals(COLOR.setColorHSVValue(0.21f).getColorHSVValue().scale(2), 0.21f, 0.1f)
        Assert.assertEquals(COLOR.addColorHSVValue(0.21f).getColorHSVValue().scale(2), 0.15f, 0.1f)
    }

    @Test
    fun testLight() {
        Assert.assertTrue(Color.parseColor("#FFFFFF").isLightColor())
        Assert.assertTrue(Color.parseColor("#C0C0C0").isLightColor())
        Assert.assertTrue(Color.parseColor("#808080").isLightColor())

        Assert.assertFalse(Color.parseColor("#000000").isLightColor())
    }

    @Test
    fun testArgbEvaluate() {
        // 不同设备存在色差，因此无法准确对比
        Assert.assertNotNull(Color.RED.colorArgbEvaluate(Color.GREEN, 0.6f).toString())
    }

    @Test
    fun testCreateMatrixColorFilter() {
        Color.parseColor("#C0C0C0").createMatrixColorFilter()
    }
}
