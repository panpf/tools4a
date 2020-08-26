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

public class PackageFilterFlagsImpl implements PackageFilter {

    @NonNull
    private final String selfPackageName;
    @PackageFilterFlags
    private int packageFilterFlags;

    public PackageFilterFlagsImpl(@NonNull Context context, @PackageFilterFlags int packageFilterFlags) {
        this.packageFilterFlags = packageFilterFlags;
        this.selfPackageName = context.getPackageName();

        if (matchFlag(PackageFilterFlags.ONLY_USER) && matchFlag(PackageFilterFlags.ONLY_SYSTEM)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.ONLY_USER' and 'PackageFilterFlags.ONLY_SYSTEM' at the same time");
        }
        if (matchFlag(PackageFilterFlags.ONLY_USER) && matchFlag(PackageFilterFlags.EXCLUDE_USER)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.ONLY_USER' and 'PackageFilterFlags.EXCLUDE_USER' at the same time");
        }
        if (matchFlag(PackageFilterFlags.ONLY_SYSTEM) && matchFlag(PackageFilterFlags.EXCLUDE_SYSTEM)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.ONLY_SYSTEM' and 'PackageFilterFlags.EXCLUDE_SYSTEM' at the same time");
        }
        if (matchFlag(PackageFilterFlags.EXCLUDE_USER) && matchFlag(PackageFilterFlags.EXCLUDE_SYSTEM)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.EXCLUDE_USER' and 'PackageFilterFlags.EXCLUDE_SYSTEM' at the same time");
        }

        if (matchFlag(PackageFilterFlags.ONLY_RELEASE) && matchFlag(PackageFilterFlags.ONLY_DEBUGGABLE)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.ONLY_RELEASE' and 'PackageFilterFlags.ONLY_DEBUGGABLE' at the same time");
        }
        if (matchFlag(PackageFilterFlags.ONLY_RELEASE) && matchFlag(PackageFilterFlags.EXCLUDE_RELEASE)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.ONLY_RELEASE' and 'PackageFilterFlags.EXCLUDE_RELEASE' at the same time");
        }
        if (matchFlag(PackageFilterFlags.ONLY_DEBUGGABLE) && matchFlag(PackageFilterFlags.EXCLUDE_DEBUGGABLE)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.ONLY_DEBUGGABLE' and 'PackageFilterFlags.EXCLUDE_DEBUGGABLE' at the same time");
        }
        if (matchFlag(PackageFilterFlags.EXCLUDE_RELEASE) && matchFlag(PackageFilterFlags.EXCLUDE_DEBUGGABLE)) {
            throw new IllegalArgumentException("packageFilterFlags param cannot set " +
                    "'PackageFilterFlags.EXCLUDE_RELEASE' and 'PackageFilterFlags.EXCLUDE_DEBUGGABLE' at the same time");
        }
    }

    private boolean matchFlag(int flag) {
        return (packageFilterFlags & flag) != 0;
    }

    @Override
    public boolean accept(@NonNull PackageInfo packageInfo) {
        if (matchFlag(PackageFilterFlags.ONLY_USER) || matchFlag(PackageFilterFlags.EXCLUDE_SYSTEM)) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                return false;
            }
        } else if (matchFlag(PackageFilterFlags.ONLY_SYSTEM) || matchFlag(PackageFilterFlags.EXCLUDE_USER)) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                return false;
            }
        }

        if (matchFlag(PackageFilterFlags.ONLY_RELEASE) || matchFlag(PackageFilterFlags.EXCLUDE_DEBUGGABLE)) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
                return false;
            }
        } else if (matchFlag(PackageFilterFlags.ONLY_DEBUGGABLE) || matchFlag(PackageFilterFlags.EXCLUDE_RELEASE)) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) == 0) {
                return false;
            }
        }

        //noinspection RedundantIfStatement
        if (matchFlag(PackageFilterFlags.EXCLUDE_SELF) && selfPackageName.equals(packageInfo.packageName)) {
            return false;
        }

        return true;
    }
}
