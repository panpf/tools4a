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

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.run.ResultNullableRunnable
import com.github.panpf.tools4a.run.ResultRunnable
import com.github.panpf.tools4a.run.Runx
import com.github.panpf.tools4a.run.ktx.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class RunxTest {

    @Test
    fun testRunInUI() {
        val results = arrayOfNulls<String>(1)
        val countDownLatch = CountDownLatch(1)
        runOnUIThread {
            results[0] = if (Runx.isOnTheMainThread()) "MainThread1" else "NoMainThread1"
            countDownLatch.countDown()
        }
        try {
            countDownLatch.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Assert.assertEquals(results[0], "MainThread1")
        val countDownLatch2 = CountDownLatch(1)
        runOnUIThread(Runnable {
            results[0] = if (Runx.isOnTheMainThread()) "MainThread1-" else "NoMainThread1-"
            countDownLatch2.countDown()
        })
        try {
            countDownLatch2.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Assert.assertEquals(results[0], "MainThread1-")

        runOnUIThreadAndWait(Runnable { results[0] = if (Runx.isOnTheMainThread()) "MainThread2-" else "NoMainThread2-" })
        Assert.assertEquals(results[0], "MainThread2-")

        runOnUIThreadAndWait { results[0] = if (Runx.isOnTheMainThread()) "MainThread2" else "NoMainThread2" }
        Assert.assertEquals(results[0], "MainThread2")

        results[0] = runOnUIThreadAndWaitResult(ResultRunnable { if (Runx.isOnTheMainThread()) "MainThread3-" else "NoMainThread3-" })
        Assert.assertEquals(results[0], "MainThread3-")

        results[0] = runOnUIThreadAndWaitResult { if (Runx.isOnTheMainThread()) "MainThread3" else "NoMainThread3" }
        Assert.assertEquals(results[0], "MainThread3")

        results[0] = runOnUIThreadAndWaitNullableResult(ResultNullableRunnable { if (Runx.isOnTheMainThread()) "MainThread4-" else null })
        Assert.assertEquals(results[0], "MainThread4-")

        results[0] = runOnUIThreadAndWaitNullableResult { if (Runx.isOnTheMainThread()) "MainThread4" else null }
        Assert.assertEquals(results[0], "MainThread4")
    }

    @Test
    fun testIsMainProcess() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertTrue(context.isOnTheMainProcess())
    }

    @Test
    fun testInProcessName() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertNotNull(context.getTheProcessName())
        Assert.assertEquals(context.getTheProcessNameSuffix(), "")
    }
}
