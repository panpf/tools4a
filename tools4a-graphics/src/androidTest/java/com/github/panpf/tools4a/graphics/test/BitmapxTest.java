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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.dimen.Dimenx;
import com.github.panpf.tools4a.graphics.Bitmapx;
import com.github.panpf.tools4a.graphics.Colorx;
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
public class BitmapxTest {

    @Test
    public void testRead() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        File file = Premisex.requireNotNull(Storagex.getFileIn(Storagex.getAppExternalCacheDirs(context), "rect.jpeg", 0));

        try {
            InputStream rectFileInputStream = null;
            OutputStream rectFileOutputStream = null;
            try {
                rectFileInputStream = context.getResources().openRawResource(R.drawable.rect);
                rectFileOutputStream = Filex.outputStream(file);
                IOx.copyTo(rectFileInputStream, rectFileOutputStream);
            } finally {
                IOx.closeQuietly(rectFileOutputStream);
                IOx.closeQuietly(rectFileInputStream);
            }

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            // file
            Bitmap bitmap1 = null;
            Bitmap bitmap2 = null;
            try {
                bitmap1 = Premisex.requireNotNull(Bitmapx.readBitmap(file));
                bitmap2 = Premisex.requireNotNull(Bitmapx.readBitmap(file, options));
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.getWidth(), options.inSampleSize), bitmap2.getWidth());
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.getHeight(), options.inSampleSize), bitmap2.getHeight());
            } finally {
                if (bitmap1 != null) bitmap1.recycle();
                if (bitmap2 != null) bitmap2.recycle();
                bitmap1 = null;
                bitmap2 = null;
            }

            // InputStream
            InputStream fileInputStream = null;
            try {
                try {
                    fileInputStream = Filex.inputStream(file);
                    bitmap1 = Premisex.requireNotNull(Bitmapx.readBitmap(fileInputStream));
                } finally {
                    IOx.closeQuietly(fileInputStream);
                }
                InputStream fileInputStream2 = null;
                try {
                    fileInputStream2 = Filex.inputStream(file);
                    bitmap2 = Premisex.requireNotNull(Bitmapx.readBitmap(fileInputStream2, null, options));
                } finally {
                    IOx.closeQuietly(fileInputStream2);
                }
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.getWidth(), options.inSampleSize), bitmap2.getWidth());
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.getHeight(), options.inSampleSize), bitmap2.getHeight());
            } finally {
                if (bitmap1 != null) bitmap1.recycle();
                if (bitmap2 != null) bitmap2.recycle();
                bitmap1 = null;
                bitmap2 = null;
            }

            // byte array
            byte[] fileBytes = Filex.readBytes(file);
            try {
                bitmap1 = Premisex.requireNotNull(Bitmapx.readBitmap(fileBytes, 0, fileBytes.length));
                bitmap2 = Premisex.requireNotNull(Bitmapx.readBitmap(fileBytes, 0, fileBytes.length, options));
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.getWidth(), options.inSampleSize), bitmap2.getWidth());
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.getHeight(), options.inSampleSize), bitmap2.getHeight());

                bitmap1 = Premisex.requireNotNull(Bitmapx.readBitmap(fileBytes));
                bitmap2 = Premisex.requireNotNull(Bitmapx.readBitmap(fileBytes, options));
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.getWidth(), options.inSampleSize), bitmap2.getWidth());
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.getHeight(), options.inSampleSize), bitmap2.getHeight());
            } finally {
                if (bitmap1 != null) bitmap1.recycle();
                if (bitmap2 != null) bitmap2.recycle();
                bitmap1 = null;
                bitmap2 = null;
            }

            // FileDescriptor
            FileInputStream fileInputStream2 = null;
            try {
                fileInputStream2 = Filex.inputStream(file);
                bitmap1 = Premisex.requireNotNull(Bitmapx.readBitmap(fileInputStream2.getFD()));
                bitmap2 = Premisex.requireNotNull(Bitmapx.readBitmap(fileInputStream2.getFD(), null, options));
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.getWidth(), options.inSampleSize), bitmap2.getWidth());
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.getHeight(), options.inSampleSize), bitmap2.getHeight());
            } finally {
                if (bitmap1 != null) bitmap1.recycle();
                if (bitmap2 != null) bitmap2.recycle();
                bitmap1 = null;
                bitmap2 = null;
                IOx.closeQuietly(fileInputStream2);
            }

            // Resources
            try {
                bitmap1 = Premisex.requireNotNull(Bitmapx.readBitmap(context.getResources(), R.drawable.rect));
                bitmap2 = Premisex.requireNotNull(Bitmapx.readBitmap(context.getResources(), R.drawable.rect, options));
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.getWidth(), options.inSampleSize), bitmap2.getWidth());
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.getHeight(), options.inSampleSize), bitmap2.getHeight());
            } finally {
                if (bitmap1 != null) bitmap1.recycle();
                if (bitmap2 != null) bitmap2.recycle();
                bitmap1 = null;
                //noinspection UnusedAssignment
                bitmap2 = null;
            }

            InputStream resInputStream = null;
            try {
                resInputStream = context.getResources().openRawResource(R.drawable.rect);
                bitmap1 = Premisex.requireNotNull(Bitmapx.readBitmap(context.getResources(), null, resInputStream, null, null));
            } finally {
                if (bitmap1 != null) bitmap1.recycle();
                IOx.closeQuietly(resInputStream);
            }
        } finally {
            Filex.deleteRecursively(file);
        }
    }

    @Test
    public void testCreateByColor() {
        Bitmap bitmap = Bitmapx.createByColor(100, 200, Bitmap.Config.ARGB_4444, Colorx.FUCHSIA);
        try {
            Assert.assertEquals(100, bitmap.getWidth());
            Assert.assertEquals(200, bitmap.getHeight());
            Assert.assertEquals(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                    ? Bitmap.Config.ARGB_8888 : Bitmap.Config.ARGB_4444, bitmap.getConfig());
            Assert.assertEquals(Colorx.FUCHSIA, bitmap.getPixel(0, 0));
        } finally {
            bitmap.recycle();
        }

        Bitmap bitmap2 = Bitmapx.createByColor(300, 600, Colorx.CYAN);
        try {
            Assert.assertEquals(300, bitmap2.getWidth());
            Assert.assertEquals(600, bitmap2.getHeight());
            Assert.assertEquals(Bitmap.Config.ARGB_8888, bitmap2.getConfig());
            Assert.assertEquals(Colorx.CYAN, bitmap2.getPixel(0, 0));
        } finally {
            bitmap2.recycle();
        }
    }

    @Test
    public void testWriteToFile() {
        Context context = InstrumentationRegistry.getContext();

        Bitmap bitmap = Bitmapx.createByColor(200, 200, Bitmap.Config.ARGB_8888, Colorx.FUCHSIA);
        File saveFile = Premisex.requireNotNull(Storagex.getFileIn(Storagex.getAppExternalCacheDirs(context), "testWriteToFile.jpeg", 0));
        try {
            Bitmapx.writeToFile(bitmap, saveFile, Bitmap.CompressFormat.JPEG, 100);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            bitmap.recycle();
        }

        Bitmap bitmap1 = Bitmapx.readBitmap(saveFile);
        try {
            Assert.assertNotNull(bitmap1);
            Assert.assertFalse(bitmap1.isRecycled());
        } finally {
            if (bitmap1 != null) {
                bitmap1.recycle();
            }
            Filex.deleteRecursively(saveFile);
        }
    }

    @Test
    public void testToByteArray() {
        Bitmap bitmap = Bitmapx.createByColor(200, 200, Bitmap.Config.ARGB_8888, Colorx.FUCHSIA);
        byte[] bytes;
        try {
            bytes = Bitmapx.toByteArray(bitmap, Bitmap.CompressFormat.JPEG, 100);
        } finally {
            bitmap.recycle();
        }

        Bitmap bitmap1 = Bitmapx.readBitmap(bytes);
        try {
            Assert.assertNotNull(bitmap1);
            Assert.assertFalse(bitmap1.isRecycled());
        } finally {
            if (bitmap1 != null) {
                bitmap1.recycle();
            }
        }
    }

    @Test
    public void tesToDrawable() {
        Context context = InstrumentationRegistry.getContext();

        Bitmap sourceBitmap = Bitmapx.createByColor(100, 100, Color.parseColor("#FF0000"));
        try {
            BitmapDrawable drawable = Bitmapx.toDrawable(sourceBitmap, context.getResources());
            Assert.assertNotNull(drawable);

            BitmapDrawable drawable2 = Bitmapx.toDrawable(sourceBitmap);
            Assert.assertNotNull(drawable2);
        } finally {
            sourceBitmap.recycle();
        }
    }

    @Test
    public void testCircular() {
        Context context = InstrumentationRegistry.getContext();

        Bitmap rectBitmap = Premisex.requireNotNull(Bitmapx.readBitmap(context.getResources(), R.drawable.rect));

        Bitmap circular1Bitmap = Bitmapx.circular(rectBitmap);
        circular1Bitmap.recycle();

        Bitmap circular2Bitmap = Bitmapx.circular(rectBitmap, Bitmap.Config.RGB_565);
        circular2Bitmap.recycle();

        Bitmap circular3Bitmap = Bitmapx.circular(rectBitmap, rectBitmap.getHeight() / 2);
        circular3Bitmap.recycle();

        Bitmap circular4Bitmap = Bitmapx.circular(rectBitmap, rectBitmap.getHeight() / 2, Bitmap.Config.RGB_565);
        circular4Bitmap.recycle();

        Bitmap circular5Bitmap = Bitmapx.circularTo(rectBitmap, Bitmap.createBitmap(rectBitmap.getHeight() / 2, rectBitmap.getHeight() / 2, Bitmap.Config.RGB_565));
        circular5Bitmap.recycle();

        rectBitmap.recycle();
    }

    @Test
    public void testCrop() {
        Context context = InstrumentationRegistry.getContext();

        Bitmap rectBitmap = Premisex.requireNotNull(Bitmapx.readBitmap(context.getResources(), R.drawable.rect));
        Rect srcRect = new Rect(0, 0, rectBitmap.getWidth() / 2, rectBitmap.getHeight() / 2);

        Bitmap centerCrop1Bitmap = Bitmapx.crop(rectBitmap, srcRect);
        centerCrop1Bitmap.recycle();

        Bitmap smallDstBitmap = Bitmapx.cropTo(rectBitmap, srcRect, Bitmap.createBitmap(srcRect.width() / 2, srcRect.height() / 2, Bitmap.Config.ARGB_8888));
        smallDstBitmap.recycle();

        try {
            Bitmapx.cropTo(rectBitmap, srcRect, Bitmap.createBitmap(srcRect.width() / 2, srcRect.height() / 2 + 10, Bitmap.Config.ARGB_8888));
            Assert.fail("No Exception");
        } catch (Exception ignored) {

        }

        rectBitmap.recycle();
    }

    @Test
    public void testCenterCrop() {
        Context context = InstrumentationRegistry.getContext();

        Bitmap rectBitmap = Premisex.requireNotNull(Bitmapx.readBitmap(context.getResources(), R.drawable.rect));

        Bitmap centerCrop1Bitmap = Bitmapx.centerCrop(rectBitmap, rectBitmap.getHeight() / 2, rectBitmap.getHeight());
        centerCrop1Bitmap.recycle();

        Bitmap centerCrop2Bitmap = Bitmapx.centerCrop(rectBitmap, rectBitmap.getHeight() / 2, rectBitmap.getHeight(), Bitmap.Config.RGB_565);
        centerCrop2Bitmap.recycle();

        Bitmap centerCrop3Bitmap = Bitmapx.centerCropTo(rectBitmap, Bitmap.createBitmap(rectBitmap.getHeight() / 2, rectBitmap.getHeight(), Bitmap.Config.RGB_565));
        centerCrop3Bitmap.recycle();

        rectBitmap.recycle();
    }

    @Test
    public void testTint() {
        Context context = InstrumentationRegistry.getContext();

        Bitmap operaBitmap = Premisex.requireNotNull(Bitmapx.readBitmap(context.getResources(), R.drawable.ic_opera));

        Bitmap centerCrop1Bitmap = Bitmapx.tint(operaBitmap, Colorx.YELLOW);
        centerCrop1Bitmap.recycle();

        operaBitmap.recycle();
    }

    @Test
    public void testInSampleSize() {
        Context context = InstrumentationRegistry.getContext();
        InputStream inputStream = context.getResources().openRawResource(R.drawable.rect);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        IOx.closeQuietly(inputStream);

        final int bitmapWidth = 99, bitmapHeight = 55;
        Bitmap newBitmap = Bitmapx.centerCrop(bitmap, bitmapWidth, bitmapHeight);
        bitmap.recycle();
        byte[] bytes = Bitmapx.toByteArray(newBitmap, Bitmap.CompressFormat.JPEG, 100);
        newBitmap.recycle();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap finalBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        int finalBitmapWidth = finalBitmap.getWidth();
        int finalBitmapHeight = finalBitmap.getHeight();
        finalBitmap.recycle();

        final int expectedBitmapWidth = Bitmapx.calculateSamplingSize(bitmapWidth, options.inSampleSize);
        final int expectedBitmapHeight = Bitmapx.calculateSamplingSize(bitmapHeight, options.inSampleSize);

        Assert.assertTrue("testInSampleSize: image size is " + bitmapWidth + "x" + bitmapHeight
                        + ", inSampleSize is " + options.inSampleSize
                        + ", expected bitmap size is " + expectedBitmapWidth + "x" + expectedBitmapHeight
                        + ", actual bitmap size is " + finalBitmapWidth + "x" + finalBitmapHeight,
                finalBitmapWidth == expectedBitmapWidth && finalBitmapHeight == expectedBitmapHeight);
    }

    @Test
    public void testRegionInSampleSize() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        InputStream inputStream = context.getResources().openRawResource(R.drawable.rect);

        BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);

        final int bitmapWidth = 99, bitmapHeight = 55;

        final int left = (regionDecoder.getWidth() - bitmapWidth) / 2;
        final int top = (regionDecoder.getHeight() - bitmapHeight) / 2;


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap finalBitmap = regionDecoder.decodeRegion(new Rect(left, top, left + bitmapWidth, top + bitmapHeight), options);
        int finalBitmapWidth = finalBitmap.getWidth();
        int finalBitmapHeight = finalBitmap.getHeight();
        finalBitmap.recycle();

        final int expectedBitmapWidth = Bitmapx.calculateSamplingSizeForRegion(bitmapWidth, options.inSampleSize);
        final int expectedBitmapHeight = Bitmapx.calculateSamplingSizeForRegion(bitmapHeight, options.inSampleSize);

        Assert.assertTrue("testRegionInSampleSize: image size is " + bitmapWidth + "x" + bitmapHeight
                        + ", inSampleSize is " + options.inSampleSize
                        + ", expected bitmap size is " + expectedBitmapWidth + "x" + expectedBitmapHeight
                        + ", actual bitmap size is " + finalBitmapWidth + "x" + finalBitmapHeight,
                finalBitmapWidth == expectedBitmapWidth && finalBitmapHeight == expectedBitmapHeight);
    }
    @Test
    public void testTextToBitmap() {
        Context context = InstrumentationRegistry.getContext();
        Bitmap bitmap = null;
        Bitmap bitmapCompact = null;
        Bitmap icon = null;
        Bitmap bitmapIcon = null;
        Bitmap bitmapIconCompact = null;
        try {
            bitmap = Bitmapx.textToBitmap("测试 : My ijk : 1234567890", Color.BLACK, Dimenx.dp2px(context, 14));
            Assert.assertFalse(bitmap.isRecycled());

            bitmapCompact = Bitmapx.textToBitmap("测试 : My ijk : 1234567890", Color.BLACK, Dimenx.dp2px(context, 14), true);
            Assert.assertFalse(bitmapCompact.isRecycled());

            icon = Bitmapx.readBitmap(context.getResources(), R.drawable.ic_opera);
            bitmapIcon = Bitmapx.textToBitmap("测试 : My ijk : 1234567890", Color.BLACK, Dimenx.dp2px(context, 14), icon);
            Assert.assertFalse(bitmapIcon.isRecycled());

            bitmapIconCompact = Bitmapx.textToBitmap("测试 : My ijk : 1234567890", Color.BLACK, Dimenx.dp2px(context, 14), true, icon);
            Assert.assertFalse(bitmapIconCompact.isRecycled());

            Assert.assertTrue(bitmap.getHeight() > bitmapCompact.getHeight());
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
            if (bitmapCompact != null) {
                bitmapCompact.recycle();
            }
            if (icon != null) {
                icon.recycle();
            }
            if (bitmapIcon != null) {
                bitmapIcon.recycle();
            }
            if (bitmapIconCompact != null) {
                bitmapIconCompact.recycle();
            }
        }
    }
}
