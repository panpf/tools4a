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

package com.github.panpf.tools4a.intent.ktx.test

import android.content.Intent
import android.os.Parcelable
import android.provider.MediaStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.fileprovider.ktx.getShareFileUri
import com.github.panpf.tools4a.intent.ktx.*
import com.github.panpf.tools4a.storage.ktx.getAppExternalFilesDir
import com.github.panpf.tools4j.io.ktx.closeQuietly
import com.github.panpf.tools4j.io.ktx.createNewFileOrThrow
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class IntentxTest {

    @Test
    fun testCreateLaunchDialingIntent() {
        val recordingIntent = "18801287688".createLaunchDialingIntent()
        Assert.assertEquals(Intent.ACTION_DIAL, recordingIntent.action)
        Assert.assertEquals("tel:18801287688", recordingIntent.data!!.toString())
    }

    @Test
    fun testCreateCallPhoneIntent() {
        val callPhoneIntent = "18801287688".createCallPhoneIntent()
        Assert.assertEquals(Intent.ACTION_CALL, callPhoneIntent.action)
        Assert.assertEquals("tel:18801287688", callPhoneIntent.data!!.toString())
    }

    @Test
    fun testCreateLaunchSendSmsIntent() {
        val sendSmsIntent = "18801287688".createLaunchSendSmsIntent("好好学习吧，天天向上")
        Assert.assertEquals(Intent.ACTION_SENDTO, sendSmsIntent.action)
        Assert.assertEquals("smsto:18801287688", sendSmsIntent.data!!.toString())
        Assert.assertEquals("好好学习吧，天天向上", sendSmsIntent.getStringExtra("sms_body"))
    }

    @Test
    fun testCreateLaunchWebBrowserIntent() {
        val webIntent = "https://github.com".createLaunchWebBrowserIntent()
        Assert.assertEquals(Intent.ACTION_VIEW, webIntent.action)
        Assert.assertEquals("https://github.com", webIntent.data!!.toString())
    }

    @Test
    fun testCreateScanFileBroadcastIntent() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()
        val file = File(context.getAppExternalFilesDir(), "testCreateScanFileBroadcastIntent.jpg")

        val scanFileIntent1 = context.getShareFileUri(file).createScanFileBroadcastIntent()
        Assert.assertEquals(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, scanFileIntent1.action)
        Assert.assertEquals(context.getShareFileUri(file).toString(),
                scanFileIntent1.data!!.toString())
        Assert.assertTrue(scanFileIntent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)
    }

    @Test
    fun testCreateActivityIntentByResolveInfo() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()

        val sendIntent = "https://github.com".createSendTextIntent()
        Assert.assertNull(sendIntent.component)

        val resolveInfos = context.packageManager.queryIntentActivities(sendIntent, 0)
        val resolveInfo = resolveInfos.firstOrNull()!!

        val finalIntent = sendIntent.createActivityIntentByResolveInfo(resolveInfo)
        Assert.assertEquals(sendIntent.action, finalIntent.action)
        Assert.assertNotNull(finalIntent.component)
    }

    @Test
    @Throws(IOException::class)
    fun testCreateTakePhotoIntent() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()

        val file = File(context.getAppExternalFilesDir(), "testCreateTakePhotoIntent_temp.jpg")
        try {
            file.createNewFileOrThrow()

            val takePhotoIntent1 = context.getShareFileUri(file).createTakePhotoIntent()
            Assert.assertEquals(MediaStore.ACTION_IMAGE_CAPTURE, takePhotoIntent1.action)
            Assert.assertEquals(context.getShareFileUri(file).toString(), takePhotoIntent1.getParcelableExtra<Parcelable>(MediaStore.EXTRA_OUTPUT).toString())
            Assert.assertTrue(takePhotoIntent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)
        } finally {
            file.deleteRecursively()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testCreateCropImageIntent() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()
        val sourceFile = File(context.getAppExternalFilesDir(), "testCreateCropImageIntent.jpg")
        val saveFile = File(context.getAppExternalFilesDir(), "testCreateCropImageIntent_temp.jpg")
        try {
            saveFile.createNewFileOrThrow()

            val inputStream = context.resources.openRawResource(R.drawable.rect)
            try {
                val bytes = inputStream.readBytes()
                sourceFile.writeBytes(bytes)
            } finally {
                inputStream.closeQuietly()
            }

            val sourceFileUri = context.getShareFileUri(sourceFile)
            val saveFileUri = context.getShareFileUri(saveFile)

            val cropImageIntent1 = sourceFileUri.createCropImageIntent(50, 100, saveFileUri)
            Assert.assertEquals("com.android.camera.action.CROP", cropImageIntent1.action)
            Assert.assertEquals(sourceFileUri.toString(), cropImageIntent1.data!!.toString())
            Assert.assertEquals("image/*", cropImageIntent1.type)
            Assert.assertEquals(50, cropImageIntent1.getIntExtra("aspectX", 0).toLong())
            Assert.assertEquals(100, cropImageIntent1.getIntExtra("aspectY", 0).toLong())
            Assert.assertEquals(50, cropImageIntent1.getIntExtra("outputX", 0).toLong())
            Assert.assertEquals(100, cropImageIntent1.getIntExtra("outputY", 0).toLong())
            Assert.assertEquals(saveFileUri.toString(), cropImageIntent1.getParcelableExtra<Parcelable>("output").toString())
            Assert.assertTrue(cropImageIntent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            val cropImageIntent2 = sourceFileUri.createCropImageIntent(50, 100, null)
            Assert.assertTrue(cropImageIntent2.getBooleanExtra("return-data", false))
            Assert.assertTrue(cropImageIntent2.getBooleanExtra("scale", false))
        } finally {
            sourceFile.deleteRecursively()
            saveFile.deleteRecursively()
        }
    }

    @Test
    fun testCreateSendTextIntent() {
        val intent1 = "好好学习吧，天天向上".createSendTextIntent("座右铭")
        Assert.assertEquals(Intent.ACTION_SEND, intent1.action)
        Assert.assertEquals("text/plain", intent1.type)
        Assert.assertEquals("好好学习吧，天天向上", intent1.getStringExtra(Intent.EXTRA_TEXT))
        Assert.assertEquals("座右铭", intent1.getStringExtra(Intent.EXTRA_SUBJECT))

        val intent2 = "好好学习吧，天天向上".createSendTextIntent()
        Assert.assertEquals(Intent.ACTION_SEND, intent2.action)
        Assert.assertEquals("text/plain", intent2.type)
        Assert.assertEquals("好好学习吧，天天向上", intent2.getStringExtra(Intent.EXTRA_TEXT))
        Assert.assertNull(intent2.getStringExtra(Intent.EXTRA_SUBJECT))
    }

    @Test
    @Throws(IOException::class)
    fun testCreateSendTextFileIntent() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()
        val textFile = File(context.getAppExternalFilesDir(), "testCreateSendTextFileIntent.txt")
        try {
            textFile.createNewFileOrThrow()
            textFile.writeText("testCreateSendTextFileIntent")

            val intent1 = context.getShareFileUri(textFile).createSendTextFileIntent("座右铭")
            Assert.assertEquals(Intent.ACTION_SEND, intent1.action)
            Assert.assertEquals(context.getShareFileUri(textFile).toString(),
                    intent1.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("text/plain", intent1.type)
            Assert.assertEquals("座右铭", intent1.getStringExtra(Intent.EXTRA_SUBJECT))
            Assert.assertTrue(intent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            val intent2 = context.getShareFileUri(textFile).createSendTextFileIntent()
            Assert.assertEquals(Intent.ACTION_SEND, intent2.action)
            Assert.assertEquals(context.getShareFileUri(textFile).toString(),
                    intent2.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("text/plain", intent2.type)
            Assert.assertNull(intent2.getStringExtra(Intent.EXTRA_SUBJECT))
            Assert.assertTrue(intent2.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)
        } finally {
            textFile.deleteRecursively()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testCreateSendImageFileIntent() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()
        val imageFile = File(context.getAppExternalFilesDir(), "testCreateSendImageFileIntent.jpg")
        try {
            imageFile.createNewFileOrThrow()
            val inputStream = context.resources.openRawResource(R.drawable.rect)
            try {
                val bytes = inputStream.readBytes()
                imageFile.writeBytes(bytes)
            } finally {
                inputStream.closeQuietly()
            }

            val intent1 = context.getShareFileUri(imageFile).createSendImageFileIntent("bmp")
            Assert.assertEquals(Intent.ACTION_SEND, intent1.action)
            Assert.assertEquals(context.getShareFileUri(imageFile).toString(),
                    intent1.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("image/bmp", intent1.type)
            Assert.assertTrue(intent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            val intent2 = context.getShareFileUri(imageFile).createSendImageFileIntent()
            Assert.assertEquals(Intent.ACTION_SEND, intent2.action)
            Assert.assertEquals(context.getShareFileUri(imageFile).toString(),
                    intent2.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("image/*", intent2.type)
            Assert.assertTrue(intent2.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)
        } finally {
            imageFile.deleteRecursively()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testCreateSendFileIntent() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()
        val textFile = File(context.getAppExternalFilesDir(), "testCreateSendFileIntent.txt")
        try {
            textFile.createNewFileOrThrow()
            textFile.writeText("testCreateSendFileIntent")

            val intent1 = context.getShareFileUri(textFile).createSendFileIntent("text/plain")
            Assert.assertEquals(Intent.ACTION_SEND, intent1.action)
            Assert.assertEquals(context.getShareFileUri(textFile).toString(),
                    intent1.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("text/plain", intent1.type)
            Assert.assertTrue(intent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)
        } finally {
            textFile.deleteRecursively()
        }
    }
}
