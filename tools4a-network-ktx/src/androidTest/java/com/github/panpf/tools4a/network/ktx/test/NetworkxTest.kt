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

package com.github.panpf.tools4a.network.ktx.test

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.network.NetworkCapabilities1Impl
import com.github.panpf.tools4a.network.NetworkCapabilities21Impl
import com.github.panpf.tools4a.network.Networkx
import com.github.panpf.tools4a.network.ktx.*
import com.github.panpf.tools4j.net.Netx
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NetworkxTest {

    @Test
    fun testActiveNetwork() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val connectivity = Networkx.connectivity(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val networks = connectivity.allNetworks
            if (networks.isNotEmpty()) {
                Assert.assertNotNull(context.activeNetwork())
                Assert.assertNotNull(connectivity.activeNetworkCompat())
            } else {
                Assert.assertNull(context.activeNetwork())
                Assert.assertNull(connectivity.activeNetworkCompat())
            }
        }
    }

    @Test
    fun testNetworkCapabilitiesCompat() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val networkCapabilitiesCompat = context.networkCapabilitiesCompat()
        Assert.assertNotNull(networkCapabilitiesCompat)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertTrue(networkCapabilitiesCompat is NetworkCapabilities21Impl)
        } else {
            Assert.assertTrue(networkCapabilitiesCompat is NetworkCapabilities1Impl)
        }
    }

    @Test
    fun testIsConnected() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals(
                context.networkCapabilitiesCompat().isConnected,
                context.isNetworkConnected())
    }

    @Test
    fun testIsWifiConnected() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals(
                context.networkCapabilitiesCompat().isWifiConnected,
                context.isWifiNetworkConnected())
    }

    @Test
    fun testIsCellularConnected() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals(
                context.networkCapabilitiesCompat().isCellularConnected,
                context.isCellularNetworkConnected())
    }


    @Test
    fun testIsNotMetered() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals(
                context.networkCapabilitiesCompat().hasNotMeteredCapability(),
                context.isNetworkNotMetered())
    }

    @Test
    fun testIsMetered() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals(
                context.networkCapabilitiesCompat().hasMeteredCapability(),
                context.isNetworkMetered())
    }


    @Test
    fun testIsNotRoaming() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals(
                context.networkCapabilitiesCompat().hasNotRoamingCapability(),
                context.isNetworkNotRoaming())
    }

    @Test
    fun testIsRoaming() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals(
                context.networkCapabilitiesCompat().hasRoamingCapability(),
                context.isNetworkRoaming())
    }

    @Test
    fun testGetTransportNames() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals(
                context.networkCapabilitiesCompat().transportNames,
                context.getNetworkTransportNames())
    }

    @Test
    fun testGetCapabilityNames() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals(
                context.networkCapabilitiesCompat().capabilityNames,
                context.getNetworkCapabilityNames())
    }

    @Test
    fun testGetWifiState() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val wifiState = context.getWifiState()
        Assert.assertTrue(wifiState >= WifiManager.WIFI_STATE_DISABLING && wifiState <= WifiManager.WIFI_STATE_UNKNOWN)
    }

    @Test
    fun testIsWifiEnabled() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        val wifiState = wifiManager?.wifiState ?: WifiManager.WIFI_STATE_UNKNOWN
        if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
            Assert.assertTrue(context.isWifiEnabled())
        } else {
            Assert.assertFalse(context.isWifiEnabled())
        }
    }

    @Test
    fun testGateway() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val wifiGateway = context.getWifiGateway()
        if (context.isWifiNetworkConnected()) {
            Assert.assertNotNull(wifiGateway)
            Assert.assertTrue(Netx.isIPV4(wifiGateway) || Netx.isIPV6(wifiGateway))
        } else {
            Assert.assertNull(wifiGateway)
        }
    }
}
