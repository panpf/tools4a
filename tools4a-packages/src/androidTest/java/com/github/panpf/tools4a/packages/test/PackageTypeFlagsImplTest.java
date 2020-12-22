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
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.panpf.tools4a.packages.PackageTypeFlags;
import com.github.panpf.tools4a.packages.PackageFilterTypeFlagsImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PackageTypeFlagsImplTest {

    @Test
    public void testConstructor() {
        final Context context = InstrumentationRegistry.getInstrumentation().getContext();

        try {
            new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.USER | PackageTypeFlags.SYSTEM);
            Assert.fail();
        } catch (Exception ignored) {
        }
        try {
            new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.USER | PackageTypeFlags.EXCLUDE_USER);
            Assert.fail();
        } catch (Exception ignored) {
        }
        try {
            new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.SYSTEM | PackageTypeFlags.EXCLUDE_SYSTEM);
            Assert.fail();
        } catch (Exception ignored) {
        }
        try {
            new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_USER | PackageTypeFlags.EXCLUDE_SYSTEM);
            Assert.fail();
        } catch (Exception ignored) {
        }

        try {
            new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.RELEASE | PackageTypeFlags.DEBUGGABLE);
            Assert.fail();
        } catch (Exception ignored) {
        }
        try {
            new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.RELEASE | PackageTypeFlags.EXCLUDE_RELEASE);
            Assert.fail();
        } catch (Exception ignored) {
        }
        try {
            new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.DEBUGGABLE | PackageTypeFlags.EXCLUDE_DEBUGGABLE);
            Assert.fail();
        } catch (Exception ignored) {
        }
        try {
            new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_RELEASE | PackageTypeFlags.EXCLUDE_DEBUGGABLE);
            Assert.fail();
        } catch (Exception ignored) {
        }


        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.USER);
        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.SYSTEM);

        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.RELEASE);
        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.DEBUGGABLE);

        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_USER);
        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_SYSTEM);

        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_RELEASE);
        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_DEBUGGABLE);

        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_SELF);


        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.USER | PackageTypeFlags.RELEASE | PackageTypeFlags.EXCLUDE_SELF);
        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.SYSTEM | PackageTypeFlags.DEBUGGABLE | PackageTypeFlags.EXCLUDE_SELF);

        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_USER | PackageTypeFlags.EXCLUDE_RELEASE | PackageTypeFlags.EXCLUDE_SELF);
        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_SYSTEM | PackageTypeFlags.EXCLUDE_DEBUGGABLE | PackageTypeFlags.EXCLUDE_SELF);

        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.USER | PackageTypeFlags.EXCLUDE_RELEASE | PackageTypeFlags.EXCLUDE_SELF);
        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.RELEASE | PackageTypeFlags.EXCLUDE_USER | PackageTypeFlags.EXCLUDE_SELF);

        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.SYSTEM | PackageTypeFlags.EXCLUDE_DEBUGGABLE | PackageTypeFlags.EXCLUDE_SELF);
        new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.DEBUGGABLE | PackageTypeFlags.EXCLUDE_SYSTEM | PackageTypeFlags.EXCLUDE_SELF);
    }

    @Test
    public void testFlags() {
        final Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Assert.assertTrue(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.USER)
                .accept(newPackageInfoByFlag(0)));
        Assert.assertFalse(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.USER)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_SYSTEM)));

        Assert.assertTrue(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.SYSTEM)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_SYSTEM)));
        Assert.assertFalse(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.SYSTEM)
                .accept(newPackageInfoByFlag(0)));

        Assert.assertTrue(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_USER)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_SYSTEM)));
        Assert.assertFalse(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_USER)
                .accept(newPackageInfoByFlag(0)));

        Assert.assertTrue(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_SYSTEM)
                .accept(newPackageInfoByFlag(0)));
        Assert.assertFalse(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_SYSTEM)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_SYSTEM)));


        Assert.assertTrue(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.RELEASE)
                .accept(newPackageInfoByFlag(0)));
        Assert.assertFalse(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.RELEASE)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_DEBUGGABLE)));

        Assert.assertTrue(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.DEBUGGABLE)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_DEBUGGABLE)));
        Assert.assertFalse(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.DEBUGGABLE)
                .accept(newPackageInfoByFlag(0)));

        Assert.assertTrue(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_RELEASE)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_DEBUGGABLE)));
        Assert.assertFalse(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_RELEASE)
                .accept(newPackageInfoByFlag(0)));

        Assert.assertTrue(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_DEBUGGABLE)
                .accept(newPackageInfoByFlag(0)));
        Assert.assertFalse(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_DEBUGGABLE)
                .accept(newPackageInfoByFlag(ApplicationInfo.FLAG_DEBUGGABLE)));


        Assert.assertTrue(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_SELF)
                .accept(newPackageInfoBySelf(context, false)));
        Assert.assertFalse(new PackageFilterTypeFlagsImpl(context, PackageTypeFlags.EXCLUDE_SELF)
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
