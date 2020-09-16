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
package com.github.panpf.tools4a.activity.test

import android.R
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelStoreOwner
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.activity.Activityx
import com.github.panpf.tools4a.run.ktx.runOnUiThreadAndWait
import com.github.panpf.tools4a.test.ktx.*
import com.github.panpf.tools4j.test.ktx.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityxTest {

    @Test
    fun testIsDestroyedCompat() {
        launchActivityWithUse(TestFragmentActivity::class.java) { scenario ->
            val activity = scenario.getActivitySync()
            runOnUiThreadAndWait { Assert.assertFalse(Activityx.isDestroyedCompat(activity)) }
            scenario.moveToState(Lifecycle.State.DESTROYED)
            runOnUiThreadAndWait { Assert.assertTrue(Activityx.isDestroyedCompat(activity)) }
        }
    }

    @Test
    fun testConvertTranslucent() {
        launchAndOnActivityWithUse(TestFragmentActivity::class.java) { activity ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Assert.assertTrue(Activityx.convertToTranslucentCompat(activity))
                Assert.assertTrue(Activityx.convertFromTranslucentCompat(activity))
            } else {
                Assert.assertFalse(Activityx.convertToTranslucentCompat(activity))
                Assert.assertFalse(Activityx.convertFromTranslucentCompat(activity))
            }
        }
    }

    @Test
    fun testGetImplWithParent() {
        launchAndOnActivityWithUse(TestFragmentActivity::class.java) { activity ->
            Assert.assertNull(Activityx.getImplFromParent(activity, ImplTestInterface::class.java))
            Assert.assertEquals(TestFragmentActivity::class.java, Activityx.getImplFromParent(activity, ViewModelStoreOwner::class.java)!!.javaClass)
        }
    }

    @Test
    fun testCanStart() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertFalse(Activityx.canStart(context, Intent(context, ActivityxTest::class.java)))
        Assert.assertTrue(Activityx.canStart(context, Intent(context, TestFragmentActivity::class.java)))
    }

    @Test
    fun testStart() {
        launchAndOnActivityWithUse(TestFragmentActivity::class.java) { activity ->
            assertNoThrow {
                Activityx.start(activity, Intent(activity, TestFragmentActivity::class.java))
            }
            assertThrow(ActivityNotFoundException::class) {
                Activityx.start(activity.applicationContext, Intent(activity, ActivityxTest::class.java))
            }

            assertNoThrow {
                Activityx.start(activity.fragment, Intent(activity, TestFragmentActivity::class.java))
            }
            assertThrow(ActivityNotFoundException::class) {
                Activityx.start(activity.fragment, Intent(activity, ActivityxTest::class.java))
            }

            assertNoThrow {
                Activityx.start(activity.view, Intent(activity, TestFragmentActivity::class.java))
            }
            assertThrow(ActivityNotFoundException::class) {
                Activityx.start(activity.view, Intent(activity, ActivityxTest::class.java))
            }
        }
    }

    @Test
    fun testStartByClass() {
        launchAndOnActivityWithUse(TestFragmentActivity::class.java) { activity ->
            assertNoThrow {
                Activityx.startByClass(activity, TestFragmentActivity::class.java, null)
            }
            assertNoThrow {
                Activityx.startByClass(activity, TestFragmentActivity::class.java)
            }
            assertThrow(ActivityNotFoundException::class) {
                Activityx.startByClass(activity.applicationContext, NoRegisterTestActivity::class.java)
            }

            assertNoThrow {
                Activityx.startByClass(activity.fragment, TestFragmentActivity::class.java, null)
            }
            assertNoThrow {
                Activityx.startByClass(activity.fragment, TestFragmentActivity::class.java)
            }

            assertThrow(ActivityNotFoundException::class) {
                Activityx.startByClass(activity.fragment, NoRegisterTestActivity::class.java)
            }
            assertNoThrow {
                Activityx.startByClass(activity.view, TestFragmentActivity::class.java, null)
            }
            assertNoThrow {
                Activityx.startByClass(activity.view, TestFragmentActivity::class.java)
            }
            assertThrow(ActivityNotFoundException::class) {
                Activityx.startByClass(activity.view, NoRegisterTestActivity::class.java)
            }
        }
    }

    @Test
    fun testSafeStart() {
        launchAndOnActivityWithUse(TestFragmentActivity::class.java) { activity ->
            Assert.assertTrue(Activityx.safeStart(activity, Intent(activity, TestFragmentActivity::class.java)))
            Assert.assertFalse(Activityx.safeStart(activity.applicationContext, Intent(activity, ActivityxTest::class.java)))

            Assert.assertTrue(Activityx.safeStart(activity.fragment, Intent(activity, TestFragmentActivity::class.java)))
            Assert.assertFalse(Activityx.safeStart(activity.fragment, Intent(activity, ActivityxTest::class.java)))

            Assert.assertTrue(Activityx.safeStart(activity.fragment, Intent(activity, TestFragmentActivity::class.java)))
            Assert.assertFalse(Activityx.safeStart(activity.fragment, Intent(activity, ActivityxTest::class.java)))

            Assert.assertTrue(Activityx.safeStart(activity.view, Intent(activity, TestFragmentActivity::class.java)))
            Assert.assertFalse(Activityx.safeStart(activity.view, Intent(activity, ActivityxTest::class.java)))
        }
    }

    @Test
    fun testSafeStartByClass() {
        launchAndOnActivityWithUse(TestFragmentActivity::class.java) { activity ->
            Assert.assertTrue(Activityx.safeStartByClass(activity, TestFragmentActivity::class.java, null))
            Assert.assertTrue(Activityx.safeStartByClass(activity, TestFragmentActivity::class.java))
            Assert.assertFalse(Activityx.safeStartByClass(activity.applicationContext, NoRegisterTestActivity::class.java))

            Assert.assertTrue(Activityx.safeStartByClass(activity.fragment, TestFragmentActivity::class.java, null))
            Assert.assertTrue(Activityx.safeStartByClass(activity.fragment, TestFragmentActivity::class.java))
            Assert.assertFalse(Activityx.safeStartByClass(activity.fragment, NoRegisterTestActivity::class.java))

            Assert.assertTrue(Activityx.safeStartByClass(activity.fragment, TestFragmentActivity::class.java, null))
            Assert.assertTrue(Activityx.safeStartByClass(activity.fragment, TestFragmentActivity::class.java))
            Assert.assertFalse(Activityx.safeStartByClass(activity.fragment, NoRegisterTestActivity::class.java))

            Assert.assertTrue(Activityx.safeStartByClass(activity.view, TestFragmentActivity::class.java, null))
            Assert.assertTrue(Activityx.safeStartByClass(activity.view, TestFragmentActivity::class.java))
            Assert.assertFalse(Activityx.safeStartByClass(activity.view, NoRegisterTestActivity::class.java))
        }
    }

    interface ImplTestInterface

    class TestFragmentActivity : FragmentActivity() {

        val fragment: Fragment
            get() = supportFragmentManager.findFragmentById(R.id.content)!!

        val view: View
            get() = findViewById(R.id.content)!!

        public override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            supportFragmentManager.beginTransaction().replace(R.id.content, Fragment()).commit()
        }
    }

    class NoRegisterTestActivity : Activity(), ImplTestInterface
}