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
 * APP Package related extension methods or properties
 */


/* ************************************* is ***************************************** */


/**
 * Return true if the package with the specified packageName is installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.isPackageInstalled(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0
): Boolean = Packagex.isPackageInstalled(this, packageName, packageInfoFlags)

/**
 * Return true if the package with the specified packageName is the system package
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.isSystemPackage(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0
): Boolean = Packagex.isSystemPackage(this, packageName, packageInfoFlags)

/**
 * Return true if the package with the specified packageName is the system package, return to defaultValue if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.isSystemPackageOr(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0, defaultValue: Boolean = false
): Boolean = Packagex.isSystemPackageOr(this, packageName, packageInfoFlags, defaultValue)

/**
 * Return true if the package with the specified packageName is the debuggable package
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.isDebuggablePackage(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0
): Boolean = Packagex.isDebuggablePackage(this, packageName, packageInfoFlags)

/**
 * Return true if the package with the specified packageName is the debuggable package, return to defaultValue if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.isDebuggablePackageOr(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0, defaultValue: Boolean = false
): Boolean = Packagex.isDebuggablePackageOr(this, packageName, packageInfoFlags, defaultValue)

/**
 * Return true if the package with the specified packageName is the junit test package
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.isJunitTestPackage(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0
): Boolean = Packagex.isJunitTestPackage(this, packageName, packageInfoFlags)

/**
 * Return true if the package with the specified packageName is the junit test package, return to defaultValue if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.isJunitTestPackageOr(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0, defaultValue: Boolean = false
): Boolean = Packagex.isJunitTestPackageOr(this, packageName, packageInfoFlags, defaultValue)


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


/* ************************************* get ***************************************** */


/**
 * Get information about the package with the specified packageName
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackage(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0
): SimplePackageInfo = Packagex.getPackage(this, packageName, packageInfoFlags)

/**
 * Get information about the package with the specified packageName, return to null if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageOrNull(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0
): SimplePackageInfo? = Packagex.getPackageOrNull(this, packageName, packageInfoFlags)

/**
 * Get the versionCode of the package for the specified packageName
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageVersionCode(packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0
): Int = Packagex.getPackageVersionCode(this, packageName, packageInfoFlags)


/**
 * Get the versionCode of the package for the specified packageName, return to defaultValue if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageVersionCodeOr(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0, defaultValue: Int = 0
): Int = Packagex.getPackageVersionCodeOr(this, packageName, packageInfoFlags, defaultValue)

/**
 * Get the versionCode of the package for the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageLongVersionCode(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0
): Long = Packagex.getPackageLongVersionCode(this, packageName, packageInfoFlags)


/**
 * Get the versionCode of the package for the specified packageName, return to defaultValue if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageLongVersionCodeOr(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0, defaultValue: Long = 0L
): Long = Packagex.getPackageLongVersionCodeOr(this, packageName, packageInfoFlags, defaultValue)


/**
 * Get the versionName of the package for the specified packageName
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageVersionName(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0
): String = Packagex.getPackageVersionName(this, packageName, packageInfoFlags)

/**
 * Get the versionName of the package for the specified packageName, return to defaultValue if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageVersionNameOr(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0, defaultValue: String = ""
): String = Packagex.getPackageVersionNameOr(this, packageName, packageInfoFlags, defaultValue)

/**
 * Get the versionName of the package for the specified packageName, return to "" if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageVersionNameOrEmpty(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0
): String = Packagex.getPackageVersionNameOrEmpty(this, packageName, packageInfoFlags)

/**
 * Get the versionName of the package for the specified packageName, return to null if not installed
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getPackageVersionNameOrNull(
        packageName: String, @PackageInfoFlags packageInfoFlags: Int = 0
): String? = Packagex.getPackageVersionNameOrNull(this, packageName, packageInfoFlags)


/**
 * Get information about the first package that meets the conditions
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getFirstPackageByFilter(
        packageFilter: PackageFilter? = null, @PackageInfoFlags packageInfoFlags: Int = 0
): SimplePackageInfo? = Packagex.getFirstPackageByFilter(this, packageFilter, packageInfoFlags)

/**
 * Get information about the first package that meets the conditions
 *
 * @param packageFilterFlags see {@link PackageFilterFlags}
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getFirstPackageByFilterFlags(
        @PackageFilterFlags packageFilterFlags: Int = 0, @PackageInfoFlags packageInfoFlags: Int = 0
): SimplePackageInfo? = Packagex.getFirstPackageByFilterFlags(this, packageFilterFlags, packageInfoFlags)

/**
 * Get information about the first package
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.getFirstPackage(@PackageInfoFlags packageInfoFlags: Int = 0): SimplePackageInfo? = Packagex.getFirstPackage(this, packageInfoFlags)


/* ************************************* list ***************************************** */


/**
 * List the packageName and versionCode of all installed package
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listPackageInfo(@PackageInfoFlags packageInfoFlags: Int = 0): List<PackageInfo> = Packagex.listPackageInfo(this, packageInfoFlags)


/**
 * List information for all installed package
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listPackageByFilter(
        packageFilter: PackageFilter? = null, @PackageInfoFlags packageInfoFlags: Int = 0
): List<SimplePackageInfo> = Packagex.listPackageByFilter(this, packageFilter, packageInfoFlags)

/**
 * List information for all installed package
 *
 * @param packageFilterFlags see {@link PackageFilterFlags}
 * @param packageInfoFlags   see {@link PackageInfoFlags}
 */
inline fun Context.listPackageByFilterFlags(
        @PackageFilterFlags packageFilterFlags: Int = 0, @PackageInfoFlags packageInfoFlags: Int = 0
): List<SimplePackageInfo> = Packagex.listPackageByFilterFlags(this, packageFilterFlags, packageInfoFlags)

/**
 * List information for all installed package
 */
inline fun Context.listPackage(@PackageInfoFlags packageInfoFlags: Int = 0): List<SimplePackageInfo> = Packagex.listPackage(this, packageInfoFlags)


/**
 * List the packageName and versionCode of all installed package
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listPackageVersionCodeToMapByFilter(
        packageFilter: PackageFilter? = null, @PackageInfoFlags packageInfoFlags: Int = 0
): ArrayMap<String, Int> = Packagex.listPackageVersionCodeToMapByFilter(this, packageFilter, packageInfoFlags)

/**
 * List the packageName and versionCode of all installed package
 *
 * @param packageFilterFlags see {@link PackageFilterFlags}
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listPackageVersionCodeToMapByFilterFlags(
        @PackageFilterFlags packageFilterFlags: Int = 0, @PackageInfoFlags packageInfoFlags: Int = 0
): ArrayMap<String, Int> = Packagex.listPackageVersionCodeToMapByFilterFlags(this, packageFilterFlags, packageInfoFlags)

/**
 * List the packageName and versionCode of all installed package
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listPackageVersionCodeToMap(@PackageInfoFlags packageInfoFlags: Int = 0): ArrayMap<String, Int> = Packagex.listPackageVersionCodeToMap(this, packageInfoFlags)


/**
 * List the packageName of all installed package
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listPackageNameByFilter(
        packageFilter: PackageFilter? = null, @PackageInfoFlags packageInfoFlags: Int = 0
): List<String> = Packagex.listPackageNameByFilter(this, packageFilter, packageInfoFlags)

/**
 * List the packageName of all installed package
 *
 * @param packageFilterFlags see {@link PackageFilterFlags}
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listPackageNameByFilterFlags(
        @PackageFilterFlags packageFilterFlags: Int = 0, @PackageInfoFlags packageInfoFlags: Int = 0
): List<String> = Packagex.listPackageNameByFilterFlags(this, packageFilterFlags, packageInfoFlags)

/**
 * List the packageName of all installed package
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.listPackageName(@PackageInfoFlags packageInfoFlags: Int = 0): List<String> = Packagex.listPackageName(this, packageInfoFlags)


/* ************************************* count ***************************************** */


/**
 * Get the number of installed package
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.countPackageByFilter(
        packageFilter: PackageFilter? = null, @PackageInfoFlags packageInfoFlags: Int = 0
): Int = Packagex.countPackageByFilter(this, packageFilter, packageInfoFlags)

/**
 * Get the number of installed package
 *
 * @param packageFilterFlags see {@link PackageFilterFlags}
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.countPackageByFilterFlags(
        @PackageFilterFlags packageFilterFlags: Int = 0, @PackageInfoFlags packageInfoFlags: Int = 0
): Int = Packagex.countPackageByFilterFlags(this, packageFilterFlags, packageInfoFlags)

/**
 * Get the number of installed package
 *
 * @param packageInfoFlags see {@link PackageInfoFlags}
 */
inline fun Context.countPackage(@PackageInfoFlags packageInfoFlags: Int = 0): Int = Packagex.countPackage(this, packageInfoFlags)


/* ************************************* other ***************************************** */


/**
 * Get the apk file of the package with the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageApkFile(packageName: String): File = Packagex.getPackageApkFile(this, packageName)

/**
 * Get the apk file of the package with the specified packageName, return to null if not installed
 */
inline fun Context.getPackageApkFileOrNull(packageName: String): File? = Packagex.getPackageApkFileOrNull(this, packageName)


/**
 * Get the signature data of the package with the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageSignatureBytes(packageName: String): ByteArray = Packagex.getPackageSignatureBytes(this, packageName)

/**
 * Get the signature data of the package with the specified packageName, return to null if not installed
 */
inline fun Context.getPackageSignatureBytesOrNull(packageName: String): ByteArray? = Packagex.getPackageSignatureBytesOrNull(this, packageName)


/**
 * Get the icon Drawable of the package of the specified packageName
 *
 * @param versionCode App versionCode. Returns null if versionCode is inconsistent, Integer.MIN_VALUE: ignores versionCode
 * @throws NameNotFoundException, Exception
 */
@Throws(Exception::class)
inline fun Context.getPackageIconDrawable(packageName: String, versionCode: Int): Drawable = Packagex.getPackageIconDrawable(this, packageName, versionCode)

/**
 * Get the icon Drawable of the package of the specified packageName
 *
 * @param versionCode App versionCode. Returns null if versionCode is inconsistent, Integer.MIN_VALUE: ignores versionCode
 */
inline fun Context.getPackageIconDrawableOrNull(packageName: String, versionCode: Int): Drawable? = Packagex.getPackageIconDrawableOrNull(this, packageName, versionCode)


/**
 * Get the icon Drawable of the specified apk file
 * @throws Exception: Apk parsing error
 */
@Throws(Exception::class)
inline fun Context.getApkIconDrawable(apkFile: File): Drawable = Packagex.getApkIconDrawable(this, apkFile)

/**
 * Get the icon Drawable of the specified apk file
 */
inline fun Context.getApkIconDrawableOrNull(apkFile: File): Drawable? = Packagex.getApkIconDrawableOrNull(this, apkFile)