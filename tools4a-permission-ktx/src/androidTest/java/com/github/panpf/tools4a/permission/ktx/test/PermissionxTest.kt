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

package com.github.panpf.tools4a.permission.ktx.test

import android.Manifest
import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.permission.ktx.filterDeniedPermissions
import com.github.panpf.tools4a.permission.ktx.isGrantPermissions
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PermissionxTest {

    @Test
    fun testSinglePermission() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertTrue(context.isGrantPermissions(Manifest.permission.INTERNET))
    }

    @Test
    fun testSingleNoPermission() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result = TestUtils.getDeviceIdOr(context, "defaultValue")
            if ("defaultValue" == result) {
                Assert.assertFalse(context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE))
            } else {
                Assert.assertTrue(context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE))
            }
        } else {
            Assert.assertTrue(context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE))
        }
    }

    @Test
    fun testMultiPermission() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertTrue(context.filterDeniedPermissions(Manifest.permission.INTERNET, Manifest.permission.VIBRATE).isEmpty())
    }

    @Test
    fun testMultiNoPermission() {
        val context = InstrumentationRegistry.getInstrumentation().context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val deviceIdResult = TestUtils.getDeviceIdOr(context, "defaultValue")
            val macAddressResult = TestUtils.getMacAddressOr(context, "defaultValue")
            if ("defaultValue" == deviceIdResult || "defaultValue" == macAddressResult) {
                Assert.assertFalse(context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE))
            } else {
                Assert.assertTrue(context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE))
            }
        } else {
            Assert.assertTrue(context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE))
        }
    }
}