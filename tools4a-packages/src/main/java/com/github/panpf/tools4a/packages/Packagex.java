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


    /* ************************************* isPackageInstalled ***************************************** */

    /**
     * Return true if the package with the specified packageName is installed by PackageInfoFlags
     */
    public static boolean isPackageInstalledByInfoFlags(
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
        return isPackageInstalledByInfoFlags(context, packageName, 0);
    }


    /* ************************************* isSystemPackage ***************************************** */


    /**
     * Return true if the package with the specified packageName is the system package by PackageInfoFlags
     */
    public static boolean isSystemPackageByInfoFlags(@NonNull Context context, @NonNull String packageName,
                                                     @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, packageInfoFlags);
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    /**
     * Return true if the package with the specified packageName is the system package
     */
    public static boolean isSystemPackage(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        return isSystemPackageByInfoFlags(context, packageName, 0);
    }

    /**
     * Return true if the package with the specified packageName is the system package by PackageInfoFlags, return to defaultValue if not installed
     */
    public static boolean isSystemPackageByInfoFlagsOr(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags, boolean defaultValue) {
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
        return isSystemPackageByInfoFlagsOr(context, packageName, 0, defaultValue);
    }


    /* ************************************* isDebuggablePackage ***************************************** */


    /**
     * Return true if the package with the specified packageName is the debuggable package by PackageInfoFlags
     */
    public static boolean isDebuggablePackageByInfoFlags(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, packageInfoFlags);
        return (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    /**
     * Return true if the package with the specified packageName is the debuggable package
     */
    public static boolean isDebuggablePackage(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        return isDebuggablePackageByInfoFlags(context, packageName, 0);
    }

    /**
     * Return true if the package with the specified packageName is the debuggable package by PackageInfoFlags, return to defaultValue if not installed
     */
    public static boolean isDebuggablePackageByInfoFlagsOr(@NonNull Context context, @NonNull String packageName,
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
        return isDebuggablePackageByInfoFlagsOr(context, packageName, 0, defaultValue);
    }


    /* ************************************* isJunitTestPackage ***************************************** */


    /**
     * Return true if it is a junit test package by PackageInfoFlags
     */
    public static boolean isJunitTestPackageByInfoFlags(@NonNull Context context, @NonNull String packageName,
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
        return isJunitTestPackageByInfoFlags(context, packageName, 0);
    }

    /**
     * Return true if it is a junit test package by PackageInfoFlags
     */
    public static boolean isJunitTestPackageByInfoFlagsOr(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags, boolean defaultValue) {
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
        return isJunitTestPackageByInfoFlagsOr(context, packageName, 0, defaultValue);
    }


    /* ************************************* getPackageInfo ***************************************** */


    /**
     * Get PackageInfo about the package with the specified packageName by PackageInfoFlags
     */
    @NonNull
    public static PackageInfo getPackageInfoByInfoFlags(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        return context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
    }

    /**
     * Get PackageInfo about the package with the specified packageName
     */
    @NonNull
    public static PackageInfo getPackageInfo(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        return getPackageInfoByInfoFlags(context, packageName, 0);
    }

    /**
     * Get PackageInfo about the package with the specified packageName by PackageInfoFlags, return to null if not installed
     */
    @Nullable
    public static PackageInfo getPackageInfoByInfoFlagsOrNull(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    /**
     * Get PackageInfo about the package with the specified packageName, return to null if not installed
     */
    @Nullable
    public static PackageInfo getPackageInfoOrNull(@NonNull Context context, @NonNull String packageName) {
        return getPackageInfoByInfoFlagsOrNull(context, packageName, 0);
    }


    /**
     * Get SimplePackageInfo about the package with the specified packageName by PackageInfoFlags
     */
    @NonNull
    public static SimplePackageInfo getSimplePackageInfoByInfoFlags(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(packageName, packageInfoFlags);
        return SimplePackageInfo.fromPackageInfo(packageInfo, packageManager);
    }

    /**
     * Get SimplePackageInfo about the package with the specified packageName
     */
    @NonNull
    public static SimplePackageInfo getSimplePackageInfo(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        return getSimplePackageInfoByInfoFlags(context, packageName, 0);
    }

    /**
     * Get SimplePackageInfo about the package with the specified packageName by PackageInfoFlags, return to null if not installed
     */
    @Nullable
    public static SimplePackageInfo getSimplePackageInfoByInfoFlagsOrNull(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags) {
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
     * Get SimplePackageInfo about the package with the specified packageName, return to null if not installed
     */
    @Nullable
    public static SimplePackageInfo getSimplePackageInfoOrNull(@NonNull Context context, @NonNull String packageName) {
        return getSimplePackageInfoByInfoFlagsOrNull(context, packageName, 0);
    }


    /* ************************************* getPackageVersionCode ***************************************** */


    /**
     * Get the versionCode of the package for the specified packageName by PackageInfoFlags
     */
    public static int getPackageVersionCodeByInfoFlags(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? (int) packageInfo.getLongVersionCode() : packageInfo.versionCode;
    }

    /**
     * Get the versionCode of the package for the specified packageName
     */
    public static int getPackageVersionCode(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        return getPackageVersionCodeByInfoFlags(context, packageName, 0);
    }

    /**
     * Get the versionCode of the package for the specified packageName by PackageInfoFlags, return to defaultValue if not installed
     */
    public static int getPackageVersionCodeByInfoFlagsOr(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags, int defaultValue) {
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
        return getPackageVersionCodeByInfoFlagsOr(context, packageName, 0, defaultValue);
    }

    /**
     * Get the longVersionCode of the package for the specified packageName by PackageInfoFlags
     */
    public static long getPackageLongVersionCodeByInfoFlags(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
    }

    /**
     * Get the longVersionCode of the package for the specified packageName
     */
    public static long getPackageLongVersionCode(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        return getPackageLongVersionCodeByInfoFlags(context, packageName, 0);
    }

    /**
     * Get the longVersionCode of the package for the specified packageName by PackageInfoFlags, return to defaultValue if not installed
     */
    public static long getPackageLongVersionCodeByInfoFlagsOr(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags, long defaultValue) {
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
        return getPackageLongVersionCodeByInfoFlagsOr(context, packageName, 0, defaultValue);
    }


    /* ************************************* getPackageVersionName ***************************************** */


    /**
     * Get the versionName of the package for the specified packageName by PackageInfoFlags, return to defaultValue if not installed
     */
    @NonNull
    public static String getPackageVersionNameByInfoFlags(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, packageInfoFlags);
        return packageInfo.versionName != null ? packageInfo.versionName : "";
    }

    /**
     * Get the versionName of the package for the specified packageName, return to defaultValue if not installed
     */
    @NonNull
    public static String getPackageVersionName(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        return getPackageVersionNameByInfoFlags(context, packageName, 0);
    }

    /**
     * Get the versionName of the package for the specified packageName by PackageInfoFlags, return to defaultValue if not installed
     */
    @NonNull
    public static String getPackageVersionNameByInfoFlagsOr(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags, @NonNull String defaultValue) {
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
        return getPackageVersionNameByInfoFlagsOr(context, packageName, 0, defaultValue);
    }

    /**
     * Get the versionName of the package for the specified packageName by PackageInfoFlags, return to null if not installed
     */
    @NonNull
    public static String getPackageVersionNameByInfoFlagsOrEmpty(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags) {
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
        return getPackageVersionNameByInfoFlagsOrEmpty(context, packageName, 0);
    }

    /**
     * Get the versionName of the package for the specified packageName by PackageInfoFlags, return to null if not installed
     */
    @Nullable
    public static String getPackageVersionNameByInfoFlagsOrNull(
            @NonNull Context context, @NonNull String packageName, @PackageInfoFlags int packageInfoFlags) {
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
        return getPackageVersionNameByInfoFlagsOrNull(context, packageName, 0);
    }


    /* ************************************* listPackageInfo ***************************************** */


    /**
     * List PackageInfo for all installed package by PackageFilter and PackageInfoFlags
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    @SuppressLint("QueryPermissionsNeeded")
    public static List<PackageInfo> listPackageInfoByFilterInfoFlags(
            @NonNull Context context, @Nullable PackageFilter packageFilter, @PackageInfoFlags int packageInfoFlags) {
        List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(packageInfoFlags);
        if (packageFilter != null) {
            List<PackageInfo> newPackageInfoList = new ArrayList<>(packageInfoList.size());
            for (PackageInfo packageInfo : packageInfoList) {
                if (packageFilter.accept(packageInfo)) {
                    newPackageInfoList.add(packageInfo);
                }
            }
            return newPackageInfoList;
        } else {
            return packageInfoList;
        }
    }

    /**
     * List PackageInfo for all installed package by PackageTypeFlags and PackageInfoFlags
     *
     * @param packageTypeFlags see {@link PackageTypeFlags}
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<PackageInfo> listPackageInfoByTypeInfoFlags(
            @NonNull Context context, @PackageTypeFlags int packageTypeFlags, @PackageInfoFlags int packageInfoFlags) {
        return listPackageInfoByFilterInfoFlags(
                context, new PackageFilterTypeFlagsImpl(context, packageTypeFlags), packageInfoFlags);
    }

    /**
     * List PackageInfo for all installed package by PackageFilter
     */
    @NonNull
    public static List<PackageInfo> listPackageInfoByFilter(
            @NonNull Context context, @Nullable PackageFilter packageFilter) {
        return listPackageInfoByFilterInfoFlags(context, packageFilter, 0);
    }


    /**
     * List PackageInfo for all installed package by PackageTypeFlags
     *
     * @param packageTypeFlags see {@link PackageTypeFlags}
     */
    @NonNull
    public static List<PackageInfo> listPackageInfoByTypeFlags(
            @NonNull Context context, @PackageTypeFlags int packageTypeFlags) {
        return listPackageInfoByFilterInfoFlags(
                context, new PackageFilterTypeFlagsImpl(context, packageTypeFlags), 0);
    }


    /**
     * List PackageInfo for all installed package by PackageInfoFlags
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<PackageInfo> listPackageInfoByInfoFlags(
            @NonNull Context context, @PackageInfoFlags int packageInfoFlags) {
        return listPackageInfoByFilterInfoFlags(context, null, packageInfoFlags);
    }

    /**
     * List PackageInfo for all installed package
     */
    @NonNull
    public static List<PackageInfo> listPackageInfo(@NonNull Context context) {
        return listPackageInfoByFilterInfoFlags(context, null, 0);
    }


    /**
     * List SimplePackageInfo for all installed package by PackageFilter and PackageInfoFlags
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    @SuppressLint("QueryPermissionsNeeded")
    public static List<SimplePackageInfo> listSimplePackageInfoByFilterInfoFlags(
            @NonNull Context context, @Nullable PackageFilter packageFilter, @PackageInfoFlags int packageInfoFlags) {
        List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(packageInfoFlags);
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
     * List SimplePackageInfo for all installed package by PackageTypeFlags and PackageInfoFlags
     *
     * @param packageTypeFlags see {@link PackageTypeFlags}
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<SimplePackageInfo> listSimplePackageInfoByTypeInfoFlags(
            @NonNull Context context, @PackageTypeFlags int packageTypeFlags, @PackageInfoFlags int packageInfoFlags) {
        return listSimplePackageInfoByFilterInfoFlags(
                context, new PackageFilterTypeFlagsImpl(context, packageTypeFlags), packageInfoFlags);
    }

    /**
     * List SimplePackageInfo for all installed package by PackageFilter
     */
    @NonNull
    public static List<SimplePackageInfo> listSimplePackageInfoByFilter(
            @NonNull Context context, @Nullable PackageFilter packageFilter) {
        return listSimplePackageInfoByFilterInfoFlags(context, packageFilter, 0);
    }

    /**
     * List SimplePackageInfo for all installed package by PackageTypeFlags
     *
     * @param packageTypeFlags see {@link PackageTypeFlags}
     */
    @NonNull
    public static List<SimplePackageInfo> listSimplePackageInfoByTypeFlags(
            @NonNull Context context, @PackageTypeFlags int packageTypeFlags) {
        return listSimplePackageInfoByFilterInfoFlags(
                context, new PackageFilterTypeFlagsImpl(context, packageTypeFlags), 0);
    }

    /**
     * List SimplePackageInfo for all installed package by PackageInfoFlags
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<SimplePackageInfo> listSimplePackageInfoByInfoFlags(
            @NonNull Context context, @PackageInfoFlags int packageInfoFlags) {
        return listSimplePackageInfoByFilterInfoFlags(context, null, packageInfoFlags);
    }

    /**
     * List SimplePackageInfo for all installed package
     */
    @NonNull
    public static List<SimplePackageInfo> listSimplePackageInfo(@NonNull Context context) {
        return listSimplePackageInfoByFilterInfoFlags(context, null, 0);
    }


    /* ************************************* listPackageName ***************************************** */


    /**
     * List the packageName of all installed package
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    @SuppressLint("QueryPermissionsNeeded")
    public static List<String> listPackageNameByFilterInfoFlags(
            @NonNull Context context, @Nullable PackageFilter packageFilter, @PackageInfoFlags int packageInfoFlags) {
        List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(packageInfoFlags);

        List<String> appsSet = new ArrayList<>(packageInfoList.size());
        for (PackageInfo packageInfo : packageInfoList) {
            if (packageFilter == null || packageFilter.accept(packageInfo)) {
                appsSet.add(packageInfo.packageName);
            }
        }
        return appsSet;
    }

    /**
     * List the packageName of all installed package by PackageTypeFlags and PackageInfoFlags
     *
     * @param packageTypeFlags see {@link PackageTypeFlags}
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<String> listPackageNameByTypeInfoFlags(
            @NonNull Context context, @PackageTypeFlags int packageTypeFlags, @PackageInfoFlags int packageInfoFlags) {
        return listPackageNameByFilterInfoFlags(
                context, new PackageFilterTypeFlagsImpl(context, packageTypeFlags), packageInfoFlags);
    }

    /**
     * List the packageName of all installed package by PackageFilter
     */
    @NonNull
    public static List<String> listPackageNameByFilter(@NonNull Context context, @Nullable PackageFilter packageFilter) {
        return listPackageNameByFilterInfoFlags(context, packageFilter, 0);
    }


    /**
     * List the packageName of all installed package by PackageTypeFlags
     *
     * @param packageTypeFlags see {@link PackageTypeFlags}
     */
    @NonNull
    public static List<String> listPackageNameByTypeFlags(@NonNull Context context, @PackageTypeFlags int packageTypeFlags) {
        return listPackageNameByFilterInfoFlags(context, new PackageFilterTypeFlagsImpl(context, packageTypeFlags), 0);
    }


    /**
     * List the packageName of all installed package by PackageInfoFlags
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static List<String> listPackageNameByInfoFlags(@NonNull Context context, @PackageInfoFlags int packageInfoFlags) {
        return listPackageNameByFilterInfoFlags(context, null, packageInfoFlags);
    }

    /**
     * List the packageName of all installed package
     */
    @NonNull
    public static List<String> listPackageName(@NonNull Context context) {
        return listPackageNameByFilterInfoFlags(context, null, 0);
    }


    /* ************************************* count ***************************************** */


    /**
     * Get the number of installed package by PackageFilter and PackageInfoFlags
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @SuppressLint("QueryPermissionsNeeded")
    public static int countPackageByFilterInfoFlags(
            @NonNull Context context, @Nullable PackageFilter packageFilter, @PackageInfoFlags int packageInfoFlags) {
        List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(packageInfoFlags);
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
     * Get the number of installed package by PackageTypeFlags and PackageInfoFlags
     *
     * @param packageTypeFlags see {@link PackageTypeFlags}
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    public static int countPackageByTypeInfoFlags(
            @NonNull Context context, @PackageTypeFlags int packageTypeFlags, @PackageInfoFlags int packageInfoFlags) {
        return countPackageByFilterInfoFlags(context, new PackageFilterTypeFlagsImpl(context, packageTypeFlags), packageInfoFlags);
    }

    /**
     * Get the number of installed package by PackageFilter
     */
    public static int countPackageByFilter(@NonNull Context context, @Nullable PackageFilter packageFilter) {
        return countPackageByFilterInfoFlags(context, packageFilter, 0);
    }

    /**
     * Get the number of installed package by PackageTypeFlags
     */
    public static int countPackageByTypeFlags(@NonNull Context context, @PackageTypeFlags int packageTypeFlags) {
        return countPackageByFilterInfoFlags(context, new PackageFilterTypeFlagsImpl(context, packageTypeFlags), 0);
    }

    /**
     * Get the number of installed package by PackageInfoFlags
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    public static int countPackageByInfoFlags(@NonNull Context context, @PackageInfoFlags int packageInfoFlags) {
        return countPackageByFilterInfoFlags(context, null, packageInfoFlags);
    }

    /**
     * Get the number of installed package
     */
    public static int countPackage(@NonNull Context context) {
        return countPackageByFilterInfoFlags(context, null, 0);
    }


    /* ************************************* getAllPackageVersionCodeMap ***************************************** */


    /**
     * Get the packageName and versionCode of all installed package by PackageFilter and PackageInfoFlags
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    @SuppressLint("QueryPermissionsNeeded")
    public static ArrayMap<String, Integer> getAllPackageVersionCodeMapByFilterInfoFlags(
            @NonNull Context context, @Nullable PackageFilter packageFilter, @PackageInfoFlags int packageInfoFlags) {
        List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(packageInfoFlags);
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
     * Get the packageName and versionCode of all installed package by PackageTypeFlags and PackageInfoFlags
     *
     * @param packageTypeFlags see {@link PackageTypeFlags}
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static ArrayMap<String, Integer> getAllPackageVersionCodeMapByTypeInfoFlags(
            @NonNull Context context, @PackageTypeFlags int packageTypeFlags, @PackageInfoFlags int packageInfoFlags) {
        return getAllPackageVersionCodeMapByFilterInfoFlags(
                context, new PackageFilterTypeFlagsImpl(context, packageTypeFlags), packageInfoFlags);
    }

    /**
     * Get the packageName and versionCode of all installed package by PackageFilter
     */
    @NonNull
    public static ArrayMap<String, Integer> getAllPackageVersionCodeMapByFilter(
            @NonNull Context context, @Nullable PackageFilter packageFilter) {
        return getAllPackageVersionCodeMapByFilterInfoFlags(context, packageFilter, 0);
    }

    /**
     * Get the packageName and versionCode of all installed package by PackageTypeFlags
     *
     * @param packageTypeFlags see {@link PackageTypeFlags}
     */
    @NonNull
    public static ArrayMap<String, Integer> getPackageVersionCodeMapByTypeFlags(
            @NonNull Context context, @PackageTypeFlags int packageTypeFlags) {
        return getAllPackageVersionCodeMapByFilterInfoFlags(
                context, new PackageFilterTypeFlagsImpl(context, packageTypeFlags), 0);
    }

    /**
     * Get the packageName and versionCode of all installed package by PackageInfoFlags
     *
     * @param packageInfoFlags see {@link PackageInfoFlags}
     */
    @NonNull
    public static ArrayMap<String, Integer> getAllPackageVersionCodeMapByInfoFlags(
            @NonNull Context context, @PackageInfoFlags int packageInfoFlags) {
        return getAllPackageVersionCodeMapByFilterInfoFlags(context, null, packageInfoFlags);
    }

    /**
     * Get the packageName and versionCode of all installed package
     */
    @NonNull
    public static ArrayMap<String, Integer> getAllPackageVersionCodeMap(@NonNull Context context) {
        return getAllPackageVersionCodeMapByFilterInfoFlags(context, null, 0);
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
}