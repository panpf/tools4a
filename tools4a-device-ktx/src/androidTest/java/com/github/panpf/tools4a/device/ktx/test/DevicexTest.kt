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

package com.github.panpf.tools4a.device.ktx.test

import android.Manifest
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.device.ktx.*
import com.github.panpf.tools4a.permission.ktx.isGrantPermissions
import com.github.panpf.tools4j.lang.ktx.isSafe
import com.github.panpf.tools4j.test.Assertx.assertThrow
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern

@RunWith(AndroidJUnit4::class)
class DevicexTest {

    @Test
    fun testGetPhoneNumber() {
        val context = InstrumentationRegistry.getInstrumentation().context
        var cause: Exception? = null
        val phoneNumber = try {
            val manager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            manager.line1Number
        } catch (e: Exception) {
            cause = e
            e.printStackTrace()
            null
        }
        if (phoneNumber != null && phoneNumber.isNotEmpty() && !"unknown".equals(phoneNumber, ignoreCase = true)) {
            assertEquals(phoneNumber, context.getPhoneNumberOr("defaultValue"))
            assertEquals(phoneNumber, context.getPhoneNumberOrThrow())
            assertEquals(phoneNumber, context.getPhoneNumberOrNull())
        } else {
            assertEquals("defaultValue", context.getPhoneNumberOr("defaultValue"))
            if (cause != null && cause is SecurityException) {
                assertThrow(SecurityException::class.java) { context.getPhoneNumberOrThrow() }
            } else {
                assertThrow(IllegalStateException::class.java) { context.getPhoneNumberOrThrow() }
            }
            assertNull(context.getPhoneNumberOrNull())
        }
    }

    @Test
    fun testGetDeviceId() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            assertEquals("defaultValue", context.getDeviceIdOr("defaultValue"))
            assertThrow(SecurityException::class.java) { context.getDeviceIdOrThrow() }
            assertNull(context.getDeviceIdOrNull())
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                assertTrue(context.getDeviceIdOr("defaultValue") != "defaultValue")
                assertTrue(context.getDeviceIdOrThrow().isSafe())
                assertTrue(context.getDeviceIdOrNull().isSafe())
            } else {
                assertEquals("defaultValue", context.getDeviceIdOr("defaultValue"))
                assertThrow(SecurityException::class.java) { context.getDeviceIdOrThrow() }
                assertNull(context.getDeviceIdOrNull())
            }
        } else {
            assertTrue(context.getDeviceIdOr("defaultValue") != "defaultValue")
            assertTrue(context.getDeviceIdOrThrow().isSafe())
            assertTrue(context.getDeviceIdOrNull().isSafe())
        }
    }

    @Test
    fun testGetAndroidId() {
        val context = InstrumentationRegistry.getInstrumentation().context
        assertTrue(context.getAndroidIdOr("defaultValue").isSafe())
        assertTrue(context.getAndroidIdOrThrow().isSafe())
        assertTrue(context.getAndroidIdOrNull().isSafe())
    }

    @Test
    fun testGetSubscriberId() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            assertEquals("defaultValue", context.getSubscriberIdOr("defaultValue"))
            assertThrow(SecurityException::class.java) { context.getSubscriberIdOrThrow() }
            assertNull(context.getSubscriberIdOrNull())
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                assertTrue(context.getSubscriberIdOr("defaultValue") != "defaultValue")
                assertTrue(context.getSubscriberIdOrThrow().isSafe())
                assertTrue(context.getSubscriberIdOrThrow().isSafe())
            } else {
                assertEquals("defaultValue", context.getSubscriberIdOr("defaultValue"))
                assertThrow(SecurityException::class.java) { context.getSubscriberIdOrThrow() }
                assertNull(context.getSubscriberIdOrNull())
            }
        } else {
            assertTrue(context.getSubscriberIdOr("defaultValue") != "defaultValue")
            assertTrue(context.getSubscriberIdOrThrow().isSafe())
            assertTrue(context.getSubscriberIdOrThrow().isSafe())
        }
    }

    @Test
    fun testGetSimSerialNumber() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            assertEquals("defaultValue", context.getSimSerialNumberOr("defaultValue"))
            assertThrow(SecurityException::class.java) { context.getSimSerialNumberOrThrow() }
            assertNull(context.getSimSerialNumberOrNull())
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                assertTrue(context.getSimSerialNumberOr("defaultValue") != "defaultValue")
                assertTrue(context.getSimSerialNumberOrThrow().isSafe())
                assertTrue(context.getSimSerialNumberOrNull().isSafe())
            } else {
                assertEquals("defaultValue", context.getSimSerialNumberOr("defaultValue"))
                assertThrow(SecurityException::class.java) { context.getSimSerialNumberOrThrow() }
                assertNull(context.getSimSerialNumberOrNull())
            }
        } else {
            assertTrue(context.getSimSerialNumberOr("defaultValue") != "defaultValue")
            assertTrue(context.getSimSerialNumberOrThrow().isSafe())
            assertTrue(context.getSimSerialNumberOrNull().isSafe())
        }
    }

    @Test
    fun testGetIMEI() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            assertEquals("defaultValue", context.getIMEIOr("defaultValue"))
            assertThrow(SecurityException::class.java) { context.getIMEIOrThrow() }
            assertNull(context.getIMEIOrNull())
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                assertTrue(context.getIMEIOr("defaultValue") != "defaultValue")
                assertTrue(context.getIMEIOrThrow().isSafe())
                assertTrue(context.getIMEIOrNull().isSafe())
            } else {
                assertEquals("defaultValue", context.getIMEIOr("defaultValue"))
                assertThrow(SecurityException::class.java) { context.getIMEIOrThrow() }
                assertNull(context.getIMEIOrNull())
            }
        } else {
            assertTrue(context.getIMEIOr("defaultValue") != "defaultValue")
            assertTrue(context.getIMEIOrThrow().isSafe())
            assertTrue(context.getIMEIOrNull().isSafe())
        }
    }

    @Test
    fun testGetIMSI() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            assertEquals("defaultValue", context.getIMSIOr("defaultValue"))
            assertThrow(SecurityException::class.java) { context.getIMSIOrThrow() }
            assertNull(context.getIMSIOrNull())
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                assertTrue(context.getIMSIOr("defaultValue") != "defaultValue")
                assertTrue(context.getIMSIOrThrow().isSafe())
                assertTrue(context.getIMSIOrThrow().isSafe())
            } else {
                assertEquals("defaultValue", context.getIMSIOr("defaultValue"))
                assertThrow(SecurityException::class.java) { context.getIMSIOrThrow() }
                assertNull(context.getIMSIOrNull())
            }
        } else {
            assertTrue(context.getIMSIOr("defaultValue") != "defaultValue")
            assertTrue(context.getIMSIOrThrow().isSafe())
            assertTrue(context.getIMSIOrThrow().isSafe())
        }
    }

    @Test
    fun testGetMacAddress() {
        val macAddressPattern = Pattern.compile("([A-Fa-f0-9]{2}(-[A-Fa-f0-9]{2}){5})|([A-Fa-f0-9]{2}(:[A-Fa-f0-9]{2}){5})")
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.ACCESS_WIFI_STATE)) {
                val maxAddress = context.getMacAddressOr("defaultValue")
                assertTrue("maxAddress: $maxAddress", maxAddress == "defaultValue" || macAddressPattern.matcher(maxAddress).matches())
            } else {
                val maxAddress = context.getMacAddressOr("defaultValue")
                val maxAddressNoDefault = context.getMacAddress()
                assertEquals("maxAddressNoDefault: $maxAddressNoDefault", "02:00:00:00:00:00", maxAddressNoDefault)
                assertEquals("maxAddress: $maxAddress", "defaultValue", maxAddress)
            }
        } else {
            val maxAddress = context.getMacAddressOr("defaultValue")
            assertTrue("maxAddress: $maxAddress", macAddressPattern.matcher(maxAddress).matches())
        }
    }
}