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

package com.github.panpf.tools4a.packages.ktx

import android.content.Context
import android.content.pm.*
import android.util.Pair
import com.github.panpf.tools4a.packages.PackageInfox
import com.github.panpf.tools4a.packages.Predicate

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findActivityInfo(packageName: String, predicate: Predicate<ActivityInfo>): ActivityInfo? = PackageInfox.findActivityInfo(this, packageName, predicate)

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findActivityInfo(packageName: String, crossinline predicate: (ActivityInfo) -> Boolean): ActivityInfo? = PackageInfox.findActivityInfo(this, packageName) { predicate(it) }

inline fun Context.findSelfActivityInfo(predicate: Predicate<ActivityInfo>): ActivityInfo? = PackageInfox.findSelfActivityInfo(this, predicate)

inline fun Context.findSelfActivityInfo(crossinline predicate: (ActivityInfo) -> Boolean): ActivityInfo? = PackageInfox.findSelfActivityInfo(this) { predicate(it) }

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findActivityInfoByName(packageName: String, activityName: String): ActivityInfo? = PackageInfox.findActivityInfoByName(this, packageName, activityName)

inline fun Context.findSelfActivityInfoByName(activityName: String): ActivityInfo? = PackageInfox.findSelfActivityInfoByName(this, activityName)


@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findServiceInfo(packageName: String, predicate: Predicate<ServiceInfo>): ServiceInfo? = PackageInfox.findServiceInfo(this, packageName, predicate)

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findServiceInfo(packageName: String, crossinline predicate: (ServiceInfo) -> Boolean): ServiceInfo? = PackageInfox.findServiceInfo(this, packageName) { predicate(it) }

inline fun Context.findSelfServiceInfo(predicate: Predicate<ServiceInfo>): ServiceInfo? = PackageInfox.findSelfServiceInfo(this, predicate)

inline fun Context.findSelfServiceInfo(crossinline predicate: (ServiceInfo) -> Boolean): ServiceInfo? = PackageInfox.findSelfServiceInfo(this) { predicate(it) }

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findServiceInfoByName(packageName: String, serviceName: String): ServiceInfo? = PackageInfox.findServiceInfoByName(this, packageName, serviceName)

inline fun Context.findSelfServiceInfoByName(serviceName: String): ServiceInfo? = PackageInfox.findSelfServiceInfoByName(this, serviceName)


@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findReceiverInfo(packageName: String, predicate: Predicate<ActivityInfo>): ActivityInfo? = PackageInfox.findReceiverInfo(this, packageName, predicate)

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findReceiverInfo(packageName: String, crossinline predicate: (ActivityInfo) -> Boolean): ActivityInfo? = PackageInfox.findReceiverInfo(this, packageName) { predicate(it) }

inline fun Context.findSelfReceiverInfo(predicate: Predicate<ActivityInfo>): ActivityInfo? = PackageInfox.findSelfReceiverInfo(this, predicate)

inline fun Context.findSelfReceiverInfo(crossinline predicate: (ActivityInfo) -> Boolean): ActivityInfo? = PackageInfox.findSelfReceiverInfo(this) { predicate(it) }

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findReceiverInfoByName(packageName: String, receiverName: String): ActivityInfo? = PackageInfox.findReceiverInfoByName(this, packageName, receiverName)

inline fun Context.findSelfReceiverInfoByName(receiverName: String): ActivityInfo? = PackageInfox.findSelfReceiverInfoByName(this, receiverName)


@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findProviderInfo(packageName: String, predicate: Predicate<ProviderInfo>): ProviderInfo? = PackageInfox.findProviderInfo(this, packageName, predicate)

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findProviderInfo(packageName: String, crossinline predicate: (ProviderInfo) -> Boolean): ProviderInfo? = PackageInfox.findProviderInfo(this, packageName) { predicate(it) }

inline fun Context.findSelfProviderInfo(predicate: Predicate<ProviderInfo>): ProviderInfo? = PackageInfox.findSelfProviderInfo(this, predicate)

inline fun Context.findSelfProviderInfo(crossinline predicate: (ProviderInfo) -> Boolean): ProviderInfo? = PackageInfox.findSelfProviderInfo(this) { predicate(it) }

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findProviderInfoByName(packageName: String, providerName: String): ProviderInfo? = PackageInfox.findProviderInfoByName(this, packageName, providerName)

inline fun Context.findSelfProviderInfoByName(providerName: String): ProviderInfo? = PackageInfox.findSelfProviderInfoByName(this, providerName)


@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findPermissionInfo(packageName: String, predicate: Predicate<PermissionInfo>): PermissionInfo? = PackageInfox.findPermissionInfo(this, packageName, predicate)

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findPermissionInfo(packageName: String, crossinline predicate: (PermissionInfo) -> Boolean): PermissionInfo? = PackageInfox.findPermissionInfo(this, packageName) { predicate(it) }

inline fun Context.findSelfPermissionInfo(predicate: Predicate<PermissionInfo>): PermissionInfo? = PackageInfox.findSelfPermissionInfo(this, predicate)

inline fun Context.findSelfPermissionInfo(crossinline predicate: (PermissionInfo) -> Boolean): PermissionInfo? = PackageInfox.findSelfPermissionInfo(this) { predicate(it) }

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findPermissionInfoByName(packageName: String, permissionName: String): PermissionInfo? = PackageInfox.findPermissionInfoByName(this, packageName, permissionName)

inline fun Context.findSelfPermissionInfoByName(permissionName: String): PermissionInfo? = PackageInfox.findSelfPermissionInfoByName(this, permissionName)


@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findRequestedPermission(packageName: String, predicate: Predicate<String>): String? = PackageInfox.findRequestedPermission(this, packageName, predicate)

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findRequestedPermission(packageName: String, crossinline predicate: (String) -> Boolean): String? = PackageInfox.findRequestedPermission(this, packageName) { predicate(it) }

inline fun Context.findSelfRequestedPermission(predicate: Predicate<String>): String? = PackageInfox.findSelfRequestedPermission(this, predicate)

inline fun Context.findSelfRequestedPermission(crossinline predicate: (String) -> Boolean): String? = PackageInfox.findSelfRequestedPermission(this) { predicate(it) }

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findRequestedPermissionByName(packageName: String, permissionName: String): String? = PackageInfox.findRequestedPermissionByName(this, packageName, permissionName)

inline fun Context.findSelfRequestedPermissionByName(permissionName: String): String? = PackageInfox.findSelfRequestedPermissionByName(this, permissionName)


@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findMetaDataWithName(packageName: String, predicate: Predicate<String>): Pair<String, String>? = PackageInfox.findMetaDataWithName(this, packageName, predicate)

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findMetaDataWithName(packageName: String, crossinline predicate: (String) -> Boolean): Pair<String, String>? = PackageInfox.findMetaDataWithName(this, packageName) { predicate(it) }

inline fun Context.findSelfMetaDataWithName(predicate: Predicate<String>): Pair<String, String>? = PackageInfox.findSelfMetaDataWithName(this, predicate)

inline fun Context.findSelfMetaDataWithName(crossinline predicate: (String) -> Boolean): Pair<String, String>? = PackageInfox.findSelfMetaDataWithName(this) { predicate(it) }

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findMetaDataByName(packageName: String, metaDataName: String): Pair<String, String>? = PackageInfox.findMetaDataByName(this, packageName, metaDataName)

inline fun Context.findSelfMetaDataByName(metaDataName: String): Pair<String, String>? = PackageInfox.findSelfMetaDataByName(this, metaDataName)


@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findMetaDataWithValue(packageName: String, predicate: Predicate<String>): Pair<String, String>? = PackageInfox.findMetaDataWithValue(this, packageName, predicate)

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findMetaDataWithValue(packageName: String, crossinline predicate: (String) -> Boolean): Pair<String, String>? = PackageInfox.findMetaDataWithValue(this, packageName) { predicate(it) }

inline fun Context.findSelfMetaDataWithValue(predicate: Predicate<String>): Pair<String, String>? = PackageInfox.findSelfMetaDataWithValue(this, predicate)

inline fun Context.findSelfMetaDataWithValue(crossinline predicate: (String) -> Boolean): Pair<String, String>? = PackageInfox.findSelfMetaDataWithValue(this) { predicate(it) }

@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.findMetaDataByValue(packageName: String, metaDataValue: String): Pair<String, String>? = PackageInfox.findMetaDataByValue(this, packageName, metaDataValue)

inline fun Context.findSelfMetaDataByValue(metaDataValue: String): Pair<String, String>? = PackageInfox.findSelfMetaDataByValue(this, metaDataValue)
