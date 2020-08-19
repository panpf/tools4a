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
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

/**
 * A tool class that determines the state of the network, which can satisfy whether there is a network and what type of network at one time.
 */
public class NetworkState {

    @NonNull
    private ConnectivityManager connectivity;

    @Nullable
    private NetworkInfo networkInfo;

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private NetworkState(@NonNull Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null)
            throw new IllegalArgumentException("Not found service '" + Context.CONNECTIVITY_SERVICE + "'");
        this.connectivity = connectivity;
        this.networkInfo = this.connectivity.getActiveNetworkInfo();
    }

    /**
     * Get network state
     */
    @NonNull
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static NetworkState get(@NonNull Context context) {
        return new NetworkState(context);
    }

    /**
     * Return true if any type of network is currently available
     */
    public boolean isActivated() {
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * Return true if the currently available network type is WIFI
     */
    public boolean isWifiActivated() {
        return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * Return true if the currently available network type is not metered WIFI
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public boolean isNoMeteredWifiActivated() {
        return isWifiActivated() && !isMetered();
    }

    /**
     * Return true if the type of currently available network is mobile data
     */
    public boolean isMobileActivated() {
        return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * Return true if the currently available network type is Bluetooth
     */
    public boolean isBluetoothActivated() {
        return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_BLUETOOTH;
    }

    /**
     * Return true if the currently available network type is VPN
     */
    public boolean isVPNActivated() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && networkInfo != null
                && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_VPN;
    }

    /**
     * Return true if the currently available network is metered
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public boolean isMetered() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && connectivity.isActiveNetworkMetered();
    }

    /**
     * Return true if the type of currently available network is roaming
     */
    public boolean isRoaming() {
        return networkInfo != null && networkInfo.isRoaming();
    }

    /**
     * Return true if the currently available network is automatically transferred after a failure
     */
    public boolean isFailover() {
        return networkInfo != null && networkInfo.isFailover();
    }

    /**
     * Get the type of network currently available
     */
    public int getType() {
        return networkInfo != null ? networkInfo.getType() : -1;
    }

    /**
     * Get the name of the type of currently available network
     */
    @NonNull
    public String getTypeName() {
        String value = networkInfo != null ? networkInfo.getTypeName() : null;
        return value != null && !value.isEmpty() ? value : "unknown";
    }

    /**
     * Get the name of the subtype of the currently available network
     */
    @NonNull
    public String getSubtypeName() {
        String value = networkInfo != null ? networkInfo.getSubtypeName() : null;
        return value != null && !value.isEmpty() ? value : "unknown";
    }

    /**
     * Get additional information about the currently available network
     */
    @NonNull
    public String getExtraInfo() {
        String value = networkInfo != null ? networkInfo.getExtraInfo() : null;
        return value != null && !value.isEmpty() ? value : "unknown";
    }

    /**
     * Get information about currently available networks
     */
    @Nullable
    public NetworkInfo getNetworkInfo() {
        return networkInfo;
    }

    /**
     * Get network connection
     */
    @NonNull
    public ConnectivityManager getConnectivity() {
        return connectivity;
    }
}
