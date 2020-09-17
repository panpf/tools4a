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

package com.github.panpf.tools4a.run.ktx.test

import android.os.Looper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.run.ResultNullableRunnable
import com.github.panpf.tools4a.run.ResultRunnable
import com.github.panpf.tools4a.run.ktx.*
import com.github.panpf.tools4a.run.SyncRunnable
import com.github.panpf.tools4j.test.Assertx
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RunxTest {

    @Test
    fun testGetMainHandler() {
        Assert.assertNotNull(getMainHandler())
        Assert.assertSame(Looper.getMainLooper(), getMainHandler().looper)
        Assert.assertSame(getMainHandler(), getMainHandler())
    }

    @Test
    fun testIsOnMainThread() {
        Assert.assertFalse(isOnMainThread())
        val syncRunnable = SyncRunnable { Assert.assertTrue(isOnMainThread()) }
        getMainHandler().post(syncRunnable)
        syncRunnable.waitForComplete()
    }

    @Test
    fun testIsOnNotMainThread() {
        Assert.assertTrue(isOnNotMainThread())
        val syncRunnable = SyncRunnable { Assert.assertFalse(isOnNotMainThread()) }
        getMainHandler().post(syncRunnable)
        syncRunnable.waitForComplete()
    }

    @Test
    fun testCheckMainThread() {
        Assertx.assertThrow(IllegalStateException::class.java) { checkMainThread() }
        val syncRunnable = SyncRunnable { Assertx.assertNoThrow { checkMainThread() } }
        getMainHandler().post(syncRunnable)
        syncRunnable.waitForComplete()
    }

    @Test
    fun testCheckNotMainThread() {
        Assertx.assertNoThrow { checkNotMainThread() }
        val syncRunnable = SyncRunnable { Assertx.assertThrow(IllegalStateException::class.java) { checkNotMainThread() } }
        getMainHandler().post(syncRunnable)
        syncRunnable.waitForComplete()
    }

    @Test
    fun testRunOnMainThread() {
        val syncRunnable = SyncRunnable { checkMainThread() }
        runOnMainThread(syncRunnable)
        syncRunnable.waitForComplete()
        val syncRunnable1 = SyncRunnable { runOnMainThread { checkMainThread() } }
        getMainHandler().post(syncRunnable1)
        syncRunnable1.waitForComplete()
    }

    @Test
    fun testRunOnMainThreadSync() {
        runOnMainThreadSync { checkMainThread() }
        val syncRunnable = SyncRunnable { runOnMainThreadSync { checkMainThread() } }
        getMainHandler().post(syncRunnable)
        syncRunnable.waitForComplete()
    }

    @Test
    fun testRunOnMainThreadSyncResult() {
        val resultRunnable = ResultRunnable {
            checkMainThread()
            "testRunOnMainThreadSyncResult"
        }
        Assert.assertEquals("testRunOnMainThreadSyncResult", runOnMainThreadSyncResult(resultRunnable))
        val syncRunnable1 = SyncRunnable { Assert.assertEquals("testRunOnMainThreadSyncResult", runOnMainThreadSyncResult(resultRunnable)) }
        getMainHandler().post(syncRunnable1)
        syncRunnable1.waitForComplete()

//        val resultRunnable1 = ResultRunnable<String> {
//            checkMainThread()
//            null
//        }
//        Assertx.assertThrow(IllegalArgumentException::class.java) { runOnMainThreadSyncResult(resultRunnable1) }
    }

    @Test
    fun testRunOnMainThreadSyncResultNullable() {
        val resultNullableRunnable = ResultNullableRunnable {
            checkMainThread()
            "testRunOnMainThreadSyncResultNullable"
        }
        Assert.assertEquals("testRunOnMainThreadSyncResultNullable", runOnMainThreadSyncResultNullable(resultNullableRunnable))
        val syncRunnable1 = SyncRunnable { Assert.assertEquals("testRunOnMainThreadSyncResultNullable", runOnMainThreadSyncResultNullable(resultNullableRunnable)) }
        getMainHandler().post(syncRunnable1)
        syncRunnable1.waitForComplete()

        val resultNullableRunnable1 = ResultNullableRunnable<String> {
            checkMainThread()
            null
        }
        Assert.assertNull(runOnMainThreadSyncResultNullable(resultNullableRunnable1))
    }

    @Test
    fun testIsOnMainProcess() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertTrue(context.isOnMainProcess())
    }

    @Test
    fun testIsOnNotMainProcess() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertFalse(context.isOnNotMainProcess())
    }

    @Test
    fun testGetOnProcessNameOrNull() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals(context.packageName, context.getOnProcessNameOrNull())
    }

    @Test
    fun testGetOnProcessNameSuffixOrNull() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertNull(context.getOnProcessNameSuffixOrNull())
    }
}
