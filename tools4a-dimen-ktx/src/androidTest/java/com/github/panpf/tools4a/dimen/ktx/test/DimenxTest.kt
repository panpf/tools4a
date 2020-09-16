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

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.panpf.tools4a.dimen.ktx.*
import com.github.panpf.tools4a.test.ktx.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DimenxTest {

    @Test
    fun testDp2px() {
        launchAndOnActivityWithUse(TestActivity::class.java) { activity: TestActivity ->
            Assert.assertEquals((10f * activity.resources.displayMetrics.density + 0.5f).toInt().toLong(), activity.dp2px(10f).toLong())
            Assert.assertEquals((10f * activity.resources.displayMetrics.density + 0.5f).toInt().toLong(), activity.dp2px(10).toLong())
            Assert.assertEquals(0, activity.dp2px(0f).toLong())
            Assert.assertEquals(0, activity.dp2px(0).toLong())

            val fragment = activity.fragment
            Assert.assertEquals((10f * activity.resources.displayMetrics.density + 0.5f).toInt().toLong(), fragment.dp2px(10f).toLong())
            Assert.assertEquals((10f * activity.resources.displayMetrics.density + 0.5f).toInt().toLong(), fragment.dp2px(10).toLong())
            Assert.assertEquals(0, fragment.dp2px(0f).toLong())
            Assert.assertEquals(0, fragment.dp2px(0).toLong())

            val view = activity.view
            Assert.assertEquals((10f * activity.resources.displayMetrics.density + 0.5f).toInt().toLong(), view.dp2px(10f).toLong())
            Assert.assertEquals((10f * activity.resources.displayMetrics.density + 0.5f).toInt().toLong(), view.dp2px(10).toLong())
            Assert.assertEquals(0, view.dp2px(0f).toLong())
            Assert.assertEquals(0, view.dp2px(0).toLong())
        }
    }

    @Test
    fun testPx2dp() {
        launchAndOnActivityWithUse(TestActivity::class.java) { activity: TestActivity ->
            Assert.assertEquals(100.toFloat() / activity.resources.displayMetrics.density + 0.5f, activity.px2dp(100), 0f)
            Assert.assertEquals(0f, activity.px2dp(0), 0f)

            val fragment = activity.fragment
            Assert.assertEquals(100.toFloat() / activity.resources.displayMetrics.density + 0.5f, fragment.px2dp(100), 0f)
            Assert.assertEquals(0f, fragment.px2dp(0), 0f)

            val view = activity.view
            Assert.assertEquals(100.toFloat() / activity.resources.displayMetrics.density + 0.5f, view.px2dp(100), 0f)
            Assert.assertEquals(0f, view.px2dp(0), 0f)
        }
    }

    @Test
    fun testSp2px() {
        launchAndOnActivityWithUse(TestActivity::class.java) { activity: TestActivity ->
            Assert.assertEquals((10f * activity.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), activity.sp2px(10f).toLong())
            Assert.assertEquals((10.toFloat() * activity.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), activity.sp2px(10).toLong())
            Assert.assertEquals(0, activity.sp2px(0f).toLong())
            Assert.assertEquals(0, activity.sp2px(0).toLong())

            val fragment = activity.fragment
            Assert.assertEquals((10f * activity.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), fragment.sp2px(10f).toLong())
            Assert.assertEquals((10.toFloat() * activity.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), fragment.sp2px(10).toLong())
            Assert.assertEquals(0, fragment.sp2px(0f).toLong())
            Assert.assertEquals(0, fragment.sp2px(0).toLong())

            val view = activity.view
            Assert.assertEquals((10f * activity.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), view.sp2px(10f).toLong())
            Assert.assertEquals((10.toFloat() * activity.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), view.sp2px(10).toLong())
            Assert.assertEquals(0, view.sp2px(0f).toLong())
            Assert.assertEquals(0, view.sp2px(0).toLong())
        }
    }

    @Test
    fun testPx2sp() {
        launchAndOnActivityWithUse(TestActivity::class.java) { activity: TestActivity ->
            Assert.assertEquals(100.toFloat() / activity.resources.displayMetrics.scaledDensity + 0.5f, activity.px2sp(100), 0f)
            Assert.assertEquals(0f, activity.px2sp(0), 0f)

            val fragment = activity.fragment
            Assert.assertEquals(100.toFloat() / activity.resources.displayMetrics.scaledDensity + 0.5f, fragment.px2sp(100), 0f)
            Assert.assertEquals(0f, fragment.px2sp(0), 0f)

            val view = activity.view
            Assert.assertEquals(100.toFloat() / activity.resources.displayMetrics.scaledDensity + 0.5f, view.px2sp(100), 0f)
            Assert.assertEquals(0f, view.px2sp(0), 0f)
        }
    }

    @Test
    fun testApplyDimension() {
        launchAndOnActivityWithUse(TestActivity::class.java) { activity: TestActivity ->
            Assert.assertEquals(10f * activity.resources.displayMetrics.scaledDensity, activity.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10f), 0f)
            Assert.assertEquals(10.toFloat() * activity.resources.displayMetrics.scaledDensity, activity.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10), 0f)

            val fragment = activity.fragment
            Assert.assertEquals(10f * activity.resources.displayMetrics.scaledDensity, fragment.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10f), 0f)
            Assert.assertEquals(10.toFloat() * activity.resources.displayMetrics.scaledDensity, fragment.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10), 0f)

            val view = activity.view
            Assert.assertEquals(10f * activity.resources.displayMetrics.scaledDensity, view.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10f), 0f)
            Assert.assertEquals(10.toFloat() * activity.resources.displayMetrics.scaledDensity, view.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10), 0f)
        }
    }

    class TestActivity : androidx.fragment.app.FragmentActivity() {

        val fragment: androidx.fragment.app.Fragment
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
