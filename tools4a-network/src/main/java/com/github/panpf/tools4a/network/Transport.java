package com.github.panpf.tools4a.network;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef(value = {
        Transport.TRANSPORT_CELLULAR,
        Transport.TRANSPORT_WIFI,
        Transport.TRANSPORT_BLUETOOTH,
        Transport.TRANSPORT_ETHERNET,
        Transport.TRANSPORT_VPN,
        Transport.TRANSPORT_WIFI_AWARE,
        Transport.TRANSPORT_LOWPAN,
}, open = true)
public @interface Transport {
    /**
     * Indicates this network uses a Cellular transport.
     *
     * @see android.net.NetworkCapabilities#TRANSPORT_CELLULAR
     */
    int TRANSPORT_CELLULAR = 0;

    /**
     * Indicates this network uses a Wi-Fi transport.
     *
     * @see android.net.NetworkCapabilities#TRANSPORT_WIFI
     */
    int TRANSPORT_WIFI = 1;

    /**
     * Indicates this network uses a Bluetooth transport.
     *
     * @see android.net.NetworkCapabilities#TRANSPORT_BLUETOOTH
     */
    int TRANSPORT_BLUETOOTH = 2;

    /**
     * Indicates this network uses an Ethernet transport.
     *
     * @see android.net.NetworkCapabilities#TRANSPORT_ETHERNET
     */
    int TRANSPORT_ETHERNET = 3;

    /**
     * Indicates this network uses a VPN transport.
     *
     * @see android.net.NetworkCapabilities#TRANSPORT_VPN
     */
    int TRANSPORT_VPN = 4;

    /**
     * Indicates this network uses a Wi-Fi Aware transport.
     *
     * @see android.net.NetworkCapabilities#TRANSPORT_WIFI_AWARE
     */
    int TRANSPORT_WIFI_AWARE = 5;

    /**
     * Indicates this network uses a LoWPAN transport.
     *
     * @see android.net.NetworkCapabilities#TRANSPORT_LOWPAN
     */
    int TRANSPORT_LOWPAN = 6;
}