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

package com.github.panpf.tools4a.activity.ktx.test

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
import com.github.panpf.tools4a.activity.ktx.*
import com.github.panpf.tools4a.run.ktx.runOnMainThreadSync
import com.github.panpf.tools4a.test.ktx.*
import com.github.panpf.tools4j.test.ktx.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityxTest {

    @Test
    fun testIsDestroyedCompat() {
        launchActivityWithUse(TestActivity::class.java) { scenario ->
            val activity = scenario.getActivitySync()
            runOnMainThreadSync { Assert.assertFalse(activity.isDestroyedCompat()) }
            scenario.moveToState(Lifecycle.State.DESTROYED)
            runOnMainThreadSync { Assert.assertTrue(activity.isDestroyedCompat()) }
        }
    }

    @Test
    fun testConvertTranslucent() {
        launchAndOnActivityWithUse(TestActivity::class.java) { activity ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Assert.assertTrue(activity.convertToTranslucentCompat())
                Assert.assertTrue(activity.convertFromTranslucentCompat())
            } else {
                Assert.assertFalse(activity.convertToTranslucentCompat())
                Assert.assertFalse(activity.convertFromTranslucentCompat())
            }
        }
    }

    @Test
    fun testGetImplWithParent() {
        launchAndOnActivityWithUse(TestActivity::class.java) { activity ->
            Assert.assertNull(activity.getImplFromParent(ImplTestInterface::class.java))
            Assert.assertEquals(TestActivity::class.java, activity.getImplFromParent(ViewModelStoreOwner::class.java)!!.javaClass)
        }
    }

    @Test
    fun testCanStart() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertFalse(context.canStartActivity(Intent(context, ActivityxTest::class.java)))
        Assert.assertTrue(context.canStartActivity(Intent(context, TestActivity::class.java)))
    }

    @Test
    fun testStart() {
        launchAndOnActivityWithUse(TestActivity::class.java) { activity ->
            assertNoThrow {
                activity.startActivity(Intent(activity, TestActivity::class.java))
            }
            assertThrow(ActivityNotFoundException::class) {
                activity.applicationContext.startActivity(Intent(activity, ActivityxTest::class.java))
            }

            assertNoThrow {
                activity.fragment.startActivity(Intent(activity, TestActivity::class.java))
            }
            assertThrow(ActivityNotFoundException::class) {
                activity.fragment.startActivity(Intent(activity, ActivityxTest::class.java))
            }

            assertNoThrow {
                activity.view.startActivity(Intent(activity, TestActivity::class.java))
            }
            assertThrow(ActivityNotFoundException::class) {
                activity.view.startActivity(Intent(activity, ActivityxTest::class.java))
            }
        }
    }

    @Test
    fun testStartByClass() {
        launchAndOnActivityWithUse(TestActivity::class.java) { activity ->
            assertNoThrow {
                activity.startActivityByClass(TestActivity::class.java, null)
            }
            assertNoThrow {
                activity.startActivityByClass(TestActivity::class.java)
            }
            assertThrow(ActivityNotFoundException::class) {
                activity.applicationContext.startActivityByClass(NoRegisterTestActivity::class.java)
            }

            assertNoThrow {
                activity.fragment.startActivityByClass(TestActivity::class.java, null)
            }
            assertNoThrow {
                activity.fragment.startActivityByClass(TestActivity::class.java)
            }
            assertThrow(ActivityNotFoundException::class) {
                activity.fragment.startActivityByClass(NoRegisterTestActivity::class.java)
            }

            assertNoThrow {
                activity.view.startActivityByClass(TestActivity::class.java, null)
            }
            assertNoThrow {
                activity.view.startActivityByClass(TestActivity::class.java)
            }
            assertThrow(ActivityNotFoundException::class) {
                activity.view.startActivityByClass(NoRegisterTestActivity::class.java)
            }
        }
    }

    @Test
    fun testSafeStart() {
        launchAndOnActivityWithUse(TestActivity::class.java) { activity ->
            Assert.assertTrue(activity.safeStartActivity(Intent(activity, TestActivity::class.java)))
            Assert.assertFalse(activity.applicationContext.safeStartActivity(Intent(activity, ActivityxTest::class.java)))

            Assert.assertTrue(activity.fragment.safeStartActivity(Intent(activity, TestActivity::class.java)))
            Assert.assertFalse(activity.fragment.safeStartActivity(Intent(activity, ActivityxTest::class.java)))

            Assert.assertTrue(activity.fragment.safeStartActivity(Intent(activity, TestActivity::class.java)))
            Assert.assertFalse(activity.fragment.safeStartActivity(Intent(activity, ActivityxTest::class.java)))

            Assert.assertTrue(activity.view.safeStartActivity(Intent(activity, TestActivity::class.java)))
            Assert.assertFalse(activity.view.safeStartActivity(Intent(activity, ActivityxTest::class.java)))
        }
    }

    @Test
    fun testSafeStartByClass() {
        launchAndOnActivityWithUse(TestActivity::class.java) { activity ->
            Assert.assertTrue(activity.safeStartActivityByClass(TestActivity::class.java, null))
            Assert.assertTrue(activity.safeStartActivityByClass(TestActivity::class.java))
            Assert.assertFalse(activity.applicationContext.safeStartActivityByClass(NoRegisterTestActivity::class.java))

            Assert.assertTrue(activity.fragment.safeStartActivityByClass(TestActivity::class.java, null))
            Assert.assertTrue(activity.fragment.safeStartActivityByClass(TestActivity::class.java))
            Assert.assertFalse(activity.fragment.safeStartActivityByClass(NoRegisterTestActivity::class.java))

            Assert.assertTrue(activity.fragment.safeStartActivityByClass(TestActivity::class.java, null))
            Assert.assertTrue(activity.fragment.safeStartActivityByClass(TestActivity::class.java))
            Assert.assertFalse(activity.fragment.safeStartActivityByClass(NoRegisterTestActivity::class.java))

            Assert.assertTrue(activity.view.safeStartActivityByClass(TestActivity::class.java, null))
            Assert.assertTrue(activity.view.safeStartActivityByClass(TestActivity::class.java))
            Assert.assertFalse(activity.view.safeStartActivityByClass(NoRegisterTestActivity::class.java))
        }
    }

    interface ImplTestInterface

    class TestActivity : FragmentActivity() {

        val fragment: Fragment
            get() = supportFragmentManager.findFragmentById(android.R.id.content)!!

        val view: View
            get() = findViewById(android.R.id.content)!!

        public override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            supportFragmentManager.beginTransaction().replace(android.R.id.content, Fragment()).commit()
        }
    }

    class NoRegisterTestActivity : Activity(), ImplTestInterface
}
