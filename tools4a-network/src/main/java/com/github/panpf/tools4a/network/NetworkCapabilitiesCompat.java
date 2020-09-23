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

import androidx.annotation.Nullable;

/**
 * Provides NetworkCapabilities support for versions below L
 */
public interface NetworkCapabilitiesCompat {

    /**
     * Return true if any type of network is currently connected
     */
    boolean isConnected();

    /**
     * Indicates that currently connected network has wi-fi transport
     */
    boolean isWifiConnected();

    /**
     * Indicates that currently connected network has cellular transport
     */
    boolean isCellularConnected();


    /**
     * Tests for the presence of a transport on this instance.
     *
     * @param transportType the transport type to be tested for.
     * @return {@code true} if set on this instance.
     */
    boolean hasTransport(@Transport int transportType);

    /**
     * Indicates this network uses a Cellular transport.
     */
    boolean hasCellularTransport();

    /**
     * Indicates this network uses a Wi-Fi transport.
     */
    boolean hasWifiTransport();


    /**
     * Tests for the presence of a capability on this instance.
     *
     * @param capability the capabilities to be tested for.
     * @return {@code true} if set on this instance.
     */
    boolean hasCapability(@NetCapability int capability);

    /**
     * Indicates that this network should be able to reach the internet.
     */
    boolean hasInternetCapability();

    /**
     * Indicates that this network is unmetered.
     */
    boolean hasNotMeteredCapability();

    /**
     * Indicates that this network is metered.
     */
    boolean hasMeteredCapability();

    /**
     * Indicates that this network is not roaming.
     */
    boolean hasNotRoamingCapability();

    /**
     * Indicates that this network is roaming.
     */
    boolean hasRoamingCapability();


    /**
     * Returns the names of the current network all transport,
     * for example: CELLULAR&#38;WIFI&#38;BLUETOOTH&#38;ETHERNET&#38;VPN&#38;WIFI_AWARE&#38;LOWPAN&#38;TEST'
     */
    @Nullable
    String getTransportNames();

    /**
     * Returns the names of the current network all capability,
     * for example: 'INTERNET&#38;TRUSTED&#38;NOT_METERED&#38;NOT_VPN&#38;VALIDATED&#38;...'
     */
    @Nullable
    String getCapabilityNames();
}
