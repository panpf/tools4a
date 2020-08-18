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

package com.github.panpf.tools4a.service.ktx.test

import android.accessibilityservice.AccessibilityService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.service.ktx.isAccessibilityServiceEnabled
import com.github.panpf.tools4a.service.ktx.isServiceRunning
import com.github.panpf.tools4a.service.ktx.startService
import com.github.panpf.tools4a.service.ktx.stopService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ServicexTest {

    @Test
    @Throws(InterruptedException::class)
    fun testIsRunning() {
        val context = InstrumentationRegistry.getContext()

        try {
            context.startService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertTrue(context.isServiceRunning(TestService::class.java))
            Assert.assertFalse(context.isServiceRunning(NoRegisterTestService::class.java))
        } finally {
            context.stopService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testStart() {
        val context = InstrumentationRegistry.getContext()

        try {
            Assert.assertFalse(context.isServiceRunning(TestService::class.java))

            context.startService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertTrue(context.isServiceRunning(TestService::class.java))
            Assert.assertTrue(context.isServiceRunning(TestService::class.java, context.packageName))
            Assert.assertFalse(context.isServiceRunning(NoRegisterTestService::class.java))
            Assert.assertFalse(context.isServiceRunning(TestService::class.java, context.packageName + "_error"))

            Assert.assertTrue(context.isServiceRunning(TestService::class.java))
            Assert.assertTrue(context.isServiceRunning(TestService::class.java, context.packageName))
        } finally {
            context.stopService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        }

        try {
            Assert.assertFalse(context.isServiceRunning(TestService::class.java))

            context.startService(TestService::class.java, BundleBuilder().putString("SHARE_KEY", "testStartExtras").build())
            Thread.sleep(100)

            Assert.assertTrue(context.isServiceRunning(TestService::class.java))
            Assert.assertEquals("testStartExtras", TestService.SHARE_KEY)
        } finally {
            context.stopService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testStop() {
        val context = InstrumentationRegistry.getContext()

        try {
            context.startService(TestService::class.java)
            Thread.sleep(100)
            Assert.assertTrue(context.isServiceRunning(TestService::class.java))

            context.stopService(TestService::class.java)
            Thread.sleep(100)
            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        } finally {
            context.stopService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        }
    }

    @Test
    fun testIsAccessibilityServiceEnabled() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertFalse(context.isAccessibilityServiceEnabled(AccessibilityService::class.java))
        Assert.assertFalse(context.isAccessibilityServiceEnabled(AccessibilityService::class.java, context.packageName))
    }

    class NoRegisterTestService : Service() {

        override fun onBind(intent: Intent): IBinder? {
            return null
        }
    }
}
