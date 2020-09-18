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

import android.app.Activity
import android.content.pm.PackageManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.packages.Predicate
import com.github.panpf.tools4a.packages.ktx.*
import com.github.panpf.tools4j.lang.Stringx
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PackageInfoxTest {

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testFindActivityInfo() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val activityName = TestActivity::class.java.name

        val activityInfo1 = context.findActivityInfo(context.packageName, Predicate { activityInfo -> Stringx.equals(activityInfo.name, activityName) })
        Assert.assertNotNull(activityInfo1)
        Assert.assertEquals(activityName, activityInfo1!!.name)

        val activityInfo11 = context.findActivityInfo(context.packageName) { activityInfo -> Stringx.equals(activityInfo.name, activityName) }
        Assert.assertNotNull(activityInfo11)
        Assert.assertEquals(activityName, activityInfo11!!.name)

        val activityInfo2 = context.findSelfActivityInfo(Predicate { activityInfo -> Stringx.equals(activityInfo.name, activityName) })
        Assert.assertNotNull(activityInfo2)
        Assert.assertEquals(activityName, activityInfo2!!.name)

        val activityInfo22 = context.findSelfActivityInfo { activityInfo -> Stringx.equals(activityInfo.name, activityName) }
        Assert.assertNotNull(activityInfo22)
        Assert.assertEquals(activityName, activityInfo22!!.name)

        val activityInfo3 = context.findActivityInfoByName(context.packageName, activityName)
        Assert.assertNotNull(activityInfo3)
        Assert.assertEquals(activityName, activityInfo3!!.name)

        val activityInfo4 = context.findSelfActivityInfoByName(activityName)
        Assert.assertNotNull(activityInfo4)
        Assert.assertEquals(activityName, activityInfo4!!.name)

        try {
            Assert.assertNull(context.findActivityInfoByName(context.packageName + "1", activityName))
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        Assert.assertNull(context.findSelfActivityInfoByName(activityName + "1"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testFindServiceInfo() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val serviceName = TestService::class.java.name

        val serviceInfo1 = context.findServiceInfo(context.packageName, Predicate { serviceInfo -> Stringx.equals(serviceInfo.name, serviceName) })
        Assert.assertNotNull(serviceInfo1)
        Assert.assertEquals(serviceName, serviceInfo1!!.name)

        val serviceInfo11 = context.findServiceInfo(context.packageName) { serviceInfo -> Stringx.equals(serviceInfo.name, serviceName) }
        Assert.assertNotNull(serviceInfo11)
        Assert.assertEquals(serviceName, serviceInfo11!!.name)

        val serviceInfo2 = context.findSelfServiceInfo(Predicate { serviceInfo -> Stringx.equals(serviceInfo.name, serviceName) })
        Assert.assertNotNull(serviceInfo2)
        Assert.assertEquals(serviceName, serviceInfo2!!.name)

        val serviceInfo22 = context.findSelfServiceInfo { serviceInfo -> Stringx.equals(serviceInfo.name, serviceName) }
        Assert.assertNotNull(serviceInfo22)
        Assert.assertEquals(serviceName, serviceInfo22!!.name)

        val serviceInfo3 = context.findServiceInfoByName(context.packageName, serviceName)
        Assert.assertNotNull(serviceInfo3)
        Assert.assertEquals(serviceName, serviceInfo3!!.name)

        val serviceInfo4 = context.findSelfServiceInfoByName(serviceName)
        Assert.assertNotNull(serviceInfo4)
        Assert.assertEquals(serviceName, serviceInfo4!!.name)

        try {
            Assert.assertNull(context.findServiceInfoByName(context.packageName + "1", serviceName))
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        Assert.assertNull(context.findSelfServiceInfoByName(serviceName + "1"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testFindReceiverInfo() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val receiverName = TestReceiver::class.java.name

        val receiverInfo1 = context.findReceiverInfo(context.packageName, Predicate { receiverInfo -> Stringx.equals(receiverInfo.name, receiverName) })
        Assert.assertNotNull(receiverInfo1)
        Assert.assertEquals(receiverName, receiverInfo1!!.name)

        val receiverInfo11 = context.findReceiverInfo(context.packageName) { receiverInfo -> Stringx.equals(receiverInfo.name, receiverName) }
        Assert.assertNotNull(receiverInfo11)
        Assert.assertEquals(receiverName, receiverInfo11!!.name)

        val receiverInfo2 = context.findSelfReceiverInfo(Predicate { receiverInfo -> Stringx.equals(receiverInfo.name, receiverName) })
        Assert.assertNotNull(receiverInfo2)
        Assert.assertEquals(receiverName, receiverInfo2!!.name)

        val receiverInfo22 = context.findSelfReceiverInfo { receiverInfo -> Stringx.equals(receiverInfo.name, receiverName) }
        Assert.assertNotNull(receiverInfo22)
        Assert.assertEquals(receiverName, receiverInfo22!!.name)

        val receiverInfo3 = context.findReceiverInfoByName(context.packageName, receiverName)
        Assert.assertNotNull(receiverInfo3)
        Assert.assertEquals(receiverName, receiverInfo3!!.name)

        val receiverInfo4 = context.findSelfReceiverInfoByName(receiverName)
        Assert.assertNotNull(receiverInfo4)
        Assert.assertEquals(receiverName, receiverInfo4!!.name)

        try {
            Assert.assertNull(context.findReceiverInfoByName(context.packageName + "1", receiverName))
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        Assert.assertNull(context.findSelfReceiverInfoByName(receiverName + "1"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testFindProviderInfo() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val providerName = TestProvider::class.java.name

        val providerInfo1 = context.findProviderInfo(context.packageName, Predicate { providerInfo -> Stringx.equals(providerInfo.name, providerName) })
        Assert.assertNotNull(providerInfo1)
        Assert.assertEquals(providerName, providerInfo1!!.name)

        val providerInfo11 = context.findProviderInfo(context.packageName) { providerInfo -> Stringx.equals(providerInfo.name, providerName) }
        Assert.assertNotNull(providerInfo11)
        Assert.assertEquals(providerName, providerInfo11!!.name)

        val providerInfo2 = context.findSelfProviderInfo(Predicate { providerInfo -> Stringx.equals(providerInfo.name, providerName) })
        Assert.assertNotNull(providerInfo2)
        Assert.assertEquals(providerName, providerInfo2!!.name)

        val providerInfo22 = context.findSelfProviderInfo { providerInfo -> Stringx.equals(providerInfo.name, providerName) }
        Assert.assertNotNull(providerInfo22)
        Assert.assertEquals(providerName, providerInfo22!!.name)

        val providerInfo3 = context.findProviderInfoByName(context.packageName, providerName)
        Assert.assertNotNull(providerInfo3)
        Assert.assertEquals(providerName, providerInfo3!!.name)

        val providerInfo4 = context.findSelfProviderInfoByName(providerName)
        Assert.assertNotNull(providerInfo4)
        Assert.assertEquals(providerName, providerInfo4!!.name)

        try {
            Assert.assertNull(context.findProviderInfoByName(context.packageName + "1", providerName))
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        Assert.assertNull(context.findSelfProviderInfoByName(providerName + "1"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testFindPermissionInfo() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val permissionName = "com.github.panpf.tools4a.packages.ktx.test.share"

        val permissionInfo1 = context.findPermissionInfo(context.packageName, Predicate { permissionInfo -> Stringx.equals(permissionInfo.name, permissionName) })
        Assert.assertNotNull(permissionInfo1)
        Assert.assertEquals(permissionName, permissionInfo1!!.name)

        val permissionInfo11 = context.findPermissionInfo(context.packageName) { permissionInfo -> Stringx.equals(permissionInfo.name, permissionName) }
        Assert.assertNotNull(permissionInfo11)
        Assert.assertEquals(permissionName, permissionInfo11!!.name)

        val permissionInfo2 = context.findSelfPermissionInfo(Predicate { permissionInfo -> Stringx.equals(permissionInfo.name, permissionName) })
        Assert.assertNotNull(permissionInfo2)
        Assert.assertEquals(permissionName, permissionInfo2!!.name)

        val permissionInfo22 = context.findSelfPermissionInfo { permissionInfo -> Stringx.equals(permissionInfo.name, permissionName) }
        Assert.assertNotNull(permissionInfo22)
        Assert.assertEquals(permissionName, permissionInfo22!!.name)

        val permissionInfo3 = context.findPermissionInfoByName(context.packageName, permissionName)
        Assert.assertNotNull(permissionInfo3)
        Assert.assertEquals(permissionName, permissionInfo3!!.name)

        val permissionInfo4 = context.findSelfPermissionInfoByName(permissionName)
        Assert.assertNotNull(permissionInfo4)
        Assert.assertEquals(permissionName, permissionInfo4!!.name)

        try {
            Assert.assertNull(context.findPermissionInfoByName(context.packageName + "1", permissionName))
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        Assert.assertNull(context.findSelfPermissionInfoByName(permissionName + "1"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testFindRequestedPermissionInfo() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val requestedPermissionName = "android.permission.WRITE_EXTERNAL_STORAGE"

        val requestedPermission1 = context.findRequestedPermission(context.packageName) { requestedPermission -> Stringx.equals(requestedPermission, requestedPermissionName) }
        Assert.assertNotNull(requestedPermission1)
        Assert.assertEquals(requestedPermissionName, requestedPermission1)

        val requestedPermission11 = context.findRequestedPermission(context.packageName, Predicate { requestedPermission -> Stringx.equals(requestedPermission, requestedPermissionName) })
        Assert.assertNotNull(requestedPermission11)
        Assert.assertEquals(requestedPermissionName, requestedPermission11)

        val requestedPermission2 = context.findSelfRequestedPermission(Predicate { requestedPermission -> Stringx.equals(requestedPermission, requestedPermissionName) })
        Assert.assertNotNull(requestedPermission2)
        Assert.assertEquals(requestedPermissionName, requestedPermission2)

        val requestedPermission22 = context.findSelfRequestedPermission { requestedPermission -> Stringx.equals(requestedPermission, requestedPermissionName) }
        Assert.assertNotNull(requestedPermission22)
        Assert.assertEquals(requestedPermissionName, requestedPermission22)

        val requestedPermission3 = context.findRequestedPermissionByName(context.packageName, requestedPermissionName)
        Assert.assertNotNull(requestedPermission3)
        Assert.assertEquals(requestedPermissionName, requestedPermission3)

        val requestedPermission4 = context.findSelfRequestedPermissionByName(requestedPermissionName)
        Assert.assertNotNull(requestedPermission4)
        Assert.assertEquals(requestedPermissionName, requestedPermission4)

        try {
            Assert.assertNull(context.findRequestedPermissionByName(context.packageName + "1", requestedPermissionName))
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        Assert.assertNull(context.findSelfRequestedPermissionByName(requestedPermissionName + "1"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testFindMetaDataByName() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val metaDataName = "meta_test_name"
        val metaDataValue = "meta_test_value"

        val metaData1 = context.findMetaDataWithName(context.packageName, Predicate { s -> Stringx.equals(s, metaDataName) })
        Assert.assertNotNull(metaData1)
        Assert.assertEquals("$metaDataName/$metaDataValue", metaData1!!.first + "/" + metaData1.second)

        val metaData11 = context.findMetaDataWithName(context.packageName) { s -> Stringx.equals(s, metaDataName) }
        Assert.assertNotNull(metaData11)
        Assert.assertEquals("$metaDataName/$metaDataValue", metaData11!!.first + "/" + metaData11.second)

        val metaData2 = context.findSelfMetaDataWithName(Predicate { s -> Stringx.equals(s, metaDataName) })
        Assert.assertNotNull(metaData2)
        Assert.assertEquals("$metaDataName/$metaDataValue", metaData2!!.first + "/" + metaData2.second)

        val metaData22 = context.findSelfMetaDataWithName { s -> Stringx.equals(s, metaDataName) }
        Assert.assertNotNull(metaData22)
        Assert.assertEquals("$metaDataName/$metaDataValue", metaData22!!.first + "/" + metaData22.second)

        val metaData3 = context.findMetaDataByName(context.packageName, metaDataName)
        Assert.assertNotNull(metaData3)
        Assert.assertEquals("$metaDataName/$metaDataValue", metaData3!!.first + "/" + metaData3.second)

        val metaData4 = context.findSelfMetaDataByName(metaDataName)
        Assert.assertNotNull(metaData4)
        Assert.assertEquals("$metaDataName/$metaDataValue", metaData4!!.first + "/" + metaData4.second)

        try {
            Assert.assertNotNull(context.findMetaDataByName(context.packageName + "1", metaDataName + "1"))
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        Assert.assertNull(context.findSelfMetaDataByName(metaDataName + "1"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testFindMetaDataByValue() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val metaDataName = "meta_test_name"
        val metaDataValue = "meta_test_value"

        val metaData1 = context.findMetaDataWithValue(context.packageName, Predicate { s -> Stringx.equals(s, metaDataValue) })
        Assert.assertNotNull(metaData1)
        Assert.assertEquals("$metaDataName/$metaDataValue", metaData1!!.first + "/" + metaData1.second)

        val metaData11 = context.findMetaDataWithValue(context.packageName) { s -> Stringx.equals(s, metaDataValue) }
        Assert.assertNotNull(metaData11)
        Assert.assertEquals("$metaDataName/$metaDataValue", metaData11!!.first + "/" + metaData11.second)

        val metaData2 = context.findSelfMetaDataWithValue(Predicate { s -> Stringx.equals(s, metaDataValue) })
        Assert.assertNotNull(metaData2)
        Assert.assertEquals("$metaDataName/$metaDataValue", metaData2!!.first + "/" + metaData2.second)

        val metaData22 = context.findSelfMetaDataWithValue { s -> Stringx.equals(s, metaDataValue) }
        Assert.assertNotNull(metaData22)
        Assert.assertEquals("$metaDataName/$metaDataValue", metaData22!!.first + "/" + metaData22.second)

        val metaData3 = context.findMetaDataByValue(context.packageName, metaDataValue)
        Assert.assertNotNull(metaData3)
        Assert.assertEquals("$metaDataName/$metaDataValue", metaData3!!.first + "/" + metaData3.second)

        val metaData4 = context.findSelfMetaDataByValue(metaDataValue)
        Assert.assertNotNull(metaData4)
        Assert.assertEquals("$metaDataName/$metaDataValue", metaData4!!.first + "/" + metaData4.second)

        try {
            Assert.assertNotNull(context.findMetaDataByValue(context.packageName + "1", metaDataValue + "1"))
            Assert.fail()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        Assert.assertNull(context.findSelfMetaDataByValue(metaDataValue + "1"))
    }

    class TestActivity : Activity()
}
