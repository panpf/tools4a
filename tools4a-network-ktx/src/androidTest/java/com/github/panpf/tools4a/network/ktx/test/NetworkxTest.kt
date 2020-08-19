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

@file:Suppress("DEPRECATION")

package com.github.panpf.tools4a.network.ktx.test

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.network.ktx.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NetworkxTest {

    @Test
    fun testGetNetworkState() {
        val context = InstrumentationRegistry.getContext()
        Assert.assertNotNull(context.getNetworkState())
    }

    @Test
    fun testIsActivated() {
        val context = InstrumentationRegistry.getContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            Assert.assertTrue(context.isNetworkActivated())
            if (context.isWifiNetworkEnabled() && context.setWifiNetworkEnabled(false)) {
                Assert.assertFalse(context.isNetworkActivated())
                context.setWifiNetworkEnabled(true)
            } else if (context.isMobileNetworkEnabled() && context.setMobileNetworkEnabled(false)) {
                Assert.assertFalse(context.isNetworkActivated())
                context.setMobileNetworkEnabled(true)
            }
        } else {
            Assert.assertFalse(context.isNetworkActivated())
        }
    }

    @Test
    fun testIsWifiActivated() {
        val context = InstrumentationRegistry.getContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
            Assert.assertTrue(context.isWifiNetworkActivated())
            if (context.isWifiNetworkEnabled() && context.setWifiNetworkEnabled(false)) {
                Thread.sleep(1000)
                try {
                    Assert.assertFalse(context.isNetworkActivated())
                } finally {
                    context.setWifiNetworkEnabled(true)
                }
            }
        } else {
            Assert.assertFalse(context.isWifiNetworkActivated())
        }
    }

    @Test
    fun testIsNoMeteredWifiActivated() {
        val context = InstrumentationRegistry.getContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI && connectivityManager.isActiveNetworkMetered) {
            Assert.assertTrue(context.isNoMeteredWifiNetworkActivated())
        } else {
            Assert.assertFalse(context.isNoMeteredWifiNetworkActivated())
        }
    }

    @Test
    fun testIsMobileActivated() {
        val context = InstrumentationRegistry.getContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
            Assert.assertTrue(context.isMobileNetworkActivated())
            if (context.isMobileNetworkEnabled() && context.setMobileNetworkEnabled(false)) {
                Assert.assertFalse(context.isNetworkActivated())
                context.setMobileNetworkEnabled(true)
            }
        } else {
            Assert.assertFalse(context.isMobileNetworkActivated())
        }
    }

    @Test
    fun testIsBluetoothActivated() {
        val context = InstrumentationRegistry.getContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_BLUETOOTH) {
            Assert.assertTrue(context.isBluetoothNetworkActivated())
        } else {
            Assert.assertFalse(context.isBluetoothNetworkActivated())
        }
    }

    @Test
    fun testIsVPNActivated() {
        val context = InstrumentationRegistry.getContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_VPN) {
            Assert.assertTrue(context.isVPNNetworkActivated())
        } else {
            Assert.assertFalse(context.isVPNNetworkActivated())
        }
    }

    @Test
    fun testIsMetered() {
        val context = InstrumentationRegistry.getContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager.isActiveNetworkMetered) {
            Assert.assertTrue(context.isNetworkMetered())
        } else {
            Assert.assertFalse(context.isNetworkMetered())
        }
    }

    @Test
    fun testIsRoaming() {
        val context = InstrumentationRegistry.getContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected && networkInfo.isRoaming) {
            Assert.assertTrue(context.isNetworkRoaming())
        } else {
            Assert.assertFalse(context.isNetworkRoaming())
        }
    }

    @Test
    fun testGetType() {
        val context = InstrumentationRegistry.getContext()
        if (context.isWifiNetworkActivated()) {
            Assert.assertEquals(ConnectivityManager.TYPE_WIFI.toLong(), context.getNetworkType().toLong())
        } else if (context.isMobileNetworkActivated()) {
            Assert.assertEquals(ConnectivityManager.TYPE_MOBILE.toLong(), context.getNetworkType().toLong())
        } else if (context.isBluetoothNetworkActivated()) {
            Assert.assertEquals(ConnectivityManager.TYPE_BLUETOOTH.toLong(), context.getNetworkType().toLong())
        } else if (context.isVPNNetworkActivated()) {
            Assert.assertEquals(ConnectivityManager.TYPE_VPN.toLong(), context.getNetworkType().toLong())
        } else if (!context.isNetworkActivated()) {
            Assert.assertEquals(-1, context.getNetworkType().toLong())
        }
    }

    @Test
    fun testGetTypeName() {
        val context = InstrumentationRegistry.getContext()
        if (context.isWifiNetworkActivated()) {
            Assert.assertEquals("WIFI", context.getNetworkTypeName())
        } else if (context.isMobileNetworkActivated()) {
            Assert.assertEquals("Mobile", context.getNetworkTypeName())
        } else if (context.isBluetoothNetworkActivated()) {
            Assert.assertEquals("Bluetooth", context.getNetworkTypeName())
        } else if (context.isVPNNetworkActivated()) {
            Assert.assertEquals("VPN", context.getNetworkTypeName())
        } else if (!context.isNetworkActivated()) {
            Assert.assertEquals("unknown", context.getNetworkTypeName())
        }
    }

    @Test
    fun testGetSubTypeName() {
        val context = InstrumentationRegistry.getContext()
        val subtypeName = context.getNetworkSubtypeName()
        if (context.isNetworkActivated()) {
            Assert.assertNotNull(subtypeName)
        } else {
            Assert.assertTrue(subtypeName, "unknown" == subtypeName || "" == subtypeName)
        }
    }

    @Test
    fun testGetExtraInfo() {
        val context = InstrumentationRegistry.getContext()
        if (context.isNetworkActivated()) {
            Assert.assertNotNull(context.getNetworkExtraInfo())
        } else {
            Assert.assertEquals("unknown", context.getNetworkExtraInfo())
        }
    }

    @Test
    fun testGetNetworkInfo() {
        val context = InstrumentationRegistry.getContext()
        if (context.isNetworkActivated()) {
            Assert.assertNotNull(context.getNetworkInfo())
        } else {
            Assert.assertNull(context.getNetworkInfo())
        }
    }

    @Test
    fun testGetWifiState() {
        val context = InstrumentationRegistry.getContext()
        Assert.assertTrue(context.getWifiState() in WifiManager.WIFI_STATE_DISABLING..WifiManager.WIFI_STATE_UNKNOWN)
    }

    @Test
    fun testIsFailover() {
        val context = InstrumentationRegistry.getContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected && networkInfo.isFailover) {
            Assert.assertTrue(context.isNetworkFailover())
        } else {
            Assert.assertFalse(context.isNetworkFailover())
        }
    }

    @Test
    fun testGateway() {
        Assert.assertNotNull(InstrumentationRegistry.getContext().getNetworkGateway())
    }
}
