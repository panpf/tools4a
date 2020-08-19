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

package com.github.panpf.tools4a.view.ktx.test

import android.app.Activity
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.run.ktx.runInUI
import com.github.panpf.tools4a.view.ktx.getBrightness
import com.github.panpf.tools4a.view.ktx.isBrightnessFlowSystem
import com.github.panpf.tools4a.view.ktx.setBrightness
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WindowxTest {

    private val activityRule = ActivityTestRule(TestActivity::class.java)

    @Rule
    fun getActivityRule(): ActivityTestRule<*> {
        return this.activityRule
    }

    @Test
    fun testBrightness() {
        val activity = activityRule.activity

        val windowBrightness = activity.window.getBrightness()

        if (windowBrightness < 0) {
            Assert.assertTrue(activity.window.isBrightnessFlowSystem())
        } else {
            Assert.assertFalse(activity.window.isBrightnessFlowSystem())
        }

        try {
            val newWindowBrightnessValue = windowBrightness * -1
            runInUI { activity.window.setBrightness(newWindowBrightnessValue) }
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val newWindowBrightnessValueFromSettings = activity.window.getBrightness()

            if (newWindowBrightnessValueFromSettings < 0) {
                Assert.assertTrue(activity.window.isBrightnessFlowSystem())
            } else {
                Assert.assertFalse(activity.window.isBrightnessFlowSystem())
            }

            Assert.assertEquals(newWindowBrightnessValue, newWindowBrightnessValueFromSettings, 0f)
        } finally {
            runInUI { activity.window.setBrightness(windowBrightness) }
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
    }

    class TestActivity : Activity()
}
