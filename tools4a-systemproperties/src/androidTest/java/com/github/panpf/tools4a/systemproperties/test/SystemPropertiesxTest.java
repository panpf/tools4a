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

package com.github.panpf.tools4a.systemproperties.test;

import android.os.Build;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.github.panpf.tools4a.systemproperties.SystemPropertiesx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SystemPropertiesxTest {

    @Test
    public void testGet() {
        Assert.assertEquals(Build.BRAND, SystemPropertiesx.get("ro.product.brand"));
        Assert.assertEquals("", SystemPropertiesx.get("ro.wifi.channels"));
        Assert.assertEquals("", SystemPropertiesx.get("custom"));
    }

    @Test
    public void testGetDef() {
        Assert.assertEquals("unknown", SystemPropertiesx.getOr("ro.wifi.channels", "unknown"));
        Assert.assertEquals("customValue", SystemPropertiesx.getOr("custom", "customValue"));
    }

    @Test
    public void testGetInt() {
        Assert.assertEquals(Build.VERSION.SDK_INT, SystemPropertiesx.getIntOr("ro.build.version.sdk", -1));
        Assert.assertEquals(-1, SystemPropertiesx.getIntOr("custom", -1));
    }

    @Test
    public void testGetLong() {
        Assert.assertEquals((long) Build.VERSION.SDK_INT, SystemPropertiesx.getLongOr("ro.build.version.sdk", (long) -1));
        Assert.assertEquals((long) -1, SystemPropertiesx.getLongOr("custom", (long) -1));
    }

    @Test
    public void testGetBoolean() {
        Assert.assertNotNull(String.valueOf(SystemPropertiesx.getBooleanOr("gsm.operator.isroaming", true)));
        Assert.assertTrue(SystemPropertiesx.getBooleanOr("custom", true));
    }
}
