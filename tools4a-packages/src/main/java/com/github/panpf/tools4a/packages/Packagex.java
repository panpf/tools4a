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
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.util.Pair;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.collection.ArrayMap;

import com.github.panpf.tools4a.fileprovider.FileProviderx;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

// todo 重构 package 对现在的 api 不满意
public class Packagex {

    private Packagex() {
    }


    /**
     * Request to install apk
     *
     * @return false: Request failed
     */
    @RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
    public static boolean requestInstall(@NonNull Context context, @NonNull Uri apkFileUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW).addCategory(Intent.CATEGORY_DEFAULT)
                .setDataAndType(apkFileUri, "application/vnd.android.package-archive")
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        ;
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
     * Request to install apk
     *
     * @return false: Request failed
     */
    @RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
    public static boolean requestInstall(@NonNull Context context, @NonNull File apkFile) {
        Intent intent = createInstallAppIntent(context, apkFile);
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
     * Request to uninstall app
     *
     * @return false: Request failed
     */
    @RequiresPermission(value = Manifest.permission.REQUEST_DELETE_PACKAGES, conditional = true)
    public static boolean requestUninstall(@NonNull Context context, @NonNull String packageName) {
        Intent intent = createUninstallAppIntent(packageName);
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
     * Return true if the app with the specified packageName is installed
     */
    // todo 支持 flags ，并使用注解 @ApplicationInfoFlags
    public static boolean isInstalled(@NonNull Context context, @NonNull String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }


    /**
     * Get the versionCode of the app for the specified packageName
     */
    public static int getVersionCode(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        return context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA).versionCode;
    }

    /**
     * Get the versionCode of the app for the specified packageName, return to defaultValue if not installed
     */
    // todo 支持 long versionCode
    public static int getVersionCodeOr(@NonNull Context context, @NonNull String packageName, int defaultValue) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }

        return packageInfo.versionCode;
    }


    /**
     * Get the versionName of the app for the specified packageName, return to defaultValue if not installed
     */
    @NonNull
    public static String getVersionNameOr(@NonNull Context context, @NonNull String packageName, @NonNull String defaultValue) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }
        return packageInfo.versionName != null && !packageInfo.versionName.equals("") ? packageInfo.versionName : defaultValue;
    }

    /**
     * Get the versionName of the app for the specified packageName, return to null if not installed
     */
    @NonNull
    public static String getVersionNameOrEmpty(@NonNull Context context, @NonNull String packageName) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return "";
        }

        return packageInfo.versionName != null && !packageInfo.versionName.equals("") ? packageInfo.versionName : "";
    }

    /**
     * Get the versionName of the app for the specified packageName, return to null if not installed
     */
    @Nullable
    public static String getVersionNameOrNull(@NonNull Context context, @NonNull String packageName) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return null;
        }

        return packageInfo.versionName != null && !packageInfo.versionName.equals("") ? packageInfo.versionName : null;
    }


    /**
     * Get information about the app with the specified packageName
     */
    @NonNull
    public static AppPackage get(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        return packageInfoToAppPackage(context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA), context.getPackageManager());
    }

    /**
     * Get information about the app with the specified packageName, return to null if not installed
     */
    @Nullable
    public static AppPackage getOrNull(@NonNull Context context, @NonNull String packageName) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return null;
        }

        return packageInfoToAppPackage(packageInfo, packageManager);
    }


    /**
     * Return true if it is a system APP
     */
    public static boolean isSystemApp(@NonNull ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    /**
     * Return true if the app with the specified packageName is the system APP
     */
    public static boolean isSystemApp(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    /**
     * Return true if the app with the specified packageName is the system APP, return to defaultValue if not installed
     */
    public static boolean isSystemAppOr(@NonNull Context context, @NonNull String packageName, boolean defaultValue) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return defaultValue;
        }
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }


    /**
     * List the packageName and versionCode of all installed APPs
     *
     * @param acceptPackageType Accepted package type, see {@link AcceptPackageType}
     */
    @NonNull
    public static List<Pair<String, Integer>> listVersionCodePair(@NonNull Context context, @AcceptPackageType int acceptPackageType) {
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = context.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager crashes internally on dazen X7 4.4.4 and Coolpad Y803-8 5.1 models
        }
        if (packageInfoList == null || packageInfoList.isEmpty()) {
            return new ArrayList<>(0);
        }

        List<Pair<String, Integer>> appsSet = new ArrayList<>(packageInfoList.size());
        for (PackageInfo packageInfo : packageInfoList) {
            if ((acceptPackageType == AcceptPackageType.ALL_AND_EXCLUDE_SELF || acceptPackageType == AcceptPackageType.USER_AND_EXCLUDE_SELF || acceptPackageType == AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF)
                    && context.getPackageName().equals(packageInfo.packageName)) {
                continue;
            }

            boolean isSystemApp = isSystemApp(packageInfo.applicationInfo);
            if ((acceptPackageType == AcceptPackageType.USER || acceptPackageType == AcceptPackageType.USER_AND_EXCLUDE_SELF) && isSystemApp) {
                continue;
            } else if ((acceptPackageType == AcceptPackageType.SYSTEM || acceptPackageType == AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF) && !isSystemApp) {
                continue;
            }

            appsSet.add(new Pair<>(packageInfo.packageName, packageInfo.versionCode));
        }
        return appsSet;
    }

    /**
     * Get the packageName and versionCode of all installed apps Map
     *
     * @param acceptPackageType Accepted package type, see {@link AcceptPackageType}
     */
    @NonNull
    public static ArrayMap<String, Integer> getVersionCodeMap(@NonNull Context context, @AcceptPackageType int acceptPackageType) {
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = context.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager crashes internally on dazen X7 4.4.4 and Coolpad Y803-8 5.1 models
        }
        if (packageInfoList == null || packageInfoList.isEmpty()) {
            return new ArrayMap<>(0);
        }

        ArrayMap<String, Integer> appsSet = new ArrayMap<>();
        for (PackageInfo packageInfo : packageInfoList) {
            if ((acceptPackageType == AcceptPackageType.ALL_AND_EXCLUDE_SELF || acceptPackageType == AcceptPackageType.USER_AND_EXCLUDE_SELF || acceptPackageType == AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF)
                    && context.getPackageName().equals(packageInfo.packageName)) {
                continue;
            }

            boolean isSystemApp = isSystemApp(packageInfo.applicationInfo);
            if ((acceptPackageType == AcceptPackageType.USER || acceptPackageType == AcceptPackageType.USER_AND_EXCLUDE_SELF) && isSystemApp) {
                continue;
            } else if ((acceptPackageType == AcceptPackageType.SYSTEM || acceptPackageType == AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF) && !isSystemApp) {
                continue;
            }

            appsSet.put(packageInfo.packageName, packageInfo.versionCode);
        }
        return appsSet;
    }

    /**
     * List the packageName of all installed apps
     *
     * @param acceptPackageType Accepted package type, see {@link AcceptPackageType}
     */
    @NonNull
    public static List<String> listPackageName(@NonNull Context context, @AcceptPackageType int acceptPackageType) {
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = context.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager crashes internally on dazen X7 4.4.4 and Coolpad Y803-8 5.1 models
        }
        if (packageInfoList != null && !packageInfoList.isEmpty()) {
            List<String> appsSet = new ArrayList<>();
            for (PackageInfo packageInfo : packageInfoList) {
                if ((acceptPackageType == AcceptPackageType.ALL_AND_EXCLUDE_SELF || acceptPackageType == AcceptPackageType.USER_AND_EXCLUDE_SELF || acceptPackageType == AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF)
                        && context.getPackageName().equals(packageInfo.packageName)) {
                    continue;
                }

                boolean isSystemApp = isSystemApp(packageInfo.applicationInfo);
                if ((acceptPackageType == AcceptPackageType.USER || acceptPackageType == AcceptPackageType.USER_AND_EXCLUDE_SELF) && isSystemApp) {
                    continue;
                } else if ((acceptPackageType == AcceptPackageType.SYSTEM || acceptPackageType == AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF) && !isSystemApp) {
                    continue;
                }

                appsSet.add(packageInfo.packageName);
            }
            return appsSet;
        } else {
            return new ArrayList<>(0);
        }
    }

    /**
     * List information for all installed apps
     *
     * @param acceptPackageType Accepted package type, see {@link AcceptPackageType}
     * @param size              How many apps to get. -1: all
     */
    @NonNull
    public static List<AppPackage> list(@NonNull Context context, @AcceptPackageType int acceptPackageType, int size) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager crashes internally on dazen X7 4.4.4 and Coolpad Y803-8 5.1 models
        }
        if (packageInfoList == null || packageInfoList.isEmpty()) {
            return new ArrayList<>(0);
        }

        ArrayList<AppPackage> packageArrayList = new ArrayList<>(size > 0 ? size : packageInfoList.size());
        int index = 0;
        for (PackageInfo packageInfo : packageInfoList) {
            if ((acceptPackageType == AcceptPackageType.ALL_AND_EXCLUDE_SELF || acceptPackageType == AcceptPackageType.USER_AND_EXCLUDE_SELF || acceptPackageType == AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF)
                    && context.getPackageName().equals(packageInfo.packageName)) {
                continue;
            }

            boolean isSystemApp = isSystemApp(packageInfo.applicationInfo);
            if ((acceptPackageType == AcceptPackageType.USER || acceptPackageType == AcceptPackageType.USER_AND_EXCLUDE_SELF) && isSystemApp) {
                continue;
            } else if ((acceptPackageType == AcceptPackageType.SYSTEM || acceptPackageType == AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF) && !isSystemApp) {
                continue;
            }

            AppPackage appPackage = packageInfoToAppPackage(packageInfo, packageManager);
            packageArrayList.add(appPackage);
            index++;
            if (size > 0 && index >= size) {
                break;
            }
        }
        return packageArrayList;
    }

    /**
     * List information for all installed apps
     *
     * @param acceptPackageType Accepted package type, see {@link AcceptPackageType}
     */
    @NonNull
    public static List<AppPackage> list(@NonNull Context context, @AcceptPackageType int acceptPackageType) {
        return list(context, acceptPackageType, -1);
    }


    /**
     * Get information about an app
     *
     * @param acceptPackageType Accepted package type, see {@link AcceptPackageType}
     */
    @Nullable
    public static AppPackage getOne(@NonNull Context context, @AcceptPackageType int acceptPackageType) {
        List<AppPackage> appPackageList = list(context, acceptPackageType, 1);
        return appPackageList.size() >= 1 ? appPackageList.get(0) : null;
    }


    /**
     * Get the number of installed apps
     *
     * @param acceptPackageType Accepted package type, see {@link AcceptPackageType}
     */
    // todo 提供 Predicate 可自定义筛选条件
    public static int count(@NonNull Context context, @AcceptPackageType int acceptPackageType) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager crashes internally on dazen X7 4.4.4 and Coolpad Y803-8 5.1 models
        }
        if (packageInfoList == null || packageInfoList.isEmpty()) {
            return 0;
        }

        int count = 0;
        for (PackageInfo packageInfo : packageInfoList) {
            if ((acceptPackageType == AcceptPackageType.ALL_AND_EXCLUDE_SELF || acceptPackageType == AcceptPackageType.USER_AND_EXCLUDE_SELF || acceptPackageType == AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF)
                    && context.getPackageName().equals(packageInfo.packageName)) {
                continue;
            }

            boolean isSystemApp = isSystemApp(packageInfo.applicationInfo);
            if ((acceptPackageType == AcceptPackageType.USER || acceptPackageType == AcceptPackageType.USER_AND_EXCLUDE_SELF) && isSystemApp) {
                continue;
            } else if ((acceptPackageType == AcceptPackageType.SYSTEM || acceptPackageType == AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF) && !isSystemApp) {
                continue;
            }

            count++;
        }
        return count;
    }


    @NonNull
    public static AppPackage packageInfoToAppPackage(@NonNull PackageInfo packageInfo, @NonNull PackageManager packageManager) {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        if (applicationInfo == null) throw new IllegalStateException("applicationInfo is null");
        CharSequence label = applicationInfo.loadLabel(packageManager);
        File packageFile = new File(applicationInfo.sourceDir);
        final String packageName = applicationInfo.packageName;
        final String versionName = packageInfo.versionName;
        return new AppPackage(label.toString(), packageName != null ? packageName : "", packageInfo.versionCode,
                versionName != null ? versionName : "", packageFile.getPath(), packageFile.length(),
                packageFile.lastModified(), isSystemApp(applicationInfo), applicationInfo.enabled);
    }


    /**
     * Get the apk file of the app with the specified packageName
     */
    @NonNull
    public static File getPackageApkFile(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        return new File(applicationInfo.sourceDir);
    }

    /**
     * Get the apk file of the app with the specified packageName, return to null if not installed
     */
    @Nullable
    public static File getPackageApkFileOrNull(@NonNull Context context, @NonNull String packageName) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return new File(applicationInfo.sourceDir);
    }


    /**
     * Get the signature data of the app with the specified packageName
     */
    @NonNull
    public static byte[] getAppSignatureBytes(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
        if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
            return packageInfo.signatures[0].toByteArray();
        } else {
            throw new IllegalArgumentException(packageName + " signatures is empty");
        }
    }

    /**
     * Get the signature data of the app with the specified packageName, return to null if not installed
     */
    @Nullable
    public static byte[] getAppSignatureBytesOrNull(@NonNull Context context, @NonNull String packageName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                return packageInfo.signatures[0].toByteArray();
            } else {
                return null;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Get the icon Drawable of the app of the specified packageName
     *
     * @param versionCode App versionCode. Returns null if versionCode is inconsistent, -1: ignores versionCode
     */
    @Nullable
    public static Drawable getAppIconDrawable(@NonNull Context context, @NonNull String packageName, int versionCode) {
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return null;
        }

        if (versionCode > -1 && packageInfo.versionCode != versionCode) {
            return null;
        }

        return packageInfo.applicationInfo.loadIcon(pm);
    }


    /**
     * Get the icon Drawable of the specified apk file
     */
    @Nullable
    public static Drawable getApkIconDrawable(@NonNull Context context, @NonNull String apkFilePath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = pm.getPackageArchiveInfo(apkFilePath, PackageManager.GET_META_DATA);
        if (packageInfo == null) {
            return null;
        }

        packageInfo.applicationInfo.sourceDir = apkFilePath;
        packageInfo.applicationInfo.publicSourceDir = apkFilePath;
        return packageInfo.applicationInfo.loadIcon(pm);
    }


    /**
     * Create an Intent that opens the specified app install page
     *
     * @param apkFileUri APK file uri
     */
    @NonNull
    @RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
    public static Intent createInstallAppIntent(@NonNull Uri apkFileUri) {
        return new Intent(Intent.ACTION_VIEW).addCategory(Intent.CATEGORY_DEFAULT)
                .setDataAndType(apkFileUri, "application/vnd.android.package-archive")
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Prepare for FileProvider on Android N
    }

    /**
     * Create an Intent that opens the specified app install page
     */
    @NonNull
    @RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
    public static Intent createInstallAppIntent(@NonNull Context context, @NonNull File apkFile) {
        return createInstallAppIntent(FileProviderx.getShareFileUri(context, apkFile));
    }

    /**
     * Create an Intent that opens the specified app uninstall page
     *
     * @param packageName App package name
     */
    @NonNull
    @RequiresPermission(value = Manifest.permission.REQUEST_DELETE_PACKAGES, conditional = true)
    public static Intent createUninstallAppIntent(@NonNull String packageName) {
        return new Intent(Intent.ACTION_DELETE, Uri.fromParts("package", packageName, null));
    }

    /**
     * Create an intent that opens the specified app
     *
     * @param packageName App package name
     */
    @Nullable
    public static Intent createLaunchAppIntent(@NonNull Context context, @NonNull String packageName) {
        return context.getPackageManager().getLaunchIntentForPackage(packageName);
    }

    /**
     * Create an Intent that opens the specified app details page
     *
     * @param packageName App package name
     */
    @NonNull
    public static Intent createAppDetailInSystemIntent(@NonNull String packageName) {
        return new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .setData(Uri.fromParts("package", packageName, null));
    }

    /**
     * Return true if it is a test APP
     */
    // todo Complete test
    public static boolean isTestApp(@NonNull PackageInfo packageInfo){
        return packageInfo.instrumentation != null && packageInfo.instrumentation.length > 0;
    }

    /**
     * Return true if it is a test APP
     */
    public static boolean isTestApp(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_INSTRUMENTATION);
        return packageInfo.instrumentation != null && packageInfo.instrumentation.length > 0;
    }

    /**
     * Return true if it is a test APP
     */
    public static boolean isTestAppOr(@NonNull Context context, @NonNull String packageName, boolean defaultValue) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_INSTRUMENTATION);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return defaultValue;
        }
        return packageInfo.instrumentation != null && packageInfo.instrumentation.length > 0;
    }

//    @IntDef(flag = true, value = {
//            PackageManager.GET_META_DATA,
//            PackageManager.GET_SHARED_LIBRARY_FILES,
//            PackageManager.MATCH_UNINSTALLED_PACKAGES,
//            PackageManager.MATCH_SYSTEM_ONLY,
//            PackageManager.MATCH_DISABLED_COMPONENTS,
//            PackageManager.MATCH_DISABLED_UNTIL_USED_COMPONENTS,
//    })
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface ApplicationInfoFlags {}
}