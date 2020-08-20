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

package com.github.panpf.tools4a.settings;

import android.Manifest;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

/**
 * System setup tool method
 */
public class Settingsx {

    private Settingsx() {
    }

    /**
     * Checks if the specified app can modify system settings. As of API
     * level 23, an app cannot modify system settings unless it declares the
     * {@link Manifest.permission#WRITE_SETTINGS}
     * permission in its manifest, <em>and</em> the user specifically grants
     * the app this capability. To prompt the user to grant this approval,
     * the app must send an intent with the action {@link
     * Settings#ACTION_MANAGE_WRITE_SETTINGS}, which causes
     * the system to display a permission management screen.
     *
     * @return true if the calling app can write to system settings, false otherwise
     */
    public static boolean canWrite(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.System.canWrite(context);
        } else {
            return true;
        }
    }

    public static boolean isNotificationPolicyAccessGranted(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            return notificationManager.isNotificationPolicyAccessGranted();
        } else {
            return true;
        }
    }


    /**
     * Return true if screen brightness auto mode is on
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean isScreenBrightnessModeAutomatic(@NonNull Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Settings.SettingNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Turn on or off the screen brightness auto mode
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean setScreenBrightnessModeAutomatic(@NonNull Context context, boolean automatic) {
        int newValue = automatic ? Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC : Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;
        return Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, newValue);
    }

    /**
     * Get system brightness, the range is 0-255
     */
    @IntRange(from = 0, to = 255)
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static int getScreenBrightness(@NonNull Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Set the system brightness (only change the brightness attribute of the system, the current activity brightness does not change)
     *
     * @param brightness Brightness, the range is 0-255
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean setScreenBrightness(Context context, @IntRange(from = 0, to = 255) int brightness) {
        return Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);
    }


    /**
     * Get screen off timeout in milliseconds
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static int getScreenOffTimeout(@NonNull Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
        } catch (Settings.SettingNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Set screen off timeout in milliseconds
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean setScreenOffTimeout(@NonNull Context context, int millis) {
        return Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, millis);
    }


    /**
     * Return true if airplane mode is on
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean isAirplaneModeOn(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 1;
        } else {
            //noinspection deprecation
            return Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) == 1;
        }
    }

    /**
     * Turn airplane mode on or off
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean setAirplaneModeOn(@NonNull Context context, boolean enabled) {
        boolean result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            result = Settings.Global.putInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, enabled ? 1 : 0);
        } else {
            result = Settings.System.putInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, enabled ? 1 : 0);
        }
        context.sendBroadcast(new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED));
        return result;
    }

    /**
     * Return true if Bluetooth is available
     */
    public static boolean haveBluetooth() {
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    /**
     * Return true if Bluetooth is on or is being turned on
     */
    @RequiresPermission(Manifest.permission.BLUETOOTH)
    public static boolean isBluetoothOn() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        int state = bluetoothAdapter != null ? bluetoothAdapter.getState() : -1;
        return state == BluetoothAdapter.STATE_ON || state == BluetoothAdapter.STATE_TURNING_ON;
    }

    /**
     * Turn Bluetooth on or off
     */
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN})
    public static boolean setBluetoothOn(boolean enable) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            if (enable) {
                bluetoothAdapter.enable();
            } else {
                bluetoothAdapter.disable();
            }
            return true;
        }
        return false;
    }
}
