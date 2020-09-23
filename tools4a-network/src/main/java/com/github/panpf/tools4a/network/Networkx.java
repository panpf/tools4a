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

package com.github.panpf.tools4a.network;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.Network;
import android.net.wifi.WifiManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

import com.github.panpf.tools4a.systemproperties.SystemPropertiesx;

public class Networkx {

    private Networkx() {
    }


    /**
     * Get network connection manager
     *
     * @param context {@link Context}. App context
     * @return {@link ConnectivityManager}. Network connection manager
     */
    @NonNull
    public static ConnectivityManager connectivity(@NonNull Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            throw new IllegalArgumentException("Not found service '" + Context.CONNECTIVITY_SERVICE + "'");
        }
        return connectivity;
    }

    /**
     * Get the currently active network
     *
     * @param connectivity {@link ConnectivityManager}. Network connection manager
     * @return {@link Network}. Describe network related information
     */
    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public static Network activeNetwork(@NonNull ConnectivityManager connectivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return connectivity.getActiveNetwork();
        } else {
            Network[] networks = connectivity.getAllNetworks();
            return networks != null && networks.length > 0 ? networks[0] : null;
        }
    }

    /**
     * Get the currently active network
     *
     * @param context {@link Context}. App context
     * @return {@link Network}. Describe network related information
     */
    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public static Network activeNetwork(@NonNull Context context) {
        ConnectivityManager connectivity = connectivity(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return connectivity.getActiveNetwork();
        } else {
            Network[] networks = connectivity.getAllNetworks();
            return networks != null && networks.length > 0 ? networks[0] : null;
        }
    }

    /**
     * Return NetworkCapabilitiesCompat, provides NetworkCapabilities support for versions below L
     *
     * @param context {@link Context}. App context
     * @return {@link NetworkCapabilitiesCompat}. Provides NetworkCapabilities support for versions below L
     */
    @NonNull
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static NetworkCapabilitiesCompat networkCapabilitiesCompat(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new NetworkCapabilities21Impl(context);
        } else {
            return new NetworkCapabilities1Impl(context);
        }
    }


    /**
     * Return true if any type of network is currently connected
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isConnected(@NonNull Context context) {
        return networkCapabilitiesCompat(context).isConnected();
    }

    /**
     * Indicates that currently connected network has wi-fi transport
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isWifiConnected(@NonNull Context context) {
        return networkCapabilitiesCompat(context).isWifiConnected();
    }

    /**
     * Indicates that currently connected network has cellular transport
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isCellularConnected(@NonNull Context context) {
        return networkCapabilitiesCompat(context).isCellularConnected();
    }


    /**
     * Indicates that currently connected network is unmetered.
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isNotMetered(@NonNull Context context) {
        return networkCapabilitiesCompat(context).hasNotMeteredCapability();
    }

    /**
     * Indicates that currently connected network is metered.
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isMetered(@NonNull Context context) {
        return networkCapabilitiesCompat(context).hasMeteredCapability();
    }

    /**
     * Indicates that currently connected network is not roaming.
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isNotRoaming(@NonNull Context context) {
        return networkCapabilitiesCompat(context).hasNotRoamingCapability();
    }

    /**
     * Indicates that currently connected network is roaming.
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isRoaming(@NonNull Context context) {
        return networkCapabilitiesCompat(context).hasRoamingCapability();
    }


    /**
     * Returns the names of the current network all transport,
     * for example: 'CELLULAR&WIFI&BLUETOOTH&ETHERNET&VPN&WIFI_AWARE&LOWPAN&TEST'
     */
    @Nullable
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static String getTransportNames(@NonNull Context context) {
        return networkCapabilitiesCompat(context).getTransportNames();
    }

    /**
     * Returns the names of the current network all capability,
     * for example: 'INTERNET&TRUSTED&NOT_METERED&NOT_VPN&VALIDATED&...'
     */
    @Nullable
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static String getCapabilityNames(@NonNull Context context) {
        return networkCapabilitiesCompat(context).getCapabilityNames();
    }


    /**
     * Get the status of Wi-Fi
     *
     * @return The value is one of WIFI_STATE_ENABLED, WIFI_STATE_ENABLING, WIFI_STATE_DISABLED, WIFI_STATE_DISABLING, WIFI_STATE_UNKNOWN in WifiManager.
     */
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public static int getWifiState(@NonNull Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiManager != null ? wifiManager.getWifiState() : WifiManager.WIFI_STATE_UNKNOWN;
    }

    /**
     * Return true if Wi-Fi is turned on
     */
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public static boolean isWifiEnabled(@NonNull Context context) {
        int state = getWifiState(context);
        return state == WifiManager.WIFI_STATE_ENABLED;
    }

    /**
     * Turn Wi-Fi on or off
     *
     * @deprecated Starting with Build.VERSION_CODES#Q, applications are not allowed to
     * enable/disable Wi-Fi.
     */
    @Deprecated
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE})
    public static boolean setWifiEnabled(@NonNull Context context, boolean enable) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiManager != null && wifiManager.setWifiEnabled(enable);
    }


    /**
     * Return wi-fi gateway
     */
    @Nullable
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public static String getWifiGateway(@NonNull Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifiManager != null ? wifiManager.getDhcpInfo() : null;
        long longIPV4Value = dhcpInfo != null ? dhcpInfo.gateway : 0;
        if (longIPV4Value != 0) {
            return String.valueOf((int) (longIPV4Value & 0xff)) +
                    '.' +
                    (int) ((longIPV4Value >> 8) & 0xff) +
                    '.' +
                    (int) ((longIPV4Value >> 16) & 0xff) +
                    '.' +
                    (int) ((longIPV4Value >> 24) & 0xff);
        } else {
            return null;
        }
    }

    /**
     * DNS1
     */
    @Nullable
    public static String getDNS1() {
        String dns1 = SystemPropertiesx.get("net.dns1");
        return !dns1.isEmpty() ? dns1 : null;
    }

    /**
     * DNS2
     */
    @Nullable
    public static String getDNS2() {
        String dns2 = SystemPropertiesx.get("net.dns2");
        return !dns2.isEmpty() ? dns2 : null;
    }
}