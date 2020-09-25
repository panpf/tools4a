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

package com.github.panpf.tools4a.graphics.ktx.test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Rect
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.graphics.Bitmapx
import com.github.panpf.tools4a.graphics.Colorx
import com.github.panpf.tools4a.graphics.ktx.*
import com.github.panpf.tools4a.storage.ktx.getAppExternalCacheDirs
import com.github.panpf.tools4a.storage.ktx.getFileIn
import com.github.panpf.tools4j.io.ktx.closeQuietly
import com.github.panpf.tools4j.premise.ktx.requireNotNull
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

@RunWith(AndroidJUnit4::class)
class BitmapxTest {

    @Test
    @Throws(IOException::class)
    fun testRead() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val file = context.getAppExternalCacheDirs().getFileIn("rect.jpeg", 0).requireNotNull()

        try {
            var rectFileInputStream: InputStream? = null
            var rectFileOutputStream: OutputStream? = null
            try {
                rectFileInputStream = context.resources.openRawResource(R.drawable.rect)
                rectFileOutputStream = file.outputStream()
                rectFileInputStream.copyTo(rectFileOutputStream)
            } finally {
                rectFileOutputStream.closeQuietly()
                rectFileInputStream.closeQuietly()
            }

            val options = BitmapFactory.Options()
            options.inSampleSize = 2

            // file
            var bitmap1: Bitmap? = null
            var bitmap2: Bitmap? = null
            try {
                bitmap1 = file.readBitmap().requireNotNull()
                bitmap2 = file.readBitmap(options).requireNotNull()
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.width, options.inSampleSize).toLong(), bitmap2.width.toLong())
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.height, options.inSampleSize).toLong(), bitmap2.height.toLong())
            } finally {
                bitmap1?.recycle()
                bitmap2?.recycle()
            }

            // InputStream
            var fileInputStream: InputStream? = null
            try {
                try {
                    fileInputStream = file.inputStream()
                    bitmap1 = fileInputStream.readBitmap().requireNotNull()
                } finally {
                    fileInputStream.closeQuietly()
                }
                var fileInputStream2: InputStream? = null
                try {
                    fileInputStream2 = file.inputStream()
                    bitmap2 = fileInputStream2.readBitmap(null, options).requireNotNull()
                } finally {
                    fileInputStream2.closeQuietly()
                }
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1!!.width, options.inSampleSize).toLong(), bitmap2!!.width.toLong())
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.height, options.inSampleSize).toLong(), bitmap2.height.toLong())
            } finally {
                bitmap1?.recycle()
                bitmap2?.recycle()
                bitmap1 = null
                bitmap2 = null
            }

            // byte array
            val fileBytes = file.readBytes()
            try {
                bitmap1 = fileBytes.readBitmap(0, fileBytes.size).requireNotNull()
                bitmap2 = fileBytes.readBitmap(0, fileBytes.size, options).requireNotNull()
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.width, options.inSampleSize).toLong(), bitmap2.width.toLong())
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.height, options.inSampleSize).toLong(), bitmap2.height.toLong())

                bitmap1 = fileBytes.readBitmap().requireNotNull()
                bitmap2 = fileBytes.readBitmap(options).requireNotNull()
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.width, options.inSampleSize).toLong(), bitmap2.width.toLong())
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.height, options.inSampleSize).toLong(), bitmap2.height.toLong())
            } finally {
                bitmap1?.recycle()
                bitmap2?.recycle()
                bitmap1 = null
                bitmap2 = null
            }

            // FileDescriptor
            var fileInputStream2: FileInputStream? = null
            try {
                fileInputStream2 = file.inputStream()
                bitmap1 = fileInputStream2.fd.readBitmap().requireNotNull()
                bitmap2 = fileInputStream2.fd.readBitmap(null, options).requireNotNull()
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.width, options.inSampleSize).toLong(), bitmap2.width.toLong())
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.height, options.inSampleSize).toLong(), bitmap2.height.toLong())
            } finally {
                bitmap1?.recycle()
                bitmap2?.recycle()
                bitmap1 = null
                bitmap2 = null
                fileInputStream2.closeQuietly()
            }

            // Resources
            try {
                bitmap1 = context.resources.readBitmap(R.drawable.rect).requireNotNull()
                bitmap2 = context.resources.readBitmap(R.drawable.rect, options).requireNotNull()
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.width, options.inSampleSize).toLong(), bitmap2.width.toLong())
                Assert.assertEquals(Bitmapx.calculateSamplingSize(bitmap1.height, options.inSampleSize).toLong(), bitmap2.height.toLong())
            } finally {
                bitmap1?.recycle()
                bitmap2?.recycle()
                bitmap1 = null
                @Suppress("UNUSED_VALUE")
                bitmap2 = null
            }

            var resInputStream: InputStream? = null
            try {
                resInputStream = context.resources.openRawResource(R.drawable.rect)
                bitmap1 = context.resources.readBitmap(null, resInputStream, null, null).requireNotNull()
            } finally {
                bitmap1?.recycle()
                resInputStream.closeQuietly()
            }
        } finally {
            file.deleteRecursively()
        }
    }

    @Test
    fun testWriteToFile() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val bitmap = Bitmapx.createByColor(200, 200, Bitmap.Config.ARGB_8888, Colorx.FUCHSIA)
        val saveFile = context.getAppExternalCacheDirs().getFileIn("testWriteToFile.jpeg", 0).requireNotNull()
        try {
            bitmap.writeToFile(saveFile, Bitmap.CompressFormat.JPEG, 100)
        } catch (e: IOException) {
            e.printStackTrace()
            Assert.fail()
        } finally {
            bitmap.recycle()
        }

        val bitmap1 = saveFile.readBitmap()
        try {
            Assert.assertNotNull(bitmap1)
            Assert.assertFalse(bitmap1!!.isRecycled)
        } finally {
            bitmap1?.recycle()
            saveFile.deleteRecursively()
        }
    }

    @Test
    fun testToByteArray() {
        val bitmap = Bitmapx.createByColor(200, 200, Bitmap.Config.ARGB_8888, Colorx.FUCHSIA)
        val bytes: ByteArray
        try {
            bytes = bitmap.toByteArray(Bitmap.CompressFormat.JPEG, 100)
        } finally {
            bitmap.recycle()
        }

        val bitmap1 = bytes.readBitmap()
        try {
            Assert.assertNotNull(bitmap1)
            Assert.assertFalse(bitmap1!!.isRecycled)
        } finally {
            bitmap1?.recycle()
        }
    }

    @Test
    fun tesToDrawable() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val sourceBitmap = Bitmapx.createByColor(100, 100, Color.parseColor("#FF0000"))
        try {
            val drawable = sourceBitmap.toDrawable(context.resources)
            Assert.assertNotNull(drawable)

            val drawable2 = sourceBitmap.toDrawable()
            Assert.assertNotNull(drawable2)
        } finally {
            sourceBitmap.recycle()
        }
    }

    @Test
    fun testCircular() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val rectBitmap = context.resources.readBitmap(R.drawable.rect).requireNotNull()

        val circular1Bitmap = rectBitmap.circular()
        circular1Bitmap.recycle()

        val circular2Bitmap = rectBitmap.circular(Bitmap.Config.RGB_565)
        circular2Bitmap.recycle()

        val circular3Bitmap = rectBitmap.circular(rectBitmap.height / 2)
        circular3Bitmap.recycle()

        val circular4Bitmap = rectBitmap.circular(rectBitmap.height / 2, Bitmap.Config.RGB_565)
        circular4Bitmap.recycle()

        val circular5Bitmap = rectBitmap.circularTo(Bitmap.createBitmap(rectBitmap.height / 2, rectBitmap.height / 2, Bitmap.Config.RGB_565))
        circular5Bitmap.recycle()

        rectBitmap.recycle()
    }

    @Test
    fun testCrop() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val rectBitmap = context.resources.readBitmap(R.drawable.rect).requireNotNull()
        val srcRect = Rect(0, 0, rectBitmap.width / 2, rectBitmap.height / 2)

        val centerCrop1Bitmap = rectBitmap.crop(srcRect)
        centerCrop1Bitmap.recycle()

        val smallDstBitmap = rectBitmap.cropTo(srcRect, Bitmap.createBitmap(srcRect.width() / 2, srcRect.height() / 2, Bitmap.Config.ARGB_8888))
        smallDstBitmap.recycle()

        try {
            rectBitmap.cropTo(srcRect, Bitmap.createBitmap(srcRect.width() / 2, srcRect.height() / 2 + 10, Bitmap.Config.ARGB_8888))
            Assert.fail("No Exception")
        } catch (ignored: Exception) {

        }

        rectBitmap.recycle()
    }

    @Test
    fun testCenterCrop() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val rectBitmap = context.resources.readBitmap(R.drawable.rect).requireNotNull()

        val centerCrop1Bitmap = rectBitmap.centerCrop(rectBitmap.height / 2, rectBitmap.height)
        centerCrop1Bitmap.recycle()

        val centerCrop2Bitmap = rectBitmap.centerCrop(rectBitmap.height / 2, rectBitmap.height, Bitmap.Config.RGB_565)
        centerCrop2Bitmap.recycle()

        val centerCrop3Bitmap = rectBitmap.centerCropTo(Bitmap.createBitmap(rectBitmap.height / 2, rectBitmap.height, Bitmap.Config.RGB_565))
        centerCrop3Bitmap.recycle()

        rectBitmap.recycle()
    }

    @Test
    fun testTint() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val operaBitmap = context.resources.readBitmap(R.drawable.ic_opera).requireNotNull()

        val centerCrop1Bitmap = operaBitmap.tint(Colorx.YELLOW)
        centerCrop1Bitmap.recycle()

        operaBitmap.recycle()
    }

    @Test
    fun testTextToBitmap() {
        val context = InstrumentationRegistry.getInstrumentation().context
        var bitmap: Bitmap? = null
        var bitmapCompact: Bitmap? = null
        var icon: Bitmap? = null
        var bitmapIcon: Bitmap? = null
        var bitmapIconCompact: Bitmap? = null
        try {
            bitmap = "测试 : My ijk : 1234567890".textToBitmap(Color.BLACK, 14.dp2px)
            Assert.assertFalse(bitmap.isRecycled)

            bitmapCompact = "测试 : My ijk : 1234567890".textToBitmap(Color.BLACK, 14.dp2px, true)
            Assert.assertFalse(bitmapCompact.isRecycled)

            icon = Bitmapx.readBitmap(context.resources, R.drawable.ic_opera)
            bitmapIcon = "测试 : My ijk : 1234567890".textToBitmap(Color.BLACK, 14.dp2px, icon)
            Assert.assertFalse(bitmapIcon.isRecycled)

            bitmapIconCompact = "测试 : My ijk : 1234567890".textToBitmap(Color.BLACK, 14.dp2px, true, icon)
            Assert.assertFalse(bitmapIconCompact.isRecycled)

            Assert.assertTrue(bitmap.height > bitmapCompact.height)
        } finally {
            bitmap?.recycle()
            bitmapCompact?.recycle()
            icon?.recycle()
            bitmapIcon?.recycle()
            bitmapIconCompact?.recycle()
        }
    }
}
