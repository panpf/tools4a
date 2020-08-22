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

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.File;

public class SimplePackageInfo implements Parcelable {

    public static final Creator<SimplePackageInfo> CREATOR = new Creator<SimplePackageInfo>() {
        @Override
        public SimplePackageInfo createFromParcel(Parcel in) {
            return new SimplePackageInfo(in);
        }

        @Override
        public SimplePackageInfo[] newArray(int size) {
            return new SimplePackageInfo[size];
        }
    };

    @NonNull
    public final String name;
    @NonNull
    public final String packageName;
    public final int versionCode;
    public final long longVersionCode;
    @NonNull
    public final String versionName;
    @NonNull
    public final String packageFilePath;
    public final long packageSize;
    public final long packageLastModifiedTime;
    @ApplicationInfoFlags
    public final int applicationInfoFlags;
    public final boolean enabled;
    private final boolean junitTest;

    public SimplePackageInfo(
            @NonNull String name, @NonNull String packageName, int versionCode, long longVersionCode,
            @NonNull String versionName, @NonNull String packageFilePath, long packageSize,
            long packageLastModifiedTime, @ApplicationInfoFlags int applicationInfoFlags,
            boolean enabled, boolean junitTest) {
        this.name = name;
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.longVersionCode = longVersionCode;
        this.versionName = versionName;
        this.packageFilePath = packageFilePath;
        this.packageSize = packageSize;
        this.packageLastModifiedTime = packageLastModifiedTime;
        this.applicationInfoFlags = applicationInfoFlags;
        this.enabled = enabled;
        this.junitTest = junitTest;
    }

    @SuppressWarnings({"ConstantConditions"})
    protected SimplePackageInfo(Parcel in) {
        name = in.readString();
        packageName = in.readString();
        versionCode = in.readInt();
        longVersionCode = in.readLong();
        versionName = in.readString();
        packageFilePath = in.readString();
        packageSize = in.readLong();
        packageLastModifiedTime = in.readLong();
        applicationInfoFlags = in.readInt();
        enabled = in.readByte() != 0;
        junitTest = in.readByte() != 0;
    }

    @NonNull
    public static SimplePackageInfo fromPackageInfo(@NonNull PackageInfo packageInfo, @NonNull PackageManager packageManager) {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        if (applicationInfo == null) throw new IllegalStateException("applicationInfo is null");
        CharSequence label = applicationInfo.loadLabel(packageManager);
        File packageFile = new File(applicationInfo.sourceDir);
        final String packageName = applicationInfo.packageName;
        final String versionName = packageInfo.versionName;
        //noinspection deprecation
        int versionCode = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? (int) packageInfo.getLongVersionCode() : packageInfo.versionCode;
        long longVersionCode = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
        // Need PackageManager.getPackageInfo(packageName, PackageManager.GET_INSTRUMENTATION)
        boolean junitTest = packageInfo.instrumentation != null && packageInfo.instrumentation.length > 0;
        return new SimplePackageInfo(
                label.toString(), packageName != null ? packageName : "",
                versionCode, longVersionCode, versionName != null ? versionName : "",
                packageFile.getPath(), packageFile.length(), packageFile.lastModified(),
                applicationInfo.flags, applicationInfo.enabled, junitTest);
    }

    public boolean isSystemPackage() {
        return (applicationInfoFlags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    public boolean isDebuggablePackage() {
        return (applicationInfoFlags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    public boolean isJunitTestPackage() {
        return junitTest;
    }

    public boolean matchApplicationInfoFlag(@ApplicationInfoFlags int applicationInfoFlag) {
        return (applicationInfoFlags & applicationInfoFlag) != 0;
    }

    @NonNull
    @Override
    public String toString() {
        return "SimplePackageInfo{" +
                "name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", versionCode=" + versionCode +
                ", longVersionCode=" + longVersionCode +
                ", versionName='" + versionName + '\'' +
                ", packageFilePath='" + packageFilePath + '\'' +
                ", packageSize=" + packageSize +
                ", packageLastModifiedTime=" + packageLastModifiedTime +
                ", applicationInfoFlags=" + applicationInfoFlags +
                ", enabled=" + enabled +
                ", junitTest=" + junitTest +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(packageName);
        dest.writeInt(versionCode);
        dest.writeLong(longVersionCode);
        dest.writeString(versionName);
        dest.writeString(packageFilePath);
        dest.writeLong(packageSize);
        dest.writeLong(packageLastModifiedTime);
        dest.writeInt(applicationInfoFlags);
        dest.writeByte((byte) (enabled ? 1 : 0));
        dest.writeByte((byte) (junitTest ? 1 : 0));
    }
}
