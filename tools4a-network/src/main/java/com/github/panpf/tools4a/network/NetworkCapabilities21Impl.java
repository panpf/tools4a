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

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkCapabilities21Impl implements NetworkCapabilitiesCompat {

    @NonNull
    private final ConnectivityManager connectivity;
    @Nullable
    private final Network network;
    @Nullable
    private final NetworkCapabilities networkCapabilities;

    public NetworkCapabilities21Impl(@NonNull ConnectivityManager connectivity, @Nullable Network network,
                                     @Nullable NetworkCapabilities networkCapabilities) {
        this.connectivity = connectivity;
        this.network = network;
        this.networkCapabilities = networkCapabilities;
    }

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public NetworkCapabilities21Impl(@NonNull ConnectivityManager connectivity, @Nullable Network network) {
        this(connectivity, network, network != null ? connectivity.getNetworkCapabilities(network) : null);
    }

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public NetworkCapabilities21Impl(@NonNull Context context) {
        this(Networkx.connectivity(context), Networkx.activeNetwork(context));
    }


    @Override
    public boolean isConnected() {
        return hasInternetCapability();
    }

    @Override
    public boolean isWifiConnected() {
        return hasInternetCapability() && hasWifiTransport();
    }

    @Override
    public boolean isCellularConnected() {
        return hasInternetCapability() && hasCellularTransport();
    }


    @Override
    public boolean hasTransport(@Transport int transportType) {
        if (transportType == Transport.TRANSPORT_WIFI_AWARE && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return false;
        } else if (transportType == Transport.TRANSPORT_LOWPAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1) {
            return false;
        } else {
            return networkCapabilities != null && networkCapabilities.hasTransport(transportType);
        }
    }

    @Override
    public boolean hasCellularTransport() {
        return hasTransport(Transport.TRANSPORT_CELLULAR);
    }

    @Override
    public boolean hasWifiTransport() {
        return hasTransport(Transport.TRANSPORT_WIFI);
    }


    @Override
    @SuppressLint("SwitchIntDef")
    @SuppressWarnings("DuplicateBranchesInSwitch")
    public boolean hasCapability(@NetCapability int capability) {
        switch (capability) {
            case NetCapability.NET_CAPABILITY_MMS:
                return networkCapabilities != null && networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_SUPL:
                return networkCapabilities != null && networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_DUN:
                return networkCapabilities != null && networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_FOTA:
                return networkCapabilities != null && networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_IMS:
                return networkCapabilities != null && networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_CBS:
                return networkCapabilities != null && networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_WIFI_P2P:
                return networkCapabilities != null && networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_IA:
                return networkCapabilities != null && networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_RCS:
                return networkCapabilities != null && networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_XCAP:
                return networkCapabilities != null && networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_EIMS:
                return networkCapabilities != null && networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_NOT_METERED:
                return networkCapabilities == null || networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_INTERNET:
                return networkCapabilities != null && networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_NOT_RESTRICTED:
                return networkCapabilities == null || networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_TRUSTED:
                return networkCapabilities == null || networkCapabilities.hasCapability(capability);
            case NetCapability.NET_CAPABILITY_VALIDATED:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return networkCapabilities != null && networkCapabilities.hasCapability(capability);
                } else {
                    return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
                }
            case NetCapability.NET_CAPABILITY_CAPTIVE_PORTAL:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return networkCapabilities != null && networkCapabilities.hasCapability(capability);
                } else {
                    return false;
                }
            case NetCapability.NET_CAPABILITY_NOT_ROAMING:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    return networkCapabilities == null || networkCapabilities.hasCapability(capability);
                } else {
                    //noinspection deprecation
                    @SuppressLint("MissingPermission")
                    NetworkInfo networkInfo = connectivity.getNetworkInfo(network);
                    //noinspection deprecation
                    return networkInfo == null || !networkInfo.isRoaming();
                }
            case NetCapability.NET_CAPABILITY_FOREGROUND:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    return networkCapabilities == null || networkCapabilities.hasCapability(capability);
                } else {
                    return true;
                }
            case NetCapability.NET_CAPABILITY_NOT_CONGESTED:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    return networkCapabilities == null || networkCapabilities.hasCapability(capability);
                } else {
                    return true;
                }
            case NetCapability.NET_CAPABILITY_NOT_SUSPENDED:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    return networkCapabilities == null || networkCapabilities.hasCapability(capability);
                } else {
                    return true;
                }
            case NetCapability.NET_CAPABILITY_MCX:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    return networkCapabilities != null && networkCapabilities.hasCapability(capability);
                } else {
                    return false;
                }
            case NetCapability.NET_CAPABILITY_TEMPORARILY_NOT_METERED:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    return networkCapabilities != null && networkCapabilities.hasCapability(capability);
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    @Override
    public boolean hasInternetCapability() {
        return hasCapability(NetCapability.NET_CAPABILITY_INTERNET);
    }

    @Override
    public boolean hasNotMeteredCapability() {
        return hasCapability(NetCapability.NET_CAPABILITY_NOT_METERED);
    }

    @Override
    public boolean hasMeteredCapability() {
        return !hasNotMeteredCapability();
    }

    @Override
    public boolean hasNotRoamingCapability() {
        return hasCapability(NetCapability.NET_CAPABILITY_NOT_ROAMING);
    }

    @Override
    public boolean hasRoamingCapability() {
        return !hasNotRoamingCapability();
    }


    @Nullable
    @Override
    public String getTransportNames() {
        StringBuilder builder = new StringBuilder();
        if (hasTransport(Transport.TRANSPORT_CELLULAR)) builder.append("CELLULAR|");
        if (hasTransport(Transport.TRANSPORT_WIFI)) builder.append("WIFI|");
        if (hasTransport(Transport.TRANSPORT_BLUETOOTH)) builder.append("BLUETOOTH|");
        if (hasTransport(Transport.TRANSPORT_ETHERNET)) builder.append("ETHERNET|");
        if (hasTransport(Transport.TRANSPORT_VPN)) builder.append("VPN|");
        if (hasTransport(Transport.TRANSPORT_WIFI_AWARE)) builder.append("WIFI_AWARE|");
        if (hasTransport(Transport.TRANSPORT_LOWPAN)) builder.append("LOWPAN|");
        if (hasTransport(7)) builder.append("TEST|");
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public String getCapabilityNames() {
        StringBuilder builder = new StringBuilder();
        if (hasCapability(NetCapability.NET_CAPABILITY_MMS)) builder.append("MMS&");
        if (hasCapability(NetCapability.NET_CAPABILITY_SUPL)) builder.append("SUPL&");
        if (hasCapability(NetCapability.NET_CAPABILITY_DUN)) builder.append("DUN&");
        if (hasCapability(NetCapability.NET_CAPABILITY_FOTA)) builder.append("FOTA&");
        if (hasCapability(NetCapability.NET_CAPABILITY_IMS)) builder.append("IMS&");
        if (hasCapability(NetCapability.NET_CAPABILITY_CBS)) builder.append("CBS&");
        if (hasCapability(NetCapability.NET_CAPABILITY_WIFI_P2P)) builder.append("WIFI_P2P&");
        if (hasCapability(NetCapability.NET_CAPABILITY_IA)) builder.append("IA&");
        if (hasCapability(NetCapability.NET_CAPABILITY_RCS)) builder.append("RCS&");
        if (hasCapability(NetCapability.NET_CAPABILITY_XCAP)) builder.append("XCAP&");
        if (hasCapability(NetCapability.NET_CAPABILITY_EIMS)) builder.append("EIMS&");
        if (hasCapability(NetCapability.NET_CAPABILITY_NOT_METERED)) builder.append("NOT_METERED&");
        if (hasCapability(NetCapability.NET_CAPABILITY_INTERNET)) builder.append("INTERNET&");
        if (hasCapability(NetCapability.NET_CAPABILITY_NOT_RESTRICTED))
            builder.append("NOT_RESTRICTED&");
        if (hasCapability(NetCapability.NET_CAPABILITY_TRUSTED)) builder.append("TRUSTED&");
        if (hasCapability(NetCapability.NET_CAPABILITY_NOT_VPN)) builder.append("NOT_VPN&");
        if (hasCapability(NetCapability.NET_CAPABILITY_VALIDATED)) builder.append("VALIDATED&");
        if (hasCapability(NetCapability.NET_CAPABILITY_CAPTIVE_PORTAL))
            builder.append("CAPTIVE_PORTAL&");
        if (hasCapability(NetCapability.NET_CAPABILITY_NOT_ROAMING)) builder.append("NOT_ROAMING&");
        if (hasCapability(NetCapability.NET_CAPABILITY_FOREGROUND)) builder.append("FOREGROUND&");
        if (hasCapability(NetCapability.NET_CAPABILITY_NOT_CONGESTED))
            builder.append("NOT_CONGESTED&");
        if (hasCapability(NetCapability.NET_CAPABILITY_NOT_SUSPENDED))
            builder.append("NOT_SUSPENDED&");
        if (hasCapability(NetCapability.NET_CAPABILITY_MCX)) builder.append("MCX&");
        if (hasCapability(NetCapability.NET_CAPABILITY_TEMPORARILY_NOT_METERED))
            builder.append("TEMPORARILY_NOT_METERED");
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        } else {
            return null;
        }
    }

    @Override
    @NonNull
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("[");
        String transportNames = getTransportNames();
        if (transportNames != null) {
            stringBuilder.append(" Transports: ").append(transportNames);
        }
        String capabilityNames = getCapabilityNames();
        if (capabilityNames != null) {
            stringBuilder.append(" Capabilities: ").append(capabilityNames);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
