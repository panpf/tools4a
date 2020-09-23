package com.github.panpf.tools4a.network;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef(value = {
        NetCapability.NET_CAPABILITY_MMS,
        NetCapability.NET_CAPABILITY_SUPL,
        NetCapability.NET_CAPABILITY_DUN,
        NetCapability.NET_CAPABILITY_FOTA,
        NetCapability.NET_CAPABILITY_IMS,
        NetCapability.NET_CAPABILITY_CBS,
        NetCapability.NET_CAPABILITY_WIFI_P2P,
        NetCapability.NET_CAPABILITY_IA,
        NetCapability.NET_CAPABILITY_RCS,
        NetCapability.NET_CAPABILITY_XCAP,
        NetCapability.NET_CAPABILITY_EIMS,
        NetCapability.NET_CAPABILITY_NOT_METERED,
        NetCapability.NET_CAPABILITY_INTERNET,
        NetCapability.NET_CAPABILITY_NOT_RESTRICTED,
        NetCapability.NET_CAPABILITY_TRUSTED,
        NetCapability.NET_CAPABILITY_NOT_VPN,
        NetCapability.NET_CAPABILITY_VALIDATED,
        NetCapability.NET_CAPABILITY_CAPTIVE_PORTAL,
        NetCapability.NET_CAPABILITY_NOT_ROAMING,
        NetCapability.NET_CAPABILITY_FOREGROUND,
        NetCapability.NET_CAPABILITY_NOT_CONGESTED,
        NetCapability.NET_CAPABILITY_NOT_SUSPENDED,
        NetCapability.NET_CAPABILITY_MCX,
        NetCapability.NET_CAPABILITY_TEMPORARILY_NOT_METERED,
}, open = true)
public @interface NetCapability {
    /**
     * Indicates this is a network that has the ability to reach the
     * carrier's MMSC for sending and receiving MMS messages.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_MMS
     */
    int NET_CAPABILITY_MMS = 0;

    /**
     * Indicates this is a network that has the ability to reach the carrier's
     * SUPL server, used to retrieve GPS information.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_SUPL
     */
    int NET_CAPABILITY_SUPL = 1;

    /**
     * Indicates this is a network that has the ability to reach the carrier's
     * DUN or tethering gateway.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_DUN
     */
    int NET_CAPABILITY_DUN = 2;

    /**
     * Indicates this is a network that has the ability to reach the carrier's
     * FOTA portal, used for over the air updates.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_FOTA
     */
    int NET_CAPABILITY_FOTA = 3;

    /**
     * Indicates this is a network that has the ability to reach the carrier's
     * IMS servers, used for network registration and signaling.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_IMS
     */
    int NET_CAPABILITY_IMS = 4;

    /**
     * Indicates this is a network that has the ability to reach the carrier's
     * CBS servers, used for carrier specific services.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_CBS
     */
    int NET_CAPABILITY_CBS = 5;

    /**
     * Indicates this is a network that has the ability to reach a Wi-Fi direct
     * peer.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_WIFI_P2P
     */
    int NET_CAPABILITY_WIFI_P2P = 6;

    /**
     * Indicates this is a network that has the ability to reach a carrier's
     * Initial Attach servers.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_IA
     */
    int NET_CAPABILITY_IA = 7;

    /**
     * Indicates this is a network that has the ability to reach a carrier's
     * RCS servers, used for Rich Communication Services.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_RCS
     */
    int NET_CAPABILITY_RCS = 8;

    /**
     * Indicates this is a network that has the ability to reach a carrier's
     * XCAP servers, used for configuration and control.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_XCAP
     */
    int NET_CAPABILITY_XCAP = 9;

    /**
     * Indicates this is a network that has the ability to reach a carrier's
     * Emergency IMS servers or other services, used for network signaling
     * during emergency calls.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_EIMS
     */
    int NET_CAPABILITY_EIMS = 10;

    /**
     * Indicates that this network is unmetered.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_NOT_METERED
     */
    int NET_CAPABILITY_NOT_METERED = 11;

    /**
     * Indicates that this network should be able to reach the internet.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_INTERNET
     */
    int NET_CAPABILITY_INTERNET = 12;

    /**
     * Indicates that this network is available for general use.  If this is not set
     * applications should not attempt to communicate on this network.  Note that this
     * is simply informative and not enforcement - enforcement is handled via other means.
     * Set by default.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_NOT_RESTRICTED
     */
    int NET_CAPABILITY_NOT_RESTRICTED = 13;

    /**
     * Indicates that the user has indicated implicit trust of this network.  This
     * generally means it's a sim-selected carrier, a plugged in ethernet, a paired
     * BT device or a wifi the user asked to connect to.  Untrusted networks
     * are probably limited to unknown wifi AP.  Set by default.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_TRUSTED
     */
    int NET_CAPABILITY_TRUSTED = 14;

    /**
     * Indicates that this network is not a VPN.  This capability is set by default and should be
     * explicitly cleared for VPN networks.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_NOT_VPN
     */
    int NET_CAPABILITY_NOT_VPN = 15;

    /**
     * Indicates that connectivity on this network was successfully validated. For example, for a
     * network with NET_CAPABILITY_INTERNET, it means that Internet connectivity was successfully
     * detected.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_VALIDATED
     */
    int NET_CAPABILITY_VALIDATED = 16;

    /**
     * Indicates that this network was found to have a captive portal in place last time it was
     * probed.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_CAPTIVE_PORTAL
     */
    int NET_CAPABILITY_CAPTIVE_PORTAL = 17;

    /**
     * Indicates that this network is not roaming.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_NOT_ROAMING
     */
    int NET_CAPABILITY_NOT_ROAMING = 18;

    /**
     * Indicates that this network is available for use by apps, and not a network that is being
     * kept up in the background to facilitate fast network switching.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_FOREGROUND
     */
    int NET_CAPABILITY_FOREGROUND = 19;

    /**
     * Indicates that this network is not congested.
     * <p>
     * When a network is congested, applications should defer network traffic
     * that can be done at a later time, such as uploading analytics.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_NOT_CONGESTED
     */
    int NET_CAPABILITY_NOT_CONGESTED = 20;

    /**
     * Indicates that this network is not currently suspended.
     * <p>
     * When a network is suspended, the network's IP addresses and any connections
     * established on the network remain valid, but the network is temporarily unable
     * to transfer data. This can happen, for example, if a cellular network experiences
     * a temporary loss of signal, such as when driving through a tunnel, etc.
     * A network with this capability is not suspended, so is expected to be able to
     * transfer data.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_NOT_SUSPENDED
     */
    int NET_CAPABILITY_NOT_SUSPENDED = 21;

    /**
     * Indicates this is a network that has the ability to reach a carrier's Mission Critical
     * servers.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_MCX
     */
    int NET_CAPABILITY_MCX = 23;

    /**
     * This capability will be set for networks that are generally metered, but are currently
     * unmetered, e.g., because the user is in a particular area. This capability can be changed at
     * any time. When it is removed, applications are responsible for stopping any data transfer
     * that should not occur on a metered network.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_TEMPORARILY_NOT_METERED
     */
    int NET_CAPABILITY_TEMPORARILY_NOT_METERED = 25;
}