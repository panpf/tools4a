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

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.run.ktx.runOnUIThread
import com.github.panpf.tools4a.view.ViewAnimx
import com.github.panpf.tools4a.view.ktx.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewAnimxTest {

    private val activityRule = ActivityTestRule(TestActivity::class.java)

    @Rule
    fun getActivityRule(): ActivityTestRule<*> {
        return this.activityRule
    }

    @Test
    @Throws(InterruptedException::class)
    fun testAnimAlpha() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runOnUIThread { view.animAlpha(1.0f, 0.0f, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener) }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.animAlpha(1.0f, 0.0f, invisibleListener)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.animAlpha(1.0f, 0.0f, true)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.animAlpha(1.0f, 0.0f, 500)
        }
        Thread.sleep(500)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.animAlpha(1.0f, 0.0f)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testAnimTranslate() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runOnUIThread { view.animTranslate(0f, 300f, 0f, 300f, 0f, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener) }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.animTranslate(0f, 300f, 0f, 300f, invisibleListener)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.animTranslate(0f, 300f, 0f, 300f, true)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.animTranslate(0f, 300f, 0f, 300f, 500)
        }
        Thread.sleep(500)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.animTranslate(0f, 300f, 0f, 300f, 3f)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.animTranslate(0f, 300f, 0f, 300f)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testShakeLandscape() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runOnUIThread { view.shakeLandscape(10f, 7f, 700, true, invisibleListener) }
        Thread.sleep(700)

        runOnUIThread { view.shakeLandscape(invisibleListener) }
        Thread.sleep(700)

        runOnUIThread { view.shakeLandscape(true) }
        Thread.sleep(700)

        runOnUIThread { view.shakeLandscape(500L) }
        Thread.sleep(500L)

        runOnUIThread { view.shakeLandscape(15f) }
        Thread.sleep(700)

        runOnUIThread { view.shakeLandscape() }
        Thread.sleep(700)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testShakePortrait() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runOnUIThread { view.shakePortrait(10f, 7f, 700, true, invisibleListener) }
        Thread.sleep(700)

        runOnUIThread { view.shakePortrait(invisibleListener) }
        Thread.sleep(700)

        runOnUIThread { view.shakePortrait(true) }
        Thread.sleep(700)

        runOnUIThread { view.shakePortrait(500L) }
        Thread.sleep(500L)

        runOnUIThread { view.shakePortrait(15f) }
        Thread.sleep(700)

        runOnUIThread { view.shakePortrait() }
        Thread.sleep(700)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testStartAnimFromRes() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runOnUIThread { view.startAnimFromRes(R.anim.view_anim_test, true, invisibleListener) }
        Thread.sleep(1000)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.startAnimFromRes(R.anim.view_anim_test, invisibleListener)
        }
        Thread.sleep(1000)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.startAnimFromRes(R.anim.view_anim_test, true)
        }
        Thread.sleep(1000)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.startAnimFromRes(R.anim.view_anim_test)
        }
        Thread.sleep(1000)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testInvisibleByAnimAlpha() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runOnUIThread { view.invisibleByAnimAlpha(ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener) }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.invisibleByAnimAlpha(invisibleListener)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.invisibleByAnimAlpha(true)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.invisibleByAnimAlpha(500)
        }
        Thread.sleep(500)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.invisibleByAnimAlpha()
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testGoneByAnimAlpha() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runOnUIThread { view.goneByAnimAlpha(ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener) }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.goneByAnimAlpha(invisibleListener)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.goneByAnimAlpha(true)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.goneByAnimAlpha(500)
        }
        Thread.sleep(500)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.goneByAnimAlpha()
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testVisibleByAnimAlpha() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runOnUIThread { view.visibleByAnimAlpha(ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener) }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.visibleByAnimAlpha(invisibleListener)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.visibleByAnimAlpha(true)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.visibleByAnimAlpha(500)
        }
        Thread.sleep(500)

        runOnUIThread {
            view.visibility = View.VISIBLE
            view.visibleByAnimAlpha()
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)
    }

    class TestActivity : androidx.fragment.app.FragmentActivity() {

        val view: View
            get() = (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val viewGroup = findViewById<ViewGroup>(android.R.id.content)
            val imageView = ImageView(this)
            imageView.setImageResource(R.drawable.rect)
            viewGroup.addView(imageView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        }
    }
}
