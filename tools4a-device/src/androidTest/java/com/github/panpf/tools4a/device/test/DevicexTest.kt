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
package com.github.panpf.tools4a.device.test

import android.Manifest
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.device.Devicex
import com.github.panpf.tools4a.permission.ktx.isGrantPermissions
import com.github.panpf.tools4a.systemproperties.SystemPropertiesx
import com.github.panpf.tools4j.lang.ktx.isSafe
import com.github.panpf.tools4j.test.Assertx.assertThrow
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern

@RunWith(AndroidJUnit4::class)
class DevicexTest {
    @Test
    fun testIsEmulator() {
        assertEquals("1" == SystemPropertiesx.get("ro.kernel.qemu"), Devicex.isEmulator())
    }

    @Test
    fun testGetProduct() {
        assertTrue(Devicex.getProduct().isSafe())
    }

    @Test
    fun testGetBrand() {
        assertTrue(Devicex.getBrand().isSafe())
    }

    @Test
    fun testGetModel() {
        assertTrue(Devicex.getModel().isSafe())
    }

    @Test
    fun testGetDevice() {
        assertTrue(Devicex.getDevice().isSafe())
    }

    @Test
    fun testGetHardware() {
        assertTrue(Devicex.getHardware().isSafe())
    }

    @Test
    fun testGetSupportedAbis() {
        assertTrue(Devicex.getSupportedAbis().isNotEmpty())
    }

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
            assertEquals(phoneNumber, Devicex.getPhoneNumberOr(context, "defaultValue"))
            assertEquals(phoneNumber, Devicex.getPhoneNumberOrThrow(context))
            assertEquals(phoneNumber, Devicex.getPhoneNumberOrNull(context))
        } else {
            assertEquals("defaultValue", Devicex.getPhoneNumberOr(context, "defaultValue"))
            if (cause != null && cause is SecurityException) {
                assertThrow(SecurityException::class.java) { Devicex.getPhoneNumberOrThrow(context) }
            } else {
                assertThrow(IllegalStateException::class.java) { Devicex.getPhoneNumberOrThrow(context) }
            }
            assertNull(Devicex.getPhoneNumberOrNull(context))
        }
    }

    @Test
    fun testGetDeviceId() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            assertEquals("defaultValue", Devicex.getDeviceIdOr(context, "defaultValue"))
            assertThrow(SecurityException::class.java) { Devicex.getDeviceIdOrThrow(context) }
            assertNull(Devicex.getDeviceIdOrNull(context))
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                assertTrue(Devicex.getDeviceIdOr(context, "defaultValue") != "defaultValue")
                assertTrue(Devicex.getDeviceIdOrThrow(context).isSafe())
                assertTrue(Devicex.getDeviceIdOrNull(context).isSafe())
            } else {
                assertEquals("defaultValue", Devicex.getDeviceIdOr(context, "defaultValue"))
                assertThrow(SecurityException::class.java) { Devicex.getDeviceIdOrThrow(context) }
                assertNull(Devicex.getDeviceIdOrNull(context))
            }
        } else {
            assertTrue(Devicex.getDeviceIdOr(context, "defaultValue") != "defaultValue")
            assertTrue(Devicex.getDeviceIdOrThrow(context).isSafe())
            assertTrue(Devicex.getDeviceIdOrNull(context).isSafe())
        }
    }

    @Test
    fun testGetAndroidId() {
        val context = InstrumentationRegistry.getInstrumentation().context
        assertTrue(Devicex.getAndroidIdOr(context, "defaultValue").isSafe())
        assertTrue(Devicex.getAndroidIdOrThrow(context).isSafe())
        assertTrue(Devicex.getAndroidIdOrNull(context).isSafe())
    }

    @Test
    fun testGetSubscriberId() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            assertEquals("defaultValue", Devicex.getSubscriberIdOr(context, "defaultValue"))
            assertThrow(SecurityException::class.java) { Devicex.getSubscriberIdOrThrow(context) }
            assertNull(Devicex.getSubscriberIdOrNull(context))
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                assertTrue(Devicex.getSubscriberIdOr(context, "defaultValue") != "defaultValue")
                assertTrue(Devicex.getSubscriberIdOrThrow(context).isSafe())
                assertTrue(Devicex.getSubscriberIdOrThrow(context).isSafe())
            } else {
                assertEquals("defaultValue", Devicex.getSubscriberIdOr(context, "defaultValue"))
                assertThrow(SecurityException::class.java) { Devicex.getSubscriberIdOrThrow(context) }
                assertNull(Devicex.getSubscriberIdOrNull(context))
            }
        } else {
            assertTrue(Devicex.getSubscriberIdOr(context, "defaultValue") != "defaultValue")
            assertTrue(Devicex.getSubscriberIdOrThrow(context).isSafe())
            assertTrue(Devicex.getSubscriberIdOrThrow(context).isSafe())
        }
    }

    @Test
    fun testGetSimSerialNumber() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            assertEquals("defaultValue", Devicex.getSimSerialNumberOr(context, "defaultValue"))
            assertThrow(SecurityException::class.java) { Devicex.getSimSerialNumberOrThrow(context) }
            assertNull(Devicex.getSimSerialNumberOrNull(context))
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                assertTrue(Devicex.getSimSerialNumberOr(context, "defaultValue") != "defaultValue")
                assertTrue(Devicex.getSimSerialNumberOrThrow(context).isSafe())
                assertTrue(Devicex.getSimSerialNumberOrNull(context).isSafe())
            } else {
                assertEquals("defaultValue", Devicex.getSimSerialNumberOr(context, "defaultValue"))
                assertThrow(SecurityException::class.java) { Devicex.getSimSerialNumberOrThrow(context) }
                assertNull(Devicex.getSimSerialNumberOrNull(context))
            }
        } else {
            assertTrue(Devicex.getSimSerialNumberOr(context, "defaultValue") != "defaultValue")
            assertTrue(Devicex.getSimSerialNumberOrThrow(context).isSafe())
            assertTrue(Devicex.getSimSerialNumberOrNull(context).isSafe())
        }
    }

    @Test
    fun testGetSerial() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            assertEquals("defaultValue", Devicex.getSerialOr("defaultValue"))
            assertThrow(SecurityException::class.java) { Devicex.getSerialOrThrow() }
            assertNull(Devicex.getSerialOrNull())
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                assertTrue(Devicex.getSerialOr("defaultValue") != "defaultValue")
                assertTrue(Devicex.getSerialOrThrow().isSafe())
                assertTrue(Devicex.getSerialOrNull().isSafe())
            } else {
                assertEquals("defaultValue", Devicex.getSerialOr("defaultValue"))
                assertThrow(SecurityException::class.java) { Devicex.getSerialOrThrow() }
                assertNull(Devicex.getSerialOrNull())
            }
        } else {
            assertTrue(Devicex.getSerialOr("defaultValue") != "defaultValue")
            assertTrue(Devicex.getSerialOrThrow().isSafe())
            assertTrue(Devicex.getSerialOrNull().isSafe())
        }
    }

    @Test
    fun testGetIMEI() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            assertEquals("defaultValue", Devicex.getIMEIOr(context, "defaultValue"))
            assertThrow(SecurityException::class.java) { Devicex.getIMEIOrThrow(context) }
            assertNull(Devicex.getIMEIOrNull(context))
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                assertTrue(Devicex.getIMEIOr(context, "defaultValue") != "defaultValue")
                assertTrue(Devicex.getIMEIOrThrow(context).isSafe())
                assertTrue(Devicex.getIMEIOrNull(context).isSafe())
            } else {
                assertEquals("defaultValue", Devicex.getIMEIOr(context, "defaultValue"))
                assertThrow(SecurityException::class.java) { Devicex.getIMEIOrThrow(context) }
                assertNull(Devicex.getIMEIOrNull(context))
            }
        } else {
            assertTrue(Devicex.getIMEIOr(context, "defaultValue") != "defaultValue")
            assertTrue(Devicex.getIMEIOrThrow(context).isSafe())
            assertTrue(Devicex.getIMEIOrNull(context).isSafe())
        }
    }

    @Test
    fun testGetIMSI() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            assertEquals("defaultValue", Devicex.getIMSIOr(context, "defaultValue"))
            assertThrow(SecurityException::class.java) { Devicex.getIMSIOrThrow(context) }
            assertNull(Devicex.getIMSIOrNull(context))
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                assertTrue(Devicex.getIMSIOr(context, "defaultValue") != "defaultValue")
                assertTrue(Devicex.getIMSIOrThrow(context).isSafe())
                assertTrue(Devicex.getIMSIOrThrow(context).isSafe())
            } else {
                assertEquals("defaultValue", Devicex.getIMSIOr(context, "defaultValue"))
                assertThrow(SecurityException::class.java) { Devicex.getIMSIOrThrow(context) }
                assertNull(Devicex.getIMSIOrNull(context))
            }
        } else {
            assertTrue(Devicex.getIMSIOr(context, "defaultValue") != "defaultValue")
            assertTrue(Devicex.getIMSIOrThrow(context).isSafe())
            assertTrue(Devicex.getIMSIOrThrow(context).isSafe())
        }
    }

    @Test
    fun testGetMacAddress() {
        val macAddressPattern = Pattern.compile("([A-Fa-f0-9]{2}(-[A-Fa-f0-9]{2}){5})|([A-Fa-f0-9]{2}(:[A-Fa-f0-9]{2}){5})")
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.ACCESS_WIFI_STATE)) {
                val maxAddress = Devicex.getMacAddressOr(context, "defaultValue")
                assertTrue("maxAddress: $maxAddress", maxAddress == "defaultValue" || macAddressPattern.matcher(maxAddress).matches())
            } else {
                val maxAddress = Devicex.getMacAddressOr(context, "defaultValue")
                val maxAddressNoDefault = Devicex.getMacAddress(context)
                assertEquals("maxAddressNoDefault: $maxAddressNoDefault", "02:00:00:00:00:00", maxAddressNoDefault)
                assertEquals("maxAddress: $maxAddress", "defaultValue", maxAddress)
            }
        } else {
            val maxAddress = Devicex.getMacAddressOr(context, "defaultValue")
            assertTrue("maxAddress: $maxAddress", macAddressPattern.matcher(maxAddress).matches())
        }
    }
}