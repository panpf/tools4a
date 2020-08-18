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

package com.github.panpf.tools4a.device.test;

import android.Manifest;
import android.content.Context;
import android.os.Build;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.device.Devicex;
import com.github.panpf.tools4a.permission.Permissionx;
import com.github.panpf.tools4j.lang.Stringx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Pattern;

@RunWith(AndroidJUnit4.class)
public class DevicexTest {

    @Test
    public final void testModel() {
        Assert.assertTrue(Stringx.isSafe(Devicex.getModel()));
    }

    @Test
    public final void testName() {
        Assert.assertTrue(Stringx.isSafe(Devicex.getDevice()));
    }

    @Test
    public final void testHardware() {
        Assert.assertTrue(Stringx.isSafe(Devicex.getHardware()));
    }

    @Test
    public final void testProduct() {
        Assert.assertTrue(Stringx.isSafe(Devicex.getProduct()));
    }

    @Test
    public final void testBrand() {
        Assert.assertTrue(Stringx.isSafe(Devicex.getBrand()));
    }

    @Test
    public final void testSupportedAbis() {
        Assert.assertTrue(Devicex.getSupportedAbis().length != 0);
    }

    @Test
    public final void testPhoneNumber() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertNotNull(Devicex.getPhoneNumberOr(context, "defaultValue"));
    }

    @Test
    public final void testAndroidId() {
        String androidId = Devicex.getAndroidIdOr(InstrumentationRegistry.getContext(), "defaultValue");
        Assert.assertTrue("androidId: " + androidId, Stringx.isSafe(androidId));
    }

    @Test
    public final void testDeviceId() {
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String deviceId = Devicex.getDeviceIdOr(context, "defaultValue");
            Assert.assertEquals("deviceId: " + deviceId, "defaultValue", deviceId);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String deviceId = Devicex.getDeviceIdOr(context, "defaultValue");
                Assert.assertTrue("deviceId: " + deviceId,
                        Stringx.isSafe(deviceId) && !deviceId.equals("defaultValue")
                );
            } else {
                String deviceId = Devicex.getDeviceIdOr(context, "defaultValue");
                Assert.assertEquals("deviceId: " + deviceId, "defaultValue", deviceId);
            }
        } else {
            String deviceId = Devicex.getDeviceIdOr(context, "defaultValue");
            Assert.assertTrue("deviceId: " + deviceId,
                    Stringx.isSafe(deviceId) && !deviceId.equals("defaultValue")
            );
        }
    }

    @Test
    public final void testSubscriberId() {
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String subscriberId = Devicex.getSubscriberIdOr(context, "defaultValue");
            Assert.assertEquals("subscriberId: " + subscriberId, "defaultValue", subscriberId);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String subscriberId = Devicex.getSubscriberIdOr(context, "defaultValue");
                Assert.assertTrue("subscriberId: " + subscriberId,
                        Stringx.isSafe(subscriberId) && !subscriberId.equals("defaultValue")
                );
            } else {
                String subscriberId = Devicex.getSubscriberIdOr(context, "defaultValue");
                Assert.assertEquals("subscriberId: " + subscriberId, "defaultValue", subscriberId);
            }
        } else {
            String subscriberId = Devicex.getSubscriberIdOr(context, "defaultValue");
            Assert.assertTrue("subscriberId: " + subscriberId,
                    Stringx.isSafe(subscriberId) && !subscriberId.equals("defaultValue")
            );
        }
    }

    @Test
    public final void testSimSerialNumber() {
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String simSerialNumber = Devicex.getSimSerialNumberOr(context, "defaultValue");
            Assert.assertEquals("simSerialNumber: " + simSerialNumber, "defaultValue", simSerialNumber);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String simSerialNumber = Devicex.getSimSerialNumberOr(context, "defaultValue");
                Assert.assertTrue("simSerialNumber: " + simSerialNumber,
                        Stringx.isSafe(simSerialNumber) && !simSerialNumber.equals("defaultValue"));
            } else {
                String simSerialNumber = Devicex.getSimSerialNumberOr(context, "defaultValue");
                Assert.assertEquals("simSerialNumber: " + simSerialNumber, "defaultValue", simSerialNumber);
            }
        } else {
            String simSerialNumber = Devicex.getSimSerialNumberOr(context, "defaultValue");
            Assert.assertTrue("simSerialNumber: " + simSerialNumber,
                    Stringx.isSafe(simSerialNumber) && !simSerialNumber.equals("defaultValue")
            );
        }
    }

    @Test
    public final void testSerial() {
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String serial = Devicex.getSerialOr("defaultValue");
            Assert.assertEquals("serial: " + serial, "defaultValue", serial);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String serial = Devicex.getSerialOr("defaultValue");
                Assert.assertTrue("serial: " + serial,
                        Stringx.isSafe(serial) && !serial.equals("defaultValue")
                );
            } else {
                String serial = Devicex.getSerialOr("defaultValue");
                Assert.assertEquals("serial: " + serial, "defaultValue", serial);
            }
        } else {
            String serial = Devicex.getSerialOr("defaultValue");
            Assert.assertTrue("serial: " + serial,
                    Stringx.isSafe(serial) && !serial.equals("defaultValue")
            );
        }
    }

    @Test
    public final void testIMEI() {
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String imei = Devicex.getIMEIOr(context, "defaultValue");
            Assert.assertEquals("imei: " + imei, "defaultValue", imei);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String imei = Devicex.getIMEIOr(context, "defaultValue");
                Assert.assertTrue("imei: " + imei,
                        Stringx.isSafe(imei) && !imei.equals("defaultValue")
                );
            } else {
                String imei = Devicex.getIMEIOr(context, "defaultValue");
                Assert.assertEquals("imei: " + imei, "defaultValue", imei);
            }
        } else {
            String imei = Devicex.getIMEIOr(context, "defaultValue");
            Assert.assertTrue("imei: " + imei,
                    Stringx.isSafe(imei) && !imei.equals("defaultValue")
            );
        }
    }

    @Test
    public final void testIMSI() {
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String imsi = Devicex.getIMSIOr(context, "defaultValue");
            Assert.assertEquals("imsi: " + imsi, "defaultValue", imsi);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String imsi = Devicex.getIMSIOr(context, "defaultValue");
                Assert.assertTrue("imsi: " + imsi,
                        Stringx.isSafe(imsi) && !imsi.equals("defaultValue")
                );
            } else {
                String imsi = Devicex.getIMSIOr(context, "defaultValue");
                Assert.assertEquals("imsi: " + imsi, "defaultValue", imsi);
            }
        } else {
            String imsi = Devicex.getIMSIOr(context, "defaultValue");
            Assert.assertTrue("imsi: " + imsi,
                    Stringx.isSafe(imsi) && !imsi.equals("defaultValue")
            );
        }
    }

    @Test
    public final void testMacAddress() {
        Pattern macAddressPattern = Pattern.compile("([A-Fa-f0-9]{2}(-[A-Fa-f0-9]{2}){5})|([A-Fa-f0-9]{2}(:[A-Fa-f0-9]{2}){5})");
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.ACCESS_WIFI_STATE)) {
                Assert.assertTrue(macAddressPattern.matcher(Devicex.getMacAddressOr(context, "defaultValue")).matches());
            } else {
                Assert.assertEquals("02:00:00:00:00:00", Devicex.getMacAddress(context));
                Assert.assertEquals("defaultValue", Devicex.getMacAddressOr(context, "defaultValue"));
            }
        } else {
            Assert.assertTrue(macAddressPattern.matcher(Devicex.getMacAddressOr(context, "defaultValue")).matches());
        }
    }
}
