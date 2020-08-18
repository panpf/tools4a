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

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PackageInfox {

    private PackageInfox() {
    }

    @Nullable
    public static ActivityInfo findActivityInfo(@NonNull Context context, @NonNull String packageName,
                                                @NonNull Predicate<ActivityInfo> predicate) throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
        ActivityInfo[] activityInfos = info.activities;
        if (activityInfos != null) {
            for (ActivityInfo activityInfo : activityInfos) {
                if (predicate.accept(activityInfo)) {
                    return activityInfo;
                }
            }
        }
        return null;
    }

    @Nullable
    public static ActivityInfo findSelfActivityInfo(@NonNull Context context, @NonNull Predicate<ActivityInfo> predicate) {
        try {
            return findActivityInfo(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }

    @Nullable
    public static ActivityInfo findActivityInfoByName(@NonNull Context context, @NonNull String packageName,
                                                      @NonNull final String activityName) throws PackageManager.NameNotFoundException {
        return findActivityInfo(context, packageName, new Predicate<ActivityInfo>() {
            @Override
            public boolean accept(@NonNull ActivityInfo activityInfo) {
                final String activityInfoName = activityInfo.name;
                //noinspection EqualsReplaceableByObjectsCall,ConstantConditions
                return activityInfoName != null ? activityInfoName.equals(activityName) : activityName == null;
            }
        });
    }

    @Nullable
    public static ActivityInfo findSelfActivityInfoByName(@NonNull Context context, @NonNull final String activityName) {
        try {
            return findActivityInfoByName(context, context.getPackageName(), activityName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }


    @Nullable
    public static ServiceInfo findServiceInfo(@NonNull Context context, @NonNull String packageName,
                                              @NonNull Predicate<ServiceInfo> predicate) throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SERVICES);
        ServiceInfo[] serviceInfos = info.services;
        if (serviceInfos != null) {
            for (ServiceInfo serviceInfo : serviceInfos) {
                if (predicate.accept(serviceInfo)) {
                    return serviceInfo;
                }
            }
        }
        return null;
    }

    @Nullable
    public static ServiceInfo findSelfServiceInfo(@NonNull Context context, @NonNull Predicate<ServiceInfo> predicate) {
        try {
            return findServiceInfo(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }

    @Nullable
    public static ServiceInfo findServiceInfoByName(@NonNull Context context, @NonNull String packageName,
                                                    @NonNull final String serviceName) throws PackageManager.NameNotFoundException {
        return findServiceInfo(context, packageName, new Predicate<ServiceInfo>() {
            @Override
            public boolean accept(@NonNull ServiceInfo serviceInfo) {
                final String serviceInfoName = serviceInfo.name;
                //noinspection EqualsReplaceableByObjectsCall,ConstantConditions
                return serviceInfoName != null ? serviceInfoName.equals(serviceName) : serviceName == null;
            }
        });
    }

    @Nullable
    public static ServiceInfo findSelfServiceInfoByName(@NonNull Context context, @NonNull final String serviceName) {
        try {
            return findServiceInfoByName(context, context.getPackageName(), serviceName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }


    @Nullable
    public static ActivityInfo findReceiverInfo(@NonNull Context context, @NonNull String packageName,
                                                @NonNull Predicate<ActivityInfo> predicate) throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_RECEIVERS);
        ActivityInfo[] activityInfos = info.receivers;
        if (activityInfos != null) {
            for (ActivityInfo activityInfo : activityInfos) {
                if (predicate.accept(activityInfo)) {
                    return activityInfo;
                }
            }
        }
        return null;
    }

    @Nullable
    public static ActivityInfo findSelfReceiverInfo(@NonNull Context context, @NonNull Predicate<ActivityInfo> predicate) {
        try {
            return findReceiverInfo(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }

    @Nullable
    public static ActivityInfo findReceiverInfoByName(@NonNull Context context, @NonNull String packageName,
                                                      @NonNull final String receiverName) throws PackageManager.NameNotFoundException {
        return findReceiverInfo(context, packageName, new Predicate<ActivityInfo>() {
            @Override
            public boolean accept(@NonNull ActivityInfo activityInfo) {
                final String activityInfoName = activityInfo.name;
                //noinspection EqualsReplaceableByObjectsCall,ConstantConditions
                return activityInfoName != null ? activityInfoName.equals(receiverName) : receiverName == null;
            }
        });
    }

    @Nullable
    public static ActivityInfo findSelfReceiverInfoByName(@NonNull Context context, @NonNull final String receiverName) {
        try {
            return findReceiverInfoByName(context, context.getPackageName(), receiverName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }


    @Nullable
    public static ProviderInfo findProviderInfo(@NonNull Context context, @NonNull String packageName,
                                                @NonNull Predicate<ProviderInfo> predicate) throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_PROVIDERS);
        ProviderInfo[] providerInfos = info.providers;
        if (providerInfos != null) {
            for (ProviderInfo providerInfo : providerInfos) {
                if (predicate.accept(providerInfo)) {
                    return providerInfo;
                }
            }
        }
        return null;
    }

    @Nullable
    public static ProviderInfo findSelfProviderInfo(@NonNull Context context, @NonNull Predicate<ProviderInfo> predicate) {
        try {
            return findProviderInfo(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }

    @Nullable
    public static ProviderInfo findProviderInfoByName(@NonNull Context context, @NonNull String packageName,
                                                      @NonNull final String providerName) throws PackageManager.NameNotFoundException {
        return findProviderInfo(context, packageName, new Predicate<ProviderInfo>() {
            @Override
            public boolean accept(@NonNull ProviderInfo providerInfo) {
                final String providerInfoName = providerInfo.name;
                //noinspection EqualsReplaceableByObjectsCall,ConstantConditions
                return providerInfoName != null ? providerInfoName.equals(providerName) : providerName == null;
            }
        });
    }

    @Nullable
    public static ProviderInfo findSelfProviderInfoByName(@NonNull Context context, @NonNull final String providerName) {
        try {
            return findProviderInfoByName(context, context.getPackageName(), providerName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }


    @Nullable
    public static PermissionInfo findPermissionInfo(@NonNull Context context, @NonNull String packageName,
                                                    @NonNull Predicate<PermissionInfo> predicate) throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
        PermissionInfo[] permissionInfos = info.permissions;
        if (permissionInfos != null) {
            for (PermissionInfo permissionInfo : permissionInfos) {
                if (predicate.accept(permissionInfo)) {
                    return permissionInfo;
                }
            }
        }
        return null;
    }

    @Nullable
    public static PermissionInfo findSelfPermissionInfo(@NonNull Context context, @NonNull Predicate<PermissionInfo> predicate) {
        try {
            return findPermissionInfo(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }

    @Nullable
    public static PermissionInfo findPermissionInfoByName(@NonNull Context context, @NonNull String packageName,
                                                          @NonNull final String permissionName) throws PackageManager.NameNotFoundException {
        return findPermissionInfo(context, packageName, new Predicate<PermissionInfo>() {
            @Override
            public boolean accept(@NonNull PermissionInfo permissionInfo) {
                final String permissionInfoName = permissionInfo.name;
                //noinspection EqualsReplaceableByObjectsCall,ConstantConditions
                return permissionInfoName != null ? permissionInfoName.equals(permissionName) : permissionName == null;
            }
        });
    }

    @Nullable
    public static PermissionInfo findSelfPermissionInfoByName(@NonNull Context context, @NonNull final String permissionName) {
        try {
            return findPermissionInfoByName(context, context.getPackageName(), permissionName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }


    @Nullable
    public static String findRequestedPermission(@NonNull Context context, @NonNull String packageName,
                                                 @NonNull Predicate<String> predicate) throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
        String[] requestedPermissions = info.requestedPermissions;
        if (requestedPermissions != null) {
            for (String requestedPermission : requestedPermissions) {
                if (predicate.accept(requestedPermission)) {
                    return requestedPermission;
                }
            }
        }
        return null;
    }

    @Nullable
    public static String findSelfRequestedPermission(@NonNull Context context, @NonNull Predicate<String> predicate) {
        try {
            return findRequestedPermission(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }

    @Nullable
    public static String findRequestedPermissionByName(@NonNull Context context, @NonNull String packageName,
                                                       @NonNull final String permissionName) throws PackageManager.NameNotFoundException {
        return findRequestedPermission(context, packageName, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String requestedPermission) {
                //noinspection EqualsReplaceableByObjectsCall,ConstantConditions
                return requestedPermission != null ? requestedPermission.equals(permissionName) : permissionName == null;
            }
        });
    }

    @Nullable
    public static String findSelfRequestedPermissionByName(@NonNull Context context, @NonNull final String permissionName) {
        try {
            return findRequestedPermissionByName(context, context.getPackageName(), permissionName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }


    @Nullable
    public static Pair<String, String> findMetaDataWithName(@NonNull Context context, @NonNull String packageName,
                                                            @NonNull Predicate<String> predicate) throws PackageManager.NameNotFoundException {
        ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        Bundle metaData = info.metaData;
        if (metaData != null) {
            for (String key : metaData.keySet()) {
                if (predicate.accept(key)) {
                    Object value = metaData.get(key);
                    String valueString = value != null ? value.toString() : "";
                    return new Pair<>(key, valueString);
                }
            }
        }
        return null;
    }

    @Nullable
    public static Pair<String, String> findSelfMetaDataWithName(@NonNull Context context, @NonNull Predicate<String> predicate) {
        try {
            return findMetaDataWithName(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self ApplicationInfo", e);
        }
    }

    @Nullable
    public static Pair<String, String> findMetaDataByName(@NonNull Context context, @NonNull String packageName,
                                                          @NonNull final String expectedMetaDataName) throws PackageManager.NameNotFoundException {
        return findMetaDataWithName(context, packageName, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String metaDataName) {
                //noinspection EqualsReplaceableByObjectsCall,ConstantConditions
                return metaDataName != null ? metaDataName.equals(expectedMetaDataName) : expectedMetaDataName == null;
            }
        });
    }

    @Nullable
    public static Pair<String, String> findSelfMetaDataByName(@NonNull Context context, @NonNull String expectedMetaDataName) {
        try {
            return findMetaDataByName(context, context.getPackageName(), expectedMetaDataName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self ApplicationInfo", e);
        }
    }


    @Nullable
    public static Pair<String, String> findMetaDataWithValue(@NonNull Context context, @NonNull String packageName,
                                                             @NonNull Predicate<String> predicate) throws PackageManager.NameNotFoundException {
        ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        Bundle metaData = info.metaData;
        if (metaData != null) {
            for (String key : metaData.keySet()) {
                Object value = metaData.get(key);
                String valueString = value != null ? value.toString() : "";
                if (predicate.accept(valueString)) {
                    return new Pair<>(key, valueString);
                }
            }
        }
        return null;
    }

    @Nullable
    public static Pair<String, String> findSelfMetaDataWithValue(@NonNull Context context, @NonNull Predicate<String> predicate) {
        try {
            return findMetaDataWithValue(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self ApplicationInfo", e);
        }
    }

    @Nullable
    public static Pair<String, String> findMetaDataByValue(@NonNull Context context, @NonNull String packageName,
                                                           @NonNull final String expectedMetaDataValue) throws PackageManager.NameNotFoundException {
        return findMetaDataWithValue(context, packageName, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String metaDataValue) {
                //noinspection EqualsReplaceableByObjectsCall,ConstantConditions
                return metaDataValue != null ? metaDataValue.equals(expectedMetaDataValue) : expectedMetaDataValue == null;
            }
        });
    }

    @Nullable
    public static Pair<String, String> findSelfMetaDataByValue(@NonNull Context context, @NonNull final String expectedMetaDataValue) {
        try {
            return findMetaDataByValue(context, context.getPackageName(), expectedMetaDataValue);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self ApplicationInfo", e);
        }
    }

    public interface Predicate<T> {
        boolean accept(@NonNull T t);
    }
}
