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

package com.github.panpf.tools4a.graphics.test;

import android.content.Context;
import android.graphics.Rect;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.graphics.Imagex;
import com.github.panpf.tools4a.storage.Storagex;
import com.github.panpf.tools4j.io.Filex;
import com.github.panpf.tools4j.io.IOx;
import com.github.panpf.tools4j.premise.Premisex;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RunWith(AndroidJUnit4.class)
public class ImagexTest {

    @Test
    public void testMimeType() throws IOException {
        Context context = InstrumentationRegistry.getContext();

        File operaFile = Premisex.requireNotNull(Storagex.getFileIn(Storagex.getAppExternalCacheDirs(context), "ic_opera.png", 0));
        File rectFile = Premisex.requireNotNull(Storagex.getFileIn(Storagex.getAppExternalCacheDirs(context), "rect.jpeg", 0));

        try {
            InputStream operaFileInputStream = null;
            OutputStream operaFileOutputStream = null;
            try {
                operaFileInputStream = context.getResources().openRawResource(R.drawable.ic_opera);
                operaFileOutputStream = Filex.outputStream(operaFile);
                IOx.copyTo(operaFileInputStream, operaFileOutputStream);
            } finally {
                IOx.closeQuietly(operaFileOutputStream);
                IOx.closeQuietly(operaFileInputStream);
            }
            InputStream rectFileInputStream = null;
            OutputStream rectFileOutputStream = null;
            try {
                rectFileInputStream = context.getResources().openRawResource(R.drawable.rect);
                rectFileOutputStream = Filex.outputStream(rectFile);
                IOx.copyTo(rectFileInputStream, rectFileOutputStream);
            } finally {
                IOx.closeQuietly(rectFileOutputStream);
                IOx.closeQuietly(rectFileInputStream);
            }

            Assert.assertEquals(Imagex.getMimeType(operaFile), "image/png");
            Assert.assertEquals(Imagex.getMimeType(rectFile), "image/jpeg");

            InputStream operaResInputStream = null;
            InputStream rectResInputStream = null;
            try {
                operaResInputStream = context.getResources().openRawResource(R.drawable.ic_opera);
                rectResInputStream = context.getResources().openRawResource(R.drawable.rect);
                Assert.assertEquals(Imagex.getMimeType(operaResInputStream), "image/png");
                Assert.assertEquals(Imagex.getMimeType(rectResInputStream), "image/jpeg");
            } finally {
                IOx.closeQuietly(operaResInputStream);
                IOx.closeQuietly(rectResInputStream);
            }

            byte[] operaBytes = Filex.readBytes(operaFile);
            byte[] rectBytes = Filex.readBytes(rectFile);

            Assert.assertEquals(Imagex.getMimeType(operaBytes, 0, operaBytes.length), "image/png");
            Assert.assertEquals(Imagex.getMimeType(rectBytes, 0, rectBytes.length), "image/jpeg");

            Assert.assertEquals(Imagex.getMimeType(operaBytes), "image/png");
            Assert.assertEquals(Imagex.getMimeType(rectBytes), "image/jpeg");

            operaFileInputStream = Filex.inputStream(operaFile);
            rectFileInputStream = Filex.inputStream(rectFile);
            try {
                Assert.assertEquals(Imagex.getMimeType(((FileInputStream) operaFileInputStream).getFD()), "image/png");
                Assert.assertEquals(Imagex.getMimeType(((FileInputStream) rectFileInputStream).getFD()), "image/jpeg");
            } finally {
                IOx.closeQuietly(operaFileInputStream);
                IOx.closeQuietly(rectFileInputStream);
            }

            Assert.assertEquals(Imagex.getMimeType(context.getResources(), R.drawable.ic_opera), "image/png");
            Assert.assertEquals(Imagex.getMimeType(context.getResources(), R.drawable.rect), "image/jpeg");

            operaFileInputStream = Filex.inputStream(operaFile);
            rectFileInputStream = Filex.inputStream(rectFile);
            try {
                Assert.assertEquals(Imagex.getMimeType(context.getResources(), null, operaFileInputStream, new Rect()), "image/png");
                Assert.assertEquals(Imagex.getMimeType(context.getResources(), null, rectFileInputStream, new Rect()), "image/jpeg");
            } finally {
                IOx.closeQuietly(operaFileInputStream);
                IOx.closeQuietly(rectFileInputStream);
            }
        } finally {
            Filex.deleteRecursively(operaFile);
            Filex.deleteRecursively(rectFile);
        }
    }

    @Test
    public void testMimeSubType() throws IOException {
        Context context = InstrumentationRegistry.getContext();

        File operaFile = Premisex.requireNotNull(Storagex.getFileIn(Storagex.getAppExternalCacheDirs(context), "ic_opera.png", 0));
        File rectFile = Premisex.requireNotNull(Storagex.getFileIn(Storagex.getAppExternalCacheDirs(context), "rect.jpeg", 0));

        try {
            InputStream operaFileInputStream = null;
            OutputStream operaFileOutputStream = null;
            try {
                operaFileInputStream = context.getResources().openRawResource(R.drawable.ic_opera);
                operaFileOutputStream = Filex.outputStream(operaFile);
                IOx.copyTo(operaFileInputStream, operaFileOutputStream);
            } finally {
                IOx.closeQuietly(operaFileOutputStream);
                IOx.closeQuietly(operaFileInputStream);
            }
            InputStream rectFileInputStream = null;
            OutputStream rectFileOutputStream = null;
            try {
                rectFileInputStream = context.getResources().openRawResource(R.drawable.rect);
                rectFileOutputStream = Filex.outputStream(rectFile);
                IOx.copyTo(rectFileInputStream, rectFileOutputStream);
            } finally {
                IOx.closeQuietly(rectFileOutputStream);
                IOx.closeQuietly(rectFileInputStream);
            }

            Assert.assertEquals(Imagex.getMimeSubType(operaFile), "png");
            Assert.assertEquals(Imagex.getMimeSubType(rectFile), "jpeg");

            InputStream operaResInputStream = null;
            InputStream rectResInputStream = null;
            try {
                operaResInputStream = context.getResources().openRawResource(R.drawable.ic_opera);
                rectResInputStream = context.getResources().openRawResource(R.drawable.rect);
                Assert.assertEquals(Imagex.getMimeSubType(operaResInputStream), "png");
                Assert.assertEquals(Imagex.getMimeSubType(rectResInputStream), "jpeg");
            } finally {
                IOx.closeQuietly(operaResInputStream);
                IOx.closeQuietly(rectResInputStream);
            }

            byte[] operaBytes = Filex.readBytes(operaFile);
            byte[] rectBytes = Filex.readBytes(rectFile);

            Assert.assertEquals(Imagex.getMimeSubType(operaBytes, 0, operaBytes.length), "png");
            Assert.assertEquals(Imagex.getMimeSubType(rectBytes, 0, rectBytes.length), "jpeg");

            Assert.assertEquals(Imagex.getMimeSubType(operaBytes), "png");
            Assert.assertEquals(Imagex.getMimeSubType(rectBytes), "jpeg");

            operaFileInputStream = Filex.inputStream(operaFile);
            rectFileInputStream = Filex.inputStream(rectFile);
            try {
                Assert.assertEquals(Imagex.getMimeSubType(((FileInputStream) operaFileInputStream).getFD()), "png");
                Assert.assertEquals(Imagex.getMimeSubType(((FileInputStream) rectFileInputStream).getFD()), "jpeg");
            } finally {
                IOx.closeQuietly(operaFileInputStream);
                IOx.closeQuietly(rectFileInputStream);
            }

            Assert.assertEquals(Imagex.getMimeSubType(context.getResources(), R.drawable.ic_opera), "png");
            Assert.assertEquals(Imagex.getMimeSubType(context.getResources(), R.drawable.rect), "jpeg");

            operaFileInputStream = Filex.inputStream(operaFile);
            rectFileInputStream = Filex.inputStream(rectFile);
            try {
                Assert.assertEquals(Imagex.getMimeSubType(context.getResources(), null, operaFileInputStream, new Rect()), "png");
                Assert.assertEquals(Imagex.getMimeSubType(context.getResources(), null, rectFileInputStream, new Rect()), "jpeg");
            } finally {
                IOx.closeQuietly(operaFileInputStream);
                IOx.closeQuietly(rectFileInputStream);
            }
        } finally {
            Filex.deleteRecursively(operaFile);
            Filex.deleteRecursively(rectFile);
        }
    }
}
