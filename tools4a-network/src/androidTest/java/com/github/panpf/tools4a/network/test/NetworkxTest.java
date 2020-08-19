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

package com.github.panpf.tools4a.network.test;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.network.Networkx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NetworkxTest {

    @Test
    public void testGetNetworkState() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertNotNull(Networkx.getState(context));
    }

    @Test
    public void testIsActivated() {
        Context context = InstrumentationRegistry.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Assert.assertTrue(Networkx.isActivated(context));
            if (Networkx.isWifiEnabled(context) && Networkx.setWifiEnabled(context, false)) {
                Assert.assertFalse(Networkx.isActivated(context));
                Networkx.setWifiEnabled(context, true);
            } else if (Networkx.isMobileEnabled(context) && Networkx.setMobileEnabled(context, false)) {
                Assert.assertFalse(Networkx.isActivated(context));
                Networkx.setMobileEnabled(context, true);
            }
        } else {
            Assert.assertFalse(Networkx.isActivated(context));
        }
    }

    @Test
    public void testIsWifiActivated() throws InterruptedException {
        Context context = InstrumentationRegistry.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            Assert.assertTrue(Networkx.isWifiActivated(context));
            if (Networkx.isWifiEnabled(context) && Networkx.setWifiEnabled(context, false)) {
                Thread.sleep(1000);
                try {
                    Assert.assertFalse(Networkx.isActivated(context));
                } finally {
                    Networkx.setWifiEnabled(context, true);
                }
            }
        } else {
            Assert.assertFalse(Networkx.isWifiActivated(context));
        }
    }

    @Test
    public void testIsNoMeteredWifiActivated() {
        Context context = InstrumentationRegistry.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            if (!connectivityManager.isActiveNetworkMetered()) {
                Assert.assertTrue(Networkx.isNoMeteredWifiActivated(context));
            } else {
                Assert.assertFalse(Networkx.isNoMeteredWifiActivated(context));
            }
        } else {
            Assert.assertFalse(Networkx.isNoMeteredWifiActivated(context));
        }
    }

    @Test
    public void testIsMobileActivated() {
        Context context = InstrumentationRegistry.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            Assert.assertTrue(Networkx.isMobileActivated(context));
            if (Networkx.isMobileEnabled(context) && Networkx.setMobileEnabled(context, false)) {
                Assert.assertFalse(Networkx.isActivated(context));
                Networkx.setMobileEnabled(context, true);
            }
        } else {
            Assert.assertFalse(Networkx.isMobileActivated(context));
        }
    }

    @Test
    public void testIsBluetoothActivated() {
        Context context = InstrumentationRegistry.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_BLUETOOTH) {
            Assert.assertTrue(Networkx.isBluetoothActivated(context));
        } else {
            Assert.assertFalse(Networkx.isBluetoothActivated(context));
        }
    }

    @Test
    public void testIsVPNActivated() {
        Context context = InstrumentationRegistry.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_VPN) {
            Assert.assertTrue(Networkx.isVPNActivated(context));
        } else {
            Assert.assertFalse(Networkx.isVPNActivated(context));
        }
    }

    @Test
    public void testIsMetered() {
        Context context = InstrumentationRegistry.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.isActiveNetworkMetered()) {
            Assert.assertTrue(Networkx.isMetered(context));
        } else {
            Assert.assertFalse(Networkx.isMetered(context));
        }
    }

    @Test
    public void testIsRoaming() {
        Context context = InstrumentationRegistry.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.isRoaming()) {
            Assert.assertTrue(Networkx.isRoaming(context));
        } else {
            Assert.assertFalse(Networkx.isRoaming(context));
        }
    }

    @Test
    public void testGetType() {
        Context context = InstrumentationRegistry.getContext();
        if (Networkx.isWifiActivated(context)) {
            Assert.assertEquals(ConnectivityManager.TYPE_WIFI, Networkx.getType(context));
        } else if (Networkx.isMobileActivated(context)) {
            Assert.assertEquals(ConnectivityManager.TYPE_MOBILE, Networkx.getType(context));
        } else if (Networkx.isBluetoothActivated(context)) {
            Assert.assertEquals(ConnectivityManager.TYPE_BLUETOOTH, Networkx.getType(context));
        } else if (Networkx.isVPNActivated(context)) {
            Assert.assertEquals(ConnectivityManager.TYPE_VPN, Networkx.getType(context));
        } else if (!Networkx.isActivated(context)) {
            Assert.assertEquals(-1, Networkx.getType(context));
        }
    }

    @Test
    public void testGetTypeName() {
        Context context = InstrumentationRegistry.getContext();
        if (Networkx.isWifiActivated(context)) {
            Assert.assertEquals("WIFI", Networkx.getTypeName(context));
        } else if (Networkx.isMobileActivated(context)) {
            Assert.assertEquals("Mobile", Networkx.getTypeName(context));
        } else if (Networkx.isBluetoothActivated(context)) {
            Assert.assertEquals("Bluetooth", Networkx.getTypeName(context));
        } else if (Networkx.isVPNActivated(context)) {
            Assert.assertEquals("VPN", Networkx.getTypeName(context));
        } else if (!Networkx.isActivated(context)) {
            Assert.assertEquals("unknown", Networkx.getTypeName(context));
        }
    }

    @Test
    public void testGetSubTypeName() {
        Context context = InstrumentationRegistry.getContext();
        String subtypeName = Networkx.getSubtypeName(context);
        if (Networkx.isActivated(context)) {
            Assert.assertNotNull(subtypeName);
        } else {
            Assert.assertTrue(subtypeName, "unknown".equals(subtypeName) || "".equals(subtypeName));
        }
    }

    @Test
    public void testGetExtraInfo() {
        Context context = InstrumentationRegistry.getContext();
        if (Networkx.isActivated(context)) {
            Assert.assertNotNull(Networkx.getExtraInfo(context));
        } else {
            Assert.assertEquals("unknown", Networkx.getExtraInfo(context));
        }
    }

    @Test
    public void testGetWifiState() {
        Context context = InstrumentationRegistry.getContext();
        int wifiState = Networkx.getWifiState(context);
        Assert.assertTrue(wifiState >= WifiManager.WIFI_STATE_DISABLING && wifiState <= WifiManager.WIFI_STATE_UNKNOWN);
    }

    @Test
    public void testIsFailover() {
        Context context = InstrumentationRegistry.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.isFailover()) {
            Assert.assertTrue(Networkx.isFailover(context));
        } else {
            Assert.assertFalse(Networkx.isFailover(context));
        }
    }

    @Test
    public void testGateway() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertNotNull(Networkx.getGateway(context));
    }

    @Test
    public void testDNS() {
        Assert.assertNotNull(Networkx.getDNS1());
        Assert.assertNotNull(Networkx.getDNS2());
    }
}
