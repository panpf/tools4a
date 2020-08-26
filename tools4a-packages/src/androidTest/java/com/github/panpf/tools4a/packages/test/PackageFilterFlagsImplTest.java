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
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import androidx.annotation.NonNull;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.packages.PackageFilterFlags;
import com.github.panpf.tools4a.packages.PackageFilterFlagsImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PackageFilterFlagsImplTest {

    @Test
    public void testConstructor() {
        final Context context = InstrumentationRegistry.getContext();

        try {
            new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_USER | PackageFilterFlags.ONLY_SYSTEM);
            Assert.fail();
        } catch (Exception ignored) {
        }
        try {
            new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_USER | PackageFilterFlags.EXCLUDE_USER);
            Assert.fail();
        } catch (Exception ignored) {
        }
        try {
            new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_SYSTEM | PackageFilterFlags.EXCLUDE_SYSTEM);
            Assert.fail();
        } catch (Exception ignored) {
        }
        try {
            new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_USER | PackageFilterFlags.EXCLUDE_SYSTEM);
            Assert.fail();
        } catch (Exception ignored) {
        }

        try {
            new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_RELEASE | PackageFilterFlags.ONLY_DEBUGGABLE);
            Assert.fail();
        } catch (Exception ignored) {
        }
        try {
            new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_RELEASE | PackageFilterFlags.EXCLUDE_RELEASE);
            Assert.fail();
        } catch (Exception ignored) {
        }
        try {
            new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_DEBUGGABLE | PackageFilterFlags.EXCLUDE_DEBUGGABLE);
            Assert.fail();
        } catch (Exception ignored) {
        }
        try {
            new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_RELEASE | PackageFilterFlags.EXCLUDE_DEBUGGABLE);
            Assert.fail();
        } catch (Exception ignored) {
        }


        new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_USER);
        new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_SYSTEM);

        new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_RELEASE);
        new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_DEBUGGABLE);

        new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_USER);
        new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_SYSTEM);

        new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_RELEASE);
        new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_DEBUGGABLE);

        new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_SELF);


        new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_USER | PackageFilterFlags.ONLY_RELEASE | PackageFilterFlags.EXCLUDE_SELF);
        new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_SYSTEM | PackageFilterFlags.ONLY_DEBUGGABLE | PackageFilterFlags.EXCLUDE_SELF);

        new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_USER | PackageFilterFlags.EXCLUDE_RELEASE | PackageFilterFlags.EXCLUDE_SELF);
        new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_SYSTEM | PackageFilterFlags.EXCLUDE_DEBUGGABLE | PackageFilterFlags.EXCLUDE_SELF);

        new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_USER | PackageFilterFlags.EXCLUDE_RELEASE | PackageFilterFlags.EXCLUDE_SELF);
        new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_RELEASE | PackageFilterFlags.EXCLUDE_USER | PackageFilterFlags.EXCLUDE_SELF);

        new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_SYSTEM | PackageFilterFlags.EXCLUDE_DEBUGGABLE | PackageFilterFlags.EXCLUDE_SELF);
        new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_DEBUGGABLE | PackageFilterFlags.EXCLUDE_SYSTEM | PackageFilterFlags.EXCLUDE_SELF);
    }

    @Test
    public void testFlags() {
        final Context context = InstrumentationRegistry.getContext();

        Assert.assertTrue(new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_USER)
                .accept(newPackageInfoByFlag(0)));
        Assert.assertFalse(new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_USER)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_SYSTEM)));

        Assert.assertTrue(new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_SYSTEM)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_SYSTEM)));
        Assert.assertFalse(new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_SYSTEM)
                .accept(newPackageInfoByFlag(0)));

        Assert.assertTrue(new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_USER)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_SYSTEM)));
        Assert.assertFalse(new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_USER)
                .accept(newPackageInfoByFlag(0)));

        Assert.assertTrue(new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_SYSTEM)
                .accept(newPackageInfoByFlag(0)));
        Assert.assertFalse(new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_SYSTEM)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_SYSTEM)));


        Assert.assertTrue(new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_RELEASE)
                .accept(newPackageInfoByFlag(0)));
        Assert.assertFalse(new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_RELEASE)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_DEBUGGABLE)));

        Assert.assertTrue(new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_DEBUGGABLE)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_DEBUGGABLE)));
        Assert.assertFalse(new PackageFilterFlagsImpl(context, PackageFilterFlags.ONLY_DEBUGGABLE)
                .accept(newPackageInfoByFlag(0)));

        Assert.assertTrue(new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_RELEASE)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_DEBUGGABLE)));
        Assert.assertFalse(new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_RELEASE)
                .accept(newPackageInfoByFlag(0)));

        Assert.assertTrue(new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_DEBUGGABLE)
                .accept(newPackageInfoByFlag(0)));
        Assert.assertFalse(new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_DEBUGGABLE)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_DEBUGGABLE)));


        Assert.assertTrue(new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_SELF)
                .accept(newPackageInfoBySelf(context, false)));
        Assert.assertFalse(new PackageFilterFlagsImpl(context, PackageFilterFlags.EXCLUDE_SELF)
                .accept(newPackageInfoBySelf(context, true)));
    }

    @NonNull
    private PackageInfo newPackageInfoByFlag(int flags) {
        PackageInfo packageInfo = new PackageInfo();
        packageInfo.applicationInfo = new ApplicationInfo();
        packageInfo.applicationInfo.flags = flags;
        return packageInfo;
    }

    @NonNull
    private PackageInfo newPackageInfoBySelf(@NonNull Context context, boolean ok) {
        PackageInfo packageInfo = new PackageInfo();
        if (ok) {
            packageInfo.packageName = context.getPackageName();
        } else {
            packageInfo.packageName = context.getPackageName() + "_nonono";
        }
        return packageInfo;
    }
}
