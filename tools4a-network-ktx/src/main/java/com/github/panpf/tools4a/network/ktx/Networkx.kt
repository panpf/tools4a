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

@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4a.network.ktx

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.github.panpf.tools4a.network.NetworkCapabilitiesCompat
import com.github.panpf.tools4a.network.Networkx

/**
 * Get the currently active network
 *
 * @receiver [ConnectivityManager]. Network connection manager
 * @return [Network]. Describe network related information
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun ConnectivityManager.activeNetworkCompat(): Network? = Networkx.activeNetwork(this)

/**
 * Get the currently active network
 *
 * @receiver [Context]. App context
 * @return [Network]. Describe network related information
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.activeNetwork(): Network? = Networkx.activeNetwork(this)

/**
 * Return NetworkCapabilitiesCompat, provides NetworkCapabilities support for versions below L
 *
 * @receiver [Context]. App context
 * @return [NetworkCapabilitiesCompat]. Provides NetworkCapabilities support for versions below L
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.networkCapabilitiesCompat(): NetworkCapabilitiesCompat = Networkx.networkCapabilitiesCompat(this)


/**
 * Return true if any type of network is currently connected
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isNetworkConnected(): Boolean = Networkx.isConnected(this)

/**
 * Indicates that currently connected network has wi-fi transport
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isWifiNetworkConnected(): Boolean = Networkx.isWifiConnected(this)

/**
 * Indicates that currently connected network has cellular transport
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isCellularNetworkConnected(): Boolean = Networkx.isCellularConnected(this)


/**
 * Indicates that currently connected network is unmetered.
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isNetworkNotMetered(): Boolean = Networkx.isNotMetered(this)

/**
 * Indicates that currently connected network is metered.
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isNetworkMetered(): Boolean = Networkx.isMetered(this)

/**
 * Indicates that currently connected network is not roaming.
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isNetworkNotRoaming(): Boolean = Networkx.isNotRoaming(this)

/**
 * Indicates that currently connected network is roaming.
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isNetworkRoaming(): Boolean = Networkx.isRoaming(this)


/**
 * Returns the names of the current network all transport,
 * for example: 'CELLULAR&WIFI&BLUETOOTH&ETHERNET&VPN&WIFI_AWARE&LOWPAN&TEST'
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.getNetworkTransportNames(): String? = Networkx.getTransportNames(this)

/**
 * Returns the names of the current network all capability,
 * for example: 'INTERNET&TRUSTED&NOT_METERED&NOT_VPN&VALIDATED&...'
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.getNetworkCapabilityNames(): String? = Networkx.getCapabilityNames(this)

/**
 * Get the status of Wi-Fi
 *
 * @return The value is one of WIFI_STATE_ENABLED, WIFI_STATE_ENABLING, WIFI_STATE_DISABLED, WIFI_STATE_DISABLING, WIFI_STATE_UNKNOWN in WifiManager.
 */
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
inline fun Context.getWifiState(): Int = Networkx.getWifiState(this)

/**
 * Return true if Wi-Fi is turned on
 */
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
inline fun Context.isWifiEnabled(): Boolean = Networkx.isWifiEnabled(this)

/**
 * Turn Wi-Fi on or off
 */
@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Starting with Build.VERSION_CODES#Q, applications are not allowed to enable/disable Wi-Fi.")
@RequiresPermission(anyOf = [(Manifest.permission.ACCESS_WIFI_STATE), (Manifest.permission.CHANGE_WIFI_STATE)])
inline fun Context.setWifiEnabled(enable: Boolean): Boolean = Networkx.setWifiEnabled(this, enable)

/**
 * Return wi-fi gateway
 */
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
inline fun Context.getWifiGateway(): String? = Networkx.getWifiGateway(this)