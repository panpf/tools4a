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
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.panpf.tools4a.network.NetCapability;
import com.github.panpf.tools4a.network.NetworkCapabilities1Impl;
import com.github.panpf.tools4a.network.NetworkCapabilities21Impl;
import com.github.panpf.tools4a.network.NetworkCapabilitiesCompat;
import com.github.panpf.tools4a.network.Transport;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

@RunWith(AndroidJUnit4.class)
public class NetworkCapabilitiesCompatTest {

    @Test
    public void testIsConnected() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean isConnected = networkCapabilitiesCompat.isConnected();
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                Assert.assertTrue(isConnected);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("INTERNET"));
            } else {
                Assert.assertFalse(isConnected);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("INTERNET"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                Assert.assertTrue(isConnected);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("INTERNET"));
            } else {
                Assert.assertFalse(isConnected);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("INTERNET"));
            }
        }

        String qqWebPageContent = null;
        try {
            InputStream inputStream = new URL("https://www.qq.com").openStream();
            byte[] urlContentBytes;
            try {
                ByteArrayOutputStream bufferOutputStream = new ByteArrayOutputStream(Math.max(1024 * 8, inputStream.available()));
                byte[] buffer = new byte[1024 * 8];
                int bytes;
                while (true) {
                    bytes = inputStream.read(buffer);
                    if (bytes < 0) break;
                    bufferOutputStream.write(buffer, 0, bytes);
                }
                urlContentBytes = bufferOutputStream.toByteArray();
            } finally {
                inputStream.close();
            }
            //noinspection CharsetObjectCanBeUsed
            qqWebPageContent = new String(urlContentBytes, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (qqWebPageContent != null) {
            Assert.assertTrue(isConnected);
        } else {
            Assert.assertFalse(isConnected);
        }
    }

    @Test
    public void testIsWifiConnected() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean isWifiConnected = networkCapabilitiesCompat.isWifiConnected();
        String transportNames = networkCapabilitiesCompat.getTransportNames();
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Assert.assertTrue(isWifiConnected);
                    Assert.assertTrue(transportNames != null && transportNames.contains("WIFI"));
                } else {
                    Assert.assertFalse(isWifiConnected);
                    Assert.assertFalse(transportNames != null && transportNames.contains("WIFI"));
                }
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("INTERNET"));
            } else {
                Assert.assertFalse(isWifiConnected);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("INTERNET"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            int type = networkInfo != null ? networkInfo.getType() : -1;
            if (networkInfo != null && networkInfo.isConnected()) {
                if (type == ConnectivityManager.TYPE_WIFI || type == 13) {
                    Assert.assertTrue(isWifiConnected);
                    Assert.assertTrue(transportNames != null && transportNames.contains("WIFI"));
                } else {
                    Assert.assertFalse(isWifiConnected);
                    Assert.assertFalse(transportNames != null && transportNames.contains("WIFI"));
                }
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("INTERNET"));
            } else {
                Assert.assertFalse(isWifiConnected);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("INTERNET"));
            }
        }
    }

    @Test
    public void testIsCellularConnected() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean isCellularConnected = networkCapabilitiesCompat.isCellularConnected();
        String transportNames = networkCapabilitiesCompat.getTransportNames();
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Assert.assertTrue(isCellularConnected);
                    Assert.assertTrue(transportNames != null && transportNames.contains("CELLULAR"));
                } else {
                    Assert.assertFalse(isCellularConnected);
                    Assert.assertFalse(transportNames != null && transportNames.contains("CELLULAR"));
                }
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("INTERNET"));
            } else {
                Assert.assertFalse(isCellularConnected);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("INTERNET"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            int type = networkInfo != null ? networkInfo.getType() : -1;
            if (networkInfo != null && networkInfo.isConnected()) {
                if (type == ConnectivityManager.TYPE_MOBILE
                        || type == ConnectivityManager.TYPE_MOBILE_DUN || type == ConnectivityManager.TYPE_MOBILE_HIPRI
                        || type == ConnectivityManager.TYPE_MOBILE_MMS || type == ConnectivityManager.TYPE_MOBILE_SUPL
                        || type == 10 || type == 11 || type == 12 || type == 14 || type == 15) {
                    Assert.assertTrue(isCellularConnected);
                    Assert.assertTrue(transportNames != null && transportNames.contains("CELLULAR"));
                } else {
                    Assert.assertFalse(isCellularConnected);
                    Assert.assertFalse(transportNames != null && transportNames.contains("CELLULAR"));
                }
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("INTERNET"));
            } else {
                Assert.assertFalse(isCellularConnected);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("INTERNET"));
            }
        }
    }

    @Test
    public void testHasCellularTransport() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasCellularTransport = networkCapabilitiesCompat.hasCellularTransport();
        String transportNames = networkCapabilitiesCompat.getTransportNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Assert.assertTrue(hasCellularTransport);
                Assert.assertTrue(transportNames != null && transportNames.contains("CELLULAR"));
            } else {
                Assert.assertFalse(hasCellularTransport);
                Assert.assertFalse(transportNames != null && transportNames.contains("CELLULAR"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            int type = networkInfo != null ? networkInfo.getType() : -1;
            if (networkInfo != null && (type == ConnectivityManager.TYPE_MOBILE
                    || type == ConnectivityManager.TYPE_MOBILE_DUN || type == ConnectivityManager.TYPE_MOBILE_HIPRI
                    || type == ConnectivityManager.TYPE_MOBILE_MMS || type == ConnectivityManager.TYPE_MOBILE_SUPL
                    || type == 10 || type == 11 || type == 12 || type == 14 || type == 15)) {
                Assert.assertTrue(hasCellularTransport);
                Assert.assertTrue(transportNames != null && transportNames.contains("CELLULAR"));
            } else {
                Assert.assertFalse(hasCellularTransport);
                Assert.assertFalse(transportNames != null && transportNames.contains("CELLULAR"));
            }
        }
    }

    @Test
    public void testHasWifiTransport() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasWifiTransport = networkCapabilitiesCompat.hasWifiTransport();
        String transportNames = networkCapabilitiesCompat.getTransportNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Assert.assertTrue(hasWifiTransport);
                Assert.assertTrue(transportNames != null && transportNames.contains("WIFI"));
            } else {
                Assert.assertFalse(hasWifiTransport);
                Assert.assertFalse(transportNames != null && transportNames.contains("WIFI"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            int type = networkInfo != null ? networkInfo.getType() : -1;
            if (networkInfo != null && (type == ConnectivityManager.TYPE_WIFI || type == 13)) {
                Assert.assertTrue(hasWifiTransport);
                Assert.assertTrue(transportNames != null && transportNames.contains("WIFI"));
            } else {
                Assert.assertFalse(hasWifiTransport);
                Assert.assertFalse(transportNames != null && transportNames.contains("WIFI"));
            }
        }
    }

    @Test
    public void testHasBluetoothTransport() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasBluetoothTransport = networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_BLUETOOTH);
        String transportNames = networkCapabilitiesCompat.getTransportNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)) {
                Assert.assertTrue(hasBluetoothTransport);
                Assert.assertTrue(transportNames != null && transportNames.contains("BLUETOOTH"));
            } else {
                Assert.assertFalse(hasBluetoothTransport);
                Assert.assertFalse(transportNames != null && transportNames.contains("BLUETOOTH"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_BLUETOOTH) {
                Assert.assertTrue(hasBluetoothTransport);
                Assert.assertTrue(transportNames != null && transportNames.contains("BLUETOOTH"));
            } else {
                Assert.assertFalse(hasBluetoothTransport);
                Assert.assertFalse(transportNames != null && transportNames.contains("BLUETOOTH"));
            }
        }
    }

    @Test
    public void testHasEthernetTransport() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasEthernetTransport = networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_ETHERNET);
        String transportNames = networkCapabilitiesCompat.getTransportNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Assert.assertTrue(hasEthernetTransport);
                Assert.assertTrue(transportNames != null && transportNames.contains("ETHERNET"));
            } else {
                Assert.assertFalse(hasEthernetTransport);
                Assert.assertFalse(transportNames != null && transportNames.contains("ETHERNET"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
                Assert.assertTrue(hasEthernetTransport);
                Assert.assertTrue(transportNames != null && transportNames.contains("ETHERNET"));
            } else {
                Assert.assertFalse(hasEthernetTransport);
                Assert.assertFalse(transportNames != null && transportNames.contains("ETHERNET"));
            }
        }
    }

    @Test
    public void testHasVPNTransport() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasVPNTransport = networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_VPN);
        String transportNames = networkCapabilitiesCompat.getTransportNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                Assert.assertTrue(hasVPNTransport);
                Assert.assertTrue(transportNames != null && transportNames.contains("VPN"));
            } else {
                Assert.assertFalse(hasVPNTransport);
                Assert.assertFalse(transportNames != null && transportNames.contains("VPN"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_VPN) {
                Assert.assertTrue(hasVPNTransport);
                Assert.assertTrue(transportNames != null && transportNames.contains("VPN"));
            } else {
                Assert.assertFalse(hasVPNTransport);
                Assert.assertFalse(transportNames != null && transportNames.contains("VPN"));
            }
        }
    }

    @Test
    public void testHasWifiAwareTransport() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasWifiAwareTransport = networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_WIFI_AWARE);
        String transportNames = networkCapabilitiesCompat.getTransportNames();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE)) {
                Assert.assertTrue(hasWifiAwareTransport);
                Assert.assertTrue(transportNames != null && transportNames.contains("WIFI_AWARE"));
            } else {
                Assert.assertFalse(networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_WIFI_AWARE));
            }
        } else {
            Assert.assertFalse(hasWifiAwareTransport);
            Assert.assertFalse(transportNames != null && transportNames.contains("WIFI_AWARE"));
        }
    }

    @Test
    public void testHasLowPanTransport() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasLowPanTransport = networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_LOWPAN);
        String transportNames = networkCapabilitiesCompat.getTransportNames();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_LOWPAN)) {
                Assert.assertTrue(hasLowPanTransport);
                Assert.assertTrue(transportNames != null && transportNames.contains("LOWPAN"));
            } else {
                Assert.assertFalse(hasLowPanTransport);
                Assert.assertFalse(transportNames != null && transportNames.contains("LOWPAN"));
            }
        } else {
            Assert.assertFalse(hasLowPanTransport);
            Assert.assertFalse(transportNames != null && transportNames.contains("LOWPAN"));
        }
    }

    @Test
    public void testHasTestTransport() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasTestTransport = networkCapabilitiesCompat.hasTransport(7);
        String transportNames = networkCapabilitiesCompat.getTransportNames();

        Assert.assertFalse(hasTestTransport);
        Assert.assertFalse(transportNames != null && transportNames.contains("TEST"));
    }

    @Test
    public void testHasMMSCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasMMSCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_MMS);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_MMS)) {
                Assert.assertTrue(hasMMSCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("MMS"));
            } else {
                Assert.assertFalse(hasMMSCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("MMS"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE_MMS) {
                Assert.assertTrue(hasMMSCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("MMS"));
            } else {
                Assert.assertFalse(hasMMSCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("MMS"));
            }
        }
    }

    @Test
    public void testHasSUPLCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasSUPLCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_SUPL);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_SUPL)) {
                Assert.assertTrue(hasSUPLCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("SUPL"));
            } else {
                Assert.assertFalse(hasSUPLCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("SUPL"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE_SUPL) {
                Assert.assertTrue(hasSUPLCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("SUPL"));
            } else {
                Assert.assertFalse(hasSUPLCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("SUPL"));
            }
        }
    }

    @Test
    public void testHasDUNCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasDUNCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_DUN);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_DUN)) {
                Assert.assertTrue(hasDUNCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("DUN"));
            } else {
                Assert.assertFalse(hasDUNCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("DUN"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE_DUN) {
                Assert.assertTrue(hasDUNCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("DUN"));
            } else {
                Assert.assertFalse(hasDUNCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("DUN"));
            }
        }
    }

    @Test
    public void testHasFOTACapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasFOTACapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_FOTA);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_FOTA)) {
                Assert.assertTrue(hasFOTACapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("FOTA"));
            } else {
                Assert.assertFalse(hasFOTACapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("FOTA"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 10) {
                Assert.assertTrue(hasFOTACapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("FOTA"));
            } else {
                Assert.assertFalse(hasFOTACapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("FOTA"));
            }
        }
    }

    @Test
    public void testHasIMSCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasIMSCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_IMS);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_IMS)) {
                Assert.assertTrue(hasIMSCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("IMS"));
            } else {
                Assert.assertFalse(hasIMSCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("IMS"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 11) {
                Assert.assertTrue(hasIMSCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("IMS"));
            } else {
                Assert.assertFalse(hasIMSCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("IMS"));
            }
        }
    }

    @Test
    public void testHasCBSCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasCBSCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_CBS);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_CBS)) {
                Assert.assertTrue(hasCBSCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("CBS"));
            } else {
                Assert.assertFalse(hasCBSCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("CBS"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 12) {
                Assert.assertTrue(hasCBSCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("CBS"));
            } else {
                Assert.assertFalse(hasCBSCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("CBS"));
            }
        }
    }

    @Test
    public void testHasWifiP2PCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasWifiP2PCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_WIFI_P2P);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_WIFI_P2P)) {
                Assert.assertTrue(hasWifiP2PCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("WIFI_P2P"));
            } else {
                Assert.assertFalse(hasWifiP2PCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("WIFI_P2P"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 13) {
                Assert.assertTrue(hasWifiP2PCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("WIFI_P2P"));
            } else {
                Assert.assertFalse(hasWifiP2PCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("WIFI_P2P"));
            }
        }
    }

    @Test
    public void testHasIACapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasIACapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_IA);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_IA)) {
                Assert.assertTrue(hasIACapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("IA"));
            } else {
                Assert.assertFalse(hasIACapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("IA"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 14) {
                Assert.assertTrue(hasIACapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("IA"));
            } else {
                Assert.assertFalse(hasIACapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("IA"));
            }
        }
    }

    @Test
    public void testHasRCSCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasRCSCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_RCS);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_RCS)) {
                Assert.assertTrue(hasRCSCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("RCS"));
            } else {
                Assert.assertFalse(hasRCSCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("RCS"));
            }
        } else {
            Assert.assertFalse(hasRCSCapability);
            Assert.assertFalse(capabilityNames != null && capabilityNames.contains("RCS"));
        }
    }

    @Test
    public void testHasXCAPCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasXCAPCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_XCAP);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_XCAP)) {
                Assert.assertTrue(hasXCAPCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("XCAP"));
            } else {
                Assert.assertFalse(hasXCAPCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("XCAP"));
            }
        } else {
            Assert.assertFalse(hasXCAPCapability);
            Assert.assertFalse(capabilityNames != null && capabilityNames.contains("XCAP"));
        }
    }

    @Test
    public void testHasEIMSCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasEIMSCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_EIMS);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_EIMS)) {
                Assert.assertTrue(hasEIMSCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("EIMS"));
            } else {
                Assert.assertFalse(hasEIMSCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("EIMS"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 15) {
                Assert.assertTrue(hasEIMSCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("EIMS"));
            } else {
                Assert.assertFalse(hasEIMSCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("EIMS"));
            }
        }
    }

    @Test
    public void testHasNotMeteredCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasNotMeteredCapability = networkCapabilitiesCompat.hasNotMeteredCapability();
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null || networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)) {
                Assert.assertTrue(hasNotMeteredCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("NOT_METERED"));
            } else {
                Assert.assertFalse(hasNotMeteredCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("NOT_METERED"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnected() || !connectivityManager.isActiveNetworkMetered()) {
                Assert.assertTrue(hasNotMeteredCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("NOT_METERED"));
            } else {
                Assert.assertFalse(hasNotMeteredCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("NOT_METERED"));
            }
        }
    }

    @Test
    public void testHasMeteredCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        if (networkCapabilitiesCompat.hasNotMeteredCapability()) {
            Assert.assertFalse(networkCapabilitiesCompat.hasMeteredCapability());
        } else {
            Assert.assertTrue(networkCapabilitiesCompat.hasMeteredCapability());
        }
    }

    @Test
    public void testHasInternetCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasInternetCapability = networkCapabilitiesCompat.hasInternetCapability();
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                Assert.assertTrue(hasInternetCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("INTERNET"));
            } else {
                Assert.assertFalse(hasInternetCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("INTERNET"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                Assert.assertTrue(hasInternetCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("INTERNET"));
            } else {
                Assert.assertFalse(hasInternetCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("INTERNET"));
            }
        }
    }

    @Test
    public void testHasNotRestrictedCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasNotRestrictedCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_RESTRICTED);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null || networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)) {
                Assert.assertTrue(hasNotRestrictedCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("NOT_RESTRICTED"));
            } else {
                Assert.assertFalse(hasNotRestrictedCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("NOT_RESTRICTED"));
            }
        } else {
            Assert.assertTrue(hasNotRestrictedCapability);
            Assert.assertTrue(capabilityNames != null && capabilityNames.contains("NOT_RESTRICTED"));
        }
    }

    @Test
    public void testHasTrustedCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasTrustedCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_TRUSTED);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null || networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_TRUSTED)) {
                Assert.assertTrue(hasTrustedCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("TRUSTED"));
            } else {
                Assert.assertFalse(hasTrustedCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("TRUSTED"));
            }
        } else {
            Assert.assertTrue(hasTrustedCapability);
            Assert.assertTrue(capabilityNames != null && capabilityNames.contains("TRUSTED"));
        }
    }

    @Test
    public void testHasValidatedCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasValidatedCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_VALIDATED);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                Assert.assertTrue(hasValidatedCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("VALIDATED"));
            } else {
                Assert.assertFalse(hasValidatedCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("VALIDATED"));
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                Assert.assertTrue(hasValidatedCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("VALIDATED"));
            } else {
                Assert.assertFalse(hasValidatedCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("VALIDATED"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                Assert.assertTrue(hasValidatedCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("VALIDATED"));
            } else {
                Assert.assertFalse(hasValidatedCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("VALIDATED"));
            }
        }
    }

    @Test
    public void testHasCaptivePortalCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasCaptivePortalCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_CAPTIVE_PORTAL);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_CAPTIVE_PORTAL)) {
                Assert.assertTrue(hasCaptivePortalCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("CAPTIVE_PORTAL"));
            } else {
                Assert.assertFalse(hasCaptivePortalCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("CAPTIVE_PORTAL"));
            }
        } else {
            Assert.assertFalse(hasCaptivePortalCapability);
            Assert.assertFalse(capabilityNames != null && capabilityNames.contains("CAPTIVE_PORTAL"));
        }
    }

    @Test
    public void testHasNotRoamingCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasNotRoamingCapability = networkCapabilitiesCompat.hasNotRoamingCapability();
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null || networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_ROAMING)) {
                Assert.assertTrue(hasNotRoamingCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("NOT_ROAMING"));
            } else {
                Assert.assertFalse(hasNotRoamingCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("NOT_ROAMING"));
            }
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isRoaming()) {
                Assert.assertTrue(hasNotRoamingCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("NOT_ROAMING"));
            } else {
                Assert.assertFalse(hasNotRoamingCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("NOT_ROAMING"));
            }
        }
    }

    @Test
    public void testHasRoamingCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        if (networkCapabilitiesCompat.hasNotRoamingCapability()) {
            Assert.assertFalse(networkCapabilitiesCompat.hasRoamingCapability());
        } else {
            Assert.assertTrue(networkCapabilitiesCompat.hasRoamingCapability());
        }
    }

    @Test
    public void testHasForegroundCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasForegroundCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_FOREGROUND);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null || networkCapabilities.hasCapability(NetCapability.NET_CAPABILITY_FOREGROUND)) {
                Assert.assertTrue(hasForegroundCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("FOREGROUND"));
            } else {
                Assert.assertFalse(hasForegroundCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("FOREGROUND"));
            }
        } else {
            Assert.assertTrue(hasForegroundCapability);
            Assert.assertTrue(capabilityNames != null && capabilityNames.contains("FOREGROUND"));
        }
    }

    @Test
    public void testHasNotCongestedCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasNotCongestedCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_CONGESTED);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null || networkCapabilities.hasCapability(NetCapability.NET_CAPABILITY_NOT_CONGESTED)) {
                Assert.assertTrue(hasNotCongestedCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("NOT_CONGESTED"));
            } else {
                Assert.assertFalse(hasNotCongestedCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("NOT_CONGESTED"));
            }
        } else {
            Assert.assertTrue(hasNotCongestedCapability);
            Assert.assertTrue(capabilityNames != null && capabilityNames.contains("NOT_CONGESTED"));
        }
    }

    @Test
    public void testHasNotSuspendedCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasNotSuspendedCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_SUSPENDED);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null || networkCapabilities.hasCapability(NetCapability.NET_CAPABILITY_NOT_SUSPENDED)) {
                Assert.assertTrue(hasNotSuspendedCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("NOT_SUSPENDED"));
            } else {
                Assert.assertFalse(hasNotSuspendedCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("NOT_SUSPENDED"));
            }
        } else {
            Assert.assertTrue(hasNotSuspendedCapability);
            Assert.assertTrue(capabilityNames != null && capabilityNames.contains("NOT_SUSPENDED"));
        }
    }

    @Test
    public void testHasMCXCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasMcxCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_MCX);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetCapability.NET_CAPABILITY_MCX)) {
                Assert.assertTrue(hasMcxCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("MCX"));
            } else {
                Assert.assertFalse(hasMcxCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("MCX"));
            }
        } else {
            Assert.assertFalse(hasMcxCapability);
            Assert.assertFalse(capabilityNames != null && capabilityNames.contains("MCX"));
        }
    }

    @Test
    public void testHasTemporarilyNotMeteredCapability() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasTemporarilyNotMeteredCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_TEMPORARILY_NOT_METERED);
        String capabilityNames = networkCapabilitiesCompat.getCapabilityNames();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(NetCapability.NET_CAPABILITY_TEMPORARILY_NOT_METERED)) {
                Assert.assertTrue(hasTemporarilyNotMeteredCapability);
                Assert.assertTrue(capabilityNames != null && capabilityNames.contains("TEMPORARILY_NOT_METERED"));
            } else {
                Assert.assertFalse(hasTemporarilyNotMeteredCapability);
                Assert.assertFalse(capabilityNames != null && capabilityNames.contains("TEMPORARILY_NOT_METERED"));
            }
        } else {
            Assert.assertFalse(hasTemporarilyNotMeteredCapability);
            Assert.assertFalse(capabilityNames != null && capabilityNames.contains("TEMPORARILY_NOT_METERED"));
        }
    }

    @Test
    public void testGetTransportNames() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasTransport = networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_CELLULAR)
                || networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_WIFI)
                || networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_BLUETOOTH)
                || networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_ETHERNET)
                || networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_VPN)
                || networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_WIFI_AWARE)
                || networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_LOWPAN)
                || networkCapabilitiesCompat.hasTransport(7);
        if (hasTransport) {
            Assert.assertNotNull(networkCapabilitiesCompat.getTransportNames());
        } else {
            Assert.assertNull(networkCapabilitiesCompat.getTransportNames());
        }
    }

    @Test
    public void testGetCapabilityNames() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_MMS)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_SUPL)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_DUN)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_FOTA)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_IMS)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_CBS)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_WIFI_P2P)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_IA)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_RCS)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_XCAP)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_EIMS)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_METERED)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_INTERNET)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_RESTRICTED)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_TRUSTED)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_VPN)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_VALIDATED)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_CAPTIVE_PORTAL)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_ROAMING)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_FOREGROUND)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_CONGESTED)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_SUSPENDED)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_MCX)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_TEMPORARILY_NOT_METERED);
        if (hasCapability) {
            Assert.assertNotNull(networkCapabilitiesCompat.getCapabilityNames());
        } else {
            Assert.assertNull(networkCapabilitiesCompat.getCapabilityNames());
        }
    }

    @Test
    public void testToString() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        NetworkCapabilitiesCompat networkCapabilitiesCompat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? new NetworkCapabilities21Impl(context) : new NetworkCapabilities1Impl(context);

        boolean hasTransport = networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_CELLULAR)
                || networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_WIFI)
                || networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_BLUETOOTH)
                || networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_ETHERNET)
                || networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_VPN)
                || networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_WIFI_AWARE)
                || networkCapabilitiesCompat.hasTransport(Transport.TRANSPORT_LOWPAN)
                || networkCapabilitiesCompat.hasTransport(7);
        boolean hasCapability = networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_MMS)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_SUPL)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_DUN)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_FOTA)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_IMS)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_CBS)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_WIFI_P2P)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_IA)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_RCS)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_XCAP)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_EIMS)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_METERED)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_INTERNET)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_RESTRICTED)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_TRUSTED)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_VPN)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_VALIDATED)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_CAPTIVE_PORTAL)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_ROAMING)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_FOREGROUND)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_CONGESTED)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_NOT_SUSPENDED)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_MCX)
                || networkCapabilitiesCompat.hasCapability(NetCapability.NET_CAPABILITY_TEMPORARILY_NOT_METERED);
        String toString = networkCapabilitiesCompat.toString();
        Assert.assertNotNull(toString);
        Assert.assertTrue(toString.startsWith("["));
        Assert.assertTrue(toString.endsWith("]"));
        if (hasTransport) {
            Assert.assertTrue(toString.contains(" Transports: "));
        } else {
            Assert.assertFalse(toString.contains(" Transports: "));
        }
        if (hasCapability) {
            Assert.assertTrue(toString.contains(" Capabilities: "));
        } else {
            Assert.assertFalse(toString.contains(" Capabilities: "));
        }
    }
}