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
import android.net.NetworkInfo
import androidx.annotation.RequiresPermission
import com.github.panpf.tools4a.network.NetworkState
import com.github.panpf.tools4a.network.Networkx

/**
 * Get network state
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.getNetworkState(): NetworkState = Networkx.getState(this)

/**
 * Return true if any type of network is currently available
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isNetworkActivated(): Boolean = Networkx.isActivated(this)

/**
 * Return true if the currently available network type is WIFI
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isWifiNetworkActivated(): Boolean = Networkx.isWifiActivated(this)

/**
 * Return true if the currently available network type is not metered WIFI
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isNoMeteredWifiNetworkActivated(): Boolean = Networkx.isNoMeteredWifiActivated(this)

/**
 * Return true if the type of currently available network is mobile data
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isMobileNetworkActivated(): Boolean = Networkx.isMobileActivated(this)

/**
 * Return true if the currently available network type is Bluetooth
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isBluetoothNetworkActivated(): Boolean = Networkx.isBluetoothActivated(this)

/**
 * Return true if the currently available network type is VPN
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isVPNNetworkActivated(): Boolean = Networkx.isVPNActivated(this)

/**
 * Return true if the currently available network is metered
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isNetworkMetered(): Boolean = Networkx.isMetered(this)

/**
 * Return true if the type of currently available network is roaming
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isNetworkRoaming(): Boolean = Networkx.isRoaming(this)

/**
 * Return true if the currently available network is automatically transferred after a failure
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isNetworkFailover(): Boolean = Networkx.isFailover(this)

/**
 * Get the type of network currently available
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.getNetworkType(): Int = Networkx.getType(this)

/**
 * Get the name of the type of currently available network
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.getNetworkTypeName(): String = Networkx.getTypeName(this)

/**
 * Get the name of the subtype of the currently available network
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.getNetworkSubtypeName(): String = Networkx.getSubtypeName(this)

/**
 * Get additional information about the currently available network
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.getNetworkExtraInfo(): String = Networkx.getExtraInfo(this)

/**
 * Get information about currently available networks
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.getNetworkInfo(): NetworkInfo? = Networkx.getNetworkInfo(this)

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
inline fun Context.isWifiNetworkEnabled(): Boolean = Networkx.isWifiEnabled(this)

/**
 * Turn Wi-Fi on or off
 */
@RequiresPermission(anyOf = [(Manifest.permission.ACCESS_WIFI_STATE), (Manifest.permission.CHANGE_WIFI_STATE)])
inline fun Context.setWifiNetworkEnabled(enable: Boolean): Boolean = Networkx.setWifiEnabled(this, enable)

/**
 * Return true if mobile network is turned on
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.isMobileNetworkEnabled(): Boolean = Networkx.isMobileEnabled(this)

/**
 * Turn mobile network on or off
 */
@RequiresPermission(Manifest.permission.CHANGE_NETWORK_STATE)
inline fun Context.setMobileNetworkEnabled(enabled: Boolean): Boolean = Networkx.setMobileEnabled(this, enabled)

/**
 * Gateway
 */
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
inline fun Context.getNetworkGateway(): String = Networkx.getGateway(this)