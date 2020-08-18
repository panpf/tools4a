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
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Pair
import androidx.annotation.RequiresPermission
import com.github.panpf.tools4a.packages.AcceptPackageType
import com.github.panpf.tools4a.packages.AppPackage
import com.github.panpf.tools4a.packages.Packagex
import java.io.File


/*
 * APP Package related extension methods or properties
 */


/**
 * Request to install apk
 * @return false: Request failed
 */
@RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
inline fun Context.requestInstallPackage(apkFileUri: Uri): Boolean = Packagex.requestInstall(this, apkFileUri)

/**
 * Request to install apk
 * @return false: Request failed
 */
@RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
inline fun Context.requestInstallPackage(apkFile: File): Boolean = Packagex.requestInstall(this, apkFile)


/**
 * Request to uninstall app
 * @return false: Request failed
 */
@RequiresPermission(value = Manifest.permission.REQUEST_DELETE_PACKAGES, conditional = true)
inline fun Context.requestUninstallPackage(packageName: String): Boolean = Packagex.requestUninstall(this, packageName)


/**
 * Return true if the app with the specified packageName is installed
 */
inline fun Context.isPackageInstalled(packageName: String): Boolean = Packagex.isInstalled(this, packageName)


/**
 * Get the versionCode of the app for the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageVersionCode(packageName: String): Int = Packagex.getVersionCode(this, packageName)

/**
 * Get the versionCode of the app for the specified packageName, return to defaultValue if not installed
 */
inline fun Context.getPackageVersionCodeOr(packageName: String, defaultValue: Int): Int = Packagex.getVersionCodeOr(this, packageName, defaultValue)


/**
 * Get the versionName of the app for the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageVersionName(packageName: String): String = Packagex.getVersionName(this, packageName)

/**
 * Get the versionName of the app for the specified packageName, return to defaultValue if not installed
 */
inline fun Context.getPackageVersionNameOr(packageName: String, defaultValue: String): String = Packagex.getVersionNameOr(this, packageName, defaultValue)

/**
 * Get the versionName of the app for the specified packageName, return to null if not installed
 */
inline fun Context.getPackageVersionNameOrNull(packageName: String): String? = Packagex.getVersionNameOrNull(this, packageName)


/**
 * Get information about the app with the specified packageName
 */
inline fun Context.getPackage(packageName: String): AppPackage = Packagex.get(this, packageName)

/**
 * Get information about the app with the specified packageName, return to null if not installed
 */
inline fun Context.getPackageOrNull(packageName: String): AppPackage? = Packagex.getOrNull(this, packageName)


/**
 * Return true if it is a system APP
 */
inline fun ApplicationInfo.isSystemApp(): Boolean = Packagex.isSystemApp(this)

/**
 * Return true if the app with the specified packageName is the system APP
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.isSystemApp(packageName: String): Boolean = Packagex.isSystemApp(this, packageName)

/**
 * Return true if the app with the specified packageName is the system APP, return to defaultValue if not installed
 */
inline fun Context.isSystemAppOr(packageName: String, defaultValue: Boolean): Boolean = Packagex.isSystemAppOr(this, packageName, defaultValue)


/**
 * List the packageName and versionCode of all installed APPs
 *
 * @param acceptPackageType Accepted package type, see {@link AppType}
 */
inline fun Context.listPackageVersionCodePair(@AcceptPackageType acceptPackageType: Int): List<Pair<String, Int>> = Packagex.listVersionCodePair(this, acceptPackageType)


/**
 * Get the packageName and versionCode of all installed apps Map
 *
 * @param acceptPackageType Accepted package type, see {@link AppType}
 */
inline fun Context.getPackageVersionCodeMap(@AcceptPackageType acceptPackageType: Int): androidx.collection.ArrayMap<String, Int> = Packagex.getVersionCodeMap(this, acceptPackageType)

/**
 * List the packageName of all installed apps
 *
 * @param acceptPackageType Accepted package type, see {@link AppType}
 */
inline fun Context.listPackageName(@AcceptPackageType acceptPackageType: Int): List<String> = Packagex.listPackageName(this, acceptPackageType)

/**
 * List information for all installed apps
 *
 * @param acceptPackageType Accepted package type, see {@link AppType}
 * @param size             How many apps to get. -1: all
 */
inline fun Context.listPackage(@AcceptPackageType acceptPackageType: Int, size: Int): List<AppPackage> = Packagex.list(this, acceptPackageType, size)

/**
 * List information for all installed apps
 *
 * @param acceptPackageType Accepted package type, see {@link AppType}
 */
inline fun Context.listPackage(@AcceptPackageType acceptPackageType: Int): List<AppPackage> = Packagex.list(this, acceptPackageType)


/**
 * Get information about an app
 *
 * @param acceptPackageType Accepted package type, see {@link AppType}
 */
inline fun Context.getOnePackage(@AcceptPackageType acceptPackageType: Int): AppPackage? = Packagex.getOne(this, acceptPackageType)


/**
 * Get the number of installed apps
 *
 * @param acceptPackageType Accepted package type, see {@link AppType}
 */
inline fun Context.countPackage(@AcceptPackageType acceptPackageType: Int): Int = Packagex.count(this, acceptPackageType)


/**
 * Get the apk file of the app with the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageApkFile(packageName: String): File = Packagex.getPackageApkFile(this, packageName)

/**
 * Get the apk file of the app with the specified packageName, return to null if not installed
 */
inline fun Context.getPackageApkFileOrNull(packageName: String): File? = Packagex.getPackageApkFileOrNull(this, packageName)


/**
 * Get the signature data of the app with the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getAppSignatureBytes(packageName: String): ByteArray = Packagex.getAppSignatureBytes(this, packageName)

/**
 * Get the signature data of the app with the specified packageName, return to null if not installed
 */
inline fun Context.getAppSignatureBytesOrNull(packageName: String): ByteArray? = Packagex.getAppSignatureBytesOrNull(this, packageName)


/**
 * Get the icon Drawable of the app of the specified packageName
 *
 * @param versionCode App versionCode. Returns null if versionCode is inconsistent, -1: ignores versionCode
 */
inline fun Context.getAppIconDrawable(packageName: String, versionCode: Int): Drawable? = Packagex.getAppIconDrawable(this, packageName, versionCode)


/**
 * Get the icon Drawable of the specified apk file
 */
inline fun Context.getApkIconDrawable(apkFilePath: String): Drawable? = Packagex.getApkIconDrawable(this, apkFilePath)