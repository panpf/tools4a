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

package com.github.panpf.tools4a.display.ktx.test

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.Surface
import android.view.View
import android.view.WindowManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.display.ktx.*
import com.github.panpf.tools4a.test.ktx.launchActivityWithOnUse
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DisplayxTest {

    @Test
    fun testGetScreenSize() {
        val context = InstrumentationRegistry.getInstrumentation().context
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
        val context = InstrumentationRegistry.getInstrumentation().context
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
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertNotNull(context.getDisplayMetrics())
    }

    @Test
    fun testGetDensity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals(context.resources.displayMetrics.density, context.getDisplayDensity(), 0f)
        Assert.assertEquals(context.resources.displayMetrics.densityDpi.toFloat(), context.getDisplayDensityDpi().toFloat(), 0f)
    }

    @Test
    fun testGetRotation() {
        launchActivityWithOnUse(TestActivity::class) { activity ->
            val rotation = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                activity.display!!.rotation
            } else {
                @Suppress("DEPRECATION")
                (activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.rotation
            }
            when (rotation) {
                Surface.ROTATION_0 -> Assert.assertEquals(0, activity.getDisplayRotation().toLong())
                Surface.ROTATION_90 -> Assert.assertEquals(90, activity.getDisplayRotation().toLong())
                Surface.ROTATION_180 -> Assert.assertEquals(180, activity.getDisplayRotation().toLong())
                Surface.ROTATION_270 -> Assert.assertEquals(270, activity.getDisplayRotation().toLong())
            }
            when (rotation) {
                Surface.ROTATION_0 -> Assert.assertEquals(0, activity.supportFragment.getDisplayRotation().toLong())
                Surface.ROTATION_90 -> Assert.assertEquals(90, activity.supportFragment.getDisplayRotation().toLong())
                Surface.ROTATION_180 -> Assert.assertEquals(180, activity.supportFragment.getDisplayRotation().toLong())
                Surface.ROTATION_270 -> Assert.assertEquals(270, activity.supportFragment.getDisplayRotation().toLong())
            }
            when (rotation) {
                Surface.ROTATION_0 -> Assert.assertEquals(0, activity.view.getDisplayRotation().toLong())
                Surface.ROTATION_90 -> Assert.assertEquals(90, activity.view.getDisplayRotation().toLong())
                Surface.ROTATION_180 -> Assert.assertEquals(180, activity.view.getDisplayRotation().toLong())
                Surface.ROTATION_270 -> Assert.assertEquals(270, activity.view.getDisplayRotation().toLong())
            }
        }
    }

    @Test
    fun testGetOrientation() {
        launchActivityWithOnUse(TestActivity::class) { activity ->
            when (activity.resources.configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    Assert.assertEquals(Configuration.ORIENTATION_PORTRAIT.toLong(), activity.getDisplayOrientation().toLong())
                    Assert.assertEquals(Configuration.ORIENTATION_PORTRAIT.toLong(), activity.supportFragment.getDisplayOrientation().toLong())
                    Assert.assertEquals(Configuration.ORIENTATION_PORTRAIT.toLong(), activity.view.getDisplayOrientation().toLong())
                    Assert.assertTrue(activity.isOrientationPortrait())
                    Assert.assertTrue(activity.supportFragment.isOrientationPortrait())
                    Assert.assertTrue(activity.view.isOrientationPortrait())
                    Assert.assertFalse(activity.isOrientationLandscape())
                    Assert.assertFalse(activity.supportFragment.isOrientationLandscape())
                    Assert.assertFalse(activity.view.isOrientationLandscape())
                    Assert.assertFalse(activity.isOrientationUndefined())
                    Assert.assertFalse(activity.supportFragment.isOrientationUndefined())
                    Assert.assertFalse(activity.view.isOrientationUndefined())
                }
                Configuration.ORIENTATION_LANDSCAPE -> {
                    Assert.assertEquals(Configuration.ORIENTATION_LANDSCAPE.toLong(), activity.getDisplayOrientation().toLong())
                    Assert.assertEquals(Configuration.ORIENTATION_LANDSCAPE.toLong(), activity.supportFragment.getDisplayOrientation().toLong())
                    Assert.assertEquals(Configuration.ORIENTATION_LANDSCAPE.toLong(), activity.view.getDisplayOrientation().toLong())
                    Assert.assertFalse(activity.isOrientationPortrait())
                    Assert.assertFalse(activity.supportFragment.isOrientationPortrait())
                    Assert.assertFalse(activity.view.isOrientationPortrait())
                    Assert.assertTrue(activity.isOrientationLandscape())
                    Assert.assertTrue(activity.supportFragment.isOrientationLandscape())
                    Assert.assertTrue(activity.view.isOrientationLandscape())
                    Assert.assertFalse(activity.isOrientationUndefined())
                    Assert.assertFalse(activity.supportFragment.isOrientationUndefined())
                    Assert.assertFalse(activity.view.isOrientationUndefined())
                }
                else -> {
                    Assert.assertEquals(Configuration.ORIENTATION_UNDEFINED.toLong(), activity.getDisplayOrientation().toLong())
                    Assert.assertEquals(Configuration.ORIENTATION_UNDEFINED.toLong(), activity.supportFragment.getDisplayOrientation().toLong())
                    Assert.assertEquals(Configuration.ORIENTATION_UNDEFINED.toLong(), activity.view.getDisplayOrientation().toLong())
                    Assert.assertFalse(activity.isOrientationPortrait())
                    Assert.assertFalse(activity.supportFragment.isOrientationPortrait())
                    Assert.assertFalse(activity.view.isOrientationPortrait())
                    Assert.assertFalse(activity.isOrientationLandscape())
                    Assert.assertFalse(activity.supportFragment.isOrientationLandscape())
                    Assert.assertFalse(activity.view.isOrientationLandscape())
                    Assert.assertTrue(activity.isOrientationUndefined())
                    Assert.assertTrue(activity.supportFragment.isOrientationUndefined())
                    Assert.assertTrue(activity.view.isOrientationUndefined())
                }
            }
        }

    }

    @Test
    fun testStatusBar() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertTrue(context.getStatusBarHeight() > 0)
    }

    @Test
    fun testNavigationBar() {
        val context = InstrumentationRegistry.getInstrumentation().context
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

    class TestActivity : androidx.fragment.app.FragmentActivity() {

        val supportFragment: androidx.fragment.app.Fragment
            get() = supportFragmentManager.findFragmentById(R.id.multiFrameAt_frame2)!!

        val view: View
            get() = findViewById(android.R.id.content)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_multi_frame)

            supportFragmentManager.beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, androidx.fragment.app.Fragment())
                    .commit()
        }
    }
}
