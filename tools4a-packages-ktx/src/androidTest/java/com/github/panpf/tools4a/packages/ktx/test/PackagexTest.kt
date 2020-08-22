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

package com.github.panpf.tools4a.packages.ktx.test

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.fileprovider.ktx.getShareFileUri
import com.github.panpf.tools4a.packages.PackageFilter
import com.github.panpf.tools4a.packages.PackageFilterFlags
import com.github.panpf.tools4a.packages.ktx.*
import com.github.panpf.tools4j.collections.Collectionx
import com.github.panpf.tools4j.collections.Mapx
import com.github.panpf.tools4j.io.Filex
import com.github.panpf.tools4j.lang.Stringx
import com.github.panpf.tools4j.premise.Premisex
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PackagexTest {

    @Test
    fun testIsPackageInstalled() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertTrue(context.isPackageInstalled(context.packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertFalse(context.isPackageInstalled(context.packageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES))

        Assert.assertTrue(context.isPackageInstalled(context.packageName))
        Assert.assertFalse(context.isPackageInstalled(context.packageName + "_nonono"))
    }

    @Test
    fun testIsSystemPackage() {
        val context = InstrumentationRegistry.getContext()

        val systemAppPackageName = Premisex.requireNotNull(context.getFirstPackageByFilterFlags(PackageFilterFlags.ONLY_SYSTEM)).packageName
        val selfAppPackageName = context.packageName

        try {
            Assert.assertTrue(context.isSystemPackage(systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertFalse(context.isSystemPackage(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            Assert.fail()
        }
        try {
            context.isSystemPackage(systemAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES)
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        try {
            Assert.assertTrue(context.isSystemPackage(systemAppPackageName))
            Assert.assertFalse(context.isSystemPackage(selfAppPackageName))
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            Assert.fail()
        }
        try {
            context.isSystemPackage(systemAppPackageName + "_nonono")
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertTrue(context.isSystemPackageOr(systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertFalse(context.isSystemPackageOr(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertFalse(context.isSystemPackageOr(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, true))
        Assert.assertTrue(context.isSystemPackageOr(systemAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, true))
        Assert.assertFalse(context.isSystemPackageOr(systemAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, false))

        Assert.assertTrue(context.isSystemPackageOr(systemAppPackageName, defaultValue = false))
        Assert.assertFalse(context.isSystemPackageOr(selfAppPackageName, defaultValue = false))
        Assert.assertFalse(context.isSystemPackageOr(selfAppPackageName, defaultValue = true))
        Assert.assertTrue(context.isSystemPackageOr(systemAppPackageName + "_nonono", defaultValue = true))
        Assert.assertFalse(context.isSystemPackageOr(systemAppPackageName + "_nonono", defaultValue = false))
    }

    @Test
    fun testIsDebuggablePackage() {
        val context = InstrumentationRegistry.getContext()

        val systemAppPackageName = Premisex.requireNotNull(context.getFirstPackageByFilterFlags(PackageFilterFlags.ONLY_SYSTEM)).packageName
        val userAppPackageName = Premisex.requireNotNull(context.getFirstPackageByFilterFlags(
                PackageFilterFlags.ONLY_USER or PackageFilterFlags.EXCLUDE_SELF or PackageFilterFlags.EXCLUDE_DEBUGGABLE)).packageName
        val debuggableAppPackageName = Premisex.requireNotNull(context.getFirstPackageByFilterFlags(PackageFilterFlags.ONLY_DEBUGGABLE)).packageName
        val selfAppPackageName = context.packageName

        try {
            Assert.assertFalse(context.isDebuggablePackage(systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertFalse(context.isDebuggablePackage(userAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertTrue(context.isDebuggablePackage(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertTrue(context.isDebuggablePackage(debuggableAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        } catch (e: PackageManager.NameNotFoundException) {
            Assert.fail()
        }
        try {
            context.isDebuggablePackage(systemAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES)
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        try {
            Assert.assertFalse(context.isDebuggablePackage(systemAppPackageName))
            Assert.assertFalse(context.isDebuggablePackage(userAppPackageName))
            Assert.assertTrue(context.isDebuggablePackage(selfAppPackageName))
            Assert.assertTrue(context.isDebuggablePackage(debuggableAppPackageName))
        } catch (e: PackageManager.NameNotFoundException) {
            Assert.fail()
        }
        try {
            context.isDebuggablePackage(systemAppPackageName + "_nonono")
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        Assert.assertFalse(context.isDebuggablePackageOr(systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertFalse(context.isDebuggablePackageOr(userAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertTrue(context.isDebuggablePackageOr(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertTrue(context.isDebuggablePackageOr(debuggableAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertFalse(context.isDebuggablePackageOr(selfAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertTrue(context.isDebuggablePackageOr(selfAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, true))

        Assert.assertFalse(context.isDebuggablePackageOr(systemAppPackageName, defaultValue = false))
        Assert.assertFalse(context.isDebuggablePackageOr(userAppPackageName, defaultValue = false))
        Assert.assertTrue(context.isDebuggablePackageOr(selfAppPackageName, defaultValue = false))
        Assert.assertTrue(context.isDebuggablePackageOr(debuggableAppPackageName, defaultValue = false))
        Assert.assertFalse(context.isDebuggablePackageOr(selfAppPackageName + "_nonono", defaultValue = false))
        Assert.assertTrue(context.isDebuggablePackageOr(selfAppPackageName + "_nonono", defaultValue = true))
    }

    @Test
    fun testIsJUnitTestPackage() {
        val context = InstrumentationRegistry.getContext()

        val systemAppPackageName = Premisex.requireNotNull(context.getFirstPackageByFilterFlags(PackageFilterFlags.ONLY_SYSTEM)).packageName
        val userAppPackageName = Premisex.requireNotNull(context.getFirstPackageByFilterFlags(
                PackageFilterFlags.ONLY_USER or PackageFilterFlags.EXCLUDE_SELF or PackageFilterFlags.EXCLUDE_JUNIT_TEST)).packageName
        val junitAppPackageName = Premisex.requireNotNull(context.getFirstPackageByFilterFlags(PackageFilterFlags.ONLY_JUNIT_TEST)).packageName
        val selfAppPackageName = context.packageName

        try {
            Assert.assertFalse(context.isJunitTestPackage(systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertFalse(context.isJunitTestPackage(userAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertTrue(context.isJunitTestPackage(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertTrue(context.isJunitTestPackage(junitAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        } catch (e: PackageManager.NameNotFoundException) {
            Assert.fail()
        }
        try {
            context.isJunitTestPackage(systemAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES)
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        try {
            Assert.assertFalse(context.isJunitTestPackage(systemAppPackageName))
            Assert.assertFalse(context.isJunitTestPackage(userAppPackageName))
            Assert.assertTrue(context.isJunitTestPackage(selfAppPackageName))
            Assert.assertTrue(context.isJunitTestPackage(junitAppPackageName))
        } catch (e: PackageManager.NameNotFoundException) {
            Assert.fail()
        }
        try {
            context.isJunitTestPackage(systemAppPackageName + "_nonono")
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        Assert.assertFalse(context.isJunitTestPackageOr(systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertFalse(context.isJunitTestPackageOr(userAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertTrue(context.isJunitTestPackageOr(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertTrue(context.isJunitTestPackageOr(junitAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertFalse(context.isJunitTestPackageOr(selfAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertTrue(context.isJunitTestPackageOr(selfAppPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, true))

        Assert.assertFalse(context.isJunitTestPackageOr(systemAppPackageName, defaultValue = false))
        Assert.assertFalse(context.isJunitTestPackageOr(userAppPackageName, defaultValue = false))
        Assert.assertTrue(context.isJunitTestPackageOr(selfAppPackageName, defaultValue = false))
        Assert.assertTrue(context.isJunitTestPackageOr(junitAppPackageName, defaultValue = false))
        Assert.assertFalse(context.isJunitTestPackageOr(selfAppPackageName + "_nonono", defaultValue = false))
        Assert.assertTrue(context.isJunitTestPackageOr(selfAppPackageName + "_nonono", defaultValue = true))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testCreateInstallPackageIntent() {
        val context = InstrumentationRegistry.getContext()

        val file = context.getPackageApkFile(context.packageName)

        val installAppIntent1 = context.createInstallPackageIntent(context.getShareFileUri(file))
        Assert.assertEquals(Intent.ACTION_VIEW, installAppIntent1.action)
        Assert.assertEquals(Intent.CATEGORY_DEFAULT, Collectionx.find(installAppIntent1.categories) { s: String? -> Stringx.equals(s, Intent.CATEGORY_DEFAULT) })
        Assert.assertEquals(
                context.getShareFileUri(file).toString(),
                Premisex.requireNotNull(installAppIntent1.data).toString()
        )
        Assert.assertEquals("application/vnd.android.package-archive", installAppIntent1.type)
        Assert.assertTrue(installAppIntent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

        val installAppIntent2 = context.createInstallPackageIntent(file)
        Assert.assertEquals(Intent.ACTION_VIEW, installAppIntent2.action)
        Assert.assertNotNull(Collectionx.find(installAppIntent2.categories) { s: String? -> Stringx.equals(s, Intent.CATEGORY_DEFAULT) })
        Assert.assertEquals(
                context.getShareFileUri(file).toString(),
                Premisex.requireNotNull(installAppIntent2.data).toString()
        )
        Assert.assertEquals("application/vnd.android.package-archive", installAppIntent2.type)
        Assert.assertTrue(installAppIntent2.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)
    }

    @Test
    fun testCreateUninstallPackageIntent() {
        val context = InstrumentationRegistry.getContext()

        val uninstallAppIntent = context.createUninstallPackageIntent(context.packageName)
        Assert.assertEquals(Intent.ACTION_DELETE, uninstallAppIntent.action)
        Assert.assertEquals(
                Uri.fromParts("package", context.packageName, null).toString(),
                Premisex.requireNotNull(uninstallAppIntent.data).toString()
        )
    }

    @Test
    fun testCreateLaunchPackageIntent() {
        val context = InstrumentationRegistry.getContext()

        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"))
        val resolveInfos = context.packageManager.queryIntentActivities(webIntent, 0)
        val resolveInfo = Premisex.requireNotNull(Collectionx.firstOrNull(resolveInfos))
        val webBrowserPackageName = resolveInfo.activityInfo.packageName

        val launchAppIntent = Premisex.requireNotNull(context.createLaunchPackageIntent(webBrowserPackageName))
        Assert.assertEquals(Intent.ACTION_MAIN, launchAppIntent.action)
        Assert.assertNotNull(Collectionx.find(launchAppIntent.categories) { s: String? -> Stringx.equals(s, Intent.CATEGORY_LAUNCHER) })
        Assert.assertEquals(webBrowserPackageName, launchAppIntent.getPackage())
    }

    @Test
    fun testCreateApplicationDetailsSettingsIntent() {
        val context = InstrumentationRegistry.getContext()

        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"))
        val resolveInfos = context.packageManager.queryIntentActivities(webIntent, 0)
        val resolveInfo = Premisex.requireNotNull(Collectionx.firstOrNull(resolveInfos))
        val webBrowserPackageName = resolveInfo.activityInfo.packageName

        val appDetailInSystemIntent = context.createApplicationDetailsSettingsIntent(webBrowserPackageName)
        Assert.assertEquals(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, appDetailInSystemIntent.action)
        Assert.assertEquals(
                Uri.fromParts("package", webBrowserPackageName, null).toString(),
                Premisex.requireNotNull(appDetailInSystemIntent.data).toString()
        )
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetPackage() {
        val context = InstrumentationRegistry.getContext()

        val selfAppPackage = Premisex.requireNotNull(context.getPackage(context.packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertEquals("com.github.panpf.tools4a.packages.ktx.test", selfAppPackage.packageName)
        Assert.assertTrue("name: " + selfAppPackage.name, Stringx.isSafe(selfAppPackage.name))
        Assert.assertNotNull(selfAppPackage.versionName)
        Assert.assertTrue("versionCode: " + selfAppPackage.versionCode, selfAppPackage.versionCode >= 0)
        Assert.assertTrue("packageFilePath: " + selfAppPackage.packageFilePath, Stringx.isSafe(selfAppPackage.packageFilePath))
        Assert.assertTrue("packageSize: " + selfAppPackage.packageSize, selfAppPackage.packageSize >= 0)
        Assert.assertTrue("packageLastModifiedTime: " + selfAppPackage.packageLastModifiedTime, selfAppPackage.packageLastModifiedTime >= 0)
        Assert.assertFalse(selfAppPackage.isSystemPackage)
        Assert.assertTrue(selfAppPackage.enabled)

        val selfAppPackage1 = Premisex.requireNotNull(context.getPackage(context.packageName))
        Assert.assertEquals("com.github.panpf.tools4a.packages.ktx.test", selfAppPackage1.packageName)
        Assert.assertTrue("name: " + selfAppPackage1.name, Stringx.isSafe(selfAppPackage1.name))
        Assert.assertNotNull(selfAppPackage1.versionName)
        Assert.assertTrue("versionCode: " + selfAppPackage1.versionCode, selfAppPackage1.versionCode >= 0)
        Assert.assertTrue("packageFilePath: " + selfAppPackage1.packageFilePath, Stringx.isSafe(selfAppPackage1.packageFilePath))
        Assert.assertTrue("packageSize: " + selfAppPackage1.packageSize, selfAppPackage1.packageSize >= 0)
        Assert.assertTrue("packageLastModifiedTime: " + selfAppPackage1.packageLastModifiedTime, selfAppPackage1.packageLastModifiedTime >= 0)
        Assert.assertFalse(selfAppPackage1.isSystemPackage)
        Assert.assertTrue(selfAppPackage1.enabled)

        val systemAppPackage = Premisex.requireNotNull(context.getPackage(
                Premisex.requireNotNull(context.getFirstPackageByFilterFlags(PackageFilterFlags.ONLY_SYSTEM)).packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertTrue(systemAppPackage.isSystemPackage)

        val systemAppPackage1 = Premisex.requireNotNull(context.getPackage(
                Premisex.requireNotNull(context.getFirstPackageByFilterFlags(PackageFilterFlags.ONLY_SYSTEM)).packageName))
        Assert.assertTrue(systemAppPackage1.isSystemPackage)

        Assert.assertNotNull(context.getPackageOrNull(context.packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertNull(context.getPackageOrNull(context.packageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES))

        Assert.assertNotNull(context.getPackageOrNull(context.packageName))
        Assert.assertNull(context.getPackageOrNull(context.packageName + "_nonono"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetVersionCode() {
        val context = InstrumentationRegistry.getContext()

        val userPackageName = Premisex.requireNotNull(context.getFirstPackageByFilterFlags(
                PackageFilterFlags.ONLY_USER or PackageFilterFlags.EXCLUDE_SELF or PackageFilterFlags.EXCLUDE_JUNIT_TEST)).packageName
        val selfPackageName = context.packageName

        Assert.assertEquals(0, context.getPackageVersionCode(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES).toLong())
        Assert.assertNotEquals(0, context.getPackageVersionCode(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES).toLong())
        try {
            context.getPackageVersionCode(selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES)
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertEquals(0, context.getPackageVersionCode(selfPackageName).toLong())
        Assert.assertNotEquals(0, context.getPackageVersionCode(userPackageName).toLong())
        try {
            context.getPackageVersionCode(selfPackageName + "_nonono")
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertEquals(0, context.getPackageVersionCodeOr(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1).toLong())
        Assert.assertNotEquals(0, context.getPackageVersionCodeOr(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1).toLong())
        Assert.assertNotEquals(-1, context.getPackageVersionCodeOr(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1).toLong())
        Assert.assertEquals(-1, context.getPackageVersionCodeOr(selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, -1).toLong())

        Assert.assertEquals(0, context.getPackageVersionCodeOr(selfPackageName, defaultValue = -1).toLong())
        Assert.assertNotEquals(0, context.getPackageVersionCodeOr(userPackageName, defaultValue = -1).toLong())
        Assert.assertNotEquals(-1, context.getPackageVersionCodeOr(userPackageName, defaultValue = -1).toLong())
        Assert.assertEquals(-1, context.getPackageVersionCodeOr(selfPackageName + "_nonono", defaultValue = -1).toLong())
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetLongVersionCode() {
        val context = InstrumentationRegistry.getContext()

        val userPackageName = Premisex.requireNotNull(context.getFirstPackageByFilterFlags(
                PackageFilterFlags.ONLY_USER or PackageFilterFlags.EXCLUDE_SELF or PackageFilterFlags.EXCLUDE_JUNIT_TEST)).packageName
        val selfPackageName = context.packageName

        Assert.assertEquals(0L, context.getPackageLongVersionCode(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertNotEquals(0L, context.getPackageLongVersionCode(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        try {
            context.getPackageLongVersionCode(selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES)
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertEquals(0L, context.getPackageLongVersionCode(selfPackageName))
        Assert.assertNotEquals(0L, context.getPackageLongVersionCode(userPackageName))
        try {
            context.getPackageLongVersionCode(selfPackageName + "_nonono")
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertEquals(0L, context.getPackageLongVersionCodeOr(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1))
        Assert.assertNotEquals(0L, context.getPackageLongVersionCodeOr(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1))
        Assert.assertNotEquals(-1L, context.getPackageLongVersionCodeOr(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1))
        Assert.assertEquals(-1L, context.getPackageLongVersionCodeOr(selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, -1))

        Assert.assertEquals(0L, context.getPackageLongVersionCodeOr(selfPackageName, defaultValue = -1L))
        Assert.assertNotEquals(0L, context.getPackageLongVersionCodeOr(userPackageName, defaultValue = -1L))
        Assert.assertNotEquals(-1L, context.getPackageLongVersionCodeOr(userPackageName, defaultValue = -1L))
        Assert.assertEquals(-1L, context.getPackageLongVersionCodeOr(selfPackageName + "_nonono", defaultValue = -1L))
    }

    @Test
    fun testGetVersionName() {
        val context = InstrumentationRegistry.getContext()

        val userPackageName = Premisex.requireNotNull(context.getFirstPackageByFilterFlags(
                PackageFilterFlags.ONLY_USER or PackageFilterFlags.EXCLUDE_SELF or PackageFilterFlags.EXCLUDE_JUNIT_TEST)).packageName
        val selfPackageName = context.packageName

        try {
            Assert.assertEquals("", context.getPackageVersionName(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertNotEquals("", context.getPackageVersionName(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        } catch (e: PackageManager.NameNotFoundException) {
            Assert.fail()
        }
        try {
            context.getPackageVersionName(selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES)
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        try {
            Assert.assertEquals("", context.getPackageVersionName(selfPackageName))
            Assert.assertNotEquals("", context.getPackageVersionName(userPackageName))
        } catch (e: PackageManager.NameNotFoundException) {
            Assert.fail()
        }
        try {
            context.getPackageVersionName(selfPackageName + "_nonono")
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertEquals("", context.getPackageVersionNameOr(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, "unknown"))
        Assert.assertNotEquals("", context.getPackageVersionNameOr(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, "unknown"))
        Assert.assertEquals("unknown", context.getPackageVersionNameOr(selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES, "unknown"))

        Assert.assertEquals("", context.getPackageVersionNameOr(selfPackageName, defaultValue = "unknown"))
        Assert.assertNotEquals("", context.getPackageVersionNameOr(userPackageName, defaultValue = "unknown"))
        Assert.assertEquals("unknown", context.getPackageVersionNameOr(selfPackageName + "_nonono", defaultValue = "unknown"))

        Assert.assertEquals("", context.getPackageVersionNameOrEmpty(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertNotEquals("", context.getPackageVersionNameOrEmpty(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertEquals("", context.getPackageVersionNameOrEmpty(selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES))

        Assert.assertEquals("", context.getPackageVersionNameOrEmpty(selfPackageName))
        Assert.assertNotEquals("", context.getPackageVersionNameOrEmpty(userPackageName))
        Assert.assertEquals("", context.getPackageVersionNameOrEmpty(selfPackageName + "_nonono"))

        Assert.assertNull("", context.getPackageVersionNameOrNull(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertNotEquals("", context.getPackageVersionNameOrNull(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertNull("", context.getPackageVersionNameOrNull(selfPackageName + "_nonono", PackageManager.MATCH_UNINSTALLED_PACKAGES))

        Assert.assertNull("", context.getPackageVersionNameOrNull(selfPackageName))
        Assert.assertNotEquals("", context.getPackageVersionNameOrNull(userPackageName))
        Assert.assertNull("", context.getPackageVersionNameOrNull(selfPackageName + "_nonono"))
    }

    @Test
    fun testGetFirstPackage() {
        val context = InstrumentationRegistry.getContext()
        val selfPackageName = context.packageName
        val errorPackageName = selfPackageName + "_nonono"
        val firstPackageName = context.listPackageName()[0]
        val firstSystemPackageName = context.listPackageNameByFilterFlags(PackageFilterFlags.ONLY_SYSTEM)[0]

        Assert.assertEquals(selfPackageName, Premisex.requireNotNull(context.getFirstPackageByFilter(
                UniquePackageFilter(selfPackageName), PackageManager.MATCH_UNINSTALLED_PACKAGES)).packageName)
        Assert.assertNull(context.getFirstPackageByFilter(
                UniquePackageFilter(errorPackageName), PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertEquals(firstPackageName, Premisex.requireNotNull(context.getFirstPackageByFilter(
                null, PackageManager.MATCH_UNINSTALLED_PACKAGES)).packageName)

        Assert.assertEquals(selfPackageName, Premisex.requireNotNull(context.getFirstPackageByFilter(
                UniquePackageFilter(selfPackageName))).packageName)
        Assert.assertNull(context.getFirstPackageByFilter(UniquePackageFilter(errorPackageName)))
        Assert.assertEquals(firstPackageName, Premisex.requireNotNull(context.getFirstPackageByFilter(null)).packageName)

        Assert.assertEquals(firstSystemPackageName, Premisex.requireNotNull(context.getFirstPackageByFilterFlags(
                PackageFilterFlags.ONLY_SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES)).packageName)
        Assert.assertEquals(firstSystemPackageName, Premisex.requireNotNull(context.getFirstPackageByFilterFlags(
                PackageFilterFlags.ONLY_SYSTEM)).packageName)

        Assert.assertEquals(firstPackageName, Premisex.requireNotNull(context.getFirstPackage(
                PackageManager.MATCH_UNINSTALLED_PACKAGES)).packageName)
        Assert.assertEquals(firstPackageName, Premisex.requireNotNull(context.getFirstPackage()).packageName)
    }

    @Test
    fun testListPackage() {
        val context = InstrumentationRegistry.getContext()

        val allPackagesSize = context.listPackageByFilter(null, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val startsWithPackagesSize = context.listPackageByFilter(StartsWithPackageFilter("c"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val notStartsWithPackagesSize = context.listPackageByFilter(NotStartsWithPackageFilter("c"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSize > 0)
        Assert.assertTrue(startsWithPackagesSize > 0)
        Assert.assertTrue(notStartsWithPackagesSize > 0)
        Assert.assertTrue(startsWithPackagesSize < allPackagesSize)
        Assert.assertNotEquals(startsWithPackagesSize.toLong(), notStartsWithPackagesSize.toLong())
        Assert.assertEquals(allPackagesSize.toLong(), startsWithPackagesSize + notStartsWithPackagesSize.toLong())

        val allPackagesSize1 = context.listPackageByFilter(null, 0).size
        val startsWithPackagesSize1 = context.listPackageByFilter(StartsWithPackageFilter("com"), 0).size
        val notStartsWithPackagesSize1 = context.listPackageByFilter(NotStartsWithPackageFilter("com"), 0).size
        Assert.assertTrue(allPackagesSize1 > 0)
        Assert.assertTrue(startsWithPackagesSize1 > 0)
        Assert.assertNotEquals(startsWithPackagesSize1.toLong(), startsWithPackagesSize.toLong())
        Assert.assertTrue(notStartsWithPackagesSize1 > 0)
        Assert.assertTrue(startsWithPackagesSize1 < allPackagesSize1)
        Assert.assertNotEquals(startsWithPackagesSize1.toLong(), notStartsWithPackagesSize1.toLong())
        Assert.assertNotEquals(notStartsWithPackagesSize1.toLong(), notStartsWithPackagesSize.toLong())
        Assert.assertEquals(allPackagesSize1.toLong(), startsWithPackagesSize1 + notStartsWithPackagesSize1.toLong())

        val allPackagesSize2 = context.listPackageByFilter(null).size
        val startsWithPackagesSize2 = context.listPackageByFilter(StartsWithPackageFilter("c")).size
        val notStartsWithPackagesSize2 = context.listPackageByFilter(NotStartsWithPackageFilter("c")).size
        Assert.assertTrue(allPackagesSize2 > 0)
        Assert.assertTrue(startsWithPackagesSize2 > 0)
        Assert.assertTrue(notStartsWithPackagesSize2 > 0)
        Assert.assertTrue(startsWithPackagesSize2 < allPackagesSize2)
        Assert.assertNotEquals(startsWithPackagesSize2.toLong(), notStartsWithPackagesSize2.toLong())
        Assert.assertEquals(allPackagesSize2.toLong(), startsWithPackagesSize2 + notStartsWithPackagesSize2.toLong())

        val allPackagesSize3 = context.listPackageByFilterFlags(0, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val userPackagesSize3 = context.listPackageByFilterFlags(PackageFilterFlags.ONLY_USER, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val systemPackagesSize3 = context.listPackageByFilterFlags(PackageFilterFlags.ONLY_SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSize3 > 0)
        Assert.assertTrue(userPackagesSize3 > 0)
        Assert.assertTrue(systemPackagesSize3 > 0)
        Assert.assertTrue(userPackagesSize3 < allPackagesSize3)
        Assert.assertNotEquals(userPackagesSize3.toLong(), systemPackagesSize3.toLong())
        Assert.assertEquals(allPackagesSize3.toLong(), userPackagesSize3 + systemPackagesSize3.toLong())

        val allPackagesSize4 = context.listPackageByFilterFlags(0, 0).size
        val junitTestPackagesSize4 = context.listPackageByFilterFlags(PackageFilterFlags.ONLY_JUNIT_TEST, 0).size
        val nonJunitTestWithPackagesSize4 = context.listPackageByFilterFlags(PackageFilterFlags.ONLY_NON_JUNIT_TEST, 0).size
        Assert.assertTrue(allPackagesSize4 > 0)
        Assert.assertTrue(junitTestPackagesSize4 > 0)
        Assert.assertNotEquals(junitTestPackagesSize4.toLong(), userPackagesSize3.toLong())
        Assert.assertTrue(nonJunitTestWithPackagesSize4 > 0)
        Assert.assertTrue(junitTestPackagesSize4 < allPackagesSize4)
        Assert.assertNotEquals(junitTestPackagesSize4.toLong(), nonJunitTestWithPackagesSize4.toLong())
        Assert.assertNotEquals(nonJunitTestWithPackagesSize4.toLong(), systemPackagesSize3.toLong())
        Assert.assertEquals(allPackagesSize4.toLong(), junitTestPackagesSize4 + nonJunitTestWithPackagesSize4.toLong())

        val allPackagesSize5 = context.listPackageByFilterFlags(0).size
        val userPackagesSize5 = context.listPackageByFilterFlags(PackageFilterFlags.ONLY_USER).size
        val systemPackagesSize5 = context.listPackageByFilterFlags(PackageFilterFlags.ONLY_SYSTEM).size
        Assert.assertTrue(allPackagesSize5 > 0)
        Assert.assertTrue(userPackagesSize5 > 0)
        Assert.assertTrue(systemPackagesSize5 > 0)
        Assert.assertTrue(userPackagesSize5 < allPackagesSize5)
        Assert.assertNotEquals(userPackagesSize5.toLong(), systemPackagesSize5.toLong())
        Assert.assertEquals(allPackagesSize5.toLong(), userPackagesSize5 + systemPackagesSize5.toLong())

        val allPackagesSiz6 = context.listPackage(PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSiz6 > 0)

        val allPackagesSize7 = context.listPackage(0).size
        Assert.assertTrue(allPackagesSize7 > 0)

        val allPackagesSize8 = context.listPackage().size
        Assert.assertTrue(allPackagesSize8 > 0)
        Assert.assertEquals(allPackagesSize7.toLong(), allPackagesSize8.toLong())
    }

    @Test
    fun testGetVersionCodeMap() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertTrue(Mapx.all(context.listPackageVersionCodeToMapByFilter(null, 0)) { entry: Map.Entry<String?, Int> ->
            context.getPackageVersionCodeOr(entry.key!!, defaultValue = -1) == entry.value
        })
        val allPackageVersionCodeMapSize: Int = context.listPackageVersionCodeToMapByFilter(null, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val startsWithPackageVersionCodeMapSize: Int = context.listPackageVersionCodeToMapByFilter(StartsWithPackageFilter("c"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val notStartsWithPackageVersionCodeMapSize: Int = context.listPackageVersionCodeToMapByFilter(NotStartsWithPackageFilter("c"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackageVersionCodeMapSize > 0)
        Assert.assertTrue(startsWithPackageVersionCodeMapSize > 0)
        Assert.assertTrue(notStartsWithPackageVersionCodeMapSize > 0)
        Assert.assertTrue(startsWithPackageVersionCodeMapSize < allPackageVersionCodeMapSize)
        Assert.assertNotEquals(startsWithPackageVersionCodeMapSize.toLong(), notStartsWithPackageVersionCodeMapSize.toLong())
        Assert.assertEquals(allPackageVersionCodeMapSize.toLong(), startsWithPackageVersionCodeMapSize + notStartsWithPackageVersionCodeMapSize.toLong())

        val allPackageVersionCodeMapSize1: Int = context.listPackageVersionCodeToMapByFilter(null, 0).size
        val startsWithPackageVersionCodeMapSize1: Int = context.listPackageVersionCodeToMapByFilter(StartsWithPackageFilter("com"), 0).size
        val notStartsWithPackageVersionCodeMapSize1: Int = context.listPackageVersionCodeToMapByFilter(NotStartsWithPackageFilter("com"), 0).size
        Assert.assertTrue(allPackageVersionCodeMapSize1 > 0)
        Assert.assertTrue(startsWithPackageVersionCodeMapSize1 > 0)
        Assert.assertNotEquals(startsWithPackageVersionCodeMapSize1.toLong(), startsWithPackageVersionCodeMapSize.toLong())
        Assert.assertTrue(notStartsWithPackageVersionCodeMapSize1 > 0)
        Assert.assertTrue(startsWithPackageVersionCodeMapSize1 < allPackageVersionCodeMapSize1)
        Assert.assertNotEquals(startsWithPackageVersionCodeMapSize1.toLong(), notStartsWithPackageVersionCodeMapSize1.toLong())
        Assert.assertNotEquals(notStartsWithPackageVersionCodeMapSize1.toLong(), notStartsWithPackageVersionCodeMapSize.toLong())
        Assert.assertEquals(allPackageVersionCodeMapSize1.toLong(), startsWithPackageVersionCodeMapSize1 + notStartsWithPackageVersionCodeMapSize1.toLong())

        Assert.assertTrue(Mapx.all(context.listPackageVersionCodeToMapByFilter(null)) { entry: Map.Entry<String?, Int> ->
            context.getPackageVersionCodeOr(entry.key!!, defaultValue = -1) == entry.value
        })
        val allPackageVersionCodeMapSize2: Int = context.listPackageVersionCodeToMapByFilter(null).size
        val startsWithPackageVersionCodeMapSize2: Int = context.listPackageVersionCodeToMapByFilter(StartsWithPackageFilter("c")).size
        val notStartsWithPackageVersionCodeMapSize2: Int = context.listPackageVersionCodeToMapByFilter(NotStartsWithPackageFilter("c")).size
        Assert.assertTrue(allPackageVersionCodeMapSize2 > 0)
        Assert.assertTrue(startsWithPackageVersionCodeMapSize2 > 0)
        Assert.assertTrue(notStartsWithPackageVersionCodeMapSize2 > 0)
        Assert.assertTrue(startsWithPackageVersionCodeMapSize2 < allPackageVersionCodeMapSize2)
        Assert.assertNotEquals(startsWithPackageVersionCodeMapSize2.toLong(), notStartsWithPackageVersionCodeMapSize2.toLong())
        Assert.assertEquals(allPackageVersionCodeMapSize2.toLong(), startsWithPackageVersionCodeMapSize2 + notStartsWithPackageVersionCodeMapSize2.toLong())

        Assert.assertTrue(Mapx.all(context.listPackageVersionCodeToMapByFilterFlags(0, 0)) { entry: Map.Entry<String?, Int> ->
            context.getPackageVersionCodeOr(entry.key!!, defaultValue = -1) == entry.value
        })
        val allPackageVersionCodeMapSize3: Int = context.listPackageVersionCodeToMapByFilterFlags(0, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val userPackageVersionCodeMapSize3: Int = context.listPackageVersionCodeToMapByFilterFlags(PackageFilterFlags.ONLY_USER, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val systemPackageVersionCodeMapSize3: Int = context.listPackageVersionCodeToMapByFilterFlags(PackageFilterFlags.ONLY_SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackageVersionCodeMapSize3 > 0)
        Assert.assertTrue(userPackageVersionCodeMapSize3 > 0)
        Assert.assertTrue(systemPackageVersionCodeMapSize3 > 0)
        Assert.assertTrue(userPackageVersionCodeMapSize3 < allPackageVersionCodeMapSize3)
        Assert.assertNotEquals(userPackageVersionCodeMapSize3.toLong(), systemPackageVersionCodeMapSize3.toLong())
        Assert.assertEquals(allPackageVersionCodeMapSize3.toLong(), userPackageVersionCodeMapSize3 + systemPackageVersionCodeMapSize3.toLong())

        val allPackageVersionCodeMapSize4: Int = context.listPackageVersionCodeToMapByFilterFlags(0, 0).size
        val junitTestPackageVersionCodeMapSize4: Int = context.listPackageVersionCodeToMapByFilterFlags(PackageFilterFlags.ONLY_JUNIT_TEST, 0).size
        val nonJunitTestWithPackageVersionCodeMapSize4: Int = context.listPackageVersionCodeToMapByFilterFlags(PackageFilterFlags.ONLY_NON_JUNIT_TEST, 0).size
        Assert.assertTrue(allPackageVersionCodeMapSize4 > 0)
        Assert.assertTrue(junitTestPackageVersionCodeMapSize4 > 0)
        Assert.assertNotEquals(junitTestPackageVersionCodeMapSize4.toLong(), userPackageVersionCodeMapSize3.toLong())
        Assert.assertTrue(nonJunitTestWithPackageVersionCodeMapSize4 > 0)
        Assert.assertTrue(junitTestPackageVersionCodeMapSize4 < allPackageVersionCodeMapSize4)
        Assert.assertNotEquals(junitTestPackageVersionCodeMapSize4.toLong(), nonJunitTestWithPackageVersionCodeMapSize4.toLong())
        Assert.assertNotEquals(nonJunitTestWithPackageVersionCodeMapSize4.toLong(), systemPackageVersionCodeMapSize3.toLong())
        Assert.assertEquals(allPackageVersionCodeMapSize4.toLong(), junitTestPackageVersionCodeMapSize4 + nonJunitTestWithPackageVersionCodeMapSize4.toLong())

        Assert.assertTrue(Mapx.all(context.listPackageVersionCodeToMapByFilterFlags(0)) { entry: Map.Entry<String?, Int> ->
            context.getPackageVersionCodeOr(entry.key!!, defaultValue = -1) == entry.value
        })
        val allPackageVersionCodeMapSize5: Int = context.listPackageVersionCodeToMapByFilterFlags(0).size
        val userPackageVersionCodeMapSize5: Int = context.listPackageVersionCodeToMapByFilterFlags(PackageFilterFlags.ONLY_USER).size
        val systemPackageVersionCodeMapSize5: Int = context.listPackageVersionCodeToMapByFilterFlags(PackageFilterFlags.ONLY_SYSTEM).size
        Assert.assertTrue(allPackageVersionCodeMapSize5 > 0)
        Assert.assertTrue(userPackageVersionCodeMapSize5 > 0)
        Assert.assertTrue(systemPackageVersionCodeMapSize5 > 0)
        Assert.assertTrue(userPackageVersionCodeMapSize5 < allPackageVersionCodeMapSize5)
        Assert.assertNotEquals(userPackageVersionCodeMapSize5.toLong(), systemPackageVersionCodeMapSize5.toLong())
        Assert.assertEquals(allPackageVersionCodeMapSize5.toLong(), userPackageVersionCodeMapSize5 + systemPackageVersionCodeMapSize5.toLong())

        Assert.assertTrue(Mapx.all(context.listPackageVersionCodeToMap(0)) { entry: Map.Entry<String?, Int> ->
            context.getPackageVersionCodeOr(entry.key!!, defaultValue = -1) == entry.value
        })
        val allPackagesSiz6: Int = context.listPackageVersionCodeToMap(PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSiz6 > 0)

        val allPackageVersionCodeMapSize7: Int = context.listPackageVersionCodeToMap(0).size
        Assert.assertTrue(allPackageVersionCodeMapSize7 > 0)

        Assert.assertTrue(Mapx.all(context.listPackageVersionCodeToMap()) { entry: Map.Entry<String?, Int> ->
            context.getPackageVersionCodeOr(entry.key!!, defaultValue = -1) == entry.value
        })
        val allPackageVersionCodeMapSize8: Int = context.listPackageVersionCodeToMap().size
        Assert.assertTrue(allPackageVersionCodeMapSize8 > 0)
        Assert.assertEquals(allPackageVersionCodeMapSize7.toLong(), allPackageVersionCodeMapSize8.toLong())
    }

    @Test
    fun testListPackageName() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertTrue(Collectionx.all(context.listPackageNameByFilter(null, 0)) { packageName: String? ->
            context.isPackageInstalled(packageName!!)
        })
        val allPackageNameSize = context.listPackageNameByFilter(null, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val startsWithPackageNameSize = context.listPackageNameByFilter(StartsWithPackageFilter("c"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val notStartsWithPackageNameSize = context.listPackageNameByFilter(NotStartsWithPackageFilter("c"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackageNameSize > 0)
        Assert.assertTrue(startsWithPackageNameSize > 0)
        Assert.assertTrue(notStartsWithPackageNameSize > 0)
        Assert.assertTrue(startsWithPackageNameSize < allPackageNameSize)
        Assert.assertNotEquals(startsWithPackageNameSize.toLong(), notStartsWithPackageNameSize.toLong())
        Assert.assertEquals(allPackageNameSize.toLong(), startsWithPackageNameSize + notStartsWithPackageNameSize.toLong())

        val allPackageNameSize1 = context.listPackageNameByFilter(null, 0).size
        val startsWithPackageNameSize1 = context.listPackageNameByFilter(StartsWithPackageFilter("com"), 0).size
        val notStartsWithPackageNameSize1 = context.listPackageNameByFilter(NotStartsWithPackageFilter("com"), 0).size
        Assert.assertTrue(allPackageNameSize1 > 0)
        Assert.assertTrue(startsWithPackageNameSize1 > 0)
        Assert.assertNotEquals(startsWithPackageNameSize1.toLong(), startsWithPackageNameSize.toLong())
        Assert.assertTrue(notStartsWithPackageNameSize1 > 0)
        Assert.assertTrue(startsWithPackageNameSize1 < allPackageNameSize1)
        Assert.assertNotEquals(startsWithPackageNameSize1.toLong(), notStartsWithPackageNameSize1.toLong())
        Assert.assertNotEquals(notStartsWithPackageNameSize1.toLong(), notStartsWithPackageNameSize.toLong())
        Assert.assertEquals(allPackageNameSize1.toLong(), startsWithPackageNameSize1 + notStartsWithPackageNameSize1.toLong())

        Assert.assertTrue(Collectionx.all(context.listPackageNameByFilter(null)) { packageName: String? ->
            context.isPackageInstalled(packageName!!)
        })
        val allPackageNameSize2 = context.listPackageNameByFilter(null).size
        val startsWithPackageNameSize2 = context.listPackageNameByFilter(StartsWithPackageFilter("c")).size
        val notStartsWithPackageNameSize2 = context.listPackageNameByFilter(NotStartsWithPackageFilter("c")).size
        Assert.assertTrue(allPackageNameSize2 > 0)
        Assert.assertTrue(startsWithPackageNameSize2 > 0)
        Assert.assertTrue(notStartsWithPackageNameSize2 > 0)
        Assert.assertTrue(startsWithPackageNameSize2 < allPackageNameSize2)
        Assert.assertNotEquals(startsWithPackageNameSize2.toLong(), notStartsWithPackageNameSize2.toLong())
        Assert.assertEquals(allPackageNameSize2.toLong(), startsWithPackageNameSize2 + notStartsWithPackageNameSize2.toLong())

        Assert.assertTrue(Collectionx.all(context.listPackageNameByFilterFlags(0, 0)) { packageName: String? ->
            context.isPackageInstalled(packageName!!)
        })
        val allPackageNameSize3 = context.listPackageNameByFilterFlags(0, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val userPackageNameSize3 = context.listPackageNameByFilterFlags(PackageFilterFlags.ONLY_USER, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val systemPackageNameSize3 = context.listPackageNameByFilterFlags(PackageFilterFlags.ONLY_SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackageNameSize3 > 0)
        Assert.assertTrue(userPackageNameSize3 > 0)
        Assert.assertTrue(systemPackageNameSize3 > 0)
        Assert.assertTrue(userPackageNameSize3 < allPackageNameSize3)
        Assert.assertNotEquals(userPackageNameSize3.toLong(), systemPackageNameSize3.toLong())
        Assert.assertEquals(allPackageNameSize3.toLong(), userPackageNameSize3 + systemPackageNameSize3.toLong())

        val allPackageNameSize4 = context.listPackageNameByFilterFlags(0, 0).size
        val junitTestPackageNameSize4 = context.listPackageNameByFilterFlags(PackageFilterFlags.ONLY_JUNIT_TEST, 0).size
        val nonJunitTestWithPackageNameSize4 = context.listPackageNameByFilterFlags(PackageFilterFlags.ONLY_NON_JUNIT_TEST, 0).size
        Assert.assertTrue(allPackageNameSize4 > 0)
        Assert.assertTrue(junitTestPackageNameSize4 > 0)
        Assert.assertNotEquals(junitTestPackageNameSize4.toLong(), userPackageNameSize3.toLong())
        Assert.assertTrue(nonJunitTestWithPackageNameSize4 > 0)
        Assert.assertTrue(junitTestPackageNameSize4 < allPackageNameSize4)
        Assert.assertNotEquals(junitTestPackageNameSize4.toLong(), nonJunitTestWithPackageNameSize4.toLong())
        Assert.assertNotEquals(nonJunitTestWithPackageNameSize4.toLong(), systemPackageNameSize3.toLong())
        Assert.assertEquals(allPackageNameSize4.toLong(), junitTestPackageNameSize4 + nonJunitTestWithPackageNameSize4.toLong())

        Assert.assertTrue(Collectionx.all(context.listPackageNameByFilterFlags(0)) { packageName: String? ->
            context.isPackageInstalled(packageName!!)
        })
        val allPackageNameSize5 = context.listPackageNameByFilterFlags(0).size
        val userPackageNameSize5 = context.listPackageNameByFilterFlags(PackageFilterFlags.ONLY_USER).size
        val systemPackageNameSize5 = context.listPackageNameByFilterFlags(PackageFilterFlags.ONLY_SYSTEM).size
        Assert.assertTrue(allPackageNameSize5 > 0)
        Assert.assertTrue(userPackageNameSize5 > 0)
        Assert.assertTrue(systemPackageNameSize5 > 0)
        Assert.assertTrue(userPackageNameSize5 < allPackageNameSize5)
        Assert.assertNotEquals(userPackageNameSize5.toLong(), systemPackageNameSize5.toLong())
        Assert.assertEquals(allPackageNameSize5.toLong(), userPackageNameSize5 + systemPackageNameSize5.toLong())

        Assert.assertTrue(Collectionx.all(context.listPackageName(0)) { packageName: String? ->
            context.isPackageInstalled(packageName!!)
        })
        val allPackagesSiz6 = context.listPackageName(PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSiz6 > 0)

        val allPackageNameSize7 = context.listPackageName(0).size
        Assert.assertTrue(allPackageNameSize7 > 0)

        Assert.assertTrue(Collectionx.all(context.listPackageName()) { packageName: String? ->
            context.isPackageInstalled(packageName!!)
        })
        val allPackageNameSize8 = context.listPackageName().size
        Assert.assertTrue(allPackageNameSize8 > 0)
        Assert.assertEquals(allPackageNameSize7.toLong(), allPackageNameSize8.toLong())
    }

    @Test
    fun testCountPackage() {
        val context = InstrumentationRegistry.getContext()

        val allPackageNameSize = context.countPackageByFilter(null, PackageManager.MATCH_UNINSTALLED_PACKAGES)
        val startsWithPackageNameSize = context.countPackageByFilter(StartsWithPackageFilter("c"), PackageManager.MATCH_UNINSTALLED_PACKAGES)
        val notStartsWithPackageNameSize = context.countPackageByFilter(NotStartsWithPackageFilter("c"), PackageManager.MATCH_UNINSTALLED_PACKAGES)
        Assert.assertTrue(allPackageNameSize > 0)
        Assert.assertTrue(startsWithPackageNameSize > 0)
        Assert.assertTrue(notStartsWithPackageNameSize > 0)
        Assert.assertTrue(startsWithPackageNameSize < allPackageNameSize)
        Assert.assertNotEquals(startsWithPackageNameSize.toLong(), notStartsWithPackageNameSize.toLong())
        Assert.assertEquals(allPackageNameSize.toLong(), startsWithPackageNameSize + notStartsWithPackageNameSize.toLong())

        val allPackageNameSize1 = context.countPackageByFilter(null, 0)
        val startsWithPackageNameSize1 = context.countPackageByFilter(StartsWithPackageFilter("com"), 0)
        val notStartsWithPackageNameSize1 = context.countPackageByFilter(NotStartsWithPackageFilter("com"), 0)
        Assert.assertTrue(allPackageNameSize1 > 0)
        Assert.assertTrue(startsWithPackageNameSize1 > 0)
        Assert.assertNotEquals(startsWithPackageNameSize1.toLong(), startsWithPackageNameSize.toLong())
        Assert.assertTrue(notStartsWithPackageNameSize1 > 0)
        Assert.assertTrue(startsWithPackageNameSize1 < allPackageNameSize1)
        Assert.assertNotEquals(startsWithPackageNameSize1.toLong(), notStartsWithPackageNameSize1.toLong())
        Assert.assertNotEquals(notStartsWithPackageNameSize1.toLong(), notStartsWithPackageNameSize.toLong())
        Assert.assertEquals(allPackageNameSize1.toLong(), startsWithPackageNameSize1 + notStartsWithPackageNameSize1.toLong())

        val allPackageNameSize2 = context.countPackageByFilter(null)
        val startsWithPackageNameSize2 = context.countPackageByFilter(StartsWithPackageFilter("c"))
        val notStartsWithPackageNameSize2 = context.countPackageByFilter(NotStartsWithPackageFilter("c"))
        Assert.assertTrue(allPackageNameSize2 > 0)
        Assert.assertTrue(startsWithPackageNameSize2 > 0)
        Assert.assertTrue(notStartsWithPackageNameSize2 > 0)
        Assert.assertTrue(startsWithPackageNameSize2 < allPackageNameSize2)
        Assert.assertNotEquals(startsWithPackageNameSize2.toLong(), notStartsWithPackageNameSize2.toLong())
        Assert.assertEquals(allPackageNameSize2.toLong(), startsWithPackageNameSize2 + notStartsWithPackageNameSize2.toLong())

        val allPackageNameSize3 = context.countPackageByFilterFlags(0, PackageManager.MATCH_UNINSTALLED_PACKAGES)
        val userPackageNameSize3 = context.countPackageByFilterFlags(PackageFilterFlags.ONLY_USER, PackageManager.MATCH_UNINSTALLED_PACKAGES)
        val systemPackageNameSize3 = context.countPackageByFilterFlags(PackageFilterFlags.ONLY_SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES)
        Assert.assertTrue(allPackageNameSize3 > 0)
        Assert.assertTrue(userPackageNameSize3 > 0)
        Assert.assertTrue(systemPackageNameSize3 > 0)
        Assert.assertTrue(userPackageNameSize3 < allPackageNameSize3)
        Assert.assertNotEquals(userPackageNameSize3.toLong(), systemPackageNameSize3.toLong())
        Assert.assertEquals(allPackageNameSize3.toLong(), userPackageNameSize3 + systemPackageNameSize3.toLong())

        val allPackageNameSize4 = context.countPackageByFilterFlags(0, 0)
        val junitTestPackageNameSize4 = context.countPackageByFilterFlags(PackageFilterFlags.ONLY_JUNIT_TEST, 0)
        val nonJunitTestWithPackageNameSize4 = context.countPackageByFilterFlags(PackageFilterFlags.ONLY_NON_JUNIT_TEST, 0)
        Assert.assertTrue(allPackageNameSize4 > 0)
        Assert.assertTrue(junitTestPackageNameSize4 > 0)
        Assert.assertNotEquals(junitTestPackageNameSize4.toLong(), userPackageNameSize3.toLong())
        Assert.assertTrue(nonJunitTestWithPackageNameSize4 > 0)
        Assert.assertTrue(junitTestPackageNameSize4 < allPackageNameSize4)
        Assert.assertNotEquals(junitTestPackageNameSize4.toLong(), nonJunitTestWithPackageNameSize4.toLong())
        Assert.assertNotEquals(nonJunitTestWithPackageNameSize4.toLong(), systemPackageNameSize3.toLong())
        Assert.assertEquals(allPackageNameSize4.toLong(), junitTestPackageNameSize4 + nonJunitTestWithPackageNameSize4.toLong())

        val allPackageNameSize5 = context.countPackageByFilterFlags(0)
        val userPackageNameSize5 = context.countPackageByFilterFlags(PackageFilterFlags.ONLY_USER)
        val systemPackageNameSize5 = context.countPackageByFilterFlags(PackageFilterFlags.ONLY_SYSTEM)
        Assert.assertTrue(allPackageNameSize5 > 0)
        Assert.assertTrue(userPackageNameSize5 > 0)
        Assert.assertTrue(systemPackageNameSize5 > 0)
        Assert.assertTrue(userPackageNameSize5 < allPackageNameSize5)
        Assert.assertNotEquals(userPackageNameSize5.toLong(), systemPackageNameSize5.toLong())
        Assert.assertEquals(allPackageNameSize5.toLong(), userPackageNameSize5 + systemPackageNameSize5.toLong())

        val allPackagesSiz6 = context.countPackage(PackageManager.MATCH_UNINSTALLED_PACKAGES)
        Assert.assertTrue(allPackagesSiz6 > 0)

        val allPackageNameSize7 = context.countPackage(0)
        Assert.assertTrue(allPackageNameSize7 > 0)

        val allPackageNameSize8 = context.countPackage()
        Assert.assertTrue(allPackageNameSize8 > 0)
        Assert.assertEquals(allPackageNameSize7.toLong(), allPackageNameSize8.toLong())
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetPackageApkFile() {
        val context = InstrumentationRegistry.getContext()
        val selfPackageName = context.packageName
        val errorPackageName = selfPackageName + "_nonono"

        val packageFile = context.getPackageApkFile(selfPackageName)
        Assert.assertNotNull(packageFile)
        Assert.assertTrue(packageFile.path, packageFile.exists())
        try {
            context.getPackageApkFile(errorPackageName)
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        val packageFile1 = context.getPackageApkFileOrNull(selfPackageName)
        Assert.assertNotNull(packageFile1)
        Assert.assertTrue(packageFile.path, packageFile.exists())
        Assert.assertNull(context.getPackageApkFileOrNull(errorPackageName))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetAppSignatureBytes() {
        val context = InstrumentationRegistry.getContext()
        val selfPackageName = context.packageName
        val errorPackageName = selfPackageName + "_nonono"

        Assert.assertNotNull(context.getPackageSignatureBytes(selfPackageName))
        try {
            context.getPackageSignatureBytes(errorPackageName)
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertNotNull(context.getPackageSignatureBytesOrNull(selfPackageName))
        Assert.assertNull(context.getPackageSignatureBytesOrNull(errorPackageName))
    }

    @Test
    fun testGetPackageIconDrawable() {
        val context = InstrumentationRegistry.getContext()
        val selfPackageName = context.packageName
        val errorPackageName = selfPackageName + "_nonono"

        try {
            Assert.assertNotNull(context.getPackageIconDrawable(selfPackageName, 0))
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail()
        }
        try {
            context.getPackageIconDrawable(errorPackageName, 0)
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e !is PackageManager.NameNotFoundException) {
                Assert.fail()
            }
        }
        try {
            context.getPackageIconDrawable(selfPackageName, -1)
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
            if (!e.message!!.contains("versionCode inconsistent")) {
                Assert.fail()
            }
        }

        Assert.assertNotNull(context.getPackageIconDrawableOrNull(selfPackageName, 0))
        Assert.assertNull(context.getPackageIconDrawableOrNull(errorPackageName, 0))
        Assert.assertNull(context.getPackageIconDrawableOrNull(selfPackageName, -1))
    }

    @Test
    @Throws(IOException::class)
    fun testGetApkIcon() {
        val context = InstrumentationRegistry.getContext()

        var selfApkFile: File? = null
        try {
            selfApkFile = context.getPackageApkFile(context.packageName)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            Assert.fail()
        }
        val errorApkFile = File(context.filesDir, "testGetApkIcon.txt")
        try {
            Filex.writeText(Filex.createNewFileOrThrow(errorApkFile), "testGetApkIcon")
            try {
                Assert.assertNotNull(context.getApkIconDrawable(selfApkFile!!))
            } catch (e: Exception) {
                e.printStackTrace()
                Assert.fail()
            }
            try {
                context.getApkIconDrawable(errorApkFile)
                Assert.fail()
            } catch (e: Exception) {
                e.printStackTrace()
                if (!e.message!!.contains("Apk parsing error")) {
                    Assert.fail()
                }
            }

            Assert.assertNotNull(context.getApkIconDrawableOrNull(selfApkFile!!))
            Assert.assertNull(context.getApkIconDrawableOrNull(errorApkFile))
        } finally {
            Filex.deleteRecursively(errorApkFile)
        }
    }

    private class UniquePackageFilter(private val packageName: String) : PackageFilter {
        override fun getPackageInfoFlags(): Int {
            return 0
        }

        override fun accept(packageInfo: PackageInfo): Boolean {
            return packageInfo.packageName == packageName
        }

    }

    private class StartsWithPackageFilter(private val startsWith: String) : PackageFilter {
        override fun getPackageInfoFlags(): Int {
            return 0
        }

        override fun accept(packageInfo: PackageInfo): Boolean {
            return packageInfo.packageName.startsWith(startsWith)
        }

    }

    private class NotStartsWithPackageFilter(private val startsWith: String) : PackageFilter {
        override fun getPackageInfoFlags(): Int {
            return 0
        }

        override fun accept(packageInfo: PackageInfo): Boolean {
            return !packageInfo.packageName.startsWith(startsWith)
        }

    }
}
