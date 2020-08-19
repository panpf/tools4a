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
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import com.github.panpf.tools4a.systemproperties.SystemPropertiesx;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Networkx {

    private Networkx() {
    }

    /**
     * Get network state
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static NetworkState getState(@NonNull Context context) {
        return NetworkState.get(context);
    }

    /**
     * Return true if any type of network is currently available
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isActivated(@NonNull Context context) {
        return NetworkState.get(context).isActivated();
    }

    /**
     * Return true if the currently available network type is WIFI
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isWifiActivated(@NonNull Context context) {
        return NetworkState.get(context).isWifiActivated();
    }

    /**
     * Return true if the currently available network type is not metered WIFI
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isNoMeteredWifiActivated(@NonNull Context context) {
        return NetworkState.get(context).isNoMeteredWifiActivated();
    }

    /**
     * Return true if the type of currently available network is mobile data
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isMobileActivated(@NonNull Context context) {
        return NetworkState.get(context).isMobileActivated();
    }

    /**
     * Return true if the currently available network type is Bluetooth
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isBluetoothActivated(@NonNull Context context) {
        return NetworkState.get(context).isBluetoothActivated();
    }

    /**
     * Return true if the currently available network type is VPN
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isVPNActivated(@NonNull Context context) {
        return NetworkState.get(context).isVPNActivated();
    }

    /**
     * Return true if the currently available network is metered
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isMetered(@NonNull Context context) {
        return NetworkState.get(context).isMetered();
    }

    /**
     * Return true if the type of currently available network is roaming
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isRoaming(@NonNull Context context) {
        return NetworkState.get(context).isRoaming();
    }

    /**
     * Return true if the currently available network is automatically transferred after a failure
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isFailover(@NonNull Context context) {
        return NetworkState.get(context).isFailover();
    }

    /**
     * Get the type of network currently available
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static int getType(@NonNull Context context) {
        return NetworkState.get(context).getType();
    }

    /**
     * Get the name of the type of currently available network
     */
    @NonNull
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static String getTypeName(@NonNull Context context) {
        return NetworkState.get(context).getTypeName();
    }

    /**
     * Get the name of the subtype of the currently available network
     */
    @NonNull
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static String getSubtypeName(@NonNull Context context) {
        return NetworkState.get(context).getSubtypeName();
    }

    /**
     * Get additional information about the currently available network
     */
    @NonNull
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static String getExtraInfo(@NonNull Context context) {
        return NetworkState.get(context).getExtraInfo();
    }

    /**
     * Get information about currently available networks
     */
    @Nullable
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static NetworkInfo getNetworkInfo(@NonNull Context context) {
        return NetworkState.get(context).getNetworkInfo();
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
        return state == WifiManager.WIFI_STATE_ENABLED || state == WifiManager.WIFI_STATE_ENABLING;
    }

    /**
     * Turn Wi-Fi on or off
     */
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE})
    public static boolean setWifiEnabled(@NonNull Context context, boolean enable) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiManager != null && wifiManager.setWifiEnabled(enable);
    }

    /**
     * Return true if mobile network is turned on
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isMobileEnabled(@NonNull Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * Turn mobile network on or off
     */
    @RequiresPermission(Manifest.permission.CHANGE_NETWORK_STATE)
    public static boolean setMobileEnabled(@NonNull Context context, boolean enabled) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Method method = getDeclaredMethodRecursive(connectivityManager.getClass(), "setMobileDataEnabled", Boolean.class);
            method.setAccessible(true);
            return (boolean) method.invoke(connectivityManager, enabled);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gateway
     */
    @NonNull
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public static String getGateway(@NonNull Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifiManager != null ? wifiManager.getDhcpInfo() : null;
        if (dhcpInfo == null) return "";
        long longIPV4Value = dhcpInfo.gateway;
        return String.valueOf((int) (longIPV4Value & 0xff)) +
                '.' +
                (int) ((longIPV4Value >> 8) & 0xff) +
                '.' +
                (int) ((longIPV4Value >> 16) & 0xff) +
                '.' +
                (int) ((longIPV4Value >> 24) & 0xff);
    }

    /**
     * DNS1
     */
    @SuppressLint("PrivateApi")
    @NonNull
    public static String getDNS1() {
        return SystemPropertiesx.get("net.dns1");
    }

    /**
     * DNS2
     */
    @SuppressLint("PrivateApi")
    @NonNull
    public static String getDNS2() {
        return SystemPropertiesx.get("net.dns2");
    }

    /**
     * Get the declared method with the specified name from the specified class
     */
    @NonNull
    private static Method getDeclaredMethodRecursive(@NonNull Class<?> clazz, @SuppressWarnings("SameParameterValue") @NonNull String methodName,
                                                     @Nullable Class<?>... params) throws NoSuchMethodException {
        Method method = null;

        Class<?> currentClazz = clazz;
        while (method == null && currentClazz != null) {
            try {
                method = currentClazz.getDeclaredMethod(methodName, params);
            } catch (NoSuchMethodException ignored) {
            }

            if (method == null) {
                currentClazz = currentClazz.getSuperclass();
            }
        }

        if (method == null) {
            throw new NoSuchMethodException(String.format("No such method by name '%s' and params '%s' in class '%s' and its parent class", methodName, Arrays.toString(params), clazz.getName()));
        } else {
            return method;
        }
    }
}