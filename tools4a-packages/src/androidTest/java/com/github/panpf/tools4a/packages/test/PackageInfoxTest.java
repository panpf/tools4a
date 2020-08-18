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

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.packages.PackageInfox;
import com.github.panpf.tools4j.lang.Stringx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PackageInfoxTest {

    @Test
    public void testFindActivityInfo() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();
        final String activityName = TestActivity.class.getName();

        ActivityInfo activityInfo1 = PackageInfox.findActivityInfo(context, context.getPackageName(), new PackageInfox.Predicate<ActivityInfo>() {
            @Override
            public boolean accept(@NonNull ActivityInfo activityInfo) {
                return Stringx.equals(activityInfo.name, activityName);
            }
        });
        Assert.assertNotNull(activityInfo1);
        Assert.assertEquals(activityName, activityInfo1.name);

        ActivityInfo activityInfo2 = PackageInfox.findSelfActivityInfo(context, new PackageInfox.Predicate<ActivityInfo>() {
            @Override
            public boolean accept(@NonNull ActivityInfo activityInfo) {
                return Stringx.equals(activityInfo.name, activityName);
            }
        });
        Assert.assertNotNull(activityInfo2);
        Assert.assertEquals(activityName, activityInfo2.name);

        ActivityInfo activityInfo3 = PackageInfox.findActivityInfoByName(context, context.getPackageName(), activityName);
        Assert.assertNotNull(activityInfo3);
        Assert.assertEquals(activityName, activityInfo3.name);

        ActivityInfo activityInfo4 = PackageInfox.findSelfActivityInfoByName(context, activityName);
        Assert.assertNotNull(activityInfo4);
        Assert.assertEquals(activityName, activityInfo4.name);

        try {
            Assert.assertNull(PackageInfox.findActivityInfoByName(context, context.getPackageName() + "1", activityName));
            Assert.fail();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNull(PackageInfox.findSelfActivityInfoByName(context, activityName + "1"));
    }

    @Test
    public void testFindServiceInfo() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();
        final String serviceName = TestService.class.getName();

        ServiceInfo serviceInfo1 = PackageInfox.findServiceInfo(context, context.getPackageName(), new PackageInfox.Predicate<ServiceInfo>() {
            @Override
            public boolean accept(@NonNull ServiceInfo serviceInfo) {
                return Stringx.equals(serviceInfo.name, serviceName);
            }
        });
        Assert.assertNotNull(serviceInfo1);
        Assert.assertEquals(serviceName, serviceInfo1.name);

        ServiceInfo serviceInfo2 = PackageInfox.findSelfServiceInfo(context, new PackageInfox.Predicate<ServiceInfo>() {
            @Override
            public boolean accept(@NonNull ServiceInfo serviceInfo) {
                return Stringx.equals(serviceInfo.name, serviceName);
            }
        });
        Assert.assertNotNull(serviceInfo2);
        Assert.assertEquals(serviceName, serviceInfo2.name);

        ServiceInfo serviceInfo3 = PackageInfox.findServiceInfoByName(context, context.getPackageName(), serviceName);
        Assert.assertNotNull(serviceInfo3);
        Assert.assertEquals(serviceName, serviceInfo3.name);

        ServiceInfo serviceInfo4 = PackageInfox.findSelfServiceInfoByName(context, serviceName);
        Assert.assertNotNull(serviceInfo4);
        Assert.assertEquals(serviceName, serviceInfo4.name);

        try {
            Assert.assertNull(PackageInfox.findServiceInfoByName(context, context.getPackageName() + "1", serviceName));
            Assert.fail();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNull(PackageInfox.findSelfServiceInfoByName(context, serviceName + "1"));
    }

    @Test
    public void testFindReceiverInfo() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();
        final String receiverName = TestReceiver.class.getName();

        ActivityInfo receiverInfo1 = PackageInfox.findReceiverInfo(context, context.getPackageName(), new PackageInfox.Predicate<ActivityInfo>() {
            @Override
            public boolean accept(@NonNull ActivityInfo receiverInfo) {
                return Stringx.equals(receiverInfo.name, receiverName);
            }
        });
        Assert.assertNotNull(receiverInfo1);
        Assert.assertEquals(receiverName, receiverInfo1.name);

        ActivityInfo receiverInfo2 = PackageInfox.findSelfReceiverInfo(context, new PackageInfox.Predicate<ActivityInfo>() {
            @Override
            public boolean accept(@NonNull ActivityInfo receiverInfo) {
                return Stringx.equals(receiverInfo.name, receiverName);
            }
        });
        Assert.assertNotNull(receiverInfo2);
        Assert.assertEquals(receiverName, receiverInfo2.name);

        ActivityInfo receiverInfo3 = PackageInfox.findReceiverInfoByName(context, context.getPackageName(), receiverName);
        Assert.assertNotNull(receiverInfo3);
        Assert.assertEquals(receiverName, receiverInfo3.name);

        ActivityInfo receiverInfo4 = PackageInfox.findSelfReceiverInfoByName(context, receiverName);
        Assert.assertNotNull(receiverInfo4);
        Assert.assertEquals(receiverName, receiverInfo4.name);

        try {
            Assert.assertNull(PackageInfox.findReceiverInfoByName(context, context.getPackageName() + "1", receiverName));
            Assert.fail();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNull(PackageInfox.findSelfReceiverInfoByName(context, receiverName + "1"));
    }

    @Test
    public void testFindProviderInfo() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();
        final String providerName = TestProvider.class.getName();

        ProviderInfo providerInfo1 = PackageInfox.findProviderInfo(context, context.getPackageName(), new PackageInfox.Predicate<ProviderInfo>() {
            @Override
            public boolean accept(@NonNull ProviderInfo providerInfo) {
                return Stringx.equals(providerInfo.name, providerName);
            }
        });
        Assert.assertNotNull(providerInfo1);
        Assert.assertEquals(providerName, providerInfo1.name);

        ProviderInfo providerInfo2 = PackageInfox.findSelfProviderInfo(context, new PackageInfox.Predicate<ProviderInfo>() {
            @Override
            public boolean accept(@NonNull ProviderInfo providerInfo) {
                return Stringx.equals(providerInfo.name, providerName);
            }
        });
        Assert.assertNotNull(providerInfo2);
        Assert.assertEquals(providerName, providerInfo2.name);

        ProviderInfo providerInfo3 = PackageInfox.findProviderInfoByName(context, context.getPackageName(), providerName);
        Assert.assertNotNull(providerInfo3);
        Assert.assertEquals(providerName, providerInfo3.name);

        ProviderInfo providerInfo4 = PackageInfox.findSelfProviderInfoByName(context, providerName);
        Assert.assertNotNull(providerInfo4);
        Assert.assertEquals(providerName, providerInfo4.name);

        try {
            Assert.assertNull(PackageInfox.findProviderInfoByName(context, context.getPackageName() + "1", providerName));
            Assert.fail();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNull(PackageInfox.findSelfProviderInfoByName(context, providerName + "1"));
    }

    @Test
    public void testFindPermissionInfo() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();
        final String permissionName = "com.github.panpf.tools4a.packages.test.share";

        PermissionInfo permissionInfo1 = PackageInfox.findPermissionInfo(context, context.getPackageName(), new PackageInfox.Predicate<PermissionInfo>() {
            @Override
            public boolean accept(@NonNull PermissionInfo permissionInfo) {
                return Stringx.equals(permissionInfo.name, permissionName);
            }
        });
        Assert.assertNotNull(permissionInfo1);
        Assert.assertEquals(permissionName, permissionInfo1.name);

        PermissionInfo permissionInfo2 = PackageInfox.findSelfPermissionInfo(context, new PackageInfox.Predicate<PermissionInfo>() {
            @Override
            public boolean accept(@NonNull PermissionInfo permissionInfo) {
                return Stringx.equals(permissionInfo.name, permissionName);
            }
        });
        Assert.assertNotNull(permissionInfo2);
        Assert.assertEquals(permissionName, permissionInfo2.name);

        PermissionInfo permissionInfo3 = PackageInfox.findPermissionInfoByName(context, context.getPackageName(), permissionName);
        Assert.assertNotNull(permissionInfo3);
        Assert.assertEquals(permissionName, permissionInfo3.name);

        PermissionInfo permissionInfo4 = PackageInfox.findSelfPermissionInfoByName(context, permissionName);
        Assert.assertNotNull(permissionInfo4);
        Assert.assertEquals(permissionName, permissionInfo4.name);

        try {
            Assert.assertNull(PackageInfox.findPermissionInfoByName(context, context.getPackageName() + "1", permissionName));
            Assert.fail();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNull(PackageInfox.findSelfPermissionInfoByName(context, permissionName + "1"));
    }

    @Test
    public void testFindRequestedPermissionInfo() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();
        final String requestedPermissionName = "android.permission.WRITE_EXTERNAL_STORAGE";

        String requestedPermission1 = PackageInfox.findRequestedPermission(context, context.getPackageName(), new PackageInfox.Predicate<String>() {
            @Override
            public boolean accept(@NonNull String requestedPermission) {
                return Stringx.equals(requestedPermission, requestedPermissionName);
            }
        });
        Assert.assertNotNull(requestedPermission1);
        Assert.assertEquals(requestedPermissionName, requestedPermission1);

        String requestedPermission2 = PackageInfox.findSelfRequestedPermission(context, new PackageInfox.Predicate<String>() {
            @Override
            public boolean accept(@NonNull String requestedPermission) {
                return Stringx.equals(requestedPermission, requestedPermissionName);
            }
        });
        Assert.assertNotNull(requestedPermission2);
        Assert.assertEquals(requestedPermissionName, requestedPermission2);

        String requestedPermission3 = PackageInfox.findRequestedPermissionByName(context, context.getPackageName(), requestedPermissionName);
        Assert.assertNotNull(requestedPermission3);
        Assert.assertEquals(requestedPermissionName, requestedPermission3);

        String requestedPermission4 = PackageInfox.findSelfRequestedPermissionByName(context, requestedPermissionName);
        Assert.assertNotNull(requestedPermission4);
        Assert.assertEquals(requestedPermissionName, requestedPermission4);

        try {
            Assert.assertNull(PackageInfox.findRequestedPermissionByName(context, context.getPackageName() + "1", requestedPermissionName));
            Assert.fail();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNull(PackageInfox.findSelfRequestedPermissionByName(context, requestedPermissionName + "1"));
    }

    @Test
    public void testFindMetaDataByName() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();
        final String metaDataName = "meta_test_name";
        final String metaDataValue = "meta_test_value";

        Pair<String, String> metaData1 = PackageInfox.findMetaDataWithName(context, context.getPackageName(), new PackageInfox.Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Stringx.equals(s, metaDataName);
            }
        });
        Assert.assertNotNull(metaData1);
        Assert.assertEquals(metaDataName + "/" + metaDataValue, metaData1.first + "/" + metaData1.second);

        Pair<String, String> metaData2 = PackageInfox.findSelfMetaDataWithName(context, new PackageInfox.Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Stringx.equals(s, metaDataName);
            }
        });
        Assert.assertNotNull(metaData2);
        Assert.assertEquals(metaDataName + "/" + metaDataValue, metaData2.first + "/" + metaData2.second);

        Pair<String, String> metaData3 = PackageInfox.findMetaDataByName(context, context.getPackageName(), metaDataName);
        Assert.assertNotNull(metaData3);
        Assert.assertEquals(metaDataName + "/" + metaDataValue, metaData3.first + "/" + metaData3.second);

        Pair<String, String> metaData4 = PackageInfox.findSelfMetaDataByName(context, metaDataName);
        Assert.assertNotNull(metaData4);
        Assert.assertEquals(metaDataName + "/" + metaDataValue, metaData4.first + "/" + metaData4.second);

        try {
            Assert.assertNotNull(PackageInfox.findMetaDataByName(context, context.getPackageName() + "1", metaDataName + "1"));
            Assert.fail();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNull(PackageInfox.findSelfMetaDataByName(context, metaDataName + "1"));
    }

    @Test
    public void testFindMetaDataByValue() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();
        final String metaDataName = "meta_test_name";
        final String metaDataValue = "meta_test_value";

        Pair<String, String> metaData1 = PackageInfox.findMetaDataWithValue(context, context.getPackageName(), new PackageInfox.Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Stringx.equals(s, metaDataValue);
            }
        });
        Assert.assertNotNull(metaData1);
        Assert.assertEquals(metaDataName + "/" + metaDataValue, metaData1.first + "/" + metaData1.second);

        Pair<String, String> metaData2 = PackageInfox.findSelfMetaDataWithValue(context, new PackageInfox.Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Stringx.equals(s, metaDataValue);
            }
        });
        Assert.assertNotNull(metaData2);
        Assert.assertEquals(metaDataName + "/" + metaDataValue, metaData2.first + "/" + metaData2.second);

        Pair<String, String> metaData3 = PackageInfox.findMetaDataByValue(context, context.getPackageName(), metaDataValue);
        Assert.assertNotNull(metaData3);
        Assert.assertEquals(metaDataName + "/" + metaDataValue, metaData3.first + "/" + metaData3.second);

        Pair<String, String> metaData4 = PackageInfox.findSelfMetaDataByValue(context, metaDataValue);
        Assert.assertNotNull(metaData4);
        Assert.assertEquals(metaDataName + "/" + metaDataValue, metaData4.first + "/" + metaData4.second);

        try {
            Assert.assertNotNull(PackageInfox.findMetaDataByValue(context, context.getPackageName() + "1", metaDataValue + "1"));
            Assert.fail();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNull(PackageInfox.findSelfMetaDataByValue(context, metaDataValue + "1"));
    }

    public static class TestActivity extends Activity {
    }
}
