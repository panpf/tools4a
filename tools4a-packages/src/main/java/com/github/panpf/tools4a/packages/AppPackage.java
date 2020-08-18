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

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class AppPackage implements Parcelable {
    public static final Creator<AppPackage> CREATOR = new Creator<AppPackage>() {
        @Override
        public AppPackage createFromParcel(Parcel in) {
            return new AppPackage(in);
        }

        @Override
        public AppPackage[] newArray(int size) {
            return new AppPackage[size];
        }
    };
    @NonNull
    public String name;
    @NonNull
    public String packageName;
    public int versionCode;
    @NonNull
    public String versionName;
    @NonNull
    public String packageFilePath;
    public long packageSize;
    public long packageLastModifiedTime;
    public boolean systemApp;
    public boolean enabled;

    @SuppressWarnings("WeakerAccess")
    public AppPackage(@NonNull String name, @NonNull String packageName, int versionCode, @NonNull String versionName, @NonNull String packageFilePath,
                      long packageSize, long packageLastModifiedTime, boolean systemApp, boolean enabled) {
        this.name = name;
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.packageFilePath = packageFilePath;
        this.packageSize = packageSize;
        this.packageLastModifiedTime = packageLastModifiedTime;
        this.systemApp = systemApp;
        this.enabled = enabled;
    }

    @SuppressWarnings({"ConstantConditions", "WeakerAccess"})
    protected AppPackage(Parcel in) {
        name = in.readString();
        packageName = in.readString();
        versionCode = in.readInt();
        versionName = in.readString();
        packageFilePath = in.readString();
        packageSize = in.readLong();
        packageLastModifiedTime = in.readLong();
        systemApp = in.readByte() != 0;
        enabled = in.readByte() != 0;
    }

    @NonNull
    @Override
    public String toString() {
        return "AppPackage{" +
                "name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", packageFilePath='" + packageFilePath + '\'' +
                ", packageSize=" + packageSize +
                ", packageLastModifiedTime=" + packageLastModifiedTime +
                ", systemApp=" + systemApp +
                ", enabled=" + enabled +
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
        dest.writeString(versionName);
        dest.writeString(packageFilePath);
        dest.writeLong(packageSize);
        dest.writeLong(packageLastModifiedTime);
        dest.writeByte((byte) (systemApp ? 1 : 0));
        dest.writeByte((byte) (enabled ? 1 : 0));
    }
}
