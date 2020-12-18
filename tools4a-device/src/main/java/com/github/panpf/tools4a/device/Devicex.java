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

package com.github.panpf.tools4a.device;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import com.github.panpf.tools4a.systemproperties.SystemPropertiesx;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public class Devicex {

    private Devicex() {
    }

    public static boolean isEmulator() {
        return "1".equals(SystemPropertiesx.get("ro.kernel.qemu"));
    }

    @NonNull
    public static String getProduct() {
        String value = Build.PRODUCT;
        return value != null ? value : "";
    }

    @NonNull
    public static String getBrand() {
        String value = Build.BRAND;
        return value != null ? value : "";
    }

    @NonNull
    public static String getModel() {
        String value = Build.MODEL;
        return value != null ? value : "";
    }

    @NonNull
    public static String getDevice() {
        String value = Build.DEVICE;
        return value != null ? value : "";
    }

    @NonNull
    public static String getHardware() {
        String value = Build.HARDWARE;
        return value != null ? value : "";
    }

    @NonNull
    public static String[] getSupportedAbis() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return Build.SUPPORTED_ABIS;
            } else {
                LinkedList<String> abiList = new LinkedList<>();
                //noinspection deprecation
                String abi1 = Build.CPU_ABI;
                if (abi1 != null && !abi1.isEmpty() && !"unknown".equals(abi1)) {
                    abiList.add(abi1);
                }
                //noinspection deprecation
                String abi2 = Build.CPU_ABI2;
                if (abi2 != null && !abi2.isEmpty() && !"unknown".equals(abi2)) {
                    abiList.add(abi2);
                }
                return abiList.toArray(new String[0]);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    @NonNull
    @SuppressLint({"HardwareIds", "InlinedApi"})
    @RequiresPermission(anyOf = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS})
    public static String getPhoneNumberOr(@NonNull Context context, @NonNull String defaultValue) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String value = manager != null ? manager.getLine1Number() : null;
            return value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value) ? value : defaultValue;
        } catch (SecurityException e) {
            return defaultValue;
        }
    }

    @NonNull
    @SuppressLint({"HardwareIds", "InlinedApi"})
    @RequiresPermission(anyOf = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS})
    public static String getPhoneNumberOrThrow(@NonNull Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String value = manager != null ? manager.getLine1Number() : null;
        if (value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value)) {
            return value;
        }
        throw new IllegalStateException("Unable to get 'Line1Number' from TELEPHONY_SERVICE");
    }

    @Nullable
    @SuppressLint({"HardwareIds", "InlinedApi"})
    @RequiresPermission(anyOf = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS})
    public static String getPhoneNumberOrNull(@NonNull Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String value = manager != null ? manager.getLine1Number() : null;
            return value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value) ? value : null;
        } catch (SecurityException e) {
            return null;
        }
    }


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

    @NonNull
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getDeviceIdOrThrow(@NonNull Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String value = manager != null ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? manager.getImei() : manager.getDeviceId()) : null;
        if (value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value)) {
            return value;
        }
        throw new IllegalStateException("Unable to get 'DeviceId' from TELEPHONY_SERVICE");
    }

    @Nullable
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getDeviceIdOrNull(@NonNull Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String value = manager != null ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? manager.getImei() : manager.getDeviceId()) : null;
            return value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value) ? value : null;
        } catch (SecurityException e) {
            return null;
        }
    }


    @NonNull
    @SuppressLint("HardwareIds")
    public static String getAndroidIdOr(@NonNull Context context, @NonNull String defaultValue) {
        String value = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value) ? value : defaultValue;
    }

    @NonNull
    @SuppressLint("HardwareIds")
    public static String getAndroidIdOrThrow(@NonNull Context context) {
        String value = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value)) {
            return value;
        }
        throw new IllegalStateException("Unable to get 'AndroidId' from Settings");
    }

    @Nullable
    @SuppressLint("HardwareIds")
    public static String getAndroidIdOrNull(@NonNull Context context) {
        String value = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value) ? value : null;
    }


    /**
     * 获取国际移动用户识别码
     */
    @NonNull
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSubscriberIdOr(@NonNull Context context, @NonNull String defaultValue) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String value = manager != null ? manager.getSubscriberId() : null;
            return value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value) ? value : defaultValue;
        } catch (SecurityException e) {
            return defaultValue;
        }
    }

    /**
     * 获取国际移动用户识别码
     */
    @NonNull
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSubscriberIdOrThrow(@NonNull Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String value = manager != null ? manager.getSubscriberId() : null;
        if (value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value)) {
            return value;
        }
        throw new IllegalStateException("Unable to get 'SubscriberId' from TELEPHONY_SERVICE");
    }

    /**
     * 获取国际移动用户识别码
     */
    @Nullable
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSubscriberIdOrNull(@NonNull Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String value = manager != null ? manager.getSubscriberId() : null;
            return value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value) ? value : null;
        } catch (SecurityException e) {
            return null;
        }
    }


    /**
     * 获取 SIM 卡序列号
     */
    @NonNull
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSimSerialNumberOr(@NonNull Context context, @NonNull String defaultValue) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String value = manager != null ? manager.getSimSerialNumber() : null;
            return value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value) ? value : defaultValue;
        } catch (SecurityException e) {
            return defaultValue;
        }
    }

    /**
     * 获取 SIM 卡序列号
     */
    @NonNull
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSimSerialNumberOrThrow(@NonNull Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String value = manager != null ? manager.getSimSerialNumber() : null;
        if (value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value)) {
            return value;
        }
        throw new IllegalStateException("Unable to get 'SimSerialNumber' from TELEPHONY_SERVICE");
    }

    /**
     * 获取 SIM 卡序列号
     */
    @Nullable
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSimSerialNumberOrNull(@NonNull Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String value = manager != null ? manager.getSimSerialNumber() : null;
            return value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value) ? value : null;
        } catch (SecurityException e) {
            return null;
        }
    }


    @NonNull
    @SuppressLint({"MissingPermission", "HardwareIds"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSerialOr(@NonNull String defaultValue) {
        try {
            String value = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? Build.getSerial() : Build.SERIAL;
            return value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value) ? value : defaultValue;
        } catch (SecurityException e) {
            return defaultValue;
        }
    }

    @NonNull
    @SuppressLint({"MissingPermission", "HardwareIds"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSerialOrThrow() {
        String value = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? Build.getSerial() : Build.SERIAL;
        if (value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value)) {
            return value;
        }
        throw new IllegalStateException("Unable to get 'Serial' from Build");
    }

    @Nullable
    @SuppressLint({"MissingPermission", "HardwareIds"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSerialOrNull() {
        try {
            String value = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? Build.getSerial() : Build.SERIAL;
            return value != null && !value.isEmpty() && !"unknown".equalsIgnoreCase(value) ? value : null;
        } catch (SecurityException e) {
            return null;
        }
    }


    /**
     * 获取国际移动设备身份码
     */
    @NonNull
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getIMEIOr(@NonNull Context context, @NonNull String defaultValue) {
        return getDeviceIdOr(context, defaultValue);
    }

    /**
     * 获取国际移动设备身份码
     */
    @NonNull
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getIMEIOrThrow(@NonNull Context context) {
        return getDeviceIdOrThrow(context);
    }

    /**
     * 获取国际移动设备身份码
     */
    @Nullable
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getIMEIOrNull(@NonNull Context context) {
        return getDeviceIdOrNull(context);
    }


    /**
     * 获取国际移动用户识别码
     */
    @NonNull
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getIMSIOr(@NonNull Context context, @NonNull String defaultValue) {
        return getSubscriberIdOr(context, defaultValue);
    }

    /**
     * 获取国际移动用户识别码
     */
    @NonNull
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getIMSIOrThrow(@NonNull Context context) {
        return getSubscriberIdOrThrow(context);
    }

    /**
     * 获取国际移动用户识别码
     */
    @Nullable
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getIMSIOrNull(@NonNull Context context) {
        return getSubscriberIdOrNull(context);
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

    /**
     * 获取 MAC 地址
     */
    @NonNull
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public static String getMacAddress(@NonNull Context context) {
        return getMacAddressOr(context, "02:00:00:00:00:00");
    }
}