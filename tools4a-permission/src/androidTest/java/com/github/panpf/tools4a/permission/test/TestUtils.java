package com.github.panpf.tools4a.permission.test;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class TestUtils {

    @NonNull
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getDeviceIdOr(@NonNull Context context, @NonNull String defaultValue) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String value = manager != null ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? manager.getImei() : manager.getDeviceId()) : null;
            return value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value) ? value : defaultValue;
        } catch (SecurityException e) {
            return defaultValue;
        }
    }

    /**
     * 获取 MAC 地址
     */
    @NonNull
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public static String getMacAddressOr(@NonNull Context context, @NonNull String defaultValue) {
        String macAddress = null;
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
                List<NetworkInterface> nis = interfaceEnumeration != null ? Collections.list(interfaceEnumeration) : null;
                if (nis != null && nis.size() > 0) {
                    NetworkInterface networkInterface = null;
                    for (NetworkInterface ni : nis) {
                        if ("wlan0".equalsIgnoreCase(ni != null ? ni.getName() : null)) {
                            networkInterface = ni;
                            break;
                        }
                    }
                    byte[] address = networkInterface != null ? networkInterface.getHardwareAddress() : null;
                    if (address != null) {
                        StringBuilder macStringBuilder = new StringBuilder();
                        for (byte b : address) {
                            if (macStringBuilder.length() > 0) {
                                macStringBuilder.append(":");
                            }
                            String item = Integer.toHexString(b & 0xFF);
                            macStringBuilder.append(item.length() > 1 ? item : ("0" + item));
                        }
                        macAddress = macStringBuilder.toString();
                    }
                } else {
                    WifiInfo wifiInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
                    macAddress = wifiInfo != null ? wifiInfo.getMacAddress() : null;
                }
            } else {
                WifiInfo wifiInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
                macAddress = wifiInfo != null ? wifiInfo.getMacAddress() : null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            macAddress = null;
        }
        return macAddress != null && !"unknown".equalsIgnoreCase(macAddress) ? macAddress : defaultValue;
    }
}
