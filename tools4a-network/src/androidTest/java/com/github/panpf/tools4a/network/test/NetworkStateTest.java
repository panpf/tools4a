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

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.network.NetworkState;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NetworkStateTest {

    @Test
    public void testGetNetworkState() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertNotNull(NetworkState.get(context));
    }

    @Test
    public void testIsActivated() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Assert.assertTrue(networkState.isActivated());
        } else {
            Assert.assertFalse(networkState.isActivated());
        }
    }

    @Test
    public void testIsWifiActivated() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            Assert.assertTrue(networkState.isWifiActivated());
        } else {
            Assert.assertFalse(networkState.isWifiActivated());
        }
    }

    @Test
    public void testIsNoMeteredWifiActivated() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI && !connectivityManager.isActiveNetworkMetered()) {
            Assert.assertTrue(networkState.isNoMeteredWifiActivated());
        } else {
            Assert.assertFalse(networkState.isNoMeteredWifiActivated());
        }
    }

    @Test
    public void testIsMobileActivated() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            Assert.assertTrue(networkState.isMobileActivated());
        } else {
            Assert.assertFalse(networkState.isMobileActivated());
        }
    }

    @Test
    public void testIsBluetoothActivated() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_BLUETOOTH) {
            Assert.assertTrue(networkState.isBluetoothActivated());
        } else {
            Assert.assertFalse(networkState.isBluetoothActivated());
        }
    }

    @Test
    public void testIsVPNActivated() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_VPN) {
            Assert.assertTrue(networkState.isVPNActivated());
        } else {
            Assert.assertFalse(networkState.isVPNActivated());
        }
    }

    @Test
    public void testIsMetered() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.isActiveNetworkMetered()) {
            Assert.assertTrue(networkState.isMetered());
        } else {
            Assert.assertFalse(networkState.isMetered());
        }
    }

    @Test
    public void testIsRoaming() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.isRoaming()) {
            Assert.assertTrue(networkState.isRoaming());
        } else {
            Assert.assertFalse(networkState.isRoaming());
        }
    }

    @Test
    public void testGetType() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        if (networkState.isWifiActivated()) {
            Assert.assertEquals(ConnectivityManager.TYPE_WIFI, networkState.getType());
        } else if (networkState.isMobileActivated()) {
            Assert.assertEquals(ConnectivityManager.TYPE_MOBILE, networkState.getType());
        } else if (networkState.isBluetoothActivated()) {
            Assert.assertEquals(ConnectivityManager.TYPE_BLUETOOTH, networkState.getType());
        } else if (networkState.isVPNActivated()) {
            Assert.assertEquals(ConnectivityManager.TYPE_VPN, networkState.getType());
        } else if (!networkState.isActivated()) {
            Assert.assertEquals(-1, networkState.getType());
        }
    }

    @Test
    public void testGetTypeName() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        if (networkState.isWifiActivated()) {
            Assert.assertEquals("WIFI", networkState.getTypeName());
        } else if (networkState.isMobileActivated()) {
            Assert.assertEquals("Mobile", networkState.getTypeName());
        } else if (networkState.isBluetoothActivated()) {
            Assert.assertEquals("Bluetooth", networkState.getTypeName());
        } else if (networkState.isVPNActivated()) {
            Assert.assertEquals("VPN", networkState.getTypeName());
        } else if (!networkState.isActivated()) {
            Assert.assertEquals("unknown", networkState.getTypeName());
        }
    }

    @Test
    public void testGetSubTypeName() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        if (networkState.isActivated()) {
            Assert.assertNotNull(networkState.getSubtypeName());
        } else {
            Assert.assertEquals("unknown", networkState.getSubtypeName());
        }
    }

    @Test
    public void testGetExtraInfo() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        if (networkState.isActivated()) {
            Assert.assertNotNull(networkState.getExtraInfo());
        } else {
            Assert.assertEquals("unknown", networkState.getExtraInfo());
        }
    }

    @Test
    public void testIsFailover() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.isFailover()) {
            Assert.assertTrue(networkState.isFailover());
        } else {
            Assert.assertFalse(networkState.isFailover());
        }
    }

    @Test
    public void testGetConnectivity() {
        Context context = InstrumentationRegistry.getContext();
        NetworkState networkState = NetworkState.get(context);
        Assert.assertNotNull(networkState.getConnectivity());
    }
}
