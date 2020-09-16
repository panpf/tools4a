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

package com.github.panpf.tools4a.permission.test;

import android.Manifest;
import android.content.Context;
import android.os.Build;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.panpf.tools4a.permission.Permissionx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public final class PermissionxTest {

    @Test
    public final void testSinglePermission() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertTrue(Permissionx.isGrantPermissions(context, Manifest.permission.INTERNET));
    }

    @Test
    public final void testSingleNoPermission() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String result = TestUtils.getDeviceIdOr(context, "defaultValue");
            if ("defaultValue".equals(result)) {
                Assert.assertFalse(Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE));
            } else {
                Assert.assertTrue(Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE));
            }
        } else {
            Assert.assertTrue(Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE));
        }
    }

    @Test
    public final void testMultiPermission() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertEquals(
                0,
                Permissionx.filterDeniedPermissions(context, Manifest.permission.INTERNET, Manifest.permission.VIBRATE).length
        );
    }

    @Test
    public final void testMultiNoPermission() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String deviceIdResult = TestUtils.getDeviceIdOr(context, "defaultValue");
            String macAddressResult = TestUtils.getMacAddressOr(context, "defaultValue");
            if ("defaultValue".equals(deviceIdResult) || "defaultValue".equals(macAddressResult)) {
                Assert.assertFalse(Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE));
            } else {
                Assert.assertTrue(Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE));
            }
        } else {
            Assert.assertTrue(Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE));
        }
    }
}
