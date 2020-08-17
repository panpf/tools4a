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

package com.github.panpf.tools4a.activity.ktx.test

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelStoreOwner
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.activity.ktx.*
import com.github.panpf.tools4j.lang.Throwablex
import com.github.panpf.tools4j.premise.ktx.requireNotNull
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityxTest {

    @get:Rule
    val fragmentActivityTestRule = ActivityTestRule(TestFragmentActivity::class.java)

    @Test
    fun testFragmentActivityDestroyed() {
        val activity = fragmentActivityTestRule.activity

        Assert.assertFalse(activity.isDestroyedCompat())

        fragmentActivityTestRule.finishActivity()

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertTrue(activity.isDestroyedCompat())
    }

    @Test
    fun testFragmentActivityNormal() {
        val activity = fragmentActivityTestRule.activity

        Assert.assertFalse(activity.isDestroyedCompat())

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertFalse(activity.isDestroyedCompat())
    }

    @Test
    fun testConvertTranslucent() {
        val activity = fragmentActivityTestRule.activity

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        var result = TestUtils.waitRunInUIResult { activity.convertToTranslucentCompat() }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertTrue(result)
        } else {
            Assert.assertFalse(result)
        }

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        result = TestUtils.waitRunInUIResult { activity.convertFromTranslucentCompat() }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertTrue(result)
        } else {
            Assert.assertFalse(result)
        }

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    @Test
    fun testGetImplWithParent() {
        val activity2 = fragmentActivityTestRule.activity
        Assert.assertNull(activity2.getImplFromParent(ImplTestInterface::class.java))
        Assert.assertEquals(activity2.getImplFromParent(ViewModelStoreOwner::class.java).requireNotNull()::class.java, TestFragmentActivity::class.java)
    }

    @Test
    fun testCanStart() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertFalse(context.canStartActivity(Intent(context, ActivityxTest::class.java)))
        Assert.assertTrue(context.canStartActivity(Intent(context, TestFragmentActivity::class.java)))
    }

    @Test
    fun testStartActivityByIntentActivity() {
        val activity = fragmentActivityTestRule.activity

        try {
            activity.startActivity(Intent(activity, TestFragmentActivity::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getApplicationContext().startActivity(Intent(activity, ActivityxTest::class.java))
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            activity.startActivityByClass(TestFragmentActivity::class.java, null)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.startActivityByClass(TestFragmentActivity::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getApplicationContext().startActivityByClass(NoRegisterTestActivity::class.java)
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Test
    fun testStartActivityByIntentSupportFragment() {
        val activity = fragmentActivityTestRule.activity

        try {
            activity.getFragment().startActivity(Intent(activity, TestFragmentActivity::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getFragment().startActivity(Intent(activity, ActivityxTest::class.java))
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            activity.getFragment().startActivityByClass(TestFragmentActivity::class.java, null)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getFragment().startActivityByClass(TestFragmentActivity::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getFragment().startActivityByClass(NoRegisterTestActivity::class.java)
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @Test
    fun testStartActivityByIntentOriginFragment() {
        val activity = fragmentActivityTestRule.activity

        try {
            activity.getFragment().startActivity(Intent(activity, TestFragmentActivity::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getFragment().startActivity(Intent(activity, ActivityxTest::class.java))
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            activity.getFragment().startActivityByClass(TestFragmentActivity::class.java, null)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getFragment().startActivityByClass(TestFragmentActivity::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getFragment().startActivityByClass(NoRegisterTestActivity::class.java)
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @Test
    fun testStartActivityByIntentView() {
        val activity = fragmentActivityTestRule.activity

        try {
            activity.getView().startActivity(Intent(activity, TestFragmentActivity::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getView().startActivity(Intent(activity, ActivityxTest::class.java))
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            activity.getView().startActivityByClass(TestFragmentActivity::class.java, null)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getView().startActivityByClass(TestFragmentActivity::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getView().startActivityByClass(NoRegisterTestActivity::class.java)
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Test
    fun testSafeStartActivityByIntentActivity() {
        val activity = fragmentActivityTestRule.activity

        Assert.assertTrue(activity.safeStartActivity(Intent(activity, TestFragmentActivity::class.java)))
        Assert.assertFalse(activity.getApplicationContext().safeStartActivity(Intent(activity, ActivityxTest::class.java)))
        Assert.assertTrue(activity.safeStartActivityByClass(TestFragmentActivity::class.java, null))
        Assert.assertTrue(activity.safeStartActivityByClass(TestFragmentActivity::class.java))
        Assert.assertFalse(activity.getApplicationContext().safeStartActivityByClass(NoRegisterTestActivity::class.java))
    }

    @Test
    fun testSafeStartActivityByIntentSupportFragment() {
        val activity = fragmentActivityTestRule.activity

        Assert.assertTrue(activity.getFragment().safeStartActivity(Intent(activity, TestFragmentActivity::class.java)))
        Assert.assertFalse(activity.getFragment().safeStartActivity(Intent(activity, ActivityxTest::class.java)))
        Assert.assertTrue(activity.getFragment().safeStartActivityByClass(TestFragmentActivity::class.java, null))
        Assert.assertTrue(activity.getFragment().safeStartActivityByClass(TestFragmentActivity::class.java))
        Assert.assertFalse(activity.getFragment().safeStartActivityByClass(NoRegisterTestActivity::class.java))
    }

    @Test
    fun testSafeStartActivityByIntentView() {
        val activity = fragmentActivityTestRule.activity

        Assert.assertTrue(activity.getView().safeStartActivity(Intent(activity, TestFragmentActivity::class.java)))
        Assert.assertFalse(activity.getView().safeStartActivity(Intent(activity, ActivityxTest::class.java)))
        Assert.assertTrue(activity.getView().safeStartActivityByClass(TestFragmentActivity::class.java, null))
        Assert.assertTrue(activity.getView().safeStartActivityByClass(TestFragmentActivity::class.java))
        Assert.assertFalse(activity.getView().safeStartActivityByClass(NoRegisterTestActivity::class.java))
    }

    interface ImplTestInterface

    class TestFragmentActivity : androidx.fragment.app.FragmentActivity() {
        var finished: Boolean = false
        var finishedActivity: Boolean = false
        var finishedActivityFromChild: Boolean = false
        var destoryed: Boolean = false

        public override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            supportFragmentManager.beginTransaction().replace(android.R.id.content, androidx.fragment.app.Fragment()).commit()
        }

        override fun finish() {
            super.finish()
            finished = true
        }

        override fun finishActivity(requestCode: Int) {
            super.finishActivity(requestCode)
            finishedActivity = true
        }

        override fun finishActivityFromChild(child: Activity, requestCode: Int) {
            super.finishActivityFromChild(child, requestCode)
            finishedActivityFromChild = true
        }

        override fun onDestroy() {
            super.onDestroy()
            destoryed = true
        }

        fun getFragment(): androidx.fragment.app.Fragment {
            return supportFragmentManager.findFragmentById(android.R.id.content).requireNotNull()
        }

        fun getView(): View {
            return findViewById(android.R.id.content)
        }
    }

    class NoRegisterTestActivity : Activity(), ImplTestInterface {
        var finished: Boolean = false
        var finishedActivity: Boolean = false
        var finishedActivityFromChild: Boolean = false
        var destoryed: Boolean = false

        override fun finish() {
            super.finish()
            finished = true
        }

        override fun finishActivity(requestCode: Int) {
            super.finishActivity(requestCode)
            finishedActivity = true
        }

        override fun finishActivityFromChild(child: Activity, requestCode: Int) {
            super.finishActivityFromChild(child, requestCode)
            finishedActivityFromChild = true
        }

        override fun onDestroy() {
            super.onDestroy()
            destoryed = true
        }
    }
}
