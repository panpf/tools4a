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

package com.github.panpf.tools4a.settings.ktx

import android.Manifest
import android.content.Context
import androidx.annotation.IntRange
import androidx.annotation.RequiresPermission
import com.github.panpf.tools4a.settings.Settingsx


/*
 * System setup tool method
 */


/**
 * Checks if the specified app can modify system settings. As of API
 * level 23, an app cannot modify system settings unless it declares the
 * [android.Manifest.permission.WRITE_SETTINGS]
 * permission in its manifest, *and* the user specifically grants
 * the app this capability. To prompt the user to grant this approval,
 * the app must send an intent with the action [ ][android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS], which causes
 * the system to display a permission management screen.
 *
 * @return true if the calling app can write to system settings, false otherwise
 */
inline fun Context.canWrite(): Boolean = Settingsx.canWrite(this)

inline fun Context.isNotificationPolicyAccessGranted(): Boolean = Settingsx.isNotificationPolicyAccessGranted(this)


/**
 * Return true if screen brightness auto mode is on
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.isScreenBrightnessModeAutomatic(): Boolean = Settingsx.isScreenBrightnessModeAutomatic(this)

/**
 * Turn on or off the screen brightness auto mode
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.setScreenBrightnessModeAutomatic(automatic: Boolean): Boolean = Settingsx.setScreenBrightnessModeAutomatic(this, automatic)


/**
 * Get system brightness, the range is 0-255
 */
@IntRange(from = 0, to = 255)
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.getScreenBrightness(): Int = Settingsx.getScreenBrightness(this)

/**
 * Set the system brightness (only change the brightness attribute of the system, the current activity brightness does not change)
 *
 * @param brightness Brightness, the range is 0-255
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.setScreenBrightness(@IntRange(from = 0, to = 255) brightness: Int): Boolean = Settingsx.setScreenBrightness(this, brightness)


/**
 * Get screen off timeout in milliseconds
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.getScreenOffTimeout(): Int = Settingsx.getScreenOffTimeout(this)

/**
 * Set screen off timeout in milliseconds
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.setScreenOffTimeout(millis: Int): Boolean = Settingsx.setScreenOffTimeout(this, millis)


/**
 * Return true if airplane mode is on
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.isAirplaneModeOn(): Boolean = Settingsx.isAirplaneModeOn(this)

/**
 * Turn airplane mode on or off
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun Context.setAirplaneModeOn(enable: Boolean): Boolean = Settingsx.setAirplaneModeOn(this, enable)