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

import android.os.Environment;
import android.os.StatFs;

import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.storage.StatFsx;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class StatFsxTest {

    @Test
    public void testBytes() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        assertTrue(StatFsx.getFreeBytesCompat(statFs) <= StatFsx.getTotalBytesCompat(statFs));
        assertTrue(StatFsx.getAvailableBytesCompat(statFs) <= StatFsx.getTotalBytesCompat(statFs));
        assertTrue(StatFsx.getAvailableBytesCompat(statFs) <= StatFsx.getFreeBytesCompat(statFs));
    }
}