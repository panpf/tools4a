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

package com.github.panpf.tools4a.packages.test;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.packages.AcceptPackageType;
import com.github.panpf.tools4a.packages.AppPackage;
import com.github.panpf.tools4a.packages.Packagex;
import com.github.panpf.tools4j.collections.Collectionx;
import com.github.panpf.tools4j.common.Predicate;
import com.github.panpf.tools4j.lang.Stringx;
import com.github.panpf.tools4j.premise.Premisex;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(AndroidJUnit4.class)
public class PackagexTest {

    @Test
    public void testIsInstalled() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertTrue(Packagex.isInstalled(context, context.getPackageName()));
        Assert.assertFalse(Packagex.isInstalled(context, context.getPackageName() + "_nonono"));
    }

    @Test
    public void testGetVersionCode() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        AppPackage appPackage = Premisex.requireNotNull(Packagex.getOne(context, AcceptPackageType.ALL_AND_EXCLUDE_SELF));

        Assert.assertTrue("versionCode: " + appPackage.versionCode, appPackage.versionCode > 0);
        Assert.assertEquals(appPackage.versionCode, Packagex.getVersionCode(context, appPackage.packageName));
        Assert.assertEquals(-1, Packagex.getVersionCodeOr(context, appPackage.packageName + "_nonono", -1));
    }

    @Test
    public void testGetVersionName() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        AppPackage appPackage = Premisex.requireNotNull(Packagex.getOne(context, AcceptPackageType.ALL_AND_EXCLUDE_SELF));

        Assert.assertTrue("versionName: " + appPackage.versionName, Stringx.isSafe(appPackage.versionName));
        Assert.assertEquals(appPackage.versionName, Packagex.getVersionName(context, appPackage.packageName));
        Assert.assertEquals("unknown", Packagex.getVersionNameOr(context, appPackage.packageName + "_nonono", "unknown"));
        Assert.assertNull(Packagex.getVersionNameOrNull(context, appPackage.packageName + "_nonono"));
    }

    @Test
    public void testGetPackage() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        AppPackage selfAppPackage = Premisex.requireNotNull(Packagex.get(context, context.getPackageName()));

        Assert.assertEquals(BuildConfig.APPLICATION_ID, selfAppPackage.packageName);
        Assert.assertTrue("name: " + selfAppPackage.name, Stringx.isSafe(selfAppPackage.name));
        Assert.assertNotNull(selfAppPackage.versionName);
        Assert.assertTrue("versionCode: " + selfAppPackage.versionCode, selfAppPackage.versionCode >= 0);
        Assert.assertTrue("packageFilePath: " + selfAppPackage.packageFilePath, Stringx.isSafe(selfAppPackage.packageFilePath));
        Assert.assertTrue("packageSize: " + selfAppPackage.packageSize, selfAppPackage.packageSize >= 0);
        Assert.assertTrue("packageLastModifiedTime: " + selfAppPackage.packageLastModifiedTime, selfAppPackage.packageLastModifiedTime >= 0);
        Assert.assertFalse(selfAppPackage.systemApp);
        Assert.assertTrue(selfAppPackage.enabled);

        AppPackage systemAppPackage = Premisex.requireNotNull(Packagex.get(context, Premisex.requireNotNull(Collectionx.find(Packagex.list(context, AcceptPackageType.ALL_AND_EXCLUDE_SELF), new Predicate<AppPackage>() {
            @Override
            public boolean accept(@NonNull AppPackage appPackage) {
                return appPackage.systemApp;
            }
        })).packageName));
        Assert.assertTrue(systemAppPackage.systemApp);

        Assert.assertNull(Packagex.getOrNull(context, context.getPackageName() + "_nonono"));
    }

    @Test
    public void testIsSystemApp() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        String systemAppPackageName = Premisex.requireNotNull(Collectionx.find(Packagex.list(context, AcceptPackageType.ALL_AND_EXCLUDE_SELF), new Predicate<AppPackage>() {
            @Override
            public boolean accept(@NonNull AppPackage appPackage) {
                return appPackage.systemApp;
            }
        })).packageName;

        Assert.assertTrue(Packagex.isSystemApp(context.getPackageManager().getApplicationInfo(systemAppPackageName, 0)));
        Assert.assertFalse(Packagex.isSystemApp(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0)));

        Assert.assertTrue(Packagex.isSystemApp(context, systemAppPackageName));
        Assert.assertTrue(Packagex.isSystemAppOr(context, context.getPackageName() + "_nonono", true));
    }

    @Test
    public void testListVersionCodePair() throws PackageManager.NameNotFoundException {
        final Context context = InstrumentationRegistry.getContext();
        final String selfPackageName = context.getPackageName();
        final Predicate<Pair<String, Integer>> selfPredicate = new Predicate<Pair<String, Integer>>() {
            @Override
            public boolean accept(@NonNull Pair<String, Integer> stringIntegerPair) {
                return stringIntegerPair.first.equals(selfPackageName);
            }
        };
        final Predicate<Pair<String, Integer>> systemAppPredicate = new Predicate<Pair<String, Integer>>() {
            @Override
            public boolean accept(@NonNull Pair<String, Integer> stringIntegerPair) {
                return Packagex.isSystemAppOr(context, stringIntegerPair.first, false);
            }
        };
        final Predicate<Pair<String, Integer>> userAppPredicate = new Predicate<Pair<String, Integer>>() {
            @Override
            public boolean accept(@NonNull Pair<String, Integer> stringIntegerPair) {
                return !Packagex.isSystemAppOr(context, stringIntegerPair.first, false);
            }
        };

        /*
         * ALL
         */
        List<Pair<String, Integer>> allApps = Packagex.listVersionCodePair(context, AcceptPackageType.ALL);
        int systemAppsInAllSize = Collectionx.count(allApps, systemAppPredicate);
        Assert.assertTrue(systemAppsInAllSize > 0);
        int userAppsInAllSize = Collectionx.count(allApps, userAppPredicate);
        Assert.assertTrue(userAppsInAllSize > 0);
        Assert.assertNotNull(Collectionx.find(allApps, selfPredicate));

        /*
         * ALL_AND_EXCLUDE_SELF
         */
        List<Pair<String, Integer>> allAndExcludeSelfApps = Packagex.listVersionCodePair(context, AcceptPackageType.ALL_AND_EXCLUDE_SELF);
        Assert.assertEquals(allApps.size(), allAndExcludeSelfApps.size() + 1);
        Assert.assertNull(Collectionx.find(allAndExcludeSelfApps, selfPredicate));


        /*
         * USER
         */
        List<Pair<String, Integer>> userApps = Packagex.listVersionCodePair(context, AcceptPackageType.USER);
        Assert.assertTrue(Collectionx.all(userApps, userAppPredicate));
        Assert.assertEquals(userAppsInAllSize, userApps.size());
        if (!Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(userApps, selfPredicate));
        } else {
            Assert.assertNull(Collectionx.find(userApps, selfPredicate));
        }

        /*
         * USER_AND_EXCLUDE_SELF
         */
        List<Pair<String, Integer>> userAndExcludeSelfApps = Packagex.listVersionCodePair(context, AcceptPackageType.USER_AND_EXCLUDE_SELF);
        Assert.assertTrue(Collectionx.all(userAndExcludeSelfApps, userAppPredicate));
        if (!Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertEquals(userApps.size(), userAndExcludeSelfApps.size() + 1);
        } else {
            Assert.assertEquals(userApps.size(), userAndExcludeSelfApps.size());
        }
        Assert.assertNull(Collectionx.find(userAndExcludeSelfApps, selfPredicate));


        /*
         * SYSTEM
         */
        List<Pair<String, Integer>> systemApps = Packagex.listVersionCodePair(context, AcceptPackageType.SYSTEM);
        Assert.assertTrue(Collectionx.all(systemApps, systemAppPredicate));
        Assert.assertEquals(systemAppsInAllSize, systemApps.size());
        Assert.assertEquals(allApps.size(), userApps.size() + systemApps.size());
        if (Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(systemApps, selfPredicate));
        } else {
            Assert.assertNull(Collectionx.find(systemApps, selfPredicate));
        }

        /*
         * SYSTEM_AND_EXCLUDE_SELF
         */
        List<Pair<String, Integer>> systemAndExcludeSelfApps = Packagex.listVersionCodePair(context, AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF);
        Assert.assertTrue(Collectionx.all(systemAndExcludeSelfApps, systemAppPredicate));
        if (Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertEquals(systemApps.size(), systemAndExcludeSelfApps.size() + 1);
        } else {
            Assert.assertEquals(systemApps.size(), systemAndExcludeSelfApps.size());
        }
        Assert.assertNull(Collectionx.find(systemAndExcludeSelfApps, selfPredicate));
        Assert.assertEquals(allAndExcludeSelfApps.size(), userAndExcludeSelfApps.size() + systemAndExcludeSelfApps.size());
    }

    @Test
    public void testGetVersionCodeMap() throws PackageManager.NameNotFoundException {
        final Context context = InstrumentationRegistry.getContext();
        final String selfPackageName = context.getPackageName();
        final Predicate<Map.Entry<String, Integer>> selfPredicate = new Predicate<Map.Entry<String, Integer>>() {
            @Override
            public boolean accept(@NonNull Map.Entry<String, Integer> stringIntegerPair) {
                return stringIntegerPair.getKey().equals(selfPackageName);
            }
        };
        final Predicate<Map.Entry<String, Integer>> systemAppPredicate = new Predicate<Map.Entry<String, Integer>>() {
            @Override
            public boolean accept(@NonNull Map.Entry<String, Integer> stringIntegerPair) {
                return Packagex.isSystemAppOr(context, stringIntegerPair.getKey(), false);
            }
        };
        final Predicate<Map.Entry<String, Integer>> userAppPredicate = new Predicate<Map.Entry<String, Integer>>() {
            @Override
            public boolean accept(@NonNull Map.Entry<String, Integer> stringIntegerPair) {
                return !Packagex.isSystemAppOr(context, stringIntegerPair.getKey(), false);
            }
        };

        /*
         * ALL
         */
        Set<Map.Entry<String, Integer>> allApps = Packagex.getVersionCodeMap(context, AcceptPackageType.ALL).entrySet();
        int systemAppsInAllSize = Collectionx.count(allApps, systemAppPredicate);
        Assert.assertTrue(systemAppsInAllSize > 0);
        int userAppsInAllSize = Collectionx.count(allApps, userAppPredicate);
        Assert.assertTrue(userAppsInAllSize > 0);
        Assert.assertNotNull(Collectionx.find(allApps, selfPredicate));

        /*
         * ALL_AND_EXCLUDE_SELF
         */
        Set<Map.Entry<String, Integer>> allAndExcludeSelfApps = Packagex.getVersionCodeMap(context, AcceptPackageType.ALL_AND_EXCLUDE_SELF).entrySet();
        Assert.assertEquals(allApps.size(), allAndExcludeSelfApps.size() + 1);
        Assert.assertNull(Collectionx.find(allAndExcludeSelfApps, selfPredicate));


        /*
         * USER
         */
        Set<Map.Entry<String, Integer>> userApps = Packagex.getVersionCodeMap(context, AcceptPackageType.USER).entrySet();
        Assert.assertTrue(Collectionx.all(userApps, userAppPredicate));
        Assert.assertEquals(userAppsInAllSize, userApps.size());
        if (!Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(userApps, selfPredicate));
        } else {
            Assert.assertNull(Collectionx.find(userApps, selfPredicate));
        }

        /*
         * USER_AND_EXCLUDE_SELF
         */
        Set<Map.Entry<String, Integer>> userAndExcludeSelfApps = Packagex.getVersionCodeMap(context, AcceptPackageType.USER_AND_EXCLUDE_SELF).entrySet();
        Assert.assertTrue(Collectionx.all(userAndExcludeSelfApps, userAppPredicate));
        if (!Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertEquals(userApps.size(), userAndExcludeSelfApps.size() + 1);
        } else {
            Assert.assertEquals(userApps.size(), userAndExcludeSelfApps.size());
        }
        Assert.assertNull(Collectionx.find(userAndExcludeSelfApps, selfPredicate));


        /*
         * SYSTEM
         */
        Set<Map.Entry<String, Integer>> systemApps = Packagex.getVersionCodeMap(context, AcceptPackageType.SYSTEM).entrySet();
        Assert.assertTrue(Collectionx.all(systemApps, systemAppPredicate));
        Assert.assertEquals(systemAppsInAllSize, systemApps.size());
        Assert.assertEquals(allApps.size(), userApps.size() + systemApps.size());
        if (Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(systemApps, selfPredicate));
        } else {
            Assert.assertNull(Collectionx.find(systemApps, selfPredicate));
        }

        /*
         * SYSTEM_AND_EXCLUDE_SELF
         */
        Set<Map.Entry<String, Integer>> systemAndExcludeSelfApps = Packagex.getVersionCodeMap(context, AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF).entrySet();
        Assert.assertTrue(Collectionx.all(systemAndExcludeSelfApps, systemAppPredicate));
        if (Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertEquals(systemApps.size(), systemAndExcludeSelfApps.size() + 1);
        } else {
            Assert.assertEquals(systemApps.size(), systemAndExcludeSelfApps.size());
        }
        Assert.assertNull(Collectionx.find(systemAndExcludeSelfApps, selfPredicate));
        Assert.assertEquals(allAndExcludeSelfApps.size(), userAndExcludeSelfApps.size() + systemAndExcludeSelfApps.size());
    }

    @Test
    public void testListPackageName() throws PackageManager.NameNotFoundException {
        final Context context = InstrumentationRegistry.getContext();
        final String selfPackageName = context.getPackageName();
        final Predicate<String> selfPredicate = new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String string) {
                return string.equals(selfPackageName);
            }
        };
        final Predicate<String> systemAppPredicate = new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String string) {
                return Packagex.isSystemAppOr(context, string, false);
            }
        };
        final Predicate<String> userAppPredicate = new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String string) {
                return !Packagex.isSystemAppOr(context, string, false);
            }
        };

        /*
         * ALL
         */
        List<String> allApps = Packagex.listPackageName(context, AcceptPackageType.ALL);
        int systemAppsInAllSize = Collectionx.count(allApps, systemAppPredicate);
        Assert.assertTrue(systemAppsInAllSize > 0);
        int userAppsInAllSize = Collectionx.count(allApps, userAppPredicate);
        Assert.assertTrue(userAppsInAllSize > 0);
        Assert.assertNotNull(Collectionx.find(allApps, selfPredicate));

        /*
         * ALL_AND_EXCLUDE_SELF
         */
        List<String> allAndExcludeSelfApps = Packagex.listPackageName(context, AcceptPackageType.ALL_AND_EXCLUDE_SELF);
        Assert.assertEquals(allApps.size(), allAndExcludeSelfApps.size() + 1);
        Assert.assertNull(Collectionx.find(allAndExcludeSelfApps, selfPredicate));


        /*
         * USER
         */
        List<String> userApps = Packagex.listPackageName(context, AcceptPackageType.USER);
        Assert.assertTrue(Collectionx.all(userApps, userAppPredicate));
        Assert.assertEquals(userAppsInAllSize, userApps.size());
        if (!Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(userApps, selfPredicate));
        } else {
            Assert.assertNull(Collectionx.find(userApps, selfPredicate));
        }

        /*
         * USER_AND_EXCLUDE_SELF
         */
        List<String> userAndExcludeSelfApps = Packagex.listPackageName(context, AcceptPackageType.USER_AND_EXCLUDE_SELF);
        Assert.assertTrue(Collectionx.all(userAndExcludeSelfApps, userAppPredicate));
        if (!Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertEquals(userApps.size(), userAndExcludeSelfApps.size() + 1);
        } else {
            Assert.assertEquals(userApps.size(), userAndExcludeSelfApps.size());
        }
        Assert.assertNull(Collectionx.find(userAndExcludeSelfApps, selfPredicate));


        /*
         * SYSTEM
         */
        List<String> systemApps = Packagex.listPackageName(context, AcceptPackageType.SYSTEM);
        Assert.assertTrue(Collectionx.all(systemApps, systemAppPredicate));
        Assert.assertEquals(systemAppsInAllSize, systemApps.size());
        Assert.assertEquals(allApps.size(), userApps.size() + systemApps.size());
        if (Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(systemApps, selfPredicate));
        } else {
            Assert.assertNull(Collectionx.find(systemApps, selfPredicate));
        }

        /*
         * SYSTEM_AND_EXCLUDE_SELF
         */
        List<String> systemAndExcludeSelfApps = Packagex.listPackageName(context, AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF);
        Assert.assertTrue(Collectionx.all(systemAndExcludeSelfApps, systemAppPredicate));
        if (Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertEquals(systemApps.size(), systemAndExcludeSelfApps.size() + 1);
        } else {
            Assert.assertEquals(systemApps.size(), systemAndExcludeSelfApps.size());
        }
        Assert.assertNull(Collectionx.find(systemAndExcludeSelfApps, selfPredicate));
        Assert.assertEquals(allAndExcludeSelfApps.size(), userAndExcludeSelfApps.size() + systemAndExcludeSelfApps.size());
    }

    @Test
    public void testList() throws PackageManager.NameNotFoundException {
        final Context context = InstrumentationRegistry.getContext();
        final String selfPackageName = context.getPackageName();
        final Predicate<AppPackage> selfPredicate = new Predicate<AppPackage>() {
            @Override
            public boolean accept(@NonNull AppPackage appPackage) {
                return appPackage.packageName.equals(selfPackageName);
            }
        };
        final Predicate<AppPackage> systemAppPredicate = new Predicate<AppPackage>() {
            @Override
            public boolean accept(@NonNull AppPackage appPackage) {
                return Packagex.isSystemAppOr(context, appPackage.packageName, false);
            }
        };
        final Predicate<AppPackage> userAppPredicate = new Predicate<AppPackage>() {
            @Override
            public boolean accept(@NonNull AppPackage appPackage) {
                return !Packagex.isSystemAppOr(context, appPackage.packageName, false);
            }
        };

        /*
         * ALL
         */
        List<AppPackage> allApps = Packagex.list(context, AcceptPackageType.ALL);
        int systemAppsInAllSize = Collectionx.count(allApps, systemAppPredicate);
        Assert.assertTrue(systemAppsInAllSize > 0);
        int userAppsInAllSize = Collectionx.count(allApps, userAppPredicate);
        Assert.assertTrue(userAppsInAllSize > 0);
        Assert.assertNotNull(Collectionx.find(allApps, selfPredicate));

        /*
         * ALL_AND_EXCLUDE_SELF
         */
        List<AppPackage> allAndExcludeSelfApps = Packagex.list(context, AcceptPackageType.ALL_AND_EXCLUDE_SELF);
        Assert.assertEquals(allApps.size(), allAndExcludeSelfApps.size() + 1);
        Assert.assertNull(Collectionx.find(allAndExcludeSelfApps, selfPredicate));


        /*
         * USER
         */
        List<AppPackage> userApps = Packagex.list(context, AcceptPackageType.USER);
        Assert.assertTrue(Collectionx.all(userApps, userAppPredicate));
        Assert.assertEquals(userAppsInAllSize, userApps.size());
        if (!Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(userApps, selfPredicate));
        } else {
            Assert.assertNull(Collectionx.find(userApps, selfPredicate));
        }

        /*
         * USER_AND_EXCLUDE_SELF
         */
        List<AppPackage> userAndExcludeSelfApps = Packagex.list(context, AcceptPackageType.USER_AND_EXCLUDE_SELF);
        Assert.assertTrue(Collectionx.all(userAndExcludeSelfApps, userAppPredicate));
        if (!Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertEquals(userApps.size(), userAndExcludeSelfApps.size() + 1);
        } else {
            Assert.assertEquals(userApps.size(), userAndExcludeSelfApps.size());
        }
        Assert.assertNull(Collectionx.find(userAndExcludeSelfApps, selfPredicate));


        /*
         * SYSTEM
         */
        List<AppPackage> systemApps = Packagex.list(context, AcceptPackageType.SYSTEM);
        Assert.assertTrue(Collectionx.all(systemApps, systemAppPredicate));
        Assert.assertEquals(systemAppsInAllSize, systemApps.size());
        Assert.assertEquals(allApps.size(), userApps.size() + systemApps.size());
        if (Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(systemApps, selfPredicate));
        } else {
            Assert.assertNull(Collectionx.find(systemApps, selfPredicate));
        }

        /*
         * SYSTEM_AND_EXCLUDE_SELF
         */
        List<AppPackage> systemAndExcludeSelfApps = Packagex.list(context, AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF);
        Assert.assertTrue(Collectionx.all(systemAndExcludeSelfApps, systemAppPredicate));
        if (Packagex.isSystemApp(context, selfPackageName)) {
            Assert.assertEquals(systemApps.size(), systemAndExcludeSelfApps.size() + 1);
        } else {
            Assert.assertEquals(systemApps.size(), systemAndExcludeSelfApps.size());
        }
        Assert.assertNull(Collectionx.find(systemAndExcludeSelfApps, selfPredicate));
        Assert.assertEquals(allAndExcludeSelfApps.size(), userAndExcludeSelfApps.size() + systemAndExcludeSelfApps.size());
    }

    @Test
    public void testGetOne() {
        final Context context = InstrumentationRegistry.getContext();

        AppPackage allAppPackage = Packagex.getOne(context, AcceptPackageType.ALL);
        Assert.assertNotNull(allAppPackage);

        AppPackage allAndExcludeSelfAppPackage = Packagex.getOne(context, AcceptPackageType.ALL_AND_EXCLUDE_SELF);
        Assert.assertNotNull(allAndExcludeSelfAppPackage);
        Assert.assertNotEquals(context.getPackageName(), allAndExcludeSelfAppPackage.packageName);

        AppPackage userAppPackage = Packagex.getOne(context, AcceptPackageType.USER);
        Assert.assertNotNull(userAppPackage);
        Assert.assertFalse(Packagex.isSystemAppOr(context, userAppPackage.packageName, false));
        Assert.assertFalse(Packagex.isSystemAppOr(context, userAppPackage.packageName, false));

        AppPackage userAndExcludeSelfAppPackage = Packagex.getOne(context, AcceptPackageType.USER_AND_EXCLUDE_SELF);
        Assert.assertNotNull(userAndExcludeSelfAppPackage);
        Assert.assertFalse(Packagex.isSystemAppOr(context, userAndExcludeSelfAppPackage.packageName, false));
        Assert.assertNotEquals(context.getPackageName(), userAndExcludeSelfAppPackage.packageName);

        AppPackage systemAppPackage = Packagex.getOne(context, AcceptPackageType.SYSTEM);
        Assert.assertNotNull(systemAppPackage);
        Assert.assertTrue(Packagex.isSystemAppOr(context, systemAppPackage.packageName, false));

        AppPackage systemAndExcludeSelfAppPackage = Packagex.getOne(context, AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF);
        Assert.assertNotNull(systemAndExcludeSelfAppPackage);
        Assert.assertTrue(Packagex.isSystemAppOr(context, systemAndExcludeSelfAppPackage.packageName, false));
        Assert.assertNotEquals(context.getPackageName(), systemAndExcludeSelfAppPackage.packageName);
    }

    @Test
    public void testCount() {
        final Context context = InstrumentationRegistry.getContext();

        int allCount = Packagex.count(context, AcceptPackageType.ALL);
        int allAndExcludeSelfCount = Packagex.count(context, AcceptPackageType.ALL_AND_EXCLUDE_SELF);

        int userCount = Packagex.count(context, AcceptPackageType.USER);
        int userAndExcludeSelfCount = Packagex.count(context, AcceptPackageType.USER_AND_EXCLUDE_SELF);

        int systemCount = Packagex.count(context, AcceptPackageType.SYSTEM);
        int systemAndExcludeSelfCount = Packagex.count(context, AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF);

        Assert.assertTrue(allCount > 0);
        Assert.assertEquals(allCount - 1, allAndExcludeSelfCount);
        Assert.assertEquals(userCount - 1, userAndExcludeSelfCount);
        Assert.assertEquals(systemCount, systemAndExcludeSelfCount);
        Assert.assertEquals(allCount, systemCount + userCount);
        Assert.assertEquals(allAndExcludeSelfCount, systemAndExcludeSelfCount + userAndExcludeSelfCount);
    }

    @Test
    public void testGetPackageFile() throws PackageManager.NameNotFoundException {
        final Context context = InstrumentationRegistry.getContext();

        File packageFile = Packagex.getPackageApkFile(context, context.getPackageName());
        Assert.assertTrue(packageFile.getPath(), packageFile.exists());

        Assert.assertNull(Packagex.getPackageApkFileOrNull(context, context.getPackageName() + "_nonono"));
    }

    @Test
    public void testGetAppSignatureBytes() throws PackageManager.NameNotFoundException {
        final Context context = InstrumentationRegistry.getContext();

        Assert.assertNotNull(Packagex.getAppSignatureBytes(context, context.getPackageName()));
        Assert.assertNull(Packagex.getAppSignatureBytesOrNull(context, context.getPackageName() + "_nonono"));
    }

    @Test
    public void testGetAppIcon() {
        final Context context = InstrumentationRegistry.getContext();

        Assert.assertNotNull(Packagex.getAppIconDrawable(context, context.getPackageName(), 0));
        Assert.assertNull(Packagex.getAppIconDrawable(context, context.getPackageName() + "_nonono", 0));
        Assert.assertNull(Packagex.getAppIconDrawable(context, context.getPackageName(), 1));
    }

    @Test
    public void testGetApkIcon() throws PackageManager.NameNotFoundException {
        final Context context = InstrumentationRegistry.getContext();

        String selfApkFilePath = Packagex.getPackageApkFile(context, context.getPackageName()).getPath();

        Assert.assertNotNull(Packagex.getApkIconDrawable(context, selfApkFilePath));
        Assert.assertNull(Packagex.getApkIconDrawable(context, selfApkFilePath + "_nonono"));
    }
}
