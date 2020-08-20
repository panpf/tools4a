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
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.display.ktx.getStatusBarHeight
import com.github.panpf.tools4a.run.ResultRunnable
import com.github.panpf.tools4a.run.ktx.runOnUIThread
import com.github.panpf.tools4a.run.ktx.runOnUIThreadAndWait
import com.github.panpf.tools4a.run.ktx.runOnUIThreadAndWaitResult
import com.github.panpf.tools4a.view.ktx.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewxTest {

    private val activityRule = ActivityTestRule(TestActivity::class.java)

    @Rule
    fun getActivityRule(): ActivityTestRule<*> {
        return this.activityRule
    }

    @Test
    @Throws(InterruptedException::class)
    fun testSetLayoutSize() {
        val activity = activityRule.activity
        val view = activity.view

        try {
            Assert.assertEquals(ViewGroup.LayoutParams.MATCH_PARENT.toLong(), view.layoutParams.width.toLong())
            runOnUIThread { view.setLayoutWidth(100) }
            Thread.sleep(100)
            Assert.assertEquals(100, view.layoutParams.width.toLong())
        } finally {
            runOnUIThread { view.setLayoutSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) }
            Thread.sleep(100)
        }

        val newView1 = runOnUIThreadAndWaitResult { View(activity) }
        Assert.assertNull(newView1.layoutParams)
        runOnUIThread { newView1.setLayoutWidth(100) }
        Thread.sleep(100)
        Assert.assertNull(newView1.layoutParams)

        try {
            Assert.assertEquals(ViewGroup.LayoutParams.MATCH_PARENT.toLong(), view.layoutParams.height.toLong())
            runOnUIThread { view.setLayoutHeight(300) }
            Thread.sleep(100)
            Assert.assertEquals(300, view.layoutParams.height.toLong())
        } finally {
            runOnUIThread { view.setLayoutSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) }
            Thread.sleep(100)
        }

        val newView2 = runOnUIThreadAndWaitResult { View(activity) }
        Assert.assertNull(newView2.layoutParams)
        runOnUIThread { newView2.setLayoutHeight(300) }
        Thread.sleep(100)
        Assert.assertNull(newView2.layoutParams)

        try {
            Assert.assertEquals(ViewGroup.LayoutParams.MATCH_PARENT.toLong(), view.layoutParams.width.toLong())
            runOnUIThread { view.setLayoutWidthOrInitSize(200, 600) }
            Thread.sleep(100)
            Assert.assertEquals(200, view.layoutParams.width.toLong())
        } finally {
            runOnUIThread { view.setLayoutSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) }
            Thread.sleep(100)
        }

        val newView3 = runOnUIThreadAndWaitResult { View(activity) }
        Assert.assertNull(newView3.layoutParams)
        runOnUIThread { newView3.setLayoutWidthOrInitSize(200, 600) }
        Thread.sleep(100)
        Assert.assertNotNull(newView3.layoutParams)
        Assert.assertEquals(200, newView3.layoutParams.width.toLong())
        Assert.assertEquals(600, newView3.layoutParams.height.toLong())

        try {
            Assert.assertEquals(ViewGroup.LayoutParams.MATCH_PARENT.toLong(), view.layoutParams.height.toLong())
            runOnUIThread { view.setLayoutHeightOrInitSize(200, 600) }
            Thread.sleep(100)
            Assert.assertEquals(600, view.layoutParams.height.toLong())
        } finally {
            runOnUIThread { view.setLayoutSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) }
            Thread.sleep(100)
        }

        val newView4 = runOnUIThreadAndWaitResult { View(activity) }
        Assert.assertNull(newView4.layoutParams)
        runOnUIThread { newView4.setLayoutHeightOrInitSize(200, 600) }
        Thread.sleep(100)
        Assert.assertNotNull(newView4.layoutParams)
        Assert.assertEquals(200, newView4.layoutParams.width.toLong())
        Assert.assertEquals(600, newView4.layoutParams.height.toLong())

        Assert.assertEquals(0, (view.layoutParams as ViewGroup.MarginLayoutParams).topMargin.toLong())
        runOnUIThread { view.setLayoutMarginTop(200) }
        Thread.sleep(100)
        Assert.assertEquals(200, (view.layoutParams as ViewGroup.MarginLayoutParams).topMargin.toLong())
    }

    @Test
    @Throws(InterruptedException::class)
    fun testAddLayoutSize() {
        val activity = activityRule.activity
        val view = activity.view

        runOnUIThread { view.setLayoutSize(100, 300) }
        Thread.sleep(100)
        Assert.assertEquals(100, view.layoutParams.width.toLong())
        Assert.assertEquals(300, view.layoutParams.height.toLong())

        runOnUIThread {
            view.addLayoutWidth(100)
            view.addLayoutHeight(100)
        }
        Thread.sleep(100)
        Assert.assertEquals(200, view.layoutParams.width.toLong())
        Assert.assertEquals(400, view.layoutParams.height.toLong())

        runOnUIThread { view.addLayoutSize(100, 100) }
        Thread.sleep(100)
        Assert.assertEquals(300, view.layoutParams.width.toLong())
        Assert.assertEquals(500, view.layoutParams.height.toLong())

        Assert.assertEquals(0, (view.layoutParams as ViewGroup.MarginLayoutParams).topMargin.toLong())
        runOnUIThread { view.setLayoutMarginTop(200) }
        Thread.sleep(100)
        Assert.assertEquals(200, (view.layoutParams as ViewGroup.MarginLayoutParams).topMargin.toLong())
        runOnUIThread { view.addLayoutMarginTop(200) }
        Thread.sleep(100)
        Assert.assertEquals(400, (view.layoutParams as ViewGroup.MarginLayoutParams).topMargin.toLong())
    }

    @Test
    fun testToBitmap() {
        val activity = activityRule.activity
        val view = activity.view

        val bitmap = view.toBitmap(Bitmap.Config.ARGB_8888)
        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
        try {
            Assert.assertFalse(bitmap.isRecycled)
        } finally {
            bitmap.recycle()
        }

        val bitmap2 = view.toBitmap(Bitmap.Config.ARGB_8888, 0.5f)
        Assert.assertEquals((bitmapWidth / 2).toLong(), bitmap2.width.toLong())
        Assert.assertEquals((bitmapHeight / 2).toLong(), bitmap2.height.toLong())
        try {
            Assert.assertFalse(bitmap2.isRecycled)
        } finally {
            bitmap2.recycle()
        }

        val bitmap3 = view.toBitmapByMaxWidth(Bitmap.Config.ARGB_8888, 200)
        Assert.assertEquals(200, bitmap3.width.toLong())
        Assert.assertEquals((200.toFloat() / bitmapWidth * bitmapHeight).toInt().toLong(), bitmap3.height.toLong())
        try {
            Assert.assertFalse(bitmap3.isRecycled)
        } finally {
            bitmap3.recycle()
        }

        val bitmap4 = view.toBitmapByMaxHeight(Bitmap.Config.ARGB_8888, 200)
        Assert.assertEquals((200.toFloat() / bitmapHeight * bitmapWidth).toInt().toLong(), bitmap4.width.toLong())
        Assert.assertEquals(200, bitmap4.height.toLong())
        try {
            Assert.assertFalse(bitmap4.isRecycled)
        } finally {
            bitmap4.recycle()
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testInflateLayout() {
        val activity = activityRule.activity

        val contentView = activity.contentView
        val contentViewChildCount = contentView.childCount

        val childView1 = runOnUIThreadAndWaitResult { activity.inflateLayout(R.layout.at_test, contentView) }
        Thread.sleep(100)
        Assert.assertNotNull(childView1)
        Assert.assertNull(childView1.parent)
        Assert.assertNotNull(childView1.layoutParams)
        Assert.assertEquals(contentViewChildCount.toLong(), contentView.childCount.toLong())

        val childView2 = runOnUIThreadAndWaitResult(ResultRunnable<View> { activity.inflateLayout(R.layout.at_test, contentView, true).findViewById(R.id.testAt_frame) })
        Thread.sleep(100)
        Assert.assertNotNull(childView2)
        Assert.assertNotNull(childView2.parent)
        Assert.assertNotNull(childView2.layoutParams)
        Assert.assertEquals(childView2.parent, contentView)
        Assert.assertEquals((contentViewChildCount + 1).toLong(), contentView.childCount.toLong())

        val childView3 = runOnUIThreadAndWaitResult { activity.inflateLayout(R.layout.at_test) }
        Thread.sleep(100)
        Assert.assertNotNull(childView3)
        Assert.assertNull(childView3.parent)
        Assert.assertNull(childView3.layoutParams)
        Assert.assertEquals((contentViewChildCount + 1).toLong(), contentView.childCount.toLong())
    }

    @Test
    fun testAddPaddingTopByStatusBarHeight() {
        val activity = activityRule.activity
        val contentView = activity.contentView
        val oldTopPadding = contentView.paddingTop
        runOnUIThreadAndWait { contentView.addPaddingTopByStatusBarHeight() }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertEquals((oldTopPadding + activity.getStatusBarHeight()).toLong(), contentView.paddingTop.toLong())
        } else {
            Assert.assertEquals(oldTopPadding.toLong(), contentView.paddingTop.toLong())
        }
    }

    class TestActivity : Activity() {

        val view: View
            get() = (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)

        val contentView: ViewGroup
            get() = findViewById<View>(android.R.id.content) as ViewGroup

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val viewGroup = findViewById<ViewGroup>(android.R.id.content)
            val imageView = ImageView(this)
            imageView.setImageResource(R.drawable.rect)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            viewGroup.addView(imageView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        }
    }
}
