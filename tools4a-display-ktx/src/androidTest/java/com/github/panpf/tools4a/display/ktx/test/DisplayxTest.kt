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

@file:Suppress("DEPRECATION")

package com.github.panpf.tools4a.display.ktx.test

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue
import android.view.Surface
import android.view.View
import android.view.WindowManager
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.display.ktx.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DisplayxTest {

    private val activityRule = ActivityTestRule(TestActivity::class.java)

    @Rule
    fun getActivityRule(): ActivityTestRule<*> {
        return this.activityRule
    }

    @Test
    fun testGetScreenSize() {
        val context = InstrumentationRegistry.getContext()
        val point = context.getScreenSize()

        Assert.assertTrue(point.x > 0)
        Assert.assertTrue(point.y > 0)
        if (context.isOrientationPortrait()) {
            Assert.assertTrue(point.y > point.x)
        } else {
            Assert.assertTrue(point.x > point.y)
        }

        Assert.assertEquals(point.x.toLong(), context.getScreenWidth().toLong())
        Assert.assertEquals(point.y.toLong(), context.getScreenHeight().toLong())
    }

    @Test
    fun testGetActionBarSize() {
        val context = InstrumentationRegistry.getContext()
        val tv = TypedValue()
        val actonBarSize: Int = if (context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        } else {
            0
        }

        Assert.assertEquals(actonBarSize.toLong(), context.getActionBarSize().toLong())
    }

    @Test
    fun testGetMetrics() {
        val context = InstrumentationRegistry.getContext()
        Assert.assertNotNull(context.getDisplayMetrics())
    }

    @Test
    fun testGetDensity() {
        val context = InstrumentationRegistry.getContext()
        Assert.assertEquals(context.resources.displayMetrics.density, context.getDisplayDensity(), 0f)
        Assert.assertEquals(context.resources.displayMetrics.densityDpi.toFloat(), context.getDisplayDensityDpi().toFloat(), 0f)
    }

    @Test
    fun testGetRotation() {
        val activity = activityRule.activity
        val windowManager = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        when (windowManager.defaultDisplay.rotation) {
            Surface.ROTATION_0 -> Assert.assertEquals(0, activity.getDisplayRotation().toLong())
            Surface.ROTATION_90 -> Assert.assertEquals(90, activity.getDisplayRotation().toLong())
            Surface.ROTATION_180 -> Assert.assertEquals(180, activity.getDisplayRotation().toLong())
            Surface.ROTATION_270 -> Assert.assertEquals(270, activity.getDisplayRotation().toLong())
        }

        when (windowManager.defaultDisplay.rotation) {
            Surface.ROTATION_0 -> Assert.assertEquals(0, activity.view.getDisplayRotation().toLong())
            Surface.ROTATION_90 -> Assert.assertEquals(90, activity.view.getDisplayRotation().toLong())
            Surface.ROTATION_180 -> Assert.assertEquals(180, activity.view.getDisplayRotation().toLong())
            Surface.ROTATION_270 -> Assert.assertEquals(270, activity.view.getDisplayRotation().toLong())
        }
    }

    @Test
    fun testGetOrientation() {
        val activity = activityRule.activity

        when (activity.resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                Assert.assertTrue(activity.isOrientationPortrait())
                Assert.assertTrue(activity.view.isOrientationPortrait())
                Assert.assertFalse(activity.isOrientationLandscape())
                Assert.assertFalse(activity.view.isOrientationLandscape())
                Assert.assertFalse(activity.isOrientationUndefined())
                Assert.assertFalse(activity.view.isOrientationUndefined())
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                Assert.assertFalse(activity.isOrientationPortrait())
                Assert.assertFalse(activity.view.isOrientationPortrait())
                Assert.assertTrue(activity.isOrientationLandscape())
                Assert.assertTrue(activity.view.isOrientationLandscape())
                Assert.assertFalse(activity.isOrientationUndefined())
                Assert.assertFalse(activity.view.isOrientationUndefined())
            }
            else -> {
                Assert.assertFalse(activity.isOrientationPortrait())
                Assert.assertFalse(activity.view.isOrientationPortrait())
                Assert.assertFalse(activity.isOrientationLandscape())
                Assert.assertFalse(activity.view.isOrientationLandscape())
                Assert.assertTrue(activity.isOrientationUndefined())
                Assert.assertTrue(activity.view.isOrientationUndefined())
            }
        }
    }

    @Test
    fun testStatusBar() {
        val context = InstrumentationRegistry.getContext()
        Assert.assertTrue(context.getStatusBarHeight() > 0)
    }

    @Test
    fun testNavigationBar() {
        val context = InstrumentationRegistry.getContext()
        if (context.hasNavigationBar()) {
            if (context.isOrientationPortrait()) {
                Assert.assertTrue(context.getNavigationBarHeight() > 0)
            } else {
                Assert.assertTrue(context.getNavigationBarWidth() > 0)
            }
        } else {
            Assert.assertEquals(0, context.getNavigationBarWidth().toLong())
            Assert.assertEquals(0, context.getNavigationBarHeight().toLong())
        }
    }

    class TestActivity : Activity() {

        val view: View
            get() = findViewById(android.R.id.content)
    }
}
