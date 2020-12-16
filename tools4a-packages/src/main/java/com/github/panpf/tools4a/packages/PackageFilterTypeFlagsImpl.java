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
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import androidx.annotation.NonNull;

public class PackageFilterTypeFlagsImpl implements PackageFilter {

    @NonNull
    private final String selfPackageName;
    @PackageTypeFlags
    private final int packageFilterFlags;

    public PackageFilterTypeFlagsImpl(@NonNull Context context, @PackageTypeFlags int packageFilterFlags) {
        this.packageFilterFlags = packageFilterFlags;
        this.selfPackageName = context.getPackageName();

        if (matchFlag(PackageTypeFlags.ONLY_USER) && matchFlag(PackageTypeFlags.ONLY_SYSTEM)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.ONLY_USER' and 'PackageFilterFlags.ONLY_SYSTEM' at the same time");
        }
        if (matchFlag(PackageTypeFlags.ONLY_USER) && matchFlag(PackageTypeFlags.EXCLUDE_USER)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.ONLY_USER' and 'PackageFilterFlags.EXCLUDE_USER' at the same time");
        }
        if (matchFlag(PackageTypeFlags.ONLY_SYSTEM) && matchFlag(PackageTypeFlags.EXCLUDE_SYSTEM)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.ONLY_SYSTEM' and 'PackageFilterFlags.EXCLUDE_SYSTEM' at the same time");
        }
        if (matchFlag(PackageTypeFlags.EXCLUDE_USER) && matchFlag(PackageTypeFlags.EXCLUDE_SYSTEM)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.EXCLUDE_USER' and 'PackageFilterFlags.EXCLUDE_SYSTEM' at the same time");
        }

        if (matchFlag(PackageTypeFlags.ONLY_RELEASE) && matchFlag(PackageTypeFlags.ONLY_DEBUGGABLE)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.ONLY_RELEASE' and 'PackageFilterFlags.ONLY_DEBUGGABLE' at the same time");
        }
        if (matchFlag(PackageTypeFlags.ONLY_RELEASE) && matchFlag(PackageTypeFlags.EXCLUDE_RELEASE)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.ONLY_RELEASE' and 'PackageFilterFlags.EXCLUDE_RELEASE' at the same time");
        }
        if (matchFlag(PackageTypeFlags.ONLY_DEBUGGABLE) && matchFlag(PackageTypeFlags.EXCLUDE_DEBUGGABLE)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.ONLY_DEBUGGABLE' and 'PackageFilterFlags.EXCLUDE_DEBUGGABLE' at the same time");
        }
        if (matchFlag(PackageTypeFlags.EXCLUDE_RELEASE) && matchFlag(PackageTypeFlags.EXCLUDE_DEBUGGABLE)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.EXCLUDE_RELEASE' and 'PackageFilterFlags.EXCLUDE_DEBUGGABLE' at the same time");
        }
    }

    private boolean matchFlag(int flag) {
        return (packageFilterFlags & flag) != 0;
    }

    @Override
    public boolean accept(@NonNull PackageInfo packageInfo) {
        if (matchFlag(PackageTypeFlags.ONLY_USER) || matchFlag(PackageTypeFlags.EXCLUDE_SYSTEM)) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                return false;
            }
        } else if (matchFlag(PackageTypeFlags.ONLY_SYSTEM) || matchFlag(PackageTypeFlags.EXCLUDE_USER)) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                return false;
            }
        }

        if (matchFlag(PackageTypeFlags.ONLY_RELEASE) || matchFlag(PackageTypeFlags.EXCLUDE_DEBUGGABLE)) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
                return false;
            }
        } else if (matchFlag(PackageTypeFlags.ONLY_DEBUGGABLE) || matchFlag(PackageTypeFlags.EXCLUDE_RELEASE)) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) == 0) {
                return false;
            }
        }

        //noinspection RedundantIfStatement
        if (matchFlag(PackageTypeFlags.EXCLUDE_SELF) && selfPackageName.equals(packageInfo.packageName)) {
            return false;
        }

        return true;
    }
}
