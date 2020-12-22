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
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.fileprovider.ktx.getShareFileUri
import com.github.panpf.tools4a.packages.PackageFilter
import com.github.panpf.tools4a.packages.PackageTypeFlags
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
        val context = InstrumentationRegistry.getInstrumentation().context

        Assert.assertTrue(context.isPackageInstalledByInfoFlags(context.packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertFalse(context.isPackageInstalledByInfoFlags(context.packageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES))

        Assert.assertTrue(context.isPackageInstalled(context.packageName))
        Assert.assertFalse(context.isPackageInstalled(context.packageName + "_no_no_no"))
    }

    @Test
    fun testIsSystemPackage() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val systemAppPackageName = context.listPackageNameByTypeFlags(PackageTypeFlags.SYSTEM).first()
        val selfAppPackageName = context.packageName

        try {
            Assert.assertTrue(context.isSystemPackageByInfoFlags(systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertFalse(context.isSystemPackageByInfoFlags(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            Assert.fail()
        }
        try {
            context.isSystemPackageByInfoFlags(systemAppPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES)
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
            context.isSystemPackage(systemAppPackageName + "_no_no_no")
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertTrue(context.isSystemPackageByInfoFlagsOr(systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertFalse(context.isSystemPackageByInfoFlagsOr(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertFalse(context.isSystemPackageByInfoFlagsOr(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, true))
        Assert.assertTrue(context.isSystemPackageByInfoFlagsOr(systemAppPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES, true))
        Assert.assertFalse(context.isSystemPackageByInfoFlagsOr(systemAppPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES, false))

        Assert.assertTrue(context.isSystemPackageOr(systemAppPackageName, defaultValue = false))
        Assert.assertFalse(context.isSystemPackageOr(selfAppPackageName, defaultValue = false))
        Assert.assertFalse(context.isSystemPackageOr(selfAppPackageName, defaultValue = true))
        Assert.assertTrue(context.isSystemPackageOr(systemAppPackageName + "_no_no_no", defaultValue = true))
        Assert.assertFalse(context.isSystemPackageOr(systemAppPackageName + "_no_no_no", defaultValue = false))
    }

    @Test
    fun testIsDebuggablePackage() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val systemAppPackageName = context.listPackageNameByTypeFlags(PackageTypeFlags.SYSTEM).first()
        val userAppPackageName = context.listPackageNameByTypeFlags(
                PackageTypeFlags.USER or PackageTypeFlags.EXCLUDE_SELF or PackageTypeFlags.EXCLUDE_DEBUGGABLE).first()
        val debuggableAppPackageName = context.listPackageNameByTypeFlags(PackageTypeFlags.DEBUGGABLE).first()
        val selfAppPackageName = context.packageName

        try {
            Assert.assertFalse(context.isDebuggablePackageByInfoFlags(systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertFalse(context.isDebuggablePackageByInfoFlags(userAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertTrue(context.isDebuggablePackageByInfoFlags(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertTrue(context.isDebuggablePackageByInfoFlags(debuggableAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        } catch (e: PackageManager.NameNotFoundException) {
            Assert.fail()
        }
        try {
            context.isDebuggablePackageByInfoFlags(systemAppPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES)
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
            context.isDebuggablePackage(systemAppPackageName + "_no_no_no")
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        Assert.assertFalse(context.isDebuggablePackageByInfoFlagsOr(systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertFalse(context.isDebuggablePackageByInfoFlagsOr(userAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertTrue(context.isDebuggablePackageByInfoFlagsOr(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertTrue(context.isDebuggablePackageByInfoFlagsOr(debuggableAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertFalse(context.isDebuggablePackageByInfoFlagsOr(selfAppPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertTrue(context.isDebuggablePackageByInfoFlagsOr(selfAppPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES, true))

        Assert.assertFalse(context.isDebuggablePackageOr(systemAppPackageName, defaultValue = false))
        Assert.assertFalse(context.isDebuggablePackageOr(userAppPackageName, defaultValue = false))
        Assert.assertTrue(context.isDebuggablePackageOr(selfAppPackageName, defaultValue = false))
        Assert.assertTrue(context.isDebuggablePackageOr(debuggableAppPackageName, defaultValue = false))
        Assert.assertFalse(context.isDebuggablePackageOr(selfAppPackageName + "_no_no_no", defaultValue = false))
        Assert.assertTrue(context.isDebuggablePackageOr(selfAppPackageName + "_no_no_no", defaultValue = true))
    }

    @Test
    fun testIsJUnitTestPackage() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val systemAppPackageName = context.listPackageNameByTypeFlags(PackageTypeFlags.SYSTEM).first()
        val userAppPackageName = context.listPackageNameByTypeFlags(
                PackageTypeFlags.USER or PackageTypeFlags.RELEASE or PackageTypeFlags.EXCLUDE_SELF).first()
        val selfAppPackageName = context.packageName

        try {
            Assert.assertFalse(context.isJunitTestPackageByInfoFlags(systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertFalse(context.isJunitTestPackageByInfoFlags(userAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertTrue(context.isJunitTestPackageByInfoFlags(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        } catch (e: PackageManager.NameNotFoundException) {
            Assert.fail()
        }
        try {
            context.isJunitTestPackageByInfoFlags(systemAppPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES)
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        try {
            Assert.assertFalse(context.isJunitTestPackage(systemAppPackageName))
            Assert.assertFalse(context.isJunitTestPackage(userAppPackageName))
            Assert.assertTrue(context.isJunitTestPackage(selfAppPackageName))
        } catch (e: PackageManager.NameNotFoundException) {
            Assert.fail()
        }
        try {
            context.isJunitTestPackage(systemAppPackageName + "_no_no_no")
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        Assert.assertFalse(context.isJunitTestPackageByInfoFlagsOr(systemAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertFalse(context.isJunitTestPackageByInfoFlagsOr(userAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertTrue(context.isJunitTestPackageByInfoFlagsOr(selfAppPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertFalse(context.isJunitTestPackageByInfoFlagsOr(selfAppPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES, false))
        Assert.assertTrue(context.isJunitTestPackageByInfoFlagsOr(selfAppPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES, true))

        Assert.assertFalse(context.isJunitTestPackageOr(systemAppPackageName, defaultValue = false))
        Assert.assertFalse(context.isJunitTestPackageOr(userAppPackageName, defaultValue = false))
        Assert.assertTrue(context.isJunitTestPackageOr(selfAppPackageName, defaultValue = false))
        Assert.assertFalse(context.isJunitTestPackageOr(selfAppPackageName + "_no_no_no", defaultValue = false))
        Assert.assertTrue(context.isJunitTestPackageOr(selfAppPackageName + "_no_no_no", defaultValue = true))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetPackageInfo() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val selfAppPackage = context.getPackageInfoByInfoFlags(context.packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES)
        Assert.assertEquals("com.github.panpf.tools4a.packages.ktx.test", selfAppPackage.packageName)

        val selfAppPackage1 = context.getPackageInfo(context.packageName)
        Assert.assertEquals("com.github.panpf.tools4a.packages.ktx.test", selfAppPackage1.packageName)

        val systemAppPackage = context.getPackageInfoByInfoFlags(
                context.listPackageNameByTypeFlags(PackageTypeFlags.SYSTEM).first(), PackageManager.MATCH_UNINSTALLED_PACKAGES)
        Assert.assertTrue(systemAppPackage.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0)
        Assert.assertFalse(systemAppPackage.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0)

        val systemAppPackage1 = context.getPackageInfo(
                context.listPackageNameByTypeFlags(PackageTypeFlags.SYSTEM).first())
        Assert.assertTrue(systemAppPackage1.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0)

        Assert.assertNotNull(context.getPackageInfoByInfoFlagsOrNull(context.packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertNull(context.getPackageInfoByInfoFlagsOrNull(context.packageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertNotNull(context.getPackageInfoOrNull(context.packageName))
        Assert.assertNull(context.getPackageInfoOrNull(context.packageName + "_no_no_no"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetSimplePackageInfo() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val selfAppPackage = Premisex.requireNotNull(context.getSimplePackageInfoByInfoFlags(context.packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertEquals("com.github.panpf.tools4a.packages.ktx.test", selfAppPackage.packageName)
        Assert.assertTrue("name: " + selfAppPackage.name, Stringx.isSafe(selfAppPackage.name))
        Assert.assertNotNull(selfAppPackage.versionName)
        Assert.assertTrue("versionCode: " + selfAppPackage.versionCode, selfAppPackage.versionCode >= 0)
        Assert.assertTrue("packageFilePath: " + selfAppPackage.packageFilePath, Stringx.isSafe(selfAppPackage.packageFilePath))
        Assert.assertTrue("packageFileLength: " + selfAppPackage.packageFileLength, selfAppPackage.packageFileLength >= 0)
        Assert.assertTrue("packageFileLastModifiedTime: " + selfAppPackage.packageFileLastModifiedTime, selfAppPackage.packageFileLastModifiedTime >= 0)
        Assert.assertFalse(selfAppPackage.isSystemPackage)
        Assert.assertTrue(selfAppPackage.enabled)
        Assert.assertTrue(selfAppPackage.isDebuggablePackage)

        val selfAppPackage1 = context.getSimplePackageInfo(context.packageName)
        Assert.assertEquals("com.github.panpf.tools4a.packages.ktx.test", selfAppPackage1.packageName)
        Assert.assertTrue("name: " + selfAppPackage1.name, Stringx.isSafe(selfAppPackage1.name))
        Assert.assertNotNull(selfAppPackage1.versionName)
        Assert.assertTrue("versionCode: " + selfAppPackage1.versionCode, selfAppPackage1.versionCode >= 0)
        Assert.assertTrue("packageFilePath: " + selfAppPackage1.packageFilePath, Stringx.isSafe(selfAppPackage1.packageFilePath))
        Assert.assertTrue("packageFileLength: " + selfAppPackage1.packageFileLength, selfAppPackage1.packageFileLength >= 0)
        Assert.assertTrue("packageFileLastModifiedTime: " + selfAppPackage1.packageFileLastModifiedTime, selfAppPackage1.packageFileLastModifiedTime >= 0)
        Assert.assertFalse(selfAppPackage1.isSystemPackage)
        Assert.assertTrue(selfAppPackage1.enabled)

        val systemAppPackage = context.getSimplePackageInfoByInfoFlags(
                context.listPackageNameByTypeFlags(PackageTypeFlags.SYSTEM).first(), PackageManager.MATCH_UNINSTALLED_PACKAGES)
        Assert.assertTrue(systemAppPackage.isSystemPackage)
        Assert.assertFalse(systemAppPackage.isDebuggablePackage)

        val systemAppPackage1 = context.getSimplePackageInfo(
                context.listPackageNameByTypeFlags(PackageTypeFlags.SYSTEM).first())
        Assert.assertTrue(systemAppPackage1.isSystemPackage)

        Assert.assertNotNull(context.getSimplePackageInfoByInfoFlagsOrNull(context.packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertNull(context.getSimplePackageInfoByInfoFlagsOrNull(context.packageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES))

        Assert.assertNotNull(context.getSimplePackageInfoOrNull(context.packageName))
        Assert.assertNull(context.getSimplePackageInfoOrNull(context.packageName + "_no_no_no"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetVersionCode() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val userPackageName = context.listPackageNameByTypeFlags(
                PackageTypeFlags.USER or PackageTypeFlags.RELEASE or PackageTypeFlags.EXCLUDE_SELF).first()
        val selfPackageName = context.packageName

        Assert.assertEquals(0, context.getPackageVersionCodeByInfoFlags(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES).toLong())
        Assert.assertNotEquals(0, context.getPackageVersionCodeByInfoFlags(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES).toLong())
        try {
            context.getPackageVersionCodeByInfoFlags(selfPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES)
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertEquals(0, context.getPackageVersionCode(selfPackageName).toLong())
        Assert.assertNotEquals(0, context.getPackageVersionCode(userPackageName).toLong())
        try {
            context.getPackageVersionCode(selfPackageName + "_no_no_no")
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertEquals(0, context.getPackageVersionCodeByInfoFlagsOr(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1).toLong())
        Assert.assertNotEquals(0, context.getPackageVersionCodeByInfoFlagsOr(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1).toLong())
        Assert.assertNotEquals(-1, context.getPackageVersionCodeByInfoFlagsOr(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1).toLong())
        Assert.assertEquals(-1, context.getPackageVersionCodeByInfoFlagsOr(selfPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES, -1).toLong())

        Assert.assertEquals(0, context.getPackageVersionCodeOr(selfPackageName, defaultValue = -1).toLong())
        Assert.assertNotEquals(0, context.getPackageVersionCodeOr(userPackageName, defaultValue = -1).toLong())
        Assert.assertNotEquals(-1, context.getPackageVersionCodeOr(userPackageName, defaultValue = -1).toLong())
        Assert.assertEquals(-1, context.getPackageVersionCodeOr(selfPackageName + "_no_no_no", defaultValue = -1).toLong())
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetLongVersionCode() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val userPackageName = context.listPackageNameByTypeFlags(
                PackageTypeFlags.USER or PackageTypeFlags.RELEASE or PackageTypeFlags.EXCLUDE_SELF).first()
        val selfPackageName = context.packageName

        Assert.assertEquals(0L, context.getPackageLongVersionCodeByInfoFlags(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertNotEquals(0L, context.getPackageLongVersionCodeByInfoFlags(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        try {
            context.getPackageLongVersionCodeByInfoFlags(selfPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES)
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertEquals(0L, context.getPackageLongVersionCode(selfPackageName))
        Assert.assertNotEquals(0L, context.getPackageLongVersionCode(userPackageName))
        try {
            context.getPackageLongVersionCode(selfPackageName + "_no_no_no")
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertEquals(0L, context.getPackageLongVersionCodeByInfoFlagsOr(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1))
        Assert.assertNotEquals(0L, context.getPackageLongVersionCodeByInfoFlagsOr(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1))
        Assert.assertNotEquals(-1L, context.getPackageLongVersionCodeByInfoFlagsOr(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, -1))
        Assert.assertEquals(-1L, context.getPackageLongVersionCodeByInfoFlagsOr(selfPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES, -1))

        Assert.assertEquals(0L, context.getPackageLongVersionCodeOr(selfPackageName, defaultValue = -1L))
        Assert.assertNotEquals(0L, context.getPackageLongVersionCodeOr(userPackageName, defaultValue = -1L))
        Assert.assertNotEquals(-1L, context.getPackageLongVersionCodeOr(userPackageName, defaultValue = -1L))
        Assert.assertEquals(-1L, context.getPackageLongVersionCodeOr(selfPackageName + "_no_no_no", defaultValue = -1L))
    }

    @Test
    fun testGetVersionName() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val userPackageName = context.listPackageNameByTypeFlags(
                PackageTypeFlags.USER or PackageTypeFlags.RELEASE or PackageTypeFlags.EXCLUDE_SELF).first()
        val selfPackageName = context.packageName

        try {
            Assert.assertEquals("", context.getPackageVersionNameByInfoFlags(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
            Assert.assertNotEquals("", context.getPackageVersionNameByInfoFlags(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        } catch (e: PackageManager.NameNotFoundException) {
            Assert.fail()
        }
        try {
            context.getPackageVersionNameByInfoFlags(selfPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES)
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
            context.getPackageVersionName(selfPackageName + "_no_no_no")
            Assert.fail()
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        Assert.assertEquals("", context.getPackageVersionNameByInfoFlagsOr(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, "unknown"))
        Assert.assertNotEquals("", context.getPackageVersionNameByInfoFlagsOr(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES, "unknown"))
        Assert.assertEquals("unknown", context.getPackageVersionNameByInfoFlagsOr(selfPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES, "unknown"))

        Assert.assertEquals("", context.getPackageVersionNameOr(selfPackageName, defaultValue = "unknown"))
        Assert.assertNotEquals("", context.getPackageVersionNameOr(userPackageName, defaultValue = "unknown"))
        Assert.assertEquals("unknown", context.getPackageVersionNameOr(selfPackageName + "_no_no_no", defaultValue = "unknown"))

        Assert.assertEquals("", context.getPackageVersionNameByInfoFlagsOrEmpty(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertNotEquals("", context.getPackageVersionNameByInfoFlagsOrEmpty(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertEquals("", context.getPackageVersionNameByInfoFlagsOrEmpty(selfPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES))

        Assert.assertEquals("", context.getPackageVersionNameOrEmpty(selfPackageName))
        Assert.assertNotEquals("", context.getPackageVersionNameOrEmpty(userPackageName))
        Assert.assertEquals("", context.getPackageVersionNameOrEmpty(selfPackageName + "_no_no_no"))

        Assert.assertNull("", context.getPackageVersionNameByInfoFlagsOrNull(selfPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertNotEquals("", context.getPackageVersionNameByInfoFlagsOrNull(userPackageName, PackageManager.MATCH_UNINSTALLED_PACKAGES))
        Assert.assertNull("", context.getPackageVersionNameByInfoFlagsOrNull(selfPackageName + "_no_no_no", PackageManager.MATCH_UNINSTALLED_PACKAGES))

        Assert.assertNull("", context.getPackageVersionNameOrNull(selfPackageName))
        Assert.assertNotEquals("", context.getPackageVersionNameOrNull(userPackageName))
        Assert.assertNull("", context.getPackageVersionNameOrNull(selfPackageName + "_no_no_no"))
    }

    @Test
    fun testListPackageInfo() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val allPackagesSize = context.listPackageInfoByFilterInfoFlags(null, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val startsWithPackagesSize = context.listPackageInfoByFilterInfoFlags(EndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val notStartsWithPackagesSize = context.listPackageInfoByFilterInfoFlags(NotEndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSize > 0)
        Assert.assertTrue(startsWithPackagesSize > 0)
        Assert.assertTrue(notStartsWithPackagesSize > 0)
        Assert.assertTrue(startsWithPackagesSize < allPackagesSize)
        Assert.assertNotEquals(startsWithPackagesSize.toLong(), notStartsWithPackagesSize.toLong())
        Assert.assertEquals(allPackagesSize.toLong(), startsWithPackagesSize + notStartsWithPackagesSize.toLong())

        val allPackagesSize1 = context.listPackageInfoByFilterInfoFlags(null, 0).size
        val startsWithPackagesSize1 = context.listPackageInfoByFilterInfoFlags(EndsWithPackageFilter("s"), 0).size
        val notStartsWithPackagesSize1 = context.listPackageInfoByFilterInfoFlags(NotEndsWithPackageFilter("s"), 0).size
        Assert.assertTrue(allPackagesSize1 > 0)
        Assert.assertTrue(startsWithPackagesSize1 > 0)
        Assert.assertNotEquals(startsWithPackagesSize1.toLong(), startsWithPackagesSize.toLong())
        Assert.assertTrue(notStartsWithPackagesSize1 > 0)
        Assert.assertTrue(startsWithPackagesSize1 < allPackagesSize1)
        Assert.assertNotEquals(startsWithPackagesSize1.toLong(), notStartsWithPackagesSize1.toLong())
        Assert.assertNotEquals(notStartsWithPackagesSize1.toLong(), notStartsWithPackagesSize.toLong())
        Assert.assertEquals(allPackagesSize1.toLong(), startsWithPackagesSize1 + notStartsWithPackagesSize1.toLong())

        val allPackagesSize2 = context.listPackageInfoByFilter(null).size
        val startsWithPackagesSize2 = context.listPackageInfoByFilter(EndsWithPackageFilter("t")).size
        val notStartsWithPackagesSize2 = context.listPackageInfoByFilter(NotEndsWithPackageFilter("t")).size
        Assert.assertTrue(allPackagesSize2 > 0)
        Assert.assertTrue(startsWithPackagesSize2 > 0)
        Assert.assertTrue(notStartsWithPackagesSize2 > 0)
        Assert.assertTrue(startsWithPackagesSize2 < allPackagesSize2)
        Assert.assertNotEquals(startsWithPackagesSize2.toLong(), notStartsWithPackagesSize2.toLong())
        Assert.assertEquals(allPackagesSize2.toLong(), startsWithPackagesSize2 + notStartsWithPackagesSize2.toLong())

        val allPackagesSize3 = context.listPackageInfoByTypeInfoFlags(0, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val userPackagesSize3 = context.listPackageInfoByTypeInfoFlags(PackageTypeFlags.USER, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val systemPackagesSize3 = context.listPackageInfoByTypeInfoFlags(PackageTypeFlags.SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSize3 > 0)
        Assert.assertTrue(userPackagesSize3 > 0)
        Assert.assertTrue(systemPackagesSize3 > 0)
        Assert.assertTrue(userPackagesSize3 < allPackagesSize3)
        Assert.assertNotEquals(userPackagesSize3.toLong(), systemPackagesSize3.toLong())
        Assert.assertEquals(allPackagesSize3.toLong(), userPackagesSize3 + systemPackagesSize3.toLong())

        val allPackagesSize4 = context.listPackageInfoByTypeInfoFlags(0, 0).size
        val releasePackagesSize4 = context.listPackageInfoByTypeInfoFlags(PackageTypeFlags.RELEASE, 0).size
        val debuggablePackagesSize4 = context.listPackageInfoByTypeInfoFlags(PackageTypeFlags.DEBUGGABLE, 0).size
        Assert.assertTrue(allPackagesSize4 > 0)
        Assert.assertTrue(releasePackagesSize4 > 0)
        Assert.assertNotEquals(releasePackagesSize4.toLong(), userPackagesSize3.toLong())
        Assert.assertTrue(debuggablePackagesSize4 > 0)
        Assert.assertTrue(releasePackagesSize4 < allPackagesSize4)
        Assert.assertNotEquals(releasePackagesSize4.toLong(), debuggablePackagesSize4.toLong())
        Assert.assertNotEquals(debuggablePackagesSize4.toLong(), systemPackagesSize3.toLong())
        Assert.assertEquals(allPackagesSize4.toLong(), releasePackagesSize4 + debuggablePackagesSize4.toLong())

        val allPackagesSize5 = context.listPackageInfoByTypeFlags(0).size
        val userPackagesSize5 = context.listPackageInfoByTypeFlags(PackageTypeFlags.USER).size
        val systemPackagesSize5 = context.listPackageInfoByTypeFlags(PackageTypeFlags.SYSTEM).size
        Assert.assertTrue(allPackagesSize5 > 0)
        Assert.assertTrue(userPackagesSize5 > 0)
        Assert.assertTrue(systemPackagesSize5 > 0)
        Assert.assertTrue(userPackagesSize5 < allPackagesSize5)
        Assert.assertNotEquals(userPackagesSize5.toLong(), systemPackagesSize5.toLong())
        Assert.assertEquals(allPackagesSize5.toLong(), userPackagesSize5 + systemPackagesSize5.toLong())

        val allPackagesSiz6 = context.listPackageInfoByInfoFlags(PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSiz6 > 0)

        val allPackagesSize7 = context.listPackageInfoByInfoFlags(0).size
        Assert.assertTrue(allPackagesSize7 > 0)

        val allPackagesSize8 = context.listPackageInfo().size
        Assert.assertTrue(allPackagesSize8 > 0)
        Assert.assertEquals(allPackagesSize7.toLong(), allPackagesSize8.toLong())
    }

    @Test
    fun testListSimplePackageInfo() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val allPackagesSize = context.listSimplePackageInfoByFilterInfoFlags(null, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val startsWithPackagesSize = context.listSimplePackageInfoByFilterInfoFlags(EndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val notStartsWithPackagesSize = context.listSimplePackageInfoByFilterInfoFlags(NotEndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSize > 0)
        Assert.assertTrue(startsWithPackagesSize > 0)
        Assert.assertTrue(notStartsWithPackagesSize > 0)
        Assert.assertTrue(startsWithPackagesSize < allPackagesSize)
        Assert.assertNotEquals(startsWithPackagesSize.toLong(), notStartsWithPackagesSize.toLong())
        Assert.assertEquals(allPackagesSize.toLong(), startsWithPackagesSize + notStartsWithPackagesSize.toLong())

        val allPackagesSize1 = context.listSimplePackageInfoByFilterInfoFlags(null, 0).size
        val startsWithPackagesSize1 = context.listSimplePackageInfoByFilterInfoFlags(EndsWithPackageFilter("s"), 0).size
        val notStartsWithPackagesSize1 = context.listSimplePackageInfoByFilterInfoFlags(NotEndsWithPackageFilter("s"), 0).size
        Assert.assertTrue(allPackagesSize1 > 0)
        Assert.assertTrue(startsWithPackagesSize1 > 0)
        Assert.assertNotEquals(startsWithPackagesSize1.toLong(), startsWithPackagesSize.toLong())
        Assert.assertTrue(notStartsWithPackagesSize1 > 0)
        Assert.assertTrue(startsWithPackagesSize1 < allPackagesSize1)
        Assert.assertNotEquals(startsWithPackagesSize1.toLong(), notStartsWithPackagesSize1.toLong())
        Assert.assertNotEquals(notStartsWithPackagesSize1.toLong(), notStartsWithPackagesSize.toLong())
        Assert.assertEquals(allPackagesSize1.toLong(), startsWithPackagesSize1 + notStartsWithPackagesSize1.toLong())

        val allPackagesSize2 = context.listSimplePackageInfoByFilter(null).size
        val startsWithPackagesSize2 = context.listSimplePackageInfoByFilter(EndsWithPackageFilter("t")).size
        val notStartsWithPackagesSize2 = context.listSimplePackageInfoByFilter(NotEndsWithPackageFilter("t")).size
        Assert.assertTrue(allPackagesSize2 > 0)
        Assert.assertTrue(startsWithPackagesSize2 > 0)
        Assert.assertTrue(notStartsWithPackagesSize2 > 0)
        Assert.assertTrue(startsWithPackagesSize2 < allPackagesSize2)
        Assert.assertNotEquals(startsWithPackagesSize2.toLong(), notStartsWithPackagesSize2.toLong())
        Assert.assertEquals(allPackagesSize2.toLong(), startsWithPackagesSize2 + notStartsWithPackagesSize2.toLong())

        val allPackagesSize3 = context.listSimplePackageInfoByTypeInfoFlags(0, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val userPackagesSize3 = context.listSimplePackageInfoByTypeInfoFlags(PackageTypeFlags.USER, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val systemPackagesSize3 = context.listSimplePackageInfoByTypeInfoFlags(PackageTypeFlags.SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSize3 > 0)
        Assert.assertTrue(userPackagesSize3 > 0)
        Assert.assertTrue(systemPackagesSize3 > 0)
        Assert.assertTrue(userPackagesSize3 < allPackagesSize3)
        Assert.assertNotEquals(userPackagesSize3.toLong(), systemPackagesSize3.toLong())
        Assert.assertEquals(allPackagesSize3.toLong(), userPackagesSize3 + systemPackagesSize3.toLong())

        val allPackagesSize4 = context.listSimplePackageInfoByTypeInfoFlags(0, 0).size
        val releasePackagesSize4 = context.listSimplePackageInfoByTypeInfoFlags(PackageTypeFlags.RELEASE, 0).size
        val debuggablePackagesSize4 = context.listSimplePackageInfoByTypeInfoFlags(PackageTypeFlags.DEBUGGABLE, 0).size
        Assert.assertTrue(allPackagesSize4 > 0)
        Assert.assertTrue(releasePackagesSize4 > 0)
        Assert.assertNotEquals(releasePackagesSize4.toLong(), userPackagesSize3.toLong())
        Assert.assertTrue(debuggablePackagesSize4 > 0)
        Assert.assertTrue(releasePackagesSize4 < allPackagesSize4)
        Assert.assertNotEquals(releasePackagesSize4.toLong(), debuggablePackagesSize4.toLong())
        Assert.assertNotEquals(debuggablePackagesSize4.toLong(), systemPackagesSize3.toLong())
        Assert.assertEquals(allPackagesSize4.toLong(), releasePackagesSize4 + debuggablePackagesSize4.toLong())

        val allPackagesSize5 = context.listSimplePackageInfoByTypeFlags(0).size
        val userPackagesSize5 = context.listSimplePackageInfoByTypeFlags(PackageTypeFlags.USER).size
        val systemPackagesSize5 = context.listSimplePackageInfoByTypeFlags(PackageTypeFlags.SYSTEM).size
        Assert.assertTrue(allPackagesSize5 > 0)
        Assert.assertTrue(userPackagesSize5 > 0)
        Assert.assertTrue(systemPackagesSize5 > 0)
        Assert.assertTrue(userPackagesSize5 < allPackagesSize5)
        Assert.assertNotEquals(userPackagesSize5.toLong(), systemPackagesSize5.toLong())
        Assert.assertEquals(allPackagesSize5.toLong(), userPackagesSize5 + systemPackagesSize5.toLong())

        val allPackagesSiz6 = context.listSimplePackageInfoByInfoFlags(PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSiz6 > 0)

        val allPackagesSize7 = context.listSimplePackageInfoByInfoFlags(0).size
        Assert.assertTrue(allPackagesSize7 > 0)

        val allPackagesSize8 = context.listSimplePackageInfo().size
        Assert.assertTrue(allPackagesSize8 > 0)
        Assert.assertEquals(allPackagesSize7.toLong(), allPackagesSize8.toLong())
    }

    @Test
    fun testGetVersionCodeMap() {
        val context = InstrumentationRegistry.getInstrumentation().context

        Assert.assertTrue(Mapx.all(context.getAllPackageVersionCodeMapByFilterInfoFlags(null, 0)) { entry: Map.Entry<String?, Int> ->
            context.getPackageVersionCodeOr(entry.key!!, defaultValue = -1) == entry.value
        })
        val allPackageVersionCodeMapSize: Int = context.getAllPackageVersionCodeMapByFilterInfoFlags(null, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val startsWithPackageVersionCodeMapSize: Int = context.getAllPackageVersionCodeMapByFilterInfoFlags(EndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val notStartsWithPackageVersionCodeMapSize: Int = context.getAllPackageVersionCodeMapByFilterInfoFlags(NotEndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackageVersionCodeMapSize > 0)
        Assert.assertTrue(startsWithPackageVersionCodeMapSize > 0)
        Assert.assertTrue(notStartsWithPackageVersionCodeMapSize > 0)
        Assert.assertTrue(startsWithPackageVersionCodeMapSize < allPackageVersionCodeMapSize)
        Assert.assertNotEquals(startsWithPackageVersionCodeMapSize.toLong(), notStartsWithPackageVersionCodeMapSize.toLong())
        Assert.assertEquals(allPackageVersionCodeMapSize.toLong(), startsWithPackageVersionCodeMapSize + notStartsWithPackageVersionCodeMapSize.toLong())

        val allPackageVersionCodeMapSize1: Int = context.getAllPackageVersionCodeMapByFilterInfoFlags(null, 0).size
        val startsWithPackageVersionCodeMapSize1: Int = context.getAllPackageVersionCodeMapByFilterInfoFlags(EndsWithPackageFilter("s"), 0).size
        val notStartsWithPackageVersionCodeMapSize1: Int = context.getAllPackageVersionCodeMapByFilterInfoFlags(NotEndsWithPackageFilter("s"), 0).size
        Assert.assertTrue(allPackageVersionCodeMapSize1 > 0)
        Assert.assertTrue(startsWithPackageVersionCodeMapSize1 > 0)
        Assert.assertNotEquals(startsWithPackageVersionCodeMapSize1.toLong(), startsWithPackageVersionCodeMapSize.toLong())
        Assert.assertTrue(notStartsWithPackageVersionCodeMapSize1 > 0)
        Assert.assertTrue(startsWithPackageVersionCodeMapSize1 < allPackageVersionCodeMapSize1)
        Assert.assertNotEquals(startsWithPackageVersionCodeMapSize1.toLong(), notStartsWithPackageVersionCodeMapSize1.toLong())
        Assert.assertNotEquals(notStartsWithPackageVersionCodeMapSize1.toLong(), notStartsWithPackageVersionCodeMapSize.toLong())
        Assert.assertEquals(allPackageVersionCodeMapSize1.toLong(), startsWithPackageVersionCodeMapSize1 + notStartsWithPackageVersionCodeMapSize1.toLong())

        Assert.assertTrue(Mapx.all(context.getAllPackageVersionCodeMapByFilter(null)) { entry: Map.Entry<String?, Int> ->
            context.getPackageVersionCodeOr(entry.key!!, defaultValue = -1) == entry.value
        })
        val allPackageVersionCodeMapSize2: Int = context.getAllPackageVersionCodeMapByFilter(null).size
        val startsWithPackageVersionCodeMapSize2: Int = context.getAllPackageVersionCodeMapByFilter(EndsWithPackageFilter("t")).size
        val notStartsWithPackageVersionCodeMapSize2: Int = context.getAllPackageVersionCodeMapByFilter(NotEndsWithPackageFilter("t")).size
        Assert.assertTrue(allPackageVersionCodeMapSize2 > 0)
        Assert.assertTrue(startsWithPackageVersionCodeMapSize2 > 0)
        Assert.assertTrue(notStartsWithPackageVersionCodeMapSize2 > 0)
        Assert.assertTrue(startsWithPackageVersionCodeMapSize2 < allPackageVersionCodeMapSize2)
        Assert.assertNotEquals(startsWithPackageVersionCodeMapSize2.toLong(), notStartsWithPackageVersionCodeMapSize2.toLong())
        Assert.assertEquals(allPackageVersionCodeMapSize2.toLong(), startsWithPackageVersionCodeMapSize2 + notStartsWithPackageVersionCodeMapSize2.toLong())

        Assert.assertTrue(Mapx.all(context.getAllPackageVersionCodeMapByTypeInfoFlags(0, 0)) { entry: Map.Entry<String?, Int> ->
            context.getPackageVersionCodeOr(entry.key!!, defaultValue = -1) == entry.value
        })
        val allPackageVersionCodeMapSize3: Int = context.getAllPackageVersionCodeMapByTypeInfoFlags(0, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val userPackageVersionCodeMapSize3: Int = context.getAllPackageVersionCodeMapByTypeInfoFlags(PackageTypeFlags.USER, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val systemPackageVersionCodeMapSize3: Int = context.getAllPackageVersionCodeMapByTypeInfoFlags(PackageTypeFlags.SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackageVersionCodeMapSize3 > 0)
        Assert.assertTrue(userPackageVersionCodeMapSize3 > 0)
        Assert.assertTrue(systemPackageVersionCodeMapSize3 > 0)
        Assert.assertTrue(userPackageVersionCodeMapSize3 < allPackageVersionCodeMapSize3)
        Assert.assertNotEquals(userPackageVersionCodeMapSize3.toLong(), systemPackageVersionCodeMapSize3.toLong())
        Assert.assertEquals(allPackageVersionCodeMapSize3.toLong(), userPackageVersionCodeMapSize3 + systemPackageVersionCodeMapSize3.toLong())

        val allPackageVersionCodeMapSize4: Int = context.getAllPackageVersionCodeMapByTypeInfoFlags(0, 0).size
        val releasePackageVersionCodeMapSize4: Int = context.getAllPackageVersionCodeMapByTypeInfoFlags(PackageTypeFlags.RELEASE, 0).size
        val debuggablePackageVersionCodeMapSize4: Int = context.getAllPackageVersionCodeMapByTypeInfoFlags(PackageTypeFlags.DEBUGGABLE, 0).size
        Assert.assertTrue(allPackageVersionCodeMapSize4 > 0)
        Assert.assertTrue(releasePackageVersionCodeMapSize4 > 0)
        Assert.assertNotEquals(releasePackageVersionCodeMapSize4.toLong(), userPackageVersionCodeMapSize3.toLong())
        Assert.assertTrue(debuggablePackageVersionCodeMapSize4 > 0)
        Assert.assertTrue(releasePackageVersionCodeMapSize4 < allPackageVersionCodeMapSize4)
        Assert.assertNotEquals(releasePackageVersionCodeMapSize4.toLong(), debuggablePackageVersionCodeMapSize4.toLong())
        Assert.assertNotEquals(debuggablePackageVersionCodeMapSize4.toLong(), systemPackageVersionCodeMapSize3.toLong())
        Assert.assertEquals(allPackageVersionCodeMapSize4.toLong(), releasePackageVersionCodeMapSize4 + debuggablePackageVersionCodeMapSize4.toLong())

        Assert.assertTrue(Mapx.all(context.getAllPackageVersionCodeMapByTypeFlags(0)) { entry: Map.Entry<String?, Int> ->
            context.getPackageVersionCodeOr(entry.key!!, defaultValue = -1) == entry.value
        })
        val allPackageVersionCodeMapSize5: Int = context.getAllPackageVersionCodeMapByTypeFlags(0).size
        val userPackageVersionCodeMapSize5: Int = context.getAllPackageVersionCodeMapByTypeFlags(PackageTypeFlags.USER).size
        val systemPackageVersionCodeMapSize5: Int = context.getAllPackageVersionCodeMapByTypeFlags(PackageTypeFlags.SYSTEM).size
        Assert.assertTrue(allPackageVersionCodeMapSize5 > 0)
        Assert.assertTrue(userPackageVersionCodeMapSize5 > 0)
        Assert.assertTrue(systemPackageVersionCodeMapSize5 > 0)
        Assert.assertTrue(userPackageVersionCodeMapSize5 < allPackageVersionCodeMapSize5)
        Assert.assertNotEquals(userPackageVersionCodeMapSize5.toLong(), systemPackageVersionCodeMapSize5.toLong())
        Assert.assertEquals(allPackageVersionCodeMapSize5.toLong(), userPackageVersionCodeMapSize5 + systemPackageVersionCodeMapSize5.toLong())

        Assert.assertTrue(Mapx.all(context.getAllPackageVersionCodeMapByInfoFlags(0)) { entry: Map.Entry<String?, Int> ->
            context.getPackageVersionCodeOr(entry.key!!, defaultValue = -1) == entry.value
        })
        val allPackagesSiz6: Int = context.getAllPackageVersionCodeMapByInfoFlags(PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSiz6 > 0)

        val allPackageVersionCodeMapSize7: Int = context.getAllPackageVersionCodeMapByInfoFlags(0).size
        Assert.assertTrue(allPackageVersionCodeMapSize7 > 0)

        Assert.assertTrue(Mapx.all(context.getAllPackageVersionCodeMap()) { entry: Map.Entry<String?, Int> ->
            context.getPackageVersionCodeOr(entry.key!!, defaultValue = -1) == entry.value
        })
        val allPackageVersionCodeMapSize8: Int = context.getAllPackageVersionCodeMap().size
        Assert.assertTrue(allPackageVersionCodeMapSize8 > 0)
        Assert.assertEquals(allPackageVersionCodeMapSize7.toLong(), allPackageVersionCodeMapSize8.toLong())
    }

    @Test
    fun testListPackageName() {
        val context = InstrumentationRegistry.getInstrumentation().context

        Assert.assertTrue(Collectionx.all(context.listPackageNameByFilterInfoFlags(null, 0)) { packageName: String? ->
            context.isPackageInstalled(packageName!!)
        })
        val allPackageNameSize = context.listPackageNameByFilterInfoFlags(null, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val startsWithPackageNameSize = context.listPackageNameByFilterInfoFlags(EndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val notStartsWithPackageNameSize = context.listPackageNameByFilterInfoFlags(NotEndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackageNameSize > 0)
        Assert.assertTrue(startsWithPackageNameSize > 0)
        Assert.assertTrue(notStartsWithPackageNameSize > 0)
        Assert.assertTrue(startsWithPackageNameSize < allPackageNameSize)
        Assert.assertNotEquals(startsWithPackageNameSize.toLong(), notStartsWithPackageNameSize.toLong())
        Assert.assertEquals(allPackageNameSize.toLong(), startsWithPackageNameSize + notStartsWithPackageNameSize.toLong())

        val allPackageNameSize1 = context.listPackageNameByFilterInfoFlags(null, 0).size
        val startsWithPackageNameSize1 = context.listPackageNameByFilterInfoFlags(EndsWithPackageFilter("s"), 0).size
        val notStartsWithPackageNameSize1 = context.listPackageNameByFilterInfoFlags(NotEndsWithPackageFilter("s"), 0).size
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
        val startsWithPackageNameSize2 = context.listPackageNameByFilter(EndsWithPackageFilter("t")).size
        val notStartsWithPackageNameSize2 = context.listPackageNameByFilter(NotEndsWithPackageFilter("t")).size
        Assert.assertTrue(allPackageNameSize2 > 0)
        Assert.assertTrue(startsWithPackageNameSize2 > 0)
        Assert.assertTrue(notStartsWithPackageNameSize2 > 0)
        Assert.assertTrue(startsWithPackageNameSize2 < allPackageNameSize2)
        Assert.assertNotEquals(startsWithPackageNameSize2.toLong(), notStartsWithPackageNameSize2.toLong())
        Assert.assertEquals(allPackageNameSize2.toLong(), startsWithPackageNameSize2 + notStartsWithPackageNameSize2.toLong())

        Assert.assertTrue(Collectionx.all(context.listPackageNameByTypeInfoFlags(0, 0)) { packageName: String? ->
            context.isPackageInstalled(packageName!!)
        })
        val allPackageNameSize3 = context.listPackageNameByTypeInfoFlags(0, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val userPackageNameSize3 = context.listPackageNameByTypeInfoFlags(PackageTypeFlags.USER, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        val systemPackageNameSize3 = context.listPackageNameByTypeInfoFlags(PackageTypeFlags.SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackageNameSize3 > 0)
        Assert.assertTrue(userPackageNameSize3 > 0)
        Assert.assertTrue(systemPackageNameSize3 > 0)
        Assert.assertTrue(userPackageNameSize3 < allPackageNameSize3)
        Assert.assertNotEquals(userPackageNameSize3.toLong(), systemPackageNameSize3.toLong())
        Assert.assertEquals(allPackageNameSize3.toLong(), userPackageNameSize3 + systemPackageNameSize3.toLong())

        val allPackageNameSize4 = context.listPackageNameByTypeInfoFlags(0, 0).size
        val releasePackageNameSize4 = context.listPackageNameByTypeInfoFlags(PackageTypeFlags.RELEASE, 0).size
        val debuggablePackageNameSize4 = context.listPackageNameByTypeInfoFlags(PackageTypeFlags.DEBUGGABLE, 0).size
        Assert.assertTrue(allPackageNameSize4 > 0)
        Assert.assertTrue(releasePackageNameSize4 > 0)
        Assert.assertNotEquals(releasePackageNameSize4.toLong(), userPackageNameSize3.toLong())
        Assert.assertTrue(debuggablePackageNameSize4 > 0)
        Assert.assertTrue(releasePackageNameSize4 < allPackageNameSize4)
        Assert.assertNotEquals(releasePackageNameSize4.toLong(), debuggablePackageNameSize4.toLong())
        Assert.assertNotEquals(debuggablePackageNameSize4.toLong(), systemPackageNameSize3.toLong())
        Assert.assertEquals(allPackageNameSize4.toLong(), releasePackageNameSize4 + debuggablePackageNameSize4.toLong())

        Assert.assertTrue(Collectionx.all(context.listPackageNameByTypeFlags(0)) { packageName: String? ->
            context.isPackageInstalled(packageName!!)
        })
        val allPackageNameSize5 = context.listPackageNameByTypeFlags(0).size
        val userPackageNameSize5 = context.listPackageNameByTypeFlags(PackageTypeFlags.USER).size
        val systemPackageNameSize5 = context.listPackageNameByTypeFlags(PackageTypeFlags.SYSTEM).size
        Assert.assertTrue(allPackageNameSize5 > 0)
        Assert.assertTrue(userPackageNameSize5 > 0)
        Assert.assertTrue(systemPackageNameSize5 > 0)
        Assert.assertTrue(userPackageNameSize5 < allPackageNameSize5)
        Assert.assertNotEquals(userPackageNameSize5.toLong(), systemPackageNameSize5.toLong())
        Assert.assertEquals(allPackageNameSize5.toLong(), userPackageNameSize5 + systemPackageNameSize5.toLong())

        Assert.assertTrue(Collectionx.all(context.listPackageNameByInfoFlags(0)) { packageName: String? ->
            context.isPackageInstalled(packageName!!)
        })
        val allPackagesSiz6 = context.listPackageNameByInfoFlags(PackageManager.MATCH_UNINSTALLED_PACKAGES).size
        Assert.assertTrue(allPackagesSiz6 > 0)

        val allPackageNameSize7 = context.listPackageNameByInfoFlags(0).size
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
        val context = InstrumentationRegistry.getInstrumentation().context

        val allPackageNameSize = context.countPackageByFilterInfoFlags(null, PackageManager.MATCH_UNINSTALLED_PACKAGES)
        val startsWithPackageNameSize = context.countPackageByFilterInfoFlags(EndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES)
        val notStartsWithPackageNameSize = context.countPackageByFilterInfoFlags(NotEndsWithPackageFilter("t"), PackageManager.MATCH_UNINSTALLED_PACKAGES)
        Assert.assertTrue(allPackageNameSize > 0)
        Assert.assertTrue(startsWithPackageNameSize > 0)
        Assert.assertTrue(notStartsWithPackageNameSize > 0)
        Assert.assertTrue(startsWithPackageNameSize < allPackageNameSize)
        Assert.assertNotEquals(startsWithPackageNameSize.toLong(), notStartsWithPackageNameSize.toLong())
        Assert.assertEquals(allPackageNameSize.toLong(), startsWithPackageNameSize + notStartsWithPackageNameSize.toLong())

        val allPackageNameSize1 = context.countPackageByFilterInfoFlags(null, 0)
        val startsWithPackageNameSize1 = context.countPackageByFilterInfoFlags(EndsWithPackageFilter("s"), 0)
        val notStartsWithPackageNameSize1 = context.countPackageByFilterInfoFlags(NotEndsWithPackageFilter("s"), 0)
        Assert.assertTrue(allPackageNameSize1 > 0)
        Assert.assertTrue(startsWithPackageNameSize1 > 0)
        Assert.assertNotEquals(startsWithPackageNameSize1.toLong(), startsWithPackageNameSize.toLong())
        Assert.assertTrue(notStartsWithPackageNameSize1 > 0)
        Assert.assertTrue(startsWithPackageNameSize1 < allPackageNameSize1)
        Assert.assertNotEquals(startsWithPackageNameSize1.toLong(), notStartsWithPackageNameSize1.toLong())
        Assert.assertNotEquals(notStartsWithPackageNameSize1.toLong(), notStartsWithPackageNameSize.toLong())
        Assert.assertEquals(allPackageNameSize1.toLong(), startsWithPackageNameSize1 + notStartsWithPackageNameSize1.toLong())

        val allPackageNameSize2 = context.countPackageByFilter(null)
        val startsWithPackageNameSize2 = context.countPackageByFilter(EndsWithPackageFilter("t"))
        val notStartsWithPackageNameSize2 = context.countPackageByFilter(NotEndsWithPackageFilter("t"))
        Assert.assertTrue(allPackageNameSize2 > 0)
        Assert.assertTrue(startsWithPackageNameSize2 > 0)
        Assert.assertTrue(notStartsWithPackageNameSize2 > 0)
        Assert.assertTrue(startsWithPackageNameSize2 < allPackageNameSize2)
        Assert.assertNotEquals(startsWithPackageNameSize2.toLong(), notStartsWithPackageNameSize2.toLong())
        Assert.assertEquals(allPackageNameSize2.toLong(), startsWithPackageNameSize2 + notStartsWithPackageNameSize2.toLong())

        val allPackageNameSize3 = context.countPackageByTypeInfoFlags(0, PackageManager.MATCH_UNINSTALLED_PACKAGES)
        val userPackageNameSize3 = context.countPackageByTypeInfoFlags(PackageTypeFlags.USER, PackageManager.MATCH_UNINSTALLED_PACKAGES)
        val systemPackageNameSize3 = context.countPackageByTypeInfoFlags(PackageTypeFlags.SYSTEM, PackageManager.MATCH_UNINSTALLED_PACKAGES)
        Assert.assertTrue(allPackageNameSize3 > 0)
        Assert.assertTrue(userPackageNameSize3 > 0)
        Assert.assertTrue(systemPackageNameSize3 > 0)
        Assert.assertTrue(userPackageNameSize3 < allPackageNameSize3)
        Assert.assertNotEquals(userPackageNameSize3.toLong(), systemPackageNameSize3.toLong())
        Assert.assertEquals(allPackageNameSize3.toLong(), userPackageNameSize3 + systemPackageNameSize3.toLong())

        val allPackageNameSize4 = context.countPackageByTypeInfoFlags(0, 0)
        val releasePackageNameSize4 = context.countPackageByTypeInfoFlags(PackageTypeFlags.RELEASE, 0)
        val debuggablePackageNameSize4 = context.countPackageByTypeInfoFlags(PackageTypeFlags.DEBUGGABLE, 0)
        Assert.assertTrue(allPackageNameSize4 > 0)
        Assert.assertTrue(releasePackageNameSize4 > 0)
        Assert.assertNotEquals(releasePackageNameSize4.toLong(), userPackageNameSize3.toLong())
        Assert.assertTrue(debuggablePackageNameSize4 > 0)
        Assert.assertTrue(releasePackageNameSize4 < allPackageNameSize4)
        Assert.assertNotEquals(releasePackageNameSize4.toLong(), debuggablePackageNameSize4.toLong())
        Assert.assertNotEquals(debuggablePackageNameSize4.toLong(), systemPackageNameSize3.toLong())
        Assert.assertEquals(allPackageNameSize4.toLong(), releasePackageNameSize4 + debuggablePackageNameSize4.toLong())

        val allPackageNameSize5 = context.countPackageByTypeFlags(0)
        val userPackageNameSize5 = context.countPackageByTypeFlags(PackageTypeFlags.USER)
        val systemPackageNameSize5 = context.countPackageByTypeFlags(PackageTypeFlags.SYSTEM)
        Assert.assertTrue(allPackageNameSize5 > 0)
        Assert.assertTrue(userPackageNameSize5 > 0)
        Assert.assertTrue(systemPackageNameSize5 > 0)
        Assert.assertTrue(userPackageNameSize5 < allPackageNameSize5)
        Assert.assertNotEquals(userPackageNameSize5.toLong(), systemPackageNameSize5.toLong())
        Assert.assertEquals(allPackageNameSize5.toLong(), userPackageNameSize5 + systemPackageNameSize5.toLong())

        val allPackagesSiz6 = context.countPackageByInfoFlags(PackageManager.MATCH_UNINSTALLED_PACKAGES)
        Assert.assertTrue(allPackagesSiz6 > 0)

        val allPackageNameSize7 = context.countPackageByInfoFlags(0)
        Assert.assertTrue(allPackageNameSize7 > 0)

        val allPackageNameSize8 = context.countPackage()
        Assert.assertTrue(allPackageNameSize8 > 0)
        Assert.assertEquals(allPackageNameSize7.toLong(), allPackageNameSize8.toLong())
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetPackageApkFile() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val selfPackageName = context.packageName
        val errorPackageName = selfPackageName + "_no_no_no"

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
        val context = InstrumentationRegistry.getInstrumentation().context
        val selfPackageName = context.packageName
        val errorPackageName = selfPackageName + "_no_no_no"

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
        val context = InstrumentationRegistry.getInstrumentation().context
        val selfPackageName = context.packageName
        val errorPackageName = selfPackageName + "_no_no_no"

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
        val context = InstrumentationRegistry.getInstrumentation().context

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

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testCreateInstallPackageIntent() {
        val context = InstrumentationRegistry.getInstrumentation().context

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
        val context = InstrumentationRegistry.getInstrumentation().context

        val uninstallAppIntent = context.createUninstallPackageIntent(context.packageName)
        Assert.assertEquals(Intent.ACTION_DELETE, uninstallAppIntent.action)
        Assert.assertEquals(
                Uri.fromParts("package", context.packageName, null).toString(),
                Premisex.requireNotNull(uninstallAppIntent.data).toString()
        )
    }

    @Test
    fun testCreateLaunchPackageIntent() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"))
        val resolveInfoList = context.packageManager.queryIntentActivities(webIntent, 0)
        val resolveInfo = Premisex.requireNotNull(Collectionx.firstOrNull(resolveInfoList))
        val webBrowserPackageName = resolveInfo.activityInfo.packageName

        val launchAppIntent = Premisex.requireNotNull(context.createLaunchPackageIntent(webBrowserPackageName))
        Assert.assertEquals(Intent.ACTION_MAIN, launchAppIntent.action)
        Assert.assertNotNull(Collectionx.find(launchAppIntent.categories) { s: String? -> Stringx.equals(s, Intent.CATEGORY_LAUNCHER) })
        Assert.assertEquals(webBrowserPackageName, launchAppIntent.getPackage())
    }

    @Test
    fun testCreateApplicationDetailsSettingsIntent() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"))
        val resolveInfoList = context.packageManager.queryIntentActivities(webIntent, 0)
        val resolveInfo = Premisex.requireNotNull(Collectionx.firstOrNull(resolveInfoList))
        val webBrowserPackageName = resolveInfo.activityInfo.packageName

        val appDetailInSystemIntent = context.createApplicationDetailsSettingsIntent(webBrowserPackageName)
        Assert.assertEquals(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, appDetailInSystemIntent.action)
        Assert.assertEquals(
                Uri.fromParts("package", webBrowserPackageName, null).toString(),
                Premisex.requireNotNull(appDetailInSystemIntent.data).toString()
        )
    }

    private class EndsWithPackageFilter(private val endsWith: String) : PackageFilter {
        override fun accept(packageInfo: PackageInfo): Boolean {
            return packageInfo.packageName.endsWith(endsWith)
        }
    }

    private class NotEndsWithPackageFilter(private val endsWith: String) : PackageFilter {
        override fun accept(packageInfo: PackageInfo): Boolean {
            return !packageInfo.packageName.endsWith(endsWith)
        }
    }
}
