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
import android.net.Network;
import android.net.wifi.WifiManager;
import android.os.Build;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.panpf.tools4a.network.NetworkCapabilities1Impl;
import com.github.panpf.tools4a.network.NetworkCapabilities21Impl;
import com.github.panpf.tools4a.network.NetworkCapabilitiesCompat;
import com.github.panpf.tools4a.network.Networkx;
import com.github.panpf.tools4j.net.Netx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NetworkxTest {

    @Test
    public void testConnectivity() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertNotNull(Networkx.connectivity(context));
    }

    @Test
    public void testActiveNetwork() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        ConnectivityManager connectivity = Networkx.connectivity(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivity.getAllNetworks();
            if (networks != null && networks.length > 0) {
                Assert.assertNotNull(Networkx.activeNetwork(context));
                Assert.assertNotNull(Networkx.activeNetwork(connectivity));
            } else {
                Assert.assertNull(Networkx.activeNetwork(context));
                Assert.assertNull(Networkx.activeNetwork(connectivity));
            }
        }
    }

    @Test
    public void testNetworkCapabilitiesCompat() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Networkx.networkCapabilitiesCompat(context);
        Assert.assertNotNull(networkCapabilitiesCompat);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertTrue(networkCapabilitiesCompat instanceof NetworkCapabilities21Impl);
        } else {
            Assert.assertTrue(networkCapabilitiesCompat instanceof NetworkCapabilities1Impl);
        }
    }

    @Test
    public void testIsConnected() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Assert.assertEquals(
                Networkx.networkCapabilitiesCompat(context).isConnected(),
                Networkx.isConnected(context));
    }

    @Test
    public void testIsWifiConnected() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Assert.assertEquals(
                Networkx.networkCapabilitiesCompat(context).isWifiConnected(),
                Networkx.isWifiConnected(context));
    }

    @Test
    public void testIsCellularConnected() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Assert.assertEquals(
                Networkx.networkCapabilitiesCompat(context).isCellularConnected(),
                Networkx.isCellularConnected(context));
    }


    @Test
    public void testIsNotMetered() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Assert.assertEquals(
                Networkx.networkCapabilitiesCompat(context).hasNotMeteredCapability(),
                Networkx.isNotMetered(context));
    }

    @Test
    public void testIsMetered() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Assert.assertEquals(
                Networkx.networkCapabilitiesCompat(context).hasMeteredCapability(),
                Networkx.isMetered(context));
    }


    @Test
    public void testIsNotRoaming() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Assert.assertEquals(
                Networkx.networkCapabilitiesCompat(context).hasNotRoamingCapability(),
                Networkx.isNotRoaming(context));
    }

    @Test
    public void testIsRoaming() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Assert.assertEquals(
                Networkx.networkCapabilitiesCompat(context).hasRoamingCapability(),
                Networkx.isRoaming(context));
    }

    @Test
    public void testGetTransportNames() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Assert.assertEquals(
                Networkx.networkCapabilitiesCompat(context).getTransportNames(),
                Networkx.getTransportNames(context));
    }

    @Test
    public void testGetCapabilityNames() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Assert.assertEquals(
                Networkx.networkCapabilitiesCompat(context).getCapabilityNames(),
                Networkx.getCapabilityNames(context));
    }

    @Test
    public void testGetWifiState() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        int wifiState = Networkx.getWifiState(context);
        Assert.assertTrue(wifiState >= WifiManager.WIFI_STATE_DISABLING && wifiState <= WifiManager.WIFI_STATE_UNKNOWN);
    }

    @Test
    public void testIsWifiEnabled() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int wifiState = wifiManager != null ? wifiManager.getWifiState() : WifiManager.WIFI_STATE_UNKNOWN;
        if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
            Assert.assertTrue(Networkx.isWifiEnabled(context));
        } else {
            Assert.assertFalse(Networkx.isWifiEnabled(context));
        }
    }

    @Test
    public void testGateway() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        String wifiGateway = Networkx.getWifiGateway(context);
        if (Networkx.isWifiConnected(context)) {
            Assert.assertNotNull(wifiGateway);
            Assert.assertTrue(Netx.isIPV4(wifiGateway) || Netx.isIPV6(wifiGateway));
        } else {
            Assert.assertNull(wifiGateway);
        }
    }

    @Test
    public void testDNS() {
        String dns1 = Networkx.getDNS1();
        if (dns1 != null) {
            Assert.assertTrue(Netx.isIPV4(dns1) || Netx.isIPV6(dns1));
        }

        String dns2 = Networkx.getDNS2();
        if (dns2 != null) {
            Assert.assertTrue(Netx.isIPV4(dns2) || Netx.isIPV6(dns2));
        }
    }
}
