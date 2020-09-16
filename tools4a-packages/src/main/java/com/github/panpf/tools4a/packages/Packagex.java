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

package com.github.panpf.tools4a.packages;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.collection.ArrayMap;

import com.github.panpf.tools4a.fileprovider.FileProviderx;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Packagex {

    private Packagex() {
    }


    /* ************************************* is ***************************************** */


    /**
     * Return true if the package with the specified packageName is installed
     */
    public static boolean isPackageInstalled(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags) {
        try {
            context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /**
     * Return true if the package with the specified packageName is installed
     */
    public static boolean isPackageInstalled(@NonNull Context context, @NonNull String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }


    /**
     * Return true if the package with the specified packageName is the system package
     */
    public static boolean isSystemPackage(@NonNull Context context, @NonNull String packageName,
                                          @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, packageInfoFlags);
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    /**
     * Return true if the package with the specified packageName is the system package
     */
    public static boolean isSystemPackage(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    /**
     * Return true if the package with the specified packageName is the system package, return to defaultValue if not installed
     */
    public static boolean isSystemPackageOr(@NonNull Context context, @NonNull String packageName,
                                            @PackageInfoFlags int packageInfoFlags, boolean defaultValue) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(packageName, packageInfoFlags);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    /**
     * Return true if the package with the specified packageName is the system package, return to defaultValue if not installed
     */
    public static boolean isSystemPackageOr(@NonNull Context context, @NonNull String packageName, boolean defaultValue) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }


    /**
     * Return true if the package with the specified packageName is the debuggable package
     */
    public static boolean isDebuggablePackage(@NonNull Context context, @NonNull String packageName,
                                              @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, packageInfoFlags);
        return (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    /**
     * Return true if the package with the specified packageName is the debuggable package
     */
    public static boolean isDebuggablePackage(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
        return (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    /**
     * Return true if the package with the specified packageName is the debuggable package, return to defaultValue if not installed
     */
    public static boolean isDebuggablePackageOr(@NonNull Context context, @NonNull String packageName,
                                                @PackageInfoFlags int packageInfoFlags, boolean defaultValue) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(packageName, packageInfoFlags);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        return (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    /**
     * Return true if the package with the specified packageName is the debuggable package, return to defaultValue if not installed
     */
    public static boolean isDebuggablePackageOr(@NonNull Context context, @NonNull String packageName, boolean defaultValue) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        return (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }


    /**
     * Return true if it is a junit test package
     */
    public static boolean isJunitTestPackage(@NonNull Context context, @NonNull String packageName,
                                             @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        packageInfoFlags |= PackageManager.GET_INSTRUMENTATION;
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        boolean debuggable = (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        return debuggable && packageInfo.instrumentation != null && packageInfo.instrumentation.length > 0;
    }

    /**
     * Return true if it is a junit test package
     */
    public static boolean isJunitTestPackage(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_INSTRUMENTATION);
        boolean debuggable = (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        return debuggable && packageInfo.instrumentation != null && packageInfo.instrumentation.length > 0;
    }

    /**
     * Return true if it is a junit test package
     */
    public static boolean isJunitTestPackageOr(@NonNull Context context, @NonNull String packageName,
                                               @PackageInfoFlags int packageInfoFlags, boolean defaultValue) {
        packageInfoFlags |= PackageManager.GET_INSTRUMENTATION;
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        boolean debuggable = (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        return debuggable && packageInfo.instrumentation != null && packageInfo.instrumentation.length > 0;
    }

    /**
     * Return true if it is a junit test package
     */
    public static boolean isJunitTestPackageOr(@NonNull Context context, @NonNull String packageName, boolean defaultValue) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_INSTRUMENTATION);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        boolean debuggable = (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        return debuggable && packageInfo.instrumentation != null && packageInfo.instrumentation.length > 0;
    }


    /* ************************************* intent ***************************************** */


    /**
     * Create an Intent that opens the specified package install page
     *
     * @param apkFileUri APK file uri
     */
    @NonNull
    @SuppressLint("InlinedApi")
    @RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
    public static Intent createInstallPackageIntent(@NonNull Uri apkFileUri) {
        return new Intent(Intent.ACTION_VIEW).addCategory(Intent.CATEGORY_DEFAULT)
                .setDataAndType(apkFileUri, "application/vnd.android.package-archive")
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Prepare for FileProvider on Android N
    }

    /**
     * Create an Intent that opens the specified package install page
     */
    @NonNull
    @SuppressLint("InlinedApi")
    @RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
    public static Intent createInstallPackageIntent(@NonNull Context context, @NonNull File apkFile) {
        return createInstallPackageIntent(FileProviderx.getShareFileUri(context, apkFile));
    }

    /**
     * Create an Intent that opens the specified package uninstall page
     *
     * @param packageName package name
     */
    @NonNull
    @SuppressLint("InlinedApi")
    @RequiresPermission(value = Manifest.permission.REQUEST_DELETE_PACKAGES, conditional = true)
    public static Intent createUninstallPackageIntent(@NonNull String packageName) {
        return new Intent(Intent.ACTION_DELETE, Uri.fromParts("package", packageName, null));
    }

    /**
     * Create an intent that opens the specified package
     *
     * @param packageName package name
     */
    @Nullable
    public static Intent createLaunchPackageIntent(@NonNull Context context, @NonNull String packageName) {
        return context.getPackageManager().getLaunchIntentForPackage(packageName);
    }

    /**
     * Create an Intent that opens the specified package details page
     *
     * @param packageName package name
     */
    @NonNull
    public static Intent createApplicationDetailsSettingsIntent(@NonNull String packageName) {
        return new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .setData(Uri.fromParts("package", packageName, null));
    }


    /* ************************************* install/uninstall ***************************************** */


    /**
     * Request to install package
     *
     * @return false: Request failed
     */
    @SuppressLint("InlinedApi")
    @RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
    public static boolean requestInstallPackage(@NonNull Context context, @NonNull Uri apkFileUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW).addCategory(Intent.CATEGORY_DEFAULT)
                .setDataAndType(apkFileUri, "application/vnd.android.package-archive")
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Request to install package
     *
     * @return false: Request failed
     */
    @SuppressLint("InlinedApi")
    @RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
    public static boolean requestInstallPackage(@NonNull Context context, @NonNull File apkFile) {
        Intent intent = createInstallPackageIntent(context, apkFile);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Request to uninstall package
     *
     * @return false: Request failed
     */
    @SuppressLint("InlinedApi")
    @RequiresPermission(value = Manifest.permission.REQUEST_DELETE_PACKAGES, conditional = true)
    public static boolean requestUninstallPackage(@NonNull Context context, @NonNull String packageName) {
        Intent intent = createUninstallPackageIntent(packageName);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    /* ************************************* get ***************************************** */


    /**
     * Get information about the package with the specified packageName
     */
    @NonNull
    public static SimplePackageInfo getPackage(@NonNull Context context, @NonNull String packageName,
                                               @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(packageName, packageInfoFlags);
        return SimplePackageInfo.fromPackageInfo(packageInfo, packageManager);
    }

    /**
     * Get information about the package with the specified packageName
     */
    @NonNull
    public static SimplePackageInfo getPackage(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
        return SimplePackageInfo.fromPackageInfo(packageInfo, packageManager);
    }

    /**
     * Get information about the package with the specified packageName, return to null if not installed
     */
    @Nullable
    public static SimplePackageInfo getPackageOrNull(@NonNull Context context, @NonNull String packageName,
                                                     @PackageInfoFlags int packageInfoFlags) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, packageInfoFlags);
        } catch (NameNotFoundException e) {
            return null;
        }
        return SimplePackageInfo.fromPackageInfo(packageInfo, packageManager);
    }

    /**
     * Get information about the package with the specified packageName, return to null if not installed
     */
    @Nullable
    public static SimplePackageInfo getPackageOrNull(@NonNull Context context, @NonNull String packageName) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return null;
        }
        return SimplePackageInfo.fromPackageInfo(packageInfo, packageManager);
    }


    /**
     * Get the versionCode of the package for the specified packageName
     */
    public static int getPackageVersionCode(@NonNull Context context, @NonNull String packageName,
                                            @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? (int) packageInfo.getLongVersionCode() : packageInfo.versionCode;
    }

    /**
     * Get the versionCode of the package for the specified packageName
     */
    public static int getPackageVersionCode(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? (int) packageInfo.getLongVersionCode() : packageInfo.versionCode;
    }

    /**
     * Get the versionCode of the package for the specified packageName, return to defaultValue if not installed
     */
    public static int getPackageVersionCodeOr(@NonNull Context context, @NonNull String packageName,
                                              @PackageInfoFlags int packageInfoFlags, int defaultValue) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? (int) packageInfo.getLongVersionCode() : packageInfo.versionCode;
    }

    /**
     * Get the versionCode of the package for the specified packageName, return to defaultValue if not installed
     */
    public static int getPackageVersionCodeOr(@NonNull Context context, @NonNull String packageName, int defaultValue) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? (int) packageInfo.getLongVersionCode() : packageInfo.versionCode;
    }

    /**
     * Get the longVersionCode of the package for the specified packageName
     */
    public static long getPackageLongVersionCode(@NonNull Context context, @NonNull String packageName,
                                                 @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
    }

    /**
     * Get the longVersionCode of the package for the specified packageName
     */
    public static long getPackageLongVersionCode(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
    }

    /**
     * Get the longVersionCode of the package for the specified packageName, return to defaultValue if not installed
     */
    public static long getPackageLongVersionCodeOr(@NonNull Context context, @NonNull String packageName,
                                                   @PackageInfoFlags int packageInfoFlags, long defaultValue) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
    }

    /**
     * Get the longVersionCode of the package for the specified packageName, return to defaultValue if not installed
     */
    public static long getPackageLongVersionCodeOr(@NonNull Context context, @NonNull String packageName, long defaultValue) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
    }


    /**
     * Get the versionName of the package for the specified packageName, return to defaultValue if not installed
     */
    @NonNull
    public static String getPackageVersionName(@NonNull Context context, @NonNull String packageName,
                                               @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        return packageInfo.versionName != null ? packageInfo.versionName : "";
    }

    /**
     * Get the versionName of the package for the specified packageName, return to defaultValue if not installed
     */
    @NonNull
    public static String getPackageVersionName(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        return packageInfo.versionName != null ? packageInfo.versionName : "";
    }

    /**
     * Get the versionName of the package for the specified packageName, return to defaultValue if not installed
     */
    @NonNull
    public static String getPackageVersionNameOr(@NonNull Context context, @NonNull String packageName,
                                                 @PackageInfoFlags int packageInfoFlags, @NonNull String defaultValue) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        return packageInfo.versionName != null ? packageInfo.versionName : "";
    }

    /**
     * Get the versionName of the package for the specified packageName, return to defaultValue if not installed
     */
    @NonNull
    public static String getPackageVersionNameOr(@NonNull Context context, @NonNull String packageName, @NonNull String defaultValue) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        return packageInfo.versionName != null ? packageInfo.versionName : "";
    }

    /**
     * Get the versionName of the package for the specified packageName, return to null if not installed
     */
    @NonNull
    public static String getPackageVersionNameOrEmpty(@NonNull Context context, @NonNull String packageName,
                                                      @PackageInfoFlags int packageInfoFlags) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        } catch (NameNotFoundException e) {
            return "";
        }
        return packageInfo.versionName != null ? packageInfo.versionName : "";
    }

    /**
     * Get the versionName of the package for the specified packageName, return to null if not installed
     */
    @NonNull
    public static String getPackageVersionNameOrEmpty(@NonNull Context context, @NonNull String packageName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return "";
        }
        return packageInfo.versionName != null ? packageInfo.versionName : "";
    }

    /**
     * Get the versionName of the package for the specified packageName, return to null if not installed
     */
    @Nullable
    public static String getPackageVersionNameOrNull(@NonNull Context context, @NonNull String packageName,
                                                     @PackageInfoFlags int packageInfoFlags) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, packageInfoFlags).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    /**
     * Get the versionName of the package for the specified packageName, return to null if not installed
     */
    @Nullable
    public static String getPackageVersionNameOrNull(@NonNull Context context, @NonNull String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }


    /**
     * Get information about the first package that meets the conditions
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @Nullable
    public static SimplePackageInfo getFirstPackageByFilter(
            @NonNull Context context, @Nullable PackageFilter packageFilter,
            @PackageInfoFlags int packageInfoFlags) {
        List<PackageInfo> packageInfoList = listPackageInfo(context, packageInfoFlags);

        PackageManager packageManager = context.getPackageManager();
        for (PackageInfo packageInfo : packageInfoList) {
            if (packageFilter == null || packageFilter.accept(packageInfo)) {
                return SimplePackageInfo.fromPackageInfo(packageInfo, packageManager);
            }
        }
        return null;
    }

    /**
     * Get information about the first package that meets the conditions
     */
    @Nullable
    public static SimplePackageInfo getFirstPackageByFilter(
            @NonNull Context context, @Nullable PackageFilter packageFilter) {
        return getFirstPackageByFilter(context, packageFilter, 0);
    }


    /**
     * Get information about the first package that meets the conditions
     *
     * @param packageFilterFlags see {@link PackageFilterFlags}
     * @param packageInfoFlags   see {@link PackageInfoFlags}
     */
    @Nullable
    public static SimplePackageInfo getFirstPackageByFilterFlags(
            @NonNull Context context, @PackageFilterFlags int packageFilterFlags,
            @PackageInfoFlags int packageInfoFlags) {
        return getFirstPackageByFilter(
                context, new PackageFilterFlagsImpl(context, packageFilterFlags), packageInfoFlags);
    }

    /**
     * Get information about the first package that meets the conditions
     *
     * @param packageFilterFlags see {@link PackageFilterFlags}
     */
    @Nullable
    public static SimplePackageInfo getFirstPackageByFilterFlags(
            @NonNull Context context, @PackageFilterFlags int packageFilterFlags) {
        return getFirstPackageByFilter(
                context, new PackageFilterFlagsImpl(context, packageFilterFlags), 0);
    }


    /**
     * Get information about the first package
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @Nullable
    public static SimplePackageInfo getFirstPackage(
            @NonNull Context context, @PackageInfoFlags int packageInfoFlags) {
        return getFirstPackageByFilter(context, null, packageInfoFlags);
    }

    /**
     * Get information about the first package
     */
    @Nullable
    public static SimplePackageInfo getFirstPackage(@NonNull Context context) {
        return getFirstPackageByFilter(context, null, 0);
    }


    /* ************************************* list ***************************************** */


    /**
     * List the PackageInfo of all installed package
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<PackageInfo> listPackageInfo(@NonNull Context context, @PackageInfoFlags int packageInfoFlags) {
        try {
            return context.getPackageManager().getInstalledPackages(packageInfoFlags);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager crashes internally on dazen X7 4.4.4 and Coolpad Y803-8 5.1 models
            return new ArrayList<>(0);
        }
    }


    /**
     * List information for all installed package
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<SimplePackageInfo> listPackageByFilter(
            @NonNull Context context, @Nullable PackageFilter packageFilter, @PackageInfoFlags int packageInfoFlags) {
        List<PackageInfo> packageInfoList = listPackageInfo(context, packageInfoFlags);
        PackageManager packageManager = context.getPackageManager();
        ArrayList<SimplePackageInfo> packageArrayList = new ArrayList<>(packageInfoList.size());
        for (PackageInfo packageInfo : packageInfoList) {
            if (packageFilter == null || packageFilter.accept(packageInfo)) {
                packageArrayList.add(SimplePackageInfo.fromPackageInfo(packageInfo, packageManager));
            }
        }
        return packageArrayList;
    }

    /**
     * List information for all installed package
     */
    @NonNull
    public static List<SimplePackageInfo> listPackageByFilter(
            @NonNull Context context, @Nullable PackageFilter packageFilter) {
        return listPackageByFilter(context, packageFilter, 0);
    }


    /**
     * List information for all installed package
     *
     * @param packageFilterFlags see {@link PackageFilterFlags}
     * @param packageInfoFlags   see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<SimplePackageInfo> listPackageByFilterFlags(
            @NonNull Context context, @PackageFilterFlags int packageFilterFlags, @PackageInfoFlags int packageInfoFlags) {
        return listPackageByFilter(
                context, new PackageFilterFlagsImpl(context, packageFilterFlags), packageInfoFlags);
    }

    /**
     * List information for all installed package
     *
     * @param packageFilterFlags see {@link PackageFilterFlags}
     */
    @NonNull
    public static List<SimplePackageInfo> listPackageByFilterFlags(
            @NonNull Context context, @PackageFilterFlags int packageFilterFlags) {
        return listPackageByFilter(
                context, new PackageFilterFlagsImpl(context, packageFilterFlags), 0);
    }


    /**
     * List information for all installed package
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<SimplePackageInfo> listPackage(
            @NonNull Context context, @PackageInfoFlags int packageInfoFlags) {
        return listPackageByFilter(context, null, packageInfoFlags);
    }

    /**
     * List information for all installed package
     */
    @NonNull
    public static List<SimplePackageInfo> listPackage(@NonNull Context context) {
        return listPackageByFilter(context, null, 0);
    }


    /**
     * Get the packageName and versionCode of all installed package
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static ArrayMap<String, Integer> listPackageVersionCodeToMapByFilter(
            @NonNull Context context, @Nullable PackageFilter packageFilter, @PackageInfoFlags int packageInfoFlags) {
        List<PackageInfo> packageInfoList = listPackageInfo(context, packageInfoFlags);
        ArrayMap<String, Integer> appsSet = new ArrayMap<>(packageInfoList.size());
        for (PackageInfo packageInfo : packageInfoList) {
            if (packageFilter == null || packageFilter.accept(packageInfo)) {
                int versionCode = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                        ? (int) packageInfo.getLongVersionCode() : packageInfo.versionCode;
                appsSet.put(packageInfo.packageName, versionCode);
            }
        }
        return appsSet;
    }

    /**
     * Get the packageName and versionCode of all installed package
     */
    @NonNull
    public static ArrayMap<String, Integer> listPackageVersionCodeToMapByFilter(
            @NonNull Context context, @Nullable PackageFilter packageFilter) {
        return listPackageVersionCodeToMapByFilter(context, packageFilter, 0);
    }


    /**
     * Get the packageName and versionCode of all installed package
     *
     * @param packageFilterFlags see {@link PackageFilterFlags}
     * @param packageInfoFlags   see {@link PackageInfoFlags}
     */
    @NonNull
    public static ArrayMap<String, Integer> listPackageVersionCodeToMapByFilterFlags(
            @NonNull Context context, @PackageFilterFlags int packageFilterFlags, @PackageInfoFlags int packageInfoFlags) {
        return listPackageVersionCodeToMapByFilter(
                context, new PackageFilterFlagsImpl(context, packageFilterFlags), packageInfoFlags);
    }

    /**
     * Get the packageName and versionCode of all installed package
     *
     * @param packageFilterFlags see {@link PackageFilterFlags}
     */
    @NonNull
    public static ArrayMap<String, Integer> listPackageVersionCodeToMapByFilterFlags(
            @NonNull Context context, @PackageFilterFlags int packageFilterFlags) {
        return listPackageVersionCodeToMapByFilter(
                context, new PackageFilterFlagsImpl(context, packageFilterFlags), 0);
    }

    /**
     * Get the packageName and versionCode of all installed package
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static ArrayMap<String, Integer> listPackageVersionCodeToMap(
            @NonNull Context context, @PackageInfoFlags int packageInfoFlags) {
        return listPackageVersionCodeToMapByFilter(context, null, packageInfoFlags);
    }

    /**
     * Get the packageName and versionCode of all installed package
     */
    @NonNull
    public static ArrayMap<String, Integer> listPackageVersionCodeToMap(@NonNull Context context) {
        return listPackageVersionCodeToMapByFilter(context, null, 0);
    }


    /**
     * List the packageName of all installed package
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<String> listPackageNameByFilter(
            @NonNull Context context, @Nullable PackageFilter packageFilter,
            @PackageInfoFlags int packageInfoFlags) {
        List<PackageInfo> packageInfoList = listPackageInfo(context, packageInfoFlags);

        List<String> appsSet = new ArrayList<>(packageInfoList.size());
        for (PackageInfo packageInfo : packageInfoList) {
            if (packageFilter == null || packageFilter.accept(packageInfo)) {
                appsSet.add(packageInfo.packageName);
            }
        }
        return appsSet;
    }

    /**
     * List the packageName of all installed package
     */
    @NonNull
    public static List<String> listPackageNameByFilter(
            @NonNull Context context, @Nullable PackageFilter packageFilter) {
        return listPackageNameByFilter(context, packageFilter, 0);
    }


    /**
     * List the packageName of all installed package
     *
     * @param packageFilterFlags see {@link PackageFilterFlags}
     * @param packageInfoFlags   see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<String> listPackageNameByFilterFlags(
            @NonNull Context context, @PackageFilterFlags int packageFilterFlags, @PackageInfoFlags int packageInfoFlags) {
        return listPackageNameByFilter(
                context, new PackageFilterFlagsImpl(context, packageFilterFlags), packageInfoFlags);
    }

    /**
     * List the packageName of all installed package
     *
     * @param packageFilterFlags see {@link PackageFilterFlags}
     */
    @NonNull
    public static List<String> listPackageNameByFilterFlags(
            @NonNull Context context, @PackageFilterFlags int packageFilterFlags) {
        return listPackageNameByFilter(
                context, new PackageFilterFlagsImpl(context, packageFilterFlags), 0);
    }


    /**
     * List the packageName of all installed package
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<String> listPackageName(
            @NonNull Context context, @PackageInfoFlags int packageInfoFlags) {
        return listPackageNameByFilter(context, null, packageInfoFlags);
    }

    /**
     * List the packageName of all installed package
     */
    @NonNull
    public static List<String> listPackageName(@NonNull Context context) {
        return listPackageNameByFilter(context, null, 0);
    }


    /* ************************************* count ***************************************** */


    /**
     * Get the number of installed package
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    public static int countPackageByFilter(
            @NonNull Context context, @Nullable PackageFilter packageFilter, @PackageInfoFlags int packageInfoFlags) {
        List<PackageInfo> packageInfoList = listPackageInfo(context, packageInfoFlags);
        if (packageFilter != null) {
            int count = 0;
            for (PackageInfo packageInfo : packageInfoList) {
                if (packageFilter.accept(packageInfo)) {
                    count++;
                }
            }
            return count;
        } else {
            return packageInfoList.size();
        }
    }

    /**
     * Get the number of installed package
     */
    public static int countPackageByFilter(@NonNull Context context, @Nullable PackageFilter packageFilter) {
        return countPackageByFilter(context, packageFilter, 0);
    }


    /**
     * Get the number of installed package
     *
     * @param packageFilterFlags see {@link PackageFilterFlags}
     * @param packageInfoFlags   see {@link PackageInfoFlags}
     */
    public static int countPackageByFilterFlags(
            @NonNull Context context, @PackageFilterFlags int packageFilterFlags, @PackageInfoFlags int packageInfoFlags) {
        return countPackageByFilter(
                context, new PackageFilterFlagsImpl(context, packageFilterFlags), packageInfoFlags);
    }

    /**
     * Get the number of installed package
     */
    public static int countPackageByFilterFlags(
            @NonNull Context context, @PackageFilterFlags int packageFilterFlags) {
        return countPackageByFilter(
                context, new PackageFilterFlagsImpl(context, packageFilterFlags), 0);
    }


    /**
     * Get the number of installed package
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    public static int countPackage(@NonNull Context context, @PackageInfoFlags int packageInfoFlags) {
        return countPackageByFilter(context, null, packageInfoFlags);
    }

    /**
     * Get the number of installed package
     */
    public static int countPackage(@NonNull Context context) {
        return countPackageByFilter(context, null, 0);
    }


    /* ************************************* other ***************************************** */


    /**
     * Get the apk file of the package with the specified packageName
     */
    @NonNull
    public static File getPackageApkFile(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
        return new File(applicationInfo.sourceDir);
    }

    /**
     * Get the apk file of the package with the specified packageName, return to null if not installed
     */
    @Nullable
    public static File getPackageApkFileOrNull(@NonNull Context context, @NonNull String packageName) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return new File(applicationInfo.sourceDir);
    }


    /**
     * Get the signature data of the package with the specified packageName
     */
    @NonNull
    public static byte[] getPackageSignatureBytes(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        Signature[] signatures;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            SigningInfo signingInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES).signingInfo;
            signatures = signingInfo != null ? signingInfo.getApkContentsSigners() : null;
        } else {
            @SuppressLint("PackageManagerGetSignatures")
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            signatures = packageInfo.signatures;
        }
        if (signatures != null && signatures.length > 0) {
            return signatures[0].toByteArray();
        } else {
            throw new IllegalArgumentException(packageName + " signatures is empty");
        }
    }

    /**
     * Get the signature data of the package with the specified packageName, return to null if not installed
     */
    @Nullable
    public static byte[] getPackageSignatureBytesOrNull(@NonNull Context context, @NonNull String packageName) {
        try {
            Signature[] signatures;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                SigningInfo signingInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES).signingInfo;
                signatures = signingInfo != null ? signingInfo.getApkContentsSigners() : null;
            } else {
                @SuppressLint("PackageManagerGetSignatures")
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
                signatures = packageInfo.signatures;
            }
            if (signatures != null && signatures.length > 0) {
                return signatures[0].toByteArray();
            } else {
                throw new IllegalArgumentException(packageName + " signatures is empty");
            }
        } catch (NameNotFoundException e) {
            return null;
        }
    }


    /**
     * Get the icon Drawable of the package of the specified packageName
     *
     * @param versionCode App versionCode. Returns null if versionCode is inconsistent, Integer.MIN_VALUE: ignores versionCode
     * @throws NameNotFoundException Could not find package based on [packageName]
     * @throws Exception             The versionCode of the installed package is not the same as [versionCode]
     */
    @NonNull
    public static Drawable getPackageIconDrawable(@NonNull Context context, @NonNull String packageName, int versionCode) throws NameNotFoundException, Exception {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);

        int installedVersionCode = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                ? (int) packageInfo.getLongVersionCode() : packageInfo.versionCode;
        if (versionCode != Integer.MIN_VALUE && installedVersionCode != versionCode) {
            throw new Exception("package '" + packageName + "' versionCode inconsistent. " +
                    "installedVersionCode=" + installedVersionCode + ", versionCode=" + versionCode);
        }

        return packageInfo.applicationInfo.loadIcon(packageManager);
    }

    /**
     * Get the icon Drawable of the package of the specified packageName
     *
     * @param versionCode App versionCode. Returns null if versionCode is inconsistent, Integer.MIN_VALUE: ignores versionCode
     */
    @Nullable
    public static Drawable getPackageIconDrawableOrNull(@NonNull Context context, @NonNull String packageName, int versionCode) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return null;
        }

        int installedVersionCode = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                ? (int) packageInfo.getLongVersionCode() : packageInfo.versionCode;
        if (versionCode != Integer.MIN_VALUE && installedVersionCode != versionCode) {
            return null;
        }

        return packageInfo.applicationInfo.loadIcon(packageManager);
    }


    /**
     * Get the icon Drawable of the specified apk file
     *
     * @throws Exception Apk parsing error
     */
    @NonNull
    public static Drawable getApkIconDrawable(@NonNull Context context, @NonNull File apkFile) throws Exception {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(apkFile.getPath(), 0);
        if (packageInfo == null) {
            throw new Exception("Apk parsing error. " + apkFile.getPath());
        }
        packageInfo.applicationInfo.sourceDir = apkFile.getPath();
        packageInfo.applicationInfo.publicSourceDir = apkFile.getPath();
        return packageInfo.applicationInfo.loadIcon(packageManager);
    }

    /**
     * Get the icon Drawable of the specified apk file
     */
    @Nullable
    public static Drawable getApkIconDrawableOrNull(@NonNull Context context, @NonNull File apkFile) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageArchiveInfo(apkFile.getPath(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return null;
        }
        packageInfo.applicationInfo.sourceDir = apkFile.getPath();
        packageInfo.applicationInfo.publicSourceDir = apkFile.getPath();
        return packageInfo.applicationInfo.loadIcon(packageManager);
    }
}