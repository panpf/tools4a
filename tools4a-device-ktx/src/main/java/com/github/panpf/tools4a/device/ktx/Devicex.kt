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

package com.github.panpf.tools4a.device.ktx

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.RequiresPermission
import com.github.panpf.tools4a.device.Devicex

/*
 * 设备硬件相关的扩展方法或属性
 */


@SuppressLint("HardwareIds", "InlinedApi")
@RequiresPermission(anyOf = [(Manifest.permission.READ_PHONE_STATE), (Manifest.permission.READ_SMS), (Manifest.permission.READ_PHONE_NUMBERS)])
inline fun Context.getPhoneNumberOr(defaultValue: String): String = Devicex.getPhoneNumberOr(this, defaultValue)

@SuppressLint("HardwareIds", "InlinedApi")
@RequiresPermission(anyOf = [(Manifest.permission.READ_PHONE_STATE), (Manifest.permission.READ_SMS), (Manifest.permission.READ_PHONE_NUMBERS)])
inline fun Context.getPhoneNumberOrThrow(): String = Devicex.getPhoneNumberOrThrow(this)

@SuppressLint("HardwareIds", "InlinedApi")
@RequiresPermission(anyOf = [(Manifest.permission.READ_PHONE_STATE), (Manifest.permission.READ_SMS), (Manifest.permission.READ_PHONE_NUMBERS)])
inline fun Context.getPhoneNumberOrNull(): String? = Devicex.getPhoneNumberOrNull(this)


@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getDeviceIdOr(defaultValue: String): String = Devicex.getDeviceIdOr(this, defaultValue)

@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getDeviceIdOrThrow(): String = Devicex.getDeviceIdOrThrow(this)

@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getDeviceIdOrNull(): String? = Devicex.getDeviceIdOrNull(this)


@SuppressLint("HardwareIds")
inline fun Context.getAndroidIdOr(defaultValue: String): String = Devicex.getAndroidIdOr(this, defaultValue)

@SuppressLint("HardwareIds")
inline fun Context.getAndroidIdOrThrow(): String = Devicex.getAndroidIdOrThrow(this)

@SuppressLint("HardwareIds")
inline fun Context.getAndroidIdOrNull(): String? = Devicex.getAndroidIdOrNull(this)


/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSubscriberIdOr(defaultValue: String): String = Devicex.getSubscriberIdOr(this, defaultValue)

/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSubscriberIdOrThrow(): String = Devicex.getSubscriberIdOrThrow(this)

/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSubscriberIdOrNull(): String? = Devicex.getSubscriberIdOrNull(this)


/**
 * 获取 SIM 卡序列号
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSimSerialNumberOr(defaultValue: String): String = Devicex.getSimSerialNumberOr(this, defaultValue)

/**
 * 获取 SIM 卡序列号
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSimSerialNumberOrThrow(): String = Devicex.getSimSerialNumberOrThrow(this)

/**
 * 获取 SIM 卡序列号
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSimSerialNumberOrNull(): String? = Devicex.getSimSerialNumberOrNull(this)


/**
 * 获取国际移动设备身份码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMEIOr(defaultValue: String): String = Devicex.getIMEIOr(this, defaultValue)

/**
 * 获取国际移动设备身份码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMEIOrThrow(): String = Devicex.getIMEIOrThrow(this)

/**
 * 获取国际移动设备身份码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMEIOrNull(): String? = Devicex.getIMEIOrNull(this)


/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMSIOr(defaultValue: String): String = Devicex.getIMSIOr(this, defaultValue)

/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMSIOrThrow(): String = Devicex.getIMSIOrThrow(this)

/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMSIOrNull(): String? = Devicex.getIMSIOrNull(this)


/**
 * 获取 MAC 地址
 */
@SuppressLint("HardwareIds")
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
inline fun Context.getMacAddressOr(defaultValue: String): String = Devicex.getMacAddressOr(this, defaultValue)

/**
 * 获取 MAC 地址
 */
@SuppressLint("HardwareIds")
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
inline fun Context.getMacAddress(): String = Devicex.getMacAddress(this)