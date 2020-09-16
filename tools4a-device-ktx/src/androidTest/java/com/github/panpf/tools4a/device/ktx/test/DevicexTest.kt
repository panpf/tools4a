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
import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.device.ktx.*
import com.github.panpf.tools4a.permission.ktx.isGrantPermissions
import com.github.panpf.tools4j.lang.ktx.isSafe
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern

@RunWith(AndroidJUnit4::class)
class DevicexTest {

    @Test
    fun testPhoneNumber() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertNotNull(context.getPhoneNumberOr("defaultValue"))
    }

    @Test
    fun testAndroidId() {
        val androidId = InstrumentationRegistry.getInstrumentation().context.getAndroidIdOr("defaultValue")
        Assert.assertTrue("androidId: $androidId", androidId.isSafe())
    }

    @Test
    fun testDeviceId() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val deviceId = context.getDeviceIdOr("defaultValue")
            Assert.assertEquals("deviceId: $deviceId", "defaultValue", deviceId)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                val deviceId = context.getDeviceIdOr("defaultValue")
                Assert.assertTrue("deviceId: $deviceId",
                        deviceId.isSafe() && deviceId != "defaultValue"
                )
            } else {
                val deviceId = context.getDeviceIdOr("defaultValue")
                Assert.assertEquals("deviceId: $deviceId", "defaultValue", deviceId)
            }
        } else {
            val deviceId = context.getDeviceIdOr("defaultValue")
            Assert.assertTrue("deviceId: $deviceId",
                    deviceId.isSafe() && deviceId != "defaultValue"
            )
        }
    }

    @Test
    fun testSubscriberId() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val subscriberId = context.getSubscriberIdOr("defaultValue")
            Assert.assertEquals("subscriberId: $subscriberId", "defaultValue", subscriberId)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                val subscriberId = context.getSubscriberIdOr("defaultValue")
                Assert.assertTrue("subscriberId: $subscriberId",
                        subscriberId.isSafe() && subscriberId != "defaultValue"
                )
            } else {
                val subscriberId = context.getSubscriberIdOr("defaultValue")
                Assert.assertEquals("subscriberId: $subscriberId", "defaultValue", subscriberId)
            }
        } else {
            val subscriberId = context.getSubscriberIdOr("defaultValue")
            Assert.assertTrue("subscriberId: $subscriberId",
                    subscriberId.isSafe() && subscriberId != "defaultValue"
            )
        }
    }

    @Test
    fun testSimSerialNumber() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val simSerialNumber = context.getSimSerialNumberOr("defaultValue")
            Assert.assertEquals("simSerialNumber: $simSerialNumber", "defaultValue", simSerialNumber)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                val simSerialNumber = context.getSimSerialNumberOr("defaultValue")
                Assert.assertTrue("simSerialNumber: $simSerialNumber",
                        simSerialNumber.isSafe() && simSerialNumber != "defaultValue"
                )
            } else {
                val simSerialNumber = context.getSimSerialNumberOr("defaultValue")
                Assert.assertEquals("simSerialNumber: $simSerialNumber", "defaultValue", simSerialNumber)
            }
        } else {
            val simSerialNumber = context.getSimSerialNumberOr("defaultValue")
            Assert.assertTrue("simSerialNumber: $simSerialNumber",
                    simSerialNumber.isSafe() && simSerialNumber != "defaultValue"
            )
        }
    }

    @Test
    fun testIMEI() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val imei = context.getIMEIOr("defaultValue")
            Assert.assertEquals("imei: $imei", "defaultValue", imei)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                val imei = context.getIMEIOr("defaultValue")
                Assert.assertTrue("imei: $imei",
                        imei.isSafe() && imei != "defaultValue"
                )
            } else {
                val imei = context.getIMEIOr("defaultValue")
                Assert.assertEquals("imei: $imei", "defaultValue", imei)
            }
        } else {
            val imei = context.getIMEIOr("defaultValue")
            Assert.assertTrue("imei: $imei",
                    imei.isSafe() && imei != "defaultValue"
            )
        }
    }

    @Test
    fun testIMSI() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val imsi = context.getIMSIOr("defaultValue")
            Assert.assertEquals("imsi: $imsi", "defaultValue", imsi)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                val imsi = context.getIMSIOr("defaultValue")
                Assert.assertTrue("imsi: $imsi",
                        imsi.isSafe() && imsi != "defaultValue"
                )
            } else {
                val imsi = context.getIMSIOr("defaultValue")
                Assert.assertEquals("imsi: $imsi", "defaultValue", imsi)
            }
        } else {
            val imsi = context.getIMSIOr("defaultValue")
            Assert.assertTrue("imsi: $imsi",
                    imsi.isSafe() && imsi != "defaultValue"
            )
        }
    }

    @Test
    fun testMacAddress() {
        val macAddressPattern = Pattern.compile("([A-Fa-f0-9]{2}(-[A-Fa-f0-9]{2}){5})|([A-Fa-f0-9]{2}(:[A-Fa-f0-9]{2}){5})")
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.isGrantPermissions(Manifest.permission.ACCESS_WIFI_STATE)) {
                val maxAddress = context.getMacAddressOr("defaultValue")
                Assert.assertTrue("maxAddress: $maxAddress", macAddressPattern.matcher(maxAddress).matches())
            } else {
                val maxAddress = context.getMacAddressOr("defaultValue")
                val maxAddressNoDefault = context.getMacAddress()
                Assert.assertEquals("maxAddressNoDefault: $maxAddressNoDefault", "02:00:00:00:00:00", maxAddressNoDefault)
                Assert.assertEquals("maxAddress: $maxAddress", "defaultValue", maxAddress)
            }
        } else {
            val maxAddress = context.getMacAddressOr("defaultValue")
            Assert.assertTrue("maxAddress: $maxAddress", macAddressPattern.matcher(maxAddress).matches())
        }
    }
}