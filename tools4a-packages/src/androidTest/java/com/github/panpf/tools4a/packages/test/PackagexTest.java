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
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.fileprovider.FileProviderx;
import com.github.panpf.tools4a.packages.PackageFilter;
import com.github.panpf.tools4a.packages.PackageFilterFlags;
import com.github.panpf.tools4a.packages.Packagex;
import com.github.panpf.tools4a.packages.SimplePackageInfo;
import com.github.panpf.tools4j.collections.Collectionx;
import com.github.panpf.tools4j.collections.Mapx;
import com.github.panpf.tools4j.io.Filex;
import com.github.panpf.tools4j.lang.Stringx;
import com.github.panpf.tools4j.premise.Premisex;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class PackagexTest {

    @Test
    public void testIsPackageInstalled() {
        Context context = InstrumentationRegistry.getContext();

        assertTrue(Packagex.isPackageInstalled(context, context.getPackageName(), PackageManager.MATCH_UNINSTALLED_PACKAGES));
        assertFalse(Packagex.isPackageInstalled(context, context.getPackageName() + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES));

        assertTrue(Packagex.isPackageInstalled(context, context.getPackageName()));
        assertFalse(Packagex.isPackageInstalled(context, context.getPackageName() + "_nonono"));
    }

    @Test
    public void testIsSystemPackage() {
        Context context = InstrumentationRegistry.getContext();

        String systemAppPackageName = Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM)).packageName;
        String selfAppPackageName = context.getPackageName();

        try {
            assertTrue(Packagex.isSystemPackage(context, systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
            assertFalse(Packagex.isSystemPackage(context, selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        try {
            Packagex.isSystemPackage(context, systemAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES);
            fail();
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        try {
            assertTrue(Packagex.isSystemPackage(context, systemAppPackageName));
            assertFalse(Packagex.isSystemPackage(context, selfAppPackageName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        try {
            Packagex.isSystemPackage(context, systemAppPackageName + "_nonono");
            fail();
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        assertTrue(Packagex.isSystemPackageOr(context, systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false));
        assertFalse(Packagex.isSystemPackageOr(context, selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false));
        assertFalse(Packagex.isSystemPackageOr(context, selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, true));
        assertTrue(Packagex.isSystemPackageOr(context, systemAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, true));
        assertFalse(Packagex.isSystemPackageOr(context, systemAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, false));

        assertTrue(Packagex.isSystemPackageOr(context, systemAppPackageName, false));
        assertFalse(Packagex.isSystemPackageOr(context, selfAppPackageName, false));
        assertFalse(Packagex.isSystemPackageOr(context, selfAppPackageName, true));
        assertTrue(Packagex.isSystemPackageOr(context, systemAppPackageName + "_nonono", true));
        assertFalse(Packagex.isSystemPackageOr(context, systemAppPackageName + "_nonono", false));
    }

    @Test
    public void testIsDebuggablePackage() {
        Context context = InstrumentationRegistry.getContext();

        String systemAppPackageName = Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM)).packageName;
        String userAppPackageName = Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context,
                PackageFilterFlags.ONLY_USER | PackageFilterFlags.EXCLUDE_SELF | PackageFilterFlags.EXCLUDE_DEBUGGABLE)).packageName;
        String debuggableAppPackageName = Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context, PackageFilterFlags.ONLY_DEBUGGABLE)).packageName;
        String selfAppPackageName = context.getPackageName();

        try {
            assertFalse(Packagex.isDebuggablePackage(context, systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
            assertFalse(Packagex.isDebuggablePackage(context, userAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
            assertTrue(Packagex.isDebuggablePackage(context, selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
            assertTrue(Packagex.isDebuggablePackage(context, debuggableAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        } catch (PackageManager.NameNotFoundException e) {
            fail();
        }
        try {
            Packagex.isDebuggablePackage(context, systemAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES);
            fail();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assertFalse(Packagex.isDebuggablePackage(context, systemAppPackageName));
            assertFalse(Packagex.isDebuggablePackage(context, userAppPackageName));
            assertTrue(Packagex.isDebuggablePackage(context, selfAppPackageName));
            assertTrue(Packagex.isDebuggablePackage(context, debuggableAppPackageName));
        } catch (PackageManager.NameNotFoundException e) {
            fail();
        }
        try {
            Packagex.isDebuggablePackage(context, systemAppPackageName + "_nonono");
            fail();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        assertFalse(Packagex.isDebuggablePackageOr(context, systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false));
        assertFalse(Packagex.isDebuggablePackageOr(context, userAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false));
        assertTrue(Packagex.isDebuggablePackageOr(context, selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false));
        assertTrue(Packagex.isDebuggablePackageOr(context, debuggableAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false));
        assertFalse(Packagex.isDebuggablePackageOr(context, selfAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, false));
        assertTrue(Packagex.isDebuggablePackageOr(context, selfAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, true));

        assertFalse(Packagex.isDebuggablePackageOr(context, systemAppPackageName, false));
        assertFalse(Packagex.isDebuggablePackageOr(context, userAppPackageName, false));
        assertTrue(Packagex.isDebuggablePackageOr(context, selfAppPackageName, false));
        assertTrue(Packagex.isDebuggablePackageOr(context, debuggableAppPackageName, false));
        assertFalse(Packagex.isDebuggablePackageOr(context, selfAppPackageName + "_nonono", false));
        assertTrue(Packagex.isDebuggablePackageOr(context, selfAppPackageName + "_nonono", true));
    }

    @Test
    public void testIsJUnitTestPackage() {
        Context context = InstrumentationRegistry.getContext();

        String systemAppPackageName = Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM)).packageName;
        String userAppPackageName = Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context,
                PackageFilterFlags.ONLY_USER | PackageFilterFlags.ONLY_RELEASE | PackageFilterFlags.EXCLUDE_SELF)).packageName;
        String selfAppPackageName = context.getPackageName();

        try {
            assertFalse(Packagex.isJunitTestPackage(context, systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
            assertFalse(Packagex.isJunitTestPackage(context, userAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
            assertTrue(Packagex.isJunitTestPackage(context, selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        } catch (PackageManager.NameNotFoundException e) {
            fail();
        }
        try {
            Packagex.isJunitTestPackage(context, systemAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES);
            fail();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assertFalse(Packagex.isJunitTestPackage(context, systemAppPackageName));
            assertFalse(Packagex.isJunitTestPackage(context, userAppPackageName));
            assertTrue(Packagex.isJunitTestPackage(context, selfAppPackageName));
        } catch (PackageManager.NameNotFoundException e) {
            fail();
        }
        try {
            Packagex.isJunitTestPackage(context, systemAppPackageName + "_nonono");
            fail();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        assertFalse(Packagex.isJunitTestPackageOr(context, systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false));
        assertFalse(Packagex.isJunitTestPackageOr(context, userAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false));
        assertTrue(Packagex.isJunitTestPackageOr(context, selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false));
        assertFalse(Packagex.isJunitTestPackageOr(context, selfAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, false));
        assertTrue(Packagex.isJunitTestPackageOr(context, selfAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, true));

        assertFalse(Packagex.isJunitTestPackageOr(context, systemAppPackageName, false));
        assertFalse(Packagex.isJunitTestPackageOr(context, userAppPackageName, false));
        assertTrue(Packagex.isJunitTestPackageOr(context, selfAppPackageName, false));
        assertFalse(Packagex.isJunitTestPackageOr(context, selfAppPackageName + "_nonono", false));
        assertTrue(Packagex.isJunitTestPackageOr(context, selfAppPackageName + "_nonono", true));
    }

    @Test
    public void testCreateInstallPackageIntent() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        File file = Packagex.getPackageApkFile(context, context.getPackageName());

        Intent installAppIntent1 = Packagex.createInstallPackageIntent(FileProviderx.getShareFileUri(context, file));
        assertEquals(Intent.ACTION_VIEW, installAppIntent1.getAction());
        assertEquals(Intent.CATEGORY_DEFAULT, Collectionx.find(installAppIntent1.getCategories(), s -> Stringx.equals(s, Intent.CATEGORY_DEFAULT)));
        assertEquals(
                FileProviderx.getShareFileUri(context, file).toString(),
                Premisex.requireNotNull(installAppIntent1.getData()).toString()
        );
        assertEquals("application/vnd.android.package-archive", installAppIntent1.getType());
        assertTrue((installAppIntent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

        Intent installAppIntent2 = Packagex.createInstallPackageIntent(context, file);
        assertEquals(Intent.ACTION_VIEW, installAppIntent2.getAction());
        assertNotNull(Collectionx.find(installAppIntent2.getCategories(), s -> Stringx.equals(s, Intent.CATEGORY_DEFAULT)));
        assertEquals(
                FileProviderx.getShareFileUri(context, file).toString(),
                Premisex.requireNotNull(installAppIntent2.getData()).toString()
        );
        assertEquals("application/vnd.android.package-archive", installAppIntent2.getType());
        assertTrue((installAppIntent2.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);
    }

    @Test
    public void testCreateUninstallPackageIntent() {
        Context context = InstrumentationRegistry.getContext();

        Intent uninstallAppIntent = Packagex.createUninstallPackageIntent(context.getPackageName());
        assertEquals(Intent.ACTION_DELETE, uninstallAppIntent.getAction());
        assertEquals(
                Uri.fromParts("package", context.getPackageName(), null).toString(),
                Premisex.requireNotNull(uninstallAppIntent.getData()).toString()
        );
    }

    @Test
    public void testCreateLaunchPackageIntent() {
        Context context = InstrumentationRegistry.getContext();

        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"));
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(webIntent, 0);
        ResolveInfo resolveInfo = Premisex.requireNotNull(Collectionx.firstOrNull(resolveInfos));
        String webBrowserPackageName = resolveInfo.activityInfo.packageName;

        Intent launchAppIntent = Premisex.requireNotNull(Packagex.createLaunchPackageIntent(context, webBrowserPackageName));
        assertEquals(Intent.ACTION_MAIN, launchAppIntent.getAction());
        assertNotNull(Collectionx.find(launchAppIntent.getCategories(), s -> Stringx.equals(s, Intent.CATEGORY_LAUNCHER)));
        assertEquals(webBrowserPackageName, launchAppIntent.getPackage());
    }

    @Test
    public void testCreateApplicationDetailsSettingsIntent() {
        Context context = InstrumentationRegistry.getContext();

        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"));
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(webIntent, 0);
        ResolveInfo resolveInfo = Premisex.requireNotNull(Collectionx.firstOrNull(resolveInfos));
        String webBrowserPackageName = resolveInfo.activityInfo.packageName;

        Intent appDetailInSystemIntent = Packagex.createApplicationDetailsSettingsIntent(webBrowserPackageName);
        assertEquals(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, appDetailInSystemIntent.getAction());
        assertEquals(
                Uri.fromParts("package", webBrowserPackageName, null).toString(),
                Premisex.requireNotNull(appDetailInSystemIntent.getData()).toString()
        );
    }

    @Test
    public void testGetPackage() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        SimplePackageInfo selfAppPackage = Premisex.requireNotNull(Packagex.getPackage(context, context.getPackageName(), PackageManager.MATCH_UNINSTALLED_PACKAGES));
        assertEquals("com.github.panpf.tools4a.packages.test", selfAppPackage.packageName);
        assertTrue("name: " + selfAppPackage.name, Stringx.isSafe(selfAppPackage.name));
        assertNotNull(selfAppPackage.versionName);
        assertTrue("versionCode: " + selfAppPackage.versionCode, selfAppPackage.versionCode >= 0);
        assertTrue("packageFilePath: " + selfAppPackage.packageFilePath, Stringx.isSafe(selfAppPackage.packageFilePath));
        assertTrue("packageFileLength: " + selfAppPackage.packageFileLength, selfAppPackage.packageFileLength >= 0);
        assertTrue("packageFileLastModifiedTime: " + selfAppPackage.packageFileLastModifiedTime, selfAppPackage.packageFileLastModifiedTime >= 0);
        assertFalse(selfAppPackage.isSystemPackage());
        assertTrue(selfAppPackage.enabled);

        SimplePackageInfo selfAppPackage1 = Premisex.requireNotNull(Packagex.getPackage(context, context.getPackageName()));
        assertEquals("com.github.panpf.tools4a.packages.test", selfAppPackage1.packageName);
        assertTrue("name: " + selfAppPackage1.name, Stringx.isSafe(selfAppPackage1.name));
        assertNotNull(selfAppPackage1.versionName);
        assertTrue("versionCode: " + selfAppPackage1.versionCode, selfAppPackage1.versionCode >= 0);
        assertTrue("packageFilePath: " + selfAppPackage1.packageFilePath, Stringx.isSafe(selfAppPackage1.packageFilePath));
        assertTrue("packageFileLength: " + selfAppPackage1.packageFileLength, selfAppPackage1.packageFileLength >= 0);
        assertTrue("packageFileLastModifiedTime: " + selfAppPackage1.packageFileLastModifiedTime, selfAppPackage1.packageFileLastModifiedTime >= 0);
        assertFalse(selfAppPackage1.isSystemPackage());
        assertTrue(selfAppPackage1.enabled);

        SimplePackageInfo systemAppPackage = Premisex.requireNotNull(Packagex.getPackage(context,
                Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM)).packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        assertTrue(systemAppPackage.isSystemPackage());

        SimplePackageInfo systemAppPackage1 = Premisex.requireNotNull(Packagex.getPackage(context,
                Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM)).packageName));
        assertTrue(systemAppPackage1.isSystemPackage());

        assertNotNull(Packagex.getPackageOrNull(context, context.getPackageName(), PackageManager.MATCH_UNINSTALLED_PACKAGES));
        assertNull(Packagex.getPackageOrNull(context, context.getPackageName() + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES));

        assertNotNull(Packagex.getPackageOrNull(context, context.getPackageName()));
        assertNull(Packagex.getPackageOrNull(context, context.getPackageName() + "_nonono"));
    }

    @Test
    public void testGetVersionCode() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        String userPackageName = Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context,
                PackageFilterFlags.ONLY_USER | PackageFilterFlags.ONLY_RELEASE | PackageFilterFlags.EXCLUDE_SELF)).packageName;
        String selfPackageName = context.getPackageName();

        assertEquals(0, Packagex.getPackageVersionCode(context, selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        assertNotEquals(0, Packagex.getPackageVersionCode(context, userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        try {
            Packagex.getPackageVersionCode(context, selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES);
            fail();
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        assertEquals(0, Packagex.getPackageVersionCode(context, selfPackageName));
        assertNotEquals(0, Packagex.getPackageVersionCode(context, userPackageName));
        try {
            Packagex.getPackageVersionCode(context, selfPackageName + "_nonono");
            fail();
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        assertEquals(0, Packagex.getPackageVersionCodeOr(context, selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1));
        assertNotEquals(0, Packagex.getPackageVersionCodeOr(context, userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1));
        assertNotEquals(-1, Packagex.getPackageVersionCodeOr(context, userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1));
        assertEquals(-1, Packagex.getPackageVersionCodeOr(context, selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, -1));

        assertEquals(0, Packagex.getPackageVersionCodeOr(context, selfPackageName, -1));
        assertNotEquals(0, Packagex.getPackageVersionCodeOr(context, userPackageName, -1));
        assertNotEquals(-1, Packagex.getPackageVersionCodeOr(context, userPackageName, -1));
        assertEquals(-1, Packagex.getPackageVersionCodeOr(context, selfPackageName + "_nonono", -1));
    }

    @Test
    public void testGetLongVersionCode() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        String userPackageName = Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context,
                PackageFilterFlags.ONLY_USER | PackageFilterFlags.ONLY_RELEASE | PackageFilterFlags.EXCLUDE_SELF)).packageName;
        String selfPackageName = context.getPackageName();

        assertEquals(0L, Packagex.getPackageLongVersionCode(context, selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        assertNotEquals(0L, Packagex.getPackageLongVersionCode(context, userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        try {
            Packagex.getPackageLongVersionCode(context, selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES);
            fail();
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        assertEquals(0L, Packagex.getPackageLongVersionCode(context, selfPackageName));
        assertNotEquals(0L, Packagex.getPackageLongVersionCode(context, userPackageName));
        try {
            Packagex.getPackageLongVersionCode(context, selfPackageName + "_nonono");
            fail();
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        assertEquals(0L, Packagex.getPackageLongVersionCodeOr(context, selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1));
        assertNotEquals(0L, Packagex.getPackageLongVersionCodeOr(context, userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1));
        assertNotEquals(-1L, Packagex.getPackageLongVersionCodeOr(context, userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1));
        assertEquals(-1L, Packagex.getPackageLongVersionCodeOr(context, selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, -1));

        assertEquals(0L, Packagex.getPackageLongVersionCodeOr(context, selfPackageName, -1));
        assertNotEquals(0L, Packagex.getPackageLongVersionCodeOr(context, userPackageName, -1));
        assertNotEquals(-1L, Packagex.getPackageLongVersionCodeOr(context, userPackageName, -1));
        assertEquals(-1L, Packagex.getPackageLongVersionCodeOr(context, selfPackageName + "_nonono", -1));
    }

    @Test
    public void testGetVersionName() {
        Context context = InstrumentationRegistry.getContext();

        String userPackageName = Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context,
                PackageFilterFlags.ONLY_USER | PackageFilterFlags.ONLY_RELEASE | PackageFilterFlags.EXCLUDE_SELF)).packageName;
        String selfPackageName = context.getPackageName();

        try {
            assertEquals("", Packagex.getPackageVersionName(context, selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
            assertNotEquals("", Packagex.getPackageVersionName(context, userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        } catch (PackageManager.NameNotFoundException e) {
            fail();
        }
        try {
            Packagex.getPackageVersionName(context, selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES);
            fail();
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        try {
            assertEquals("", Packagex.getPackageVersionName(context, selfPackageName));
            assertNotEquals("", Packagex.getPackageVersionName(context, userPackageName));
        } catch (PackageManager.NameNotFoundException e) {
            fail();
        }
        try {
            Packagex.getPackageVersionName(context, selfPackageName + "_nonono");
            fail();
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        assertEquals("", Packagex.getPackageVersionNameOr(context, selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, "unknown"));
        assertNotEquals("", Packagex.getPackageVersionNameOr(context, userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, "unknown"));
        assertEquals("unknown", Packagex.getPackageVersionNameOr(context, selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, "unknown"));

        assertEquals("", Packagex.getPackageVersionNameOr(context, selfPackageName, "unknown"));
        assertNotEquals("", Packagex.getPackageVersionNameOr(context, userPackageName, "unknown"));
        assertEquals("unknown", Packagex.getPackageVersionNameOr(context, selfPackageName + "_nonono", "unknown"));

        assertEquals("", Packagex.getPackageVersionNameOrEmpty(context, selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        assertNotEquals("", Packagex.getPackageVersionNameOrEmpty(context, userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        assertEquals("", Packagex.getPackageVersionNameOrEmpty(context, selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES));

        assertEquals("", Packagex.getPackageVersionNameOrEmpty(context, selfPackageName));
        assertNotEquals("", Packagex.getPackageVersionNameOrEmpty(context, userPackageName));
        assertEquals("", Packagex.getPackageVersionNameOrEmpty(context, selfPackageName + "_nonono"));

        assertNull("", Packagex.getPackageVersionNameOrNull(context, selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        assertNotEquals("", Packagex.getPackageVersionNameOrNull(context, userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES));
        assertNull("", Packagex.getPackageVersionNameOrNull(context, selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES));

        assertNull("", Packagex.getPackageVersionNameOrNull(context, selfPackageName));
        assertNotEquals("", Packagex.getPackageVersionNameOrNull(context, userPackageName));
        assertNull("", Packagex.getPackageVersionNameOrNull(context, selfPackageName + "_nonono"));
    }

    @Test
    public void testGetFirstPackage() {
        final Context context = InstrumentationRegistry.getContext();
        final String selfPackageName = context.getPackageName();
        final String errorPackageName = selfPackageName + "_nonono";
        final String firstPackageName = Packagex.listPackageName(context).get(0);
        final String firstSystemPackageName = Packagex.listPackageNameByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM).get(0);

        assertEquals(selfPackageName, Premisex.requireNotNull(Packagex.getFirstPackageByFilter(context,
                new PackageNameFilter(selfPackageName), PackageManager.MATCH_UNINSTALLED_PACKAGES)).packageName);
        assertNull(Packagex.getFirstPackageByFilter(context,
                new PackageNameFilter(errorPackageName), PackageManager.MATCH_UNINSTALLED_PACKAGES));
        assertEquals(firstPackageName, Premisex.requireNotNull(Packagex.getFirstPackageByFilter(context,
                null, PackageManager.MATCH_UNINSTALLED_PACKAGES)).packageName);

        assertEquals(selfPackageName, Premisex.requireNotNull(Packagex.getFirstPackageByFilter(context,
                new PackageNameFilter(selfPackageName))).packageName);
        assertNull(Packagex.getFirstPackageByFilter(context, new PackageNameFilter(errorPackageName)));
        assertEquals(firstPackageName, Premisex.requireNotNull(Packagex.getFirstPackageByFilter(
                context, null)).packageName);

        assertEquals(firstSystemPackageName, Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context,
                PackageFilterFlags.ONLY_SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES)).packageName);
        assertEquals(firstSystemPackageName, Premisex.requireNotNull(Packagex.getFirstPackageByFilterFlags(context,
                PackageFilterFlags.ONLY_SYSTEM)).packageName);

        assertEquals(firstPackageName, Premisex.requireNotNull(Packagex.getFirstPackage(context,
                PackageManager.MATCH_UNINSTALLED_PACKAGES)).packageName);
        assertEquals(firstPackageName, Premisex.requireNotNull(Packagex.getFirstPackage(context)).packageName);
    }

    @Test
    public void testListPackage() {
        final Context context = InstrumentationRegistry.getContext();

        int allPackagesSize = Packagex.listPackageByFilter(context, null, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        int startsWithPackagesSize = Packagex.listPackageByFilter(context, new EndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        int notStartsWithPackagesSize = Packagex.listPackageByFilter(context, new NotEndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        assertTrue(allPackagesSize > 0);
        assertTrue(startsWithPackagesSize > 0);
        assertTrue(notStartsWithPackagesSize > 0);
        assertTrue(startsWithPackagesSize < allPackagesSize);
        assertNotEquals(startsWithPackagesSize, notStartsWithPackagesSize);
        assertEquals(allPackagesSize, startsWithPackagesSize + notStartsWithPackagesSize);

        int allPackagesSize1 = Packagex.listPackageByFilter(context, null, 0).size();
        int startsWithPackagesSize1 = Packagex.listPackageByFilter(context, new EndsWithPackageFilter("s"), 0).size();
        int notStartsWithPackagesSize1 = Packagex.listPackageByFilter(context, new NotEndsWithPackageFilter("s"), 0).size();
        assertTrue(allPackagesSize1 > 0);
        assertTrue(startsWithPackagesSize1 > 0);
        assertNotEquals(startsWithPackagesSize1, startsWithPackagesSize);
        assertTrue(notStartsWithPackagesSize1 > 0);
        assertTrue(startsWithPackagesSize1 < allPackagesSize1);
        assertNotEquals(startsWithPackagesSize1, notStartsWithPackagesSize1);
        assertNotEquals(notStartsWithPackagesSize1, notStartsWithPackagesSize);
        assertEquals(allPackagesSize1, startsWithPackagesSize1 + notStartsWithPackagesSize1);

        int allPackagesSize2 = Packagex.listPackageByFilter(context, null).size();
        int startsWithPackagesSize2 = Packagex.listPackageByFilter(context, new EndsWithPackageFilter("t")).size();
        int notStartsWithPackagesSize2 = Packagex.listPackageByFilter(context, new NotEndsWithPackageFilter("t")).size();
        assertTrue(allPackagesSize2 > 0);
        assertTrue(startsWithPackagesSize2 > 0);
        assertTrue(notStartsWithPackagesSize2 > 0);
        assertTrue(startsWithPackagesSize2 < allPackagesSize2);
        assertNotEquals(startsWithPackagesSize2, notStartsWithPackagesSize2);
        assertEquals(allPackagesSize2, startsWithPackagesSize2 + notStartsWithPackagesSize2);


        int allPackagesSize3 = Packagex.listPackageByFilterFlags(context, 0, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        int userPackagesSize3 = Packagex.listPackageByFilterFlags(context, PackageFilterFlags.ONLY_USER, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        int systemPackagesSize3 = Packagex.listPackageByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        assertTrue(allPackagesSize3 > 0);
        assertTrue(userPackagesSize3 > 0);
        assertTrue(systemPackagesSize3 > 0);
        assertTrue(userPackagesSize3 < allPackagesSize3);
        assertNotEquals(userPackagesSize3, systemPackagesSize3);
        assertEquals(allPackagesSize3, userPackagesSize3 + systemPackagesSize3);

        int allPackagesSize4 = Packagex.listPackageByFilterFlags(context, 0, 0).size();
        int releasePackagesSize4 = Packagex.listPackageByFilterFlags(context, PackageFilterFlags.ONLY_RELEASE, 0).size();
        int debuggablePackagesSize4 = Packagex.listPackageByFilterFlags(context, PackageFilterFlags.ONLY_DEBUGGABLE, 0).size();
        assertTrue(allPackagesSize4 > 0);
        assertTrue(releasePackagesSize4 > 0);
        assertNotEquals(releasePackagesSize4, userPackagesSize3);
        assertTrue(debuggablePackagesSize4 > 0);
        assertTrue(releasePackagesSize4 < allPackagesSize4);
        assertNotEquals(releasePackagesSize4, debuggablePackagesSize4);
        assertNotEquals(debuggablePackagesSize4, systemPackagesSize3);
        assertEquals(allPackagesSize4, releasePackagesSize4 + debuggablePackagesSize4);

        int allPackagesSize5 = Packagex.listPackageByFilterFlags(context, 0).size();
        int userPackagesSize5 = Packagex.listPackageByFilterFlags(context, PackageFilterFlags.ONLY_USER).size();
        int systemPackagesSize5 = Packagex.listPackageByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM).size();
        assertTrue(allPackagesSize5 > 0);
        assertTrue(userPackagesSize5 > 0);
        assertTrue(systemPackagesSize5 > 0);
        assertTrue(userPackagesSize5 < allPackagesSize5);
        assertNotEquals(userPackagesSize5, systemPackagesSize5);
        assertEquals(allPackagesSize5, userPackagesSize5 + systemPackagesSize5);


        int allPackagesSiz6 = Packagex.listPackage(context, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        assertTrue(allPackagesSiz6 > 0);

        int allPackagesSize7 = Packagex.listPackage(context, 0).size();
        assertTrue(allPackagesSize7 > 0);

        int allPackagesSize8 = Packagex.listPackage(context).size();
        assertTrue(allPackagesSize8 > 0);
        assertEquals(allPackagesSize7, allPackagesSize8);
    }

    @Test
    public void testGetVersionCodeMap() {
        final Context context = InstrumentationRegistry.getContext();

        assertTrue(Mapx.all(Packagex.listPackageVersionCodeToMapByFilter(context, null, 0), entry ->
                Packagex.getPackageVersionCodeOr(context, entry.getKey(), -1) == entry.getValue()
        ));
        int allPackageVersionCodeMapSize = Packagex.listPackageVersionCodeToMapByFilter(context, null, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        int startsWithPackageVersionCodeMapSize = Packagex.listPackageVersionCodeToMapByFilter(context, new EndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        int notStartsWithPackageVersionCodeMapSize = Packagex.listPackageVersionCodeToMapByFilter(context, new NotEndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        assertTrue(allPackageVersionCodeMapSize > 0);
        assertTrue(startsWithPackageVersionCodeMapSize > 0);
        assertTrue(notStartsWithPackageVersionCodeMapSize > 0);
        assertTrue(startsWithPackageVersionCodeMapSize < allPackageVersionCodeMapSize);
        assertNotEquals(startsWithPackageVersionCodeMapSize, notStartsWithPackageVersionCodeMapSize);
        assertEquals(allPackageVersionCodeMapSize, startsWithPackageVersionCodeMapSize + notStartsWithPackageVersionCodeMapSize);

        int allPackageVersionCodeMapSize1 = Packagex.listPackageVersionCodeToMapByFilter(context, null, 0).size();
        int startsWithPackageVersionCodeMapSize1 = Packagex.listPackageVersionCodeToMapByFilter(context, new EndsWithPackageFilter("s"), 0).size();
        int notStartsWithPackageVersionCodeMapSize1 = Packagex.listPackageVersionCodeToMapByFilter(context, new NotEndsWithPackageFilter("s"), 0).size();
        assertTrue(allPackageVersionCodeMapSize1 > 0);
        assertTrue(startsWithPackageVersionCodeMapSize1 > 0);
        assertNotEquals(startsWithPackageVersionCodeMapSize1, startsWithPackageVersionCodeMapSize);
        assertTrue(notStartsWithPackageVersionCodeMapSize1 > 0);
        assertTrue(startsWithPackageVersionCodeMapSize1 < allPackageVersionCodeMapSize1);
        assertNotEquals(startsWithPackageVersionCodeMapSize1, notStartsWithPackageVersionCodeMapSize1);
        assertNotEquals(notStartsWithPackageVersionCodeMapSize1, notStartsWithPackageVersionCodeMapSize);
        assertEquals(allPackageVersionCodeMapSize1, startsWithPackageVersionCodeMapSize1 + notStartsWithPackageVersionCodeMapSize1);

        assertTrue(Mapx.all(Packagex.listPackageVersionCodeToMapByFilter(context, null), entry ->
                Packagex.getPackageVersionCodeOr(context, entry.getKey(), -1) == entry.getValue()
        ));
        int allPackageVersionCodeMapSize2 = Packagex.listPackageVersionCodeToMapByFilter(context, null).size();
        int startsWithPackageVersionCodeMapSize2 = Packagex.listPackageVersionCodeToMapByFilter(context, new EndsWithPackageFilter("t")).size();
        int notStartsWithPackageVersionCodeMapSize2 = Packagex.listPackageVersionCodeToMapByFilter(context, new NotEndsWithPackageFilter("t")).size();
        assertTrue(allPackageVersionCodeMapSize2 > 0);
        assertTrue(startsWithPackageVersionCodeMapSize2 > 0);
        assertTrue(notStartsWithPackageVersionCodeMapSize2 > 0);
        assertTrue(startsWithPackageVersionCodeMapSize2 < allPackageVersionCodeMapSize2);
        assertNotEquals(startsWithPackageVersionCodeMapSize2, notStartsWithPackageVersionCodeMapSize2);
        assertEquals(allPackageVersionCodeMapSize2, startsWithPackageVersionCodeMapSize2 + notStartsWithPackageVersionCodeMapSize2);


        assertTrue(Mapx.all(Packagex.listPackageVersionCodeToMapByFilterFlags(context, 0, 0), entry ->
                Packagex.getPackageVersionCodeOr(context, entry.getKey(), -1) == entry.getValue()
        ));
        int allPackageVersionCodeMapSize3 = Packagex.listPackageVersionCodeToMapByFilterFlags(context, 0, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        int userPackageVersionCodeMapSize3 = Packagex.listPackageVersionCodeToMapByFilterFlags(context, PackageFilterFlags.ONLY_USER, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        int systemPackageVersionCodeMapSize3 = Packagex.listPackageVersionCodeToMapByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        assertTrue(allPackageVersionCodeMapSize3 > 0);
        assertTrue(userPackageVersionCodeMapSize3 > 0);
        assertTrue(systemPackageVersionCodeMapSize3 > 0);
        assertTrue(userPackageVersionCodeMapSize3 < allPackageVersionCodeMapSize3);
        assertNotEquals(userPackageVersionCodeMapSize3, systemPackageVersionCodeMapSize3);
        assertEquals(allPackageVersionCodeMapSize3, userPackageVersionCodeMapSize3 + systemPackageVersionCodeMapSize3);

        int allPackageVersionCodeMapSize4 = Packagex.listPackageVersionCodeToMapByFilterFlags(context, 0, 0).size();
        int releasePackageVersionCodeMapSize4 = Packagex.listPackageVersionCodeToMapByFilterFlags(context, PackageFilterFlags.ONLY_RELEASE, 0).size();
        int debuggablePackageVersionCodeMapSize4 = Packagex.listPackageVersionCodeToMapByFilterFlags(context, PackageFilterFlags.ONLY_DEBUGGABLE, 0).size();
        assertTrue(allPackageVersionCodeMapSize4 > 0);
        assertTrue(releasePackageVersionCodeMapSize4 > 0);
        assertNotEquals(releasePackageVersionCodeMapSize4, userPackageVersionCodeMapSize3);
        assertTrue(debuggablePackageVersionCodeMapSize4 > 0);
        assertTrue(releasePackageVersionCodeMapSize4 < allPackageVersionCodeMapSize4);
        assertNotEquals(releasePackageVersionCodeMapSize4, debuggablePackageVersionCodeMapSize4);
        assertNotEquals(debuggablePackageVersionCodeMapSize4, systemPackageVersionCodeMapSize3);
        assertEquals(allPackageVersionCodeMapSize4, releasePackageVersionCodeMapSize4 + debuggablePackageVersionCodeMapSize4);

        assertTrue(Mapx.all(Packagex.listPackageVersionCodeToMapByFilterFlags(context, 0), entry ->
                Packagex.getPackageVersionCodeOr(context, entry.getKey(), -1) == entry.getValue()
        ));
        int allPackageVersionCodeMapSize5 = Packagex.listPackageVersionCodeToMapByFilterFlags(context, 0).size();
        int userPackageVersionCodeMapSize5 = Packagex.listPackageVersionCodeToMapByFilterFlags(context, PackageFilterFlags.ONLY_USER).size();
        int systemPackageVersionCodeMapSize5 = Packagex.listPackageVersionCodeToMapByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM).size();
        assertTrue(allPackageVersionCodeMapSize5 > 0);
        assertTrue(userPackageVersionCodeMapSize5 > 0);
        assertTrue(systemPackageVersionCodeMapSize5 > 0);
        assertTrue(userPackageVersionCodeMapSize5 < allPackageVersionCodeMapSize5);
        assertNotEquals(userPackageVersionCodeMapSize5, systemPackageVersionCodeMapSize5);
        assertEquals(allPackageVersionCodeMapSize5, userPackageVersionCodeMapSize5 + systemPackageVersionCodeMapSize5);


        assertTrue(Mapx.all(Packagex.listPackageVersionCodeToMap(context, 0), entry ->
                Packagex.getPackageVersionCodeOr(context, entry.getKey(), -1) == entry.getValue()
        ));
        int allPackagesSiz6 = Packagex.listPackageVersionCodeToMap(context, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        assertTrue(allPackagesSiz6 > 0);

        int allPackageVersionCodeMapSize7 = Packagex.listPackageVersionCodeToMap(context, 0).size();
        assertTrue(allPackageVersionCodeMapSize7 > 0);

        assertTrue(Mapx.all(Packagex.listPackageVersionCodeToMap(context), entry ->
                Packagex.getPackageVersionCodeOr(context, entry.getKey(), -1) == entry.getValue()
        ));
        int allPackageVersionCodeMapSize8 = Packagex.listPackageVersionCodeToMap(context).size();
        assertTrue(allPackageVersionCodeMapSize8 > 0);
        assertEquals(allPackageVersionCodeMapSize7, allPackageVersionCodeMapSize8);
    }

    @Test
    public void testListPackageName() {
        final Context context = InstrumentationRegistry.getContext();

        assertTrue(Collectionx.all(Packagex.listPackageNameByFilter(context, null, 0), packageName ->
                Packagex.isPackageInstalled(context, packageName)
        ));
        int allPackageNameSize = Packagex.listPackageNameByFilter(context, null, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        int startsWithPackageNameSize = Packagex.listPackageNameByFilter(context, new EndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        int notStartsWithPackageNameSize = Packagex.listPackageNameByFilter(context, new NotEndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        assertTrue(allPackageNameSize > 0);
        assertTrue(startsWithPackageNameSize > 0);
        assertTrue(notStartsWithPackageNameSize > 0);
        assertTrue(startsWithPackageNameSize < allPackageNameSize);
        assertNotEquals(startsWithPackageNameSize, notStartsWithPackageNameSize);
        assertEquals(allPackageNameSize, startsWithPackageNameSize + notStartsWithPackageNameSize);

        int allPackageNameSize1 = Packagex.listPackageNameByFilter(context, null, 0).size();
        int startsWithPackageNameSize1 = Packagex.listPackageNameByFilter(context, new EndsWithPackageFilter("s"), 0).size();
        int notStartsWithPackageNameSize1 = Packagex.listPackageNameByFilter(context, new NotEndsWithPackageFilter("s"), 0).size();
        assertTrue(allPackageNameSize1 > 0);
        assertTrue(startsWithPackageNameSize1 > 0);
        assertNotEquals(startsWithPackageNameSize1, startsWithPackageNameSize);
        assertTrue(notStartsWithPackageNameSize1 > 0);
        assertTrue(startsWithPackageNameSize1 < allPackageNameSize1);
        assertNotEquals(startsWithPackageNameSize1, notStartsWithPackageNameSize1);
        assertNotEquals(notStartsWithPackageNameSize1, notStartsWithPackageNameSize);
        assertEquals(allPackageNameSize1, startsWithPackageNameSize1 + notStartsWithPackageNameSize1);

        assertTrue(Collectionx.all(Packagex.listPackageNameByFilter(context, null), packageName ->
                Packagex.isPackageInstalled(context, packageName)
        ));
        int allPackageNameSize2 = Packagex.listPackageNameByFilter(context, null).size();
        int startsWithPackageNameSize2 = Packagex.listPackageNameByFilter(context, new EndsWithPackageFilter("t")).size();
        int notStartsWithPackageNameSize2 = Packagex.listPackageNameByFilter(context, new NotEndsWithPackageFilter("t")).size();
        assertTrue(allPackageNameSize2 > 0);
        assertTrue(startsWithPackageNameSize2 > 0);
        assertTrue(notStartsWithPackageNameSize2 > 0);
        assertTrue(startsWithPackageNameSize2 < allPackageNameSize2);
        assertNotEquals(startsWithPackageNameSize2, notStartsWithPackageNameSize2);
        assertEquals(allPackageNameSize2, startsWithPackageNameSize2 + notStartsWithPackageNameSize2);


        assertTrue(Collectionx.all(Packagex.listPackageNameByFilterFlags(context, 0, 0), packageName ->
                Packagex.isPackageInstalled(context, packageName)
        ));
        int allPackageNameSize3 = Packagex.listPackageNameByFilterFlags(context, 0, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        int userPackageNameSize3 = Packagex.listPackageNameByFilterFlags(context, PackageFilterFlags.ONLY_USER, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        int systemPackageNameSize3 = Packagex.listPackageNameByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        assertTrue(allPackageNameSize3 > 0);
        assertTrue(userPackageNameSize3 > 0);
        assertTrue(systemPackageNameSize3 > 0);
        assertTrue(userPackageNameSize3 < allPackageNameSize3);
        assertNotEquals(userPackageNameSize3, systemPackageNameSize3);
        assertEquals(allPackageNameSize3, userPackageNameSize3 + systemPackageNameSize3);

        int allPackageNameSize4 = Packagex.listPackageNameByFilterFlags(context, 0, 0).size();
        int releasePackageNameSize4 = Packagex.listPackageNameByFilterFlags(context, PackageFilterFlags.ONLY_RELEASE, 0).size();
        int debuggablePackageNameSize4 = Packagex.listPackageNameByFilterFlags(context, PackageFilterFlags.ONLY_DEBUGGABLE, 0).size();
        assertTrue(allPackageNameSize4 > 0);
        assertTrue(releasePackageNameSize4 > 0);
        assertNotEquals(releasePackageNameSize4, userPackageNameSize3);
        assertTrue(debuggablePackageNameSize4 > 0);
        assertTrue(releasePackageNameSize4 < allPackageNameSize4);
        assertNotEquals(releasePackageNameSize4, debuggablePackageNameSize4);
        assertNotEquals(debuggablePackageNameSize4, systemPackageNameSize3);
        assertEquals(allPackageNameSize4, releasePackageNameSize4 + debuggablePackageNameSize4);

        assertTrue(Collectionx.all(Packagex.listPackageNameByFilterFlags(context, 0), packageName ->
                Packagex.isPackageInstalled(context, packageName)
        ));
        int allPackageNameSize5 = Packagex.listPackageNameByFilterFlags(context, 0).size();
        int userPackageNameSize5 = Packagex.listPackageNameByFilterFlags(context, PackageFilterFlags.ONLY_USER).size();
        int systemPackageNameSize5 = Packagex.listPackageNameByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM).size();
        assertTrue(allPackageNameSize5 > 0);
        assertTrue(userPackageNameSize5 > 0);
        assertTrue(systemPackageNameSize5 > 0);
        assertTrue(userPackageNameSize5 < allPackageNameSize5);
        assertNotEquals(userPackageNameSize5, systemPackageNameSize5);
        assertEquals(allPackageNameSize5, userPackageNameSize5 + systemPackageNameSize5);


        assertTrue(Collectionx.all(Packagex.listPackageName(context, 0), packageName ->
                Packagex.isPackageInstalled(context, packageName)
        ));
        int allPackagesSiz6 = Packagex.listPackageName(context, PackageManager.MATCH_UNINSTALLED_PACKAGES).size();
        assertTrue(allPackagesSiz6 > 0);

        int allPackageNameSize7 = Packagex.listPackageName(context, 0).size();
        assertTrue(allPackageNameSize7 > 0);

        assertTrue(Collectionx.all(Packagex.listPackageName(context), packageName ->
                Packagex.isPackageInstalled(context, packageName)
        ));
        int allPackageNameSize8 = Packagex.listPackageName(context).size();
        assertTrue(allPackageNameSize8 > 0);
        assertEquals(allPackageNameSize7, allPackageNameSize8);
    }

    @Test
    public void testCountPackage() {
        final Context context = InstrumentationRegistry.getContext();

        int allPackageNameSize = Packagex.countPackageByFilter(context, null, PackageManager.MATCH_UNINSTALLED_PACKAGES);
        int startsWithPackageNameSize = Packagex.countPackageByFilter(context, new EndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES);
        int notStartsWithPackageNameSize = Packagex.countPackageByFilter(context, new NotEndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES);
        assertTrue(allPackageNameSize > 0);
        assertTrue(startsWithPackageNameSize > 0);
        assertTrue(notStartsWithPackageNameSize > 0);
        assertTrue(startsWithPackageNameSize < allPackageNameSize);
        assertNotEquals(startsWithPackageNameSize, notStartsWithPackageNameSize);
        assertEquals(allPackageNameSize, startsWithPackageNameSize + notStartsWithPackageNameSize);

        int allPackageNameSize1 = Packagex.countPackageByFilter(context, null, 0);
        int startsWithPackageNameSize1 = Packagex.countPackageByFilter(context, new EndsWithPackageFilter("s"), 0);
        int notStartsWithPackageNameSize1 = Packagex.countPackageByFilter(context, new NotEndsWithPackageFilter("s"), 0);
        assertTrue(allPackageNameSize1 > 0);
        assertTrue(startsWithPackageNameSize1 > 0);
        assertNotEquals(startsWithPackageNameSize1, startsWithPackageNameSize);
        assertTrue(notStartsWithPackageNameSize1 > 0);
        assertTrue(startsWithPackageNameSize1 < allPackageNameSize1);
        assertNotEquals(startsWithPackageNameSize1, notStartsWithPackageNameSize1);
        assertNotEquals(notStartsWithPackageNameSize1, notStartsWithPackageNameSize);
        assertEquals(allPackageNameSize1, startsWithPackageNameSize1 + notStartsWithPackageNameSize1);

        int allPackageNameSize2 = Packagex.countPackageByFilter(context, null);
        int startsWithPackageNameSize2 = Packagex.countPackageByFilter(context, new EndsWithPackageFilter("t"));
        int notStartsWithPackageNameSize2 = Packagex.countPackageByFilter(context, new NotEndsWithPackageFilter("t"));
        assertTrue(allPackageNameSize2 > 0);
        assertTrue(startsWithPackageNameSize2 > 0);
        assertTrue(notStartsWithPackageNameSize2 > 0);
        assertTrue(startsWithPackageNameSize2 < allPackageNameSize2);
        assertNotEquals(startsWithPackageNameSize2, notStartsWithPackageNameSize2);
        assertEquals(allPackageNameSize2, startsWithPackageNameSize2 + notStartsWithPackageNameSize2);


        int allPackageNameSize3 = Packagex.countPackageByFilterFlags(context, 0, PackageManager.MATCH_UNINSTALLED_PACKAGES);
        int userPackageNameSize3 = Packagex.countPackageByFilterFlags(context, PackageFilterFlags.ONLY_USER, PackageManager.MATCH_UNINSTALLED_PACKAGES);
        int systemPackageNameSize3 = Packagex.countPackageByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES);
        assertTrue(allPackageNameSize3 > 0);
        assertTrue(userPackageNameSize3 > 0);
        assertTrue(systemPackageNameSize3 > 0);
        assertTrue(userPackageNameSize3 < allPackageNameSize3);
        assertNotEquals(userPackageNameSize3, systemPackageNameSize3);
        assertEquals(allPackageNameSize3, userPackageNameSize3 + systemPackageNameSize3);

        int allPackageNameSize4 = Packagex.countPackageByFilterFlags(context, 0, 0);
        int releasePackageNameSize4 = Packagex.countPackageByFilterFlags(context, PackageFilterFlags.ONLY_RELEASE, 0);
        int debuggablePackageNameSize4 = Packagex.countPackageByFilterFlags(context, PackageFilterFlags.ONLY_DEBUGGABLE, 0);
        assertTrue(allPackageNameSize4 > 0);
        assertTrue(releasePackageNameSize4 > 0);
        assertNotEquals(releasePackageNameSize4, userPackageNameSize3);
        assertTrue(debuggablePackageNameSize4 > 0);
        assertTrue(releasePackageNameSize4 < allPackageNameSize4);
        assertNotEquals(releasePackageNameSize4, debuggablePackageNameSize4);
        assertNotEquals(debuggablePackageNameSize4, systemPackageNameSize3);
        assertEquals(allPackageNameSize4, releasePackageNameSize4 + debuggablePackageNameSize4);

        int allPackageNameSize5 = Packagex.countPackageByFilterFlags(context, 0);
        int userPackageNameSize5 = Packagex.countPackageByFilterFlags(context, PackageFilterFlags.ONLY_USER);
        int systemPackageNameSize5 = Packagex.countPackageByFilterFlags(context, PackageFilterFlags.ONLY_SYSTEM);
        assertTrue(allPackageNameSize5 > 0);
        assertTrue(userPackageNameSize5 > 0);
        assertTrue(systemPackageNameSize5 > 0);
        assertTrue(userPackageNameSize5 < allPackageNameSize5);
        assertNotEquals(userPackageNameSize5, systemPackageNameSize5);
        assertEquals(allPackageNameSize5, userPackageNameSize5 + systemPackageNameSize5);


        int allPackagesSiz6 = Packagex.countPackage(context, PackageManager.MATCH_UNINSTALLED_PACKAGES);
        assertTrue(allPackagesSiz6 > 0);

        int allPackageNameSize7 = Packagex.countPackage(context, 0);
        assertTrue(allPackageNameSize7 > 0);

        int allPackageNameSize8 = Packagex.countPackage(context);
        assertTrue(allPackageNameSize8 > 0);
        assertEquals(allPackageNameSize7, allPackageNameSize8);
    }

    @Test
    public void testGetPackageApkFile() throws PackageManager.NameNotFoundException {
        final Context context = InstrumentationRegistry.getContext();
        final String selfPackageName = context.getPackageName();
        final String errorPackageName = selfPackageName + "_nonono";

        File packageFile = Packagex.getPackageApkFile(context, selfPackageName);
        assertNotNull(packageFile);
        assertTrue(packageFile.getPath(), packageFile.exists());
        try {
            Packagex.getPackageApkFile(context, errorPackageName);
            fail();
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        File packageFile1 = Packagex.getPackageApkFileOrNull(context, selfPackageName);
        assertNotNull(packageFile1);
        assertTrue(packageFile.getPath(), packageFile.exists());
        assertNull(Packagex.getPackageApkFileOrNull(context, errorPackageName));
    }

    @Test
    public void testGetAppSignatureBytes() throws PackageManager.NameNotFoundException {
        final Context context = InstrumentationRegistry.getContext();
        final String selfPackageName = context.getPackageName();
        final String errorPackageName = selfPackageName + "_nonono";

        assertNotNull(Packagex.getPackageSignatureBytes(context, selfPackageName));
        try {
            Packagex.getPackageSignatureBytes(context, errorPackageName);
            fail();
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        assertNotNull(Packagex.getPackageSignatureBytesOrNull(context, selfPackageName));
        assertNull(Packagex.getPackageSignatureBytesOrNull(context, errorPackageName));
    }

    @Test
    public void testGetPackageIconDrawable() {
        final Context context = InstrumentationRegistry.getContext();
        final String selfPackageName = context.getPackageName();
        final String errorPackageName = selfPackageName + "_nonono";

        try {
            assertNotNull(Packagex.getPackageIconDrawable(context, selfPackageName, 0));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        try {
            Packagex.getPackageIconDrawable(context, errorPackageName, 0);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            if (!(e instanceof PackageManager.NameNotFoundException)) {
                fail();
            }
        }
        try {
            Packagex.getPackageIconDrawable(context, selfPackageName, -1);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            //noinspection ConstantConditions
            if (!e.getMessage().contains("versionCode inconsistent")) {
                fail();
            }
        }

        assertNotNull(Packagex.getPackageIconDrawableOrNull(context, selfPackageName, 0));
        assertNull(Packagex.getPackageIconDrawableOrNull(context, errorPackageName, 0));
        assertNull(Packagex.getPackageIconDrawableOrNull(context, selfPackageName, -1));
    }

    @Test
    public void testGetApkIcon() throws IOException {
        final Context context = InstrumentationRegistry.getContext();

        File selfApkFile = null;
        try {
            selfApkFile = Packagex.getPackageApkFile(context, context.getPackageName());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        File errorApkFile = new File(context.getFilesDir(), "testGetApkIcon.txt");
        try {
            Filex.writeText(Filex.createNewFileOrThrow(errorApkFile), "testGetApkIcon");

            try {
                assertNotNull(Packagex.getApkIconDrawable(context, selfApkFile));
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
            try {
                Packagex.getApkIconDrawable(context, errorApkFile);
                fail();
            } catch (Exception e) {
                e.printStackTrace();
                //noinspection ConstantConditions
                if (!e.getMessage().contains("Apk parsing error")) {
                    fail();
                }
            }

            assertNotNull(Packagex.getApkIconDrawableOrNull(context, selfApkFile));
            assertNull(Packagex.getApkIconDrawableOrNull(context, errorApkFile));
        } finally {
            Filex.deleteRecursively(errorApkFile);
        }
    }

    private static class PackageNameFilter implements PackageFilter {

        @NonNull
        private final String packageName;

        public PackageNameFilter(@NonNull String packageName) {
            this.packageName = packageName;
        }

        @Override
        public boolean accept(@NonNull PackageInfo packageInfo) {
            return packageInfo.packageName.equals(packageName);
        }
    }

    private static class EndsWithPackageFilter implements PackageFilter {

        @NonNull
        private final String endsWith;

        public EndsWithPackageFilter(@NonNull String endsWith) {
            this.endsWith = endsWith;
        }

        @Override
        public boolean accept(@NonNull PackageInfo packageInfo) {
            return packageInfo.packageName.endsWith(endsWith);
        }
    }

    private static class NotEndsWithPackageFilter implements PackageFilter {

        @NonNull
        private final String endsWith;

        public NotEndsWithPackageFilter(@NonNull String endsWith) {
            this.endsWith = endsWith;
        }

        @Override
        public boolean accept(@NonNull PackageInfo packageInfo) {
            return !packageInfo.packageName.endsWith(endsWith);
        }
    }
}
