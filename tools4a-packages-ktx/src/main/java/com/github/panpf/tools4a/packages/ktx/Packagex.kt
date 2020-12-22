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

@file:Suppress("NOTHING_TO_INLINE", "NOTHING_TO_INLINE")

package com.github.panpf.tools4a.packages.ktx

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.RequiresPermission
import androidx.collection.ArrayMap
import com.github.panpf.tools4a.packages.*
import java.io.File


/*
 * App Package related extension
 */


/* ************************************* isPackageInstalled ***************************************** */


/**
 * Return true if the package with the specified packageName is installed by PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.isPackageInstalledByInfoFlags(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): Boolean = Packagex.isPackageInstalledByInfoFlags(this, packageName, packageInfoFlags)

/**
 * Return true if the package with the specified packageName is installed
 */
inline fun Context.isPackageInstalled(
        packageName: String
): Boolean = Packagex.isPackageInstalled(this, packageName)


/* ************************************* isSystemPackage ***************************************** */


/**
 * Return true if the package with the specified packageName is the system package by PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.isSystemPackageByInfoFlags(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): Boolean = Packagex.isSystemPackageByInfoFlags(this, packageName, packageInfoFlags)

/**
 * Return true if the package with the specified packageName is the system package
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.isSystemPackage(
        packageName: String
): Boolean = Packagex.isSystemPackage(this, packageName)

/**
 * Return true if the package with the specified packageName is the system package, return to defaultValue if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.isSystemPackageByInfoFlagsOr(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int, defaultValue: Boolean
): Boolean = Packagex.isSystemPackageByInfoFlagsOr(this, packageName, packageInfoFlags, defaultValue)

/**
 * Return true if the package with the specified packageName is the system package, return to defaultValue if not installed
 */
inline fun Context.isSystemPackageOr(
        packageName: String, defaultValue: Boolean
): Boolean = Packagex.isSystemPackageOr(this, packageName, defaultValue)


/* ************************************* isDebuggablePackage ***************************************** */


/**
 * Return true if the package with the specified packageName is the debuggable package by PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.isDebuggablePackageByInfoFlags(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): Boolean = Packagex.isDebuggablePackageByInfoFlags(this, packageName, packageInfoFlags)

/**
 * Return true if the package with the specified packageName is the debuggable package
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.isDebuggablePackage(
        packageName: String
): Boolean = Packagex.isDebuggablePackage(this, packageName)

/**
 * Return true if the package with the specified packageName is the debuggable package by PackageInfoFlags, return to defaultValue if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.isDebuggablePackageByInfoFlagsOr(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int, defaultValue: Boolean
): Boolean = Packagex.isDebuggablePackageByInfoFlagsOr(this, packageName, packageInfoFlags, defaultValue)

/**
 * Return true if the package with the specified packageName is the debuggable package, return to defaultValue if not installed
 */
inline fun Context.isDebuggablePackageOr(
        packageName: String, defaultValue: Boolean
): Boolean = Packagex.isDebuggablePackageOr(this, packageName, defaultValue)


/* ************************************* isJunitTestPackage ***************************************** */


/**
 * Return true if the package with the specified packageName is the junit test package by PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.isJunitTestPackageByInfoFlags(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): Boolean = Packagex.isJunitTestPackageByInfoFlags(this, packageName, packageInfoFlags)

/**
 * Return true if the package with the specified packageName is the junit test package
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.isJunitTestPackage(
        packageName: String
): Boolean = Packagex.isJunitTestPackage(this, packageName)

/**
 * Return true if the package with the specified packageName is the junit test package by PackageInfoFlags, return to defaultValue if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.isJunitTestPackageByInfoFlagsOr(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int, defaultValue: Boolean
): Boolean = Packagex.isJunitTestPackageByInfoFlagsOr(this, packageName, packageInfoFlags, defaultValue)

/**
 * Return true if the package with the specified packageName is the junit test package, return to defaultValue if not installed
 */
inline fun Context.isJunitTestPackageOr(
        packageName: String, defaultValue: Boolean
): Boolean = Packagex.isJunitTestPackageOr(this, packageName, defaultValue)


/* ************************************* getPackageInfo ***************************************** */


/**
 * Get PackageInfo about the package with the specified packageName by PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageInfoByInfoFlags(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): PackageInfo = Packagex.getPackageInfoByInfoFlags(this, packageName, packageInfoFlags)

/**
 * Get PackageInfo about the package with the specified packageName
 */
inline fun Context.getPackageInfo(
        packageName: String
): PackageInfo = Packagex.getPackageInfo(this, packageName)

/**
 * Get PackageInfo about the package with the specified packageName by PackageInfoFlags, return to null if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageInfoByInfoFlagsOrNull(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): PackageInfo? = Packagex.getPackageInfoByInfoFlagsOrNull(this, packageName, packageInfoFlags)

/**
 * Get PackageInfo about the package with the specified packageName, return to null if not installed
 */
inline fun Context.getPackageInfoOrNull(
        packageName: String
): PackageInfo? = Packagex.getPackageInfoOrNull(this, packageName)


/**
 * Get SimplePackageInfo about the package with the specified packageName by PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getSimplePackageInfoByInfoFlags(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): SimplePackageInfo = Packagex.getSimplePackageInfoByInfoFlags(this, packageName, packageInfoFlags)

/**
 * Get SimplePackageInfo about the package with the specified packageName
 */
inline fun Context.getSimplePackageInfo(
        packageName: String
): SimplePackageInfo = Packagex.getSimplePackageInfo(this, packageName)

/**
 * Get SimplePackageInfo about the package with the specified packageName by PackageInfoFlags, return to null if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getSimplePackageInfoByInfoFlagsOrNull(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): SimplePackageInfo? = Packagex.getSimplePackageInfoByInfoFlagsOrNull(this, packageName, packageInfoFlags)

/**
 * Get SimplePackageInfo about the package with the specified packageName, return to null if not installed
 */
inline fun Context.getSimplePackageInfoOrNull(
        packageName: String
): SimplePackageInfo? = Packagex.getSimplePackageInfoOrNull(this, packageName)


/* ************************************* getPackageVersionCode ***************************************** */


/**
 * Get the versionCode of the package for the specified packageName by PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageVersionCodeByInfoFlags(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): Int = Packagex.getPackageVersionCodeByInfoFlags(this, packageName, packageInfoFlags)

/**
 * Get the versionCode of the package for the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageVersionCode(
        packageName: String
): Int = Packagex.getPackageVersionCode(this, packageName)


/**
 * Get the versionCode of the package for the specified packageName by PackageInfoFlags, return to defaultValue if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageVersionCodeByInfoFlagsOr(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int, defaultValue: Int
): Int = Packagex.getPackageVersionCodeByInfoFlagsOr(this, packageName, packageInfoFlags, defaultValue)

/**
 * Get the versionCode of the package for the specified packageName, return to defaultValue if not installed
 */
inline fun Context.getPackageVersionCodeOr(
        packageName: String, defaultValue: Int
): Int = Packagex.getPackageVersionCodeOr(this, packageName, defaultValue)


/**
 * Get the versionCode of the package for the specified packageName by PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageLongVersionCodeByInfoFlags(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): Long = Packagex.getPackageLongVersionCodeByInfoFlags(this, packageName, packageInfoFlags)

/**
 * Get the versionCode of the package for the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageLongVersionCode(
        packageName: String
): Long = Packagex.getPackageLongVersionCode(this, packageName)


/**
 * Get the versionCode of the package for the specified packageName by PackageInfoFlags, return to defaultValue if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageLongVersionCodeByInfoFlagsOr(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int, defaultValue: Long
): Long = Packagex.getPackageLongVersionCodeByInfoFlagsOr(this, packageName, packageInfoFlags, defaultValue)

/**
 * Get the versionCode of the package for the specified packageName, return to defaultValue if not installed
 */
inline fun Context.getPackageLongVersionCodeOr(packageName: String, defaultValue: Long
): Long = Packagex.getPackageLongVersionCodeOr(this, packageName, defaultValue)


/**
 * Get the versionName of the package for the specified packageName by PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageVersionNameByInfoFlags(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): String = Packagex.getPackageVersionNameByInfoFlags(this, packageName, packageInfoFlags)

/**
 * Get the versionName of the package for the specified packageName
 */
inline fun Context.getPackageVersionName(
        packageName: String
): String = Packagex.getPackageVersionName(this, packageName)

/**
 * Get the versionName of the package for the specified packageName by PackageInfoFlags, return to defaultValue if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageVersionNameByInfoFlagsOr(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int, defaultValue: String
): String = Packagex.getPackageVersionNameByInfoFlagsOr(this, packageName, packageInfoFlags, defaultValue)

/**
 * Get the versionName of the package for the specified packageName, return to defaultValue if not installed
 */
inline fun Context.getPackageVersionNameOr(
        packageName: String, defaultValue: String
): String = Packagex.getPackageVersionNameOr(this, packageName, defaultValue)

/**
 * Get the versionName of the package for the specified packageName by PackageInfoFlags, return to "" if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageVersionNameByInfoFlagsOrEmpty(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): String = Packagex.getPackageVersionNameByInfoFlagsOrEmpty(this, packageName, packageInfoFlags)

/**
 * Get the versionName of the package for the specified packageName, return to "" if not installed
 */
inline fun Context.getPackageVersionNameOrEmpty(
        packageName: String
): String = Packagex.getPackageVersionNameOrEmpty(this, packageName)

/**
 * Get the versionName of the package for the specified packageName by PackageInfoFlags, return to null if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageVersionNameByInfoFlagsOrNull(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int
): String? = Packagex.getPackageVersionNameByInfoFlagsOrNull(this, packageName, packageInfoFlags)

/**
 * Get the versionName of the package for the specified packageName, return to null if not installed
 */
inline fun Context.getPackageVersionNameOrNull(
        packageName: String
): String? = Packagex.getPackageVersionNameOrNull(this, packageName)


/* ************************************* listPackageInfo ***************************************** */


/**
 * List PackageInfo for all installed package by PackageFilter and PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listPackageInfoByFilterInfoFlags(
        packageFilter: PackageFilter?, @PackageInfoFlags packageInfoFlags: Int
): List<PackageInfo> = Packagex.listPackageInfoByFilterInfoFlags(this, packageFilter, packageInfoFlags)

/**
 * List PackageInfo for all installed package by PackageTypeFlags and PackageInfoFlags
 *
 * @param packageTypeFlags see {@link PackageTypeFlags}
 * @param packageInfoFlags   see {@link PackageInfoFlags}
 */
inline fun Context.listPackageInfoByTypeInfoFlags(
        @PackageTypeFlags packageTypeFlags: Int, @PackageInfoFlags packageInfoFlags: Int
): List<PackageInfo> = Packagex.listPackageInfoByTypeInfoFlags(this, packageTypeFlags, packageInfoFlags)

/**
 * List PackageInfo for all installed package by PackageFilter
 */
inline fun Context.listPackageInfoByFilter(
        packageFilter: PackageFilter?
): List<PackageInfo> = Packagex.listPackageInfoByFilter(this, packageFilter)

/**
 * List PackageInfo for all installed package by PackageTypeFlags
 */
inline fun Context.listPackageInfoByTypeFlags(
        @PackageTypeFlags packageTypeFlags: Int
): List<PackageInfo> = Packagex.listPackageInfoByTypeFlags(this, packageTypeFlags)

/**
 * List PackageInfo for all installed package by PackageInfoFlags
 */
inline fun Context.listPackageInfoByInfoFlags(
        @PackageInfoFlags packageInfoFlags: Int
): List<PackageInfo> = Packagex.listPackageInfoByInfoFlags(this, packageInfoFlags)

/**
 * List PackageInfo for all installed package
 */
inline fun Context.listPackageInfo(): List<PackageInfo> = Packagex.listPackageInfo(this)


/**
 * List SimplePackageInfo for all installed package by PackageFilter and PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listSimplePackageInfoByFilterInfoFlags(
        packageFilter: PackageFilter?, @PackageInfoFlags packageInfoFlags: Int
): List<SimplePackageInfo> = Packagex.listSimplePackageInfoByFilterInfoFlags(this, packageFilter, packageInfoFlags)

/**
 * List SimplePackageInfo for all installed package by PackageTypeFlags and PackageInfoFlags
 *
 * @param packageTypeFlags see {@link PackageTypeFlags}
 * @param packageInfoFlags   see {@link PackageInfoFlags}
 */
inline fun Context.listSimplePackageInfoByTypeInfoFlags(
        @PackageTypeFlags packageTypeFlags: Int, @PackageInfoFlags packageInfoFlags: Int
): List<SimplePackageInfo> = Packagex.listSimplePackageInfoByTypeInfoFlags(this, packageTypeFlags, packageInfoFlags)

/**
 * List SimplePackageInfo for all installed package by PackageFilter
 */
inline fun Context.listSimplePackageInfoByFilter(
        packageFilter: PackageFilter?
): List<SimplePackageInfo> = Packagex.listSimplePackageInfoByFilter(this, packageFilter)

/**
 * List SimplePackageInfo for all installed package by PackageTypeFlags
 */
inline fun Context.listSimplePackageInfoByTypeFlags(
        @PackageTypeFlags packageTypeFlags: Int
): List<SimplePackageInfo> = Packagex.listSimplePackageInfoByTypeFlags(this, packageTypeFlags)

/**
 * List SimplePackageInfo for all installed package by PackageInfoFlags
 */
inline fun Context.listSimplePackageInfoByInfoFlags(
        @PackageInfoFlags packageInfoFlags: Int
): List<SimplePackageInfo> = Packagex.listSimplePackageInfoByInfoFlags(this, packageInfoFlags)

/**
 * List SimplePackageInfo for all installed package
 */
inline fun Context.listSimplePackageInfo(): List<SimplePackageInfo> = Packagex.listSimplePackageInfo(this)


/* ************************************* listPackageName ***************************************** */


/**
 * List the packageName of all installed package by PackageFilter and PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listPackageNameByFilterInfoFlags(
        packageFilter: PackageFilter? = null, @PackageInfoFlags packageInfoFlags: Int
): List<String> = Packagex.listPackageNameByFilterInfoFlags(this, packageFilter, packageInfoFlags)

/**
 * List the packageName of all installed package by PackageTypeFlags and PackageInfoFlags
 *
 * @param packageTypeFlags see {@link PackageTypeFlags}
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listPackageNameByTypeInfoFlags(
        @PackageTypeFlags packageTypeFlags: Int, @PackageInfoFlags packageInfoFlags: Int
): List<String> = Packagex.listPackageNameByTypeInfoFlags(this, packageTypeFlags, packageInfoFlags)

/**
 * List the packageName of all installed package by PackageFilter
 */
inline fun Context.listPackageNameByFilter(
        packageFilter: PackageFilter?
): List<String> = Packagex.listPackageNameByFilter(this, packageFilter)

/**
 * List the packageName of all installed package by PackageTypeFlags
 *
 * @param packageTypeFlags see {@link PackageTypeFlags}
 */
inline fun Context.listPackageNameByTypeFlags(
        @PackageTypeFlags packageTypeFlags: Int
): List<String> = Packagex.listPackageNameByTypeFlags(this, packageTypeFlags)

/**
 * List the packageName of all installed package by PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listPackageNameByInfoFlags(@PackageInfoFlags packageInfoFlags: Int)
        : List<String> = Packagex.listPackageNameByInfoFlags(this, packageInfoFlags)

/**
 * List the packageName of all installed package
 */
inline fun Context.listPackageName(): List<String> = Packagex.listPackageName(this)


/* ************************************* count ***************************************** */


/**
 * Get the number of installed package by PackageFilter and PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.countPackageByFilterInfoFlags(
        packageFilter: PackageFilter?, @PackageInfoFlags packageInfoFlags: Int
): Int = Packagex.countPackageByFilterInfoFlags(this, packageFilter, packageInfoFlags)

/**
 * Get the number of installed package by PackageTypeFlags and PackageInfoFlags
 *
 * @param packageTypeFlags see {@link PackageTypeFlags}
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.countPackageByTypeInfoFlags(
        @PackageTypeFlags packageTypeFlags: Int, @PackageInfoFlags packageInfoFlags: Int
): Int = Packagex.countPackageByTypeInfoFlags(this, packageTypeFlags, packageInfoFlags)

/**
 * Get the number of installed package by PackageFilter
 */
inline fun Context.countPackageByFilter(
        packageFilter: PackageFilter?
): Int = Packagex.countPackageByFilter(this, packageFilter)

/**
 * Get the number of installed package by PackageTypeFlags
 *
 * @param packageTypeFlags see {@link PackageTypeFlags}
 */
inline fun Context.countPackageByTypeFlags(
        @PackageTypeFlags packageTypeFlags: Int
): Int = Packagex.countPackageByTypeFlags(this, packageTypeFlags)

/**
 * Get the number of installed package by PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.countPackageByInfoFlags(@PackageInfoFlags packageInfoFlags: Int)
        : Int = Packagex.countPackageByInfoFlags(this, packageInfoFlags)

/**
 * Get the number of installed package
 */
inline fun Context.countPackage(): Int = Packagex.countPackage(this)


/* ************************************* getAllPackageVersionCodeMap ***************************************** */


/**
 * Get the packageName and versionCode of all installed package by PackageFilter and PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getAllPackageVersionCodeMapByFilterInfoFlags(
        packageFilter: PackageFilter?, @PackageInfoFlags packageInfoFlags: Int
): ArrayMap<String, Int> = Packagex.getAllPackageVersionCodeMapByFilterInfoFlags(this, packageFilter, packageInfoFlags)

/**
 * Get the packageName and versionCode of all installed package by PackageTypeFlags and PackageInfoFlags
 *
 * @param packageTypeFlags see {@link PackageTypeFlags}
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getAllPackageVersionCodeMapByTypeInfoFlags(
        @PackageTypeFlags packageTypeFlags: Int, @PackageInfoFlags packageInfoFlags: Int
): ArrayMap<String, Int> = Packagex.getAllPackageVersionCodeMapByTypeInfoFlags(this, packageTypeFlags, packageInfoFlags)

/**
 * Get the packageName and versionCode of all installed package by PackageFilter
 */
inline fun Context.getAllPackageVersionCodeMapByFilter(
        packageFilter: PackageFilter?
): ArrayMap<String, Int> = Packagex.getAllPackageVersionCodeMapByFilter(this, packageFilter)

/**
 * Get the packageName and versionCode of all installed package by PackageTypeFlags
 *
 * @param packageTypeFlags see {@link PackageTypeFlags}
 */
inline fun Context.getAllPackageVersionCodeMapByTypeFlags(@PackageTypeFlags packageTypeFlags: Int)
        : ArrayMap<String, Int> = Packagex.getPackageVersionCodeMapByTypeFlags(this, packageTypeFlags)

/**
 * Get the packageName and versionCode of all installed package by PackageInfoFlags
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getAllPackageVersionCodeMapByInfoFlags(@PackageInfoFlags packageInfoFlags: Int)
        : ArrayMap<String, Int> = Packagex.getAllPackageVersionCodeMapByInfoFlags(this, packageInfoFlags)

/**
 * Get the packageName and versionCode of all installed package
 */
inline fun Context.getAllPackageVersionCodeMap()
        : ArrayMap<String, Int> = Packagex.getAllPackageVersionCodeMap(this)


/* ************************************* other ***************************************** */


/**
 * Get the apk file of the package with the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageApkFile(packageName: String)
        : File = Packagex.getPackageApkFile(this, packageName)

/**
 * Get the apk file of the package with the specified packageName, return to null if not installed
 */
inline fun Context.getPackageApkFileOrNull(packageName: String)
        : File? = Packagex.getPackageApkFileOrNull(this, packageName)


/**
 * Get the signature data of the package with the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageSignatureBytes(packageName: String)
        : ByteArray = Packagex.getPackageSignatureBytes(this, packageName)

/**
 * Get the signature data of the package with the specified packageName, return to null if not installed
 */
inline fun Context.getPackageSignatureBytesOrNull(packageName: String)
        : ByteArray? = Packagex.getPackageSignatureBytesOrNull(this, packageName)


/**
 * Get the icon Drawable of the package of the specified packageName
 *
 * @param versionCode App versionCode. Returns null if versionCode is inconsistent, Integer.MIN_VALUE: ignores versionCode
 * @throws PackageManager.NameNotFoundException
 * @throws Exception
 */
@Throws(PackageManager.NameNotFoundException::class, Exception::class)
inline fun Context.getPackageIconDrawable(packageName: String, versionCode: Int)
        : Drawable = Packagex.getPackageIconDrawable(this, packageName, versionCode)

/**
 * Get the icon Drawable of the package of the specified packageName
 *
 * @param versionCode App versionCode. Returns null if versionCode is inconsistent, Integer.MIN_VALUE: ignores versionCode
 */
inline fun Context.getPackageIconDrawableOrNull(packageName: String, versionCode: Int)
        : Drawable? = Packagex.getPackageIconDrawableOrNull(this, packageName, versionCode)


/**
 * Get the icon Drawable of the specified apk file
 * @throws Exception: Apk parsing error
 */
@Throws(Exception::class)
inline fun Context.getApkIconDrawable(apkFile: File): Drawable = Packagex.getApkIconDrawable(this, apkFile)

/**
 * Get the icon Drawable of the specified apk file
 */
inline fun Context.getApkIconDrawableOrNull(apkFile: File)
        : Drawable? = Packagex.getApkIconDrawableOrNull(this, apkFile)


/* ************************************* intent ***************************************** */


/**
 * Create an Intent that opens the specified package install page
 *
 * @receiver APK file uri
 */
@SuppressLint("InlinedApi")
@RequiresPermission(Manifest.permission.REQUEST_INSTALL_PACKAGES)
inline fun Context.createInstallPackageIntent(fileUri: Uri): Intent = Packagex.createInstallPackageIntent(fileUri)

/**
 * Create an Intent that opens the specified package install page
 */
@SuppressLint("InlinedApi")
@RequiresPermission(Manifest.permission.REQUEST_INSTALL_PACKAGES)
inline fun Context.createInstallPackageIntent(apkFile: File): Intent = Packagex.createInstallPackageIntent(this, apkFile)

/**
 * Create an Intent that opens the specified package uninstall page
 *
 * @receiver package name
 */
@SuppressLint("InlinedApi")
@RequiresPermission(Manifest.permission.REQUEST_DELETE_PACKAGES)
inline fun Context.createUninstallPackageIntent(packageName: String): Intent = Packagex.createUninstallPackageIntent(packageName)

/**
 * Create an intent that opens the specified package
 *
 * @param packageName package name
 */
inline fun Context.createLaunchPackageIntent(packageName: String): Intent? = Packagex.createLaunchPackageIntent(this, packageName)

/**
 * Create an Intent that opens the specified package details page
 *
 * @receiver package name
 */
inline fun Context.createApplicationDetailsSettingsIntent(packageName: String): Intent = Packagex.createApplicationDetailsSettingsIntent(packageName)


/* ************************************* install/uninstall ***************************************** */


/**
 * Request to install apk
 * @return false: Request failed
 */
@SuppressLint("InlinedApi")
@RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
inline fun Context.requestInstallPackage(apkFileUri: Uri): Boolean = Packagex.requestInstallPackage(this, apkFileUri)

/**
 * Request to install apk
 * @return false: Request failed
 */
@SuppressLint("InlinedApi")
@RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
inline fun Context.requestInstallPackage(apkFile: File): Boolean = Packagex.requestInstallPackage(this, apkFile)

/**
 * Request to uninstall package
 * @return false: Request failed
 */
@SuppressLint("InlinedApi")
@RequiresPermission(value = Manifest.permission.REQUEST_DELETE_PACKAGES, conditional = true)
inline fun Context.requestUninstallPackage(packageName: String): Boolean = Packagex.requestUninstallPackage(this, packageName)