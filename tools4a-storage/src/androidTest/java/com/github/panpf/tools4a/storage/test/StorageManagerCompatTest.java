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

package com.github.panpf.tools4a.storage.test;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.panpf.tools4a.storage.StorageManagerCompat;
import com.github.panpf.tools4a.storage.Storagex;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(AndroidJUnit4.class)
public class StorageManagerCompatTest {

    @Test
    public void testGetVolumeList() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertTrue(Storagex.storageManagerCompat(context).getVolumes().length >= 1);
    }

    @Test
    public void testGetStorageVolumes() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertTrue(Storagex.storageManagerCompat(context).getVolumeList().size() >= 1);
    }

    @Test
    public void testGetVolumePaths() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertTrue(Storagex.storageManagerCompat(context).getVolumePaths().length >= 1);
    }

    @Test
    public void testGetStorageVolume() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        StorageManagerCompat managerCompat = Storagex.storageManagerCompat(context);
        Assert.assertNotNull(managerCompat.getVolume(new File(managerCompat.getVolumePaths()[0])));
    }

    @Test
    public void testGetVolumeState() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        StorageManagerCompat managerCompat = Storagex.storageManagerCompat(context);
        Assert.assertNotEquals(managerCompat.getVolumeState(managerCompat.getVolumePaths()[0]), "unknown");
    }
}
