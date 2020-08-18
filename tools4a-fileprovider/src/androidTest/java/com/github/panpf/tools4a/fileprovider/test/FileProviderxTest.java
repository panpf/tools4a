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

package com.github.panpf.tools4a.fileprovider.test;

import android.content.Context;
import android.net.Uri;
import android.os.Build;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.fileprovider.FileProviderx;
import com.github.panpf.tools4j.io.Filex;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class FileProviderxTest {

    @Test
    public void testGetShareFileUri() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        File file = new File(context.getExternalFilesDir(null), "testGetShareFileUri.txt");
        String authority = "com.github.panpf.tools4a.fileprovider.test.file.provider";
        try {
            Filex.writeText(file, "testGetShareFileUri");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri uri = FileProviderx.getShareFileUri(context, file, authority);
                Assert.assertEquals("content://" + authority + "/files/testGetShareFileUri.txt", uri.toString());

                try {
                    FileProviderx.getShareFileUri(context, file, authority + ".test");
                    Assert.fail();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            } else {
                Uri uri = FileProviderx.getShareFileUri(context, file, "");
                Assert.assertEquals(Uri.fromFile(file).toString(), uri.toString());
            }

            Uri uri = FileProviderx.getShareFileUri(context, file);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Assert.assertEquals("content://" + authority + "/files/testGetShareFileUri.txt", uri.toString());
            } else {
                Assert.assertEquals(Uri.fromFile(file).toString(), uri.toString());
            }
        } finally {
            Filex.deleteRecursively(file);
        }
    }
}
